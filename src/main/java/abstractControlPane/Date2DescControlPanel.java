package abstractControlPane;

import controllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.geometry.HPos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import tables.BasicTable;

public abstract class Date2DescControlPanel extends DateControlPanel {

    protected Label date2LB;
    protected DatePicker date2DP;

    public Date2DescControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonText, formDataController, editFormController, formController);
        createBaseControlPanel();
    }

    public Date2DescControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        createBaseControlPanel();
    }

    protected void createBaseControlPanel() {

        date2LB = new Label("Created: ");
        date2DP = new DatePicker();
        date2DP.setId("DP2");

        controlPane.add(date2LB, 0, 3);
        controlPane.setHalignment(date2LB, HPos.RIGHT);
        controlPane.add(date2DP, 1, 3);

    }


    protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);


}
