package controllers.graphicsComponentsControllers;

import abstractform.BasicForm;
import controllers.formControllers.FormController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;

public class SelectItemController {

    private ComboBox box;
    private ChangeListener<Number> roleListener;
    private int formiIndex = -1;
    private FormController formController;
    private DrawerPanelController drawerPanelController;

    public SelectItemController(DrawerPanelController drawerPanelController) {
        this.drawerPanelController = drawerPanelController;
        roleListener = new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                formiIndex = newValue.intValue();
                drawerPanelController.showLeftPanel(getFormFromSelectItem());
            }
        };

    }

    public BasicForm getFormFromSelectItem() {
        return formController.getForm(formiIndex);
    }

    public int getFormiIndex() {
        return formiIndex;
    }

    public void setBox(ComboBox box) {
        this.box = box;
        box.getSelectionModel().selectedIndexProperty().addListener(roleListener);
        box.setOnMouseClicked(event -> {
            if (formiIndex != -1) {
                drawerPanelController.showLeftPanel(getFormFromSelectItem());
            }
        });
    }

    public void setFormController(FormController formController) {
        this.formController = formController;
    }
}
