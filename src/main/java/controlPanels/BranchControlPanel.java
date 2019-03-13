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
import tables.BasicTable;
import tables.BranchTable;

public class BranchControlPanel extends NameControlPanel {

    private Label isMainLB;

    private ToggleGroup group = new ToggleGroup();

    private RadioButton rbYes;
    private RadioButton rbNo;

    public BranchControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);
    }

    @Override
    protected void createBaseControlPanel() {

    }



    public void createControlPanel(){
        isMainLB = new Label("Main");

        rbYes = new RadioButton("Yes");
        rbYes.setToggleGroup(group);
        rbYes.setSelected(true);
        rbYes.setId("YesRB");
        rbNo = new RadioButton("No");
        rbNo.setToggleGroup(group);
        rbNo.setId("NoRB");

        group.selectedToggleProperty().addListener(controlPanelController.radioButtonListener());

        controlPane.add(isMainLB, 0, 1);
        controlPane.add(rbYes, 1, 1);
        controlPane.add(rbNo, 2, 1);
        controlPane.add(button, 1, 2);

    }


    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        BranchTable branchTable = (BranchTable) basicTable;
        int id = basicTable.getId();
        String[] classData = formDataController.getBranchStringData(id);

        nameTF.setTextToTextField(classData[0]);
        boolean isMainBranch = Boolean.valueOf(classData[1]);
        if(isMainBranch){
            rbYes.setSelected(true);
        }else{
            rbNo.setSelected(true);
        }

        button.setOnAction(event ->{
            editFormController.editDataFromBranch(nameTF.getTextFromTextField(), controlPanelController.isMain(), branchTable);
            clearPanel(tableView);
        });
    }

    public void clearPanel(TableView<BranchTable> tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }



}
