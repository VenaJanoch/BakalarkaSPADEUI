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
        String[] changeData = formDataController.getChangeStringData(id);

        nameTF.setTextToTextField(changeData[0]);
        controlPanelController.setValueTextField(descriptionTF, changeData, 1);

        exist = Boolean.valueOf(changeData[2]);

        existRB.setSelected(exist);

        button.setOnAction(event -> saveDataFromPanel(changeTable, tableView));
    }

    @Override
    protected void addItemsToControlPanel() {
        existRB = new RadioButton("Exist");
        existRB.setSelected(true);

        controlPane.add(existRB, 0, 3);
        controlPane.add(button, 2, 4);

    }

    public void saveDataFromPanel(BasicTable table, TableView tableView){
        int id = table.getId();
        changeTable.setName(id + "_" + nameTF.getTextFromTextField());

        String desc = controlPanelController.checkValueFromTextItem(descriptionTF);

        exist = existRB.isSelected();

        editFormController.editDataFromChange(nameTF.getTextFromTextField(), desc , exist, changeTable, id);

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
