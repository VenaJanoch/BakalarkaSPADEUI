package graphics.controlPanelItems;

import abstractControlPane.ControlPanel;
import controllers.graphicsComponentsControllers.ControlPanelController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import org.controlsfx.control.CheckComboBox;
import services.Constans;
import services.ControlPanelLineObject;
import tables.BasicTable;

import java.util.Arrays;
import java.util.List;

/**
 * Třída rozsirujici funkcnost komponenty CheckComboBox
 *
 * @author Václav Janoch
 */
public class CheckComboBoxItem extends ItemBox {

    /**Globalni promenne tridy**/
    private CheckComboBox<BasicTable> itemCB;


    /**
     * Konstruktor tridy,
     * Zinicializuje globalni promenne tridy
     * @param controlPanelLine instance tridy ControlPanelLine
     * @param controlPanel instance tridy ControlPanel
     * @param controlPanelController instace tridy ControlPanelController
     * @param listForBox seznam s prvky pro komponentu CheckCombobox
     * @param lineList seznam parametru na radku
     */
    public CheckComboBoxItem(ControlPanelLine controlPanelLine, ControlPanel controlPanel, ControlPanelController controlPanelController,
                             ObservableList listForBox, ObservableList<ControlPanelLineObject> lineList) {
        super(FXCollections.observableList(Arrays.asList(Constans.textIndicatorList)), controlPanelController);


        itemCB = new CheckComboBox<>(listForBox);

        itemCB.getCheckModel().getCheckedItems().addListener(itemBoxController.CheckBoxListener(itemCB));

        setExitButtonsActions(controlPanelLine, controlPanel, lineList);

        this.getChildren().addAll(itemButton, itemCB);

    }


    /**
     * Metoda pro zpetne zvoleni prvku v komponente CheckComboBox
     * @param list seznam indexu pro zvoleni
     */
    public void selectItemsInComboBox(List<Integer> list) {

        for (int i : list) {
            itemCB.getCheckModel().check(i);
        }
    }

    /**Getrs and Setters**/

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
