package graphics;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import services.Constans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ItemBox extends HBox {

    protected ComboBox<String> indicatorCB;
    protected boolean isShowItem;
    protected Button itemButton;

    private int indicatorIndex;

    public ItemBox(ObservableList<String> indicatorSymbols){
        indicatorCB = new ComboBox<>(indicatorSymbols);
        indicatorCB.getSelectionModel().selectedIndexProperty().addListener(itemListener);
        indicatorCB.getSelectionModel().select(Constans.indicatorIndex);
        indicatorCB.setVisible(false);
    }

    protected void setExitButtonsActions(Node node){
        isShowItem = false;
        itemButton = new Button("+");
        itemButton.setOnAction(event -> addButtonAction(node));

    }

    public void addButtonAction(Node node){

        if (!isShowItem){
            node.setVisible(true);
            indicatorCB.setVisible(true);
            isShowItem = true;
            itemButton.setText("-");
        }else{
            node.setVisible(false);
            indicatorCB.setVisible(false);
            isShowItem = false;
            itemButton.setText("+");
        }
    }

    public void setShowItem(Node node, boolean showItem) {
        isShowItem = !showItem;
        addButtonAction(node);
    }

    public boolean isShowItem() {
        return isShowItem;
    }

    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Priority
     */
    ChangeListener<Number> itemListener = new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            indicatorIndex = newValue.intValue();

        }
    };

    public int getIndicatorIndex() {
        return indicatorIndex;
    }

    public ComboBox<String> getIndicatorCB() {
        return indicatorCB;
    }
}


