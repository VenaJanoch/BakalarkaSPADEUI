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
    private int roleId;
    private RoleTable roleTable;
    private int roleIndex;
    public RoleControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController,
                            FormController formController, RoleTable roleTable, int roleId, int roleIndex){
        super(buttonName, formDataController, editFormController, formController);
        this.roleId = roleId;
        this.roleIndex = roleIndex;
        this.roleTable = roleTable;
        this.addItemsToControlPanel();
    }



    protected void addItemsToControlPanel(){
        roleTypeCB = new ComboBoxItem("Type: ",formController.getRoleTypeObservable());

        controlPane.add(roleTypeCB.getItemButton(), 0, 2);
        controlPane.add(roleTypeCB.getItemNameLB(), 1, 2);
        controlPane.add(roleTypeCB.getItemCB(), 2, 2);
        controlPane.add(button, 2, 3);
        button.setOnAction(event ->{
            editFormController.editDataFromRole(nameTF.getText(), descriptionTF.getTextFromTextField(), roleTypeCB.getItemIndex(), roleTable, roleId);
            formController.setNameToItem(roleIndex, nameTF.getText());
        });

    }

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {

        String[] roleData = formDataController.getRoleStringData(roleId);
        roleTypeCB.setShowItem(false);
        if (roleData[2] != null){
            roleTypeCB.setShowItem(true);
            roleTypeCB.selectItemInComboBox(Integer.parseInt(roleData[2]));
        }

        nameTF.setText(roleData[0]);
        descriptionTF.setTextToTextField(null);
        descriptionTF.setShowItem(false);
        if (roleData[1] != null){
            descriptionTF.setShowItem(true);
            descriptionTF.setTextToTextField(roleData[1]);
        }
    }
}
