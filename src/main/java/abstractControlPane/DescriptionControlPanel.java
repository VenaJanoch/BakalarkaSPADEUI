package abstractControlPane;

import controllers.formControllers.FormController;
import graphics.controlPanelItems.TextFieldItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.TableView;
import services.ControlPanelLineObject;
import services.ControlPanelLineType;
import services.ParamType;
import tables.BasicTable;


public abstract class DescriptionControlPanel extends NameControlPanel {


    /**
     * Konstruktor tridy zavola konstruktor materske tridy a metodu pro vytvoreni grafickych prvku
     *
     * @param buttonText         nazev tlacitka
     * @param formDataController Trida FormDataController
     * @param editFormController Trida EditFormController
     * @param formController     Trida FormController
     */
    public DescriptionControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
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
    public DescriptionControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        createBaseControlPanel();
    }

    public void createBaseControlPanel() {

        lineList.add(new ControlPanelLineObject("Description: ", ControlPanelLineType.Text, ParamType.Description));
    }

    /**
     * Metoda pro zobrazeni postraniho editacniho panelu
     *
     * @param basicTable Instance BasicTable
     * @param tableView  Instace TableView
     */
    protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);

    /**
     * Metoda pro pridani prvku do panelu
     */
    protected abstract void addItemsToControlPanel();


}