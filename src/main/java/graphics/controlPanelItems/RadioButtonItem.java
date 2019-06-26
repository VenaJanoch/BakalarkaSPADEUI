package graphics.controlPanelItems;

import abstractControlPane.ControlPanel;
import controllers.graphicsComponentsControllers.ControlPanelController;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import services.ControlPanelLineObject;

/**
 * Třída rozsirujici funkcnost komponenty RadioButton
 *
 * @author Václav Janoch
 */
public class RadioButtonItem extends ItemBox {

    /**
     * Globalni promenne tridy
     */
    private Label nameLb;
    private RadioButton yesRb;
    private RadioButton noRb;
    private ToggleGroup group;


    /**
     * Konstruktor tridy, zinicializuje globalni promenne tridy
     *
     * @param controlPanelLine       instace tridy ControlPanelLine
     * @param controlPanel           instace tridy ControlPanel
     * @param controlPanelController instace tridy ControlPanelController
     * @param lineList               seznam moznych typu radku
     */
    public RadioButtonItem(ControlPanelLine controlPanelLine, ControlPanel controlPanel, ControlPanelController controlPanelController,
                           ObservableList<ControlPanelLineObject> lineList) {
        super(null, controlPanelController);

        yesRb = new RadioButton();
        noRb = new RadioButton();
        group = new ToggleGroup();
        noRb = new RadioButton("No");
        yesRb = new RadioButton("Yes");
        yesRb.setSelected(true);

        nameLb = new Label("Exist: ");
    }

    /**
     * Metoda pro nastaveni skupiny radiobuttonu
     */
    public void setGroup() {
        group.selectedToggleProperty().addListener(controlPanelController.radioButtonGroupListener());

        yesRb.setToggleGroup(group);
        noRb.setToggleGroup(group);
    }

    public boolean isSelectYes() {
        return yesRb.isSelected();
    }

    public RadioButton getYesRb() {
        return yesRb;
    }

    public RadioButton getNoRb() {
        return noRb;
    }

    public ToggleGroup getGroup() {
        return group;
    }

    public Label getNameLb() {
        return nameLb;
    }
}
