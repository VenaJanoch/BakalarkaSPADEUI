package abstractControlPane;

import controllers.FormController;
import graphics.DateItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import tables.BasicTable;

public abstract class DateControlPanel extends NameControlPanel {

    protected DateItem dateDP;

    public DateControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonText, formDataController, editFormController, formController);
        createBaseControlPanel();
    }

    public DateControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        createBaseControlPanel();
    }

    protected void createBaseControlPanel() {

        dateDP = new DateItem("Date: ");

        controlPane.add(dateDP.getItemButton(), 0, 1);
        controlPane.add(dateDP.getItemNameLB(), 1, 1);
        controlPane.setHalignment(dateDP.getItemNameLB(), HPos.RIGHT);
        controlPane.add(dateDP.getItemDate(), 2, 1);


    }


    protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);

    public DateItem getDateDP() {
        return dateDP;
    }
}
