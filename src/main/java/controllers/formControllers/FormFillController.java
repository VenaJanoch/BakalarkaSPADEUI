package controllers.formControllers;

import SPADEPAC.*;
import abstractform.BasicForm;
import controllers.DataPreparer;
import controllers.LinkControl;
import controllers.graphicsComponentsControllers.CanvasController;
import controllers.graphicsComponentsControllers.CanvasItemController;
import forms.*;
import graphics.canvas.CanvasItem;
import javafx.geometry.Point2D;
import model.DataManipulator;
import model.DataModel;
import model.IdentificatorCreater;
import services.Constans;
import services.SegmentLists;
import services.SegmentType;
import tables.*;

import javafx.scene.control.TableView;

import java.util.ArrayList;
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

        fillCriterionForm();
        fillMilestoneForm();
        fillRoleTypeForm();
        fillPersonForm();
        fillCPR();
        fillPriorityForm();
        fillSeverityForm();
        fillRelationForm();
        fillResolutionForm();
        fillStatusForm();
        fillTypeForm();
        fillBranchForm();
        fillConfigurationForm();
        fillCommitForm();
        fillCommitedConfigurationForm();
        fillPhaseForm();
        fillActivityForm();
        fillChangeForm();
        fillArtifactForm();
        fillIterationForm();
        fillWorkUnitForm();
        fillVCSTagForm();
        fillLink();


    }


    public void fillConfigurationForm(int oldFormId){
        int newFormId = formController.createNewForm(SegmentType.Configuration, projectCanvasController.getCanvasType());
        int configurationId = identificatorCreater.getConfigurationFormIndex(oldFormId);
        int newConfigurationId = identificatorCreater.getConfigurationFormIndex(newFormId);
        dataManipulator.copyDataFromConfiguration(configurationId, newConfigurationId);
        fillConfigurationForm(newConfigurationId, newFormId, projectCanvasController);
    }

    private void fillConfigurationForm(int segmentId, int formId, CanvasController canvasController){

        Configuration configuration = dataModel.getConfiguration(segmentId);
        String name = configuration.getName().get(0);
        canvasController.addCanvasItemFromExistData(SegmentType.Configuration, formId, name, configuration.getCoordinates().getXCoordinate(),
                configuration.getCoordinates().getYCoordinate(), configuration.getCount(), configuration.isExist());

    }

    private void fillConfigurationForm() {
        for (Configuration configuration : project.getConfiguration()){

            String name = configuration.getName().get(0);
            int formId = identificatorCreater.setDataToConfigurationMappers(configuration.getId());
            formController.createNewConfigurationFormWithoutCreateId(name, configuration.isExist(), configuration.getId(), formId);


            fillConfigurationForm(configuration.getId(), formId, projectCanvasController);
        }
    }

    

    private void fillChangeForm() {
        ChangeForm form = (ChangeForm) forms.get(Constans.changeFormIndex);
        for (Change segment : project.getChanges()) {
            
            int id = formController.createTableItem(SegmentType.Change);
            String idName = segment.getName().get(0);
            ChangeTable table = new ChangeTable(idName, segment.isExist(), id);

            form.getTableTV().getItems().add(table);
            segmentLists.getChangeObservable().add(table);
        }
    }

    public void fillCommitForm(int oldFormId){
        int newFormId = formController.createNewForm(SegmentType.Commit, projectCanvasController.getCanvasType());
        int commitId = identificatorCreater.getCommitFormIndex(oldFormId);
        int newCommitId = identificatorCreater.getCommitFormIndex(newFormId);
        dataManipulator.copyDataFromCommit(commitId, newCommitId);
        fillCommitForm(newCommitId, newFormId, projectCanvasController);
    }

    private void fillCommitForm(int segmentId, int formId, CanvasController canvasController){

        Commit commit = dataModel.getCommit(segmentId);
       
        String name = commit.getName().get(0);
        canvasController.addCanvasItemFromExistData(SegmentType.Commit, formId, name, commit.getCoordinates().getXCoordinate(),
                commit.getCoordinates().getYCoordinate(), commit.getCount(), commit.isExist());

    }

    private void fillCommitForm() {
        for (Commit commit : project.getCommit()){

            String name = commit.getName().get(0);
            int formId = identificatorCreater.setDataToCommitMapper(commit.getId());
            formController.createNewCommitFormWithoutCreateId(name, commit.isExist(), commit.getId(), formId);


            fillCommitForm(commit.getId(), formId, projectCanvasController);
        }
    }

    public void fillCommitedConfigurationForm(int oldFormId){
        int newFormId = formController.createNewForm(SegmentType.Committed_Configuration, projectCanvasController.getCanvasType());
        int commitConfigurationId = identificatorCreater.getCommitedConfigurationFormIndex(oldFormId);
        int newCommitConfigurationId = identificatorCreater.getCommitedConfigurationFormIndex(newFormId);
        dataManipulator.copyDataFromCommitedConfiguration(commitConfigurationId, newCommitConfigurationId);
        fillCommitedConfigurationForm(newCommitConfigurationId, newFormId, projectCanvasController);
    }

    private void fillCommitedConfigurationForm(int segmentId, int formId, CanvasController canvasController){

        CommitedConfiguration commitedConfiguration = dataModel.getCommitedConfiguration(segmentId);

        String name = commitedConfiguration.getName().get(0);
        canvasController.addCanvasItemFromExistData(SegmentType.Committed_Configuration, formId, name, commitedConfiguration.getCoordinates().getXCoordinate(),
                commitedConfiguration.getCoordinates().getYCoordinate(), commitedConfiguration.getCount(), commitedConfiguration.isExist());

    }

    private void fillCommitedConfigurationForm() {
        for (CommitedConfiguration commitedConfiguration : project.getCommitConfiguration()){

            String name = commitedConfiguration.getName().get(0);

            int formId = identificatorCreater.setDataToCommitedConfigurationMapper(commitedConfiguration.getId());
            formController.createNewCommitedConfigurationFormWithoutCreateId(name, commitedConfiguration.isExist(), commitedConfiguration.getId(), formId);

            fillCommitedConfigurationForm(commitedConfiguration.getId(), formId, projectCanvasController);
        }
    }
    
    

    public void fillArtifactForm(int oldFormId){
        int newFormId = formController.createNewForm(SegmentType.Artifact, projectCanvasController.getCanvasType());
        int artifactId = identificatorCreater.getArtifactIndex(oldFormId);
        int newArtifactId = identificatorCreater.getArtifactIndex(newFormId);
        dataManipulator.copyDataFromArtifact(artifactId, newArtifactId);
        fillArtifactForm(newArtifactId, newFormId, projectCanvasController);
    }

    private void fillArtifactForm(int segmentId, int formId, CanvasController canvasController){

        Artifact artifact = dataModel.getArtifact(segmentId);
        
        String name = artifact.getName().get(0);
        canvasController.addCanvasItemFromExistData(SegmentType.Artifact, formId, name, artifact.getCoordinates().getXCoordinate(),
                artifact.getCoordinates().getYCoordinate(), artifact.getCount(), artifact.isExist());

    }

    private void fillArtifactForm() {
        for (Artifact artifact : project.getArtifacts()){
            
            String name = artifact.getName().get(0);
           // String description = artifact.getDescription().get(0);

            int formId =  identificatorCreater.setDataToArtifactMappers(artifact.getId());
            formController.createNewArtifactFormWithoutCreateId(name, artifact.isExist(), artifact.getId(), formId);
            fillArtifactForm(artifact.getId(), formId, projectCanvasController);
            }
    }

    private void fillBranchForm() {
        BranchForm form = (BranchForm) forms.get(Constans.branchIndex);
        for (int i = 0; i < project.getBranches().size(); i++){
            Branch segment = project.getBranches().get(i);
            int id = formController.createTableItem(SegmentType.Branch);
           String idName = segment.getName().get(0);
            String isMain = "NO";
            if(segment.isIsMain()){
                isMain = "YES";
            }

            BranchTable table = new BranchTable(idName, isMain, segment.isIsMain(), segment.isExist(), id);

            form.getTableTV().getItems().add(table);
            segmentLists.getBranchObservable().add(table);
        }
    }

    private ClassTable createClassTable(int id, List<String> name, List<String> lclass, List<String> lsuperClass, boolean exist){

        String  idName = name.get(0);
        String classType = lclass.get(0);
        String superClass = lsuperClass.get(0);
        
       return  new ClassTable(idName, classType, superClass, exist, id);
    }

    private void fillTypeForm() {
        TypeForm form = (TypeForm) forms.get(Constans.wuTypeFormIndex);
        for (int i = 0; i < project.getTypes().size(); i++){
            Type segment = project.getTypes().get(i);
            formController.createTableItem(SegmentType.Type);
            ClassTable table = createClassTable(segment.getId(), segment.getName(), segment.getTypeClass(),
                    segment.getTypeSuperClass(), segment.isExist());

            form.getTableTV().getItems().add(table);
            segmentLists.getTypeObservable().add(table);
        }
    }

    private void fillStatusForm() {
        StatusForm form = (StatusForm) forms.get(Constans.statusFormIndex);
        for (int i = 0; i < project.getStatus().size(); i++){
            Status segment = project.getStatus().get(i);
            formController.createTableItem(SegmentType.Status);
            ClassTable table = createClassTable(segment.getId(), segment.getName(), segment.getStatusClass(),
                    segment.getStatusSuperClass(), segment.isExist());

            form.getTableTV().getItems().add(table);
            segmentLists.getStatusTypeObservable().add(table);
        }
    }

    private void fillResolutionForm() {
        ResolutionForm form = (ResolutionForm) forms.get(Constans.resolutionormIndex);
        for (int i = 0; i < project.getResolution().size(); i++){
            Resolution segment = project.getResolution().get(i);
            formController.createTableItem(SegmentType.Resolution);
         
            ClassTable table = createClassTable(segment.getId(), segment.getName(), segment.getResolutionClass(),
                    segment.getResolutionSuperClass(), segment.isExist());

            form.getTableTV().getItems().add(table);
            segmentLists.getResolutionTypeObservable().add(table);
        }
    }

    private void fillRelationForm() {
        RelationForm form = (RelationForm) forms.get(Constans.relationFormIndex);
        for (int i = 0; i < project.getRelation().size(); i++){
            Relation segment = project.getRelation().get(i);
            formController.createTableItem(SegmentType.Relation);
            
            ClassTable table = createClassTable(segment.getId(), segment.getName(), segment.getRelationClass(),
                    segment.getRelationSuperClass(), segment.isExist());

            form.getTableTV().getItems().add(table);
            segmentLists.getRelationTypeObservable().add(table);
        }
    }

    private void fillSeverityForm() {
        SeverityForm form = (SeverityForm) forms.get(Constans.severityFormIndex);
        for (int i = 0; i < project.getSeverity().size(); i++){
            Severity segment = project.getSeverity().get(i);
            formController.createTableItem(SegmentType.Severity);

            ClassTable table = createClassTable(segment.getId(), segment.getName(), segment.getSeverityClass(),
                    segment.getSeveritySuperClass(), segment.isExist());

            form.getTableTV().getItems().add(table);
            segmentLists.getSeverityTypeObservable().add(table);
        }
    }

    private void fillPriorityForm() {
        PriorityForm form = (PriorityForm) forms.get(Constans.priorityFormIndex);
        for (int i = 0; i < project.getPriority().size(); i++){
            Priority segment = project.getPriority().get(i);
            formController.createTableItem(SegmentType.Priority);
            
            ClassTable table = createClassTable(segment.getId(), segment.getName(), segment.getPriorityClass(),
                    segment.getPrioritySuperClass(), segment.isExist());

            form.getTableTV().getItems().add(table);
            segmentLists.getPriorityTypeObservable().add(table);
        }
    }

    private void fillCPR() {
        ConfigPersonRelationForm cprForm = (ConfigPersonRelationForm) forms.get(Constans.cprFormIndex);
        for (int i = 0; i < project.getCpr().size(); i++){
            ConfigPersonRelation cpr = project.getCpr().get(i);
            int id = cpr.getId();
            formController.createTableItem(SegmentType.Config_Person_Relation);
            String name = cpr.getName().get(0);
            
            CPRTable cprTable = new CPRTable(name, "", cpr.isExist(), id);

            cprForm.getTableTV().getItems().add(cprTable);
            segmentLists.getCPRObservable().add(cprTable);
        }
    }

    public void fillPersonForm(int oldFormId){
        int newFormId = formController.createNewForm(SegmentType.Person, projectCanvasController.getCanvasType());
        int personId = identificatorCreater.getRoleIndexToIdMaper().get(oldFormId);
        int newPersonId = identificatorCreater.getRoleSegmentIndexToFormMaper().get(newFormId);
        dataManipulator.copyDataFromPerson(personId, newPersonId);
        fillArtifactForm(newPersonId, newFormId, projectCanvasController);
    }

    private void fillPersonForm(int segmentId, int formId, CanvasController canvasController){

        Person person = dataModel.getRole(segmentId);
        String name = person.getName().get(0);
        canvasController.addCanvasItemFromExistData(SegmentType.Person, formId, name, person.getCoordinates().getXCoordinate(),
                person.getCoordinates().getYCoordinate(), person.getCount(), person.isExist());

    }

    private void fillPersonForm() {
        for (Person person : project.getRoles() ){
            
            String name = person.getName().get(0);

            int formId = identificatorCreater.setDataToRoleMappers(person.getId());
            formController.createNewPersonFormWithoutCreateId(name,  person.isExist(), person.getId(), formId);

            fillPersonForm(person.getId(), formId, projectCanvasController);
        }
    }

    private void fillRoleTypeForm() {
        RoleTypeForm roleTypeForm = (RoleTypeForm) forms.get(Constans.roleTypeIndex);
        for (int i = 0; i < project.getRoleType().size(); i++){
            RoleType segment = project.getRoleType().get(i);
            formController.createTableItem(SegmentType.Role_Type);

            String name = segment.getName().get(0);
            String classType = segment.getRoleTypeClass().get(0);
            String superClassType = segment.getRoleTypeSuperClass().get(0);
            RoleTypeTable table = new RoleTypeTable(name, classType, segment.isExist(), superClassType, segment.getId());
            TableView<RoleTypeTable> roleType = roleTypeForm.getTableTV();
            roleType.getItems().add(table);
            segmentLists.getRoleTypeObservable().add(table);
        }
    }

    private void fillMilestoneForm() {
        MilestoneForm milestoneForm = (MilestoneForm) forms.get(Constans.milestoneFormIndex);
        for (int i = 0; i < project.getMilestones().size(); i++){
            Milestone milestone = project.getMilestones().get(i);
            int id = milestone.getId();
            formController.createTableItem(SegmentType.Milestone);
            String idName = milestone.getName().get(0);
            MilestoneTable milestoneTable = new MilestoneTable(idName, milestone.isExist(), id);
            milestoneForm.getTableTV().getItems().add(milestoneTable);
            segmentLists.getMilestoneObservable().add(milestoneTable);
        }
    }


    private void fillCriterionForm() {
        CriterionForm criterionForm = (CriterionForm) forms.get(Constans.criterionFormIndex);
        for (Criterion criterion : project.getCriterions()){
            formController.createTableItem(SegmentType.Criterion);
            TableView<CriterionTable> criterionView =  criterionForm.getTableTV();
            CriterionTable criterionTable = new CriterionTable(criterion.getName().get(0), criterion.isExist(), criterion.getId());
            criterionView.getItems().add(criterionTable);
           segmentLists.getCriterionObservable().add(criterionTable);
        }

    }



    public void fillPhaseForm(){
        PhaseForm phaseForm = (PhaseForm) forms.get(Constans.phaseFormIndex);
        for (Phase phase : project.getPhases()){
            identificatorCreater.createPhaseID();
            TableView<PhaseTable> phaseView =  phaseForm.getTableTV();
            PhaseTable phaseTable = new PhaseTable(phase.getName().get(0), phase.isExist(), phase.getId());
            phaseView.getItems().add(phaseTable);
        }

    }

    public void fillActivityForm(){
        ActivityForm activityForm = (ActivityForm) forms.get(Constans.activityFormIndex);
        for (Activity activity : project.getActivities()){
            identificatorCreater.createActivityID();
            TableView<ActivityTable> activityView =  activityForm.getTableTV();
            ActivityTable activityTable = new ActivityTable(activity.getName().get(0), activity.isExist(), activity.getId());
            activityView.getItems().add(activityTable);
        }

    }

    public void fillIterationForm(){
        IterationForm iterationForm = (IterationForm) forms.get(Constans.iterationFormIndex);
        for (Iteration iteration : project.getIterations()){
            identificatorCreater.createIterationID();
            TableView<IterationTable> iterationView =  iterationForm.getTableTV();
            IterationTable iterationTable = new IterationTable(iteration.getName().get(0), iteration.isExist(), iteration.getId());
            iterationView.getItems().add(iterationTable);
        }

    }

    public void fillWorkUnitForm(){
        WorkUnitForm workUnitForm = (WorkUnitForm) forms.get(Constans.workUnitFormIndex);
        for (WorkUnit workUnit : project.getWorkUnits()){
            identificatorCreater.createWorkUnitID();
            TableView<WorkUnitTable> workUnitView =  workUnitForm.getTableTV();
            WorkUnitTable workUnitTable = new WorkUnitTable(workUnit.getName().get(0), workUnit.isExist(), workUnit.getId());
            workUnitView.getItems().add(workUnitTable);
        }

    }

    public void fillVCSTagForm(){
        VCSTagForm tagForm = (VCSTagForm) forms.get(Constans.VCSTagIndex);
        for (VCSTag tag : project.getVcsTag()){
            identificatorCreater.createVCSTagID();
            TableView<VCSTagTable> tagView =  tagForm.getTableTV();
            VCSTagTable tagTable = new VCSTagTable(tag.getName().get(0), tag.isExist(), tag.getId());
            tagView.getItems().add(tagTable);
        }

    }
    
    private void repaintLink(CanvasItem item, int formId){

        double x = item.getTranslateX();
        double y = item.getTranslateY();
        double width = item.getLength();
        double height = item.getHeight();

        linkControl.repaintArrow(SegmentType.Artifact, formId, x+1, y+1, width, height );
        Point2D point = new Point2D(x, y);
        item.setPosition(point);
    }

    public void fillLink(){
        for(int i = 0; i < project.getLinks().size(); i++) {
            Link link = project.getLinks().get(i);
            int linkId = link.getId();
            int startId;
            int endId;
            int startFormId;
            int endFormId;
            CanvasItem item;
            CanvasItem endItem;
            switch (link.getType()){
                case PERSON_ARTIFACT:
                    startId = link.getStartIndex();
                    startFormId = identificatorCreater.getRoleSegmentIndexToFormMaper().get(startId);
                    item = projectCanvasController.getListOfItemOnCanvas().get(startFormId);
                    linkControl.ArrowManipulation(false, projectCanvasController, item.getFormIdentificator(), SegmentType.Person, item.getTranslateX(),
                            item.getTranslateY(), item.getLength(), item.getHeight(), true, linkId);
                    repaintLink(item, startFormId);
                   endId = link.getEndIndex();
                   endFormId = identificatorCreater.getArtifactSegmentIdToFormIndexMaper().get(endId);
                    endItem = projectCanvasController.getListOfItemOnCanvas().get(endFormId);
                    
                    linkControl.ArrowManipulation(true, projectCanvasController, endItem.getFormIdentificator(), SegmentType.Artifact,
                            endItem.getTranslateX(), endItem.getTranslateY(), endItem.getLength(), endItem.getHeight(),
                            true, linkId);
                    repaintLink(endItem, endFormId);
                    identificatorCreater.setLinkId(linkId);
                    break;
                case PERSON_CONFIGURATION:
                    startId = link.getStartIndex();
                    startFormId = identificatorCreater.getRoleSegmentIndexToFormMaper().get(startId);
                    item = projectCanvasController.getListOfItemOnCanvas().get(startFormId);
                    linkControl.ArrowManipulation(false, projectCanvasController, item.getFormIdentificator(), SegmentType.Person, item.getTranslateX(),
                            item.getTranslateY(), item.getLength(), item.getHeight(), true, linkId);
                    repaintLink(item, startFormId);
                    endId = link.getEndIndex();
                    endFormId = identificatorCreater.getConfigurationFormIndex(endId);
                   endItem = projectCanvasController.getListOfItemOnCanvas().get(endFormId);

                    linkControl.ArrowManipulation(true, projectCanvasController, endItem.getFormIdentificator(), SegmentType.Configuration,
                            endItem.getTranslateX(), endItem.getTranslateY(), endItem.getLength(), endItem.getHeight(),
                            true, linkId);
                    repaintLink(endItem, endFormId);
                    identificatorCreater.setLinkId(linkId);
                    break;
                case ARTIFACT_CONFIGURATION:
                    startId = link.getStartIndex();
                    startFormId = identificatorCreater.getArtifactIndex(startId);
                    item = projectCanvasController.getListOfItemOnCanvas().get(startFormId);
                    linkControl.ArrowManipulation(false, projectCanvasController, item.getFormIdentificator(), SegmentType.Artifact, item.getTranslateX(),
                            item.getTranslateY(), item.getLength(), item.getHeight(), true, linkId);
                    repaintLink(item, startFormId);
                    endId = link.getEndIndex();
                    endFormId = identificatorCreater.getConfigurationFormIndex(endId);
                    endItem = projectCanvasController.getListOfItemOnCanvas().get(endFormId);

                    linkControl.ArrowManipulation(true, projectCanvasController, endItem.getFormIdentificator(), SegmentType.Configuration,
                            endItem.getTranslateX(), endItem.getTranslateY(), endItem.getLength(), endItem.getHeight(),
                            true, linkId);
                    repaintLink(endItem, endFormId);
                    identificatorCreater.setLinkId(linkId);
                    break;
                case COMMITED_CONFIGURATION_CONFIGURATION:
                    startId = link.getStartIndex();
                    startFormId = identificatorCreater.getCommitedConfigurationFormIndex(startId);
                    item = projectCanvasController.getListOfItemOnCanvas().get(startFormId);
                    linkControl.ArrowManipulation(false, projectCanvasController, item.getFormIdentificator(), SegmentType.Committed_Configuration, item.getTranslateX(),
                            item.getTranslateY(), item.getLength(), item.getHeight(), true, linkId);
                    repaintLink(item, startFormId);
                    endId = link.getEndIndex();
                    endFormId = identificatorCreater.getConfigurationFormIndex(endId);
                    endItem = projectCanvasController.getListOfItemOnCanvas().get(endFormId);

                    linkControl.ArrowManipulation(true, projectCanvasController, endItem.getFormIdentificator(), SegmentType.Configuration,
                            endItem.getTranslateX(), endItem.getTranslateY(), endItem.getLength(), endItem.getHeight(),
                            true, linkId);
                    repaintLink(endItem, endFormId);
                    identificatorCreater.setLinkId(linkId);
                    break;
                case COMMIT_COMMITED_CONFIGURATION:
                    startId = link.getStartIndex();
                    startFormId = identificatorCreater.getCommitFormIndex(startId);
                    item = projectCanvasController.getListOfItemOnCanvas().get(startFormId);
                    linkControl.ArrowManipulation(false, projectCanvasController, item.getFormIdentificator(), SegmentType.Commit, item.getTranslateX(),
                            item.getTranslateY(), item.getLength(), item.getHeight(), true, linkId);
                    repaintLink(item, startFormId);
                    endId = link.getEndIndex();
                    endFormId = identificatorCreater.getCommitedConfigurationFormIndex(endId);
                    endItem = projectCanvasController.getListOfItemOnCanvas().get(endFormId);

                    linkControl.ArrowManipulation(true, projectCanvasController, endItem.getFormIdentificator(), SegmentType.Committed_Configuration,
                            endItem.getTranslateX(), endItem.getTranslateY(), endItem.getLength(), endItem.getHeight(),
                            true, linkId);
                    repaintLink(endItem, endFormId);
                    identificatorCreater.setLinkId(linkId);
                    break;
            }
        }

    }




    /**
     * Geters
     */

    public void setProjectCanvasController(CanvasController projectCanvasController) {
        this.projectCanvasController = projectCanvasController;
    }




}
