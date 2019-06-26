package controlPanels;

import abstractControlPane.WorkUnitControlPanel;
import controllers.formControllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import services.ControlPanelLineObject;
import services.ControlPanelLineType;
import services.ParamType;
import services.SegmentType;
import tables.ActivityTable;
import tables.BasicTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Trida predstavujici editacni panel pro element Activity
 *
 * @author Vaclav Janoch
 */

public class ActivityControlPanel extends WorkUnitControlPanel {

    /**
     * Globální proměnné třídy
     */
    private ActivityTable activityTable;

    /**
     * Konstruktor tridy, zinicializuje globalni promenne tridy
     * Je zde rozsiren seznam poznych typu panelu pro dany element
     *
     * @param buttonName         textovy retezec pro potvrzovaci tlacitko
     * @param formDataController instace tridy FormDataController pro ziskani dat z datoveho modelu
     * @param editFormController instace tridy EditDataController pro predani novych dat
     * @param formController     instace tridy FormController
     */
    public ActivityControlPanel(String buttonName, IFormDataController formDataController,
                                IEditFormController editFormController, FormController formController) {
        super(buttonName, formDataController, editFormController, formController);
        lineList.add(new ControlPanelLineObject("End date: ", ControlPanelLineType.Date, ParamType.EndDate));
        addItemsToControlPanel();
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
        activityTable = (ActivityTable) basicTable;
        int id = activityTable.getId();
        List activityData[] = formDataController.getActivityStringData(id);

        controlPanelController.resetPanel(controlPane);
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList, ParamType.Name, activityData, activityData[3], 0);
        controlPanelController.setValueTextField(this, lineList, ParamType.Description, activityData, activityData[4], 1);
        controlPanelController.setValueDatePicker(this, lineList, ParamType.EndDate, (ArrayList<LocalDate>) activityData[6], activityData[7]);
        ArrayList<ArrayList<Integer>> workUnits = formDataController.getWorkUnitFromSegment(id, SegmentType.Activity);
        controlPanelController.setValueCheckComboBox(this, lineList, ParamType.WorkUnit, workUnits, activityData[2]);

        boolean exist = false;
        List boolList = activityData[5];
        exist = (boolean) boolList.get(0);

        controlPanelController.setValueExistRadioButton(exist);
        controlPanelController.setAlias((String) activityData[5].get(2), this);
        button.setOnAction(event -> saveDataFromPanel(activityTable, tableView));
    }

    /**
     * Metoda volajici kontroler ControlPanelController pro vygenerovani noveho radku
     * Pripadne rozsireni o staticke objekty
     */
    @Override
    protected void addItemsToControlPanel() {

        controlPanelController.createNewLineWithExist(this, lineList);

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
        ArrayList<Integer> descIndicators = new ArrayList<>();
        ArrayList<Integer> workUnitIndicators = new ArrayList<>();
        ArrayList<Integer> endDateIndicators = new ArrayList<>();
        ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
        ArrayList<String> desc = controlPanelController.processTextLines(ParamType.Description, descIndicators);
        ArrayList<LocalDate> endDate = controlPanelController.processDateLines(ParamType.EndDate, endDateIndicators);
        ArrayList<ArrayList<Integer>> workUnit = controlPanelController.processCheckComboBoxLines(ParamType.WorkUnit, workUnitIndicators);
        editFormController.editDataFromActivity(aliasTF.getText(), name, desc, workUnit, nameIndicators, descIndicators,
                workUnitIndicators, endDate, endDateIndicators, activityTable, controlPanelController.isExist(), id);

        clearPanelCB(tableView);
    }


    public Button getButton() {
        return button;
    }

    /**
     * Metoda pro smazani vyberu a aktualizaci dat v tabulce
     * @param tableView tabulka pro aktualizaci dat
     */
    public void clearPanelCB(TableView tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }

}
