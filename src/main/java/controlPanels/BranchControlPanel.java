package controlPanels;

import controllers.FormController;
import abstractControlPane.NameControlPanel;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import services.ParamType;
import tables.BasicTable;
import tables.BranchTable;

import java.util.ArrayList;
import java.util.List;

public class BranchControlPanel extends NameControlPanel {

    private Label isMainLB;

    private ToggleGroup group = new ToggleGroup();

    private RadioButton rbYes;
    private RadioButton rbNo;

    public BranchControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);
        createControlPanel();
    }

    @Override
    protected void createBaseControlPanel() {
        controlPanelController.createNewLine(this, lineList);
    }



    public void createControlPanel(){


        controlPanelController.setRadioButton(this, 1,"Main: ", true);
        controlPanelController.createNewLine(this, lineList);
    }


    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        BranchTable branchTable = (BranchTable) basicTable;
        int id = basicTable.getId();

        List branchData[] = formDataController.getBranchStringData(id);
        controlPane.getChildren().clear();
        createControlPanel();

        controlPanelController.setValueTextField(this, lineList ,ParamType.Name, branchData, branchData[2], 0);
       boolean exist = false;
        List boolList = branchData[1];
        if (boolList.size() != 0){
            exist = true;
        }
        controlPanelController.setValueRadioButton(exist);



        button.setOnAction(event ->{
            ArrayList<Integer> nameIndicators = new ArrayList<>();
            ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);

            editFormController.editDataFromBranch(name, nameIndicators, controlPanelController.isMain(), branchTable);
            clearPanel(tableView);
        });
    }

    public void clearPanel(TableView<BranchTable> tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }



}
