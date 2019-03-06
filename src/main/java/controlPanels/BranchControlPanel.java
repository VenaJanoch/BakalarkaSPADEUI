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
import tables.BasicTable;
import tables.BranchTable;

public class BranchControlPanel extends NameControlPanel {

    private Label isMainLB;
    private boolean isMain = true;

    private ToggleGroup group = new ToggleGroup();

    private RadioButton rbYes;
    private RadioButton rbNo;

    public BranchControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);
    }

    @Override
    protected void createBaseControlPanel() {

    }



    public GridPane createControlPanel(){
        isMainLB = new Label("Main");

        rbYes = new RadioButton("Yes");
        rbYes.setToggleGroup(group);
        rbYes.setSelected(true);
        rbYes.setId("YesRB");
        rbNo = new RadioButton("No");
        rbNo.setToggleGroup(group);
        rbNo.setId("NoRB");

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                RadioButton chk = (RadioButton) newValue.getToggleGroup().getSelectedToggle();

                if (chk.getText().contains("Yes")) {
                    isMain = true;
                } else {
                   isMain = false;
                }

            }
        });
        controlPane.add(isMainLB, 2, 0);
        controlPane.add(rbYes, 3, 0);
        controlPane.add(rbNo, 4, 0);
        controlPane.add(button, 5, 0);

        return controlPane;
    }


    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        BranchTable branchTable = (BranchTable) basicTable;
        int id = basicTable.getId();
        String[] classData = formDataController.getBranchStringData(id);

        nameTF.setText(classData[0]);
        boolean isMainBranch = Boolean.valueOf(classData[1]);
        if(isMainBranch){
            rbYes.setSelected(true);
        }else{
            rbNo.setSelected(true);
        }

        button.setOnAction(event ->{
            editFormController.editDataFromBranch(nameTF.getText(), isMainBranch, branchTable);
            clearPanel(tableView);
       //     this.close();
        });

     //   this.show();

    }

    public void clearPanel(TableView<BranchTable> tableView) {
        nameTF.setText("");
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
        rbYes.setSelected(true);
    }

    public boolean isMain() {
        return isMain;
    }


}
