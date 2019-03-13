package abstractControlPane;

import controllers.FormController;
import graphics.CheckComboBoxItem;
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

public abstract class WorkUnitControlPanel extends DescriptionControlPanel {

    private SegmentLists segmentLists;
    protected CheckComboBoxItem workUnitCB;

    public WorkUnitControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonText, formDataController, editFormController, formController);
        segmentLists = formController.getSegmentLists();
        addItemsToControlPanel2();
    }

    public WorkUnitControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        addItemsToControlPanel2();
    }

    protected void addItemsToControlPanel2() {


        workUnitCB = new CheckComboBoxItem("Work units: ", segmentLists.getWorkUnitsObservable());

        controlPanelController.setCheckComboBoxItemToControlPanel(controlPane, workUnitCB, 0, 2);



    }


    protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);

}
