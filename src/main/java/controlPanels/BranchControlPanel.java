package controlPanels;

import abstractControlPane.NameControlPanel;
import controllers.formControllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.TableView;
import services.ParamType;
import tables.BasicTable;
import tables.BranchTable;

import java.util.ArrayList;
import java.util.List;

public class BranchControlPanel extends NameControlPanel {


    public BranchControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonName, formDataController, editFormController, formController);
        createControlPanel();
    }

    @Override
    protected void createBaseControlPanel() {
        controlPanelController.createNewLineWithExist(this, lineList);
    }


    public void createControlPanel() {


        controlPanelController.setRadioButton(this, 2, "Main: ", true);
        controlPanelController.createNewLineWithExist(this, lineList);
    }


    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        BranchTable branchTable = (BranchTable) basicTable;
        int id = basicTable.getId();

        List branchData[] = formDataController.getBranchStringData(id);
        controlPanelController.resetPanel(controlPane);
        createControlPanel();

        controlPanelController.setValueTextField(this, lineList, ParamType.Name, branchData, branchData[1], 0);
        boolean main = false;
        List boolList = branchData[2];
        if (boolList.get(1) != null) {
            main = (boolean) boolList.get(1);
        }

        controlPanelController.setValueRadioButton(main);
        controlPanelController.setValueExistRadioButton((boolean) boolList.get(0));
        controlPanelController.setAlias((String) boolList.get(2), this);

        button.setOnAction(event -> {
            ArrayList<Integer> nameIndicators = new ArrayList<>();
            ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
            editFormController.editDataFromBranch(aliasTF.getText(), name, nameIndicators, controlPanelController.isMain(), controlPanelController.isExist(), branchTable);
            clearPanel(tableView);
        });
    }

    public void clearPanel(TableView<BranchTable> tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }


}
