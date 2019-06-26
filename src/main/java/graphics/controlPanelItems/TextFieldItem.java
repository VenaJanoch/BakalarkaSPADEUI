package graphics.controlPanelItems;

import abstractControlPane.ControlPanel;
import controllers.graphicsComponentsControllers.ControlPanelController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ControlPanelLineObject;

import java.util.Arrays;

/**
 * Třída rozsirujici funkcnost komponenty TextField
 *
 * @author Václav Janoch
 */
public class TextFieldItem extends ItemBox {

    /**
     * Globalni promenne tridy
     */
    private TextField itemTF;

    /**
     * Konstruktor tridy,
     * Zinicializuje globalni promenne tridy
     *
     * @param controlPanelLine       instance tridy ControlPanelLine
     * @param controlPanel           instance tridy ControlPanel
     * @param controlPanelController instace tridy ControlPanelController
     * @param lineList               seznam parametru na radku
     */
    public TextFieldItem(ControlPanelLine controlPanelLine, ControlPanel controlPanel, ControlPanelController controlPanelController,
                         ObservableList<ControlPanelLineObject> lineList, String[] indicators) {
        super(FXCollections.observableList(Arrays.asList(indicators)), controlPanelController);

        itemTF = new TextField();
        itemTF.setMaxWidth(80);
        setExitButtonsActions(controlPanelLine, controlPanel, lineList);

        this.getChildren().addAll(itemButton, indicatorCB, itemTF);

    }

    public String getTextFromTextField() {
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
