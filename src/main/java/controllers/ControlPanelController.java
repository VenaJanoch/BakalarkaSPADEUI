package controllers;

import graphics.CheckComboBoxItem;
import graphics.ComboBoxItem;
import graphics.DateItem;
import graphics.TextFieldItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.CheckComboBox;
import services.Constans;
import tables.BasicTable;

import java.time.LocalDate;
import java.util.List;

public class ControlPanelController {

    private boolean isMain;


    public String checkValueFromTextItem(TextFieldItem textFieldItem){
        String desc = "null*";
        if (textFieldItem.isShowItem() == true){
            desc = textFieldItem.getTextFromTextField();
        }
        return desc;
    }

    public LocalDate checkValueFromDateItem(DateItem dateItem){
        LocalDate date = Constans.nullDate;
        if(dateItem.isShowItem() == true){
            date = dateItem.getDateFromDatePicker();
        }
        return date;
    }


    public void setValueTextField(TextFieldItem item, String[] value, int index) {

        item.setShowItem(item.getItemTF(), false);
        if (value[index] != null) {
            item.setTextToTextField(value[index]);
            item.setShowItem(item.getItemTF(), true);
        }

    }

    public void setValueComboBox(ComboBoxItem item, String[] value, int index) {

        item.setShowItem(item.getItemCB(), false);
        if (value[index] != null) {
            item.selectItemInComboBox(Integer.parseInt(value[index]));
            item.setShowItem(item.getItemCB(), true);
        }

    }

    public void setValueDatePicker(DateItem item, String[] value, int index) {

        item.setShowItem(item.getItemDate(), false);
        if (value[index] != null) {
            item.setDateToPicker(LocalDate.parse(value[index]));
            item.setShowItem(item.getItemDate(), false);
        }

    }

    public void setValueCheckComboBox(CheckComboBoxItem item, List<Integer> indexList) {
        item.setShowItem(item.getItemCB(), false);

        if (indexList.size() != 0) {
            item.selectItemsInComboBox(indexList);
            item.setShowItem(item.getItemCB(), true);
        }
    }


    public void setComboBoxItemToControlPanel(GridPane controlPane, ComboBoxItem item, int startColomIndex, int rowIndex) {
        controlPane.add(item.getItemButton(), startColomIndex, rowIndex);
        controlPane.add(item.getItemNameLB(), startColomIndex + 1, rowIndex);
        controlPane.setHalignment(item.getItemNameLB(), HPos.LEFT);
        controlPane.add(item.getIndicatorCB(), startColomIndex + 2, rowIndex);
        controlPane.add(item.getItemCB(), startColomIndex + 3, rowIndex);
    }

    public void setDateItemToControlPanel(GridPane controlPane, DateItem item, int startColomIndex, int rowIndex) {
        controlPane.add(item.getItemButton(), startColomIndex, rowIndex);
        controlPane.add(item.getItemNameLB(), startColomIndex + 1, rowIndex);
        controlPane.setHalignment(item.getItemNameLB(), HPos.LEFT);
        controlPane.add(item.getIndicatorCB(), startColomIndex + 2, rowIndex);
        controlPane.add(item.getItemDate(), startColomIndex + 3, rowIndex);
    }

    public void setTextItemToControlPanel(GridPane controlPane, TextFieldItem item, int startColomIndex, int rowIndex) {
        controlPane.add(item.getItemButton(), startColomIndex, rowIndex);
        controlPane.add(item.getItemNameLB(), startColomIndex + 1, rowIndex);
        controlPane.setHalignment(item.getItemNameLB(), HPos.LEFT);
        controlPane.add(item.getIndicatorCB(), startColomIndex + 2, rowIndex);
        controlPane.add(item.getItemTF(), startColomIndex + 3, rowIndex);
    }

    public void setCheckComboBoxItemToControlPanel(GridPane controlPane, CheckComboBoxItem item, int startColomIndex, int rowIndex) {
        controlPane.add(item.getItemButton(), startColomIndex, rowIndex);
        controlPane.add(item.getItemNameLB(), startColomIndex + 1, rowIndex);
        controlPane.setHalignment(item.getItemNameLB(), HPos.LEFT);
        controlPane.add(item.getIndicatorCB(), startColomIndex + 2, rowIndex);
        controlPane.add(item.getItemCB(), startColomIndex + 3, rowIndex);
    }

    public ChangeListener<Toggle> radioButtonListener() {
        ChangeListener<Toggle> listener = new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                RadioButton chk = (RadioButton) newValue.getToggleGroup().getSelectedToggle();

                if (chk.getText().contains("Yes")) {
                    isMain = true;
                } else {
                    isMain = false;
                }

            }
        };
        return listener;
    }


    public boolean isMain() {
        return isMain;
    }
}

