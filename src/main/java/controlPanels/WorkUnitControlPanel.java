package controlPanels;

import abstractControlPane.DateDescControlPanel;
import abstractControlPane.DescriptionControlPanel;
import abstractControlPane.NameControlPanel;
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
import tables.PhaseTable;
import tables.WorkUnitTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WorkUnitControlPanel extends DescriptionControlPanel {

    /**
     * Globální proměnné třídy
     */
    private Label estimatedTimeLB;
    private Label priorityLB;
    private Label severityLB;
    private Label categoryLB;
    private Label statusLB;
    private Label resolutionLB;
    private Label typeLB;
    private Label asigneeRoleLB;
    private Label authorRoleLB;

    private TextField estimatedTimeTF;
    private RadioButton existRB;
    private ComboBox<BasicTable> priorityCB;
    private ComboBox<BasicTable> severityCB;
    private ComboBox<BasicTable> resolutionCB;
    private ComboBox<BasicTable> statusCB;
    private TextField categoryTF;
    private ComboBox<BasicTable> typeCB;
    private ComboBox<BasicTable> asigneeRoleCB;
    private ComboBox<BasicTable> authorRoleCB;

    private int priorityIndex;
    private int severityIndex;
    private int assigneIndex;
    private int typeIndex;
    private int authorIndex;
    private int resolutionIndex;
    private int statusIndex;

    private SegmentLists segmentLists;

    private WorkUnitTable workUnitTable;

    public WorkUnitControlPanel(String buttonName, IFormDataController formDataController,
                                IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);
        this.segmentLists = formController.getSegmentLists();
        addItemsToControlPanel();
    }

    protected void addItemsToControlPanel(){

        estimatedTimeLB = new Label("Estimated Time: ");
        estimatedTimeTF = new TextField();

        priorityLB = new Label("Priority: ");
        priorityCB = new ComboBox<BasicTable>();

        priorityCB.getSelectionModel().selectedIndexProperty().addListener(priorityListener);
        priorityCB.setVisibleRowCount(5);

        severityLB = new Label("Severity: ");
        severityCB = new ComboBox<BasicTable>();
        severityCB.getSelectionModel().selectedIndexProperty().addListener(severityListener);
        severityCB.setVisibleRowCount(5);

        categoryLB = new Label("Category: ");
        categoryTF = new TextField();

        typeLB = new Label("Type: ");
        typeCB = new ComboBox<BasicTable>();
        typeCB.getSelectionModel().selectedIndexProperty().addListener(typeListener);
        typeCB.setVisibleRowCount(5);

        asigneeRoleLB = new Label("Asignee-role: ");
        asigneeRoleCB = new ComboBox<>();
        asigneeRoleCB.setVisibleRowCount(5);
        asigneeRoleCB.getSelectionModel().selectedIndexProperty().addListener(roleListenerAsig);

        authorRoleLB = new Label("Author-role: ");
        authorRoleCB = new ComboBox<>();
        authorRoleCB.setVisibleRowCount(5);
        authorRoleCB.getSelectionModel().selectedIndexProperty().addListener(roleListenerAut);

        resolutionLB = new Label("Resolution: ");
        resolutionCB = new ComboBox<BasicTable>();
        resolutionCB.getSelectionModel().selectedIndexProperty().addListener(resolutionListener);
        resolutionCB.setVisibleRowCount(5);

        statusLB = new Label("Status: ");
        statusCB = new ComboBox<BasicTable>();
        statusCB.getSelectionModel().selectedIndexProperty().addListener(statusListener);
        statusCB.setVisibleRowCount(5);

        existRB = new RadioButton("Exist");
        existRB.setSelected(true);

        asigneeRoleCB.setItems(segmentLists.getRoleObservable());
        authorRoleCB.setItems(segmentLists.getRoleObservable());
        priorityCB.setItems(segmentLists.getPriorityTypeObservable());
        severityCB.setItems(segmentLists.getSeverityTypeObservable());
        statusCB.setItems(segmentLists.getStatusTypeObservable());
        typeCB.setItems(segmentLists.getTypeObservable());
        resolutionCB.setItems(segmentLists.getResolutionTypeObservable());


        controlPane.add(estimatedTimeLB, 0, 2);
        controlPane.setHalignment(estimatedTimeLB, HPos.RIGHT);
        controlPane.add(estimatedTimeTF, 1, 2);

        controlPane.add(categoryLB, 0, 3);
        controlPane.setHalignment(categoryLB, HPos.RIGHT);
        controlPane.add(categoryTF, 1, 3);

        controlPane.add(priorityLB, 0, 4);
        controlPane.setHalignment(priorityLB, HPos.RIGHT);
        controlPane.add(priorityCB, 1, 4);

        controlPane.add(severityLB, 0, 5);
        controlPane.setHalignment(severityLB, HPos.RIGHT);
        controlPane.add(severityCB, 1, 5);

        controlPane.add(typeLB, 0, 6);
        controlPane.setHalignment(typeLB, HPos.RIGHT);
        controlPane.add(typeCB, 1, 6);

        controlPane.add(resolutionLB, 0, 7);
        controlPane.setHalignment(resolutionLB, HPos.RIGHT);
        controlPane.add(resolutionCB, 1, 7);

        controlPane.add(statusLB, 0, 8);
        controlPane.setHalignment(statusLB, HPos.RIGHT);
        controlPane.add(statusCB, 1, 8);

        controlPane.add(asigneeRoleLB, 0, 9);
        controlPane.setHalignment(asigneeRoleLB, HPos.RIGHT);
        controlPane.add(asigneeRoleCB, 1, 9);

        controlPane.add(authorRoleLB, 0, 10);
        controlPane.setHalignment(authorRoleLB, HPos.RIGHT);
        controlPane.add(authorRoleCB, 1, 10);

        controlPane.add(existRB, 1, 11);

        controlPane.add(button, 1, 12);
    }


    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Work Unit - Type
     */
    ChangeListener<Number> typeListener = new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            typeIndex = newValue.intValue();

        }
    };
    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Resolution
     */
    ChangeListener<Number> resolutionListener = new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            resolutionIndex = newValue.intValue();

        }
    };

    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Status
     */
    ChangeListener<Number> statusListener = new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            statusIndex = newValue.intValue();

        }
    };
    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Role-Assiggne
     */
    ChangeListener<Number> roleListenerAsig = new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            assigneIndex = newValue.intValue();

        }
    };

    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Role-Author
     */
    ChangeListener<Number> roleListenerAut = new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            authorIndex = newValue.intValue();

        }
    };
    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Priority
     */
    ChangeListener<Number> priorityListener = new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            priorityIndex = newValue.intValue();

        }
    };
    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Severity
     */
    ChangeListener<Number> severityListener = new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            severityIndex = newValue.intValue();

        }
    };

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        workUnitTable = (WorkUnitTable) basicTable;
        int id = workUnitTable.getId();
        String[] milestoneData = formDataController.getWorkUnitStringData(id);

        nameTF.setText(milestoneData[0]);
        if (milestoneData[1] != null){
            descriptionTF.setText(milestoneData[1]);
        }

        if(milestoneData[2] != null){
            estimatedTimeTF.setText(milestoneData[2]);
        }

        if(milestoneData[3] != null){
            priorityCB.getSelectionModel().select(Integer.parseInt(milestoneData[3]));
        }

        if(milestoneData[4] != null){
            severityCB.getSelectionModel().select(Integer.parseInt(milestoneData[4]));
        }

        if(milestoneData[5] != null){
            resolutionCB.getSelectionModel().select(Integer.parseInt(milestoneData[5]));
        }

        if(milestoneData[6] != null){
            statusCB.getSelectionModel().select(Integer.parseInt(milestoneData[6]));
        }

        if(milestoneData[7] != null){
            categoryTF.setText(milestoneData[7]);
        }

        if(milestoneData[8] != null){
            typeCB.getSelectionModel().select(Integer.parseInt(milestoneData[8]));
        }

        if(milestoneData[9] != null){
            asigneeRoleCB.getSelectionModel().select(Integer.parseInt(milestoneData[9]));
        }

        if(milestoneData[10] != null){
            authorRoleCB.getSelectionModel().select(Integer.parseInt(milestoneData[10]));
        }

        button.setOnAction(event -> saveDataFromPanel(basicTable, tableView));
    }


    public void saveDataFromPanel(BasicTable table, TableView tableView){
        int id = table.getId();
        workUnitTable.setName(id + "_" + nameTF.getText());


        String desc = "null";
        if (descriptionTF.getText() != null){
            desc = descriptionTF.getText();
        }

        editFormController.editDataFromWorkUnit(nameTF.getText(), desc, estimatedTimeTF.getText(), priorityIndex, severityIndex, resolutionIndex, statusIndex, categoryTF.getText(),
                typeIndex, assigneIndex, authorIndex, existRB.isSelected(), workUnitTable, id);

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
