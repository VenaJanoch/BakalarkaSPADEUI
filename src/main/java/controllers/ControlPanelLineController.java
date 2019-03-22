package controllers;

import abstractControlPane.ControlPanel;
import graphics.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.CheckComboBox;
import services.ControlPanelLineObject;
import tables.BasicTable;

public class ControlPanelLineController {

    private  ControlPanelController controlPanelController;
    private  ControlPanel controlPanel;
    private ChangeListener<Number> listener;
    private ControlPanelLine line;

    private int itemIndex = 0;
    private ObservableList<ControlPanelLineObject> paramList;
    private boolean isSetType = false;
    public ControlPanelLineController(ControlPanelLine line, ControlPanel controlPanel, ControlPanelController controlPanelController, ObservableList<ControlPanelLineObject> paramList){
        this.controlPanelController = controlPanelController;
        this.controlPanel = controlPanel;
        this.line = line;
        this.paramList = paramList;

    }


    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Priority
     */

    public ChangeListener<Number> comboBoxListener () {

        listener = new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                itemIndex = newValue.intValue();
                ControlPanelLineObject object = paramList.get(itemIndex);
                line.setType(object.getType());

            }
        };
        return listener;
    }

    public void setBoxToControlPanel(){
        ControlPanelLineObject object = paramList.get(itemIndex);
        controlPanelController.removeFromControlPanel(line, controlPanel.getControlPane());

        switch (object.getLineType()){
            case Text:
                controlPanelController.setTextItemToControlPanel(controlPanel.getControlPane(), line.getTextItem(), 2);
                break;
            case Date:
                controlPanelController.setDateItemToControlPanel(controlPanel.getControlPane(), line.getDateItem(), 2);
                break;
            case ComboBox:
                line.createComboBoxItem(object.getSegmentsList());
                controlPanelController.setComboBoxItemToControlPanel(controlPanel.getControlPane(), line.getComboBoxItem(), 2);
                break;
            case RelationComboBoxes:
                line.createRelationComboBoxItem(object.getSegmentsList(), object.getSegmentsList2());
                controlPanelController.setRelationComboBoxItemToControlPanel(controlPanel.getControlPane(), line.getComboBoxItem(), line.getCheckComboBoxItem(), 2);
                break;
            case CheckBox:
                line.createCheckComboBoxItem(object.getSegmentsList());
                controlPanelController.setCheckComboBoxItemToControlPanel(controlPanel.getControlPane(), line.getCheckComboBoxItem(), 2);
                break;
            default:
        }
    }

    public void setBoxToControlPanel(int index){
        itemIndex = index;
        if(paramList.size() != 0){
            setBoxToControlPanel();
        }
    }


    public void copyLine(ControlPanelLine oldLine, int paramIndex) {
        line.getParamBox().getSelectionModel().select(paramIndex);
        setBoxToControlPanel(paramIndex);
        setDataFromLine(oldLine);
    }

    private void setDataFromLine(ControlPanelLine oldLine) {
        ControlPanelLineObject object = paramList.get(itemIndex);
        switch (object.getLineType()){
            case Text:
                setDataToTextFieldLine(oldLine);
                break;
            case Date:
                setDataToDateFieldLine(oldLine);
                break;
            case ComboBox:
                setDataToComboBoxFieldLine(oldLine);
                break;
            case CheckBox:
                setDataToCheckComboBoxFieldLine(oldLine);
                break;
            default:
        }
    }


    private void setDataToCheckComboBoxFieldLine(ControlPanelLine oldLine) {
        CheckComboBoxItem oldItem = oldLine.getCheckComboBoxItem();
        CheckComboBoxItem item = line.getCheckComboBoxItem();

        item.setIndicator(oldItem.getIndicatorIndex());
        item.selectItemsInComboBox(oldItem.getChoosedIndicies());
    }


    private void setDataToComboBoxFieldLine(ControlPanelLine oldLine) {
        ComboBoxItem oldItem = oldLine.getComboBoxItem();
        ComboBoxItem item = line.getComboBoxItem();

        item.setIndicator(oldItem.getIndicatorIndex());
        item.selectItemInComboBox(oldItem.getItemIndex());
    }


    private void setDataToDateFieldLine(ControlPanelLine oldLine) {
        DateItem oldItem = oldLine.getDateItem();
        DateItem item = line.getDateItem();

        item.setIndicator(oldItem.getIndicatorIndex());
        item.setDateToPicker(oldItem.getDateFromDatePicker());
    }

    private void setDataToTextFieldLine(ControlPanelLine oldLine) {

        TextFieldItem oldItem = oldLine.getTextItem();
        TextFieldItem item = line.getTextItem();

        item.setIndicator(oldItem.getIndicatorIndex());
        item.getItemTF().setText(oldItem.getTextFromTextField());

    }
}
