package graphics;

import controllers.ItemBoxController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.controlsfx.control.CheckComboBox;
import services.Constans;
import tables.BasicTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckComboBoxItem extends ItemBox {

    private Label itemNameLB;
    private CheckComboBox<BasicTable> itemCB;
    private ItemBoxController itemBoxController;

    public CheckComboBoxItem(String name, ObservableList list){
        super(FXCollections.observableList(Arrays.asList(Constans.indicatorList)));

        itemBoxController = new ItemBoxController();
        itemNameLB = new Label(name);
        itemCB = new CheckComboBox<>(list);
        itemCB.setVisible(false);

        itemCB.getCheckModel().getCheckedItems().addListener(itemBoxController.CheckBoxListener(itemCB));

        setExitButtonsActions(itemCB);

        this.getChildren().addAll(itemButton, itemNameLB, itemCB);

    }


    public void selectItemsInComboBox(List<Integer> list){

        for(int i : list){
            itemCB.getCheckModel().check(i);
        }
    }



    public Label getItemNameLB() {
        return itemNameLB;
    }

    public CheckComboBox<BasicTable> getItemCB() {
        return itemCB;
    }

    public ObservableList<Integer> getChoosedIndicies() {
        return itemBoxController.getChoosedIndicies();
    }


    public boolean isShowItem() {
        return isShowItem;
    }

    public Button getItemButton() {
        return itemButton;
    }

}
