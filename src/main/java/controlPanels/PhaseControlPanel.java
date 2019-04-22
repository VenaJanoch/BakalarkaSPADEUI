package controlPanels;

import abstractControlPane.WorkUnitDateControlPanel;
import controllers.formControllers.FormController;
import graphics.controlPanelItems.ComboBoxItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.*;
import services.*;
import tables.BasicTable;
import tables.PhaseTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PhaseControlPanel extends WorkUnitDateControlPanel {

    /**
     * Globální proměnné třídy
     */

    private ComboBoxItem configCB;
    private ComboBoxItem milestoneCB;
    private SegmentLists segmentLists;

    private PhaseTable phaseTable;

    public PhaseControlPanel(String buttonName, IFormDataController formDataController,
                             IEditFormController editFormController, FormController formController) {
        super(buttonName, formDataController, editFormController, formController);
        segmentLists = formController.getSegmentLists();
        lineList.add(new ControlPanelLineObject("Configuration: ", ControlPanelLineType.ComboBox, ParamType.Configuration, segmentLists.getConfigObservable()));
        lineList.add(new ControlPanelLineObject("Milestone: ", ControlPanelLineType.ComboBox, ParamType.Milestone, segmentLists.getMilestoneObservable()));

        addItemsToControlPanel();

    }

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        phaseTable = (PhaseTable) basicTable;
        int id = phaseTable.getId();
        List[] phaseStringData = formDataController.getPhaseStringData(id);

        controlPanelController.resetPanel(controlPane);
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList ,ParamType.Name, phaseStringData, phaseStringData[5], 0);
        controlPanelController.setValueTextField(this, lineList ,ParamType.Description, phaseStringData, phaseStringData[6], 1);
        controlPanelController.setValueComboBox(this, lineList ,ParamType.Configuration, (ArrayList<Integer>) phaseStringData[2], phaseStringData[7]);
        controlPanelController.setValueDatePicker(this, lineList ,ParamType.Date, (ArrayList<LocalDate>)phaseStringData[4],  phaseStringData[9]);
        controlPanelController.setValueComboBox(this, lineList ,ParamType.Milestone, (ArrayList<Integer>) phaseStringData[3], phaseStringData[8]);
        ArrayList<ArrayList<Integer>> workUnits = formDataController.getWorkUnitFromSegment(id, SegmentType.Phase);
        controlPanelController.setValueCheckComboBox(this, lineList ,ParamType.WorkUnit, workUnits, phaseStringData[10]);

        List boolList = phaseStringData[11];
        boolean exist = (boolean) boolList.get(0);

        controlPanelController.setValueExistRadioButton(exist);
        controlPanelController.setAlias((String)boolList.get(2), this);


        button.setOnAction(event -> saveDataFromPanel(phaseTable, tableView));
    }

    public void saveDataFromPanel(BasicTable table, TableView tableView) {
        int id = table.getId();
        ArrayList<Integer> nameIndicators = new ArrayList<>();
        ArrayList<Integer> descIndicators = new ArrayList<>();
        ArrayList<Integer> milestoneIndicators = new ArrayList<>();
        ArrayList<Integer> workUnitIndicators = new ArrayList<>();
        ArrayList<Integer> date1Indicators = new ArrayList<>();
        ArrayList<Integer> configIndicators = new ArrayList<>();

        ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
        ArrayList<String> desc = controlPanelController.processTextLines(ParamType.Description, descIndicators);
        ArrayList<ArrayList<Integer>> workUnit = controlPanelController.processCheckComboBoxLines(ParamType.WorkUnit, workUnitIndicators);
        ArrayList<Integer> milestone = controlPanelController.processComboBoxLines(ParamType.Milestone, milestoneIndicators);
        ArrayList<Integer> config = controlPanelController.processComboBoxLines(ParamType.Configuration, configIndicators);
        ArrayList<LocalDate> startDate = controlPanelController.processDateLines(ParamType.Date, date1Indicators);

        editFormController.editDataFromPhase(aliasTF.getText(), name, startDate, desc, config, milestone, workUnit, workUnitIndicators, nameIndicators, date1Indicators,
                descIndicators, configIndicators, milestoneIndicators, phaseTable, controlPanelController.isExist(), id);

        clearPanelCB(tableView);
    }


    @Override
    protected void addItemsToControlPanel() {

        controlPanelController.createNewLineWithExist(this, lineList);
    }

    public Button getButton() {
        return button;
    }

    public void clearPanelCB(TableView tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }

}
