package graphics.controlPanelItems;

import abstractControlPane.ControlPanel;
import controllers.ControlPanelController;
import graphics.controlPanelItems.ControlPanelLine;
import graphics.controlPanelItems.ItemBox;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import services.ControlPanelLineObject;

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
    }

    public void setGroup(){
        group.selectedToggleProperty().addListener(controlPanelController.radioButtonGroupListener());

        yesRb.setToggleGroup(group);
         noRb.setToggleGroup(group);
    }

    public boolean isSelectYes(){
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
