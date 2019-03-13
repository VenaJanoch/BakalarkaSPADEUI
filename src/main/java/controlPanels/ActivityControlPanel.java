package controlPanels;

import controllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import services.SegmentType;
import tables.ActivityTable;
import tables.BasicTable;
import abstractControlPane.WorkUnitControlPanel;

import java.util.ArrayList;
import java.util.List;

public class ActivityControlPanel extends WorkUnitControlPanel {

    /**
     * Globální proměnné třídy
     */


    private ActivityTable activityTable;
    
    public ActivityControlPanel(String buttonName, IFormDataController formDataController,
                                IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);
        addItemsToControlPanel();
    }

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        activityTable = (ActivityTable) basicTable;
        int id = activityTable.getId();
        String[] activityData = formDataController.getActivityStringData(id);

        nameTF.setTextToTextField(activityData[0]);

        controlPanelController.setValueTextField(descriptionTF, activityData, 1);

        List<Integer> workUnits = formDataController.getWorkUnitFromSegment(id, SegmentType.Activity);
        controlPanelController.setValueCheckComboBox(workUnitCB, workUnits);


        button.setOnAction(event -> saveDataFromPanel(activityTable, tableView));
    }

    @Override
    protected void addItemsToControlPanel() {

        controlPane.add(button, 2, 3);


    }

    public void saveDataFromPanel(BasicTable table, TableView tableView){
        int id = table.getId();
        String desc = controlPanelController.checkValueFromTextItem(descriptionTF);
        editFormController.editDataFromActivity(nameTF.getTextFromTextField(), desc , new ArrayList<Integer>(workUnitCB.getChoosedIndicies()), activityTable, id);

        clearPanelCB(tableView);
    }


    public Button getButton() {
        return button;
    }

    public void clearPanelCB(TableView tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }

}
