package controlPanels;

import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.RoleClass;
import SPADEPAC.RoleSuperClass;
import abstractControlPane.NameControlPanel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import services.ClassSwitcher;
import services.SegmentType;
import tables.BasicTable;
import tables.CPRTable;
import tables.ClassTable;

public class ConfigPersonRelationControlPanel extends NameControlPanel {
    private Label roleLB;
    private ComboBox roleCB;

    protected int roleIndex;

    public ConfigPersonRelationControlPanel(String buttonName, FormDataController formDataController, FormController formController){
        super(buttonName, formDataController, formController);
        roleIndex = 0;
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


        roleLB = new Label("Role: ");

        roleCB = new ComboBox<>(formDataController.getRoleList());
        roleCB.getSelectionModel().selectedIndexProperty().addListener(roleListener);

        controlPane.add(roleLB, 2, 0);
        controlPane.add(roleCB, 3, 0);
        controlPane.add(button, 4, 0);

        creatSceneCanvas();
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
            formDataController.editDataFromCPR(nameTF.getText(), this.roleIndex, cprTable);
            nameTF.setText("");
            tableView.refresh();
            tableView.getSelectionModel().clearSelection();
            this.close();
        });

        this.show();

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
}
