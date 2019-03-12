package controlPanels;

import abstractControlPane.NameControlPanel;
import controllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import tables.BranchTable;
import tables.CommitTable;

public class CommitControlPanel extends NameControlPanel {

    private Label releaseLB;
    private boolean isRelease = true;

    private ToggleGroup group = new ToggleGroup();

    private RadioButton rbYes;
    private RadioButton rbNo;

    private int commitId;
    private int commitFormId;
    private CommitTable commitTable;

    public CommitControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController,
                              FormController formController, CommitTable branchTable, int id, int formIndex){
        super(buttonName, formDataController, editFormController, formController);
        this.commitFormId = formIndex;
        this.commitId = id;
        this.commitTable = branchTable;
    }

    @Override
    protected void createBaseControlPanel() {

    }



    public void createControlPanel(){
        releaseLB = new Label("Release: ");

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
                    isRelease = true;
                } else {
                   isRelease = false;
                }

            }
        });

        controlPane.add(releaseLB, 0, 1);
        controlPane.add(rbYes, 1, 1);
        controlPane.add(rbNo, 0, 1);
        controlPane.add(button, 1, 2);

        button.setOnAction(event ->{
            editFormController.editDataFromCommit(nameTF.getText(), isRelease, commitId);
            formController.setNameToItem(commitFormId, nameTF.getText());
        });

    }


    public void showEditControlPanel() {

        String[] classData = formDataController.getCommitStringData(commitId);

        nameTF.setText(classData[0]);
        boolean isRelease = Boolean.valueOf(classData[1]);
        if(isRelease){
            rbYes.setSelected(true);
        }else{
            rbNo.setSelected(true);
        }
    }

    public void clearPanel(TableView<BranchTable> tableView) {
        nameTF.setText("");
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
        rbYes.setSelected(true);
    }

    public boolean isRelease() {
        return isRelease;
    }


}
