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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MilestoneControlPanel extends DescriptionControlPanel {

    private CheckComboBoxItem criteriaCB;

    private MilestoneTable milestoneTable;

    public MilestoneControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController,editFormController, formController);
        addItemsToControlPanel();
    }


    protected void addItemsToControlPanel(){

        criteriaCB = new CheckComboBoxItem("Criterion: ", formController.getCriterionObservable());
        controlPanelController.setCheckComboBoxItemToControlPanel(controlPane, criteriaCB, 0, 2);

    }



    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        milestoneTable = (MilestoneTable) basicTable;
        int id = milestoneTable.getId();
        String[] milestoneData = formDataController.getMilestoneStringData(id);
        List<Integer> criteriaID = formDataController.getCriterionFromMilestone(id);

        controlPanelController.setValueCheckComboBox(criteriaCB, criteriaID);
        nameTF.setTextToTextField(milestoneData[0]);

        controlPanelController.setValueTextField(descriptionTF, milestoneData, 1);

        button.setOnAction(event ->{

            saveDataFromPanel(milestoneTable, tableView);


        });

    }

    public void saveDataFromPanel(BasicTable table, TableView tableView){
        int id =   table.getId();

        String desc = controlPanelController.checkValueFromTextItem(descriptionTF);
        milestoneTable.setDescription(desc);
        ArrayList<Integer> criterionList = null;
        if (criteriaCB.getChoosedIndicies() != null){
            criterionList = new ArrayList<>(criteriaCB.getChoosedIndicies());
        }

        editFormController.editDataFromMilestone(nameTF.getTextFromTextField(),  milestoneTable, criterionList, id);

        clearPanelCB(tableView);
    }

    public Button getButton() {
        return button;
    }

    public ObservableList<Integer> getCriterionIndex() {
        return criteriaCB.getChoosedIndicies();
    }


    public void clearPanelCB(TableView tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }
}
