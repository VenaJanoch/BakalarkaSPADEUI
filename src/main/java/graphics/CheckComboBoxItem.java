package graphics;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.controlsfx.control.CheckComboBox;
import tables.BasicTable;

import java.util.ArrayList;
import java.util.List;

public class CheckComboBoxItem extends HBox {

    private Label itemNameLB;
    private CheckComboBox<BasicTable> itemCB;

    private boolean isShowItem;
    private Button itemButton;

    private ObservableList<Integer> criterionIndex;

    public CheckComboBoxItem(String name, ObservableList list){
        super();

        itemNameLB = new Label(name);
        itemCB = new CheckComboBox<>(list);
        itemCB.setVisible(false);
        criterionIndex = FXCollections.observableArrayList();


        itemCB.getCheckModel().getCheckedItems().addListener(checkListener);

        setExitButtonsActions();

        this.getChildren().addAll(itemButton, itemNameLB, itemCB);

    }

    private void setExitButtonsActions(){
        isShowItem = false;
        itemButton = new Button("+");
        itemButton.setOnAction(event -> addButtonAction() );

    }

    public void addButtonAction(){
        {
            if (!isShowItem){
                itemCB.setVisible(true);
                isShowItem = true;
                itemButton.setText("-");
            }else{
                itemCB.setVisible(false);
                itemCB.getCheckModel().clearChecks();
                isShowItem = false;
                itemButton.setText("+");
            }
        }
    }

    public void setShowItem(boolean showItem) {
        isShowItem = !showItem;
        addButtonAction();
    }


    public void selectItemsInComboBox(List<Integer> list){

        for(int i : list){
            itemCB.getCheckModel().check(i);
        }
    }

    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Priority
     */
    ListChangeListener checkListener = new ListChangeListener<BasicTable>() {

        public void onChanged(ListChangeListener.Change<? extends BasicTable> c) {
            criterionIndex = itemCB.getCheckModel().getCheckedIndices();
        }

    };

    public Label getItemNameLB() {
        return itemNameLB;
    }

    public CheckComboBox<BasicTable> getItemCB() {
        return itemCB;
    }

    public boolean isShowItem() {
        return isShowItem;
    }

    public Button getItemButton() {
        return itemButton;
    }

    public ObservableList<Integer> getItemIndicies() {
        return criterionIndex;
    }
}
