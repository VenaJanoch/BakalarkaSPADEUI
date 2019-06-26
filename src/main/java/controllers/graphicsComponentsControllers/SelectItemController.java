package controllers.graphicsComponentsControllers;

import abstractform.BasicForm;
import controllers.formControllers.FormController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;

/**
 * Trida predstavujici controller rizeni vyberu prvku z Comboboxu pro vlozeni do leveho postraniho panelu
 *
 * @author Václav Janoch
 */
public class SelectItemController {

    private ComboBox box;
    private ChangeListener<Number> roleListener;

    private int formIndex = -1;
    private FormController formController;
    private DrawerPanelController drawerPanelController;

    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne tridy a listener
     *
     * @param drawerPanelController
     */
    public SelectItemController(DrawerPanelController drawerPanelController) {
        this.drawerPanelController = drawerPanelController;
        roleListener = new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                formIndex = newValue.intValue();
                drawerPanelController.showLeftPanel(getFormFromSelectItem());
            }
        };

    }

    /**
     * Metoda pro ziskani zvoleneho Formulare
     *
     * @return BasicForm vybrany formular
     */
    public BasicForm getFormFromSelectItem() {
        return formController.getForm(formIndex);
    }

    public int getFormIndex() {
        return formIndex;
    }

    /**
     * Metoda pro komponenty
     *
     * @param box ComboBox
     */
    public void setBox(ComboBox box) {
        this.box = box;
        box.getSelectionModel().selectedIndexProperty().addListener(roleListener);
        box.setOnMouseClicked(event -> {
            if (formIndex != -1) {
                drawerPanelController.showLeftPanel(getFormFromSelectItem());
            }
        });
    }

    /**
     * Setter pro nastaveni tridy FormController
     *
     * @param formController instace tridy FormController
     */
    public void setFormController(FormController formController) {
        this.formController = formController;
    }
}
