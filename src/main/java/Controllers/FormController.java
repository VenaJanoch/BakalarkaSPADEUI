package Controllers;

import SPADEPAC.Artifact;
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

    private FormControl formControl;
    private ApplicationController applicationController;

    public FormController(IdentificatorCreater identificatorCreater, DataManipulator dataManipulator, ApplicationController applicationController) {

        this.applicationController = applicationController;
        this.forms = new ArrayList<>();
        this.canvasItemList = new HashMap<>();

        this.dataManipulator = dataManipulator;
        this.identificatorCreater = identificatorCreater;

        this.formControl = new FormControl();

        projectForm = new ProjectForm(this, "Project");
        forms.add(Constans.projectFormIndex, projectForm);

        milestoneForm = new MilestoneForm(this, "Milestone");
        forms.add(Constans.milestoneFormIndex, milestoneForm);

        roleForm = new RoleForm(this, "Role");
        forms.add(Constans.roleFormIndex, roleForm);

        CPRForm = new ConfigPersonRelationForm(this, "Configuration Person RElation");
        forms.add(Constans.cprFormIndex, CPRForm);

        priorityForm = new PriorityForm(this, "Priority");
        forms.add(Constans.priorityFormIndex, priorityForm);

        severityForm = new SeverityForm(this, "Severity");
        forms.add(Constans.severityFormIndex, severityForm);

        relationForm = new RelationForm(this, "Relation");
        forms.add(Constans.relationFormIndex, relationForm);

        resolutionForm = new ResolutionForm(this, "Resolution");
        forms.add(Constans.resolutionormIndex, resolutionForm);

        statusForm = new StatusForm(this, "Status");
        forms.add(Constans.statusFormIndex, statusForm);

        typeForm = new TypeForm(this, "Type");
        forms.add(Constans.wuTypeFormIndex, typeForm);


        branchFrom = new BranchForm(this, "Branch");
        forms.add(Constans.branchIndex, branchFrom);

        forms.add(Constans.configurationFormIndex, null);
        confTableForm = new ConfigurationTableForm(this,"Configuration");
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

    public  void createCopyForm(int oldFormIndex, SegmentType segmentType){
        //Todo CreateCopyForm
    }

    /**
     * Rozhodne, který segment nebo element se vytvoří
     *
     * @param item
     *            instance třídy CanvasItem
     * @param form
     *            instance seznamu formulářů
     * @return pole identifikátorů prvku
     */
    public int[] createCopyForm(CanvasItem item, BasicForm form) {
       /* SegmentType sType = item.getType();

        int[] IDs = new int[4];

        switch (sType) {
            case Phase:
                Phase phase = project.getPhases().get(itemIds[1]);
                return copyForms.createPhase(item, form, phase, IDs, itemIds);

            case Iteration:

                Iteration iteration = project.getIterations().get(itemIds[1]);
                return copyForms.createIteration(item, form, iteration, IDs, itemIds);
            case Activity:

                Activity activity = project.getActivities().get(itemIds[1]);
                return copyForms.createActivity(item, form, activity, IDs, itemIds);

            case WorkUnit:

                WorkUnit unit = lists.getWorkUnitList().get(itemIds[1]);
                return copyForms.createWorkUnit(item, form, unit, IDs, itemIds);

            case Change:
                Change change = lists.getChangeList().get(itemIds[1]);
                return copyForms.createChange(item, form, change, IDs, itemIds);

            case Artifact:
                Artifact artifact = lists.getArtifactList().get(itemIds[1]);
                return copyForms.createArtifact(item, form, artifact, IDs, itemIds);

            default:
                return IDs;
        }*/
       return  null; //Todo dodelat
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
       int index = identificatorCreater.createArtifactID();
       dataManipulator.createNewArtifact(index);
       ArtifactForm artifactForm = new ArtifactForm(this, SegmentType.Artifact.name(), index);
       forms.add(index, artifactForm);
       return index;
    }

    private int createNewChangeForm() {
        int index = identificatorCreater.createChangeID();
        dataManipulator.createNewChance(index);
        ChangeForm changeForm = new ChangeForm(this, SegmentType.Change.name(), index);
        forms.add(index, changeForm);
        return index;
    }

    private int createNewConfigurationForm() {
        int index = identificatorCreater.createConfigurationID();
        dataManipulator.createNewConfiguration(index);
        CanvasController canvasController = new CanvasController(CanvasType.Configuration, applicationController);
        ConfigurationForm configurationForm = new ConfigurationForm(this, canvasController,
                new DragAndDropItemPanel(Constans.configurationDragTextIndexs), SegmentType.Configuration.name(), index);
        forms.add(configurationForm);

        return  index;

    }

    private int createNewWorkUnitForm(CanvasType canvasType) {

        int index = identificatorCreater.createWorkUnitID();
        dataManipulator.createNewWorkUnit(index);
        WorkUnitForm workUnitForm = new WorkUnitForm(this, SegmentType.WorkUnit.name());
        forms.add(index, workUnitForm);

        workUnitForm.getAsigneeRoleCB().setItems(dataManipulator.getRoleObservable());
        workUnitForm.getAuthorRoleCB().setItems(dataManipulator.getRoleObservable());
        workUnitForm.getPriorityCB().setItems(dataManipulator.getPriorityObservable());
        workUnitForm.getSeverityCB().setItems(dataManipulator.getSeverityObservable());
        workUnitForm.getStatusCB().setItems(dataManipulator.getStatusObservable());
        workUnitForm.getTypeCB().setItems(dataManipulator.getRoleTypeObservable());
        workUnitForm.getResolutionCB().setItems(dataManipulator.getResolutionObservable());

        return  index;
    }

    private int createNewActivityForm() {
        int index = identificatorCreater.createActivityID();
        dataManipulator.createNewActivity(index);
        CanvasController canvasController = new CanvasController(CanvasType.Activity, applicationController);
        ActivityForm activityForm = new ActivityForm(this, canvasController, new DragAndDropItemPanel(canvasController,
                Constans.activityDragTextIndexs), SegmentType.Activity.name(), index);
        forms.add(index, activityForm);

        return  index;
    }

    int createNewPhaseForm(){

        int index = identificatorCreater.createPhaseID();
        dataManipulator.createNewPhase(index);
        CanvasController canvasController = new CanvasController(CanvasType.Phase, applicationController);
        PhaseForm phaseForm = new PhaseForm(this, canvasController, new DragAndDropItemPanel(canvasController,
                Constans.phaseDragTextIndexs ), SegmentType.Phase.name(), index);
        forms.add(index, phaseForm);

        phaseForm.getConfigCB().setItems(dataManipulator.getConfigurationObservable());
        phaseForm.getMilestoneCB().setItems(dataManipulator.getMilestoneObservable());
        return  index;
    }

    int createNewIterationForm(){

        int index = identificatorCreater.createIterationID();
        dataManipulator.createNewIteration(index);
        CanvasController canvasController = new CanvasController(CanvasType.Iteration, applicationController);

        IterationForm iterationForm = new IterationForm(this, canvasController, new DragAndDropItemPanel(canvasController,
                Constans.iterationDragTextIndexs),SegmentType.Iteration.name(), index);
        forms.add(index, iterationForm);

        iterationForm.getConfigCB().setItems(dataManipulator.getConfigurationObservable());
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

    public int prepareIndexForManipulator(int index){
        if(index != 0){
            return  index -1;
        }
        return index;
    }

    private List<Integer> prepareIndexsForManipulator(List<Integer> indexis) {
        List<Integer> tmpIndexis = new ArrayList<>();
        for (int index : indexis) {
            tmpIndexis.add(prepareIndexForManipulator(index));
        }
        return tmpIndexis;
    }

    public boolean saveDataFromPhaseForm(String actName, LocalDate endDateL, String desc,int confIndex, int milestoneIndex, Map<Integer, CanvasItem> itemIndexList,
                                         int indexForm) {
        String nameForManipulator = formControl.fillTextMapper(actName);
        String descriptionForManipulator = formControl.fillTextMapper(desc);

        int[] coords = getCoordsFromItem(indexForm);

        dataManipulator.addDataToPhase(nameForManipulator, endDateL, descriptionForManipulator, prepareIndexForManipulator(confIndex),
                prepareIndexForManipulator(milestoneIndex), coords[0], coords[1], itemIndexList.keySet(), indexForm);
        return true;
    }

    public boolean saveDataFromIterationForm(String actName, LocalDate startDate, LocalDate endDate, String desc, int chooseConfigID, Map<Integer,CanvasItem> itemIndexList, int indexForm) {
        String nameForManipulator = formControl.fillTextMapper(actName);
        String descriptionForManipulator = formControl.fillTextMapper(desc);

        int[] coords = getCoordsFromItem(indexForm);

        dataManipulator.addDataToIteration(nameForManipulator,startDate, endDate, descriptionForManipulator, prepareIndexForManipulator(chooseConfigID),
                coords[0], coords[1], itemIndexList.keySet(), indexForm);
        return true;
    }

    public boolean saveDataFromActivityForm(String actName, String desc, Map<Integer, CanvasItem> mapOfItemOnCanvas, int indexForm) {

        String nameForManipulator = formControl.fillTextMapper(actName);
        String descriptionForManipulator = formControl.fillTextMapper(desc);
        int[] coords = getCoordsFromItem(indexForm);

        dataManipulator.addDataToActivity(nameForManipulator, descriptionForManipulator, coords[0], coords[1], mapOfItemOnCanvas.keySet(), indexForm);
        return true;

    }

    public void removeCanvasItemFromList(int id) {
        canvasItemList.put(id,null);
    }

    public void addCanvasItemToList(int formIndex, CanvasItem item) {
        canvasItemList.put(formIndex,item);
    }


    public void createChangeArtifactRelation(int startId, int endId) {

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
        return dataManipulator.getCriterionObservable();
    }

    public ObservableList<String> getRoleObservable() {
        return dataManipulator.getRoleObservable();
    }

    public ObservableList<String> getRoleTypeObservable() {
       return dataManipulator.getRoleTypeObservable();
    }


    public void deleteActivityForm(int indexForm) {

        if (!forms.get(indexForm).isSave()) {
            forms.remove(indexForm);
            forms.add(indexForm, null);
        }
        dataManipulator.removeActivity(indexForm);
    }

    public boolean saveDataFromWorkUnit(String actName,String description, String category, int assigneIndex, int authorIndex, int priorityIndex,int severityIndex,
                                        int typeIndex, int resolutionIndex, int statusIndex, String estimated, boolean selected, int indexForm) {

        String nameForManipulator = formControl.fillTextMapper(actName);
        String categoryForManipulator = formControl.fillTextMapper(category);

        int[] coords = getCoordsFromItem(indexForm);

        double estimateForDataManipulator = -1.0;

        try {
            if (!estimated.equals("")) {
                estimateForDataManipulator = Double.parseDouble(estimated);
            }

        } catch (NumberFormatException e) {
            Alerts.showWrongEstimatedTimeAlert();
            return  false;
        }

        setItemColor(indexForm, selected);

        dataManipulator.addDataToWorkUnit(nameForManipulator, description ,categoryForManipulator, prepareIndexForManipulator(assigneIndex),
                prepareIndexForManipulator(authorIndex), prepareIndexForManipulator(priorityIndex), prepareIndexForManipulator(severityIndex),
                prepareIndexForManipulator(typeIndex), prepareIndexForManipulator(resolutionIndex), prepareIndexForManipulator(statusIndex),
                coords[0], coords[1], estimateForDataManipulator, selected, indexForm);

        return  true;
    }

    private void setItemColor(int indexForm, boolean isExist) {

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


    public void deleteConfiguration(int indexForm) {

        if (!forms.get(indexForm).isSave()) {
            forms.remove(indexForm);
            forms.add(indexForm, null);
        }
        dataManipulator.removeConfiguration(indexForm);

    }

    public void saveDataFromConfiguration(String actName, LocalDate createDate, boolean isRelease, int authorIndex, ArrayList<Integer> branchIndex,
                                          ArrayList<Integer> cprIndex, Map<Integer,CanvasItem> itemIndexList, int indexForm) {
        String nameForManipulator = formControl.fillTextMapper(actName);
        int[] coords = getCoordsFromItem(indexForm);
        ArrayList artefactList = new ArrayList();
        ArrayList changeList = new ArrayList();

        for(int index : itemIndexList.keySet()) {
            if(identificatorCreater.getChangeIndexMaper().get(index) != null){
                changeList.add(index);
            }else {
                artefactList.add(index);
            }
        }

        dataManipulator.addDataToConfiguration(nameForManipulator, createDate, isRelease, coords[0], coords[1],
                prepareIndexForManipulator(authorIndex), prepareIndexsForManipulator(branchIndex),
                prepareIndexsForManipulator(cprIndex), artefactList, changeList, indexForm);


    }


    public boolean saveDataFromChange(String actName, String desc, boolean selected, int indexForm) {

        String nameForManipulator = formControl.fillTextMapper(actName);
        String descForManipulator = formControl.fillTextMapper(desc);

        int[] coords = getCoordsFromItem(indexForm);

        setItemColor(indexForm, selected);
        dataManipulator.addDataToChange(nameForManipulator, descForManipulator, coords[0], coords[1], selected, indexForm);
        return true;
    }

    public void deleteChange(int indexForm) {
        if (!forms.get(indexForm).isSave()) {
            forms.remove(indexForm);
            forms.add(indexForm, null);
        }
        dataManipulator.removeChange(indexForm);

    }

    public boolean saveDataFromArtifact(String actName, LocalDate createdDate, String type, String desc, int authorIndex,
                                        int typeIndex, boolean selected, int indexForm) {

        String nameForManipulator = formControl.fillTextMapper(actName);
        String descForManipulator = formControl.fillTextMapper(desc);

        int[] coords = getCoordsFromItem(indexForm);
        setItemColor(indexForm, selected);

        dataManipulator.addDataToArtifact(nameForManipulator, descForManipulator, createdDate, selected, coords[0], coords[1],
                prepareIndexForManipulator(authorIndex), typeIndex, indexForm);

        return  true;
    }

    public void deleteArtifact(int indexForm) {
        if (!forms.get(indexForm).isSave()) {
            forms.remove(indexForm);
            forms.add(indexForm, null);
        }

        dataManipulator.removeArtifact(indexForm);

    }

    public ConfigurationForm getConfigurationForm(int id) {

        return (ConfigurationForm) forms.get(id);
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
}

