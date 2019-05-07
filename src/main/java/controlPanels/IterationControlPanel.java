package controlPanels;

import abstractControlPane.WorkUnitDateControlPanel;
import controllers.formControllers.FormController;
import graphics.controlPanelItems.ComboBoxItem;
import graphics.controlPanelItems.DateItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.*;
import services.*;
import tables.BasicTable;
import tables.IterationTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IterationControlPanel extends WorkUnitDateControlPanel {

    /**
     * Globální proměnné třídy
     */

    private ComboBoxItem configCB;
    private DateItem endDateDP;
    private SegmentLists segmentLists;
    private IterationTable iterationTable;

    public IterationControlPanel(String buttonName, IFormDataController formDataController,
                                 IEditFormController editFormController, FormController formController) {
        super(buttonName, formDataController, editFormController, formController);
        segmentLists = formController.getSegmentLists();
        lineList.add(new ControlPanelLineObject("End date: ", ControlPanelLineType.Date, ParamType.EndDate));
        lineList.add(new ControlPanelLineObject("Configuration: ", ControlPanelLineType.ComboBox, ParamType.Configuration, segmentLists.getConfigObservable()));
        addItemsToControlPanel();
    }


    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        iterationTable = (IterationTable) basicTable;
        int id = iterationTable.getId();
        List[] iterationData = formDataController.getIterationStringData(id);

        controlPanelController.resetPanel(controlPane);
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList, ParamType.Name, iterationData, iterationData[5], 0);
        controlPanelController.setValueTextField(this, lineList, ParamType.Description, iterationData, iterationData[6], 1);
        controlPanelController.setValueComboBox(this, lineList, ParamType.Configuration, (ArrayList<Integer>) iterationData[2], iterationData[7]);
        controlPanelController.setValueDatePicker(this, lineList, ParamType.Date, (ArrayList<LocalDate>) iterationData[3], iterationData[8]);
        controlPanelController.setValueDatePicker(this, lineList, ParamType.EndDate, (ArrayList<LocalDate>) iterationData[4], iterationData[9]);

        List boolList = iterationData[11];
        boolean exist = (boolean) boolList.get(0);

        controlPanelController.setValueExistRadioButton(exist);
        controlPanelController.setAlias((String) boolList.get(2), this);
        ArrayList<ArrayList<Integer>> workUnits = formDataController.getWorkUnitFromSegment(id, SegmentType.Iteration);
        controlPanelController.setValueCheckComboBox(this, lineList, ParamType.WorkUnit, workUnits, iterationData[10]);


        button.setOnAction(event -> saveDataFromPanel(iterationTable, tableView));
    }

    @Override
    protected void addItemsToControlPanel() {

        controlPanelController.createNewLineWithExist(this, lineList);
    }

    public void saveDataFromPanel(BasicTable table, TableView tableView) {
        int id = table.getId();
        ArrayList<Integer> nameIndicators = new ArrayList<>();
        ArrayList<Integer> descIndicators = new ArrayList<>();
        ArrayList<Integer> workUnitIndicators = new ArrayList<>();
        ArrayList<Integer> date1Indicators = new ArrayList<>();
        ArrayList<Integer> date2Indicators = new ArrayList<>();
        ArrayList<Integer> configIndicators = new ArrayList<>();

        ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
        ArrayList<String> desc = controlPanelController.processTextLines(ParamType.Description, descIndicators);
        ArrayList<ArrayList<Integer>> workUnit = controlPanelController.processCheckComboBoxLines(ParamType.WorkUnit, workUnitIndicators);
        ArrayList<LocalDate> startDate = controlPanelController.processDateLines(ParamType.Date, date1Indicators);
        ArrayList<LocalDate> endDate = controlPanelController.processDateLines(ParamType.EndDate, date2Indicators);
        ArrayList<Integer> configIndex = controlPanelController.processComboBoxLines(ParamType.Configuration, configIndicators);


        editFormController.editDataFromIteration(aliasTF.getText(), name, endDate, startDate, desc, configIndex,
                workUnit, workUnitIndicators, nameIndicators, date2Indicators, date1Indicators, descIndicators, configIndicators,
                iterationTable, controlPanelController.isExist(), id);

        clearPanelCB(tableView);
    }

    public Button getButton() {
        return button;
    }

    public void clearPanelCB(TableView tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }

    public ComboBox<BasicTable> getConfigCB() {
        return configCB.getItemCB();
    }
}
