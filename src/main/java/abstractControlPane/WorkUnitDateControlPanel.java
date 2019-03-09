package abstractControlPane;

import controllers.FormController;
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

    protected Label dateLB;
    protected DatePicker dateDP;

    private Button dateButton;
    private boolean isShowDate;

    public WorkUnitDateControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonText, formDataController, editFormController, formController);
        addItemsToControlPanel3();
    }

    public WorkUnitDateControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        addItemsToControlPanel3();
    }

    protected void addItemsToControlPanel3() {


        dateLB = new Label("Created: ");
        dateDP = new DatePicker();
        dateDP.setId("DP1");
        dateDP.setVisible(false);

        isShowDate = false;
        dateButton = new Button("+");
        dateButton.setOnAction(event -> {
            if (!isShowDate){
                dateDP.setVisible(true);
                isShowDate  = true;
                dateButton.setText("-");
            }else{
                dateDP.setVisible(false);
                dateDP.setValue(null);
                isShowDate = false;
                dateButton.setText("+");
            }
        });

        controlPane.add(dateLB, 1, 3);
        controlPane.setHalignment(dateLB, HPos.LEFT);
        controlPane.add(dateDP, 2, 3);
        controlPane.add(dateButton, 0, 3);

    }


    protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);


}
