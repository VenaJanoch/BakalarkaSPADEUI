package graphics;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.time.LocalDate;

public class DateItem extends HBox {

    private Label itemNameLB;
    private DatePicker itemDate;

    private boolean isShowItem;
    private Button itemButton;

    private int itemIndex;

    public DateItem(String name){
        super();

        itemNameLB = new Label(name);
        itemDate = new DatePicker();
        itemDate.setVisible(false);
        setExitButtonsActions();

        this.getChildren().addAll(itemButton, itemNameLB, itemDate);

    }

    private void setExitButtonsActions(){
        isShowItem = false;
        itemButton = new Button("+");
        itemButton.setOnAction(event -> addButtonAction());

    }

    public void addButtonAction(){

        if (!isShowItem){
            itemDate.setVisible(true);
            isShowItem = true;
            itemButton.setText("-");
        }else{
            itemDate.setVisible(false);
            itemDate.setValue(null);
            isShowItem = false;
            itemButton.setText("+");
        }
    }

    public void setShowItem(boolean showItem) {
        isShowItem = !showItem;
        addButtonAction();
    }
    public void setItemNameLB(String nameLB){
        itemNameLB.setText(nameLB);
    }

    public LocalDate getDateFromDatePicker(){
        return itemDate.getValue();
    }

    public boolean isShowItem() {
        return isShowItem;
    }

    public Button getItemButton() {
        return itemButton;
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public void setDateToPicker(LocalDate date) {
        itemDate.setValue(date);
    }

    public Label getItemNameLB() {
        return itemNameLB;
    }

    public DatePicker getItemDate() {
        return itemDate;
    }
}
