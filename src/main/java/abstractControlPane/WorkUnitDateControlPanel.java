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

    /**
     * Konstruktor tridy zavola konstruktor materske tridy a metodu pro vytvoreni grafickych prvku
     *
     * @param buttonText         nazev tlacitka
     * @param formDataController Trida FormDataController
     * @param editFormController Trida EditFormController
     * @param formController     Trida FormController
     */
    public WorkUnitDateControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonText, formDataController, editFormController, formController);
        addItemsToControlPanel3();
    }


    /**
     * Konstruktor tridy zavola konstruktor materske tridy a metodu pro vytvoreni grafickych prvku
     *
     * @param buttonText         nazev tlacitka
     * @param formDataController Trida FormDataController
     * @param editFormController Trida EditFormController
     */
    public WorkUnitDateControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        addItemsToControlPanel3();
    }

    protected void addItemsToControlPanel3() {

        lineList.add(new ControlPanelLineObject("Created: ", ControlPanelLineType.Date, ParamType.Date));

    }

    /**
     * Metoda pro zobrazeni postraniho editacniho panelu
     *
     * @param basicTable Instance BasicTable
     * @param tableView  Instace TableView
     */
    protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);

}
