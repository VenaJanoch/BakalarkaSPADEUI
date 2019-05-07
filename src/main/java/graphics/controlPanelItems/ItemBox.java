package graphics.controlPanelItems;

import abstractControlPane.ControlPanel;
import controllers.graphicsComponentsControllers.ControlPanelController;
import controllers.graphicsComponentsControllers.ItemBoxController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import services.Constans;
import services.ControlPanelLineObject;

public class ItemBox extends HBox {

    protected ComboBox<String> indicatorCB;
    protected boolean isShowItem;
    protected Button itemButton;
    protected ItemBoxController itemBoxController;
    protected Label label;
    private int indicatorIndex;
    protected ControlPanelController controlPanelController;

    public ItemBox(ObservableList<String> indicatorSymbols, ControlPanelController controlPanelController) {

        this.controlPanelController = controlPanelController;
        this.itemBoxController = new ItemBoxController();
        label = new Label();
        indicatorCB = new ComboBox<>(indicatorSymbols);
        indicatorCB.getSelectionModel().selectedIndexProperty().addListener(itemListener);
        indicatorCB.getSelectionModel().select(Constans.indicatorIndex);
    }

    protected void setExitButtonsActions(ControlPanelLine line, ControlPanel controlPanel, ObservableList<ControlPanelLineObject> lineList) {
        itemButton = new Button("+");
        itemButton.setOnAction(event -> controlPanelController.copyLine(line, controlPanel, lineList, line.getParamBox().getSelectionModel().getSelectedIndex()));

    }

    public void addButtonAction(Node node) {

        if (!isShowItem) {
            node.setVisible(true);
            indicatorCB.setVisible(true);
            isShowItem = true;
            itemButton.setText("-");
        } else {
            node.setVisible(false);
            indicatorCB.setVisible(false);
            isShowItem = false;
            itemButton.setText("+");
        }
    }

    public void setShowItem(Node node, boolean showItem) {
        isShowItem = !showItem;
        addButtonAction(node);
    }

    public boolean isShowItem() {
        return isShowItem;
    }

    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Priority
     */
    ChangeListener<Number> itemListener = new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            indicatorIndex = newValue.intValue();

        }
    };

    public int getIndicatorIndex() {
        return indicatorIndex;
    }

    public ComboBox<String> getIndicatorCB() {
        return indicatorCB;
    }

    public void setIndicator(int indicatorIndex) {
        indicatorCB.getSelectionModel().select(indicatorIndex);
    }

    public Label getLabel() {
        return label;
    }
}


