package controlPanels;

import controllers.FormController;
import abstractControlPane.DescriptionControlPanel;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.CheckComboBox;
import services.Constans;
import tables.BasicTable;
import tables.MilestoneTable;

import java.util.ArrayList;
import java.util.List;

public class MilestoneControlPanel extends DescriptionControlPanel {

    private Label criteriaLB;
    private CheckComboBox<BasicTable> criteriaCB;
    private ObservableList<Integer> criterionIndex;


    private boolean isShowcriterion;
    private Button criterionButton;

    public MilestoneControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController,editFormController, formController);
        criterionIndex = FXCollections.observableArrayList();
        addItemsToControlPanel();
    }


    protected void addItemsToControlPanel(){

        criteriaLB = new Label("Criteria: ");
        criteriaCB = new CheckComboBox<BasicTable>(formController.getCriterionObservable());
        criteriaCB.setMaxWidth(Constans.checkComboBox);
        criteriaCB.setVisible(false);
        setExitButtonsActions();
        criteriaCB.getCheckModel().getCheckedItems().addListener(new ListChangeListener<BasicTable>() {

            public void onChanged(ListChangeListener.Change<? extends BasicTable> c) {
                criterionIndex = criteriaCB.getCheckModel().getCheckedIndices();
            }
        });

        controlPane.add(criterionButton, 0, 2);
        controlPane.add(criteriaLB, 1, 2);
        controlPane.add(criteriaCB, 2, 2);
        controlPane.add(button, 2, 3);

    }

    private void setExitButtonsActions(){
        isShowcriterion = false;
        criterionButton = new Button("+");
        criterionButton.setOnAction(event -> {
            if (!isShowcriterion){
                criteriaCB.setVisible(true);
                isShowcriterion  = true;
                criterionButton.setText("-");
            }else{
                criteriaCB.setVisible(false);
                criteriaCB.getCheckModel().clearChecks();
                isShowcriterion = false;
                criterionButton.setText("+");
            }
        });

    }

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        MilestoneTable milestoneTable = (MilestoneTable) basicTable;
        int id = milestoneTable.getId();
        String[] milestoneData = formDataController.getMilestoneStringData(id);
        List<Integer> criteriaID = formDataController.getCriterionFromMilestone(id);
        for(int i : criteriaID){
            criteriaCB.getCheckModel().check(i);
        }

        nameTF.setText(milestoneData[0]);
        descriptionTF.setText(milestoneData[1]);

        button.setOnAction(event ->{

            milestoneTable.setName(id + "_" + nameTF.getText());
            milestoneTable.setDescription(descriptionTF.getText());
            ArrayList<Integer> criterionList = new ArrayList<>(criterionIndex);

            editFormController.editDataFromMilestone(nameTF.getText(), milestoneTable, criterionList, id);

            clearPanelCB(tableView);
           // this.close();
        });

      //  this.show();

    }

    public Button getButton() {
        return button;
    }

    public ObservableList<Integer> getCriterionIndex() {
        return criterionIndex;
    }


    public void clearPanelCB(TableView tableView) {
        nameTF.setText("");
        descriptionTF.setText("");
        criteriaCB.getCheckModel().clearChecks();
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }
}
