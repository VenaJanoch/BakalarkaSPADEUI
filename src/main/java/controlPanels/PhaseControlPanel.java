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
    public PhaseControlPanel(String buttonName, IFormDataController formDataController,
                             IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);
        segmentLists = formController.getSegmentLists();
        addItemsToControlPanel();

    }

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        PhaseTable phaseTable = (PhaseTable) basicTable;
        int id = phaseTable.getId();
        String[] milestoneData = formDataController.getPhaseStringData(id);

        nameTF.setText(milestoneData[0]);

        descriptionTF.setShowItem(false);
        if (milestoneData[1] != null){
            descriptionTF.setShowItem(true);
            descriptionTF.setTextToTextField(milestoneData[1]);
        }

        configCB.setShowItem(false);
        if(milestoneData[2] != null){
            configCB.setShowItem(true);
            configCB.selectItemInComboBox(Integer.parseInt(milestoneData[2]));
        }

        milestoneCB.setShowItem(false);
        if(milestoneData[3] != null){
            milestoneCB.setShowItem(true);
            milestoneCB.selectItemInComboBox(Integer.parseInt(milestoneData[3]));
        }

        dateDP.setShowItem(false);
        if(milestoneData[4] != null){
            dateDP.setShowItem(true);
            dateDP.setDateToPicker(LocalDate.parse(milestoneData[4]));
        }

        List<Integer> workUnits = formDataController.getWorkUnitFromSegment(id, SegmentType.Phase);
        workUnitCB.setShowItem(false);
        if (workUnits.size() != 0){
            workUnitCB.setShowItem(true);
            workUnitCB.selectItemsInComboBox(workUnits);
        }


        button.setOnAction(event ->{

            phaseTable.setName(id + "_" + nameTF.getText());
            if (configCB.getItemCB() != null){
                phaseTable.setConfiguration(segmentLists.getConfigObservable().get(configCB.getItemIndex()).getName());
            }

            if (milestoneCB.getItemCB() != null){
                phaseTable.setMilestone(segmentLists.getMilestoneObservable().get(milestoneCB.getItemIndex()).getName());
            }
            String desc = "null";
            if (descriptionTF.getTextFromTextField() != null){
                desc = descriptionTF.getTextFromTextField();
            }

            LocalDate date = LocalDate.of(1900,1,1);
            if(dateDP.getDateFromDatePicker() != null){
                date = dateDP.getDateFromDatePicker();
            }
            editFormController.editDataFromPhase(nameTF.getText(), desc ,date, milestoneCB.getItemIndex(), configCB.getItemIndex()
                    , new ArrayList<>(workUnitCB.getItemIndicies()), phaseTable, id);

            clearPanelCB(tableView);
        });
    }
    @Override
    protected void addItemsToControlPanel() {

        dateDP.setItemNameLB("End-Date");

        configCB = new ComboBoxItem("Configuration: ", segmentLists.getConfigObservable());
        milestoneCB = new ComboBoxItem("Milestone: ", segmentLists.getMilestoneObservable());

        controlPane.add(configCB.getItemNameLB(), 1, 4);
        controlPane.setHalignment(configCB.getItemNameLB(), HPos.LEFT);
        controlPane.add(configCB.getItemCB(), 2, 4);
        controlPane.add(configCB.getItemButton(), 0, 4);

        controlPane.add(milestoneCB.getItemNameLB(), 1, 5);
        controlPane.setHalignment(milestoneCB.getItemNameLB(), HPos.LEFT);
        controlPane.add(milestoneCB.getItemCB(), 2, 5);
        controlPane.add(milestoneCB.getItemButton(), 0, 5);
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
