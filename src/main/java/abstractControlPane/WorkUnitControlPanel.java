package abstractControlPane;

import controllers.FormController;
import graphics.controlPanelItems.CheckComboBoxItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.TableView;
import services.*;
import tables.BasicTable;

public abstract class WorkUnitControlPanel extends DescriptionControlPanel {

    private SegmentLists segmentLists;
    protected CheckComboBoxItem workUnitCB;

    public WorkUnitControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonText, formDataController, editFormController, formController);
        segmentLists = formController.getSegmentLists();
        addItemsToControlPanel2();
    }

    public WorkUnitControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        addItemsToControlPanel2();
    }

    protected void addItemsToControlPanel2() {

        lineList.add(new ControlPanelLineObject("Work units: ", ControlPanelLineType.CheckBox, ParamType.WorkUnit, segmentLists.getWorkUnitsObservable()));

    }


    protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);

}
