package graphics;

import abstractControlPane.ControlPanel;
import controllers.ControlPanelController;
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
import services.ControlPanelLineObject;
import tables.BasicTable;

import java.util.Arrays;

public class TextFieldItem extends ItemBox {

    private TextField itemTF;

    public TextFieldItem(ControlPanelLine controlPanelLine, ControlPanel controlPanel, ControlPanelController controlPanelController,
                         ObservableList<ControlPanelLineObject> lineList, String[] indicators){
        super(FXCollections.observableList(Arrays.asList(indicators)), controlPanelController);

        itemTF = new TextField();
        itemTF.setMaxWidth(80);
        setExitButtonsActions(controlPanelLine, controlPanel,  lineList);

        this.getChildren().addAll(itemButton, indicatorCB, itemTF);

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

    public TextField getItemTF() {
        return itemTF;
    }
}
