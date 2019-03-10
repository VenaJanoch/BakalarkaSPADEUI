package controlPanels;

import controllers.FormController;
import abstractControlPane.DescriptionControlPanel;
import graphics.CheckComboBoxItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import tables.BasicTable;
import tables.MilestoneTable;

import java.util.ArrayList;
import java.util.List;

public class MilestoneControlPanel extends DescriptionControlPanel {

    private CheckComboBoxItem criteriaCB;


    public MilestoneControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController,editFormController, formController);
        addItemsToControlPanel();
    }


    protected void addItemsToControlPanel(){

        criteriaCB = new CheckComboBoxItem("Criterion: ", formController.getCriterionObservable());
        controlPane.add(criteriaCB.getItemButton(), 0, 2);
        controlPane.add(criteriaCB.getItemNameLB(), 1, 2);
        controlPane.add(criteriaCB.getItemCB(), 2, 2);
        controlPane.add(button, 2, 3);

    }



    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        MilestoneTable milestoneTable = (MilestoneTable) basicTable;
        int id = milestoneTable.getId();
        String[] milestoneData = formDataController.getMilestoneStringData(id);
        List<Integer> criteriaID = formDataController.getCriterionFromMilestone(id);

        criteriaCB.setShowItem(false);
        if (criteriaID != null ){
            criteriaCB.setShowItem(true);
            criteriaCB.selectItemsInComboBox(criteriaID);

        }
        nameTF.setText(milestoneData[0]);

        descriptionTF.setShowItem(false);
        if (milestoneData[1] != null){
            descriptionTF.setTextToTextField(milestoneData[1]);
            descriptionTF.setShowItem(true);
        }
        button.setOnAction(event ->{

            milestoneTable.setName(id + "_" + nameTF.getText());
            if (descriptionTF.getTextFromTextField() != null){
                milestoneTable.setDescription(descriptionTF.getTextFromTextField());
            }

            ArrayList<Integer> criterionList = null;
            if (criteriaCB.getItemIndicies() != null){
               criterionList = new ArrayList<>(criteriaCB.getItemIndicies());
            }

            editFormController.editDataFromMilestone(nameTF.getText(), milestoneTable, criterionList, id);

            clearPanelCB(tableView);

        });

    }

    public Button getButton() {
        return button;
    }

    public ObservableList<Integer> getCriterionIndex() {
        return criteriaCB.getItemIndicies();
    }


    public void clearPanelCB(TableView tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }
}
