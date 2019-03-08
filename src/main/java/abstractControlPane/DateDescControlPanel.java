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

public abstract class DateDescControlPanel extends DescriptionControlPanel {

    protected Label dateLB;
    protected DatePicker dateDP;

    public DateDescControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonText, formDataController, editFormController, formController);
        addItemsToControlPanel();
    }

    public DateDescControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        addItemsToControlPanel();
    }

    protected void addItemsToControlPanel() {

        dateLB = new Label("Created: ");
        dateDP = new DatePicker();
        dateDP.setId("DP1");

        controlPane.add(dateLB, 0, 2);
        controlPane.setHalignment(dateLB, HPos.LEFT);
        controlPane.add(dateDP, 1, 2);

    }


    protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);


}
