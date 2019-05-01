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
        lineList.add(new ControlPanelLineObject("Created: ", ControlPanelLineType.Date, ParamType.Date));
    }

    public DateDescControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        addItemsToControlPanel();
    }

    protected void addItemsToControlPanel() {



//        controlPanelController.setDateItemToControlPanel(controlPane, dateDP, 0, 2);
    }


    protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);

    public DateItem getDateDP() {
        return dateDP;
    }
}
