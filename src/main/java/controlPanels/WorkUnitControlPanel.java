package controlPanels;

import abstractControlPane.DateDescControlPanel;
import abstractControlPane.DescriptionControlPanel;
import abstractControlPane.NameControlPanel;
import controllers.FormController;
import controllers.WorkUnitControlPanelController;
import graphics.ComboBoxItem;
import graphics.TextFieldItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.CheckComboBox;
import services.Constans;
import services.SegmentLists;
import services.SegmentType;
import tables.BasicTable;
import tables.PhaseTable;
import tables.WorkUnitTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WorkUnitControlPanel extends DescriptionControlPanel {

    /**
     * Globální proměnné třídy
     */

    private TextFieldItem estimatedTimeTF;
    private RadioButton existRB;
    private ComboBoxItem priorityCB;
    private ComboBoxItem severityCB;
    private ComboBoxItem resolutionCB;
    private ComboBoxItem statusCB;
    private TextFieldItem categoryTF;
    private ComboBoxItem typeCB;
    private ComboBoxItem asigneeRoleCB;
    private ComboBoxItem authorRoleCB;
    private Button addRelationButton;

    private SegmentLists segmentLists;

    private WorkUnitTable workUnitTable;
    private WorkUnitControlPanelController controller;

    private boolean exist;

    public WorkUnitControlPanel(String buttonName, IFormDataController formDataController,
                                IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);
        this.segmentLists = formController.getSegmentLists();
        this.controller = new WorkUnitControlPanelController(this, segmentLists);
        addItemsToControlPanel();
    }

    protected void addItemsToControlPanel(){

        estimatedTimeTF = new TextFieldItem("Estimated Time: ");
        priorityCB = new ComboBoxItem("Priority: ", segmentLists.getPriorityTypeObservable());
        severityCB = new ComboBoxItem("Severity :", segmentLists.getSeverityTypeObservable());
        categoryTF = new TextFieldItem("Category: ");
        typeCB = new ComboBoxItem("Type :", segmentLists.getTypeObservable());
        asigneeRoleCB = new ComboBoxItem("Asignee :", segmentLists.getRoleObservable());
        authorRoleCB = new ComboBoxItem("Autohor: ", segmentLists.getRoleObservable());
        resolutionCB = new ComboBoxItem("Resolution: ", segmentLists.getResolutionTypeObservable());
        statusCB = new ComboBoxItem("Status: ", segmentLists.getStatusTypeObservable());


        existRB = new RadioButton("Exist");
        existRB.setSelected(true);

        addRelationButton = new Button("add Relation");
        addRelationButton.setOnAction(event -> controller.addRelationToPanel(button, addRelationButton, existRB));

        controlPanelController.setTextItemToControlPanel(controlPane, estimatedTimeTF, 0, 2);
        controlPanelController.setTextItemToControlPanel(controlPane, categoryTF, 0, 3);
        controlPanelController.setComboBoxItemToControlPanel(controlPane, priorityCB, 0, 4);
        controlPanelController.setComboBoxItemToControlPanel(controlPane, severityCB, 0, 5);
        controlPanelController.setComboBoxItemToControlPanel(controlPane, typeCB, 0, 6);
        controlPanelController.setComboBoxItemToControlPanel(controlPane, resolutionCB, 0, 7);
        controlPanelController.setComboBoxItemToControlPanel(controlPane, statusCB, 0, 8);
        controlPanelController.setComboBoxItemToControlPanel(controlPane, asigneeRoleCB, 0, 9);
        controlPanelController.setComboBoxItemToControlPanel(controlPane, authorRoleCB, 0, 10);

        controlPane.add(addRelationButton, 3,11);

        controlPane.add(existRB, 2, 12);
        controlPane.add(button, 3, 13);

        controller.addRelationToPanel(button, addRelationButton, existRB);


    }


    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        workUnitTable = (WorkUnitTable) basicTable;
        int id = workUnitTable.getId();
        String[] milestoneData = formDataController.getWorkUnitStringData(id);

        nameTF.setTextToTextField(milestoneData[0]);

        controlPanelController.setValueTextField(descriptionTF, milestoneData, 1);
        controlPanelController.setValueTextField(estimatedTimeTF, milestoneData, 2);
        controlPanelController.setValueComboBox(priorityCB, milestoneData, 3);
        controlPanelController.setValueComboBox(severityCB, milestoneData, 4);
        controlPanelController.setValueComboBox(resolutionCB, milestoneData, 5);
        controlPanelController.setValueComboBox(statusCB, milestoneData, 6);
        controlPanelController.setValueTextField(categoryTF, milestoneData, 7);
        controlPanelController.setValueComboBox(typeCB, milestoneData, 8);
        controlPanelController.setValueComboBox(asigneeRoleCB, milestoneData, 9);
        controlPanelController.setValueComboBox(authorRoleCB, milestoneData, 10);

        exist = Boolean.valueOf(milestoneData[11]);
        existRB.setSelected(exist);

        button.setOnAction(event -> saveDataFromPanel(basicTable, tableView));
    }


    public void saveDataFromPanel(BasicTable table, TableView tableView){
        int id = table.getId();
        workUnitTable.setName(id + "_" + nameTF.getTextFromTextField());


        String desc = controlPanelController.checkValueFromTextItem(descriptionTF);
        String estimated = controlPanelController.checkValueFromTextItem(estimatedTimeTF);
        String category = controlPanelController.checkValueFromTextItem(categoryTF);


        editFormController.editDataFromWorkUnit(nameTF.getTextFromTextField(), desc, estimated, priorityCB.getItemIndex(),
                severityCB.getItemIndex(), resolutionCB.getItemIndex(), statusCB.getItemIndex(), category,
                typeCB.getItemIndex(), asigneeRoleCB.getItemIndex(), authorRoleCB.getItemIndex(), existRB.isSelected(), workUnitTable, id);

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
