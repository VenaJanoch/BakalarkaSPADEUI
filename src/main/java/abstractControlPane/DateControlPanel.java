package abstractControlPane;

import controllers.formControllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.TableView;
import services.ControlPanelLineObject;
import services.ControlPanelLineType;
import services.ParamType;
import tables.BasicTable;

public abstract class DateControlPanel extends NameControlPanel {


    /**
     * Konstruktor tridy zavola konstruktor materske tridy a metodu pro vytvoreni grafickych prvku
     *
     * @param buttonText         nazev tlacitka
     * @param formDataController Trida FormDataController
     * @param editFormController Trida EditFormController
     * @param formController     Trida FormController
     */
    public DateControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonText, formDataController, editFormController, formController);
        createBaseControlPanel();
    }

    /**
     * Konstruktor tridy zavola konstruktor materske tridy a metodu pro vytvoreni grafickych prvku
     *
     * @param buttonText         nazev tlacitka
     * @param formDataController Trida FormDataController
     * @param editFormController Trida EditFormController
     */
    public DateControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        createBaseControlPanel();
    }

    protected void createBaseControlPanel() {

        lineList.add(new ControlPanelLineObject("Date: ", ControlPanelLineType.Date, ParamType.Date));

    }

    /**
     * Metoda pro zobrazeni postraniho editacniho panelu
     *
     * @param basicTable Instance BasicTable
     * @param tableView  Instace TableView
     */
    protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);
}
