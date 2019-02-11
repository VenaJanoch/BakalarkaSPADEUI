package Controllers;

import SPADEPAC.Milestone;
import SPADEPAC.Role;
import abstractform.BasicForm;
import forms.ConfigurationForm;
import forms.*;
import graphics.CanvasItem;
import graphics.DragAndDropItemPanel;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import model.DataManipulator;
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
    private DeleteControl deleteControl;
    private SegmentLists segmentLists;
    private FormDataController formDataController;

    private int lastConfigurationFormIndex = -1;

    private FormFillController formFillController;
    private DataPreparer dataPreparer;
    public FormController(IdentificatorCreater identificatorCreater, DataManipulator dataManipulator,
                          ApplicationController applicationController, SegmentLists segmentLists, DeleteControl deleteControl, DataPreparer dataPreparer) {

        this.segmentLists = segmentLists;
        this.dataPreparer = dataPreparer;
        this.applicationController = applicationController;
        this.forms = new ArrayList<>();
        this.canvasItemList = new HashMap<>();

        this.dataManipulator = dataManipulator;
        this.identificatorCreater = identificatorCreater;

        this.deleteControl = deleteControl;

        }

    public void initBasicForms(FormDataController formDataController){

        this.formDataController = formDataController;

        projectForm = new ProjectForm(this, formDataController, SegmentType.Project);
        forms.add(Constans.projectFormIndex, projectForm);

        criterionForm = new CriterionForm(this, formDataController, SegmentType.Criterion);
        forms.add(criterionForm);

        milestoneForm = new MilestoneForm(
                this, formDataController,  SegmentType.Milestone);
        forms.add(Constans.milestoneFormIndex, milestoneForm);

        roleTypeForm = new RoleTypeForm(this, formDataController, SegmentType.RoleType);
        forms.add(roleTypeForm);

        roleForm = new RoleForm(this, formDataController,SegmentType.Role);
        forms.add(Constans.roleFormIndex, roleForm);

        CPRForm = new ConfigPersonRelationForm(this, formDataController,SegmentType.ConfigPersonRelation);
        forms.add(Constans.cprFormIndex, CPRForm);

        priorityForm = new PriorityForm(this, formDataController,SegmentType.Priority);
        forms.add(Constans.priorityFormIndex, priorityForm);

        severityForm = new SeverityForm(this, formDataController,SegmentType.Severity);
        forms.add(Constans.severityFormIndex, severityForm);

        relationForm = new RelationForm(this, formDataController,SegmentType.Relation);
        forms.add(Constans.relationFormIndex, relationForm);

        resolutionForm = new ResolutionForm(this, formDataController,SegmentType.Resolution);
        forms.add(Constans.resolutionormIndex, resolutionForm);

        statusForm = new StatusForm(this, formDataController,SegmentType.Status);
        forms.add(Constans.statusFormIndex, statusForm);

        typeForm = new TypeForm(this, formDataController,SegmentType.Type);
        forms.add(Constans.wuTypeFormIndex, typeForm);


        branchFrom = new BranchForm(this, formDataController,SegmentType.Branch);
        forms.add(Constans.branchIndex, branchFrom);

        forms.add(Constans.configurationFormIndex, null);
        confTableForm = new ConfigurationTableForm(this,formDataController,SegmentType.Configuration);
        forms.remove(Constans.configurationFormIndex);
        forms.add(Constans.configurationFormIndex, confTableForm);
    }

    public void deleteIterationForm(int formIdentificator) {

        if (!forms.get(formIdentificator).isSave()) {
            forms.remove(formIdentificator);
            forms.add(formIdentificator, null);
        }

        dataManipulator.removeIteration(formIdentificator);
    }

    public void deletePhaseForm(int formIdentificator) {

        if (!forms.get(formIdentificator).isSave()) {
            forms.remove(formIdentificator);
            forms.add(formIdentificator, null);
        }
        dataManipulator.removePhase(formIdentificator);
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
        dataManipulator.createNewArtifact();
        int index = createNewArtifactFormWithoutManipulator();
        return  index;
    }

    public int createNewArtifactFormWithoutManipulator(){
        int index = identificatorCreater.createArtifactID();

        ArtifactForm artifactForm = new ArtifactForm(this, formDataController,  SegmentType.Artifact, index);
        forms.add(index, artifactForm);
        return index;

    }

    private int createNewChangeForm() {
        dataManipulator.createNewChance();
        int index = createNewChangeFormWithoutManipulator();
        return  index;
    }
    public int createNewChangeFormWithoutManipulator(){

        int index = identificatorCreater.createChangeID();
        ChangeForm changeForm = new ChangeForm(this, formDataController, SegmentType.Change, index);
        forms.add(index, changeForm);
        return index;
    }

    private int createNewConfigurationForm() {

        dataManipulator.createNewConfiguration();
        int index = createNewConfiguratioFormWithoutManipulator();
        return  index;
    }

    public int createNewConfiguratioFormWithoutManipulator(){
        lastConfigurationFormIndex = identificatorCreater.createConfigurationID();
        CanvasController canvasController = new CanvasController(CanvasType.Configuration, applicationController);
        ConfigurationForm configurationForm = new ConfigurationForm(this, formDataController,  canvasController,
                new DragAndDropItemPanel(canvasController, Constans.configurationDragTextIndexs), SegmentType.Configuration,
                segmentLists.getCPRObservable(), segmentLists.getBranchObservable(), segmentLists.getRoleObservable(), lastConfigurationFormIndex);
        forms.add(configurationForm);

        return lastConfigurationFormIndex;
    }

    private int createNewWorkUnitForm(CanvasType canvasType) {
        dataManipulator.createNewWorkUnit();
        int index = createNewWorkUnitFormWithoutManipulator(canvasType);
        return index;
    }

    public int createNewWorkUnitFormWithoutManipulator(CanvasType canvasType){
        int index = identificatorCreater.createWorkUnitID();

        CanvasController canvasController = new CanvasController(canvasType, applicationController);
        WorkUnitForm workUnitForm = new WorkUnitForm(this, formDataController, canvasController, SegmentType.WorkUnit, index);
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

        dataManipulator.createNewActivity();
        int index = createNewActivityFormWithoutManipulator();
        return index;
    }

    public int createNewActivityFormWithoutManipulator(){
        int index = identificatorCreater.createActivityID();
        CanvasController canvasController = new CanvasController(CanvasType.Activity, applicationController);
        ActivityForm activityForm = new ActivityForm(this, formDataController,  canvasController, new DragAndDropItemPanel(canvasController,
                Constans.activityDragTextIndexs), SegmentType.Activity, index);
        forms.add(index, activityForm);

        return  index;
    }

    private int createNewPhaseForm(){
        dataManipulator.createNewPhase();
        int index = createNewPhaseFormWithoutManipulator();
        return  index;
    }

    public int createNewPhaseFormWithoutManipulator(){

        int index = identificatorCreater.createPhaseID();
        CanvasController canvasController = new CanvasController(CanvasType.Phase, applicationController);
        PhaseForm phaseForm = new PhaseForm(this, formDataController,  canvasController, new DragAndDropItemPanel(canvasController,
                Constans.phaseDragTextIndexs ), SegmentType.Phase, index);
        forms.add(index, phaseForm);

        phaseForm.getConfigCB().setItems(segmentLists.getConfigObservable());
        phaseForm.getMilestoneCB().setItems(segmentLists.getMilestoneObservable());
        return  index;
    }

    int createNewIterationForm(){

        dataManipulator.createNewIteration();
        int index = createNewIterationFormWithoutManipulator();
        return index;
    }

    public int createNewIterationFormWithoutManipulator(){
        int index = identificatorCreater.createIterationID();

        CanvasController canvasController = new CanvasController(CanvasType.Iteration, applicationController);

        IterationForm iterationForm = new IterationForm(this, formDataController,  canvasController, new DragAndDropItemPanel(canvasController,
                Constans.iterationDragTextIndexs),SegmentType.Iteration, index);
        forms.add(index, iterationForm);

        iterationForm.getConfigCB().setItems(segmentLists.getConfigObservable());
        return  index;
    }

    public void showForm(int formIdentificator) {
        BasicForm form = forms.get(formIdentificator);
        switch (form.getSegmentType()){
            case Phase:
                formFillController.fillPhaseForm(identificatorCreater.getPhaseIndex(formIdentificator), formIdentificator);
                break;
            case Iteration:
                formFillController.fillIterationForm(identificatorCreater.getIterationIndex(formIdentificator), formIdentificator);
                break;
            case Activity:
                formFillController.fillActivityForm(identificatorCreater.getActivityIndex(formIdentificator), formIdentificator);
                break;
            case WorkUnit:
                formFillController.fillWorkUnitForm(identificatorCreater.getWorkUnitIndex(formIdentificator), formIdentificator);
                break;
            case Change:
                //  formFillController.fillChangeForm(identificatorCreater.getIterationIndex(formIdentificator), formIdentificator);break;
            case Artifact:
                //  formFillController.fillArtifactForm(identificatorCreater.getIterationIndex(formIdentificator), formIdentificator);
                break;
            case Milestone:

                List<Milestone> milestones = dataManipulator.getProject().getMilestones();
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

                List<Role> roles = dataManipulator.getProject().getRoles();
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

        Integer changeStartIndex = identificatorCreater.getChangeIndex(startId);
        Integer changeEndIndex = identificatorCreater.getChangeIndex(endId);

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

        dataManipulator.createChangeArtifactRelation(artifactIndex,changeIndex);
    }

    public void createWorkUnitRelation(int startSegmentId, int endSegmentId) {

        Integer startIndex = identificatorCreater.getWorkUnitIndex(startSegmentId);
        Integer endIndex = identificatorCreater.getWorkUnitIndex(endSegmentId);

        dataManipulator.createWorkUnitRelation(startIndex, endIndex);
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


    public void deleteActivityForm(int indexForm) {

        if (!forms.get(indexForm).isSave()) {
            forms.remove(indexForm);
            forms.add(indexForm, null);
        }
        dataManipulator.removeActivity(indexForm);
    }

    public void setItemColor(int indexForm, boolean isExist) {

        CanvasItem item = canvasItemList.get(indexForm);
        if(!isExist){
            item.getSegmentInfo().setRectangleColor(Constans.nonExistRectangleBorderColor);
        } else {
            item.getSegmentInfo().setRectangleColor(Constans.rectangleBorderColor);
        }

    }

    public void deleteWorkUnit(ArrayList<Integer> indicesForm) {

        for(int i : indicesForm){
            deleteWorkUnit(identificatorCreater.getWorkUnitIndex(i));
        }
    }

    public void deleteWorkUnit(int indexForm) {

        if (!forms.get(indexForm).isSave()) {
            forms.remove(indexForm);
            forms.add(indexForm, null);
        }
        dataManipulator.removeWorkUnit(identificatorCreater.getWorkUnitIndexMaper().get(indexForm));
    }


    public void deleteChange(int indexForm) {
        if (!forms.get(indexForm).isSave()) {
            forms.remove(indexForm);
            forms.add(indexForm, null);
        }
        dataManipulator.removeChange(indexForm);

    }

    public void deleteArtifact(int indexForm) {
        if (!forms.get(indexForm).isSave()) {
            forms.remove(indexForm);
            forms.add(indexForm, null);
        }

        dataManipulator.removeArtifact(indexForm);

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
                return identificatorCreater.getPhaseIndex(formIndex);
            case Iteration:
                return identificatorCreater.getIterationIndex(formIndex);
            case Activity:
                return identificatorCreater.getActivityIndex(formIndex);
            case WorkUnit:
                return identificatorCreater.getWorkUnitIndex(formIndex);
            case Configuration:
                return identificatorCreater.getConfigurationIndex(formIndex);
            case Change:
                return identificatorCreater.getChangeIndex(formIndex);
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


    public Map<Integer, CanvasItem> getCanvasItemList() {
        return canvasItemList;
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
}

