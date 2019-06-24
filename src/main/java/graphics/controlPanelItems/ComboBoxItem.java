package graphics.controlPanelItems;

import abstractControlPane.ControlPanel;
import controllers.graphicsComponentsControllers.ControlPanelController;
import controllers.graphicsComponentsControllers.ItemBoxController;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import services.Constans;
import services.ControlPanelLineObject;
import tables.BasicTable;

import java.util.Arrays;

/**
 * Třída rozsirujici funkcnost komponenty CheckComboBox
 *
 * @author Václav Janoch
 */
public class ComboBoxItem extends ItemBox {

    /**Globalni promenne tridy**/
    private ComboBox itemCB;

    private ComboBoxItem otherComboBoxItem;

    private ObservableList<String> listForBox;

    /**
     * Konstruktor tridy,
     * Zinicializuje globalni promenne tridy
     * @param controlPanelLine instance tridy ControlPanelLine
     * @param controlPanel instance tridy ControlPanel
     * @param controlPanelController instace tridy ControlPanelController
     * @param listForBox seznam s prvky pro komponentu CheckCombobox
     * @param lineList seznam parametru na radku
     */
    public ComboBoxItem(ControlPanelLine controlPanelLine, ControlPanel controlPanel, ControlPanelController controlPanelController,
                        ObservableList listForBox, ObservableList<ControlPanelLineObject> lineList) {
        super(FXCollections.observableList(Arrays.asList(Constans.textIndicatorList)), controlPanelController);

        this.listForBox = listForBox;

        itemBoxController = new ItemBoxController();

        itemCB = new ComboBox<BasicTable>();
        itemCB.getSelectionModel().selectedIndexProperty().addListener(itemBoxController.comboBoxListener());
        itemCB.setVisibleRowCount(5);
        itemCB.setItems(listForBox);

        setExitButtonsActions(controlPanelLine, controlPanel, lineList);

        this.getChildren().addAll(itemButton, indicatorCB, itemCB);

    }

    /**

     * Konstruktor tridy,
     * Zinicializuje globalni promenne tridy
     * @param controlPanelLine instance tridy ControlPanelLine
     * @param controlPanel instance tridy ControlPanel
     * @param controlPanelController instace tridy ControlPanelController
     * @param listForBox seznam s prvky pro komponentu CheckCombobox
     * @param lineList seznam parametru na radku
     * @param listener listener urceny pro ComboBox
     */
    public ComboBoxItem(ControlPanelLine controlPanelLine, ControlPanel controlPanel, ControlPanelController controlPanelController,
                        ObservableList listForBox, ChangeListener<Number> listener, ObservableList<ControlPanelLineObject> lineList) {
        super(FXCollections.observableList(Arrays.asList(Constans.textIndicatorList)), controlPanelController);

        itemCB = new ComboBox<String>();
        itemCB.setVisibleRowCount(5);
        itemCB.getSelectionModel().selectedIndexProperty().addListener(listener);
        itemCB.setItems(listForBox);

        setExitButtonsActions(controlPanelLine, controlPanel, lineList);

        this.getChildren().addAll(itemButton, itemCB);

    }


    /**
     * Metoda pro zpetne zvoleni prvku v komponente ComboBox
     * @param index index pro zvoleni
     */
    public void selectItemInComboBox(int index) {
        itemCB.getSelectionModel().select(index);
    }

    /**Getrs and Setters**/
    public ComboBox<BasicTable> getItemCB() {
        return itemCB;
    }

    public Button getItemButton() {
        return itemButton;
    }

    public int getItemIndex() {
        return itemBoxController.getItemIndex();
    }
}
