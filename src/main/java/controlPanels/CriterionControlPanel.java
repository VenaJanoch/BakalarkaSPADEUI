package controlPanels;

import abstractControlPane.DescriptionControlPanel;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.TableView;
import services.ParamType;
import tables.BasicTable;
import tables.CriterionTable;

import java.util.ArrayList;
import java.util.List;

public class CriterionControlPanel extends DescriptionControlPanel {


    public CriterionControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonName, formDataController, editFormController);
        addItemsToControlPanel();
    }


    public void addItemsToControlPanel() {
        controlPanelController.createNewLineWithExist(this, lineList);
    }


    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        CriterionTable criterionTable = (CriterionTable) basicTable;
        int id = criterionTable.getId();
        List[] criterionData = formDataController.getCriterionData(id);

        controlPanelController.resetPanel(controlPane);
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList, ParamType.Name, criterionData, criterionData[2], 0);
        controlPanelController.setValueTextField(this, lineList, ParamType.Description, criterionData, criterionData[3], 1);
        List boolList = criterionData[4];
        boolean exist = (boolean) boolList.get(0);

        controlPanelController.setValueExistRadioButton(exist);
        controlPanelController.setAlias((String) boolList.get(2), this);


        button.setOnAction(event -> {

            ArrayList<Integer> nameIndicators = new ArrayList<>();
            ArrayList<Integer> descIndicators = new ArrayList<>();
            ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
            ArrayList<String> desc = controlPanelController.processTextLines(ParamType.Description, descIndicators);

            editFormController.editDataFromCriterion(aliasTF.getText(), name, nameIndicators, desc, descIndicators, criterionTable, controlPanelController.isExist(), id);
            clearPanel(tableView);
        });
    }


    public void clearPanel(TableView tableView) {
        tableView.getSelectionModel().clearSelection();
        tableView.refresh();
    }
}
