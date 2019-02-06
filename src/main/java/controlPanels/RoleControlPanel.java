package controlPanels;

import Controllers.FormController;
import Controllers.FormDataController;
import abstractControlPane.DescriptionControlPanel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import services.Constans;
import tables.BasicTable;
import tables.RoleTable;

public class RoleControlPanel extends DescriptionControlPanel {

    protected Label typeLB;
    protected Label roleTypeLB;
    protected ChoiceBox<BasicTable> roleTypeCB;
    protected int roleTypeIndex;

    public RoleControlPanel(String buttonName, FormDataController formDataController, FormController formController){
        super(buttonName, formDataController, formController);
        this.roleTypeIndex = 0;
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
        roleTypeLB = new Label("Type: ");
        roleTypeCB = new ChoiceBox<>(formController.getRoleTypeObservable());
        roleTypeCB.getSelectionModel().selectedIndexProperty().addListener(roleListener);
        roleTypeCB.setMaxWidth(Constans.checkComboBox);
        descriptionLB = new Label("Description: ");
        descriptionTF = new TextField();

        controlPane.add(descriptionLB, 2, 0);
        controlPane.add(descriptionTF, 3, 0);
        controlPane.add(roleTypeLB, 4, 0);
        controlPane.add(roleTypeCB, 5, 0);
        controlPane.add(button, 6, 0);

        creatSceneCanvas();
        return controlPane;
    }

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        RoleTable roleTable = (RoleTable) basicTable;
        int id = roleTable.getId();
        String[] roleData = formDataController.getRoleStringData(id);
        roleTypeCB.getSelectionModel().select(Integer.parseInt(roleData[2]));
        nameTF.setText(roleData[0]);
        descriptionTF.setText(roleData[1]);

        button.setOnAction(event ->{
            roleTable.setName(id + "_" + nameTF.getText());
            roleTable.setDescription(descriptionTF.getText());
            formDataController.editDataFromRole(nameTF.getText(), roleTable, roleTypeIndex, id);
            tableView.refresh();
            nameTF.setText("");
            descriptionTF.setText("");
            tableView.getSelectionModel().clearSelection();
            this.close();
        });

        this.show();

    }


    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Role-Type
     */
    ChangeListener<Number> roleListener = new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            roleTypeIndex = newValue.intValue();

        }
    };

    public void clearSelection() {
        roleTypeCB.getSelectionModel().clearSelection();
    }

    public int getRoleTypeIndex() {
        return roleTypeIndex;
    }


}
