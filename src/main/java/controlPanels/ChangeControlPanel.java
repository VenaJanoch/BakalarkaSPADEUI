package controlPanels;

import abstractControlPane.DescriptionControlPanel;
import controllers.FormController;
import forms.ChangeForm;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import org.controlsfx.control.CheckComboBox;
import services.*;
import tables.ActivityTable;
import tables.BasicTable;
import tables.ChangeTable;

import java.util.ArrayList;
import java.util.List;

public class ChangeControlPanel extends DescriptionControlPanel {

    /**
     * Globální proměnné třídy
     */

    private RadioButton existRB;

    private boolean exist;
    
    private ChangeTable changeTable;


    public ChangeControlPanel(String buttonName, IFormDataController formDataController,
                              IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);
        SegmentLists segmentLists = formController.getSegmentLists();
        lineList.add(new ControlPanelLineObject("Artifact: ", ControlPanelLineType.ComboBox, ParamType.Artifact, segmentLists.getArtifactObservable()));
        addItemsToControlPanel();
    }

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        changeTable = (ChangeTable) basicTable;
        int id = changeTable.getId();
        List[] changeData = formDataController.getChangeStringData(id);

        controlPanelController.resetPanel(controlPane);
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList ,ParamType.Name, changeData, changeData[2], 0);
        controlPanelController.setValueTextField(this, lineList ,ParamType.Description, changeData, changeData[3], 1);
        ArrayList<Integer> artifactIndicators = new ArrayList<>();
        artifactIndicators.add(0);
        controlPanelController.setValueComboBox(this, lineList ,ParamType.Artifact, (ArrayList<Integer>) changeData[5], artifactIndicators);

        List boolList = changeData[4];
        controlPanelController.setValueExistRadioButton((boolean) boolList.get(0));

        button.setOnAction(event -> saveDataFromPanel(changeTable, tableView));
    }

    @Override
    protected void addItemsToControlPanel() {
        controlPanelController.createNewLine(this, lineList);


    }

    public void saveDataFromPanel(BasicTable table, TableView tableView){
        int id = table.getId();

        ArrayList<Integer> nameIndicators = new ArrayList<>();
        ArrayList<Integer> descIndicators = new ArrayList<>();
        ArrayList<Integer> artifactsIndicators = new ArrayList<>();
        ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
        ArrayList<String> desc = controlPanelController.processTextLines(ParamType.Description, descIndicators);
        ArrayList<Integer> aritifacts = controlPanelController.processComboBoxLines(ParamType.Artifact, artifactsIndicators);

        exist = controlPanelController.isExist();

        editFormController.editDataFromChange(name, nameIndicators, desc, aritifacts, descIndicators, exist, changeTable, id);

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
