package controllers;

import SPADEPAC.ConfigPersonRelation;
import SPADEPAC.Milestone;
import abstractControlPane.ControlPanel;
import abstractform.BasicForm;
import controlPanels.*;
import forms.ConfigurationForm;
import forms.*;
import graphics.CanvasItem;
import graphics.DragAndDropItemPanel;
import interfaces.*;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import model.DataManipulator;
import model.DataModel;
import model.IdentificatorCreater;
import org.controlsfx.control.CheckComboBox;
import services.*;
import tables.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormController {

    private DataManipulator dataManipulator;
    private IdentificatorCreater identificatorCreater;

    private ArrayList<BasicForm> forms;
    private ArrayList<ControlPanel> controlPanels;

    private Map<Integer,CanvasItem> canvasItemList;

    private PhaseForm phaseForm;
    private IterationForm iterationForm;
    private ActivityForm activityForm;
    private WorkUnitForm workUnitForm;
    private ChangeForm changeForm;
    private VCSTagForm VCSTagForm;

    private MilestoneForm milestoneForm;
    private ConfigPersonRelationForm CPRForm;
    private RoleForm roleForm;
    private PriorityForm priorityForm;
    private SeverityForm severityForm;
    private RelationForm relationForm;
    private ResolutionForm resolutionForm;
    private StatusForm statusForm;
    private BranchForm branchFrom;
    private ConfigurationTableForm confTableForm;
    private ProjectForm projectForm;
    private TypeForm typeForm;
    private CriterionForm criterionForm;
    private RoleTypeForm roleTypeForm;

    private ApplicationController applicationController;
    private SegmentLists segmentLists;
    private IFormDataController formDataController;
    private IEditFormController editFormController;
    private IDeleteFormController deleteFormController;
    private DataModel dataModel;
    private ISaveDataModel saveDataModel;

    private int lastConfigurationFormIndex = -1;

    private FormFillController formFillController;
    private DataPreparer dataPreparer;
    private DrawerPanelController drawerPanelController;
    private SelectItemController selectItemController;
    public FormController(IdentificatorCreater identificatorCreater, DataModel dataModel,
                          ApplicationController applicationController, SegmentLists segmentLists, DataPreparer dataPreparer,
                          DrawerPanelController drawerPanelController, SelectItemController selectItemController) {

        this.drawerPanelController = drawerPanelController;
        this.selectItemController = selectItemController;
        this.segmentLists = segmentLists;
        this.dataPreparer = dataPreparer;
        this.applicationController = applicationController;
        this.forms = new ArrayList<>();
        this.controlPanels = new ArrayList<>();
        this.canvasItemList = new HashMap<>();

        this.dataModel = dataModel;
        this.saveDataModel = dataModel.getSaveDataModel();

        this.identificatorCreater = identificatorCreater;

        }

    public void initBasicForms(IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController){

        this.formDataController = formDataController;
        this.editFormController = editFormController;
        this.deleteFormController = deleteFormController;

        controlPanels.add(new ProjectControlPanel("Edit", formDataController, editFormController,this));

        CanvasController canvasController = new CanvasController(CanvasType.Phase, applicationController);
        phaseForm =new PhaseForm(this, formDataController, editFormController, deleteFormController, canvasController ,
                new DragAndDropItemPanel(canvasController, Constans.phaseDragTextIndexs, drawerPanelController), SegmentType.Phase, Constans.phaseFormIndex);
        forms.add(phaseForm);

        iterationForm = new IterationForm(this, formDataController, editFormController, deleteFormController,  canvasController, new DragAndDropItemPanel(canvasController,
                Constans.iterationDragTextIndexs, drawerPanelController),SegmentType.Iteration, Constans.iterationFormIndex);
        forms.add(iterationForm);

        activityForm =  new ActivityForm(this, formDataController, editFormController, deleteFormController,  canvasController, new DragAndDropItemPanel(canvasController,
                Constans.activityDragTextIndexs, drawerPanelController), SegmentType.Activity, Constans.activityFormIndex);
        forms.add(activityForm);

        workUnitForm = new WorkUnitForm(this, formDataController, editFormController, deleteFormController, canvasController,
                SegmentType.Work_Unit, Constans.workUnitFormIndex);
        forms.add(workUnitForm);

        milestoneForm = new MilestoneForm(
                this, formDataController, editFormController, deleteFormController,  SegmentType.Milestone);
        forms.add(milestoneForm);

        criterionForm = new CriterionForm(this, formDataController, editFormController, deleteFormController, SegmentType.Criterion);
        forms.add(criterionForm);

        CPRForm = new ConfigPersonRelationForm(this, formDataController, editFormController, deleteFormController, SegmentType.Config_Person_Relation);
        forms.add(CPRForm);

        roleTypeForm = new RoleTypeForm(this, formDataController, editFormController, deleteFormController, SegmentType.Role_Type);
        forms.add(roleTypeForm);


        priorityForm = new PriorityForm(this, formDataController, editFormController, deleteFormController, SegmentType.Priority);
        forms.add(priorityForm);

        severityForm = new SeverityForm(this, formDataController, editFormController, deleteFormController, SegmentType.Severity);
        forms.add(severityForm);

        relationForm = new RelationForm(this, formDataController, editFormController, deleteFormController, SegmentType.Relation);
        forms.add(relationForm);

        resolutionForm = new ResolutionForm(this, formDataController, editFormController, deleteFormController, SegmentType.Resolution);
        forms.add(resolutionForm);

        statusForm = new StatusForm(this, formDataController, editFormController, deleteFormController, SegmentType.Status);
        forms.add(statusForm);

        typeForm = new TypeForm(this, formDataController, editFormController, deleteFormController, SegmentType.Type);
        forms.add(typeForm);

        branchFrom = new BranchForm(this, formDataController, editFormController, deleteFormController, SegmentType.Branch);
        forms.add(branchFrom);

        changeForm = new ChangeForm(this, formDataController, editFormController, deleteFormController, SegmentType.Change);
        forms.add(changeForm);

        VCSTagForm = new VCSTagForm(this, formDataController, editFormController, deleteFormController, SegmentType.VCSTag);
        forms.add(VCSTagForm);


        forms.add(roleForm);



    }


    /**
     * Metoda pro určení metody pro vytvoření konkrétního segmentu nebo elementu
     *
     *            kořenový formulář
     * @return identifikátory objektu pro CanvasItem
     */
    public int createNewForm(SegmentType type, CanvasType canvasType) {

            switch (type) {
                case Configuration:
                    return createNewConfigurationPanel();
                case Person:
                    return createNewRolePanel();
                case Artifact:
                    return createNewArtifactPanel();
                case Commit:
                    return createNewCommitPanel();
                case Committed_Configuration:
                    return createNewCommitedConfigurationPanel();
                default:
                    return -1;
            }

    }

    private int createNewCommitPanel() {
        int formIndex = createNewCommitFormWithoutManipulator("", true);
        int id = identificatorCreater.getCommitId(formIndex);
        saveDataModel.createNewCommit(id);
        return formIndex;
    }


    public int createNewCommitFormWithoutCreateId(String name, boolean exist, int id, int formIndex){
        CommitTable commitTable = new CommitTable(name, "", true, exist, id);

        CommitControlPanel commitControlPanel = new CommitControlPanel(
                "Edit", formDataController, editFormController,this, commitTable, id, formIndex);
        segmentLists.getCommitObservable().add(commitTable);
        controlPanels.add(commitControlPanel);
        return formIndex;

    }


        public int createNewCommitFormWithoutManipulator(String name, boolean exist){

        int index = identificatorCreater.createCommitID();
        int id = identificatorCreater.getCommitId(index);
        return createNewCommitFormWithoutCreateId(name, exist, id, index);
    }


    public int createNewCommitedConfigurationFormWithoutCreateId(String name, boolean exist, int id, int formIndex){
        CommitedConfigurationTable commitTable = new CommitedConfigurationTable(name,"", exist, id);

        CommitedConfigurationControlPanel commitControlPanel = new CommitedConfigurationControlPanel(
                "Edit", formDataController, editFormController,this, commitTable, id, formIndex);
        segmentLists.getCommitedConfigurationObservable().add(commitTable);
        controlPanels.add(commitControlPanel);
        return formIndex;
    }

    private int createNewCommitedConfigurationPanel() {
        int formIndex = createNewCommitedConfigurationPanelFormWithoutManipulator("", true);
        int id = identificatorCreater.getCommitedConfigurationId(formIndex);
        saveDataModel.createNewCommitedConfiguration(id);
        return formIndex;
    }

    public int createNewCommitedConfigurationPanelFormWithoutManipulator(String name, boolean exist){

        int index = identificatorCreater.createCommiedConfigurationtID();
        int id = identificatorCreater.getCommitedConfigurationId(index);
        return createNewCommitedConfigurationFormWithoutCreateId(name, exist, id, index);
    }
    
    private int createNewRolePanel() {
        int formIndex = createNewRoleFormWithoutManipulator("", true);
        int id = identificatorCreater.getRoleIndexToIdMaper().get(formIndex);
        saveDataModel.createNewRole(id);
        return formIndex;
    }

    public int createNewPersonFormWithoutCreateId(String name, boolean exist, int id, int formIndex){

        PersonTable personTable = new PersonTable(name, exist, id);

        PersonControlPanel personControlPanel = new PersonControlPanel(
                "Edit", formDataController, editFormController,this, personTable, id, formIndex);

        segmentLists.getRoleObservable().add(personTable);
        controlPanels.add(personControlPanel);
        return formIndex;
    }


    public int createNewRoleFormWithoutManipulator(String name, boolean exist){

        int index = identificatorCreater.createRoleID();
        int id = identificatorCreater.getRoleIndexToIdMaper().get(index);
        return createNewPersonFormWithoutCreateId(name, exist, id, index);
    }

        private int createNewArtifactPanel() {
        int formIndex = createNewArtifactFormWithoutManipulator("","", true);
        int id = identificatorCreater.getArtifactIndexToIdMaper().get(formIndex);
        saveDataModel.createNewArtifact(id);
        return formIndex;
    }

    public int createNewArtifactFormWithoutCreateId(String name, boolean exist, int id, int formIndex){

        ArtifactTable artifactTable = new ArtifactTable(name, exist, id);

        ArtifactControlPanel artifactControlPanel = new ArtifactControlPanel(
                "Edit", formDataController, editFormController,this, artifactTable, id, formIndex);

        segmentLists.getArtifactObservable().add(artifactTable);
        controlPanels.add(artifactControlPanel);
        return formIndex;

    }

    public int createNewArtifactFormWithoutManipulator(String name, String description, boolean exist){
        int index = identificatorCreater.createArtifactID();
        int id = identificatorCreater.getArtifactIndexToIdMaper().get(index);
        return createNewArtifactFormWithoutCreateId(name, exist, id, index);

    }

    private int createNewChangeForm(int id) {
      saveDataModel.createNewChange(id);

        return  id;
    }
    public int createNewChangeFormWithoutManipulator(){

        int index = identificatorCreater.createChangeID();
        ChangeForm changeForm = new ChangeForm(this, formDataController, editFormController, deleteFormController, SegmentType.Change);
        forms.add(index, changeForm);
        return index;
    }

    private int createNewConfigurationPanel() {
        int formIndex = createNewConfiguratioFormWithoutManipulator("", true);
        int id = identificatorCreater.getConfigurationId(formIndex);
        saveDataModel.createNewConfiguration(id);
        return formIndex;
    }

    public int createNewConfigurationFormWithoutCreateId(String name, boolean exist, int id, int formIndex){

        ConfigTable configTable = new ConfigTable(name, "", formIndex, exist, id);

        ConfigurationControlPanel roleControlPanel = new ConfigurationControlPanel(
                "Edit", formDataController, editFormController,this, configTable, id, formIndex);

        segmentLists.getConfigObservable().add(configTable);
        controlPanels.add(roleControlPanel);
        return formIndex;
    }


        public int createNewConfiguratioFormWithoutManipulator(String name, boolean exist){
        int index = identificatorCreater.createConfigurationID();
        int id = identificatorCreater.getConfigurationId(index);

        return createNewConfigurationFormWithoutCreateId(name, exist, id, index);
    }

    private int createNewWorkUnitForm(int id) {
        saveDataModel.createNewWorkUnit(id);
        return id;
    }

    public int createNewWorkUnitFormWithoutManipulator(CanvasType canvasType){
        int index = identificatorCreater.createWorkUnitID();
        int id = identificatorCreater.getWorkUnitIndexToIdMaper().get(index);
        CanvasController canvasController = new CanvasController(canvasType, applicationController);
        WorkUnitForm workUnitForm = new WorkUnitForm(this, formDataController, editFormController, deleteFormController,
                canvasController, SegmentType.Work_Unit, index);

        forms.add(index, workUnitForm);

        return  index;
    }

    private int createNewActivityForm(int id) {

        saveDataModel.createNewActivity(id);

        return id;
    }

    public int createNewActivityFormWithoutManipulator(){
        int index = identificatorCreater.createActivityID();
        CanvasController canvasController = new CanvasController(CanvasType.Activity, applicationController);
        ActivityForm activityForm = new ActivityForm(this, formDataController, editFormController, deleteFormController,  canvasController, new DragAndDropItemPanel(canvasController,
                Constans.activityDragTextIndexs, drawerPanelController), SegmentType.Activity, index);
        forms.add(index, activityForm);

        return  index;
    }

    public int createNewPhaseForm(int id){
        //int index = createNewPhaseFormWithoutManipulator();
        saveDataModel.createNewPhase(id);
        return  id;
    }

    public int createNewPhaseFormWithoutManipulator(){

        int index = identificatorCreater.createPhaseID();
        CanvasController canvasController = new CanvasController(CanvasType.Phase, applicationController);
        PhaseForm phaseForm = new PhaseForm(this, formDataController, editFormController, deleteFormController,  canvasController, new DragAndDropItemPanel(canvasController,
                Constans.phaseDragTextIndexs, drawerPanelController), SegmentType.Phase, index);
        forms.add(index, phaseForm);

        return  index;
    }

    int createNewIterationForm(int id){

        saveDataModel.createNewIteration(id);

        return id;
    }

    public int createNewIterationFormWithoutManipulator(){
        int index = identificatorCreater.createIterationID();

        CanvasController canvasController = new CanvasController(CanvasType.Iteration, applicationController);

        IterationForm iterationForm = new IterationForm(this, formDataController, editFormController, deleteFormController,  canvasController, new DragAndDropItemPanel(canvasController,
                Constans.iterationDragTextIndexs, drawerPanelController),SegmentType.Iteration, index);

        return  index;
    }

    public void showForm(int formIdentificator) {
        BasicForm form = forms.get(formIdentificator);
        switch (form.getSegmentType()){
            case Milestone:

                List<Milestone> milestones = dataModel.getMilestones();
                MilestoneForm milestoneForm = (MilestoneForm) form;
                TableView tableView = milestoneForm.getTableTV();
                tableView.getItems().clear();
                for (int i = 0; i < milestones.size(); i++) {
                    Milestone milestone = milestones.get(i);
                    tableView.getItems().add(dataPreparer.prepareMilestoneTable(milestone.getName().get(0), milestone.getId()));

                }
                tableView.sort();
                tableView.refresh();
                break;

            case Config_Person_Relation:
                List<ConfigPersonRelation> cprlist = dataModel.getCpr();
                ConfigPersonRelationForm cprForm = (ConfigPersonRelationForm) form;
                TableView cprTableView = cprForm.getTableTV();
                cprTableView.getItems().clear();
                for (int i = 0; i < cprlist.size(); i++) {
                    ConfigPersonRelation cpr = cprlist.get(i);
                    CPRTable cprTable = dataPreparer.prepareCPRTable(cpr.getName().get(0), cpr.getId());
                    cprTableView.getItems().add(cprTable);

                }
                cprTableView.sort();
                cprTableView.refresh();
                break;
            default:

        }


       // form.show();
       // form.toFront();

    }

    public int[] getCoordsFromItem(int indexForm){

        CanvasItem item = canvasItemList.get(indexForm);
        int x = (int) item.getTranslateX();
        int y = (int) item.getTranslateY();
        int[] coords = {x, y};
        return coords;
    }

    public void setNameToItem(int index, String name) {

        CanvasItem item = canvasItemList.get(index);
        item.setNameText(name);
    }

    public void setItemInstanceCount(int index, int instanceCount) {
        CanvasItem item = canvasItemList.get(index);
        item.setInstanceCountToItem(instanceCount);
    }

    public String getSegmentIdentificator(int indexForm){
        CanvasItem item = canvasItemList.get(indexForm);
        return item.getSegmentIdentificator();
    }

    public void removeCanvasItemFromList(int id) {
        canvasItemList.put(id,null);
    }

    public void addCanvasItemToList(int formIndex, CanvasItem item) {
        canvasItemList.put(formIndex,item);
    }


    public void setItemColor(int indexForm, boolean isExist) {

        CanvasItem item = canvasItemList.get(indexForm);
        if(!isExist){
            item.getSegmentInfo().setRectangleColor(Constans.nonExistRectangleBorderColor);
        } else {
            item.getSegmentInfo().setRectangleColor(Constans.rectangleBorderColor);
        }

    }

    public Node getMainPanelFromForm(int formId) {

        return forms.get(formId);

    }

    public Node getConfigurationMainPanelFromSegmentId(int id) {

        return getMainPanelFromForm(identificatorCreater.getConfigurationFormIndex(id));

    }

    public int getSegmetIdFromFormId(SegmentType type, int formIndex) {

        switch (type) {
            case Person:
                return identificatorCreater.getRoleId(formIndex);
            case Iteration:
                return identificatorCreater.getIterationId(formIndex);
            case Activity:
                return identificatorCreater.getActivityId(formIndex);
            case Committed_Configuration:
                return identificatorCreater.getCommitedConfigurationId(formIndex);
            case Configuration:
                return identificatorCreater.getConfigurationId(formIndex);
            case Commit:
                return identificatorCreater.getCommitId(formIndex);
            case Artifact:
                return identificatorCreater.getArtifactId(formIndex);
            default:
                return -1;
        }
    }


    public int createTableItem(SegmentType segmentType) {
        int id;
        switch (segmentType) {
            case Work_Unit:
                id = identificatorCreater.createWorkUnitID();
                createNewWorkUnitForm(id);
                return id;
            case VCSTag:
                id = identificatorCreater.createVCSTagID();
                createNewVCSTagForm(id);
                return id;
            case Iteration:
                id = identificatorCreater.createIterationID();
                createNewIterationForm(id);
                return id;
            case Activity:
                id = identificatorCreater.createActivityID();
                createNewActivityForm(id);
                return id;
            case Change:
                id = identificatorCreater.createChangeID();
                createNewChangeForm(id);
                return id;
            case Phase:
                id = identificatorCreater.createPhaseID();
                createNewPhaseForm(id);
                return id;
            case Branch:
                return identificatorCreater.createBranchID();
            case Priority:
                return identificatorCreater.createPriorityID();
            case Severity:
            return identificatorCreater.createSeverityID();
            case Milestone:
             return identificatorCreater.createMilestoneID();
            case Criterion:
             return  identificatorCreater.createCriterionID();
            case Person:
               return identificatorCreater.createRoleID();
            case Role_Type:
               return identificatorCreater.createRoleTypeID();
            case Config_Person_Relation:
                id = identificatorCreater.createCPRID();
                createNewCPRForm(id);
                return id;
            case Relation:
                return identificatorCreater.createRelationID();
            case Resolution:
                return identificatorCreater.createResolutionID();
            case Status:
                return identificatorCreater.createStatusID();
            case Type:
                return identificatorCreater.createTypeID();
            case Tag:
                return identificatorCreater.createTagID();
            default:
                return -1;
        }
    }

    private int createNewCPRForm(int id) {
        saveDataModel.createNewCPR(id);
        return id;
    }

    private int createNewVCSTagForm(int id) {
        saveDataModel.createNewVCSTag(id);
        return  id;
    }


    public ArrayList<BasicForm> getForms() {
        return forms;
    }

    public void setNewItemToConfigurationTable(String actName, String isRelease, int formIndex, int id) {
        ConfigTable configTable = new ConfigTable(actName, isRelease, formIndex, true, id);
        ConfigurationTableForm configurationTableForm = (ConfigurationTableForm) forms.get(Constans.configurationFormIndex);
        configurationTableForm.getTableTV().getItems().add(configTable);
        configurationTableForm.getTableTV().sort();
        configurationTableForm.createConfigItem();
    }



    public void setConfigurationFormToTableForm(){
        ConfigurationTableForm configurationTableForm = (ConfigurationTableForm) forms.get(Constans.configurationFormIndex);
        configurationTableForm.getMainPanel().setLeft(getMainPanelFromForm(lastConfigurationFormIndex));
    }




    public void upDateComboBox(ComboBox<BasicTable> cb, int position){
        cb.getSelectionModel().select(position);
    }

    public void upDateCheckComboBox(CheckComboBox<BasicTable> cb, ArrayList<Integer> positions) {
        for (int i : positions) {
            cb.getCheckModel().check(i);
        }
    }

    public void updateCheckComboBoxItem(SegmentType formType, SegmentType comboBoxType, ArrayList<Integer> formList, ArrayList<Integer> indices) {
        switch (formType) {
            case Configuration:
                ComboBox<BasicTable> cb = null;

                for (int i : formList) {
                    ConfigurationForm form = (ConfigurationForm) forms.get(i);
                    switch (comboBoxType ) {
                        case Config_Person_Relation:
                        upDateCheckComboBox(form.getCprCB(), indices);
                        break;
                        default:
                    }
                }
                break;

        }
    }
    private void updateWUListItem(SegmentType type, ArrayList<Integer> wuList) {
            switch (type) {
                case Priority:
                    for (int i : wuList){
                        WorkUnitForm form = (WorkUnitForm) forms.get(i);
                    //    form.getPriorityCB().getSelectionModel().select(0);
                    }
                    break;
                case Severity:
                    for (int i : wuList){
                        WorkUnitForm form = (WorkUnitForm) forms.get(i);
                   //     form.getSeverityCB().getSelectionModel().select(0);
                    }
                    break;
                case Person:
                    for (int i : wuList){
                        WorkUnitForm form = (WorkUnitForm) forms.get(i);
                 //       form.getAuthorRoleCB().getSelectionModel().select(0);
                 //       form.getAsigneeRoleCB().getSelectionModel().select(0);
                    }
                    break;
                case Resolution:
                    for (int i : wuList){
                        WorkUnitForm form = (WorkUnitForm) forms.get(i);
                  //      form.getResolutionCB().getSelectionModel().select(0);
                    }
                    break;
                case Status:
                    for (int i : wuList){
                        WorkUnitForm form = (WorkUnitForm) forms.get(i);
                  //      form.getStatusCB().getSelectionModel().select(0);
                    }
                    break;
                case Type:
                    for (int i : wuList){
                        WorkUnitForm form = (WorkUnitForm) forms.get(i);
                 //       form.getTypeCB().getSelectionModel().select(0);
                    }
                    break;
                default:

            }

        }


    public void setFormFillController(FormFillController formFillController) {
        this.formFillController = formFillController;
    }

    public Map<Integer, CanvasItem> getCanvasItemList() {
        return canvasItemList;
    }

    public ObservableList<BasicTable> getCriterionObservable() {
        return segmentLists.getCriterionObservable();
    }

    public ObservableList<BasicTable> getRoleObservable() {
        return segmentLists.getRoleObservable();
    }

    public ObservableList<BasicTable> getRoleTypeObservable() {
        return segmentLists.getRoleTypeObservable();
    }

    public BasicForm getForm(int formiIndex) {
        return forms.get(formiIndex);
    }

    public void showEditControlPanel(ControlPanel editControlPanel) {
        drawerPanelController.showRightPanel(editControlPanel);
    }

    public void showEditControlPanel(int formIndex) {

        ControlPanel panel = controlPanels.get(formIndex);
        IControlPanel controlPanel = (IControlPanel) panel;
        controlPanel.showEditControlPanel();
        drawerPanelController.showRightPanel(panel);
    }

    public SegmentLists getSegmentLists() {
        return segmentLists;
    }


    public void setCoordinatesToCanvasItem(SegmentType segmentType, double newTranslateX, double newTranslateY, int fromIndex) {
        int id = 0;
        switch (segmentType){
            case Person:
                id = identificatorCreater.getRoleId(fromIndex);
                editFormController.editCoordsInRole(newTranslateX, newTranslateY, id);
                break;
            case Commit:
                id = identificatorCreater.getCommitId(fromIndex);
                editFormController.editCoordsInCommit(newTranslateX, newTranslateY, id);
            break;

            case Committed_Configuration:
                id = identificatorCreater.getCommitedConfigurationId(fromIndex);
                editFormController.editCoordsInCommitedConfiguration(newTranslateX, newTranslateY, id);
                break;
            case Configuration:
                id = identificatorCreater.getConfigurationId(fromIndex);
                editFormController.editCoordsInConfiguration(newTranslateX, newTranslateY, id);
                break;
            case Artifact:
                id = identificatorCreater.getArtifactIndexToIdMaper().get(fromIndex);
                editFormController.editCoordsInArtifact(newTranslateX, newTranslateY, id);
                break;
        }
    }

    private Integer[] findCorectId(Integer startIndex, Integer endIndex, Integer startIndex1, Integer endIndex1){
        Integer[] result = new Integer[2];

        Integer firstResult = -1;
        Integer secondResult = -1;

        if( startIndex != null){
            firstResult = startIndex;
        }else{
            firstResult = endIndex;
        }

        if(startIndex1 != null){
            secondResult = startIndex1;
        }else {
            secondResult = endIndex1;
        }
        result[0] = firstResult;
        result[1] = secondResult;
        return result;
    }

    public void createCommitToCommitedConfigurationRelation(int linkId, int startId, int endId, boolean isXML) {
          Integer[] result = findResultsFromCommitToCommitedConfigurationRelation(startId, endId);

        formDataController.createCommitToCommitedConfigurationRelation(linkId, result[0], result[1], isXML);
    }

    public Integer[] findResultsFromCommitToCommitedConfigurationRelation(int startSegmentId, int endSegmentId){
        Integer startIndex = identificatorCreater.getCommitId(startSegmentId);
        Integer endIndex = identificatorCreater.getCommitId(endSegmentId);

        Integer startIndex1 = identificatorCreater.getCommitedConfigurationId(startSegmentId);
        Integer endIndex2 = identificatorCreater.getCommitedConfigurationId(endSegmentId);

        return findCorectId(startIndex, endIndex, startIndex1, endIndex2);

    }


    public void createCommitedConfigurationToConfigurationRelation(int linkId, int startSegmentId, int endSegmentId, boolean isXML) {

        Integer[] result = findResultsFromCommitedConfigurationToConfigurationRelation(startSegmentId, endSegmentId);
        formDataController.createCommitedConfigurationToConfigurationRelation(linkId, result[0], result[1], isXML);

    }

    public Integer[] findResultsFromCommitedConfigurationToConfigurationRelation(int startSegmentId, int endSegmentId){
        Integer startIndex = identificatorCreater.getCommitedConfigurationId(startSegmentId);
        Integer endIndex = identificatorCreater.getCommitedConfigurationId(endSegmentId);

        Integer startIndex1 = identificatorCreater.getConfigurationId(startSegmentId);
        Integer endIndex2 = identificatorCreater.getConfigurationId(endSegmentId);

        return findCorectId(startIndex, endIndex, startIndex1, endIndex2);
    }


    public Integer[] findResultsFromArtifactToConfigurationRelation(int startSegmentId, int endSegmentId){
        Integer startIndex = identificatorCreater.getArtifactId(startSegmentId);
        Integer endIndex = identificatorCreater.getArtifactId(endSegmentId);

        Integer startIndex1 = identificatorCreater.getConfigurationId(startSegmentId);
        Integer endIndex2 = identificatorCreater.getConfigurationId(endSegmentId);

        return findCorectId(startIndex, endIndex, startIndex1, endIndex2);
    }


    public void createArtifactToConfigurationRelation(int linkId, int startSegmentId, int endSegmentId, boolean isXML) {

        Integer[] result = findResultsFromArtifactToConfigurationRelation(startSegmentId, endSegmentId);
        formDataController.createArtifactToConfigurationRelation(linkId, result[0], result[1], isXML);

    }

    public Integer[] findResultsFromPersonToArtifactRelation(int startSegmentId, int endSegmentId){
        Integer startIndex = identificatorCreater.getRoleId(startSegmentId);
        Integer endIndex = identificatorCreater.getRoleId(endSegmentId);

        Integer startIndex1 = identificatorCreater.getArtifactId(startSegmentId);
        Integer endIndex2 = identificatorCreater.getArtifactId(endSegmentId);

        return findCorectId(startIndex, endIndex, startIndex1, endIndex2);

    }

    public Integer[] findResultsFromPersonToConfigurationRelation(int startSegmentId, int endSegmentId){
        Integer startIndex = identificatorCreater.getRoleId(startSegmentId);
        Integer endIndex = identificatorCreater.getRoleId(endSegmentId);

        Integer startIndex1 = identificatorCreater.getConfigurationId(startSegmentId);
        Integer endIndex2 = identificatorCreater.getConfigurationId(endSegmentId);

        return findCorectId(startIndex, endIndex, startIndex1, endIndex2);

    }


    public void createRoleToConfigurationRelation(int linkId, int startSegmentId, int endSegmentId, boolean isXML) {

        Integer[] result = findResultsFromPersonToConfigurationRelation(startSegmentId, endSegmentId);
        formDataController.createNewPersonToConfigurationRelation(linkId, result[0], result[1], isXML);

    }

    public void createRoleToArtifactRelation(int linkId, int startSegmentId, int endSegmentId, boolean isXML) {

        Integer[] result = findResultsFromPersonToArtifactRelation(startSegmentId, endSegmentId);
        formDataController.createNewPersonToArtifactRelation(linkId, result[0], result[1], isXML);

    }
}

