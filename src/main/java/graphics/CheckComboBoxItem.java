package graphics;

import abstractControlPane.ControlPanel;
import controllers.ControlPanelController;
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
import services.ControlPanelLineObject;
import tables.BasicTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckComboBoxItem extends ItemBox {

    private CheckComboBox<BasicTable> itemCB;


    public CheckComboBoxItem(ControlPanelLine controlPanelLine, ControlPanel controlPanel, ControlPanelController controlPanelController,
                             ObservableList listForBox, ObservableList<ControlPanelLineObject> lineList){
        super(FXCollections.observableList(Arrays.asList(Constans.textIndicatorList)), controlPanelController);


        itemCB = new CheckComboBox<>(listForBox);

        itemCB.getCheckModel().getCheckedItems().addListener(itemBoxController.CheckBoxListener(itemCB));

        setExitButtonsActions(controlPanelLine, controlPanel, lineList);

        this.getChildren().addAll(itemButton, itemCB);

    }


    public void selectItemsInComboBox(List<Integer> list){

        for(int i : list){
            itemCB.getCheckModel().check(i);
        }
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
