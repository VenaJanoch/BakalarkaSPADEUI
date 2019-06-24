package graphics.controlPanelItems;

import abstractControlPane.ControlPanel;
import controllers.graphicsComponentsControllers.ControlPanelController;
import controllers.graphicsComponentsControllers.ControlPanelLineController;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import services.Constans;
import services.ControlPanelLineObject;
import services.ParamType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída predstavujici jeden radek editacniho panelu
 *
 * @author Václav Janoch
 */
public class ControlPanelLine {

    /**Globalni promenne tridy**/
    private ComboBox<ControlPanelLineObject> paramBox;
    private TextFieldItem textItem;
    private TextFieldItem numberItem;
    private DateItem dateItem;
    private ComboBoxItem comboBoxItem;
    private CheckComboBoxItem checkComboBoxItem;
    private RadioButtonItem radioButtonItem;
    private ControlPanelLineController controlPanelLineController;
    private ControlPanel controlPanel;
    private ControlPanelController controlPanelController;
    private ObservableList<ControlPanelLineObject> paramList;
    private RadioButton exitButton;
    private ParamType type;

    /**
     * Konstruktor tridy,
     * Zinicializuje globalni promnenne tridy
     * @param paramNameList seznam parametru pouzitelnych v radku
     * @param controlPanel instace tridy ControlPanel
     * @param controlPanelController instace tridy ControlPanelController
     * @param indicators seznam indikatoru urcenych pro radek
     * @param lineIndex pozice radku v panelu
     */
    public ControlPanelLine(ObservableList<ControlPanelLineObject> paramNameList, ControlPanel controlPanel,
                            ControlPanelController controlPanelController, String[] indicators, int lineIndex) {
        this.paramBox = new ComboBox(paramNameList);
        this.paramList = paramNameList;
        this.controlPanel = controlPanel;
        this.controlPanelController = controlPanelController;
        this.exitButton = new RadioButton();
        this.comboBoxItem = new ComboBoxItem(this, controlPanel, controlPanelController, null, null);
        this.checkComboBoxItem = new CheckComboBoxItem(this, controlPanel, controlPanelController, null, null);
        this.radioButtonItem = new RadioButtonItem(this, controlPanel, controlPanelController, null);
        this.textItem = new TextFieldItem(this, controlPanel, controlPanelController, paramNameList, indicators);
        this.numberItem = new TextFieldItem(this, controlPanel, controlPanelController, paramNameList, indicators);
        this.dateItem = new DateItem(this, controlPanel, controlPanelController, paramNameList);
        this.controlPanelLineController = new ControlPanelLineController(this, controlPanel, controlPanelController, paramNameList, lineIndex);
        paramBox.getSelectionModel().select(0);
        controlPanelLineController.setBoxToControlPanel(0);
        this.type = paramNameList.get(0).getType();
        paramBox.getSelectionModel().selectedIndexProperty().addListener(controlPanelLineController.comboBoxListener());
    }

    /**
     * Konstruktor tridy,
     * Zinicializuje globalni promnenne tridy
     * @param paramNameList seznam parametru pouzitelnych v radku
     * @param controlPanel instace tridy ControlPanel
     * @param controlPanelController instace tridy ControlPanelController
     * @param lineIndex pozice radku v panelu
     */
    public ControlPanelLine(ObservableList<ControlPanelLineObject> paramNameList, ControlPanel controlPanel,
                            ControlPanelController controlPanelController, int lineIndex) {
        this.paramBox = new ComboBox(paramNameList);
        this.paramList = paramNameList;
        this.controlPanel = controlPanel;
        this.controlPanelController = controlPanelController;
        this.exitButton = new RadioButton();
        this.radioButtonItem = new RadioButtonItem(this, controlPanel, controlPanelController, null);
        this.comboBoxItem = new ComboBoxItem(this, controlPanel, controlPanelController, null, null);
        this.checkComboBoxItem = new CheckComboBoxItem(this, controlPanel, controlPanelController, null, null);
        this.textItem = new TextFieldItem(this, controlPanel, controlPanelController, paramNameList, Constans.textIndicatorList);
        this.numberItem = new TextFieldItem(this, controlPanel, controlPanelController, paramNameList, Constans.numberIndicatorList);
        this.dateItem = new DateItem(this, controlPanel, controlPanelController, paramNameList);
        this.controlPanelLineController = new ControlPanelLineController(this, controlPanel, controlPanelController, paramNameList, lineIndex);
        paramBox.getSelectionModel().select(0);
        controlPanelLineController.setBoxToControlPanel(0);
        if (paramNameList.size() != 0) {
            this.type = paramNameList.get(0).getType();
        }
        paramBox.getSelectionModel().selectedIndexProperty().addListener(controlPanelLineController.comboBoxListener());
    }

    /**
     * Konstruktor tridy,
     * Zinicializuje globalni promnenne tridy
     * @param controlPanel instace tridy ControlPanel
     * @param controlPanelController instace tridy ControlPanelController
     * @param listener listener pro combobox
     * @param listForBox seznam parametru radku
     */
    public ControlPanelLine(ControlPanel controlPanel,
                            ControlPanelController controlPanelController, ChangeListener<Number> listener, ObservableList listForBox) {

        this.controlPanel = controlPanel;
        this.controlPanelController = controlPanelController;
        this.comboBoxItem = new ComboBoxItem(this, controlPanel, controlPanelController, listForBox, listener, null);
    }


    /**
     * Metoda pro vytvoreni ComboBoxItem v radku
     * @param list seznam hodnot pro ComboBox
     */
    public void createComboBoxItem(ObservableList list) {
        this.comboBoxItem = new ComboBoxItem(this, controlPanel, controlPanelController, list, paramList);
    }

    /**
     * Metoda pro vytvoreni relace
     * @param relationList indexi relaci
     * @param workUnitList seznam indexu WorkUnit
     */
    public void createRelationComboBoxItem(ObservableList relationList, ObservableList workUnitList) {
        this.comboBoxItem = new ComboBoxItem(this, controlPanel, controlPanelController, relationList, paramList);
        this.checkComboBoxItem = new CheckComboBoxItem(this, controlPanel, controlPanelController, workUnitList, paramList);
    }

    /**
     * Metoda pro vytvoreni BoxItem v radku
     * @param list seznam hodnot pro ComboBox
     */
    public void createCheckComboBoxItem(ObservableList list) {
        this.checkComboBoxItem = new CheckComboBoxItem(this, controlPanel, controlPanelController, list, paramList);
    }

    public ParamType getType() {
        return type;
    }

    /**
     * Metoda pro ziskani indexu typu radku
     * @param type typ parametru
     * @return index parametru v ComboBoxu
     */
    public int findTypeIndex(ParamType type) {
        int i = 0;
        for (ControlPanelLineObject ob : paramBox.getItems()) {

            if (ob.getType() == type) {
                return i;
            }
            i++;
        }
        return 0;
    }


    /**
     * Metoda pro nastaveni textove hodnoty do radku
     * @param value hodnota pro nastaveni
     * @param indicator index indikatoru
     * @param type typ parametru
     */
    public void fillTextLine(String value, int indicator, ParamType type) {
        paramBox.getSelectionModel().select(findTypeIndex(type));
        textItem.setTextToTextField(value);
        textItem.getIndicatorCB().getSelectionModel().select(indicator);
        exitButton.setSelected(true);
    }

    /**
     * Metoda pro nastaveni ciselne hodnoty do radku
     * @param value hodnota pro nastaveni
     * @param indicator index indikatoru
     * @param type typ parametru
     */
    public void fillNumberLine(String value, int indicator, ParamType type) {
        paramBox.getSelectionModel().select(findTypeIndex(type));
        numberItem.setTextToTextField(value);
        numberItem.getIndicatorCB().getSelectionModel().select(indicator);
        exitButton.setSelected(true);
    }

    /**
     * Metoda por nastaveni dat v CheckComboBoxu
     * @param value indexi pro nastaveni ComboBoxu
     * @param indicator index indikatoru
     * @param type typ parametru
     */
    public void fillCheckComboBoxLine(List<Integer> value, int indicator, ParamType type) {
        paramBox.getSelectionModel().select(findTypeIndex(type));
        checkComboBoxItem.selectItemsInComboBox(value);
        checkComboBoxItem.getIndicatorCB().getSelectionModel().select(indicator);
        exitButton.setSelected(true);
    }

    /**
     * Metoda pro nastaveni dat v ComboBoxu
     * @param value index pro nastaveni
     * @param indicator index indikatoru
     * @param type typ parametru
     */
    public void fillComboBoxLine(int value, int indicator, ParamType type) {
        paramBox.getSelectionModel().select(findTypeIndex(type));
        comboBoxItem.selectItemInComboBox(value);
        comboBoxItem.getIndicatorCB().getSelectionModel().select(indicator);
        exitButton.setSelected(true);
    }

    /**
     * Metoda pro nastaveni datumu do panelu
     * @param value hodnota pro nastaveni
     * @param indicator index indicatoru
     * @param type typ parametru
     */
    public void fillDateLine(LocalDate value, int indicator, ParamType type) {
        paramBox.getSelectionModel().select(findTypeIndex(type));
        dateItem.setDateToPicker(value);
        dateItem.getIndicatorCB().getSelectionModel().select(indicator);
        exitButton.setSelected(true);
    }


    /**
     * Metoda pro nastaveni hodnoty count do editacniho panelu
     * @param value hodnota pro pridani do panelu
     */
    public void setCount(Integer value) {
        textItem.setTextToTextField(value.toString());
    }

    /**
     * Metoda pro naplneni radku s relaci
     * @param value index relace
     * @param workUnit indexi WorkUnitu v relaci
     * @param type typ parametru
     */
    public void fillRelationComboBoxLine(int value, ArrayList<Integer> workUnit, ParamType type) {
        paramBox.getSelectionModel().select(findTypeIndex(type));
        comboBoxItem.selectItemInComboBox(value);
        checkComboBoxItem.selectItemsInComboBox(workUnit);
        exitButton.setSelected(true);
    }

    /**
     * Metoda pro odblokovani boxu
     */
    public void unlockComboBox() {
        comboBoxItem.getItemCB().setDisable(true);
    }

    /**
     * Metoda pro uzamceni boxu
     */
    public void lockComboBox() {
        comboBoxItem.getItemCB().setDisable(false);
    }

    /**Gettrs and Setters**/

    public void setParamType(int id) {
        type = paramList.get(id).getType();
    }

    public RadioButtonItem getRadioButtonItem() {
        return radioButtonItem;
    }

    public boolean isCheckYesRadioButton() {
        return radioButtonItem.isSelectYes();
    }

    public ComboBox getParamBox() {
        return paramBox;
    }

    public TextFieldItem getTextItem() {
        return textItem;
    }
    public TextFieldItem getNumberItem() {
        return numberItem;
    }

    public DateItem getDateItem() {
        return dateItem;
    }

    public ComboBoxItem getComboBoxItem() {
        return comboBoxItem;
    }

    public CheckComboBoxItem getCheckComboBoxItem() {
        return checkComboBoxItem;
    }

    public ControlPanelLineController getControlPanelLineController() {
        return controlPanelLineController;
    }



    public void setType(ParamType type) {
        this.type = type;
        for (ControlPanelLineObject ob : paramBox.getItems()) {

            if (ob.getType() == type) {
                controlPanelLineController.setBoxToControlPanel();
            }
        }

    }

    public RadioButton getExitButton() {
        return exitButton;
    }
}
