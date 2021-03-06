package controlPanels;

import abstractControlPane.DateDescControlPanel;
import controllers.formControllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import services.*;
import tables.BasicTable;
import tables.WorkUnitTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Trida predstavujici editacni panel pro element Work Unit
 *
 * @author Vaclav Janoch
 */
public class WorkUnitControlPanel extends DateDescControlPanel {

    /**
     * Globální proměnné třídy
     */

    private SegmentLists segmentLists;

    private WorkUnitTable workUnitTable;

    private boolean exist;

    /**
     * Konstruktor tridy, zinicializuje globalni promenne tridy
     * Je zde rozsiren seznam poznych typu panelu pro dany element
     *
     * @param buttonName         textovy retezec pro potvrzovaci tlacitko
     * @param formDataController instace tridy FormDataController pro ziskani dat z datoveho modelu
     * @param editFormController instace tridy EditDataController pro predani novych dat
     * @param formController     instace tridy FormController
     */
    public WorkUnitControlPanel(String buttonName, IFormDataController formDataController,
                                IEditFormController editFormController, FormController formController) {
        super(buttonName, formDataController, editFormController, formController);
        this.segmentLists = formController.getSegmentLists();
        lineList.add(new ControlPanelLineObject("Estimated time: ", ControlPanelLineType.Number, ParamType.EstimateTime));
        lineList.add(new ControlPanelLineObject("Progress: ", ControlPanelLineType.Number, ParamType.Progress));
        lineList.add(new ControlPanelLineObject("Category: ", ControlPanelLineType.Text, ParamType.Category));
        lineList.add(new ControlPanelLineObject("Priority: ", ControlPanelLineType.ComboBox, ParamType.Priority, segmentLists.getPriorityTypeObservable()));
        lineList.add(new ControlPanelLineObject("Severity: ", ControlPanelLineType.ComboBox, ParamType.Severity, segmentLists.getSeverityTypeObservable()));
        lineList.add(new ControlPanelLineObject("Status: ", ControlPanelLineType.ComboBox, ParamType.Status, segmentLists.getStatusTypeObservable()));
        lineList.add(new ControlPanelLineObject("Resolution: ", ControlPanelLineType.ComboBox, ParamType.Resolution, segmentLists.getResolutionTypeObservable()));
        lineList.add(new ControlPanelLineObject("Type: ", ControlPanelLineType.ComboBox, ParamType.Type, segmentLists.getTypeObservable()));
        lineList.add(new ControlPanelLineObject("Assigne: ", ControlPanelLineType.ComboBox, ParamType.AssigneeRole, segmentLists.getPersonObservable()));
        lineList.add(new ControlPanelLineObject("Autor: ", ControlPanelLineType.ComboBox, ParamType.Role, segmentLists.getPersonObservable()));
        lineList.add(new ControlPanelLineObject("Relation: ", ControlPanelLineType.RelationComboBoxes, ParamType.Relation,
                segmentLists.getRelationTypeObservable(), segmentLists.getWorkUnitsObservable()));

        addItemsToControlPanel();
    }

    /**
     * Metoda volajici kontroler ControlPanelController pro vygenerovani noveho radku
     * Pripadne rozsireni o staticke objekty
     */
    protected void addItemsToControlPanel() {


        controlPanelController.createNewLineWithExist(this, lineList);
    }

    /**
     * Metoda pro zobrazeni postraniho editacniho panelu
     * Nejprve jsou ziskana data z datoveho modelu
     * nasledne pomoci kontroleru ControlPanelController pridana do panelu
     *
     * @param basicTable Instance BasicTable
     * @param tableView  Instace TableView
     */
    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        workUnitTable = (WorkUnitTable) basicTable;
        int id = workUnitTable.getId();
        List[] workUnitData = formDataController.getWorkUnitStringData(id);

        controlPanelController.resetPanel(controlPane);
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList, ParamType.Name, workUnitData, workUnitData[11], 0);
        controlPanelController.setValueDatePicker(this, lineList, ParamType.Date, (ArrayList<LocalDate>) workUnitData[25], workUnitData[26]);
        controlPanelController.setValueTextField(this, lineList, ParamType.Description, workUnitData, workUnitData[12], 1);
        controlPanelController.setValueNumberField(this, lineList, ParamType.EstimateTime, workUnitData, workUnitData[13], 2);
        controlPanelController.setValueTextField(this, lineList, ParamType.Category, workUnitData, workUnitData[14], 3);
        controlPanelController.setValueNumberField(this, lineList, ParamType.Progress, workUnitData, workUnitData[28], 27);
        controlPanelController.setValueComboBox(this, lineList, ParamType.Priority, (ArrayList<Integer>) workUnitData[4], workUnitData[15]);
        controlPanelController.setValueComboBox(this, lineList, ParamType.Severity, (ArrayList<Integer>) workUnitData[5], workUnitData[16]);
        controlPanelController.setValueComboBox(this, lineList, ParamType.Resolution, (ArrayList<Integer>) workUnitData[6], workUnitData[17]);
        controlPanelController.setValueComboBox(this, lineList, ParamType.Status, (ArrayList<Integer>) workUnitData[7], workUnitData[18]);
        controlPanelController.setValueComboBox(this, lineList, ParamType.Type, (ArrayList<Integer>) workUnitData[8], workUnitData[19]);
        controlPanelController.setValueComboBox(this, lineList, ParamType.AssigneeRole, (ArrayList<Integer>) workUnitData[9], workUnitData[20]);
        controlPanelController.setValueComboBox(this, lineList, ParamType.Role, (ArrayList<Integer>) workUnitData[10], workUnitData[21]);
        ArrayList<ArrayList<Integer>> workUnits = formDataController.getWorkUnitFromSegment(id, SegmentType.Work_Unit);
        controlPanelController.setValueRelationBox(this, lineList, ParamType.Relation, (ArrayList<Integer>) workUnitData[23], workUnits);
        List boolList = workUnitData[22];
        exist = (boolean) boolList.get(0);
        controlPanelController.setValueExistRadioButton(exist);
        controlPanelController.setAlias((String) boolList.get(2), this);
        button.setOnAction(event -> saveDataFromPanel(basicTable, tableView));
    }

    /**
     * Metoda pro ziskani dat z grafickych komponent a predani dat do editacniho kontroleru EditFormController
     *
     * @param table     instace tridy BasicTable pro ziskani identifikatoru segmentu
     * @param tableView instace tridy TableView pro moznost zpetne aktualizace
     */
    public void saveDataFromPanel(BasicTable table, TableView tableView) {
        int id = table.getId();
        ArrayList<Integer> nameIndicators = new ArrayList<>();
        ArrayList<Integer> progressIndicators = new ArrayList<>();
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
        ArrayList<Integer> createIndicators = new ArrayList<>();

        ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
        ArrayList<String> progress = controlPanelController.processNumberLines(ParamType.Progress, progressIndicators);
        ArrayList<String> desc = controlPanelController.processTextLines(ParamType.Description, descIndicators);
        ArrayList<LocalDate> created = controlPanelController.processDateLines(ParamType.Date, createIndicators);
        ArrayList<String> estimated = controlPanelController.processNumberLines(ParamType.EstimateTime, estimatedIndicators);
        ArrayList<String> category = controlPanelController.processTextLines(ParamType.Category, categoryIndicators);
        ArrayList<Integer> priority = controlPanelController.processComboBoxLines(ParamType.Priority, priorityIndicators);
        ArrayList<Integer> severity = controlPanelController.processComboBoxLines(ParamType.Severity, severityIndicators);
        ArrayList<Integer> status = controlPanelController.processComboBoxLines(ParamType.Status, statusIndicators);
        ArrayList<Integer> resolution = controlPanelController.processComboBoxLines(ParamType.Resolution, resolutionIndicators);
        ArrayList<Integer> type = controlPanelController.processComboBoxLines(ParamType.Type, typeIndicators);
        ArrayList<Integer> assignee = controlPanelController.processComboBoxLines(ParamType.AssigneeRole, assigneeIndicators);
        ArrayList<Integer> role = controlPanelController.processComboBoxLines(ParamType.Role, roleIndicators);
        ArrayList<Integer> relation = controlPanelController.processRelationComboBoxLines(ParamType.Relation, workUnit);

        boolean exist = controlPanelController.isExist();


        editFormController.editDataFromWorkUnit(aliasTF.getText(), progress, progressIndicators, name, desc, category, assignee, role, priority, severity, type, resolution, status, estimated, nameIndicators,
                descIndicators, categoryIndicators, assigneeIndicators, roleIndicators, priorityIndicators, severityIndicators, typeIndicators, resolutionIndicators,
                statusIndicators, estimatedIndicators, exist, relation, workUnit, created, createIndicators, workUnitTable, id);

        clearPanelCB(tableView);
    }

    public Button getButton() {
        return button;
    }

    /**
     * Metoda pro smazani vyberu a aktualizaci dat v tabulce
     */
    public void clearPanelCB(TableView tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }
}
