package graphics;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import services.Constans;
import tables.BasicTable;

import java.util.Arrays;

public class TextFieldItem extends ItemBox {

    private Label itemNameLB;
    private TextField itemTF;

    public TextFieldItem(String name){
        super(FXCollections.observableList(Arrays.asList(Constans.indicatorList)));

        itemNameLB = new Label(name);
        itemTF = new TextField();
        itemTF.setMaxWidth(60);
        itemTF.setVisible(false);
        setExitButtonsActions(itemTF);

        this.getChildren().addAll(itemButton, itemNameLB, indicatorCB, itemTF);

    }

    public String getTextFromTextField(){
        return itemTF.getText();
    }

    public Button getItemButton() {
        return itemButton;
    }

    public void setTextToTextField(String text) {
        itemTF.setText(text);
    }

    public Label getItemNameLB() {
        return itemNameLB;
    }

    public TextField getItemTF() {
        return itemTF;
    }
}
