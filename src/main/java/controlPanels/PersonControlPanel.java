package controlPanels;

import abstractControlPane.NameControlPanel;
import controllers.formControllers.FormController;
import graphics.controlPanelItems.ControlPanelLine;
import interfaces.IControlPanel;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import services.*;
import tables.PersonTable;

import java.util.ArrayList;
import java.util.List;

public class PersonControlPanel extends NameControlPanel implements IControlPanel {

    private int roleId;
    private PersonTable personTable;
    private int roleIndex;

    public PersonControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController,
                              FormController formController, PersonTable personTable, int roleId, int roleIndex) {
        super(buttonName, formDataController, editFormController, formController);
        this.roleId = roleId;
        this.roleIndex = roleIndex;
        this.personTable = personTable;
        SegmentLists segmentLists = formController.getSegmentLists();
        lineList.add(new ControlPanelLineObject("Role type: ", ControlPanelLineType.ComboBox, ParamType.RoleType, segmentLists.getRoleTypeObservable()));
        this.addItemsToControlPanel();
    }

    protected void addItemsToControlPanel() {

        controlPanelController.setCountLine(this, 1, new ControlPanelLine(lineList, this, controlPanelController, Constans.numberIndicatorList, controlPanelController.getLineCount()));
        controlPanelController.createNewLineWithExist(this, lineList);

        button.setOnAction(event -> {
            ArrayList<Integer> nameIndicators = new ArrayList<>();
            //ArrayList<Integer> descIndicators = new ArrayList<>();
            ArrayList<Integer> roleTypeIndicators = new ArrayList<>();

            ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
            ArrayList<Integer> roleType = controlPanelController.processComboBoxLines(ParamType.RoleType, roleTypeIndicators);

            String count = controlPanelController.getInstanceCount();
            int countIndicator = controlPanelController.getInstanceCountIndicator();
            editFormController.editDataFromPerson(aliasTF.getText(), name, nameIndicators, count, countIndicator, roleType, roleTypeIndicators, personTable,
                    controlPanelController.isExist(), roleId);

        });

    }

    @Override
    public void showEditControlPanel() {

        List[] roleData = formDataController.getPersonStringData(roleId);
        controlPanelController.resetPanel(controlPane);
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList, ParamType.Name, roleData, roleData[2], 0);
        // controlPanelController.setValueTextField(this, lineList ,ParamType.Description, roleData, roleData[4], 1);
        controlPanelController.setValueComboBox(this, lineList, ParamType.RoleType, (ArrayList<Integer>) roleData[1], roleData[3]);
        List boolList = roleData[4];
        boolean exist = (boolean) boolList.get(0);

        controlPanelController.setValueExistRadioButton(exist);
        controlPanelController.setCountToCountLine((int) boolList.get(1), (int) boolList.get(2));
        controlPanelController.setAlias((String) boolList.get(3), this);
    }

    @Override
    protected void createBaseControlPanel() {

    }
}
