package abstractControlPane;

import controllers.FormController;
import graphics.DateItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import org.controlsfx.control.CheckComboBox;
import services.Constans;
import services.SegmentLists;
import tables.BasicTable;

public abstract class WorkUnitDateControlPanel extends WorkUnitControlPanel {

    protected DateItem dateDP;

    public WorkUnitDateControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonText, formDataController, editFormController, formController);
        addItemsToControlPanel3();
    }

    public WorkUnitDateControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        addItemsToControlPanel3();
    }

    protected void addItemsToControlPanel3() {

        dateDP = new DateItem("Created: ");

        controlPanelController.setDateItemToControlPanel(controlPane, dateDP, 0, 3);
    }


    protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);


}
