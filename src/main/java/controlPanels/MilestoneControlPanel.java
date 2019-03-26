package controlPanels;

import controllers.FormController;
import abstractControlPane.DescriptionControlPanel;
import graphics.CheckComboBoxItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import services.*;
import tables.BasicTable;
import tables.MilestoneTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MilestoneControlPanel extends DescriptionControlPanel {

    private MilestoneTable milestoneTable;

    public MilestoneControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController,editFormController, formController);
        SegmentLists segmentLists = formController.getSegmentLists();
        lineList.add(new ControlPanelLineObject("Criterions: ", ControlPanelLineType.CheckBox, ParamType.Criterion, segmentLists.getCriterionObservable()));

        addItemsToControlPanel();
    }


    protected void addItemsToControlPanel(){

        controlPanelController.createNewLine(this, lineList);

    }



    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        milestoneTable = (MilestoneTable) basicTable;
        int id = milestoneTable.getId();

        List[] milestoneData = formDataController.getMilestoneStringData(id);

        controlPanelController.resetPanel(controlPane);
        addItemsToControlPanel();

        ArrayList<ArrayList<Integer>>  criteriaID = formDataController.getCriterionFromMilestone(id);


        controlPanelController.setValueTextField(this, lineList ,ParamType.Name, milestoneData, milestoneData[2], 0);
        controlPanelController.setValueTextField(this, lineList ,ParamType.Description, milestoneData, milestoneData[3], 1);

        controlPanelController.setValueCheckComboBox(this, lineList ,ParamType.Criterion, criteriaID, milestoneData[4]);


        button.setOnAction(event ->{

            saveDataFromPanel(milestoneTable, tableView);


        });

    }

    public void saveDataFromPanel(BasicTable table, TableView tableView){
        int id =   table.getId();

        ArrayList<Integer> nameIndicators = new ArrayList<>();
        ArrayList<Integer> descIndicators = new ArrayList<>();
        ArrayList<Integer> criterionIndicators = new ArrayList<>();
        ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
        ArrayList<String> desc = controlPanelController.processTextLines(ParamType.Description, descIndicators);
        ArrayList<ArrayList<Integer>> criterion = controlPanelController.processCheckComboBoxLines(ParamType.Criterion, criterionIndicators);


        editFormController.editDataFromMilestone(name, nameIndicators, desc, descIndicators, milestoneTable, criterion, criterionIndicators, id);

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
