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

        nameTF.setText(criterionData[0]);
        descriptionTF.setShowItem(false);
        if (criterionData[1] != null){
            descriptionTF.setTextToTextField(criterionData[1]);
            descriptionTF.setShowItem(true);
        }
        button.setOnAction(event ->{
            editFormController.editDataFromCriterion(nameTF.getText(), descriptionTF.getTextFromTextField(), criterionTable, id);
            clearPanel(tableView);
        });
    }


    public TextField getNameTF() {
        return nameTF;
    }

    public void clearPanel(TableView tableView) {
        tableView.getSelectionModel().clearSelection();
        tableView.refresh();
        nameTF.setText("");
        descriptionTF.setTextToTextField("");
    }
}
