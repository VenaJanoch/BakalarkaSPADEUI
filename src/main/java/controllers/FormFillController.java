package controllers;

import SPADEPAC.*;
import abstractform.BasicForm;
import forms.*;
import graphics.CanvasItem;
import model.DataManipulator;
import model.DataModel;
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
    private DataModel dataModel;
    private FormController formController;
    private CanvasItemController canvasItemController;
    private Project project;
    private ArrayList<BasicForm> forms;
    private CanvasController projectCanvasController;
    private IdentificatorCreater identificatorCreater;
    private SegmentLists segmentLists;
    private LinkControl linkControl;
    private Map<Integer,CanvasItem> canvasItemList;
    private DataPreparer dataPreparer;

    public FormFillController(FormController formController, DataModel dataModel, CanvasItemController canvasItemController,
                              IdentificatorCreater identificatorCreater, DataPreparer dataPreparer, SegmentLists lists, LinkControl linkControl,
                              Map<Integer,CanvasItem> canvasItemList){
        this.formController = formController;
        this.forms = formController.getForms();
        this.project = dataModel.getProject();
        this.dataModel = dataModel;
        this.dataManipulator = dataModel.getDataManipulator();
        this.canvasItemController = canvasItemController;
        this.identificatorCreater = identificatorCreater;
        this.segmentLists = lists;
        this.linkControl = linkControl;
        this.canvasItemList = canvasItemList;
        this.dataPreparer = dataPreparer;

    }


    public void createFormsFromData(){
        this.project = dataModel.getProject();
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
        addExistPhaseFormToCanvas();
        addExistActivityFormToCanvas();
        addExistIterationFormToCanvas();
      //  addExistWorkUnitFormToCanvas(project.getWorkUnitIndexs(), projectCanvasController, CanvasType.Project);
        fillLink();

    }


    private void addDataToConfigurationTable(int id, int formId, Configuration configuration, ConfigurationTableForm configTable){

   //     String idName = dataPreparer.createTableItemIdName(id,dataPreparer.prepareStringForForm(configuration.getName()));
        String isRelease = "YES";
        if(configuration.isIsRelease() == null || !configuration.isIsRelease()){
            isRelease = "NO";
        }

   //     ConfigTable table = new ConfigTable(idName, isRelease, formId, id);

//        configTable.getTableTV().getItems().add(table);
//        segmentLists.getConfigObservable().add(table);
    }

    private void fillConfigurationForm() {
        ConfigurationTableForm form = (ConfigurationTableForm)forms.get(Constans.configurationFormIndex);
        Configuration configuration = project.getConfiguration().get(0);
        if (configuration != null){
            fillConfigurationFormWithoutCreateId(project.getConfiguration().get(0), Constans.configurationFormIndex + 1);
            addDataToConfigurationTable(configuration.getId(), Constans.configurationFormIndex + 1, configuration, form);
        }

        int formId;
        for (int i = 1; i < project.getConfiguration().size() - 1; i++){
            configuration = project.getConfiguration().get(i);
            formId = formController.createNewConfiguratioFormWithoutManipulator();
            fillConfigurationFormWithoutCreateId(configuration, formId);
            addDataToConfigurationTable(configuration.getId(), formId, configuration, form);
        }
        formId = formController.createNewForm(SegmentType.Configuration, CanvasType.Configuration);
        form.getMainPanel().setLeft(formController.getMainPanelFromForm(formId));

    }
    public void fillConfigurationFormWithoutCreateId(Configuration configuration, int formId){

        ConfigurationForm form = (ConfigurationForm) forms.get(formId);
        identificatorCreater.setDataToConfigurationMappers(formId, configuration.getId());

//        String name = dataPreparer.prepareStringForForm(configuration.getName());
//        int authorIndex = dataPreparer.prepareIndexForForm(configuration.getAuthorIndex());
//        LocalDate createdDate = convertDateFromXML(configuration.getCreate());
//        ArrayList<Integer> cprIndexs = dataPreparer.prepareIndexForMultiComboBox(configuration.getCPRsIndexs());
//        ArrayList<Integer> branchIndexs = dataPreparer.prepareIndexForMultiComboBox(configuration.getBranchesIndexs());
//
//        form.setDataToForm(name, createdDate, authorIndex, cprIndexs, branchIndexs);
        TableView<TagTable> tagView =  form.getTagForm().getTableTV();
        for(int j = 0; j < configuration.getTags().size(); j++){
            formId = identificatorCreater.createTagID();
            TagTable tagTable = new TagTable(configuration.getTags().get(j), formId);
            tagView.getItems().add(tagTable);
        }
        CanvasController canvasController = form.getCanvasController();

        fillArtifactForm(configuration.getArtifactsIndexs(), canvasController);
   //     fillChangeForm(configuration.getChangesIndexs(), canvasController);
    }


    public void fillChangeForm(int oldFormId, CanvasController canvasController){
        int newFormId = formController.createNewForm(SegmentType.Change, canvasController.getCanvasType());
        int changeId = identificatorCreater.getChangeId(oldFormId);
        int newChangeId = identificatorCreater.getChangeId(newFormId);
        dataManipulator.copyDataFromChange(changeId, newChangeId);
        fillChangeForm(newChangeId, newFormId, canvasController);
    }

    private void fillChangeForm(int segmentId, int formId, CanvasController canvasController) {

        Change change = dataModel.getChange(segmentId);
        ChangeForm form = (ChangeForm) forms.get(formId);

        boolean isExist = false;
        if(change.isExist()){
            isExist = true;
        }

//        String name = dataPreparer.prepareStringForForm(change.getName());
//        String description = dataPreparer.prepareStringForForm(change.getDescriptoin());
//
//       // form.setDataToForm(name, description, isExist);
//
//        canvasController.addCanvasItemFromExistData(SegmentType.Change, formId, change.getName(), change.getCoordinates().getXCoordinate(),
//                change.getCoordinates().getYCoordinate(), 1, isExist); //todo upravit pocet instanci
   }

    private void fillChangeForm(List<Integer> changeIndexs, CanvasController canvasController) {
        for (int i = 0; i < changeIndexs.size(); i++){

            int fromId = formController.createNewChangeFormWithoutManipulator();
            int indexInProject = changeIndexs.get(i);
            Change change = project.getChanges().get(indexInProject);
            identificatorCreater.setDataToChangeMappers(fromId, change.getId());
            fillChangeForm(change.getId(), fromId, canvasController);
            }
    }

    public void fillArtifactForm(int oldFormId, CanvasController canvasController){
        int newFormId = formController.createNewForm(SegmentType.Artifact, canvasController.getCanvasType());
        int artifactId = identificatorCreater.getArtifactIndex(oldFormId);
        int newArtifactId = identificatorCreater.getArtifactIndex(newFormId);
        dataManipulator.copyDataFromArtifact(artifactId, newArtifactId);
        fillArtifactForm(newArtifactId, newFormId, canvasController);
    }

    private void fillArtifactForm(int segmentId, int formId, CanvasController canvasController){

        Artifact artifact = dataModel.getArtifact(segmentId);
        ArtifactForm form = (ArtifactForm) forms.get(formId);

//        int authorIndex = dataPreparer.prepareIndexForForm(artifact.getAuthorIndex());
//        LocalDate createDate = convertDateFromXML(artifact.getCreated());
//        String name = dataPreparer.prepareStringForForm(artifact.getName());
//        String description = dataPreparer.prepareStringForForm(artifact.getDescriptoin());
        boolean isExist = false;
        if(artifact.isExist()){
            isExist = true;
        }

       // form.setDataToForm(name, description, authorIndex, createDate, artifact.getMimeType(), isExist);

//        canvasController.addCanvasItemFromExistData(SegmentType.Artifact, formId, artifact.getName(), artifact.getCoordinates().getXCoordinate(),
//                artifact.getCoordinates().getYCoordinate(), 1, isExist); //todo upravit pocet instanci

    }

    private void fillArtifactForm(List<Integer> artifactsIndexs, CanvasController canvasController) {
        for (int i = 0; i < artifactsIndexs.size(); i++){

            int formId = formController.createNewArtifactFormWithoutManipulator();
            int indexInProject = artifactsIndexs.get(i);
            Artifact artifact = project.getArtifacts().get(indexInProject);
            identificatorCreater.setDataToArtifactMappers(formId, artifact.getId());
            fillArtifactForm(artifact.getId(), formId, canvasController);
            }
    }

    private void fillBranchForm() {
        BranchForm form = (BranchForm) forms.get(Constans.branchIndex);
        for (int i = 0; i < project.getBranches().size(); i++){
            Branch segment = project.getBranches().get(i);
            int id = formController.createTableItem(SegmentType.Branch);
    //        String idName = dataPreparer.createTableItemIdName(id, segment.getName());
            String isMain = "NO";
            if(segment.isIsMain()){
                isMain = "YES";
            }

      //      BranchTable table = new BranchTable(idName, isMain, segment.isIsMain(), id);

//            form.getTableTV().getItems().add(table);
//            segmentLists.getBranchObservable().add(table);
        }
    }

    private ClassTable createClassTable(int id, String name, String lclass, String superClass){

        String idName = dataPreparer.createTableItemIdName(id, name);
        return new ClassTable(idName, lclass, superClass, id);
    }

    private void fillTypeForm() {
        TypeForm form = (TypeForm) forms.get(Constans.wuTypeFormIndex);
        for (int i = 0; i < project.getTypes().size(); i++){
            Type segment = project.getTypes().get(i);
            formController.createTableItem(SegmentType.Type);

       //     ClassTable table = createClassTable(segment.getId(), segment.getName(), segment.getTypeClass(), segment.getTypeSuperClass());

//            form.getTableTV().getItems().add(table);
//            segmentLists.getTypeObservable().add(table);
        }
    }

    private void fillStatusForm() {
        StatusForm form = (StatusForm) forms.get(Constans.statusFormIndex);
        for (int i = 0; i < project.getStatus().size(); i++){
            Status segment = project.getStatus().get(i);
            formController.createTableItem(SegmentType.Status);
//       //     ClassTable table = createClassTable(segment.getId(), segment.getName(), segment.getStatusClass(), segment.getStatusSuperClass());
//            form.getTableTV().getItems().add(table);
//            segmentLists.getStatusTypeObservable().add(table);
        }
    }

    private void fillResolutionForm() {
        ResolutionForm form = (ResolutionForm) forms.get(Constans.resolutionormIndex);
        for (int i = 0; i < project.getResolution().size(); i++){
            Resolution segment = project.getResolution().get(i);
            formController.createTableItem(SegmentType.Resolution);

//            ClassTable table = createClassTable(segment.getId(), segment.getName(), segment.getResolutionClass(), segment.getResolutionSuperClass());
//            form.getTableTV().getItems().add(table);
//            segmentLists.getResolutionTypeObservable().add(table);
        }
    }

    private void fillRelationForm() {
        RelationForm form = (RelationForm) forms.get(Constans.relationFormIndex);
        for (int i = 0; i < project.getRelation().size(); i++){
            Relation segment = project.getRelation().get(i);
            formController.createTableItem(SegmentType.Relation);
//            ClassTable table = createClassTable(segment.getId(), segment.getName(), segment.getRelationClass(), segment.getRelationSuperClass());
//            form.getTableTV().getItems().add(table);
//            segmentLists.getRelationTypeObservable().add(table);
        }
    }

    private void fillSeverityForm() {
        SeverityForm form = (SeverityForm) forms.get(Constans.severityFormIndex);
        for (int i = 0; i < project.getSeverity().size(); i++){
            Severity segment = project.getSeverity().get(i);
//            int id = formController.createTableItem(SegmentType.Severity);
//            ClassTable table = createClassTable(segment.getId(), segment.getName(), segment.getSeverityClass(), segment.getSeveritySuperClass());

//            form.getTableTV().getItems().add(table);
//            segmentLists.getSeverityTypeObservable().add(table);
        }
    }

    private void fillPriorityForm() {
        PriorityForm form = (PriorityForm) forms.get(Constans.priorityFormIndex);
        for (int i = 0; i < project.getPriority().size(); i++){
            Priority segment = project.getPriority().get(i);
            int id = formController.createTableItem(SegmentType.Priority);

         //   ClassTable table = createClassTable(segment.getId(), segment.getName(), segment.getPriorityClass(), segment.getPrioritySuperClass());
//
//            form.getTableTV().getItems().add(table);
//            segmentLists.getPriorityTypeObservable().add(table);
        }
    }

    private void fillCPR() {
        ConfigPersonRelationForm cprForm = (ConfigPersonRelationForm) forms.get(Constans.cprFormIndex);
        for (int i = 0; i < project.getCpr().size(); i++){
            ConfigPersonRelation cpr = project.getCpr().get(i);
            int id = cpr.getId();
            formController.createTableItem(SegmentType.ConfigPersonRelation);
//            String idName = dataPreparer.createTableItemIdName(id, cpr.getName());
//            BasicTable role = segmentLists.getRoleObservable().get(dataPreparer.prepareIndexForForm(cpr.getPersonIndex()));
//            CPRTable cprTable = new CPRTable(idName, role.getName(), id);
//
//            cprForm.getTableTV().getItems().add(cprTable);
//            segmentLists.getCPRObservable().add(cprTable);
        }
    }

    private void fillRoleForm() {
        RoleForm roleForm = (RoleForm) forms.get(Constans.roleFormIndex);
        for (int i = 0; i < project.getRoleType().size(); i++){
            Role role = project.getRoles().get(i);
            int id = role.getId();
            formController.createTableItem(SegmentType.Role);
//            String idName = dataPreparer.createTableItemIdName(id, role.getName());
//            BasicTable type = segmentLists.getRoleTypeObservable().get(dataPreparer.prepareIndexForForm(role.getType()));
//            RoleTable roleTable = new RoleTable(idName, dataPreparer.prepareStringForForm(role.getDescription()), type.getName(), id);
//
//            roleForm.getTableTV().getItems().add(roleTable);
//            segmentLists.getRoleObservable().add(roleTable);
        }
    }

    private void fillRoleTypeForm() {
        RoleTypeForm roleTypeForm = (RoleTypeForm) forms.get(Constans.roleTypeIndex);
        for (int i = 0; i < project.getRoleType().size(); i++){
//            RoleType segment = project.getRoleType().get(i);
//            formController.createTableItem(SegmentType.RoleType);
//            ClassTable table = createClassTable(segment.getId(), segment.getName(), segment.getRoleClass(), segment.getRoleSuperClass());
//            roleTypeForm.getTableTV().getItems().add(table);
//            segmentLists.getRoleTypeObservable().add(table);
        }
    }

    private void fillMilestoneForm() {
        MilestoneForm milestoneForm = (MilestoneForm) forms.get(Constans.milestoneFormIndex);
        for (int i = 0; i < project.getMilestones().size(); i++){
            Milestone milestone = project.getMilestones().get(i);
//            int id = milestone.getId();
//            formController.createTableItem(SegmentType.Milestone);
//            String idName = dataPreparer.createTableItemIdName(id, milestone.getName());
//            String criterion = dataPreparer.prepareIndexForTable(milestone.getCriteriaIndexs(), segmentLists.getCriterionObservable()).toString();
//            MilestoneTable milestoneTable = new MilestoneTable(idName, milestone.getDescription(), criterion, id);
//            milestoneForm.getTableTV().getItems().add(milestoneTable);
//            segmentLists.getMilestoneObservable().add(milestoneTable);
        }
    }


    private void fillCriterionForm() {
        CriterionForm criterionForm = (CriterionForm) forms.get(Constans.criterionFormIndex);
        for (int i = 0; i < project.getCriterions().size(); i++){
//            Criterion criterion = project.getCriterions().get(i);
//            int id = criterion.getId();
//            formController.createTableItem(SegmentType.Criterion);
//            String idName = dataPreparer.createTableItemIdName(id, criterion.getName());
//            CriterionTable criterionTable = new CriterionTable(idName, dataPreparer.prepareStringForForm(criterion.getDescription()), id);
//            criterionForm.getTableTV().getItems().add(criterionTable);
//            segmentLists.getCriterionObservable().add(criterionTable);
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
    

    private void fillProjectForm() {
        ProjectForm projectForm = (ProjectForm) forms.get(Constans.projectFormIndex);
//        String name = dataPreparer.prepareStringForForm(project.getName());
//        String description = dataPreparer.prepareStringForForm(project.getDescription());
//        projectForm.setDataToForm(name, description, convertDateFromXML(project.getStartDate()), convertDateFromXML(project.getEndDate()));
    }

    public void addExistPhaseFormToCanvas(int oldFormId){
        int id = formController.createNewForm(SegmentType.Phase, CanvasType.Phase);
        int phaseId = identificatorCreater.getRoleId(oldFormId);
        int newPhaseId = identificatorCreater.getRoleId(id);
        dataManipulator.copyDataFromPhase(phaseId, newPhaseId);
        addExistPhaseFormToCanvas(newPhaseId, id);

    }

    public void addExistPhaseFormToCanvas(int segmentId, int formId){
//        Phase phase = fillPhaseForm(segmentId, formId);
//  //      identificatorCreater.setDataToPhaseMapper(formId, phase.getId());
//        projectCanvasController.addCanvasItemFromExistData(SegmentType.Phase, formId, phase.getName(), phase.getCoordinates().getXCoordinate(),
//                phase.getCoordinates().getYCoordinate(), 1);//todo upravit pocet instanci
//
//    }
//
//    public Phase fillPhaseForm(int segmentId, int formId){
//        Phase phase = dataModel.getPhase(segmentId);
//
//        PhaseForm phaseForm = (PhaseForm) forms.get(formId);
//        int milestoneIndex = dataPreparer.prepareIndexForForm(phase.getMilestoneIndex());
//        int configurationIndex = dataPreparer.prepareIndexForForm(phase.getConfiguration());
//        String name = dataPreparer.prepareStringForForm(phase.getName());
//        String description = dataPreparer.prepareStringForForm(phase.getDescription());
//       // TODO prededelat na tabulkovy formular phaseForm.setDataToForm(name, convertDateFromXML(phase.getEndDate()), description, milestoneIndex, configurationIndex);
//
//        CanvasController canvasController = phaseForm.getCanvasController();
//        canvasController.clearCanvas();
//        addExistWorkUnitFormToCanvas(phase.getWorkUnits(), canvasController, CanvasType.Phase);
//
//        return phase;
    }


    public void addExistPhaseFormToCanvas(){

        for (int i = 0; i < project.getPhases().size(); i++){

         int id = formController.createNewPhaseFormWithoutManipulator();
         Phase phase = project.getPhases().get(i);
         addExistPhaseFormToCanvas(phase.getId(), id);

        }
    }

    public void addExistWorkUnitFormToCanvas(int oldFormId, CanvasController canvasController){
        int id = formController.createNewForm(SegmentType.WorkUnit, canvasController.getCanvasType());
        int wuId = identificatorCreater.getWorkUnitIndex(oldFormId);
        int newWUId = identificatorCreater.getWorkUnitIndex(id);
        dataManipulator.copyDataFromWorkUnit(wuId, newWUId);
        addExistWorkUnitFormToCanvas(newWUId, id, canvasController);
    }


    public WorkUnit fillWorkUnitForm(int segmentId, int formId){
        WorkUnit workUnit = dataModel.getWorkUnit(segmentId);
        WorkUnitForm workUnitForm = (WorkUnitForm) forms.get(formId);

//        int assigneIndex = dataPreparer.prepareIndexForForm(workUnit.getAssigneeIndex());
//        int authorIndex = dataPreparer.prepareIndexForForm(workUnit.getAuthorIndex());
//        int priorityIndex = dataPreparer.prepareIndexForForm(workUnit.getPriorityIndex());
//        int resolutionIndex = dataPreparer.prepareIndexForForm(workUnit.getResolutionIndex());
//        int severityIndex = dataPreparer.prepareIndexForForm(workUnit.getSeverityIndex());
//        int statusIndex = dataPreparer.prepareIndexForForm(workUnit.getStatusIndex());
//        int typeIndex = dataPreparer.prepareIndexForForm(workUnit.getTypeIndex());
//        String name = dataPreparer.prepareStringForForm(workUnit.getName());
//        String category = dataPreparer.prepareStringForForm(workUnit.getCategory());
//        String description = dataPreparer.prepareStringForForm(workUnit.getDescription());
//        String estimate;
//        if(workUnit.getEstimatedTime() == null){
//            estimate = "";
//        }else{
//            estimate = dataPreparer.prepareEstimateForForm(workUnit.getEstimatedTime());
//
//        }

        boolean isExist = false;
        if(workUnit.isExist() != null && workUnit.isExist()){
            isExist = true;
        }

      //  workUnitForm.setDataToForm(name, assigneIndex,authorIndex, category, description,estimate ,
       //         priorityIndex, resolutionIndex, severityIndex, statusIndex, typeIndex, isExist);
        return workUnit;

    }

    public void addExistWorkUnitFormToCanvas(List<Integer> workUnitsIndexs, CanvasController canvasController, CanvasType canvasType){


        for (int i = 0; i < workUnitsIndexs.size(); i++){
            int id = formController.createNewWorkUnitFormWithoutManipulator(canvasType);
            WorkUnit workUnit = project.getWorkUnits().get(workUnitsIndexs.get(i));
            addExistWorkUnitFormToCanvas(workUnit.getId() , id, canvasController);

        }

    }

    private void addExistWorkUnitFormToCanvas(int segmentId, int formId, CanvasController canvasController){
        WorkUnit workUnit = fillWorkUnitForm(segmentId, formId);
        identificatorCreater.setDataToWorkUnitsMappers(formId, workUnit.getId());
//        canvasController.addCanvasItemFromExistData(SegmentType.WorkUnit, formId, workUnit.getName(), workUnit.getCoordinates().getXCoordinate(),
//                workUnit.getCoordinates().getYCoordinate(), 1); //todo upravit pocet instanci
    }

    public void fillLink(){
        for(int i = 0; i < project.getLinks().size(); i++){
            Link link = project.getLinks().get(i);

            if (link.getLeftUnitIndex() == null){
                int startIndexItem = identificatorCreater.getRoleSegmentIndexToFormMaper().get(link.getChangeIndex());
                int endIndexItem = identificatorCreater.getArtifactSegmentIdToFormIndexMaper().get(link.getArtifactIndex());

                CanvasItem startItem = canvasItemList.get(startIndexItem);
                CanvasItem endItem = canvasItemList.get(endIndexItem);
                CanvasController canvasController = startItem.getCanvasController();

                linkControl.ArrowManipulation(true, false, canvasController, startIndexItem, SegmentType.Change, startItem.getTranslateX()
                        ,startItem.getTranslateY(), startItem.getWidth(), startItem.getHeight() );
                linkControl.ArrowManipulation(true, true, canvasController, endIndexItem, SegmentType.Artifact, endItem.getTranslateX()
                        ,endItem.getTranslateY(), endItem.getWidth(), endItem.getHeight() );

            }else{
                int startIndexItem = identificatorCreater.getWorkUnitSegmentIdToFormIndexMaper().get(link.getLeftUnitIndex());
                int endIndexItem = identificatorCreater.getWorkUnitSegmentIdToFormIndexMaper().get(link.getRightUnitIndex());

                CanvasItem startItem = canvasItemList.get(startIndexItem);
                CanvasItem endItem = canvasItemList.get(endIndexItem);
                CanvasController canvasController = startItem.getCanvasController();

                linkControl.ArrowManipulation(true, false, canvasController, startIndexItem, SegmentType.WorkUnit, startItem.getTranslateX()
                            ,startItem.getTranslateY(), startItem.getWidth(), startItem.getHeight() );
                linkControl.ArrowManipulation(true, true, canvasController, endIndexItem, SegmentType.WorkUnit, endItem.getTranslateX()
                        ,endItem.getTranslateY(), endItem.getWidth(), endItem.getHeight(), dataPreparer.prepareIndexForForm(link.getRelationIndex()));
            }

        }

    }

    public void addExistActivityFormToCanvas(int oldFormId){

        int id = formController.createNewForm(SegmentType.Activity, CanvasType.Activity);
        int activityId = identificatorCreater.getActivityId(oldFormId);
        int newActivityId = identificatorCreater.getActivityId(id);
        dataManipulator.copyDataFromActivity(activityId, newActivityId);
        addExistActivityFormToCanvas(newActivityId, id);

    }

    public void addExistActivityFormToCanvas(int segmentId, int formId){

        Activity activity = fillActivityForm(segmentId, formId);
        identificatorCreater.setDataToActivityMapper(formId, activity.getId());
    //    projectCanvasController.addCanvasItemFromExistData(SegmentType.Activity, formId, activity.getName(), activity.getCoordinates().getXCoordinate(),
      //          activity.getCoordinates().getYCoordinate(), 1); //todo upravit pocet instanci
    }

    public Activity fillActivityForm(int segmentId, int formId){
        Activity activity = dataModel.getActivity(segmentId);

        ActivityForm activityForm = (ActivityForm) forms.get(formId);
       // String name = dataPreparer.prepareStringForForm(activity.getName());
       // String description = dataPreparer.prepareStringForForm(activity.getDescription());
       // activityForm.setDataToForm(name, description);

        CanvasController canvasController = activityForm.getCanvasController();
        canvasController.clearCanvas();
       // addExistWorkUnitFormToCanvas(activity.getWorkUnits(), canvasController, CanvasType.Activity);

        return activity;
    }


    public void addExistActivityFormToCanvas(){

        for (int i = 0; i < project.getActivities().size(); i++){

            int id = formController.createNewActivityFormWithoutManipulator();
            Activity activity = project.getActivities().get(i);
            addExistActivityFormToCanvas(activity.getId(), id);

        }
    }

    public void addExistIterationFormToCanvas(){

        for (int i = 0; i < project.getIterations().size(); i++){

            int id = formController.createNewIterationFormWithoutManipulator();
            Iteration iteration = project.getIterations().get(i);
            addExistIterationFormToCanvas(iteration.getId(), id);

        }
    }

    public void addExistIterationFormToCanvas(int segmentId, int formId){
        Iteration iteration = fillIterationForm(segmentId, formId);
        identificatorCreater.setDataToIterationMapper(formId, iteration.getId());
//        projectCanvasController.addCanvasItemFromExistData(SegmentType.Iteration, formId, iteration.getName(), iteration.getCoordinates().getXCoordinate(),
//                iteration.getCoordinates().getYCoordinate(), 1);
    }

    public void addExistIterationFormToCanvas(int oldFormId){
        int id = formController.createNewForm(SegmentType.Iteration, CanvasType.Iteration);
        int iteratationId = identificatorCreater.getIterationId(oldFormId);
        int newIterationId = identificatorCreater.getIterationId(id);
        dataManipulator.copyDataFromIteration(iteratationId, newIterationId);
        addExistIterationFormToCanvas(newIterationId, id);
    }

    public Iteration fillIterationForm(int segmentId, int formId){
        Iteration iteration = dataModel.getIteration(segmentId);
        IterationForm iterationForm = (IterationForm) forms.get(formId);

//        String name = dataPreparer.prepareStringForForm(iteration.getName());
//        String description = dataPreparer.prepareStringForForm(iteration.getDescription());
//        int confiIndex =  dataPreparer.prepareIndexForForm(iteration.getConfiguration());
//
//       // iterationForm.setDataToForm(name, description, convertDateFromXML(iteration.getStartDate()), convertDateFromXML(iteration.getEndDate()),
//          //      confiIndex);
//
//        CanvasController canvasController = iterationForm.getCanvasController();
//        canvasController.clearCanvas();
//        addExistWorkUnitFormToCanvas(iteration.getWorkUnits(), canvasController, CanvasType.Iteration);

        return iteration;
    }


    /**
     * Geters
     */

    public void setProjectCanvasController(CanvasController projectCanvasController) {
        this.projectCanvasController = projectCanvasController;
    }




}
