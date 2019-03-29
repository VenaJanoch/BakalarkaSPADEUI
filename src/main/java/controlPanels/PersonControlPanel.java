package controlPanels;

import abstractControlPane.NameControlPanel;
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
import tables.PersonTable;

import java.util.ArrayList;
import java.util.List;

public class PersonControlPanel extends NameControlPanel implements IControlPanel {

    private int roleId;
    private PersonTable personTable;
    private int roleIndex;
    public PersonControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController,
                              FormController formController, PersonTable personTable, int roleId, int roleIndex){
        super(buttonName, formDataController, editFormController, formController);
        this.roleId = roleId;
        this.roleIndex = roleIndex;
        this.personTable = personTable;
        SegmentLists segmentLists = formController.getSegmentLists();
        lineList.add(new ControlPanelLineObject("Person Type: ", ControlPanelLineType.ComboBox, ParamType.RoleType, segmentLists.getRoleTypeObservable()));
        this.addItemsToControlPanel();
    }

    protected void addItemsToControlPanel(){

        controlPanelController.setCountLine(this, 1, new ControlPanelLine(lineList, this, controlPanelController));
        controlPanelController.createNewLine(this, lineList);

        button.setOnAction(event ->{
            ArrayList<Integer> nameIndicators = new ArrayList<>();
            //ArrayList<Integer> descIndicators = new ArrayList<>();
            ArrayList<Integer> roleTypeIndicators = new ArrayList<>();

            ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
            ArrayList<Integer> roleType = controlPanelController.processComboBoxLines(ParamType.RoleType, roleTypeIndicators);

            String count = controlPanelController.getInstanceCount();

            editFormController.editDataFromRole(name, nameIndicators, count, roleType, roleTypeIndicators, personTable,
                    controlPanelController.isExist(), roleId);

        });

    }

    @Override
    public void showEditControlPanel() {

        List[] roleData = formDataController.getPersonStringData(roleId);
        controlPanelController.resetPanel(controlPane);
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList ,ParamType.Name, roleData, roleData[2], 0);
       // controlPanelController.setValueTextField(this, lineList ,ParamType.Description, roleData, roleData[4], 1);
        controlPanelController.setValueComboBox(this, lineList ,ParamType.RoleType, (ArrayList<Integer>) roleData[1], roleData[3]);
        List boolList = roleData[4];
        boolean exist = (boolean) boolList.get(0);

        controlPanelController.setValueExistRadioButton(exist);
        controlPanelController.setCountToCountLine((int) boolList.get(1));

    }

    @Override
    protected void createBaseControlPanel() {

    }
}
