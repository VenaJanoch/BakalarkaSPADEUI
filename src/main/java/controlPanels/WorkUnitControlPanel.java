package controlPanels;

import abstractControlPane.DateDescControlPanel;
import abstractControlPane.DescriptionControlPanel;
import abstractControlPane.NameControlPanel;
import controllers.FormController;
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

    private SegmentLists segmentLists;

    private WorkUnitTable workUnitTable;

    public WorkUnitControlPanel(String buttonName, IFormDataController formDataController,
                                IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);
        this.segmentLists = formController.getSegmentLists();
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

        controlPane.add(estimatedTimeTF.getItemButton(), 0, 2);
        controlPane.add(estimatedTimeTF.getItemNameLB(), 1, 2);
        controlPane.setHalignment(estimatedTimeTF.getItemNameLB(), HPos.RIGHT);
        controlPane.add(estimatedTimeTF.getItemTF(), 2, 2);

        controlPane.add(categoryTF.getItemButton(), 0, 3);
        controlPane.add(categoryTF.getItemNameLB(), 1, 3);
        controlPane.setHalignment(categoryTF.getItemNameLB(), HPos.RIGHT);
        controlPane.add(categoryTF.getItemTF(), 2, 3);

        controlPane.add(priorityCB.getItemButton(), 0, 4);
        controlPane.add(priorityCB.getItemNameLB(), 1, 4);
        controlPane.setHalignment(priorityCB.getItemNameLB(), HPos.RIGHT);
        controlPane.add(priorityCB.getItemCB(), 2, 4);

        controlPane.add(severityCB.getItemButton(), 0, 5);
        controlPane.add(severityCB.getItemNameLB(), 1, 5);
        controlPane.setHalignment(severityCB.getItemNameLB(), HPos.RIGHT);
        controlPane.add(severityCB.getItemCB(), 2, 5);

        controlPane.add(typeCB.getItemButton(), 0, 6);
        controlPane.add(typeCB.getItemNameLB(), 1, 6);
        controlPane.setHalignment(typeCB.getItemNameLB(), HPos.RIGHT);
        controlPane.add(typeCB, 2, 6);

        controlPane.add(resolutionCB.getItemButton(), 0, 7);
        controlPane.add(resolutionCB.getItemNameLB(), 1, 7);
        controlPane.setHalignment(resolutionCB.getItemNameLB(), HPos.RIGHT);
        controlPane.add(resolutionCB.getItemCB(), 2, 7);

        controlPane.add(statusCB.getItemButton(), 0, 8);
        controlPane.add(statusCB.getItemNameLB(), 1, 8);
        controlPane.setHalignment(statusCB.getItemNameLB(), HPos.RIGHT);
        controlPane.add(statusCB.getItemCB(), 2, 8);

        controlPane.add(asigneeRoleCB.getItemButton(), 0, 9);
        controlPane.add(asigneeRoleCB.getItemNameLB(), 1, 9);
        controlPane.setHalignment(asigneeRoleCB.getItemNameLB(), HPos.RIGHT);
        controlPane.add(asigneeRoleCB.getItemCB(), 2, 9);

        controlPane.add(authorRoleCB.getItemButton(), 0, 10);
        controlPane.add(authorRoleCB.getItemNameLB(), 1, 10);
        controlPane.setHalignment(authorRoleCB.getItemNameLB(), HPos.RIGHT);
        controlPane.add(authorRoleCB.getItemCB(), 2, 10);

        controlPane.add(existRB, 1, 11);

        controlPane.add(button, 1, 12);


    }


    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        workUnitTable = (WorkUnitTable) basicTable;
        int id = workUnitTable.getId();
        String[] milestoneData = formDataController.getWorkUnitStringData(id);

        nameTF.setText(milestoneData[0]);

        descriptionTF.setShowItem(false);
        if (milestoneData[1] != null){
            descriptionTF.setShowItem(true);
            descriptionTF.setTextToTextField(milestoneData[1]);
        }
            estimatedTimeTF.setShowItem(false);
        if(milestoneData[2] != null){
            estimatedTimeTF.setShowItem(true);
            estimatedTimeTF.setTextToTextField(milestoneData[2]);
        }
        priorityCB.setShowItem(false);
        if(milestoneData[3] != null){
            priorityCB.setShowItem(true);
            priorityCB.selectItemInComboBox(Integer.parseInt(milestoneData[3]));
        }
            severityCB.setShowItem(false);
        if(milestoneData[4] != null){
            severityCB.setShowItem(true);
            severityCB.selectItemInComboBox(Integer.parseInt(milestoneData[4]));
        }

        resolutionCB.setShowItem(false);
        if(milestoneData[5] != null){
            resolutionCB.setShowItem(true);
            resolutionCB.selectItemInComboBox(Integer.parseInt(milestoneData[5]));
        }

        severityCB.setShowItem(false);
        if(milestoneData[6] != null){
            severityCB.setShowItem(true);
            statusCB.selectItemInComboBox(Integer.parseInt(milestoneData[6]));
        }

        categoryTF.setShowItem(false);
        if(milestoneData[7] != null){
            categoryTF.setShowItem(true);
            categoryTF.setTextToTextField(milestoneData[7]);
        }

        typeCB.setShowItem(false);
        if(milestoneData[8] != null){
            typeCB.setShowItem(true);
            typeCB.selectItemInComboBox(Integer.parseInt(milestoneData[8]));
        }
            asigneeRoleCB.setShowItem(false);
        if(milestoneData[9] != null){
            asigneeRoleCB.setShowItem(true);
            asigneeRoleCB.selectItemInComboBox(Integer.parseInt(milestoneData[9]));
        }

        authorRoleCB.setShowItem(false);
        if(milestoneData[10] != null){
            authorRoleCB.setShowItem(true);
            authorRoleCB.selectItemInComboBox(Integer.parseInt(milestoneData[10]));
        }

        button.setOnAction(event -> saveDataFromPanel(basicTable, tableView));
    }


    public void saveDataFromPanel(BasicTable table, TableView tableView){
        int id = table.getId();
        workUnitTable.setName(id + "_" + nameTF.getText());


        String desc = "null";
        if (descriptionTF.getTextFromTextField() != null){
            desc = descriptionTF.getTextFromTextField();
        }

        editFormController.editDataFromWorkUnit(nameTF.getText(), desc, estimatedTimeTF.getTextFromTextField(), priorityCB.getItemIndex(),
                severityCB.getItemIndex(), resolutionCB.getItemIndex(), statusCB.getItemIndex(), categoryTF.getTextFromTextField(),
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
