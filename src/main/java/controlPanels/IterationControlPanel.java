package controlPanels;

import abstractControlPane.WorkUnitDateControlPanel;
import controllers.FormController;
import graphics.ComboBoxItem;
import graphics.DateItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.*;
import services.SegmentLists;
import services.SegmentType;
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
                                 IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);
        segmentLists = formController.getSegmentLists();
        addItemsToControlPanel();
    }


    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        iterationTable = (IterationTable) basicTable;
        int id = iterationTable.getId();
        String[] iterationData = formDataController.getIterationStringData(id);

        nameTF.setTextToTextField(iterationData[0]);
        controlPanelController.setValueTextField(descriptionTF, iterationData, 1);
        controlPanelController.setValueComboBox(configCB, iterationData, 2);
        controlPanelController.setValueDatePicker(dateDP, iterationData, 3);
        controlPanelController.setValueDatePicker(endDateDP, iterationData, 4);

        List<Integer> workUnits = formDataController.getWorkUnitFromSegment(id, SegmentType.Iteration);
        controlPanelController.setValueCheckComboBox(workUnitCB, workUnits);


        button.setOnAction(event -> saveDataFromPanel(iterationTable, tableView));
    }

    @Override
    protected void addItemsToControlPanel() {

        endDateDP = new DateItem("End-Date");
        configCB = new ComboBoxItem("Configuration: ", segmentLists.getConfigObservable());


        controlPanelController.setDateItemToControlPanel(controlPane, endDateDP, 0, 4);
        controlPanelController.setComboBoxItemToControlPanel(controlPane, configCB, 0, 5);

        controlPane.add(button, 2, 6);

    }

    public void saveDataFromPanel(BasicTable table, TableView tableView){
        int id = table.getId();
        iterationTable.setName(id + "_" + nameTF.getTextFromTextField());
        if (configCB.getItemIndex() != -1){
            iterationTable.setConfiguration(segmentLists.getConfigObservable().get(configCB.getItemIndex()).getName());
        }

        String desc = controlPanelController.checkValueFromTextItem(descriptionTF);

        LocalDate date = controlPanelController.checkValueFromDateItem(dateDP);

        LocalDate date2 = controlPanelController.checkValueFromDateItem(endDateDP);

        editFormController.editDataFromIteration(nameTF.getTextFromTextField(), desc ,date, date2, configCB.getItemIndex(),
                new ArrayList<Integer>(workUnitCB.getChoosedIndicies()),
                iterationTable, id);

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
