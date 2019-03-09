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
        String[] milestoneData = formDataController.getActivityStringData(id);

        nameTF.setText(milestoneData[0]);
        if (milestoneData[1] != null){
            descriptionTF.setText(milestoneData[1]);
        }

        List<Integer> workUnits = formDataController.getWorkUnitFromSegment(id, SegmentType.Activity);
        if(workUnits != null){
            for(int i : workUnits){
                workUnitCB.getCheckModel().check(i);
            }
        }


        button.setOnAction(event -> saveDataFromPanel(activityTable, tableView));
    }

    @Override
    protected void addItemsToControlPanel() {

        controlPane.add(button, 2, 3);

    }

    public void saveDataFromPanel(BasicTable table, TableView tableView){
        int id = table.getId();
        activityTable.setName(id + "_" + nameTF.getText());

        String desc = "null";
        if (descriptionTF.getText() != null){
            desc = descriptionTF.getText();
        }


        editFormController.editDataFromActivity(nameTF.getText(), desc , new ArrayList<Integer>(workUnitIndicies), activityTable, id);

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
