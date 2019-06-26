package abstractControlPane;

import controllers.formControllers.FormController;
import graphics.controlPanelItems.TextFieldItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import services.ControlPanelLineObject;
import services.ControlPanelLineType;
import services.ParamType;

public abstract class NameControlPanel extends ControlPanel {

    protected TextFieldItem nameTF;

    /**
     * Konstruktor tridy zavola konstruktor materske tridy a metodu pro vytvoreni grafickych prvku
     *
     * @param buttonText         nazev tlacitka
     * @param formDataController Trida FormDataController
     * @param editFormController Trida EditFormController
     * @param formController     Trida FormController
     */
    public NameControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonText, formDataController, editFormController, formController);
        createBasicPanel();
    }

    /**
     * Konstruktor tridy zavola konstruktor materske tridy a metodu pro vytvoreni grafickych prvku
     *
     * @param buttonText         nazev tlacitka
     * @param formDataController Trida FormDataController
     * @param editFormController Trida EditFormController
     */
    public NameControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        createBasicPanel();
    }

    protected void createBasicPanel() {
        lineList.add(new ControlPanelLineObject("Name: ", ControlPanelLineType.Text, ParamType.Name));
    }

    /**
     * Metoda pro zobrazeni postraniho editacniho panelu
     */
    protected abstract void createBaseControlPanel();

    public String getName() {
        return nameTF.getTextFromTextField();
    }

}