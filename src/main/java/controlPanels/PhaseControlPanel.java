package controlPanels;

import abstractControlPane.DateDescControlPanel;
import abstractControlPane.WorkUnitControlPanel;
import abstractControlPane.WorkUnitDateControlPanel;
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

public class PhaseControlPanel extends WorkUnitDateControlPanel {

    /**
     * Globální proměnné třídy
     */
    private Label configLB;
    private Label milestoneLB;

    private ChoiceBox<BasicTable> configCB;
    private ChoiceBox<BasicTable> milestoneCB;

    private int milestoneIndex = 0;
    private int configIndex = 0;

    private boolean isShowConfig;
    private Button configButton;

    private boolean isShowMilestone;
    private Button milestoneButton;

    
    public PhaseControlPanel(String buttonName, IFormDataController formDataController,
                             IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);
        addItemsToControlPanel();
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
    
    
    private void setExitButtonsActions(){
        isShowConfig = false;
        configButton = new Button("+");
        configButton.setOnAction(event -> {
            if (!isShowConfig){
                configCB.setVisible(true);
                isShowConfig  = true;
                configButton.setText("-");
            }else{
                configCB.setVisible(false);
                configCB.getSelectionModel().clearSelection();
                isShowConfig = false;
                configButton.setText("+");
            }
        });

        isShowMilestone = false;
        milestoneButton = new Button("+");
        milestoneButton.setOnAction(event -> {
            if (!isShowMilestone){
                milestoneCB.setVisible(true);
                isShowMilestone  = true;
                milestoneButton.setText("-");
            }else{
                milestoneCB.setVisible(false);
                milestoneCB.getSelectionModel().clearSelection();
                isShowMilestone = false;
                milestoneButton.setText("+");
            }
        });
    }

    @Override
    protected void addItemsToControlPanel() {

        dateLB.setText("End-Date");

        setExitButtonsActions();

        configLB = new Label("Configuration: ");
        configCB = new ChoiceBox<>();
        configCB.getSelectionModel().selectedIndexProperty().addListener(configListener);
        configCB.setVisible(false);

        milestoneLB = new Label("Milestone: ");
        milestoneCB = new ChoiceBox<>();
        milestoneCB.getSelectionModel().selectedIndexProperty().addListener(milestoneListener);
        milestoneCB.setVisible(false);
        configCB.setItems(segmentLists.getConfigObservable());
        milestoneCB.setItems(segmentLists.getMilestoneObservable());

        controlPane.add(configLB, 1, 4);
        controlPane.setHalignment(configLB, HPos.LEFT);
        controlPane.add(configCB, 2, 4);
        controlPane.add(configButton, 0, 4);

        controlPane.add(milestoneLB, 1, 5);
        controlPane.setHalignment(milestoneLB, HPos.LEFT);
        controlPane.add(milestoneCB, 2, 5);
        controlPane.add(milestoneButton, 0, 5);
        controlPane.add(button, 2, 6);

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
