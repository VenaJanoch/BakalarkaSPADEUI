package Controllers;

import SPADEPAC.*;
import abstractform.BasicForm;
import forms.*;
import graphics.CanvasItem;
import model.DataManipulator;
import services.CanvasType;
import services.Control;
import services.SegmentType;

import java.util.ArrayList;

public class FormController {

    private DataManipulator dataManipulator;

    private ArrayList<BasicForm> forms;

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


    public FormController() {

        milestoneForm = new MilestoneForm(this, deleteControl, idCreater);
        CPRForm = new ConfigPersonRelationForm(this, deleteControl, idCreater);
        roleForm = new RoleForm(this, deleteControl, idCreater);
        priorityForm = new PriorityForm(this, deleteControl, idCreater);
        severityForm = new SeverityForm(this, deleteControl, idCreater);
        relationForm = new RelationForm(this, deleteControl, idCreater);
        resolutionForm = new ResolutionForm(this, deleteControl, idCreater);
        statusForm = new StatusForm(this, deleteControl, idCreater);
        branchFrom = new BranchForm(this, deleteControl, idCreater);
        confTableForm = new ConfigurationTableForm(this, deleteControl, idCreater);
        typeForm = new TypeForm(this, deleteControl, idCreater);

    }


    public void showProjectForm() {

        forms.get(0).show();
        forms.get(0).toFront();
    }

    public void showMilestoneFormAction() {
        control.getMilestoneForm().show();
        control.getMilestoneForm().toFront();
    }

    public void showRoleFormAction() {

        control.getRoleForm().show();
        control.getRoleForm().toFront();
    }

    public void showCPRFormAction() {
        control.getCPRForm().show();
        control.getCPRForm().toFront();
    }

    public void showPriorityFormAction() {
        control.getPriorityForm().show(); control.getPriorityForm().toFront();

    }

    public void showSeverityFormAction() {
        control.getSeverityForm().show();
        control.getSeverityForm().toFront();


    }

    public void showRealtionFormAction() {
        control.getRelationForm().show();
        control.getRelationForm().toFront();
    }

    public void showResolutionFormAction() {
         control.getResolutionForm().show();
         control.getResolutionForm().toFront();
    }

    public void showStatusFormAction() {
        control.getStatusForm().show();
        control.getStatusForm().toFront();
    }

    public void showTypeFormAction() {
         control.getTypeForm().show();
         control.getTypeForm().toFront();
    }

    public void showBranchFormAction() {
        control.getBranchFrom().show();
        control.getBranchFrom().toFront();
    }

    public void showConfTableFromAction() {
        control.getConfTableForm().show();
        control.getConfTableForm().toFront();
    }

    public void deleteForm(int formIdentificator) {

        if (!forms.get(formIdentificator).isNew()) {
            forms.remove(formIdentificator);
        }
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
        SegmentType sType = item.getType();

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
        }
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
        return copyForms.createCopyWorkUnit(canvasItem, rootForm, IDs);
    }

    /**
     * Metoda pro určení metody pro vytvoření konkrétního segmentu nebo elementu
     *
     * @param item
     *            CavasItem
     * @param form
     *            kořenový formulář
     * @return identifikátory objektu pro CanvasItem
     */
    public int createNewForm(SegmentType type) {

            switch (type) {
                case Phase:

                    return fillForms.createPhase(item, form, IDs);

                case Iteration:

                    return fillForms.createIteration(item, form, IDs);

                case Activity:

                    return fillForms.createActivity(item, form, IDs);

                case WorkUnit:
                    return fillForms.createWorkUnit(item, form, IDs);

                case Configuration:

                    return fillForms.createConfigruration(item, form, IDs);
                case Change:
                    return fillForms.createChange(item, form, IDs);

                case Artifact:
                    return fillForms.createArtifact(item, form, IDs);
                case Project:
                    IDs[0] = 0;
                    return IDs;
                default:
                    return IDs;
            }

    }

    int createNewPhaseForm(){

        forms.add(index, new PhaseForm(item, control, Constans.phaseDragTextIndexs, phase, index, deleteControl));
        IDs[0] = index;
        IDs[1] = idCreater.createPhaseID();

    }

    public void showForm(int formIdentificator) {


        forms.get(formIdentificator).show();
        forms.get(formIdentificator).toFront();

    }
}

