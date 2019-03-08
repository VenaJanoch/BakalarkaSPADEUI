package controlPanels;

import abstractControlPane.DateDescControlPanel;
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
import tables.BasicTable;
import tables.PhaseTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PhaseControlPanel extends DateDescControlPanel {

    /**
     * Globální proměnné třídy
     */
    private Label configLB;
    private Label milestoneLB;
    private Label workUnitLB;

    private ChoiceBox<BasicTable> configCB;
    private ChoiceBox<BasicTable> milestoneCB;
    private CheckComboBox<BasicTable> workUnitCB;

    private int milestoneIndex = 0;
    private int configIndex = 0;
    private ObservableList<Integer> workUnitIndicies;
    private SegmentLists segmentLists;

    public PhaseControlPanel(String buttonName, IFormDataController formDataController,
                             IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);
        workUnitIndicies = FXCollections.observableArrayList();
        this.segmentLists = formController.getSegmentLists();
        createControlPanel();
    }

    public GridPane createControlPanel(){

        dateLB.setText("End-Date");

        configLB = new Label("Configuration: ");
        configCB = new ChoiceBox<>();
        configCB.getSelectionModel().selectedIndexProperty().addListener(configListener);

        milestoneLB = new Label("Milestone: ");
        milestoneCB = new ChoiceBox<>();
        milestoneCB.getSelectionModel().selectedIndexProperty().addListener(milestoneListener);

        workUnitLB = new Label("Work Unit: ");
        workUnitCB = new CheckComboBox<>(segmentLists.getWorkUnitsObservable());
        workUnitCB.setMaxWidth(Constans.checkComboBox);

        workUnitCB.getCheckModel().getCheckedItems().addListener(new ListChangeListener<BasicTable>() {

            public void onChanged(ListChangeListener.Change<? extends BasicTable> c) {
                workUnitIndicies = workUnitCB.getCheckModel().getCheckedIndices();
            }
        });

        configCB.setItems(segmentLists.getConfigObservable());
        milestoneCB.setItems(segmentLists.getMilestoneObservable());

        controlPane.add(configLB, 0, 3);
        controlPane.setHalignment(configLB, HPos.LEFT);
        controlPane.add(configCB, 1, 3);

        controlPane.add(milestoneLB, 0, 4);
        controlPane.setHalignment(milestoneLB, HPos.LEFT);
        controlPane.add(milestoneCB, 1, 4);

        controlPane.add(workUnitLB, 0, 5);
        controlPane.setHalignment(workUnitLB, HPos.LEFT);
        controlPane.add(workUnitCB, 1, 5);

        controlPane.add(button, 2, 6);

        return controlPane;
    }

    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Milestone
     */
    ChangeListener<Number> milestoneListener = new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            milestoneIndex = newValue.intValue();

        }
    };

    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Configuration
     */
    ChangeListener<Number> configListener = new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            configIndex = newValue.intValue();

        }
    };

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        PhaseTable phaseTable = (PhaseTable) basicTable;
        int id = phaseTable.getId();
        String[] milestoneData = formDataController.getPhaseStringData(id);

        nameTF.setText(milestoneData[0]);
        if (milestoneData[1] != null){
            descriptionTF.setText(milestoneData[1]);
        }

        if(milestoneData[2] != null){
            configCB.getSelectionModel().select(Integer.parseInt(milestoneData[2]));
        }

        if(milestoneData[3] != null){
            milestoneCB.getSelectionModel().select(Integer.parseInt(milestoneData[3]));
        }

        if(milestoneData[4] != null){
            dateDP.setValue(LocalDate.parse(milestoneData[4]));
        }

        List<Integer> workUnits = formDataController.getWorkUnitFromSegment(id, SegmentType.Phase);
        if(workUnits != null){
            for(int i : workUnits){
                workUnitCB.getCheckModel().check(i);
            }
        }


        button.setOnAction(event ->{

            phaseTable.setName(id + "_" + nameTF.getText());
            if (configCB.getValue() != null){
                phaseTable.setConfiguration(configCB.getValue().getName());
            }

            if (milestoneCB.getValue() != null){
                phaseTable.setMilestone(milestoneCB.getValue().getName());
            }
            String desc = "null";
            if (descriptionTF.getText() != null){
                desc = descriptionTF.getText();
            }

            LocalDate date = LocalDate.of(1900,1,1);
            if(dateDP.getValue() != null){
                date = dateDP.getValue();
            }
            editFormController.editDataFromPhase(nameTF.getText(), desc ,date, milestoneIndex, configIndex, new ArrayList<>(workUnitIndicies), phaseTable, id);

            clearPanelCB(tableView);
        });
    }

    public Button getButton() {
        return button;
    }

    public void clearPanelCB(TableView tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }

    public ChoiceBox<BasicTable> getConfigCB() {
        return configCB;
    }

    public ChoiceBox<BasicTable> getMilestoneCB() {
        return milestoneCB;
    }
}
