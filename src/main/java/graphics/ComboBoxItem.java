package graphics;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import services.SegmentLists;
import tables.BasicTable;

public class ComboBoxItem extends HBox {

    private Label itemNameLB;
    private ComboBox itemCB;

    private boolean isShowItem;
    private Button itemButton;

    private int itemIndex;

    public ComboBoxItem(String name, ObservableList list){
        super();

        itemNameLB = new Label(name);
        itemCB = new ComboBox<BasicTable>();
        itemCB.getSelectionModel().selectedIndexProperty().addListener(priorityListener);
        itemCB.setVisibleRowCount(5);
        itemCB.setVisible(false);
        itemCB.setItems(list);

        setExitButtonsActions();

        this.getChildren().addAll(itemButton, itemNameLB, itemCB);

    }

    public ComboBoxItem(String name, ObservableList list, boolean isBasicTable, ChangeListener<Number> listener ) {
        super();

        itemNameLB = new Label(name);
        itemCB = new ComboBox<String>();
        itemCB.setVisibleRowCount(5);
        itemCB.getSelectionModel().selectedIndexProperty().addListener(listener);
        itemCB.setItems(list);

        setExitButtonsActions();

        this.getChildren().addAll(itemButton, itemNameLB, itemCB);

    }


    private void setExitButtonsActions(){
        isShowItem = false;
        itemButton = new Button("+");
        itemButton.setOnAction(event -> addButtonAction());

    }

    public void selectItemInComboBox(int index){
        itemCB.getSelectionModel().select(index);
    }

    public void selectItemInComboBox(String  value){
        itemCB.getSelectionModel().select(value);
    }
    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Priority
     */
    ChangeListener<Number> priorityListener = new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            itemIndex = newValue.intValue();

        }
    };

    public ComboBox<BasicTable> getItemCB() {
        return itemCB;
    }

    public boolean isShowItem() {
        return isShowItem;
    }

    public Button getItemButton() {
        return itemButton;
    }

    public Label getItemNameLB() {
        return itemNameLB;
    }

    public void addButtonAction(){
        if (!isShowItem){
            itemCB.setVisible(true);
            isShowItem = true;
            itemButton.setText("-");
        }else{
            itemCB.setVisible(false);
            itemCB.getSelectionModel().clearSelection();
            isShowItem = false;
            itemButton.setText("+");
        }
    }

    public void setShowItem(boolean showItem) {
        isShowItem = !showItem;
        addButtonAction();
    }


    public int getItemIndex() {
        return itemIndex;
    }
}
