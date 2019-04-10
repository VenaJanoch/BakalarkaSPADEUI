package graphics.controlPanelItems;

import abstractControlPane.ControlPanel;
import controllers.ControlPanelController;
import controllers.ControlPanelLineController;
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

public class ControlPanelLine {

    private ComboBox<ControlPanelLineObject> paramBox;
    private TextFieldItem textItem;
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

    public ControlPanelLine(ObservableList<ControlPanelLineObject> paramNameList, ControlPanel controlPanel,
                            ControlPanelController controlPanelController, String[] indicators, int lineIndex){
        this.paramBox = new ComboBox(paramNameList);
        this.paramList = paramNameList;
        this.controlPanel = controlPanel;
        this.controlPanelController = controlPanelController;
        this.exitButton = new RadioButton();
        this.comboBoxItem = new ComboBoxItem(this, controlPanel, controlPanelController, null, null);
        this.checkComboBoxItem = new CheckComboBoxItem(this, controlPanel, controlPanelController, null, null);
        this.radioButtonItem = new RadioButtonItem(this, controlPanel, controlPanelController, null);
        this.textItem = new TextFieldItem(this, controlPanel, controlPanelController, paramNameList, indicators);
        this.dateItem = new DateItem(this, controlPanel, controlPanelController, paramNameList);
        this.controlPanelLineController = new ControlPanelLineController(this,controlPanel, controlPanelController, paramNameList, lineIndex);
        paramBox.getSelectionModel().select(0);
        controlPanelLineController.setBoxToControlPanel(0);
        this.type = paramNameList.get(0).getType();
        paramBox.getSelectionModel().selectedIndexProperty().addListener(controlPanelLineController.comboBoxListener());
    }

    public ControlPanelLine(ObservableList<ControlPanelLineObject> paramNameList, ControlPanel controlPanel,
                            ControlPanelController controlPanelController, int lineIndex){
        this.paramBox = new ComboBox(paramNameList);
        this.paramList = paramNameList;
        this.controlPanel = controlPanel;
        this.controlPanelController = controlPanelController;
        this.exitButton = new RadioButton();
        this.radioButtonItem = new RadioButtonItem(this, controlPanel, controlPanelController, null);
        this.comboBoxItem = new ComboBoxItem(this, controlPanel, controlPanelController, null, null);
        this.checkComboBoxItem = new CheckComboBoxItem(this, controlPanel, controlPanelController, null, null);
        this.textItem = new TextFieldItem(this, controlPanel, controlPanelController, paramNameList, Constans.textIndicatorList);
        this.dateItem = new DateItem(this, controlPanel, controlPanelController, paramNameList);
        this.controlPanelLineController = new ControlPanelLineController(this,controlPanel, controlPanelController, paramNameList, lineIndex);
        paramBox.getSelectionModel().select(0);
        controlPanelLineController.setBoxToControlPanel(0);
        if (paramNameList.size() !=0 ){
            this.type = paramNameList.get(0).getType();
        }
        paramBox.getSelectionModel().selectedIndexProperty().addListener(controlPanelLineController.comboBoxListener());
    }

    public ControlPanelLine( ControlPanel controlPanel,
                            ControlPanelController controlPanelController, ChangeListener<Number> listener, ObservableList listForBox){

        this.controlPanel = controlPanel;
        this.controlPanelController = controlPanelController;
        this.comboBoxItem = new ComboBoxItem(this, controlPanel, controlPanelController, listForBox, listener, null);
        }



    public void setParamType(int id){
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


    public void createComboBoxItem(ObservableList list) {
        this.comboBoxItem = new ComboBoxItem(this, controlPanel, controlPanelController, list, paramList);
    }

    public void createRelationComboBoxItem(ObservableList relationList, ObservableList workUnitList) {
        this.comboBoxItem = new ComboBoxItem(this, controlPanel, controlPanelController, relationList, paramList);
        this.checkComboBoxItem = new CheckComboBoxItem(this, controlPanel, controlPanelController, workUnitList, paramList);
    }

    public void createCheckComboBoxItem(ObservableList list) {
        this.checkComboBoxItem = new CheckComboBoxItem(this, controlPanel, controlPanelController, list, paramList);
    }

    public ParamType getType() {
        return type;
    }

    public int findTypeIndex(ParamType type){
        int i = 0;
        for(ControlPanelLineObject ob : paramBox.getItems()){

            if (ob.getType() == type){
                return i;
                }
                i++;
        }
        return 0;
    }

    public void setType(ParamType type) {
        this.type = type;
        for(ControlPanelLineObject ob : paramBox.getItems()){

            if (ob.getType() == type){
                controlPanelLineController.setBoxToControlPanel();
            }
        }

    }

    public RadioButton getExitButton() {
        return exitButton;
    }

    public void fillTextLine(String value, int indicator, ParamType type) {
        paramBox.getSelectionModel().select(findTypeIndex(type));
        textItem.setTextToTextField(value);
        textItem.getIndicatorCB().getSelectionModel().select(indicator);
        exitButton.setSelected(true);
    }

    public void fillCheckComboBoxLine(List<Integer> value, int indicator, ParamType type) {
        paramBox.getSelectionModel().select(findTypeIndex(type));
        checkComboBoxItem.selectItemsInComboBox(value);
        checkComboBoxItem.getIndicatorCB().getSelectionModel().select(indicator);
        exitButton.setSelected(true);
    }

    public void fillComboBoxLine(int value, int indicator, ParamType type) {
        paramBox.getSelectionModel().select(findTypeIndex(type));
        comboBoxItem.selectItemInComboBox(value);
        comboBoxItem.getIndicatorCB().getSelectionModel().select(indicator);
        exitButton.setSelected(true);
    }

    public void fillDateLine(LocalDate value, int indicator, ParamType type) {
        paramBox.getSelectionModel().select(findTypeIndex(type));
        dateItem.setDateToPicker(value);
        comboBoxItem.getIndicatorCB().getSelectionModel().select(indicator);
        exitButton.setSelected(true);
    }


    public void setCount(Integer value) {
        textItem.setTextToTextField(value.toString());
    }

    public void fillRelationComboBoxLine(int value, ArrayList<Integer> workUnit, ParamType type) {
        paramBox.getSelectionModel().select(findTypeIndex(type));
        comboBoxItem.selectItemInComboBox(value);
        checkComboBoxItem.selectItemsInComboBox(workUnit);
        exitButton.setSelected(true);
    }

    public void unlockComboBox() {
        comboBoxItem.getItemCB().setDisable(true);
    }

    public void lockComboBox() {
        comboBoxItem.getItemCB().setDisable(false);
    }
}
