package graphics;

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
import services.SegmentLists;
import tables.BasicTable;
import tables.CommitedConfigurationTable;

import java.util.Arrays;

public class ComboBoxItem extends ItemBox {

    private Label itemNameLB;
    private ComboBox itemCB;

    private ComboBoxItem otherComboBoxItem;
    private ItemBoxController itemBoxController;

    public ComboBoxItem(String name, ObservableList list){
        super(FXCollections.observableList(Arrays.asList(Constans.indicatorList)));

        itemBoxController = new ItemBoxController();

        itemNameLB = new Label(name);
        itemCB = new ComboBox<BasicTable>();
        itemCB.getSelectionModel().selectedIndexProperty().addListener(itemBoxController.comboBoxListener());
        itemCB.setVisibleRowCount(5);
        itemCB.setVisible(false);
        itemCB.setItems(list);

        setExitButtonsActions(itemCB);

        this.getChildren().addAll(itemButton, itemNameLB, indicatorCB, itemCB);

    }

    public ComboBoxItem(String name, ObservableList list, boolean isBasicTable, ChangeListener<Number> listener ) {
        super(FXCollections.observableList(Arrays.asList(Constans.indicatorList)));

        itemNameLB = new Label(name);
        itemCB = new ComboBox<String>();
        itemCB.setVisibleRowCount(5);
        itemCB.getSelectionModel().selectedIndexProperty().addListener(listener);
        itemCB.setItems(list);

        setExitButtonsActions(itemCB);

        this.getChildren().addAll(itemButton, itemNameLB, itemCB);

    }

    public ComboBoxItem(String name, ObservableList list, ComboBoxItem comboBoxItem, ChangeListener<Number> indexListener) {
        this(name, list);
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

    public Label getItemNameLB() {
        return itemNameLB;
    }

    public int getItemIndex() {
        return itemBoxController.getItemIndex() ;
    }
}
