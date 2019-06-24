package graphics.controlPanelItems;

import abstractControlPane.ControlPanel;
import controllers.graphicsComponentsControllers.ControlPanelController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import services.Constans;
import services.ControlPanelLineObject;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Třída rozsirujici funkcnost komponenty DatePicker
 *
 * @author Václav Janoch
 */
public class DateItem extends ItemBox {

    /**
     * Globalni promenne tridy
     */
    private DatePicker itemDate;

    /**
     * Konstruktor tridy,
     * Zinicializuje globalni promenne tridy
     * @param controlPanelLine instance tridy ControlPanelLine
     * @param controlPanel instance tridy ControlPanel
     * @param controlPanelController instace tridy ControlPanelController
     * @param lineList seznam parametru na radku
     */
    public DateItem(ControlPanelLine controlPanelLine, ControlPanel controlPanel, ControlPanelController controlPanelController,
                    ObservableList<ControlPanelLineObject> lineList) {
        super(FXCollections.observableList(Arrays.asList(Constans.numberIndicatorList)), controlPanelController);

        itemDate = new DatePicker();
        itemDate.setMaxWidth(80);
        setExitButtonsActions(controlPanelLine, controlPanel, lineList);

        this.getChildren().addAll(itemButton, indicatorCB, itemDate);

    }

    public LocalDate getDateFromDatePicker() {
            return itemDate.getValue();

    }

    public Button getItemButton() {
        return itemButton;
    }

    public void setDateToPicker(LocalDate date) {
        itemDate.setValue(date);
    }

    public DatePicker getItemDate() {
        return itemDate;
    }
}
