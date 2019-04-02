package controlPanels;

import abstractControlPane.ControlPanel;
import abstractControlPane.DescriptionControlPanel;
import controllers.FormController;
import graphics.ControlPanelLine;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import services.ControlPanelLineObject;
import services.ControlPanelLineType;
import services.ParamType;
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
                                IEditFormController editFormController, FormController formController) {
        super(buttonName, formDataController, editFormController, formController);
        addItemsToControlPanel();
        }

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        activityTable = (ActivityTable) basicTable;
        int id = activityTable.getId();
        List activityData[] = formDataController.getActivityStringData(id);

       controlPanelController.resetPanel(controlPane);
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList ,ParamType.Name, activityData, activityData[3], 0);
        controlPanelController.setValueTextField(this, lineList ,ParamType.Description, activityData, activityData[4], 1);

        ArrayList<ArrayList<Integer>> workUnits = formDataController.getWorkUnitFromSegment(id, SegmentType.Activity);
        controlPanelController.setValueCheckComboBox(this, lineList ,ParamType.WorkUnit, workUnits, activityData[2]);

        boolean exist = false;
        List boolList = activityData[5];
        exist = (boolean) boolList.get(0);

        controlPanelController.setValueExistRadioButton(exist);

        button.setOnAction(event -> saveDataFromPanel(activityTable, tableView));
    }

    @Override
    protected void addItemsToControlPanel() {

        controlPanelController.createNewLine(this, lineList);

    }

    public void saveDataFromPanel(BasicTable table, TableView tableView) {
        int id = table.getId();
        ArrayList<Integer> nameIndicators = new ArrayList<>();
        ArrayList<Integer> descIndicators = new ArrayList<>();
        ArrayList<Integer> workUnitIndicators = new ArrayList<>();
        ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
        ArrayList<String> desc = controlPanelController.processTextLines(ParamType.Description, descIndicators);
        ArrayList<ArrayList<Integer>> workUnit = controlPanelController.processCheckComboBoxLines(ParamType.WorkUnit, workUnitIndicators);
        editFormController.editDataFromActivity(name, desc, workUnit, nameIndicators, descIndicators,
                workUnitIndicators, activityTable, controlPanelController.isExist(), id);

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
