package controlPanels;

import controllers.FormController;
import abstractControlPane.DescriptionControlPanel;
import graphics.ComboBoxItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import services.Constans;
import tables.BasicTable;
import tables.RoleTable;

public class RoleControlPanel extends DescriptionControlPanel {

    protected ComboBoxItem roleTypeCB;

    public RoleControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);

    }



    protected void addItemsToControlPanel(){
        roleTypeCB = new ComboBoxItem("Type: ",formController.getRoleTypeObservable());

        controlPane.add(roleTypeCB.getItemNameLB(), 2, 2);
        controlPane.add(roleTypeCB.getItemCB(), 2, 2);
        controlPane.add(roleTypeCB.getItemButton(), 0, 2);
        controlPane.add(button, 2, 3);


    }

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        RoleTable roleTable = (RoleTable) basicTable;
        int id = roleTable.getId();
        String[] roleData = formDataController.getRoleStringData(id);
        roleTypeCB.setShowItem(false);
        if (roleData[2] != null){
            roleTypeCB.setShowItem(true);
            roleTypeCB.selectItemInComboBox(Integer.parseInt(roleData[2]));
        }

        nameTF.setText(roleData[0]);        descriptionTF.setShowItem(false);
        if (roleData[1] != null){
            descriptionTF.setShowItem(true);
            descriptionTF.setTextToTextField(roleData[1]);
        }
        button.setOnAction(event ->{
            editFormController.editDataFromRole(nameTF.getText(), descriptionTF.getTextFromTextField(), roleTable, roleTypeCB.getItemIndex(), id);
            clearPanel(tableView);
        });


    }


    public void clearPanel(TableView<RoleTable> tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }
}
