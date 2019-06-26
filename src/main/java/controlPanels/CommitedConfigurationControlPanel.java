package controlPanels;

import abstractControlPane.DateDescControlPanel;
import controllers.formControllers.FormController;
import graphics.controlPanelItems.ControlPanelLine;
import interfaces.IControlPanel;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import services.Constans;
import services.ControlPanelLineObject;
import services.ControlPanelLineType;
import services.ParamType;
import tables.BasicTable;
import tables.CommitedConfigurationTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Trida predstavujici editacni panel pro element Committed Configuration
 *
 * @author Vaclav Janoch
 */
public class CommitedConfigurationControlPanel extends DateDescControlPanel implements IControlPanel {

    /**
     * Globální proměnné třídy
     */


    private CommitedConfigurationTable commitedConfigurationTable;

    private int commitedConfigurationId;
    private int commitedConfigurationFormId;

    /**
     * Konstruktor tridy, zinicializuje globalni promenne tridy
     * Je zde rozsiren seznam poznych typu panelu pro dany element
     *
     * @param buttonName         textovy retezec pro potvrzovaci tlacitko
     * @param formDataController instace tridy FormDataController pro ziskani dat z datoveho modelu
     * @param editFormController instace tridy EditDataController pro predani novych dat
     * @param formController     instace tridy FormController
     */
    public CommitedConfigurationControlPanel(String buttonName, IFormDataController formDataController,
                                             IEditFormController editFormController, FormController formController, CommitedConfigurationTable branchTable, int id, int formIndex) {
        super(buttonName, formDataController, editFormController, formController);

        lineList.add(new ControlPanelLineObject("Committed: ", ControlPanelLineType.Date, ParamType.EndDate));

        addItemsToControlPanel();

        this.commitedConfigurationFormId = formIndex;
        this.commitedConfigurationId = id;
        this.commitedConfigurationTable = branchTable;

    }

    /**
     * Metoda pro zobrazeni postraniho editacniho panelu
     * Nejprve jsou ziskana data z datoveho modelu
     * nasledne pomoci kontroleru ControlPanelController pridana do panelu
     */
    @Override
    public void showEditControlPanel() {
        List[] commitedData = formDataController.getCommitedConfigurationStringData(commitedConfigurationId);

        controlPanelController.resetPanel(controlPane);
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList, ParamType.Name, commitedData, commitedData[1], 0);
        controlPanelController.setValueTextField(this, lineList, ParamType.Description, commitedData, commitedData[8], 7);
        controlPanelController.setValueDatePicker(this, lineList, ParamType.EndDate, (ArrayList<LocalDate>) commitedData[2], commitedData[3]);
        controlPanelController.setValueDatePicker(this, lineList, ParamType.Date, (ArrayList<LocalDate>) commitedData[5], commitedData[6]);

        List boolList = commitedData[4];
        controlPanelController.setCountToCountLine((int) boolList.get(1), (int) boolList.get(2));
        boolean exist = (boolean) boolList.get(0);
        controlPanelController.setValueExistRadioButton(exist);
        controlPanelController.setAlias((String) boolList.get(3), this);
    }

    /**
     * Metoda volajici kontroler ControlPanelController pro vygenerovani noveho radku
     * Pripadne rozsireni o staticke objekty
     */
    protected void addItemsToControlPanel() {

        controlPanelController.setCountLine(this, 2, new ControlPanelLine(lineList, this, controlPanelController, Constans.numberIndicatorList, controlPanelController.getLineCount()));
        controlPanelController.createNewLineWithExist(this, lineList);

        button.setOnAction(event -> saveDataFromPanel());

    }

    /**
     * Metoda pro ziskani dat z grafickych komponent a predani dat do editacniho kontroleru EditFormController
     */
    public void saveDataFromPanel() {

        ArrayList<Integer> nameIndicators = new ArrayList<>();
        ArrayList<Integer> dateIndicators = new ArrayList<>();
        ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
        ArrayList<LocalDate> date = controlPanelController.processDateLines(ParamType.EndDate, dateIndicators);
        ArrayList<Integer> descriptionIndicators = new ArrayList<>();
        ArrayList<Integer> createdIndicators = new ArrayList<>();
        ArrayList<String> description = controlPanelController.processTextLines(ParamType.Description, descriptionIndicators);
        ArrayList<LocalDate> created = controlPanelController.processDateLines(ParamType.Date, createdIndicators);


        String count = controlPanelController.getInstanceCount();
        int countIndicator = controlPanelController.getInstanceCountIndicator();
        editFormController.editDataFromCommitedConfiguration(aliasTF.getText(), name, nameIndicators, description, descriptionIndicators, created,
                createdIndicators, date, dateIndicators, count, countIndicator, controlPanelController.isExist(), commitedConfigurationId);

    }


    public Button getButton() {
        return button;
    }

    @Override
    protected void showEditControlPanel(BasicTable basicTable, TableView tableView) {

    }
}
