package controlPanels;

import abstractControlPane.DescriptionControlPanel;
import controllers.formControllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import services.ControlPanelLineObject;
import services.ControlPanelLineType;
import services.ParamType;
import services.SegmentLists;
import tables.BasicTable;
import tables.ChangeTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Trida predstavujici editacni panel pro element Activity
 *
 * @author Vaclav Janoch
 */
public class ChangeControlPanel extends DescriptionControlPanel {

    /**
     * Globální proměnné třídy
     */

    private RadioButton existRB;

    private boolean exist;

    private ChangeTable changeTable;

    /**
     * Konstruktor tridy, zinicializuje globalni promenne tridy
     * Je zde rozsiren seznam poznych typu panelu pro dany element
     *
     * @param buttonName         textovy retezec pro potvrzovaci tlacitko
     * @param formDataController instace tridy FormDataController pro ziskani dat z datoveho modelu
     * @param editFormController instace tridy EditDataController pro predani novych dat
     * @param formController     instace tridy FormController
     */
    public ChangeControlPanel(String buttonName, IFormDataController formDataController,
                              IEditFormController editFormController, FormController formController) {
        super(buttonName, formDataController, editFormController, formController);
        SegmentLists segmentLists = formController.getSegmentLists();
        lineList.add(new ControlPanelLineObject("Artifact: ", ControlPanelLineType.ComboBox, ParamType.Artifact, segmentLists.getArtifactObservable()));
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
        changeTable = (ChangeTable) basicTable;
        int id = changeTable.getId();
        List[] changeData = formDataController.getChangeStringData(id);

        controlPanelController.resetPanel(controlPane);
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList, ParamType.Name, changeData, changeData[2], 0);
        controlPanelController.setValueTextField(this, lineList, ParamType.Description, changeData, changeData[3], 1);
        ArrayList<Integer> artifactIndicators = new ArrayList<>();
        artifactIndicators.add(0);
        controlPanelController.setValueComboBox(this, lineList, ParamType.Artifact, (ArrayList<Integer>) changeData[5], artifactIndicators);

        List boolList = changeData[4];
        controlPanelController.setValueExistRadioButton((boolean) boolList.get(0));
        controlPanelController.setAlias((String) boolList.get(2), this);
        button.setOnAction(event -> saveDataFromPanel(changeTable, tableView));
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
        ArrayList<Integer> artifactsIndicators = new ArrayList<>();
        ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
        ArrayList<String> desc = controlPanelController.processTextLines(ParamType.Description, descIndicators);
        ArrayList<Integer> aritifacts = controlPanelController.processComboBoxLines(ParamType.Artifact, artifactsIndicators);

        exist = controlPanelController.isExist();

        editFormController.editDataFromChange(aliasTF.getText(), name, nameIndicators, desc, aritifacts, descIndicators, exist, changeTable, id);

        clearPanelCB(tableView);
    }


    public Button getButton() {
        return button;
    }

    /**
     * Metoda pro smazani vyberu v tabulce
     */
    public void clearPanelCB(TableView tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }

}
