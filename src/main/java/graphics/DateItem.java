package graphics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import services.Constans;

import java.time.LocalDate;
import java.util.Arrays;

public class DateItem extends ItemBox {

    private Label itemNameLB;
    private DatePicker itemDate;

    public DateItem(String name){
        super(FXCollections.observableList(Arrays.asList(Constans.indicatorList)));

        itemNameLB = new Label(name);
        itemDate = new DatePicker();
        itemDate.setVisible(false);
        setExitButtonsActions(itemDate);

        this.getChildren().addAll(itemButton, itemNameLB, indicatorCB, itemDate);

    }


    public void setItemNameLB(String nameLB){
        itemNameLB.setText(nameLB);
    }

    public LocalDate getDateFromDatePicker(){
        return itemDate.getValue();
    }

    public Button getItemButton() {
        return itemButton;
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
