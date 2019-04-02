package graphics;

import abstractControlPane.ControlPanel;
import controllers.ControlPanelController;
import controllers.ItemBoxController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.controlsfx.control.PropertySheet;
import services.Constans;
import services.ControlPanelLineObject;
import services.SegmentLists;
import tables.BasicTable;
import tables.CommitedConfigurationTable;

import java.util.Arrays;

public class ComboBoxItem extends ItemBox {

     private ComboBox itemCB;

     private ComboBoxItem otherComboBoxItem;

     private ObservableList<String> listForBox;

    public ComboBoxItem(ControlPanelLine controlPanelLine, ControlPanel controlPanel, ControlPanelController controlPanelController,
                        ObservableList listForBox, ObservableList<ControlPanelLineObject> lineList){
        super(FXCollections.observableList(Arrays.asList(Constans.textIndicatorList)), controlPanelController);

        this.listForBox = listForBox;

        itemBoxController = new ItemBoxController();

        itemCB = new ComboBox<BasicTable>();
        itemCB.getSelectionModel().selectedIndexProperty().addListener(itemBoxController.comboBoxListener());
        itemCB.setVisibleRowCount(5);
        itemCB.setItems(listForBox);

        setExitButtonsActions(controlPanelLine, controlPanel, lineList);

        this.getChildren().addAll(itemButton, indicatorCB, itemCB);

    }

    public ComboBoxItem(ControlPanelLine controlPanelLine, ControlPanel controlPanel, ControlPanelController controlPanelController,
                        ObservableList listForBox, ChangeListener<Number> listener, ObservableList<ControlPanelLineObject> lineList ) {
        super(FXCollections.observableList(Arrays.asList(Constans.textIndicatorList)), controlPanelController);

        itemCB = new ComboBox<String>();
        itemCB.setVisibleRowCount(5);
        itemCB.getSelectionModel().selectedIndexProperty().addListener(listener);
        itemCB.setItems(listForBox);

        setExitButtonsActions(controlPanelLine, controlPanel, lineList);

        this.getChildren().addAll(itemButton, itemCB);

    }

    public ComboBoxItem(ControlPanelLine controlPanelLine, ControlPanel controlPanel, ControlPanelController controlPanelController,
                        ObservableList listForBox, ComboBoxItem comboBoxItem, ChangeListener<Number> indexListener, ObservableList<ControlPanelLineObject> lineList) {
        this(controlPanelLine, controlPanel,controlPanelController, listForBox, lineList);
        otherComboBoxItem = comboBoxItem;
        itemButton.setOnAction(event -> addButtonAction(otherComboBoxItem));
    }


    public void addButtonAction(ComboBoxItem comboBoxItem){
        addButtonAction(itemCB);
        if (!isShowItem){
            comboBoxItem.setShowItem(comboBoxItem.itemCB, false);
        }else{
            comboBoxItem.setShowItem(comboBoxItem.itemCB, true);
        }

    }

    public void selectItemInComboBox(int index){
        itemCB.getSelectionModel().select(index);
    }

    public void selectItemInComboBox(String  value){
        itemCB.getSelectionModel().select(value);
    }

    public ComboBox<BasicTable> getItemCB() {
        return itemCB;
    }

    public Button getItemButton() {
        return itemButton;
    }

    public int getItemIndex() {
        return itemBoxController.getItemIndex() ;
    }
}
