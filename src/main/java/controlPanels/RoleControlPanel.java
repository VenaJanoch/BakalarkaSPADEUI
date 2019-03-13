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

        controlPanelController.setComboBoxItemToControlPanel(controlPane, roleTypeCB, 0, 2);

        controlPane.add(button, 2, 3);

        button.setOnAction(event ->{
            String desc = controlPanelController.checkValueFromTextItem(descriptionTF);
            editFormController.editDataFromRole(nameTF.getTextFromTextField(), desc, roleTypeCB.getItemIndex(), roleTable, roleId);

        });

    }

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {

        String[] roleData = formDataController.getRoleStringData(roleId);
        nameTF.setTextToTextField(roleData[0]);

        controlPanelController.setValueTextField(descriptionTF, roleData, 1);
        controlPanelController.setValueComboBox(roleTypeCB, roleData, 2);
    }
}
