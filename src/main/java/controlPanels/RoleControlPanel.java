package controlPanels;

import controllers.FormController;
import abstractControlPane.DescriptionControlPanel;
import graphics.ComboBoxItem;
import graphics.ControlPanelLine;
import graphics.TextFieldItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.*;
import services.ControlPanelLineObject;
import services.ControlPanelLineType;
import services.ParamType;
import services.SegmentType;
import tables.BasicTable;
import tables.RoleTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoleControlPanel extends DescriptionControlPanel {

    protected ComboBoxItem roleTypeCB;
    private int roleId;
    private RoleTable roleTable;
    private TextFieldItem entityCounterTF;
    private int roleIndex;
    public RoleControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController,
                            FormController formController, RoleTable roleTable, int roleId, int roleIndex){
        super(buttonName, formDataController, editFormController, formController);
        this.roleId = roleId;
        this.roleIndex = roleIndex;
        this.roleTable = roleTable;
        lineList.add(new ControlPanelLineObject("Role Type: ", ControlPanelLineType.ComboBox, ParamType.RoleType));
        this.addItemsToControlPanel();
    }



    protected void addItemsToControlPanel(){

        controlPanelController.setCountLine(this, 1, new ControlPanelLine(lineList, this, controlPanelController));
        controlPanelController.createNewLine(this, lineList);

        button.setOnAction(event ->{
            ArrayList<Integer> nameIndicators = new ArrayList<>();
            ArrayList<Integer> descIndicators = new ArrayList<>();
            ArrayList<Integer> roleTypeIndicators = new ArrayList<>();

            ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
            ArrayList<String> desc = controlPanelController.processTextLines(ParamType.Description, descIndicators);
            ArrayList<Integer> roleType = controlPanelController.processComboBoxLines(ParamType.RoleType, roleTypeIndicators);

            String count = controlPanelController.getInstanceCount();

            editFormController.editDataFromRole(name, nameIndicators, desc,descIndicators, count, roleType, roleTypeIndicators, roleTable, roleId);

        });

    }

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {

        List[] roleData = formDataController.getRoleStringData(roleId);
        controlPane.getChildren().clear();
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList ,ParamType.Name, roleData, roleData[3], 0);
        controlPanelController.setValueTextField(this, lineList ,ParamType.Description, roleData, roleData[4], 1);
        controlPanelController.setValueComboBox(this, lineList ,ParamType.RoleType, (ArrayList<Integer>) roleData[2], roleData[5]);


    }
}
