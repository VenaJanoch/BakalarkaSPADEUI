package controlPanels;

import abstractControlPane.DateControlPanel;
import abstractControlPane.DateDescControlPanel;
import abstractControlPane.WorkUnitDateControlPanel;
import controllers.FormController;
import graphics.ComboBoxItem;
import graphics.DateItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
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

        nameTF.setText(iterationData[0]);

        descriptionTF.setShowItem(false);
        if (iterationData[1] != null){
            descriptionTF.setShowItem(true);
            descriptionTF.setTextToTextField(iterationData[1]);
        }

        configCB.setShowItem(false);
        if(iterationData[2] != null){
            configCB.setShowItem(true);
            configCB.selectItemInComboBox(Integer.parseInt(iterationData[2]));
        }

        dateDP.setShowItem(false);
        if(iterationData[3] != null){
            dateDP.setShowItem(true);
            dateDP.setDateToPicker(LocalDate.parse(iterationData[3]));
        }

        endDateDP.setShowItem(false);
        if(iterationData[4] != null){
            endDateDP.setShowItem(true);
            endDateDP.setDateToPicker(LocalDate.parse(iterationData[4]));
        }

        List<Integer> workUnits = formDataController.getWorkUnitFromSegment(id, SegmentType.Iteration);
        workUnitCB.setShowItem(false);
        if(workUnits.size() != 0){
                workUnitCB.selectItemsInComboBox(workUnits);
                workUnitCB.setShowItem(true);
        }


        button.setOnAction(event -> saveDataFromPanel(iterationTable, tableView));
    }

    @Override
    protected void addItemsToControlPanel() {

        endDateDP = new DateItem("End-Date");
        configCB = new ComboBoxItem("Configuration: ", segmentLists.getConfigObservable());


        controlPane.add(endDateDP.getItemNameLB(), 1, 4);
        controlPane.setHalignment(endDateDP.getItemNameLB(), HPos.LEFT);
        controlPane.add(endDateDP.getItemDate(), 2, 4);
        controlPane.add(endDateDP.getItemButton(), 0 ,4);

        controlPane.add(configCB.getItemButton(), 0, 5);
        controlPane.add(configCB.getItemNameLB(), 1, 5);
        controlPane.setHalignment(configCB.getItemNameLB(), HPos.LEFT);
        controlPane.add(configCB.getItemCB(), 2, 5);

        controlPane.add(button, 2, 6);

    }

    public void saveDataFromPanel(BasicTable table, TableView tableView){
        int id = table.getId();
        iterationTable.setName(id + "_" + nameTF.getText());
        if (configCB.getItemIndex() != -1){
            iterationTable.setConfiguration(segmentLists.getConfigObservable().get(configCB.getItemIndex()).getName());
        }

        String desc = "null";
        if (descriptionTF.getTextFromTextField() != null){
            desc = descriptionTF.getTextFromTextField();
        }

        LocalDate date = LocalDate.of(1900,1,1);
        if(dateDP.getDateFromDatePicker() != null){
            date = dateDP.getDateFromDatePicker();
        }

        LocalDate date2 = LocalDate.of(1900,1,1);
        if(endDateDP.getDateFromDatePicker() != null){
            date = endDateDP.getDateFromDatePicker();
        }
        editFormController.editDataFromIteration(nameTF.getText(), desc ,date, date2, configCB.getItemIndex(), new ArrayList<Integer>(workUnitCB.getItemIndicies()),
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
