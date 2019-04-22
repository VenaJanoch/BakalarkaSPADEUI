package abstractControlPane;

import controllers.formControllers.FormController;
import graphics.controlPanelItems.DateItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.*;
import services.ControlPanelLineObject;
import services.ControlPanelLineType;
import services.ParamType;
import tables.BasicTable;

public abstract class DateDescControlPanel extends DescriptionControlPanel {

    protected DateItem dateDP;


    public DateDescControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonText, formDataController, editFormController, formController);
        addItemsToControlPanel();
    }

    public DateDescControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        addItemsToControlPanel();
    }

    protected void addItemsToControlPanel() {

        lineList.add(new ControlPanelLineObject("Date: ", ControlPanelLineType.Date, ParamType.Date));

//        controlPanelController.setDateItemToControlPanel(controlPane, dateDP, 0, 2);
    }


    protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);

    public DateItem getDateDP() {
        return dateDP;
    }
}
