package controlPanels;

import abstractControlPane.DescriptionControlPanel;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.TableView;
import services.ParamType;
import tables.BasicTable;
import tables.CriterionTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Trida predstavujici editacni panel pro element Criterion
 *
 * @author Vaclav Janoch
 */
public class CriterionControlPanel extends DescriptionControlPanel {

    /**
     * Konstruktor tridy, zinicializuje globalni promenne tridy
     * Je zde rozsiren seznam poznych typu panelu pro dany element
     *
     * @param buttonName         textovy retezec pro potvrzovaci tlacitko
     * @param formDataController instace tridy FormDataController pro ziskani dat z datoveho modelu
     * @param editFormController instace tridy EditDataController pro predani novych dat
     */
    public CriterionControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonName, formDataController, editFormController);
        addItemsToControlPanel();
    }

    /**
     * Metoda volajici kontroler ControlPanelController pro vygenerovani noveho radku
     * Pripadne rozsireni o staticke objekty
     */
    public void addItemsToControlPanel() {
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
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        CriterionTable criterionTable = (CriterionTable) basicTable;
        int id = criterionTable.getId();
        List[] criterionData = formDataController.getCriterionData(id);

        controlPanelController.resetPanel(controlPane);
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList, ParamType.Name, criterionData, criterionData[2], 0);
        controlPanelController.setValueTextField(this, lineList, ParamType.Description, criterionData, criterionData[3], 1);
        List boolList = criterionData[4];
        boolean exist = (boolean) boolList.get(0);

        controlPanelController.setValueExistRadioButton(exist);
        controlPanelController.setAlias((String) boolList.get(2), this);


        button.setOnAction(event -> {

            ArrayList<Integer> nameIndicators = new ArrayList<>();
            ArrayList<Integer> descIndicators = new ArrayList<>();
            ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
            ArrayList<String> desc = controlPanelController.processTextLines(ParamType.Description, descIndicators);

            editFormController.editDataFromCriterion(aliasTF.getText(), name, nameIndicators, desc, descIndicators, criterionTable, controlPanelController.isExist(), id);
            clearPanel(tableView);
        });
    }

    /**
     * Metoda pro smazani vyberu v tabulce
     *
     * @param tableView instace tridy TableView
     */
    public void clearPanel(TableView tableView) {
        tableView.getSelectionModel().clearSelection();
        tableView.refresh();
    }
}
