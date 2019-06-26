package abstractControlPane;

import controllers.formControllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.TableView;
import services.ControlPanelLineObject;
import services.ControlPanelLineType;
import services.ParamType;
import services.SegmentLists;
import tables.BasicTable;

public abstract class WorkUnitControlPanel extends DescriptionControlPanel {

    private SegmentLists segmentLists;

    /**
     * Konstruktor tridy zavola konstruktor materske tridy a metodu pro vytvoreni grafickych prvku
     *
     * @param buttonText         nazev tlacitka
     * @param formDataController Trida FormDataController
     * @param editFormController Trida EditFormController
     * @param formController     Trida FormController
     */
    public WorkUnitControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonText, formDataController, editFormController, formController);
        segmentLists = formController.getSegmentLists();
        addItemsToControlPanel2();
    }

    /**
     * Konstruktor tridy zavola konstruktor materske tridy a metodu pro vytvoreni grafickych prvku
     *
     * @param buttonText         nazev tlacitka
     * @param formDataController Trida FormDataController
     * @param editFormController Trida EditFormController
     */
    public WorkUnitControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        addItemsToControlPanel2();
    }

    protected void addItemsToControlPanel2() {

        lineList.add(new ControlPanelLineObject("Work units: ", ControlPanelLineType.CheckBox, ParamType.WorkUnit, segmentLists.getWorkUnitsObservable()));

    }

    /**
     * Metoda pro zobrazeni postraniho editacniho panelu
     *
     * @param basicTable Instance BasicTable
     * @param tableView  Instace TableView
     */
    protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);

}
