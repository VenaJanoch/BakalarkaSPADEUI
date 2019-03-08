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

public abstract class DateControlPanel extends NameControlPanel {

    protected Label dateLB;
    protected DatePicker dateDP;

    public DateControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonText, formDataController, editFormController, formController);
        createBaseControlPanel();
    }

    public DateControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        createBaseControlPanel();
    }

    protected void createBaseControlPanel() {

        dateLB = new Label("Created: ");
        dateDP = new DatePicker();

        controlPane.add(dateLB, 0, 1);
        controlPane.setHalignment(dateLB, HPos.RIGHT);
        controlPane.add(dateDP, 1, 1);

    }


    protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);

}
