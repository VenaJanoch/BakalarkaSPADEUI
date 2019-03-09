package controlPanels;

import abstractControlPane.WorkUnitDateControlPanel;
import controllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import services.SegmentType;
import tables.BasicTable;
import tables.IterationTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IterationControlPanel extends WorkUnitDateControlPanel {

    /**
     * Globální proměnné třídy
     */

    private Label configLB;
    private Label endDateLB;

    private ChoiceBox<BasicTable> configCB;

    private DatePicker endDateDP;

    private int configIndex = 0;
    private IterationTable iterationTable;

    private boolean isShowConfig;
    private Button configButton;

    private boolean isShowDate;
    private Button dateButton;

    public IterationControlPanel(String buttonName, IFormDataController formDataController,
                                 IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);
        addItemsToControlPanel();
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

    @Override
    protected void addItemsToControlPanel() {
        dateLB.setText("Start-Date");
        endDateLB = new Label("End-Data");
        endDateDP = new DatePicker();
        endDateDP.setVisible(false);
        setExitButtonsActions();

        configLB = new Label("Configuration: ");
        configCB = new ChoiceBox<>();
        configCB.getSelectionModel().selectedIndexProperty().addListener(configListener);
        configCB.setVisible(false);
        configCB.setItems(segmentLists.getConfigObservable());

        controlPane.add(endDateLB, 1, 4);
        controlPane.setHalignment(endDateLB, HPos.LEFT);
        controlPane.add(endDateDP, 2, 4);
        controlPane.add(dateButton, 0 ,4);

        controlPane.add(configButton, 0, 5);
        controlPane.add(configLB, 1, 5);
        controlPane.setHalignment(configLB, HPos.LEFT);
        controlPane.add(configCB, 2, 5);

        controlPane.add(button, 2, 6);
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

        isShowDate = false;
        dateButton = new Button("+");
        dateButton.setOnAction(event -> {
            if (!isShowDate){
                endDateDP.setVisible(true);
                isShowDate = true;
                dateButton.setText("-");
            }else{
                endDateDP.setVisible(false);
                endDateDP.setValue(null);
                isShowDate = false;
                dateButton.setText("+");
            }
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
}
