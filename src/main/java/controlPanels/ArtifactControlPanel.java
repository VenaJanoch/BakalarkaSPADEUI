package controlPanels;

import SPADEPAC.ArtifactClass;
import abstractControlPane.DateDescControlPanel;
import controllers.FormController;
import graphics.ComboBoxItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import services.SegmentLists;
import tables.ArtifactTable;
import tables.BasicTable;

import java.time.LocalDate;

public class ArtifactControlPanel extends DateDescControlPanel {

    /**
     * Globální proměnné třídy
     */

    private ComboBoxItem roleCB;
    private ComboBoxItem typeCB;

    private RadioButton existRB;
    private boolean exist;
    private SegmentLists segmentLists;

    private ArtifactTable artifactTable;

    private  int artifactId;
    private  int artifactFormIndex;
    public ArtifactControlPanel(String buttonName, IFormDataController formDataController,
                                IEditFormController editFormController, FormController formController, ArtifactTable artifactTable, int id, int formIndex){
        super(buttonName, formDataController, editFormController, formController);
        this.artifactTable = artifactTable;
        this.artifactId = id;
        this.artifactFormIndex = formIndex;
        this.segmentLists = formController.getSegmentLists();
        createControlPanel();
    }

    public void createControlPanel(){

        dateDP.setItemNameLB("Created: ");

        roleCB = new ComboBoxItem("Author: ", formController.getRoleObservable());
        typeCB = new ComboBoxItem("Mine Type: ", FXCollections.observableArrayList(ArtifactClass.values()));

        existRB = new RadioButton("Exist");
        existRB.setSelected(true);

        controlPanelController.setComboBoxItemToControlPanel(controlPane, typeCB,  0, 4);

        controlPane.add(existRB, 1, 5);
        controlPane.add(button, 3, 6);

        button.setOnAction(event -> saveDataFromPanel() );

    }

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {


        String[] artifactData = formDataController.getArtifactStringData(artifactId);

        nameTF.setTextToTextField(artifactData[0]);

        controlPanelController.setValueTextField(descriptionTF, artifactData, 1);
        controlPanelController.setValueComboBox(roleCB, artifactData, 2);
        controlPanelController.setValueComboBox(typeCB, artifactData, 3);
        controlPanelController.setValueDatePicker(dateDP, artifactData, 4);

        exist = Boolean.valueOf(artifactData[5]);
        existRB.setSelected(exist);

    }

    public void saveDataFromPanel(){
        int id = artifactId;
        artifactTable.setName(id + "_" + nameTF.getTextFromTextField());

        String desc = controlPanelController.checkValueFromTextItem(descriptionTF);
        LocalDate date = controlPanelController.checkValueFromDateItem(dateDP);

        exist = existRB.isSelected();

        editFormController.editDataFromArtifact(nameTF.getTextFromTextField(), desc , exist, roleCB.getItemIndex(), typeCB.getItemIndex(), date, artifactTable, id);
        formController.setNameToItem(artifactFormIndex, nameTF.getTextFromTextField());
    }


    public Button getButton() {
        return button;
    }

    public void clearPanelCB(TableView tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }
}
