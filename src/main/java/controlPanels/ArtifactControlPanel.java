package controlPanels;

import SPADEPAC.ArtifactClass;
import abstractControlPane.DateDescControlPanel;
import controllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import services.SegmentLists;
import services.SegmentType;
import tables.ArtifactTable;
import tables.BasicTable;
import tables.ChangeTable;
import tables.PhaseTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArtifactControlPanel extends DateDescControlPanel {

    /**
     * Globální proměnné třídy
     */
    private Label roleLB;
    private Label typeLB;

    private ChoiceBox<BasicTable> roleCB;
    private ComboBox<ArtifactClass> typeCB;

    private int roleIndex = 0;
    private int typeIndex = 0;

    private RadioButton existRB;
    private boolean exist;
    private SegmentLists segmentLists;

    private ArtifactTable artifactTable;

    public ArtifactControlPanel(String buttonName, IFormDataController formDataController,
                                IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);

        this.segmentLists = formController.getSegmentLists();
        createControlPanel();
    }

    public GridPane createControlPanel(){

        dateLB.setText("Created: ");

        roleLB = new Label("Author: ");
        roleCB = new ChoiceBox<>();
        roleCB.getSelectionModel().selectedIndexProperty().addListener(typeListener);

        typeLB = new Label("Mine Type: ");
        typeCB = new ComboBox<ArtifactClass>(FXCollections.observableArrayList(ArtifactClass.values()));
        typeCB.getSelectionModel().selectedIndexProperty().addListener(roleListener);

     
        roleCB.setItems(segmentLists.getRoleObservable());

        existRB = new RadioButton("Exist");
        existRB.setSelected(true);
        
        controlPane.add(roleLB, 0, 3);
        controlPane.setHalignment(roleLB, HPos.LEFT);
        controlPane.add(roleCB, 1, 3);

        controlPane.add(typeLB, 0, 4);
        controlPane.setHalignment(typeLB, HPos.LEFT);
        controlPane.add(typeCB, 1, 4);

        controlPane.add(existRB, 0, 5);
        controlPane.add(button, 2, 6);

        return controlPane;
    }

    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Milestone
     */
    ChangeListener<Number> roleListener = new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            roleIndex = newValue.intValue();

        }
    };

    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Configuration
     */
    ChangeListener<Number> typeListener = new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            typeIndex = newValue.intValue();

        }
    };

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        artifactTable = (ArtifactTable) basicTable;
        int id = artifactTable.getId();
        String[] artifactData = formDataController.getArtifactStringData(id);

        nameTF.setText(artifactData[0]);
        if (artifactData[1] != null){
            descriptionTF.setText(artifactData[1]);
        }

        if(artifactData[2] != null){
            roleCB.getSelectionModel().select(Integer.parseInt(artifactData[2]));
        }

        if(artifactData[3] != null){
            typeCB.getSelectionModel().select(Integer.parseInt(artifactData[3]));
        }

        if(artifactData[4] != null){
            dateDP.setValue(LocalDate.parse(artifactData[4]));
        }

        exist = Boolean.valueOf(artifactData[5]);
        existRB.setSelected(exist);

        button.setOnAction(event -> saveDataFromPanel(basicTable, tableView) );
    }

    public void saveDataFromPanel(BasicTable table, TableView tableView){
        int id = table.getId();
        artifactTable.setName(id + "_" + nameTF.getText());

        String desc = "null";
        if (descriptionTF.getText() != null){
            desc = descriptionTF.getText();
        }

        LocalDate date = LocalDate.of(1900,1,1);
        if(dateDP.getValue() != null){
            date = dateDP.getValue();
        }

        exist = existRB.isSelected();

        editFormController.editDataFromArtifact(nameTF.getText(), desc , exist, roleIndex, typeIndex, date, artifactTable, id);

        clearPanelCB(tableView);
    }


    public Button getButton() {
        return button;
    }

    public void clearPanelCB(TableView tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }
}
