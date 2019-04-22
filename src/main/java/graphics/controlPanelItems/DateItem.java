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

public class DateItem extends ItemBox {

    private DatePicker itemDate;

    public DateItem(ControlPanelLine controlPanelLine, ControlPanel controlPanel, ControlPanelController controlPanelController,
                    ObservableList<ControlPanelLineObject> lineList){
        super(FXCollections.observableList(Arrays.asList(Constans.numberIndicatorList)), controlPanelController);

        itemDate = new DatePicker();
        itemDate.setMaxWidth(80);
        setExitButtonsActions(controlPanelLine, controlPanel, lineList);

        this.getChildren().addAll(itemButton, indicatorCB, itemDate);

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

    public DatePicker getItemDate() {
        return itemDate;
    }
}
