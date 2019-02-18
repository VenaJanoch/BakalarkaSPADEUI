package controllers;

import SPADEPAC.ConfigPersonRelation;
import SPADEPAC.Milestone;
import SPADEPAC.Role;
import abstractform.BasicForm;
import forms.ConfigurationForm;
import forms.*;
import graphics.CanvasItem;
import graphics.DragAndDropItemPanel;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import interfaces.ISaveDataModel;
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
    private Map<Integer,CanvasItem> canvasItemList;

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

    public FormController(IdentificatorCreater identificatorCreater, DataModel dataModel,
                          ApplicationController applicationController, SegmentLists segmentLists, DataPreparer dataPreparer) {

        this.segmentLists = segmentLists;
        this.dataPreparer = dataPreparer;
        this.applicationController = applicationController;
        this.forms = new ArrayList<>();
        this.canvasItemList = new HashMap<>();

        this.dataModel = dataModel;
        this.saveDataModel = dataModel.getSaveDataModel();

        this.identificatorCreater = identificatorCreater;

        }

    public void initBasicForms(IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController){

        this.formDataController = formDataController;
        this.editFormController = editFormController;
        this.deleteFormController = deleteFormController;

        projectForm = new ProjectForm(this, formDataController, editFormController, deleteFormController, SegmentType.Project);
        forms.add(Constans.projectFormIndex, projectForm);

        criterionForm = new CriterionForm(this, formDataController, editFormController, deleteFormController, SegmentType.Criterion);
        forms.add(criterionForm);

        milestoneForm = new MilestoneForm(
                this, formDataController, editFormController, deleteFormController,  SegmentType.Milestone);
        forms.add(Constans.milestoneFormIndex, milestoneForm);

        roleTypeForm = new RoleTypeForm(this, formDataController, editFormController, deleteFormController, SegmentType.RoleType);
        forms.add(roleTypeForm);

        roleForm = new RoleForm(this, formDataController, editFormController, deleteFormController, SegmentType.Role);
        forms.add(Constans.roleFormIndex, roleForm);

        CPRForm = new ConfigPersonRelationForm(this, formDataController, editFormController, deleteFormController, SegmentType.ConfigPersonRelation);
        forms.add(Constans.cprFormIndex, CPRForm);

        priorityForm = new PriorityForm(this, formDataController, editFormController, deleteFormController, SegmentType.Priority);
        forms.add(Constans.priorityFormIndex, priorityForm);

        severityForm = new SeverityForm(this, formDataController, editFormController, deleteFormController, SegmentType.Severity);
        forms.add(Constans.severityFormIndex, severityForm);

        relationForm = new RelationForm(this, formDataController, editFormController, deleteFormController, SegmentType.Relation);
        forms.add(Constans.relationFormIndex, relationForm);

        resolutionForm = new ResolutionForm(this, formDataController, editFormController, deleteFormController, SegmentType.Resolution);
        forms.add(Constans.resolutionormIndex, resolutionForm);

        statusForm = new StatusForm(this, formDataController, editFormController, deleteFormController, SegmentType.Status);
        forms.add(Constans.statusFormIndex, statusForm);

        typeForm = new TypeForm(this, formDataController, editFormController, deleteFormController, SegmentType.Type);
        forms.add(Constans.wuTypeFormIndex, typeForm);


        branchFrom = new BranchForm(this, formDataController, editFormController, deleteFormController, SegmentType.Branch);
        forms.add(Constans.branchIndex, branchFrom);

        forms.add(Constans.configurationFormIndex, null);
        confTableForm = new ConfigurationTableForm(this, formDataController, editFormController, deleteFormController, SegmentType.Configuration);
        forms.remove(Constans.configurationFormIndex);
        forms.add(Constans.configurationFormIndex, confTableForm);
    }


    /**
     * Metoda pro určení metody pro vytvoření konkrétního segmentu nebo elementu
     *
     *            kořenový formulář
     * @return identifikátory objektu pro CanvasItem
     */
    public int createNewForm(SegmentType type, CanvasType canvasType) {

            switch (type) {
                case Phase:
                    return createNewPhaseForm();
                case Iteration:
                    return createNewIterationForm();
                case Activity:
                    return  createNewActivityForm();
                case WorkUnit:
                    return createNewWorkUnitForm(canvasType);
                case Configuration:
                    return createNewConfigurationForm();
                case Change:
                    return createNewChangeForm();
                case Artifact:
                    return createNewArtifactForm();
                default:
                    return -1;
            }

    }

    private int createNewArtifactForm() {

        int index = createNewArtifactFormWithoutManipulator();
        saveDataModel.createNewArtifact(identificatorCreater.getArtifactIndex(index));
        return  index;
    }

    public int createNewArtifactFormWithoutManipulator(){

        int index = identificatorCreater.createArtifactID();
        ArtifactForm artifactForm = new ArtifactForm(this, formDataController, editFormController, deleteFormController,  SegmentType.Artifact, index);
        forms.add(index, artifactForm);
        return index;

    }

    private int createNewChangeForm() {

        int index = createNewChangeFormWithoutManipulator();
        saveDataModel.createNewChange(identificatorCreater.getChangeId(index));

        return  index;
    }
    public int createNewChangeFormWithoutManipulator(){

        int index = identificatorCreater.createChangeID();
        ChangeForm changeForm = new ChangeForm(this, formDataController, editFormController, deleteFormController, SegmentType.Change, index);
        forms.add(index, changeForm);
        return index;
    }

    private int createNewConfigurationForm() {

        int index = createNewConfiguratioFormWithoutManipulator();
        saveDataModel.createNewConfiguration(identificatorCreater.getConfigurationId(index));
        return  index;
    }

    public int createNewConfiguratioFormWithoutManipulator(){
        lastConfigurationFormIndex = identificatorCreater.createConfigurationID();
        CanvasController canvasController = new CanvasController(CanvasType.Configuration, applicationController);
        ConfigurationForm configurationForm = new ConfigurationForm(this, formDataController, editFormController, deleteFormController,  canvasController,
                new DragAndDropItemPanel(canvasController, Constans.configurationDragTextIndexs), SegmentType.Configuration,
                segmentLists.getCPRObservable(), segmentLists.getBranchObservable(), segmentLists.getRoleObservable(), lastConfigurationFormIndex);
        forms.add(configurationForm);

        return lastConfigurationFormIndex;
    }

    private int createNewWorkUnitForm(CanvasType canvasType) {
        int index = createNewWorkUnitFormWithoutManipulator(canvasType);
        saveDataModel.createNewWorkUnit(identificatorCreater.getWorkUnitIndex(index));
        return index;
    }

    public int createNewWorkUnitFormWithoutManipulator(CanvasType canvasType){
        int index = identificatorCreater.createWorkUnitID();

        CanvasController canvasController = new CanvasController(canvasType, applicationController);
        WorkUnitForm workUnitForm = new WorkUnitForm(this, formDataController, editFormController, deleteFormController, canvasController, SegmentType.WorkUnit, index);
        forms.add(index, workUnitForm);

        workUnitForm.getAsigneeRoleCB().setItems(segmentLists.getRoleObservable());
        workUnitForm.getAuthorRoleCB().setItems(segmentLists.getRoleObservable());
        workUnitForm.getPriorityCB().setItems(segmentLists.getPriorityTypeObservable());
        workUnitForm.getSeverityCB().setItems(segmentLists.getSeverityTypeObservable());
        workUnitForm.getStatusCB().setItems(segmentLists.getStatusTypeObservable());
        workUnitForm.getTypeCB().setItems(segmentLists.getTypeObservable());
        workUnitForm.getResolutionCB().setItems(segmentLists.getResolutionTypeObservable());

        return  index;
    }

    private int createNewActivityForm() {

        int index = createNewActivityFormWithoutManipulator();
        saveDataModel.createNewActivity(identificatorCreater.getActivityId(index));

        return index;
    }

    public int createNewActivityFormWithoutManipulator(){
        int index = identificatorCreater.createActivityID();
        CanvasController canvasController = new CanvasController(CanvasType.Activity, applicationController);
        ActivityForm activityForm = new ActivityForm(this, formDataController, editFormController, deleteFormController,  canvasController, new DragAndDropItemPanel(canvasController,
                Constans.activityDragTextIndexs), SegmentType.Activity, index);
        forms.add(index, activityForm);

        return  index;
    }

    private int createNewPhaseForm(){
        int index = createNewPhaseFormWithoutManipulator();
        saveDataModel.createNewPhase(identificatorCreater.getPhaseId(index));
        return  index;
    }

    public int createNewPhaseFormWithoutManipulator(){

        int index = identificatorCreater.createPhaseID();
        CanvasController canvasController = new CanvasController(CanvasType.Phase, applicationController);
        PhaseForm phaseForm = new PhaseForm(this, formDataController, editFormController, deleteFormController,  canvasController, new DragAndDropItemPanel(canvasController,
                Constans.phaseDragTextIndexs ), SegmentType.Phase, index);
        forms.add(index, phaseForm);

        phaseForm.getConfigCB().setItems(segmentLists.getConfigObservable());
        phaseForm.getMilestoneCB().setItems(segmentLists.getMilestoneObservable());
        return  index;
    }

    int createNewIterationForm(){

        int index = createNewIterationFormWithoutManipulator();
        saveDataModel.createNewIteration(identificatorCreater.getIterationId(index));

        return index;
    }

    public int createNewIterationFormWithoutManipulator(){
        int index = identificatorCreater.createIterationID();

        CanvasController canvasController = new CanvasController(CanvasType.Iteration, applicationController);

        IterationForm iterationForm = new IterationForm(this, formDataController, editFormController, deleteFormController,  canvasController, new DragAndDropItemPanel(canvasController,
                Constans.iterationDragTextIndexs),SegmentType.Iteration, index);
        forms.add(index, iterationForm);

        iterationForm.getConfigCB().setItems(segmentLists.getConfigObservable());
        return  index;
    }

    public void showForm(int formIdentificator) {
        BasicForm form = forms.get(formIdentificator);
        switch (form.getSegmentType()){
            case Phase:
                formFillController.fillPhaseForm(identificatorCreater.getPhaseId(formIdentificator), formIdentificator);
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

                List<Milestone> milestones = dataModel.getMilestones();
                MilestoneForm milestoneForm = (MilestoneForm) form;
                TableView tableView = milestoneForm.getTableTV();
                tableView.getItems().clear();
                for (int i = 0; i < milestones.size(); i++) {
                    Milestone milestone = milestones.get(i);
                    tableView.getItems().add(dataPreparer.prepareMilestoneTable(milestone.getName(), milestone.getDescription(), milestone.getId(),
                            milestone.getCriteriaIndexs(), segmentLists.getCriterionObservable()));

                }
                tableView.sort();
                tableView.refresh();
                break;
            case Role:

                List<Role> roles = dataModel.getRoles();
                RoleForm roleForm = (RoleForm) form;
                TableView roleTableView = roleForm.getTableTV();
                roleTableView.getItems().clear();
                for (int i = 0; i < roles.size(); i++) {
                    Role role = roles.get(i);
                    RoleTable roleTable = dataPreparer.prepareRoleTable(role.getName(), role.getDescription(), role.getId(),
                            role.getType(), segmentLists.getRoleTypeObservable());
                    roleTableView.getItems().add(roleTable);

                }
                roleTableView.sort();
                roleTableView.refresh();
                break;
            case ConfigPersonRelation:
                List<ConfigPersonRelation> cprlist = dataModel.getCpr();
                ConfigPersonRelationForm cprForm = (ConfigPersonRelationForm) form;
                TableView cprTableView = cprForm.getTableTV();
                cprTableView.getItems().clear();
                for (int i = 0; i < cprlist.size(); i++) {
                    ConfigPersonRelation cpr = cprlist.get(i);
                    CPRTable cprTable = dataPreparer.prepareCPRTable(cpr.getName(), cpr.getPersonIndex(), cpr.getId(), segmentLists.getRoleObservable());
                    cprTableView.getItems().add(cprTable);

                }
                cprTableView.sort();
                cprTableView.refresh();
                break;
            default:

        }


        form.show();
        form.toFront();

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

    public void setNameToItem(int indexForm, String name) {
        CanvasItem item = canvasItemList.get(indexForm);
        item.setNameText(name);
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

        return forms.get(formId).getMainPanel();

    }

    public Node getConfigurationMainPanelFromSegmentId(int id) {

        return getMainPanelFromForm(identificatorCreater.getConfigurationFormIndex(id));

    }

    public int getSegmetIdFromFormId(SegmentType type, int formIndex) {

        switch (type) {
            case Phase:
                return identificatorCreater.getPhaseId(formIndex);
            case Iteration:
                return identificatorCreater.getIterationId(formIndex);
            case Activity:
                return identificatorCreater.getActivityId(formIndex);
            case WorkUnit:
                return identificatorCreater.getWorkUnitIndex(formIndex);
            case Configuration:
                return identificatorCreater.getConfigurationId(formIndex);
            case Change:
                return identificatorCreater.getChangeId(formIndex);
            case Artifact:
                return identificatorCreater.getArtifactIndex(formIndex);
            default:
                return -1;
        }
    }


    public int createTableItem(SegmentType segmentType) {
        switch (segmentType) {
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
               return identificatorCreater.createCPRID();
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

    public void upDateCheckComboBox(CheckComboBox<BasicTable> cb, ArrayList<Integer> positions){
        for (int i : positions){
            cb.getCheckModel().check(i);
        }
    }

    public void updateComboBoxItem(SegmentType formType, SegmentType comboBoxType, ArrayList<Integer> formList){
        switch (formType) {

            case WorkUnit:
                updateWUListItem(comboBoxType, formList);
            case Phase:
                if(formList != null){
                    for (int i : formList){
                        PhaseForm form = (PhaseForm) forms.get(i);
                        form.getMilestoneCB().getSelectionModel().select(0);
                    }
                }

                break;
            default:

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
                        form.getPriorityCB().getSelectionModel().select(0);
                    }
                    break;
                case Severity:
                    for (int i : wuList){
                        WorkUnitForm form = (WorkUnitForm) forms.get(i);
                        form.getSeverityCB().getSelectionModel().select(0);
                    }
                    break;
                case Role:
                    for (int i : wuList){
                        WorkUnitForm form = (WorkUnitForm) forms.get(i);
                        form.getAuthorRoleCB().getSelectionModel().select(0);
                        form.getAsigneeRoleCB().getSelectionModel().select(0);
                    }
                    break;
                case Resolution:
                    for (int i : wuList){
                        WorkUnitForm form = (WorkUnitForm) forms.get(i);
                        form.getResolutionCB().getSelectionModel().select(0);
                    }
                    break;
                case Status:
                    for (int i : wuList){
                        WorkUnitForm form = (WorkUnitForm) forms.get(i);
                        form.getStatusCB().getSelectionModel().select(0);
                    }
                    break;
                case Type:
                    for (int i : wuList){
                        WorkUnitForm form = (WorkUnitForm) forms.get(i);
                        form.getTypeCB().getSelectionModel().select(0);
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

}

