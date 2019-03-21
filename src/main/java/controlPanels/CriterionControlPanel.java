package controlPanels;

import abstractControlPane.DescriptionControlPanel;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import services.ParamType;
import tables.BasicTable;
import tables.CriterionTable;

import java.util.ArrayList;
import java.util.List;

public class CriterionControlPanel extends DescriptionControlPanel {


    public CriterionControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController){
        super(buttonName, formDataController, editFormController);
        addItemsToControlPanel();
    }



    public void addItemsToControlPanel(){
        controlPanelController.createNewLine(this, lineList);
    }


    public void showEditControlPanel(BasicTable basicTable, TableView tableView){
        CriterionTable criterionTable = (CriterionTable) basicTable;
        int id = criterionTable.getId();
        List[] criterionData = formDataController.getCriterionData(id);

        controlPane.getChildren().clear();
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList ,ParamType.Name, criterionData, criterionData[2], 0);
        controlPanelController.setValueTextField(this, lineList ,ParamType.Description, criterionData, criterionData[3], 1);

        button.setOnAction(event ->{

            ArrayList<Integer> nameIndicators = new ArrayList<>();
            ArrayList<Integer> descIndicators = new ArrayList<>();
           ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
            ArrayList<String> desc = controlPanelController.processTextLines(ParamType.Description, descIndicators);

            editFormController.editDataFromCriterion(name, nameIndicators,  desc, descIndicators, criterionTable, id);
            clearPanel(tableView);
        });
    }


    public void clearPanel(TableView tableView) {
        tableView.getSelectionModel().clearSelection();
        tableView.refresh();
    }
}
