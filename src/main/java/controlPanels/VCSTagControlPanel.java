package controlPanels;

import abstractControlPane.DescriptionControlPanel;
import controllers.formControllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import services.ParamType;
import tables.BasicTable;
import tables.VCSTagTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Trida predstavujici editacni panel pro element VCSTag
 *
 * @author Vaclav Janoch
 */
public class VCSTagControlPanel extends DescriptionControlPanel {

    /**
     * Globální proměnné třídy
     */

    private VCSTagTable tagTable;

    /**
     * Konstruktor tridy, zinicializuje globalni promenne tridy
     * Je zde rozsiren seznam poznych typu panelu pro dany element
     *
     * @param buttonName         textovy retezec pro potvrzovaci tlacitko
     * @param formDataController instace tridy FormDataController pro ziskani dat z datoveho modelu
     * @param editFormController instace tridy EditDataController pro predani novych dat
     * @param formController     instace tridy FormController
     */
    public VCSTagControlPanel(String buttonName, IFormDataController formDataController,
                              IEditFormController editFormController, FormController formController) {
        super(buttonName, formDataController, editFormController, formController);
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
        int id = basicTable.getId();
        List[] vcsTagStringData = formDataController.getVCSTagStringData(id);

        controlPanelController.resetPanel(controlPane);
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList, ParamType.Name, vcsTagStringData, vcsTagStringData[2], 0);
        controlPanelController.setValueTextField(this, lineList, ParamType.Description, vcsTagStringData, vcsTagStringData[3], 1);
        List boolList = vcsTagStringData[4];
        boolean exist = (boolean) boolList.get(0);

        controlPanelController.setValueExistRadioButton(exist);
        controlPanelController.setAlias((String) boolList.get(2), this);
        button.setOnAction(event -> saveDataFromPanel(basicTable, tableView));
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
    public void saveDataFromPanel(BasicTable basicTable, TableView tableView) {
        tagTable = (VCSTagTable) basicTable;
        int id = tagTable.getId();
        ArrayList<Integer> nameIndicators = new ArrayList<>();
        ArrayList<Integer> descIndicators = new ArrayList<>();
        ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
        ArrayList<String> desc = controlPanelController.processTextLines(ParamType.Description, descIndicators);

        editFormController.editDataFromVCSTag(aliasTF.getText(), name, desc, nameIndicators, descIndicators, tagTable,
                controlPanelController.isExist(), id);
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
