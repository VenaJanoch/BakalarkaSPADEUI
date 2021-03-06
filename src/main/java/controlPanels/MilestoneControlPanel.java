package controlPanels;

import abstractControlPane.DescriptionControlPanel;
import controllers.formControllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import services.ControlPanelLineObject;
import services.ControlPanelLineType;
import services.ParamType;
import services.SegmentLists;
import tables.BasicTable;
import tables.MilestoneTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Trida predstavujici editacni panel pro element Milestone
 *
 * @author Vaclav Janoch
 */

public class MilestoneControlPanel extends DescriptionControlPanel {
    /**
     * Globální proměnné třídy
     */
    private MilestoneTable milestoneTable;

    /**
     * Konstruktor tridy, zinicializuje globalni promenne tridy
     * Je zde rozsiren seznam poznych typu panelu pro dany element
     *
     * @param buttonName         textovy retezec pro potvrzovaci tlacitko
     * @param formDataController instace tridy FormDataController pro ziskani dat z datoveho modelu
     * @param editFormController instace tridy EditDataController pro predani novych dat
     * @param formController     instace tridy FormController
     */
    public MilestoneControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonName, formDataController, editFormController, formController);
        SegmentLists segmentLists = formController.getSegmentLists();
        lineList.add(new ControlPanelLineObject("Criterions: ", ControlPanelLineType.CheckBox, ParamType.Criterion, segmentLists.getCriterionObservable()));

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
        milestoneTable = (MilestoneTable) basicTable;
        int id = milestoneTable.getId();

        List[] milestoneData = formDataController.getMilestoneStringData(id);

        controlPanelController.resetPanel(controlPane);
        addItemsToControlPanel();

        ArrayList<ArrayList<Integer>> criteriaID = formDataController.getCriterionFromMilestone(id);


        controlPanelController.setValueTextField(this, lineList, ParamType.Name, milestoneData, milestoneData[2], 0);
        controlPanelController.setValueTextField(this, lineList, ParamType.Description, milestoneData, milestoneData[3], 1);

        controlPanelController.setValueCheckComboBox(this, lineList, ParamType.Criterion, criteriaID, milestoneData[4]);
        List boolList = milestoneData[5];
        boolean exist = (boolean) boolList.get(0);

        controlPanelController.setValueExistRadioButton(exist);
        controlPanelController.setAlias((String) boolList.get(2), this);
        button.setOnAction(event -> {

            saveDataFromPanel(milestoneTable, tableView);


        });

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
        ArrayList<Integer> criterionIndicators = new ArrayList<>();
        ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
        ArrayList<String> desc = controlPanelController.processTextLines(ParamType.Description, descIndicators);
        ArrayList<ArrayList<Integer>> criterion = controlPanelController.processCheckComboBoxLines(ParamType.Criterion, criterionIndicators);


        editFormController.editDataFromMilestone(aliasTF.getText(), name, nameIndicators, desc, descIndicators, milestoneTable, criterion, criterionIndicators,
                controlPanelController.isExist(), id);

        clearPanelCB(tableView);
    }

    public Button getButton() {
        return button;
    }

    /**
     * Metoda pro smazani vyberu v tabulce
     *
     * @param tableView instace tridy TableView
     */
    public void clearPanelCB(TableView tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }
}
