package controlPanels;

import abstractControlPane.WorkUnitDateControlPanel;
import controllers.formControllers.FormController;
import graphics.controlPanelItems.ComboBoxItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import services.*;
import tables.BasicTable;
import tables.PhaseTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Trida predstavujici editacni panel pro element Phase
 *
 * @author Vaclav Janoch
 */
public class PhaseControlPanel extends WorkUnitDateControlPanel {

    /**
     * Globální proměnné třídy
     */

    private ComboBoxItem configCB;
    private ComboBoxItem milestoneCB;
    private SegmentLists segmentLists;

    private PhaseTable phaseTable;

    /**
     * Konstruktor tridy, zinicializuje globalni promenne tridy
     * Je zde rozsiren seznam poznych typu panelu pro dany element
     *
     * @param buttonName         textovy retezec pro potvrzovaci tlacitko
     * @param formDataController instace tridy FormDataController pro ziskani dat z datoveho modelu
     * @param editFormController instace tridy EditDataController pro predani novych dat
     * @param formController     instace tridy FormController
     */
    public PhaseControlPanel(String buttonName, IFormDataController formDataController,
                             IEditFormController editFormController, FormController formController) {
        super(buttonName, formDataController, editFormController, formController);
        segmentLists = formController.getSegmentLists();
        lineList.add(new ControlPanelLineObject("Configuration: ", ControlPanelLineType.ComboBox, ParamType.Configuration, segmentLists.getConfigObservable()));
        lineList.add(new ControlPanelLineObject("Milestone: ", ControlPanelLineType.ComboBox, ParamType.Milestone, segmentLists.getMilestoneObservable()));

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
        phaseTable = (PhaseTable) basicTable;
        int id = phaseTable.getId();
        List[] phaseStringData = formDataController.getPhaseStringData(id);

        controlPanelController.resetPanel(controlPane);
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList, ParamType.Name, phaseStringData, phaseStringData[5], 0);
        controlPanelController.setValueTextField(this, lineList, ParamType.Description, phaseStringData, phaseStringData[6], 1);
        controlPanelController.setValueComboBox(this, lineList, ParamType.Configuration, (ArrayList<Integer>) phaseStringData[2], phaseStringData[7]);
        controlPanelController.setValueDatePicker(this, lineList, ParamType.Date, (ArrayList<LocalDate>) phaseStringData[4], phaseStringData[9]);
        controlPanelController.setValueComboBox(this, lineList, ParamType.Milestone, (ArrayList<Integer>) phaseStringData[3], phaseStringData[8]);
        ArrayList<ArrayList<Integer>> workUnits = formDataController.getWorkUnitFromSegment(id, SegmentType.Phase);
        controlPanelController.setValueCheckComboBox(this, lineList, ParamType.WorkUnit, workUnits, phaseStringData[10]);

        List boolList = phaseStringData[11];
        boolean exist = (boolean) boolList.get(0);

        controlPanelController.setValueExistRadioButton(exist);
        controlPanelController.setAlias((String) boolList.get(2), this);


        button.setOnAction(event -> saveDataFromPanel(phaseTable, tableView));
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
        ArrayList<Integer> milestoneIndicators = new ArrayList<>();
        ArrayList<Integer> workUnitIndicators = new ArrayList<>();
        ArrayList<Integer> date1Indicators = new ArrayList<>();
        ArrayList<Integer> configIndicators = new ArrayList<>();

        ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
        ArrayList<String> desc = controlPanelController.processTextLines(ParamType.Description, descIndicators);
        ArrayList<ArrayList<Integer>> workUnit = controlPanelController.processCheckComboBoxLines(ParamType.WorkUnit, workUnitIndicators);
        ArrayList<Integer> milestone = controlPanelController.processComboBoxLines(ParamType.Milestone, milestoneIndicators);
        ArrayList<Integer> config = controlPanelController.processComboBoxLines(ParamType.Configuration, configIndicators);
        ArrayList<LocalDate> startDate = controlPanelController.processDateLines(ParamType.Date, date1Indicators);

        editFormController.editDataFromPhase(aliasTF.getText(), name, startDate, desc, config, milestone, workUnit, workUnitIndicators, nameIndicators, date1Indicators,
                descIndicators, configIndicators, milestoneIndicators, phaseTable, controlPanelController.isExist(), id);

        clearPanelCB(tableView);
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
     * Metoda pro smazani vyberu v tabulce
     */
    public Button getButton() {
        return button;
    }

    public void clearPanelCB(TableView tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }

}
