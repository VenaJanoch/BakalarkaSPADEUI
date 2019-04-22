package abstractControlPane;

import controllers.formControllers.FormController;
import graphics.controlPanelItems.DateItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.TableView;
import services.*;
import tables.BasicTable;

public abstract class WorkUnitDateControlPanel extends WorkUnitControlPanel {

    protected DateItem dateDP;

    public WorkUnitDateControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonText, formDataController, editFormController, formController);
        addItemsToControlPanel3();
    }

    public WorkUnitDateControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        addItemsToControlPanel3();
    }

    protected void addItemsToControlPanel3() {

        lineList.add(new ControlPanelLineObject("Created: ", ControlPanelLineType.Date, ParamType.Date));


        // dateDP = new DateItem("Created: ");

      // controlPanelController.setDateItemToControlPanel(controlPane, dateDP, 0, 3);
    }


    protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);

}
