package controlPanels;

import abstractControlPane.DescriptionControlPanel;
import controllers.FormController;
import controllers.WorkUnitControlPanelController;
import graphics.ComboBoxItem;
import graphics.TextFieldItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.*;
import services.*;
import tables.BasicTable;
import tables.WorkUnitTable;

import java.util.ArrayList;
import java.util.List;

public class WorkUnitControlPanel extends DescriptionControlPanel {

    /**
     * Globální proměnné třídy
     */

    private SegmentLists segmentLists;

    private WorkUnitTable workUnitTable;
    private WorkUnitControlPanelController controller;

    private boolean exist;

    public WorkUnitControlPanel(String buttonName, IFormDataController formDataController,
                                IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);
        this.segmentLists = formController.getSegmentLists();
        this.controller = new WorkUnitControlPanelController(this, segmentLists);
        lineList.add(new ControlPanelLineObject("Estimated time: ", ControlPanelLineType.Text, ParamType.EstimateTime));
        lineList.add(new ControlPanelLineObject("Category: ", ControlPanelLineType.Text, ParamType.Category));
        lineList.add(new ControlPanelLineObject("Priority: ", ControlPanelLineType.ComboBox, ParamType.Priority, segmentLists.getPriorityTypeObservable()));
        lineList.add(new ControlPanelLineObject("Severity: ", ControlPanelLineType.ComboBox, ParamType.Severity, segmentLists.getSeverityTypeObservable()));
        lineList.add(new ControlPanelLineObject("Status: ", ControlPanelLineType.ComboBox, ParamType.Status, segmentLists.getStatusTypeObservable()));
        lineList.add(new ControlPanelLineObject("Resolution: ", ControlPanelLineType.ComboBox, ParamType.Resolution, segmentLists.getResolutionTypeObservable()));
        lineList.add(new ControlPanelLineObject("Type: ", ControlPanelLineType.ComboBox, ParamType.Type, segmentLists.getTypeObservable()));
        lineList.add(new ControlPanelLineObject("Assigne: ", ControlPanelLineType.ComboBox, ParamType.AssigneeRole, segmentLists.getRoleObservable()));
        lineList.add(new ControlPanelLineObject("Autor: ", ControlPanelLineType.ComboBox, ParamType.Role, segmentLists.getRoleObservable()));
        lineList.add(new ControlPanelLineObject("Relation: ", ControlPanelLineType.RelationComboBoxes, ParamType.Relation,
                segmentLists.getRelationTypeObservable(), segmentLists.getWorkUnitsObservable()));

        addItemsToControlPanel();
    }

    protected void addItemsToControlPanel(){


        controlPanelController.setRadioButton(this, 1, "Exist: ", false);
        controlPanelController.createNewLine(this, lineList);
    }


    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        workUnitTable = (WorkUnitTable) basicTable;
        int id = workUnitTable.getId();
        List[] workUnitData = formDataController.getWorkUnitStringData(id);

        controlPanelController.resetPanel(controlPane);
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList ,ParamType.Name, workUnitData, workUnitData[11], 0);
        controlPanelController.setValueTextField(this, lineList ,ParamType.Description, workUnitData, workUnitData[12], 1);
        controlPanelController.setValueTextField(this, lineList ,ParamType.EstimateTime, workUnitData, workUnitData[13], 2);
        controlPanelController.setValueTextField(this, lineList ,ParamType.Category, workUnitData, workUnitData[14], 3);
        controlPanelController.setValueComboBox(this, lineList ,ParamType.Priority, (ArrayList<Integer>) workUnitData[4], workUnitData[15]);
        controlPanelController.setValueComboBox(this, lineList ,ParamType.Severity, (ArrayList<Integer>) workUnitData[5], workUnitData[16]);
        controlPanelController.setValueComboBox(this, lineList ,ParamType.Resolution, (ArrayList<Integer>) workUnitData[6], workUnitData[17]);
        controlPanelController.setValueComboBox(this, lineList ,ParamType.Status, (ArrayList<Integer>) workUnitData[7], workUnitData[18]);
        controlPanelController.setValueComboBox(this, lineList ,ParamType.Type, (ArrayList<Integer>) workUnitData[8], workUnitData[19]);
        controlPanelController.setValueComboBox(this, lineList ,ParamType.AssigneeRole, (ArrayList<Integer>) workUnitData[9], workUnitData[20]);
        controlPanelController.setValueComboBox(this, lineList ,ParamType.Role, (ArrayList<Integer>) workUnitData[10], workUnitData[21]);
        ArrayList<ArrayList<Integer>> workUnits = formDataController.getWorkUnitFromSegment(id, SegmentType.WorkUnit);
        controlPanelController.setValueRelationBox(this, lineList, ParamType.Relation, (ArrayList<Integer>) workUnitData[23], workUnits);
        List boolList = workUnitData[22];
        exist = false;
        if (boolList.size() != 0){
            exist = true;
        }
        controlPanelController.setValueRadioButton(exist);

        button.setOnAction(event -> saveDataFromPanel(basicTable, tableView));
    }


    public void saveDataFromPanel(BasicTable table, TableView tableView){
        int id = table.getId();
        ArrayList<Integer> nameIndicators = new ArrayList<>();
        ArrayList<Integer> descIndicators = new ArrayList<>();
        ArrayList<Integer> estimatedIndicators = new ArrayList<>();
        ArrayList<Integer> categoryIndicators = new ArrayList<>();
        ArrayList<Integer> priorityIndicators = new ArrayList<>();
        ArrayList<Integer> severityIndicators = new ArrayList<>();
        ArrayList<Integer> statusIndicators = new ArrayList<>();
        ArrayList<Integer> resolutionIndicators = new ArrayList<>();
        ArrayList<Integer> typeIndicators = new ArrayList<>();
        ArrayList<Integer> assigneeIndicators = new ArrayList<>();
        ArrayList<Integer> roleIndicators = new ArrayList<>();
        ArrayList<ArrayList<Integer>> workUnit = new ArrayList<>();

        ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
        ArrayList<String> desc = controlPanelController.processTextLines(ParamType.Description, descIndicators);
        ArrayList<String> estimated = controlPanelController.processTextLines(ParamType.EstimateTime, estimatedIndicators);
        ArrayList<String> category = controlPanelController.processTextLines(ParamType.Category, categoryIndicators);
        ArrayList<Integer> priority = controlPanelController.processComboBoxLines(ParamType.Priority, priorityIndicators);
        ArrayList<Integer> severity = controlPanelController.processComboBoxLines(ParamType.Severity, severityIndicators);
        ArrayList<Integer> status = controlPanelController.processComboBoxLines(ParamType.Status, statusIndicators);
        ArrayList<Integer> resolution = controlPanelController.processComboBoxLines(ParamType.Resolution, resolutionIndicators);
        ArrayList<Integer> type = controlPanelController.processComboBoxLines(ParamType.Type, typeIndicators);
        ArrayList<Integer> assignee = controlPanelController.processComboBoxLines(ParamType.AssigneeRole, assigneeIndicators);
        ArrayList<Integer> role = controlPanelController.processComboBoxLines(ParamType.Role, roleIndicators);
        ArrayList<Integer> relation = controlPanelController.processRelationComboBoxLines(ParamType.Relation, workUnit);

        boolean exist = controlPanelController.isMain();


        editFormController.editDataFromWorkUnit(name, desc, category, assignee, role, priority, severity, type, resolution, status, estimated, nameIndicators,
                descIndicators, categoryIndicators, assigneeIndicators, roleIndicators, priorityIndicators, severityIndicators, typeIndicators, resolutionIndicators,
                statusIndicators, estimatedIndicators, exist, relation, workUnit, workUnitTable, id);

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
