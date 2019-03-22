package controllers;

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
import model.DataManipulator;
import model.DataModel;
import model.IdentificatorCreater;
import org.controlsfx.control.CheckComboBox;
import services.*;
import tables.*;

import java.util.ArrayList;
import java.util.HashMap;
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
                SegmentType.WorkUnit, Constans.workUnitFormIndex);
        forms.add(workUnitForm);

        milestoneForm = new MilestoneForm(
                this, formDataController, editFormController, deleteFormController,  SegmentType.Milestone);
        forms.add(milestoneForm);

        criterionForm = new CriterionForm(this, formDataController, editFormController, deleteFormController, SegmentType.Criterion);
        forms.add(criterionForm);

        CPRForm = new ConfigPersonRelationForm(this, formDataController, editFormController, deleteFormController, SegmentType.ConfigPersonRelation);
        forms.add(CPRForm);

        roleTypeForm = new RoleTypeForm(this, formDataController, editFormController, deleteFormController, SegmentType.RoleType);
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
                case Role:
                    return createNewRolePanel();
                case Artifact:
                    return createNewArtifactPanel();
                case Commit:
                    return createNewCommitPanel();
                case CommittedConfiguration:
                    return createNewCommitedConfigurationPanel();
                default:
                    return -1;
            }

    }

    private int createNewCommitPanel() {
        int formIndex = createNewCommitFormWithoutManipulator();
        int id = identificatorCreater.getCommitId(formIndex);
        saveDataModel.createNewCommit(id);
        return formIndex;
    }

    public int createNewCommitFormWithoutManipulator(){

        int index = identificatorCreater.createCommitID();
        int id = identificatorCreater.getCommitId(index);
        CommitTable commitTable = new CommitTable(id + "_", "", true, id);

        CommitControlPanel commitControlPanel = new CommitControlPanel(
                "Edit", formDataController, editFormController,this, commitTable, id, index);

        controlPanels.add(commitControlPanel);
        return index;
    }

    private int createNewCommitedConfigurationPanel() {
        int formIndex = createNewCommitedConfigurationPanelFormWithoutManipulator();
        int id = identificatorCreater.getCommitedConfigurationId(formIndex);
        saveDataModel.createNewCommitedConfiguration(id);
        return formIndex;
    }

    public int createNewCommitedConfigurationPanelFormWithoutManipulator(){

        int index = identificatorCreater.createCommiedConfigurationtID();
        int id = identificatorCreater.getCommitedConfigurationId(index);
        CommitedConfigurationTable commitTable = new CommitedConfigurationTable(id + "_","",id);

        CommitedConfigurationControlPanel commitControlPanel = new CommitedConfigurationControlPanel(
                "Edit", formDataController, editFormController,this, commitTable, id, index);

        controlPanels.add(commitControlPanel);
        return index;
    }
    
    private int createNewRolePanel() {
        int formIndex = createNewRoleFormWithoutManipulator();
        int id = identificatorCreater.getRoleIndexToIdMaper().get(formIndex);
        saveDataModel.crateNewRole(id);
        return formIndex;
    }

    public int createNewRoleFormWithoutManipulator(){

        int index = identificatorCreater.createRoleID();
        int id = identificatorCreater.getRoleIndexToIdMaper().get(index);
        RoleTable roleTable = new RoleTable(id + "_", "", "", id);

        RoleControlPanel roleControlPanel = new RoleControlPanel(
                "Edit", formDataController, editFormController,this, roleTable, id, index);

        segmentLists.getRoleObservable().add(roleTable);
        controlPanels.add(roleControlPanel);
        return index;

    }

    private int createNewArtifactPanel() {
        int formIndex = createNewArtifactFormWithoutManipulator();
        int id = identificatorCreater.getArtifactIndexToIdMaper().get(formIndex);
        saveDataModel.createNewArtifact(id);
        return formIndex;
    }
    

    public int createNewArtifactFormWithoutManipulator(){
        int index = identificatorCreater.createArtifactID();
        int id = identificatorCreater.getArtifactIndexToIdMaper().get(index);
        ArtifactTable artifactTable = new ArtifactTable(id + "_", "", id);

        ArtifactControlPanel artifactControlPanel = new ArtifactControlPanel(
                "Edit", formDataController, editFormController,this, artifactTable, id, index);

        segmentLists.getArtifactObservable().add(artifactTable);
        controlPanels.add(artifactControlPanel);
        return index;

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
        int formIndex = createNewConfiguratioFormWithoutManipulator();
        int id = identificatorCreater.getConfigurationId(formIndex);
        saveDataModel.createNewConfiguration(id);
        return formIndex;
    }

    public int createNewConfiguratioFormWithoutManipulator(){
        int index = identificatorCreater.createConfigurationID();
        int id = identificatorCreater.getConfigurationId(index);
        ConfigTable configTable = new ConfigTable(id + "_", "", index, id);

        ConfigurationControlPanel roleControlPanel = new ConfigurationControlPanel(
                "Edit", formDataController, editFormController,this, configTable, id, index);

        segmentLists.getConfigObservable().add(configTable);
        controlPanels.add(roleControlPanel);
        return index;
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
                canvasController, SegmentType.WorkUnit, index);

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
            case Phase:
        //        formFillController.fillPhaseForm(identificatorCreater.getRoleId(formIdentificator), formIdentificator);
                break;
            case Iteration:
                formFillController.fillIterationForm(identificatorCreater.getIterationId(formIdentificator), formIdentificator);
                break;
            case Activity:
                formFillController.fillActivityForm(identificatorCreater.getActivityId(formIdentificator), formIdentificator);
                break;
            case WorkUnit:
                formFillController.fillWorkUnitForm(identificatorCreater.getWorkUnitIndex(formIdentificator), formIdentificator);
                break;
            case Change:
                //  formFillController.fillChangeForm(identificatorCreater.getIterationId(formIdentificator), formIdentificator);break;
            case Artifact:
                //  formFillController.fillArtifactForm(identificatorCreater.getIterationId(formIdentificator), formIdentificator);
                break;
            case Milestone:

//                List<Milestone> milestones = dataModel.getMilestones();
//                MilestoneForm milestoneForm = (MilestoneForm) form;
//                TableView tableView = milestoneForm.getTableTV();
//                tableView.getItems().clear();
//                for (int i = 0; i < milestones.size(); i++) {
//                    Milestone milestone = milestones.get(i);
//                    tableView.getItems().add(dataPreparer.prepareMilestoneTable(milestone.getName(), milestone.getDescription(), milestone.getId(),
//                            milestone.getCriteriaIndexs(), segmentLists.getCriterionObservable()));
//
//                }
//                tableView.sort();
//                tableView.refresh();
//                break;
            case Role:

//                List<Role> roles = dataModel.getRoles();
//                RoleForm roleForm = (RoleForm) form;
//                TableView roleTableView = roleForm.getTableTV();
//                roleTableView.getItems().clear();
//                for (int i = 0; i < roles.size(); i++) {
//                    Role role = roles.get(i);
//                    RoleTable roleTable = dataPreparer.prepareRoleTable(role.getName(), role.getDescription(), role.getId(),
//                            role.getType(), segmentLists.getRoleTypeObservable());
//                    roleTableView.getItems().add(roleTable);
//
//                }
//                roleTableView.sort();
         //       roleTableView.refresh();
                break;
            case ConfigPersonRelation:
//                List<ConfigPersonRelation> cprlist = dataModel.getCpr();
//                ConfigPersonRelationForm cprForm = (ConfigPersonRelationForm) form;
//                TableView cprTableView = cprForm.getTableTV();
//                cprTableView.getItems().clear();
//                for (int i = 0; i < cprlist.size(); i++) {
//                    ConfigPersonRelation cpr = cprlist.get(i);
//                    CPRTable cprTable = dataPreparer.prepareCPRTable(cpr.getName(), cpr.getPersonIndex(), cpr.getId(), segmentLists.getRoleObservable());
//                    cprTableView.getItems().add(cprTable);
//
//                }
//                cprTableView.sort();
//                cprTableView.refresh();
                break;
            default:

        }


       // form.show();
       // form.toFront();

    }

    public boolean isFormFill(int formIdentificator) {
       return forms.get(formIdentificator).isSave();
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

    public void createChangeArtifactRelation(int startId, int endId) {
        //TODO mozna pridat do FormDataControlleru
        Integer artifactStartIndex = identificatorCreater.getArtifactIndex(startId);
        Integer artifactEndIndex = identificatorCreater.getArtifactIndex(endId);

        Integer changeStartIndex = identificatorCreater.getChangeId(startId);
        Integer changeEndIndex = identificatorCreater.getChangeId(endId);

        int artifactIndex;
        int changeIndex;

        if( artifactStartIndex != null){
            artifactIndex = artifactStartIndex;
        }else{
            artifactIndex = artifactEndIndex;
        }

        if(changeStartIndex != null){
            changeIndex = changeStartIndex;
        }else {
            changeIndex = changeEndIndex;
        }

        saveDataModel.createChangeArtifactRelation(artifactIndex,changeIndex);
    }

    public void createWorkUnitRelation(int startSegmentId, int endSegmentId) {

        Integer startIndex = identificatorCreater.getWorkUnitIndex(startSegmentId);
        Integer endIndex = identificatorCreater.getWorkUnitIndex(endSegmentId);

        saveDataModel.createWorkUnitRelation(startIndex, endIndex);
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
            case Role:
                return identificatorCreater.getRoleId(formIndex);
            case Iteration:
                return identificatorCreater.getIterationId(formIndex);
            case Activity:
                return identificatorCreater.getActivityId(formIndex);
            case CommittedConfiguration:
                return identificatorCreater.getCommitedConfigurationId(formIndex);
            case Configuration:
                return identificatorCreater.getConfigurationId(formIndex);
            case Commit:
                return identificatorCreater.getCommitId(formIndex);
            case Artifact:
                return identificatorCreater.getArtifactIndex(formIndex);
            default:
                return -1;
        }
    }


    public int createTableItem(SegmentType segmentType) {
        int id;
        switch (segmentType) {
            case WorkUnit:
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
            case Role:
               return identificatorCreater.createRoleID();
            case RoleType:
               return identificatorCreater.createRoleTypeID();
            case ConfigPersonRelation:
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
        ConfigTable configTable = new ConfigTable(actName, isRelease, formIndex, id);
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
                        case ConfigPersonRelation:
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
                case Role:
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


}

