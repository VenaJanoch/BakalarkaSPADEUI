package abstractControlPane;

import controllers.FormController;
import graphics.controlPanelItems.DateItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.*;
import services.ControlPanelLineObject;
import services.ControlPanelLineType;
import services.ParamType;
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

        lineList.add(new ControlPanelLineObject("Date: ", ControlPanelLineType.Date, ParamType.Date));

        // dateDP = new DateItem("Date: ");
       // controlPanelController.setDateItemToControlPanel(controlPane, dateDP, 0, 1);

    }


    protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);

    public DateItem getDateDP() {
        return dateDP;
    }
}
