package controlPanels;

import abstractControlPane.DateDescControlPanel;
import abstractform.TableBasicForm;
import controllers.FormController;
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
import tables.IterationTable;
import tables.PhaseTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IterationControlPanel extends DateDescControlPanel {

    /**
     * Globální proměnné třídy
     */

    private Label configLB;
    private Label endDateLB;
    private Label workUnitLB;

    private ChoiceBox<BasicTable> configCB;
    private CheckComboBox<BasicTable> workUnitCB;

    private DatePicker endDateDP;

    private int configIndex = 0;
    private ObservableList<Integer> workUnitIndicies;
    private SegmentLists segmentLists;
    IterationTable iterationTable;

    public IterationControlPanel(String buttonName, IFormDataController formDataController,
                                 IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);
        this.segmentLists = formController.getSegmentLists();
        this.workUnitIndicies = FXCollections.observableArrayList();
        createControlPanel();
    }

    public GridPane createControlPanel(){

        dateLB.setText("Start-Date");
        endDateLB = new Label("End-Data");
        endDateDP = new DatePicker();

        configLB = new Label("Configuration: ");
        configCB = new ChoiceBox<>();
        configCB.getSelectionModel().selectedIndexProperty().addListener(configListener);

        configCB.setItems(segmentLists.getConfigObservable());

        workUnitLB = new Label("Work Unit: ");
        workUnitCB = new CheckComboBox<>(segmentLists.getWorkUnitsObservable());
        workUnitCB.setMaxWidth(Constans.checkComboBox);

        workUnitCB.getCheckModel().getCheckedItems().addListener(new ListChangeListener<BasicTable>() {

            public void onChanged(ListChangeListener.Change<? extends BasicTable> c) {
                workUnitIndicies = workUnitCB.getCheckModel().getCheckedIndices();
            }
        });


        controlPane.add(endDateLB, 0, 3);
        controlPane.setHalignment(endDateLB, HPos.LEFT);
        controlPane.add(endDateDP, 1, 3);

        controlPane.add(configLB, 0, 4);
        controlPane.setHalignment(configLB, HPos.LEFT);
        controlPane.add(configCB, 1, 4);

        controlPane.add(workUnitLB, 0, 5);
        controlPane.setHalignment(workUnitLB, HPos.LEFT);
        controlPane.add(workUnitCB, 1, 5);

        controlPane.add(button, 2, 6);
        return controlPane;
    }

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
        iterationTable = (IterationTable) basicTable;
        int id = iterationTable.getId();
        String[] iterationData = formDataController.getIterationStringData(id);

        nameTF.setText(iterationData[0]);

        if (iterationData[1] != null){
            descriptionTF.setText(iterationData[1]);
        }

        if(iterationData[2] != null){
            configCB.getSelectionModel().select(Integer.parseInt(iterationData[2]));
        }

        if(iterationData[3] != null){
            dateDP.setValue(LocalDate.parse(iterationData[3]));
        }

        if(iterationData[4] != null){
            endDateDP.setValue(LocalDate.parse(iterationData[4]));
        }

        List<Integer> workUnits = formDataController.getWorkUnitFromSegment(id, SegmentType.Iteration);
        if(workUnits != null){
            for(int i : workUnits){
                workUnitCB.getCheckModel().check(i);
            }
        }


        button.setOnAction(event -> saveDataFromPanel(iterationTable, tableView));
    }

    public void saveDataFromPanel(BasicTable table, TableView tableView){
        int id = table.getId();
        iterationTable.setName(id + "_" + nameTF.getText());
        if (configCB.getValue() != null){
            iterationTable.setConfiguration(configCB.getValue().getName());
        }

        String desc = "null";
        if (descriptionTF.getText() != null){
            desc = descriptionTF.getText();
        }

        LocalDate date = LocalDate.of(1900,1,1);
        if(dateDP.getValue() != null){
            date = dateDP.getValue();
        }

        LocalDate date2 = LocalDate.of(1900,1,1);
        if(endDateDP.getValue() != null){
            date = endDateDP.getValue();
        }
        editFormController.editDataFromIteration(nameTF.getText(), desc ,date, date2, configIndex, new ArrayList<Integer>(workUnitIndicies),
                iterationTable, id);

        clearPanelCB(tableView);
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
}
