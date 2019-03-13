package controlPanels;

import abstractControlPane.DescriptionControlPanel;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import tables.BasicTable;
import tables.CriterionTable;

public class CriterionControlPanel extends DescriptionControlPanel {


    public CriterionControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController){
        super(buttonName, formDataController, editFormController);
        addItemsToControlPanel();
    }



    public void addItemsToControlPanel(){
        controlPane.add(button, 1, 2);

    }


    public void showEditControlPanel(BasicTable basicTable, TableView tableView){
        CriterionTable criterionTable = (CriterionTable) basicTable;
        int id = criterionTable.getId();
        String[] criterionData = formDataController.getCriterionData(id);

        nameTF.setTextToTextField(criterionData[0]);
        controlPanelController.setValueTextField(descriptionTF, criterionData, 1);

        button.setOnAction(event ->{
            String description = controlPanelController.checkValueFromTextItem(descriptionTF);
            editFormController.editDataFromCriterion(nameTF.getTextFromTextField(), description, criterionTable, id);
            clearPanel(tableView);
        });
    }


    public void clearPanel(TableView tableView) {
        tableView.getSelectionModel().clearSelection();
        tableView.refresh();
    }
}
