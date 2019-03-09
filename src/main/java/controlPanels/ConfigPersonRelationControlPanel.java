package controlPanels;

import controllers.FormController;
import abstractControlPane.NameControlPanel;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import tables.BasicTable;
import tables.CPRTable;

public class ConfigPersonRelationControlPanel extends NameControlPanel {
    private Label roleLB;
    private ComboBox roleCB;

    protected int roleIndex;

    private boolean isShowRole;
    private Button roleButton;


    public ConfigPersonRelationControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);
        createControlPanel();
        roleIndex = 0;
    }

    @Override
    protected void createBaseControlPanel() {

    }


    private void setExitButtonsActions(){
        isShowRole = false;
        roleButton = new Button("+");
        roleButton.setOnAction(event -> {
            if (!isShowRole){
                roleCB.setVisible(true);
                isShowRole = true;
                roleButton.setText("-");
            }else{
                roleCB.setVisible(false);
                roleCB.getSelectionModel().clearSelection();
                isShowRole = false;
                roleButton.setText("+");
            }
        });
    }


    public GridPane createControlPanel(){


        roleLB = new Label("Role: ");

        roleCB = new ComboBox<>(formDataController.getRoleList());
        roleCB.getSelectionModel().selectedIndexProperty().addListener(roleListener);
        roleCB.setVisible(false);
        setExitButtonsActions();

        controlPane.add(roleButton, 0, 1);
        controlPane.add(roleLB, 1, 1);
        controlPane.add(roleCB, 2, 1);
        controlPane.add(button, 2, 2);

        return controlPane;
    }


    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        CPRTable cprTable = (CPRTable) basicTable;
        int id = cprTable.getId();
        String[] classData = formDataController.getCPRStringData(id);

        nameTF.setText(classData[0]);
        int roleIndex = Integer.parseInt(classData[1]);
        roleCB.getSelectionModel().select(roleIndex);

        button.setOnAction(event ->{
            editFormController.editDataFromCPR(nameTF.getText(), this.roleIndex, cprTable);
            clearPanel(tableView);
           // this.close();
        });

      //  this.show();

    }


    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Role
     */
    ChangeListener<Number> roleListener = new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            roleIndex = newValue.intValue();

        }
    };

    public int getRoleIndex() {
        return roleIndex;
    }

    public void clearPanel(TableView<CPRTable> tableView) {
        nameTF.setText("");
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
        roleCB.getSelectionModel().clearSelection();
    }
}
