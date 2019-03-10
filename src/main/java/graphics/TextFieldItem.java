package graphics;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import tables.BasicTable;

public class TextFieldItem extends HBox {

    private Label itemNameLB;
    private TextField itemTF;

    private boolean isShowItem;
    private Button itemButton;

    private int itemIndex;

    public TextFieldItem(String name){
        super();

        itemNameLB = new Label(name);
        itemTF = new TextField();
        itemTF.setVisible(false);
        setExitButtonsActions();

        this.getChildren().addAll(itemButton, itemNameLB, itemTF);

    }

    private void setExitButtonsActions(){
        isShowItem = false;
        itemButton = new Button("+");
        itemButton.setOnAction(event -> addButtonAction());

    }

    private void addButtonAction(){
        if (!isShowItem){
            itemTF.setVisible(true);
            isShowItem = true;
            itemButton.setText("-");
        }else{
            itemTF.setVisible(false);
            itemTF.setText(null);
            isShowItem = false;
            itemButton.setText("+");
        }
    }

    public String getTextFromTextField(){
        return itemTF.getText();
    }

    public boolean isShowItem() {
        return isShowItem;
    }

    public void setShowItem(boolean showItem) {
        isShowItem = !showItem;
        addButtonAction();
    }

    public Button getItemButton() {
        return itemButton;
    }

    public int getItemIndex() {
        return itemIndex;
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
