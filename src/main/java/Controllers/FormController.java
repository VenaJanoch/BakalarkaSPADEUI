package Controllers;

import abstractform.BasicForm;
import forms.ConfigurationForm;
import forms.*;
import graphics.CanvasItem;
import graphics.DragAndDropItemPanel;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import model.DataManipulator;
import model.IdentificatorCreater;
import services.*;
import tables.*;

import java.time.LocalDate;
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
    private SegmentLists lists;
    private FormDataController formDataController;

    private int lastConfigurationIndex = -1;

    public FormController(IdentificatorCreater identificatorCreater, DataManipulator dataManipulator,
                          ApplicationController applicationController, SegmentLists segmentLists, DeleteControl deleteControl) {

        this.lists = segmentLists;

        this.applicationController = applicationController;
        this.forms = new ArrayList<>();
        this.canvasItemList = new HashMap<>();

        this.dataManipulator = dataManipulator;
        this.identificatorCreater = identificatorCreater;

        this.deleteControl = deleteControl;
        }

    public void initBasicForms(FormDataController formDataController){

        this.formDataController = formDataController;

        projectForm = new ProjectForm(this, formDataController, "Project");
        forms.add(Constans.projectFormIndex, projectForm);

        criterionForm = new CriterionForm(this, formDataController, "Criterion");
        forms.add(criterionForm);

        milestoneForm = new MilestoneForm(this, formDataController, "Milestone");
        forms.add(Constans.milestoneFormIndex, milestoneForm);

        roleTypeForm = new RoleTypeForm(this, formDataController, "Role type");
        forms.add(roleTypeForm);

        roleForm = new RoleForm(this, formDataController,"Role");
        forms.add(Constans.roleFormIndex, roleForm);

        CPRForm = new ConfigPersonRelationForm(this, formDataController,"Configuration Person RElation");
        forms.add(Constans.cprFormIndex, CPRForm);

        priorityForm = new PriorityForm(this, formDataController,"Priority");
        forms.add(Constans.priorityFormIndex, priorityForm);

        severityForm = new SeverityForm(this, formDataController,"Severity");
        forms.add(Constans.severityFormIndex, severityForm);

        relationForm = new RelationForm(this, formDataController,"Relation");
        forms.add(Constans.relationFormIndex, relationForm);

        resolutionForm = new ResolutionForm(this, formDataController,"Resolution");
        forms.add(Constans.resolutionormIndex, resolutionForm);

        statusForm = new StatusForm(this, formDataController,"Status");
        forms.add(Constans.statusFormIndex, statusForm);

        typeForm = new TypeForm(this, formDataController,"Type");
        forms.add(Constans.wuTypeFormIndex, typeForm);


        branchFrom = new BranchForm(this, formDataController,"Branch");
        forms.add(Constans.branchIndex, branchFrom);

        forms.add(Constans.configurationFormIndex, null);
        confTableForm = new ConfigurationTableForm(this,formDataController,"Configuration");
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
     * Metoda pro vytvoření nových Work Unit u nových prvků s daty starých
     *
     * @param canvasItem
     *            instance třídy CanvasItem
     * @param rootForm
     *            kořenový formulář
     * @return
     */
    public int[] createCopyWorkUnitForm(CanvasItem canvasItem, BasicForm rootForm) {
        int[] IDs = new int[4];
        return IDs; //copyForms.createCopyWorkUnit(canvasItem, rootForm, IDs);
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

        ArtifactForm artifactForm = new ArtifactForm(this, formDataController,  SegmentType.Artifact.name(), index);
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
        ChangeForm changeForm = new ChangeForm(this, formDataController, SegmentType.Change.name(), index);
        forms.add(index, changeForm);
        return index;
    }


    private int createNewConfigurationForm() {

        dataManipulator.createNewConfiguration();
        int index = createNewConfiguratioFormWithoutManipulator();
        return  index;
    }

    public int createNewConfiguratioFormWithoutManipulator(){
        lastConfigurationIndex = identificatorCreater.createConfigurationID();
        CanvasController canvasController = new CanvasController(CanvasType.Configuration, applicationController);
        ConfigurationForm configurationForm = new ConfigurationForm(this, formDataController,  canvasController,
                new DragAndDropItemPanel(canvasController, Constans.configurationDragTextIndexs), SegmentType.Configuration.name(),
                lists.getCPRObservable(), lists.getBranchObservable(), lists.getRoleObservable(), lastConfigurationIndex);
        forms.add(configurationForm);

        return  lastConfigurationIndex;
    }

    private int createNewWorkUnitForm(CanvasType canvasType) {
        dataManipulator.createNewWorkUnit();
        int index = createNewWorkUnitFormWithoutManipulator(canvasType);
        return index;
    }

    public int createNewWorkUnitFormWithoutManipulator(CanvasType canvasType){
        int index = identificatorCreater.createWorkUnitID();

        CanvasController canvasController = new CanvasController(canvasType, applicationController);
        WorkUnitForm workUnitForm = new WorkUnitForm(this, formDataController, canvasController, SegmentType.WorkUnit.name(), index);
        forms.add(index, workUnitForm);

        workUnitForm.getAsigneeRoleCB().setItems(lists.getRoleObservable());
        workUnitForm.getAuthorRoleCB().setItems(lists.getRoleObservable());
        workUnitForm.getPriorityCB().setItems(lists.getPriorityTypeObservable());
        workUnitForm.getSeverityCB().setItems(lists.getSeverityTypeObservable());
        workUnitForm.getStatusCB().setItems(lists.getStatusTypeObservable());
        workUnitForm.getTypeCB().setItems(lists.getRoleTypeObservable());
        workUnitForm.getResolutionCB().setItems(lists.getResolutionTypeObservable());

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
                Constans.activityDragTextIndexs), SegmentType.Activity.name(), index);
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
                Constans.phaseDragTextIndexs ), SegmentType.Phase.name(), index);
        forms.add(index, phaseForm);

        phaseForm.getConfigCB().setItems(lists.getConfigObservable());
        phaseForm.getMilestoneCB().setItems(lists.getMilestoneObservable());
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
                Constans.iterationDragTextIndexs),SegmentType.Iteration.name(), index);
        forms.add(index, iterationForm);

        iterationForm.getConfigCB().setItems(lists.getConfigObservable());
        return  index;
    }

    public void showForm(int formIdentificator) {
        forms.get(formIdentificator).show();
        forms.get(formIdentificator).toFront();

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

    public ObservableList<String> getCriterionObservable() {
        return lists.getCriterionObservable();
    }

    public ObservableList<String> getRoleObservable() {
        return lists.getRoleObservable();
    }

    public ObservableList<String> getRoleTypeObservable() {
       return lists.getRoleTypeObservable();
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

    public void deleteWorkUnit(int indexForm) {

        if (!forms.get(indexForm).isSave()) {
            forms.remove(indexForm);
            forms.add(indexForm, null);
        }
        dataManipulator.removeWorkUnit(indexForm);
    }


    public void deleteConfiguration(ObservableList<ConfigTable> list) {

        ArrayList<Integer> indexList = deleteControl.deleteConfig(list);
        lists.removeItemFromObservableList(SegmentType.Configuration, indexList);

        for(int i : indexList){

            if (!forms.get(i).isSave()) {
                forms.remove(i);
                forms.add(i, null);
            }
            dataManipulator.removeConfiguration(identificatorCreater.getConfigurationFormIndex(i));
        }



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

    public Node getMainPanelFromForm(int id) {

        return forms.get(id).getMainPanel();

    }

    public int getSegmetIdFromFromId(SegmentType type, int formIndex) {

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

    public void setNewItemToConfigurationTable(String actName, String isRelease, int id) {
        ConfigTable configTable = new ConfigTable(actName, isRelease, id);
        ConfigurationTableForm configurationTableForm = (ConfigurationTableForm) forms.get(Constans.configurationFormIndex);
        configurationTableForm.getTableTV().getItems().add(configTable);
        configurationTableForm.getTableTV().sort();
        configurationTableForm.createConfigItem();
    }

    public void setEditItemInConfigurationTable(String actName, String isRelease, int indexForm, int id) {
        ConfigTable configTable = new ConfigTable(actName, isRelease, indexForm);
        ConfigurationTableForm configurationTableForm = (ConfigurationTableForm) forms.get(Constans.configurationFormIndex);
        configurationTableForm.getTableTV().getItems().remove(id);
        configurationTableForm.getTableTV().getItems().add(id,configTable);
        configurationTableForm.getTableTV().sort();

    }

    public void setConfigurationFormToTableForm(){
        ConfigurationTableForm configurationTableForm = (ConfigurationTableForm) forms.get(Constans.configurationFormIndex);
        configurationTableForm.getMainPanel().setCenter(getMainPanelFromForm(lastConfigurationIndex));
    }


    public Map<Integer, CanvasItem> getCanvasItemList() {
        return canvasItemList;
    }
}

