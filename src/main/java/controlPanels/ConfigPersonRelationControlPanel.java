package controlPanels;

import controllers.FormController;
import abstractControlPane.NameControlPanel;
import graphics.ComboBoxItem;
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
    private ComboBoxItem roleCB;


    public ConfigPersonRelationControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);
        createControlPanel();
    }

    @Override
    protected void createBaseControlPanel() {

    }


    public void createControlPanel(){


        roleCB = new ComboBoxItem("Role: ", formController.getRoleObservable());

        controlPanelController.setComboBoxItemToControlPanel(controlPane, roleCB, 0, 1);

        controlPane.add(button, 2, 2);


    }


    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        CPRTable cprTable = (CPRTable) basicTable;
        int id = cprTable.getId();
        String[] classData = formDataController.getCPRStringData(id);

        nameTF.setTextToTextField(classData[0]);

        controlPanelController.setValueComboBox(roleCB, classData, 1);

        button.setOnAction(event ->{
            editFormController.editDataFromCPR(nameTF.getTextFromTextField(), roleCB.getItemIndex(), cprTable);
            clearPanel(tableView);

        });
    }



    public void clearPanel(TableView<CPRTable> tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }
}
