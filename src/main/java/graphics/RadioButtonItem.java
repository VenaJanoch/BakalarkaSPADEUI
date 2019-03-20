package graphics;

import abstractControlPane.ControlPanel;
import controllers.ControlPanelController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import services.ControlPanelLineObject;

import java.util.Arrays;

public class RadioButtonItem extends ItemBox {

    private Label nameLb;
    private RadioButton yesRb;
    private RadioButton noRb;
    private ToggleGroup group;


    public RadioButtonItem(ControlPanelLine controlPanelLine, ControlPanel controlPanel, ControlPanelController controlPanelController,
                           ObservableList<ControlPanelLineObject> lineList ){
        super(null, controlPanelController);

        yesRb = new RadioButton();
        noRb = new RadioButton();
        group = new ToggleGroup();
        noRb = new RadioButton("No");
        yesRb = new RadioButton("Yes");
        yesRb.setSelected(true);

        nameLb = new Label("Exist: ");

        group.selectedToggleProperty().addListener(controlPanelController.radioButtonListener());

    }

    public void setGroup(){
         yesRb.setToggleGroup(group);
         noRb.setToggleGroup(group);
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
