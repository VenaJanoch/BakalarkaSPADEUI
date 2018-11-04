package Controllers;

import SPADEPAC.*;
import abstractform.BasicForm;
import forms.*;
import graphics.CanvasItem;
import graphics.DragAndDropItemPanel;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import model.DataManipulator;
import model.IdentificatorCreater;
import services.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class FormController {

    private DataManipulator dataManipulator;
    private IdentificatorCreater identificatorCreater;

    private ArrayList<BasicForm> forms;
    private ArrayList<CanvasItem> canvasItemList;

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
        this.canvasItemList = new ArrayList<>();

        this.dataManipulator = dataManipulator;
        this.identificatorCreater = identificatorCreater;

        this.formControl = new FormControl();

        projectForm = new ProjectForm(this, "Project");
        forms.add(Constans.projectFormIndex, projectForm);

        milestoneForm = new MilestoneForm(this, "Milestone");
        forms.add(Constans.milestoneFormIndex, milestoneForm);

        CPRForm = new ConfigPersonRelationForm(this, "Configuration Person RElation");
        forms.add(Constans.cprFormIndex, CPRForm);

        roleForm = new RoleForm(this, "Role");
        forms.add(Constans.roleFormIndex, roleForm);

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

        branchFrom = new BranchForm(this, "Branch");
        forms.add(Constans.branchIndex, branchFrom);

        confTableForm = new ConfigurationTableForm(this,"Configuration");
        forms.add(Constans.configurationFormIndex, confTableForm);

        typeForm = new TypeForm(this, "Type");
        forms.add(Constans.wuTypeFormIndex, typeForm);


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
    public int createNewForm(SegmentType type) {

            switch (type) {
                case Phase:
                    return createNewPhaseForm();
                case Iteration:
                            createNewIterationForm();
                    return fillForms.createIteration(item, form, IDs);

                case Activity:
                    createNewActivityForm();
                    return fillForms.createActivity(item, form, IDs);

                case WorkUnit:
                    createNewWorkUnitForm();
                    return fillForms.createWorkUnit(item, form, IDs);

                case Configuration:
                    createNewConfigurationForm();
                    return fillForms.createConfigruration(item, form, IDs);
                case Change:
                    createNewChangeForm();
                    return fillForms.createChange(item, form, IDs);

                case Artifact:
                    createNewArtifactForm();
                    return fillForms.createArtifact(item, form, IDs);
                default:
                    return -1;
            }

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

        IterationForm iterationForm = new IterationForm(this, SegmentType.Iteration.name(),canvasController, new DragAndDropItemPanel(canvasController,
                Constans.iterationDragTextIndexs) index);
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

    public boolean saveDataFromPhaseForm(String actName, LocalDate endDateL, String desc,int confIndex, int milestoneIndex, ArrayList itemIndexList, int indexForm) {
        String nameForManipulator = formControl.fillTextMapper(actName);
        String descriptionForManipulator = formControl.fillTextMapper(desc);

        int[] coords = getCoordsFromItem(indexForm);

        dataManipulator.addDataToPhase(nameForManipulator, endDateL, descriptionForManipulator, prepareIndexForManipulator(confIndex),
                prepareIndexForManipulator(milestoneIndex), coords[0], coords[1], itemIndexList, indexForm);
        return true;
    }

    public boolean saveDataFromIterationForm(String actName, LocalDate startDate, LocalDate endDate, String desc, int chooseConfigID, ArrayList itemIndexList, int indexForm) {
        String nameForManipulator = formControl.fillTextMapper(actName);
        String descriptionForManipulator = formControl.fillTextMapper(desc);

        int[] coords = getCoordsFromItem(indexForm);

        dataManipulator.addDataToIteration(nameForManipulator,startDate, endDate, descriptionForManipulator, prepareIndexForManipulator(chooseConfigID),
                coords[0], coords[1], itemIndexList, indexForm);
        return true;
    }


    public void removeCanvasItemFromList(int id) {
        canvasItemList.remove(id);
        canvasItemList.add(id,null);
    }

    public void addCanvasItemToList(int formIndex, CanvasItem item) {
        canvasItemList.add(formIndex,item);
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

}

