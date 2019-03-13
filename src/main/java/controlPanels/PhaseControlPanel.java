package controlPanels;

import abstractControlPane.DateDescControlPanel;
import abstractControlPane.WorkUnitControlPanel;
import abstractControlPane.WorkUnitDateControlPanel;
import controllers.FormController;
import graphics.ComboBoxItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.CheckComboBox;
import services.Constans;
import services.SegmentLists;
import services.SegmentType;
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
        addItemsToControlPanel();

    }

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        phaseTable = (PhaseTable) basicTable;
        int id = phaseTable.getId();
        String[] phaseStringData = formDataController.getPhaseStringData(id);

        nameTF.setTextToTextField(phaseStringData[0]);

        controlPanelController.setValueTextField(descriptionTF, phaseStringData, 1);
        controlPanelController.setValueComboBox(configCB, phaseStringData, 2);
        controlPanelController.setValueComboBox(milestoneCB, phaseStringData, 3);
        controlPanelController.setValueDatePicker(dateDP, phaseStringData, 4);

        List<Integer> workUnits = formDataController.getWorkUnitFromSegment(id, SegmentType.Phase);
        controlPanelController.setValueCheckComboBox(workUnitCB, workUnits);


        button.setOnAction(event -> saveDataFromPanel(phaseTable, tableView));
    }

    public void saveDataFromPanel(BasicTable table, TableView tableView) {
        int id = table.getId();
        phaseTable.setName(id + "_" + nameTF.getTextFromTextField());
        if (configCB.getItemCB() != null) {
            phaseTable.setConfiguration(segmentLists.getConfigObservable().get(configCB.getItemIndex()).getName());
        }

        if (milestoneCB.getItemCB() != null) {
            phaseTable.setMilestone(segmentLists.getMilestoneObservable().get(milestoneCB.getItemIndex()).getName());
        }
        String desc = controlPanelController.checkValueFromTextItem(descriptionTF);

        LocalDate date = controlPanelController.checkValueFromDateItem(dateDP);
        editFormController.editDataFromPhase(nameTF.getTextFromTextField(), desc, date, milestoneCB.getItemIndex(), configCB.getItemIndex()
                , new ArrayList<>(workUnitCB.getChoosedIndicies()), phaseTable, id);

        clearPanelCB(tableView);
    }


    @Override
    protected void addItemsToControlPanel() {

        dateDP.setItemNameLB("End-Date");

        configCB = new ComboBoxItem("Configuration: ", segmentLists.getConfigObservable());
        milestoneCB = new ComboBoxItem("Milestone: ", segmentLists.getMilestoneObservable());

        controlPanelController.setComboBoxItemToControlPanel(controlPane, configCB, 0, 4);
        controlPanelController.setComboBoxItemToControlPanel(controlPane, milestoneCB, 0, 5);

        controlPane.add(button, 2, 6);


    }

    public Button getButton() {
        return button;
    }

    public void clearPanelCB(TableView tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }

}
