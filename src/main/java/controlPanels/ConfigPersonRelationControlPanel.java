package controlPanels;

import abstractControlPane.DescriptionControlPanel;
import controllers.formControllers.FormController;
import abstractControlPane.NameControlPanel;
import graphics.controlPanelItems.ComboBoxItem;
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

public class ConfigPersonRelationControlPanel extends DescriptionControlPanel {
    private ComboBoxItem roleCB;


    public ConfigPersonRelationControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);
        SegmentLists segmentLists = formController.getSegmentLists();
        lineList.add(new ControlPanelLineObject("Person: ", ControlPanelLineType.ComboBox, ParamType.Role, segmentLists.getRoleObservable()));

        createControlPanel();
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
        controlPanelController.setValueTextField(this, lineList ,ParamType.Description, configPersonRelation, configPersonRelation[6], 5);
        controlPanelController.setValueComboBox(this, lineList ,ParamType.Role, (ArrayList<Integer>)configPersonRelation[1], configPersonRelation[3]);
        List boolList = configPersonRelation[4];
        boolean exist = (boolean) boolList.get(0);

        controlPanelController.setValueExistRadioButton(exist);
        controlPanelController.setAlias((String)boolList.get(2), this);
        button.setOnAction(event ->{

            ArrayList<Integer> nameIndicators = new ArrayList<>();
            ArrayList<Integer> roleIndicators = new ArrayList<>();
            ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
            ArrayList<Integer> role = controlPanelController.processComboBoxLines(ParamType.Role, roleIndicators);
            ArrayList<Integer> descriptionIndicators = new ArrayList<>();
            ArrayList<String> description = controlPanelController.processTextLines(ParamType.Description, descriptionIndicators);


            editFormController.editDataFromCPR(aliasTF.getText(), name, nameIndicators, description, descriptionIndicators, role, roleIndicators, controlPanelController.isExist(), cprTable);
            clearPanel(tableView);

        });
    }

    @Override
    protected void addItemsToControlPanel() {

    }


    public void clearPanel(TableView<CPRTable> tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }
}
