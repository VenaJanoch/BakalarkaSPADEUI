package controlPanels;

import abstractControlPane.DateDescControlPanel;
import abstractControlPane.DescriptionControlPanel;
import controllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.CheckComboBox;
import services.Constans;
import services.SegmentLists;
import services.SegmentType;
import tables.ActivityTable;
import tables.BasicTable;
import tables.PhaseTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActivityControlPanel extends DescriptionControlPanel {

    /**
     * Globální proměnné třídy
     */

    private Label workUnitLB;

    private CheckComboBox<BasicTable> workUnitCB;

    private ObservableList<Integer> workUnitIndicies;
    private SegmentLists segmentLists;
    private ActivityTable activityTable;


    public ActivityControlPanel(String buttonName, IFormDataController formDataController,
                                IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);
        workUnitIndicies = FXCollections.observableArrayList();
        this.segmentLists = formController.getSegmentLists();
        addItemsToControlPanel();
    }

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        activityTable = (ActivityTable) basicTable;
        int id = activityTable.getId();
        String[] milestoneData = formDataController.getActivityStringData(id);

        nameTF.setText(milestoneData[0]);
        if (milestoneData[1] != null){
            descriptionTF.setText(milestoneData[1]);
        }

        List<Integer> workUnits = formDataController.getWorkUnitFromSegment(id, SegmentType.Activity);
        if(workUnits != null){
            for(int i : workUnits){
                workUnitCB.getCheckModel().check(i);
            }
        }


        button.setOnAction(event -> saveDataFromPanel(activityTable, tableView));
    }

    @Override
    protected void addItemsToControlPanel() {
        workUnitLB = new Label("Work Unit: ");
        workUnitCB = new CheckComboBox<>(segmentLists.getWorkUnitsObservable());
        workUnitCB.setMaxWidth(Constans.checkComboBox);

        workUnitCB.getCheckModel().getCheckedItems().addListener(new ListChangeListener<BasicTable>() {

            public void onChanged(Change<? extends BasicTable> c) {
                workUnitIndicies = workUnitCB.getCheckModel().getCheckedIndices();
            }
        });

        controlPane.add(workUnitLB, 0, 3);
        controlPane.setHalignment(workUnitLB, HPos.LEFT);
        controlPane.add(workUnitCB, 1, 3);

        controlPane.add(button, 2, 4);

    }

    public void saveDataFromPanel(BasicTable table, TableView tableView){
        int id = table.getId();
        activityTable.setName(id + "_" + nameTF.getText());

        String desc = "null";
        if (descriptionTF.getText() != null){
            desc = descriptionTF.getText();
        }


        editFormController.editDataFromActivity(nameTF.getText(), desc , new ArrayList<Integer>(workUnitIndicies), activityTable, id);

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
