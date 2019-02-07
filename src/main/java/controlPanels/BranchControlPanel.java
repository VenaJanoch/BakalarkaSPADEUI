package controlPanels;

import Controllers.FormController;
import Controllers.FormDataController;
import abstractControlPane.NameControlPanel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import tables.BasicTable;
import tables.BranchTable;
import tables.CPRTable;

public class BranchControlPanel extends NameControlPanel {

    private Label isMainLB;
    private boolean isMain = true;

    private ToggleGroup group = new ToggleGroup();

    private RadioButton rbYes;
    private RadioButton rbNo;

    public BranchControlPanel(String buttonName, FormDataController formDataController, FormController formController){
        super(buttonName, formDataController, formController);
    }

    @Override
    protected void createBaseControlPanel() {

    }

    /**
     * Vytvoří scénu s formulářem
     *
     * @return Scene
     */
    private void creatSceneCanvas() {
        mainPanel.setCenter(controlPane);
        this.setScene(new Scene(mainPanel));

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

        creatSceneCanvas();
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
            formDataController.editDataFromBranch(nameTF.getText(), isMainBranch, branchTable);
            clearPanel(tableView);
            this.close();
        });

        this.show();

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
