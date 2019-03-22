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
import services.Constans;
import services.ParamType;
import services.SegmentLists;
import services.SegmentType;
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
        addItemsToControlPanel();
    }

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        changeTable = (ChangeTable) basicTable;
        int id = changeTable.getId();
        List[] changeData = formDataController.getChangeStringData(id);

        controlPane.getChildren().clear();
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList ,ParamType.Name, changeData, changeData[2], 0);
        controlPanelController.setValueTextField(this, lineList ,ParamType.Description, changeData, changeData[3], 1);

        boolean exist = false;
        List boolList = changeData[4];
        if (boolList.size() != 0){
            exist = true;
        }
        controlPanelController.setValueRadioButton(exist);

        button.setOnAction(event -> saveDataFromPanel(changeTable, tableView));
    }

    @Override
    protected void addItemsToControlPanel() {
        controlPanelController.setRadioButton(this,1, "Exist: ", false);
        controlPanelController.createNewLine(this, lineList);


    }

    public void saveDataFromPanel(BasicTable table, TableView tableView){
        int id = table.getId();

        ArrayList<Integer> nameIndicators = new ArrayList<>();
        ArrayList<Integer> descIndicators = new ArrayList<>();
        ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
        ArrayList<String> desc = controlPanelController.processTextLines(ParamType.Description, descIndicators);

        exist = controlPanelController.isMain();

        editFormController.editDataFromChange(name, nameIndicators, desc, descIndicators, exist, changeTable, id);

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
