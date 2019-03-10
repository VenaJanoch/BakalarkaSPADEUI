package abstractControlPane;

import controllers.FormController;
import graphics.DateItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.geometry.HPos;
import javafx.scene.control.*;
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

        dateDP = new DateItem("Date: ");
        controlPane.add(dateDP.getItemButton(), 0, 2);
        controlPane.add(dateDP.getItemNameLB(), 1, 2);
        controlPane.setHalignment(dateDP.getItemNameLB(), HPos.LEFT);
        controlPane.add(dateDP.getItemDate(), 2, 2);


    }


    protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);

    public DateItem getDateDP() {
        return dateDP;
    }
}
