package controlPanels;

import abstractControlPane.DateDescControlPanel;
import controllers.formControllers.FormController;
import graphics.controlPanelItems.ControlPanelLine;
import interfaces.IControlPanel;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.TableView;
import services.*;
import tables.BasicTable;
import tables.ConfigTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Trida predstavujici editacni panel pro element Configuration
 *
 * @author Vaclav Janoch
 */
public class ConfigurationControlPanel extends DateDescControlPanel implements IControlPanel {

    /**
     * Globální proměnné třídy
     */
    private int configId;
    private ConfigTable configTable;

    /**
     * Konstruktor tridy, zinicializuje globalni promenne tridy
     * Je zde rozsiren seznam poznych typu panelu pro dany element
     *
     * @param buttonName         textovy retezec pro potvrzovaci tlacitko
     * @param formDataController instace tridy FormDataController pro ziskani dat z datoveho modelu
     * @param editFormController instace tridy EditDataController pro predani novych dat
     * @param formController     instace tridy FormController
     */
    public ConfigurationControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController,
                                     FormController formController, ConfigTable configTable, int configId, int configIndex) {
        super(buttonName, formDataController, editFormController, formController);

        this.configId = configId;
        this.configTable = configTable;
        SegmentLists segmentLists = formController.getSegmentLists();
        lineList.add(new ControlPanelLineObject("CPRs: ", ControlPanelLineType.CheckBox, ParamType.CPR, segmentLists.getCPRObservable()));
        lineList.add(new ControlPanelLineObject("Changes: ", ControlPanelLineType.CheckBox, ParamType.Change, segmentLists.getChangeObservable()));
        lineList.add(new ControlPanelLineObject("VCS Tag: ", ControlPanelLineType.ComboBox, ParamType.VCSTag, segmentLists.getVCSTag()));
        lineList.add(new ControlPanelLineObject("Branches: ", ControlPanelLineType.CheckBox, ParamType.Branch, segmentLists.getBranchObservable()));

        this.addItemsToControlPanel();
    }

    /**
     * Metoda volajici kontroler ControlPanelController pro vygenerovani noveho radku
     * Pripadne rozsireni o staticke objekty
     */
    protected void addItemsToControlPanel() {


        controlPanelController.setCountLine(this, 2, new ControlPanelLine(lineList, this, controlPanelController, Constans.numberIndicatorList, controlPanelController.getLineCount()));
        controlPanelController.createNewLineWithExist(this, lineList);

        button.setOnAction(event -> {

            ArrayList<Integer> nameIndicators = new ArrayList<>();
            ArrayList<Integer> descriptionIndicators = new ArrayList<>();
            ArrayList<Integer> dateIndicators = new ArrayList<>();
            ArrayList<Integer> tagIndicator = new ArrayList<>();

            ArrayList<Integer> cprsIndicators = new ArrayList<>();
            ArrayList<Integer> branchIndicators = new ArrayList<>();

            ArrayList<Integer> changeIndicators = new ArrayList<>();

            ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
            ArrayList<String> description = controlPanelController.processTextLines(ParamType.Description, descriptionIndicators);
            ArrayList<Integer> tag = controlPanelController.processComboBoxLines(ParamType.VCSTag, tagIndicator);
            ArrayList<LocalDate> dates = controlPanelController.processDateLines(ParamType.Date, dateIndicators);
            ArrayList<ArrayList<Integer>> cprs = controlPanelController.processCheckComboBoxLines(ParamType.CPR, cprsIndicators);
            ArrayList<ArrayList<Integer>> changes = controlPanelController.processCheckComboBoxLines(ParamType.Change, changeIndicators);
            ArrayList<ArrayList<Integer>> branches = controlPanelController.processCheckComboBoxLines(ParamType.Branch, branchIndicators);
            String instanceCount = controlPanelController.getInstanceCount();
            int countIndicator = controlPanelController.getInstanceCountIndicator();

            editFormController.editDataFromConfiguration(aliasTF.getText(), name, description, dates, false, tag, cprs, branches, branchIndicators, changes, cprsIndicators,
                    nameIndicators, descriptionIndicators, dateIndicators, tagIndicator, changeIndicators, instanceCount, countIndicator, controlPanelController.isExist(), configId);
        });

    }

    /**
     * Metoda pro zobrazeni postraniho editacniho panelu
     * Nejprve jsou ziskana data z datoveho modelu
     * nasledne pomoci kontroleru ControlPanelController pridana do panelu
     */
    @Override
    public void showEditControlPanel() {

        List[] configData = formDataController.getConfigurationStringData(configId);

        controlPanelController.resetPanel(controlPane);
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList, ParamType.Name, configData, configData[3], 0);
        controlPanelController.setValueDatePicker(this, lineList, ParamType.Date, (ArrayList<LocalDate>) configData[1], configData[4]);
        controlPanelController.setValueComboBox(this, lineList, ParamType.VCSTag, (ArrayList<Integer>) configData[2], configData[10]);

        ArrayList<ArrayList<Integer>> cprList = formDataController.getCPRFromConfiguration(configId);
        controlPanelController.setValueCheckComboBox(this, lineList, ParamType.CPR, cprList, configData[6]);

        ArrayList<ArrayList<Integer>> changeList = formDataController.getChangesFromConfiguration(configId);
        controlPanelController.setValueCheckComboBox(this, lineList, ParamType.Change, changeList, configData[8]);

        ArrayList<ArrayList<Integer>> branchList = formDataController.getBranchesFromConfiguration(configId);
        controlPanelController.setValueCheckComboBox(this, lineList, ParamType.Branch, branchList, configData[7]);


        List boolList = configData[9];
        boolean exist = (boolean) boolList.get(0);

        controlPanelController.setValueExistRadioButton(exist);
        controlPanelController.setCountToCountLine((int) boolList.get(1), (int) boolList.get(2));
        controlPanelController.setAlias((String) boolList.get(3), this);
    }

    @Override
    protected void showEditControlPanel(BasicTable basicTable, TableView tableView) {

    }
}
