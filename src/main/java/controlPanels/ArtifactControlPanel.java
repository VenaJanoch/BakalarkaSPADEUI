package controlPanels;

import SPADEPAC.ArtifactClass;
import SPADEPAC.WorkUnitPriorityClass;
import abstractControlPane.DateDescControlPanel;
import controllers.FormController;
import graphics.ComboBoxItem;
import graphics.ControlPanelLine;
import interfaces.IControlPanel;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import services.*;
import tables.ArtifactTable;
import tables.BasicTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArtifactControlPanel extends DateDescControlPanel implements IControlPanel {

    /**
     * Globální proměnné třídy
     */


    private SegmentLists segmentLists;
    private boolean exist;
    private ArtifactTable artifactTable;
    private ObservableList<String> artifactArray;

    private  int artifactId;
    private  int artifactFormIndex;
    public ArtifactControlPanel(String buttonName, IFormDataController formDataController,
                                IEditFormController editFormController, FormController formController, ArtifactTable artifactTable, int id, int formIndex){
        super(buttonName, formDataController, editFormController, formController);
        this.artifactTable = artifactTable;
        this.artifactId = id;
        this.artifactFormIndex = formIndex;
        this.segmentLists = formController.getSegmentLists();
        int i = 0;

        artifactArray = FXCollections.observableArrayList();
        for(ArtifactClass classItem : ArtifactClass.values()){
            artifactArray.add(classItem.name());
            i++;
        }


        lineList.add(new ControlPanelLineObject("Type: ", ControlPanelLineType.ComboBox, ParamType.ArtifactType, artifactArray));
        createControlPanel();
    }

    public void createControlPanel(){

        controlPanelController.setRadioButton(this, 1, "Exist: ", false);
        controlPanelController.setCountLine(this, 2, new ControlPanelLine(lineList,this, controlPanelController ));
        controlPanelController.createNewLine(this, lineList);

        button.setOnAction(event -> saveDataFromPanel());
        }

    @Override
    public void showEditControlPanel() {



        List[] artifactData = formDataController.getArtifactStringData(artifactId);
        controlPanelController.resetPanel(controlPane);
        createControlPanel();

        controlPanelController.setValueTextField(this, lineList ,ParamType.Name, artifactData, artifactData[5], 0);
        controlPanelController.setValueTextField(this, lineList ,ParamType.Description, artifactData, artifactData[6], 1);
        controlPanelController.setValueTextField(this, lineList ,ParamType.Role, artifactData, artifactData[7], 2);
        controlPanelController.setValueTextField(this, lineList ,ParamType.Date, artifactData, artifactData[8], 3);
        controlPanelController.setValueTextField(this, lineList ,ParamType.ArtifactType, artifactData, artifactData[9], 4);

        boolean exist = false;
        List boolList = artifactData[10];
        if (boolList.size() != 1){
            exist = true;
        }
        controlPanelController.setValueRadioButton(exist);
        controlPanelController.setCountToCountLine((Integer)boolList.get(0));

    }

    public void saveDataFromPanel(){
        int id = artifactId;


        ArrayList<Integer> roleIndicators = new ArrayList<>();
        ArrayList<Integer> typeIndicators = new ArrayList<>();
        ArrayList<Integer> nameIndicators = new ArrayList<>();
        ArrayList<Integer> descIndicators = new ArrayList<>();
        ArrayList<Integer> dateIndicators = new ArrayList<>();
        ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
        ArrayList<String> desc = controlPanelController.processTextLines(ParamType.Description, descIndicators);
        ArrayList<Integer> role = controlPanelController.processComboBoxLines(ParamType.Role, roleIndicators);
        ArrayList<Integer> type = controlPanelController.processComboBoxLines(ParamType.ArtifactType, typeIndicators);
        ArrayList<LocalDate> date = controlPanelController.processDateLines(ParamType.Date, dateIndicators);

        exist = controlPanelController.isMain();
        String count = controlPanelController.getInstanceCount();
        editFormController.editDataFromArtifact(name, desc , exist, role, type, date, nameIndicators, descIndicators, roleIndicators,
                typeIndicators, dateIndicators, artifactTable, count, id);

    }


    public Button getButton() {
        return button;
    }

    public void clearPanelCB(TableView tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }

    @Override
    protected void showEditControlPanel(BasicTable basicTable, TableView tableView) {

    }
}
