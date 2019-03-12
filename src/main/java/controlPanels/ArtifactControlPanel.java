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

        controlPane.add(roleCB.getItemButton(), 0, 3);
        controlPane.add(roleCB.getItemNameLB(), 1, 3);
        controlPane.setHalignment(roleCB.getItemNameLB(), HPos.LEFT);
        controlPane.add(roleCB.getItemCB(), 2, 3);

        controlPane.add(typeCB.getItemButton(), 0, 4);
        controlPane.add(typeCB.getItemNameLB(), 1, 4);
        controlPane.setHalignment(typeCB.getItemNameLB(), HPos.LEFT);
        controlPane.add(typeCB.getItemCB(), 2, 4);

        controlPane.add(existRB, 0, 5);
        controlPane.add(button, 2, 6);

        button.setOnAction(event -> saveDataFromPanel() );

    }

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {


        String[] artifactData = formDataController.getArtifactStringData(artifactId);

        nameTF.setText(artifactData[0]);
        descriptionTF.setShowItem(false);
        if (artifactData[1] != null){
            descriptionTF.setTextToTextField(artifactData[1]);
            descriptionTF.setShowItem(true);
        }

        roleCB.setShowItem(false);
        if(artifactData[2] != null){
            roleCB.selectItemInComboBox(Integer.parseInt(artifactData[2]));
            roleCB.setShowItem(true);
        }

        typeCB.setShowItem(false);
        if(artifactData[3] != null){
            typeCB.selectItemInComboBox(Integer.parseInt(artifactData[3]));
            typeCB.setShowItem(false);
        }

        dateDP.setShowItem(false);
        if(artifactData[4] != null){
            dateDP.setDateToPicker(LocalDate.parse(artifactData[4]));
            dateDP.setShowItem(false);
        }

        exist = Boolean.valueOf(artifactData[5]);
        existRB.setSelected(exist);

    }

    public void saveDataFromPanel(){
        int id = artifactId;
        artifactTable.setName(id + "_" + nameTF.getText());

        String desc = "null";
        if (descriptionTF.getTextFromTextField() != null){
            desc = descriptionTF.getTextFromTextField();
        }

        LocalDate date = LocalDate.of(1900,1,1);
        if(dateDP.getDateFromDatePicker() != null){
            date = dateDP.getDateFromDatePicker();
        }

        exist = existRB.isSelected();

        editFormController.editDataFromArtifact(nameTF.getText(), desc , exist, roleCB.getItemIndex(), typeCB.getItemIndex(), date, artifactTable, id);
        formController.setNameToItem(artifactFormIndex, nameTF.getText());
    }


    public Button getButton() {
        return button;
    }

    public void clearPanelCB(TableView tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }
}
