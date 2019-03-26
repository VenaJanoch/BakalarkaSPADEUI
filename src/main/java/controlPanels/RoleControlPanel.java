package controlPanels;

import controllers.FormController;
import abstractControlPane.DescriptionControlPanel;
import graphics.ComboBoxItem;
import graphics.ControlPanelLine;
import graphics.TextFieldItem;
import interfaces.IControlPanel;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.*;
import services.*;
import tables.BasicTable;
import tables.RoleTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoleControlPanel extends DescriptionControlPanel implements IControlPanel {

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
        SegmentLists segmentLists = formController.getSegmentLists();
        lineList.add(new ControlPanelLineObject("Role Type: ", ControlPanelLineType.ComboBox, ParamType.RoleType, segmentLists.getRoleTypeObservable()));
        this.addItemsToControlPanel();
    }


    @Override
    protected void showEditControlPanel(BasicTable basicTable, TableView tableView) {

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
    public void showEditControlPanel() {

        List[] roleData = formDataController.getRoleStringData(roleId);
        controlPanelController.resetPanel(controlPane);
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList ,ParamType.Name, roleData, roleData[3], 0);
        controlPanelController.setValueTextField(this, lineList ,ParamType.Description, roleData, roleData[4], 1);
        controlPanelController.setValueComboBox(this, lineList ,ParamType.RoleType, (ArrayList<Integer>) roleData[2], roleData[5]);

        controlPanelController.setCountToCountLine((int) roleData[6].get(0));

    }
}
