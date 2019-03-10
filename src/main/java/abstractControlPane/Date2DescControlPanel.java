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

        controlPane.getChildren().add(date2DP);

    }


    protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);


}
