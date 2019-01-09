package Controllers;

import SPADEPAC.*;
import abstractform.BasicForm;
import forms.*;
import graphics.CanvasItem;
import javafx.collections.ObservableList;
import model.DataManipulator;
import model.IdentificatorCreater;
import services.CanvasType;
import services.Constans;
import services.SegmentLists;
import services.SegmentType;
import tables.*;

import javafx.scene.control.TableView;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class FormFillController {

    private DataManipulator dataManipulator;
    private FormController formController;
    private CanvasItemController canvasItemController;
    private ArrayList<BasicForm> forms;
    private Project project;
    private CanvasController projectCanvasController;
    private IdentificatorCreater identificatorCreater;
    private SegmentLists segmentLists;
    private LinkControl linkControl;
    private Map<Integer,CanvasItem> canvasItemList;

    public FormFillController(FormController formController, DataManipulator dataManipulator, CanvasItemController canvasItemController,
                              IdentificatorCreater identificatorCreater, SegmentLists lists, LinkControl linkControl, Map<Integer,CanvasItem> canvasItemList){
        this.formController = formController;
        this.forms = formController.getForms();
        this.dataManipulator = dataManipulator;
        this.canvasItemController = canvasItemController;
        this.identificatorCreater = identificatorCreater;
        this.segmentLists = lists;
        this.linkControl = linkControl;
        this.canvasItemList = canvasItemList;
        this.project = dataManipulator.getProject();
    }


    public void createFormsFromData(){
        this.project = dataManipulator.getProject();
        fillProjectForm();
        fillCriterionForm();
        fillMilestoneForm();
        fillRoleTypeForm();
        fillRoleForm();
        fillCPR();
        fillPriorityForm();
        fillSeverityForm();
        fillRelationForm();
        fillResolutionForm();
        fillStatusForm();
        fillTypeForm();
        fillBranchForm();
        fillConfigurationForm();
        fillPhaseForm();
        fillActivityForm();
        fillIterationForm();
        fillWorkUnitForm(project.getWorkUnitIndexs(), projectCanvasController, CanvasType.Project);
        fillLink();

    }

    private String prepareStringForForm(String text){
        if (text == null){
            return "";
        }
        return text;
    }

    private void addDataToConfigurationTable(int id, int formId, Configuration configuration, ConfigurationTableForm configTable){

        String idName = createTableItemIdName(id,prepareStringForForm(configuration.getName()));
        String isRelease = "YES";
        if(configuration.isIsRelease() == null || !configuration.isIsRelease()){
            isRelease = "NO";
        }

        ConfigTable table = new ConfigTable(idName, isRelease, formId);

        configTable.getTableTV().getItems().add(table);
        segmentLists.getConfigObservable().add(idName);
    }

    private void fillConfigurationForm() {
        ConfigurationTableForm form = (ConfigurationTableForm)forms.get(Constans.configurationFormIndex);
        Configuration configuration = project.getConfiguration().get(0);
        if (configuration != null){
            fillConfigurationWithoutCreateId(project.getConfiguration().get(0), Constans.configurationFormIndex + 1);
            addDataToConfigurationTable(0, Constans.configurationFormIndex + 1, configuration, form);
        }

        int id;
        for (int i = 1; i < project.getConfiguration().size() - 1; i++){
            configuration = project.getConfiguration().get(i);
            id = formController.createNewConfiguratioFormWithoutManipulator();
            fillConfigurationWithoutCreateId(configuration, id);
            addDataToConfigurationTable(i, id, configuration, form);
        }
        id = formController.createNewForm(SegmentType.Configuration, CanvasType.Configuration);
        form.getMainPanel().setCenter(formController.getMainPanelFromForm(id));

    }
    private void fillConfigurationWithoutCreateId(Configuration configuration, int id){

        ConfigurationForm form = (ConfigurationForm) forms.get(id);

        String name = prepareStringForForm(configuration.getName());
        int authorIndex = prepareIndexForForm(configuration.getAuthorIndex());
        LocalDate createdDate = convertDateFromXML(configuration.getCreate());
        ArrayList<Integer> cprIndexs = prepareIndexForMultiComboBox(configuration.getCPRsIndexs());
        ArrayList<Integer> branchIndexs = prepareIndexForMultiComboBox(configuration.getBranchesIndexs());

        form.setDataToForm(name, createdDate, authorIndex, cprIndexs, branchIndexs);
        TableView<TagTable> tagView =  form.getTagForm().getTableTV();
        for(int j = 0; j < configuration.getTags().size(); j++){
            TagTable tagTable = new TagTable(configuration.getTags().get(j));
            tagView.getItems().add(tagTable);
        }
        CanvasController canvasController = form.getCanvasController();

        fillArtifactForm(configuration.getArtifactsIndexs(), canvasController);
        fillChangeForm(configuration.getChangesIndexs(), canvasController);
    }


    public void fillChangeForm(int oldFormId, CanvasController canvasController){
        int id = formController.createNewForm(SegmentType.Change, canvasController.getCanvasType());
        int changeId = identificatorCreater.getChangeIndex(oldFormId);
        int newChangeId = identificatorCreater.getChangeIndex(id);
        dataManipulator.copyDataFromChange(changeId, newChangeId);
        fillChangeForm(newChangeId, id, canvasController);
    }

    private void fillChangeForm(int segmentId, int formId, CanvasController canvasController) {

        Change change = project.getChanges().get(segmentId);
        ChangeForm form = (ChangeForm) forms.get(formId);

        boolean isExist = false;
        if(change.isExist()){
            isExist = true;
        }

        String name = prepareStringForForm(change.getName());
        String description = prepareStringForForm(change.getDescriptoin());
        form.setDataToForm(name, description, isExist);

        canvasController.addCanvasItemFromExistData(SegmentType.Change, formId, change.getName(), change.getCoordinates().getXCoordinate(),
                change.getCoordinates().getYCoordinate(), isExist);
   }

    private void fillChangeForm(List<Integer> changeIndexs, CanvasController canvasController) {
        for (int i = 0; i < changeIndexs.size(); i++){

            int id = formController.createNewChangeFormWithoutManipulator();
            fillChangeForm(changeIndexs.get(i), id, canvasController);
            }
    }

    public void fillArtifactForm(int oldFormId, CanvasController canvasController){
        int id = formController.createNewForm(SegmentType.Artifact, canvasController.getCanvasType());
        int artifactId = identificatorCreater.getArtifactIndex(oldFormId);
        int newArtifactId = identificatorCreater.getArtifactIndex(id);
        dataManipulator.copyDataFromArtifact(artifactId, newArtifactId);
        fillArtifactForm(newArtifactId, id, canvasController);
    }

    private void fillArtifactForm(int segmentId, int formId, CanvasController canvasController){
        Artifact artifact = project.getArtifacts().get(segmentId);
        ArtifactForm form = (ArtifactForm) forms.get(formId);

        int authorIndex = prepareIndexForForm(artifact.getAuthorIndex());
        LocalDate createDate = convertDateFromXML(artifact.getCreated());
        String name = prepareStringForForm(artifact.getName());
        String description = prepareStringForForm(artifact.getDescriptoin());
        boolean isExist = false;
        if(artifact.isExist()){
            isExist = true;
        }

        form.setDataToForm(name, description, authorIndex, createDate, artifact.getMimeType(), isExist);

        canvasController.addCanvasItemFromExistData(SegmentType.Artifact, formId, artifact.getName(), artifact.getCoordinates().getXCoordinate(),
                artifact.getCoordinates().getYCoordinate(), isExist);

    }
    private void fillArtifactForm(List<Integer> artifactsIndexs, CanvasController canvasController) {
        for (int i = 0; i < artifactsIndexs.size(); i++){

            int id = formController.createNewArtifactFormWithoutManipulator();
            fillArtifactForm(artifactsIndexs.get(i), id, canvasController);
            }
    }



    private ArrayList<Integer> prepareIndexForMultiComboBox(List<Integer> indexs) {

        ArrayList<Integer> values = new ArrayList();
        for (int i : indexs){
            values.add(prepareIndexForForm(i));
        }
        return values;
    }

    private void fillBranchForm() {
        BranchForm form = (BranchForm) forms.get(Constans.branchIndex);
        for (int i = 0; i < project.getBranches().size(); i++){
            Branch segment = project.getBranches().get(i);
            int id = formController.createTableItem(SegmentType.Branch);
            String idName = createTableItemIdName(id, segment.getName());
            String isMain = "NO";
            if(segment.isIsMain()){
                isMain = "YES";
            }

            BranchTable table = new BranchTable(idName, isMain, id);

            form.getTableTV().getItems().add(table);
            segmentLists.getBranchObservable().add(idName);
        }
    }

    private void fillTypeForm() {
        TypeForm form = (TypeForm) forms.get(Constans.wuTypeFormIndex);
        for (int i = 0; i < project.getTypes().size(); i++){
            Type segment = project.getTypes().get(i);
            int id = formController.createTableItem(SegmentType.Type);
            String idName = createTableItemIdName(id, segment.getName());
            String lclass = segment.getTypeClass();
            String superClass = segment.getTypeSuperClass();
            ClassTable table = new ClassTable(idName, lclass, superClass, id);

            form.getTableTV().getItems().add(table);
            segmentLists.getTypeObservable().add(table);
        }
    }

    private void fillStatusForm() {
        StatusForm form = (StatusForm) forms.get(Constans.statusFormIndex);
        for (int i = 0; i < project.getStatus().size(); i++){
            Status segment = project.getStatus().get(i);
            int id = formController.createTableItem(SegmentType.Status);
            String idName = createTableItemIdName(id, segment.getName());
            String lclass = segment.getStatusClass();
            String superClass = segment.getStatusSuperClass();
            ClassTable table = new ClassTable(idName, lclass, superClass, id);

            form.getTableTV().getItems().add(table);
            segmentLists.getStatusTypeObservable().add(table);
        }
    }

    private void fillResolutionForm() {
        ResolutionForm form = (ResolutionForm) forms.get(Constans.resolutionormIndex);
        for (int i = 0; i < project.getResolution().size(); i++){
            Resolution segment = project.getResolution().get(i);
            int id = formController.createTableItem(SegmentType.Resolution);
            String idName = createTableItemIdName(id, segment.getName());
            String lclass = segment.getResolutionClass();
            String superClass = segment.getResolutionSuperClass();
            ClassTable table = new ClassTable(idName, lclass, superClass, id);

            form.getTableTV().getItems().add(table);
            segmentLists.getResolutionTypeObservable().add(table);
        }
    }

    private void fillRelationForm() {
        RelationForm form = (RelationForm) forms.get(Constans.relationFormIndex);
        for (int i = 0; i < project.getRelation().size(); i++){
            Relation segment = project.getRelation().get(i);
            int id = formController.createTableItem(SegmentType.Relation);
            String idName = createTableItemIdName(id, segment.getName());
            String lclass = segment.getRelationClass();
            String superClass = segment.getRelationSuperClass();
            ClassTable table = new ClassTable(idName, lclass, superClass, id);

            form.getTableTV().getItems().add(table);
            segmentLists.getRelationTypeObservable().add(table);
        }
    }

    private void fillSeverityForm() {
        SeverityForm form = (SeverityForm) forms.get(Constans.severityFormIndex);
        for (int i = 0; i < project.getSeverity().size(); i++){
            Severity segment = project.getSeverity().get(i);
            int id = formController.createTableItem(SegmentType.Severity);
            String idName = createTableItemIdName(id, segment.getName());
            String lclass = segment.getSeverityClass();
            String superClass = segment.getSeveritySuperClass();
            ClassTable table = new ClassTable(idName, lclass, superClass, id);

            form.getTableTV().getItems().add(table);
            segmentLists.getSeverityTypeObservable().add(table);
        }
    }

    private void fillPriorityForm() {
        PriorityForm form = (PriorityForm) forms.get(Constans.priorityFormIndex);
        for (int i = 0; i < project.getPriority().size(); i++){
            Priority segment = project.getPriority().get(i);
            int id = formController.createTableItem(SegmentType.Priority);
            String idName = createTableItemIdName(id, segment.getName());
            String lclass = segment.getPriorityClass();
            String superClass = segment.getPrioritySuperClass();
            ClassTable table = new ClassTable(idName, lclass, superClass, id);

            form.getTableTV().getItems().add(table);
            segmentLists.getPriorityTypeObservable().add(table);
        }
    }

    private void fillCPR() {
        ConfigPersonRelationForm cprForm = (ConfigPersonRelationForm) forms.get(Constans.cprFormIndex);
        for (int i = 0; i < project.getCpr().size(); i++){
            ConfigPersonRelation cpr = project.getCpr().get(i);
            int id = formController.createTableItem(SegmentType.ConfigPersonRelation);
            String idName = createTableItemIdName(id, cpr.getName());
            BasicTable role = segmentLists.getRoleObservable().get(prepareIndexForForm(cpr.getPersonIndex()));
            CPRTable cprTable = new CPRTable(idName, role.getName(), id);

            cprForm.getTableTV().getItems().add(cprTable);
            segmentLists.getCPRObservable().add(cprTable);
        }
    }

    private void fillRoleForm() {
        RoleForm roleForm = (RoleForm) forms.get(Constans.roleFormIndex);
        for (int i = 0; i < project.getRoleType().size(); i++){
            Role role = project.getRoles().get(i);
            int id = formController.createTableItem(SegmentType.Role);
            String idName = createTableItemIdName(id, role.getName());
            BasicTable type = segmentLists.getRoleTypeObservable().get(prepareIndexForForm(role.getType()));
            RoleTable roleTable = new RoleTable(idName, prepareStringForForm(role.getDescription()), type.getName(), id);

            roleForm.getTableTV().getItems().add(roleTable);
            segmentLists.getRoleObservable().add(roleTable);
        }
    }

    private void fillRoleTypeForm() {
        RoleTypeForm roleTypeForm = (RoleTypeForm) forms.get(Constans.roleTypeIndex);
        for (int i = 0; i < project.getRoleType().size(); i++){
            RoleType roleType = project.getRoleType().get(i);
            int id = formController.createTableItem(SegmentType.RoleType);
            String idName = createTableItemIdName(id, roleType.getName());
            ClassTable type = new ClassTable(idName, roleType.getRoleClass(), roleType.getRoleSuperClass(), id);

            roleTypeForm.getTableTV().getItems().add(type);
            segmentLists.getRoleTypeObservable().add(type);
        }
    }

    private void fillMilestoneForm() {
        MilestoneForm milestoneForm = (MilestoneForm) forms.get(Constans.milestoneFormIndex);
        for (int i = 0; i < project.getMilestones().size(); i++){
            Milestone milestone = project.getMilestones().get(i);
            int id = formController.createTableItem(SegmentType.Milestone);
            String idName = createTableItemIdName(id, milestone.getName());
            String criterion = prepareIndexForTable(milestone.getCriteriaIndexs(), segmentLists.getCriterionObservable()).toString();
            MilestoneTable milestoneTable = new MilestoneTable(idName, criterion, id);
            milestoneForm.getTableTV().getItems().add(milestoneTable);
            segmentLists.getMilestoneObservable().add(milestoneTable);
        }
    }

    public String createTableItemIdName(int id, String name){
        return id + "_" + prepareStringForForm(name);
    }

    private ArrayList<String> prepareIndexForTable(List<Integer> indexs, ObservableList observableList){
        ArrayList values = new ArrayList();
        for (int i : indexs){
            values.add(observableList.get(i));
        }
        return values;
    }
    private void fillCriterionForm() {
        CriterionForm criterionForm = (CriterionForm) forms.get(Constans.criterionFormIndex);
        for (int i = 0; i < project.getCriterions().size(); i++){
            Criterion criterion = project.getCriterions().get(i);
            int id = formController.createTableItem(SegmentType.Criterion);
            String idName = createTableItemIdName(id, criterion.getName());
            CriterionTable criterionTable = new CriterionTable(idName, prepareStringForForm(criterion.getDescription()), id);
            criterionForm.getTableTV().getItems().add(criterionTable);
            segmentLists.getCriterionObservable().add(criterionTable);
        }

    }


    /**
     * Umožní převedení data ve formátu XMLGregorianCalendar uloženého v XML do
     * formátu LocalDate
     *
     * @param xmlDate XMLGregorianCalendar
     * @return LocalDate
     */
    public LocalDate convertDateFromXML(XMLGregorianCalendar xmlDate) {

        if (xmlDate == null) {
            return null;
        }

        Date date = xmlDate.toGregorianCalendar().getTime();
        Instant instant = date.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zdt.toLocalDate();
        return localDate;
    }

    private int prepareIndexForForm(Integer index){
        if((Integer)index == null){
            return 0;
        }
        return index + 1;
    }

    private void fillProjectForm() {
        ProjectForm projectForm = (ProjectForm) forms.get(Constans.projectFormIndex);
        String name = prepareStringForForm(project.getName());
        String description = prepareStringForForm(project.getDescription());
        projectForm.setDataToForm(name, description, convertDateFromXML(project.getStartDate()), convertDateFromXML(project.getEndDate()));
    }

    public void fillPhaseForm(int oldFormId){
        int id = formController.createNewForm(SegmentType.Phase, CanvasType.Phase);
        int phaseId = identificatorCreater.getPhaseIndex(oldFormId);
        int newPhaseId = identificatorCreater.getPhaseIndex(id);
        dataManipulator.copyDataFromPhase(phaseId, newPhaseId);
        fillPhaseForm(newPhaseId, id);

    }
    public void fillPhaseForm(int segmentId, int formId){
        Phase phase = project.getPhases().get(segmentId);
        PhaseForm phaseForm = (PhaseForm) forms.get(formId);
        int milestoneIndex = prepareIndexForForm(phase.getMilestoneIndex());
        int configurationIndex = prepareIndexForForm(phase.getConfiguration());
        String name = prepareStringForForm(phase.getName());
        String description = prepareStringForForm(phase.getDescription());
        phaseForm.setDataToForm(name, convertDateFromXML(phase.getEndDate()), description, milestoneIndex, configurationIndex);

        projectCanvasController.addCanvasItemFromExistData(SegmentType.Phase, formId, phase.getName(), phase.getCoordinates().getXCoordinate(),
                phase.getCoordinates().getYCoordinate());
        CanvasController canvasController = phaseForm.getCanvasController();

        fillWorkUnitForm(phase.getWorkUnits(), canvasController, CanvasType.Phase);
    }


    public void fillPhaseForm(){

        for (int i = 0; i < project.getPhases().size(); i++){

         int id = formController.createNewPhaseFormWithoutManipulator();
         fillPhaseForm(i, id);

        }
    }

    public void fillWorkUnitForm(int oldFormId, CanvasController canvasController){
        int id = formController.createNewForm(SegmentType.WorkUnit, canvasController.getCanvasType());
        int wuId = identificatorCreater.getWorkUnitIndex(oldFormId);
        int newWUId = identificatorCreater.getWorkUnitIndex(id);
        dataManipulator.copyDataFromWorkUnit(wuId, newWUId);
        fillWorkUnitForm(newWUId, id, canvasController);
    }


    public void fillWorkUnitForm(int segmentId, int formId, CanvasController canvasController){
        WorkUnit workUnit = project.getWorkUnits().get(segmentId);
        WorkUnitForm workUnitForm = (WorkUnitForm) forms.get(formId);

        int assigneIndex = prepareIndexForForm(workUnit.getAssigneeIndex());
        int authorIndex = prepareIndexForForm(workUnit.getAuthorIndex());
        int priorityIndex = prepareIndexForForm(workUnit.getPriorityIndex());
        int resolutionIndex = prepareIndexForForm(workUnit.getResolutionIndex());
        int severityIndex = prepareIndexForForm(workUnit.getSeverityIndex());
        int statusIndex = prepareIndexForForm(workUnit.getStatusIndex());
        int typeIndex = prepareIndexForForm(workUnit.getTypeIndex());
        String name = prepareStringForForm(workUnit.getName());
        String category = prepareStringForForm(workUnit.getCategory());
        String description = prepareStringForForm(workUnit.getDescription());
        String estimate = prepareEstimateForForm(workUnit.getEstimatedTime());

        boolean isExist = false;
        if(workUnit.isExist()){
            isExist = true;
        }

        workUnitForm.setDataToForm(name, assigneIndex,authorIndex, category, description,estimate ,
                priorityIndex, resolutionIndex, severityIndex, statusIndex, typeIndex, isExist);

        canvasController.addCanvasItemFromExistData(SegmentType.WorkUnit, formId, workUnit.getName(), workUnit.getCoordinates().getXCoordinate(),
                workUnit.getCoordinates().getYCoordinate(), isExist);

    }

    public void fillWorkUnitForm(List<Integer> workUnitsIndexs, CanvasController canvasController, CanvasType canvasType){


        for (int i = 0; i < workUnitsIndexs.size(); i++){

            int id = formController.createNewWorkUnitFormWithoutManipulator(canvasType);
            fillWorkUnitForm(workUnitsIndexs.get(i), id, canvasController);

        }

    }

    private String prepareEstimateForForm(double estimate) {

        if (estimate == -1.0){
            return "";
        }


        return String.valueOf(estimate);
    }


    public void fillLink(){
        for(int i = 0; i < project.getLinks().size(); i++){
            Link link = project.getLinks().get(i);

            if (link.getLeftUnitIndex() == null){
                int startIndexItem = identificatorCreater.getChangeSegmentIndexToFormMaper().get(link.getChangeIndex());
                int endIndexItem = identificatorCreater.getArtifactSegmentIndexToFormMaper().get(link.getArtifactIndex());

                CanvasItem startItem = canvasItemList.get(startIndexItem);
                CanvasItem endItem = canvasItemList.get(endIndexItem);
                CanvasController canvasController = startItem.getCanvasController();

                linkControl.ArrowManipulation(true, false, canvasController, startIndexItem, SegmentType.Change, startItem.getTranslateX()
                        ,startItem.getTranslateY(), startItem.getWidth(), startItem.getHeight() );
                linkControl.ArrowManipulation(true, true, canvasController, endIndexItem, SegmentType.Artifact, endItem.getTranslateX()
                        ,endItem.getTranslateY(), endItem.getWidth(), endItem.getHeight() );

            }else{
                int startIndexItem = identificatorCreater.getWorkUnitSegmentIndexToFormMaper().get(link.getLeftUnitIndex());
                int endIndexItem = identificatorCreater.getWorkUnitSegmentIndexToFormMaper().get(link.getRightUnitIndex());

                CanvasItem startItem = canvasItemList.get(startIndexItem);
                CanvasItem endItem = canvasItemList.get(endIndexItem);
                CanvasController canvasController = startItem.getCanvasController();

                linkControl.ArrowManipulation(true, false, canvasController, startIndexItem, SegmentType.WorkUnit, startItem.getTranslateX()
                            ,startItem.getTranslateY(), startItem.getWidth(), startItem.getHeight() );
                linkControl.ArrowManipulation(true, true, canvasController, endIndexItem, SegmentType.WorkUnit, endItem.getTranslateX()
                        ,endItem.getTranslateY(), endItem.getWidth(), endItem.getHeight(), prepareIndexForForm(link.getRelationIndex()));
            }

        }

    }

    public void fillActivityForm(int oldFormId){

        int id = formController.createNewForm(SegmentType.Activity, CanvasType.Activity);
        int activityId = identificatorCreater.getActivityIndex(oldFormId);
        int newActivityId = identificatorCreater.getActivityIndex(id);
        dataManipulator.copyDataFromActivity(activityId, newActivityId);
        fillActivityForm(newActivityId, id);

    }

    public void fillActivityForm(int segmentId, int formId){
        Activity activity = project.getActivities().get(segmentId);
        ActivityForm activityForm = (ActivityForm) forms.get(formId);
        String name = prepareStringForForm(activity.getName());
        String description = prepareStringForForm(activity.getDescription());
        activityForm.setDataToForm(name, description);

        projectCanvasController.addCanvasItemFromExistData(SegmentType.Activity, formId, activity.getName(), activity.getCoordinates().getXCoordinate(),
                activity.getCoordinates().getYCoordinate());
        CanvasController canvasController = activityForm.getCanvasController();

        fillWorkUnitForm(activity.getWorkUnits(), canvasController, CanvasType.Activity);
    }


    public void fillActivityForm(){

        for (int i = 0; i < project.getActivities().size(); i++){

            int id = formController.createNewActivityFormWithoutManipulator();
            fillActivityForm(i, id);

        }
    }

    public void fillIterationForm(int oldFormId){
        int id = formController.createNewForm(SegmentType.Iteration, CanvasType.Iteration);
        int iteratationId = identificatorCreater.getIterationIndex(oldFormId);
        int newIterationId = identificatorCreater.getIterationIndex(id);
        dataManipulator.copyDataFromIteration(iteratationId, newIterationId);
        fillIterationForm(newIterationId, id);
    }

    public void fillIterationForm(int segmentId, int formId){
        Iteration iteration = project.getIterations().get(segmentId);
        IterationForm iterationForm = (IterationForm) forms.get(formId);

        String name = prepareStringForForm(iteration.getName());
        String description = prepareStringForForm(iteration.getDescription());
        int confiIndex =  prepareIndexForForm(iteration.getConfiguration());

        iterationForm.setDataToForm(name, description, convertDateFromXML(iteration.getStartDate()), convertDateFromXML(iteration.getEndDate()),
                confiIndex);

        projectCanvasController.addCanvasItemFromExistData(SegmentType.Iteration, formId, iteration.getName(), iteration.getCoordinates().getXCoordinate(),
                iteration.getCoordinates().getYCoordinate());
        CanvasController canvasController = iterationForm.getCanvasController();

        fillWorkUnitForm(iteration.getWorkUnits(), canvasController, CanvasType.Iteration);

    }

    public void fillIterationForm(){

        for (int i = 0; i < project.getIterations().size(); i++){

            int id = formController.createNewIterationFormWithoutManipulator();
            fillIterationForm(i, id);

        }
    }

    /**
     * Getrs
     */

    public void setProjectCanvasController(CanvasController projectCanvasController) {
        this.projectCanvasController = projectCanvasController;
    }
}
