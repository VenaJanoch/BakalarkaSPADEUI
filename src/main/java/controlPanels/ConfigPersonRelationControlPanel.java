package controlPanels;

import controllers.FormController;
import abstractControlPane.NameControlPanel;
import graphics.ComboBoxItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.TableView;
import services.ControlPanelLineObject;
import services.ControlPanelLineType;
import services.ParamType;
import services.SegmentLists;
import tables.BasicTable;
import tables.CPRTable;

import java.util.ArrayList;
import java.util.List;

public class ConfigPersonRelationControlPanel extends NameControlPanel {
    private ComboBoxItem roleCB;


    public ConfigPersonRelationControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);
        SegmentLists segmentLists = formController.getSegmentLists();
        lineList.add(new ControlPanelLineObject("Person: ", ControlPanelLineType.ComboBox, ParamType.Role, segmentLists.getRoleObservable()));

        createControlPanel();
    }

    @Override
    protected void createBaseControlPanel() {

    }


    public void createControlPanel(){

        controlPanelController.createNewLineWithExist(this, lineList);

    }


    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        CPRTable cprTable = (CPRTable) basicTable;
        int id = cprTable.getId();
        List[] configPersonRelation = formDataController.getCPRStringData(id);

        controlPanelController.resetPanel(controlPane);
        createControlPanel();

        controlPanelController.setValueTextField(this, lineList ,ParamType.Name, configPersonRelation, configPersonRelation[2], 0);
        controlPanelController.setValueTextField(this, lineList ,ParamType.Role, configPersonRelation, configPersonRelation[3], 1);
        List boolList = configPersonRelation[4];
        boolean exist = (boolean) boolList.get(0);

        controlPanelController.setValueExistRadioButton(exist);
        button.setOnAction(event ->{

            ArrayList<Integer> nameIndicators = new ArrayList<>();
            ArrayList<Integer> roleIndicators = new ArrayList<>();
            ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
            ArrayList<Integer> role = controlPanelController.processComboBoxLines(ParamType.Role, roleIndicators);

            editFormController.editDataFromCPR(name, nameIndicators, role, roleIndicators, controlPanelController.isExist(), cprTable);
            clearPanel(tableView);

        });
    }



    public void clearPanel(TableView<CPRTable> tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }
}
