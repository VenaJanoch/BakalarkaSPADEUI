package abstractform;

import controlPanels.ClassControlPanel;
import controllers.formControllers.FormController;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import interfaces.ISegmentTableForm;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import services.SegmentType;
import tables.ClassTable;
import javafx.geometry.Insets;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;

/**
 * Třída sloužící k vytvoření tabulkového formuláře výčtových typů. Odděděná od
 * třídy TableBasicForm
 *
 * @author Václav Janoch
 */
public abstract class TableClassBasicForm extends TableBasicForm implements ISegmentTableForm {
    /**
     * Globální proměnné třídy
     */

    protected TableView<ClassTable> tableTV;
    protected EventHandler<MouseEvent> OnMousePressedEventHandler;
    protected ClassControlPanel editClassControlPanelTCB;


    /**
     * Konstruktor tridy zavola konstruktor nadrazene tridy
     * @param formController
     * @param formDataController
     * @param editFormController
     * @param deleteFormController
     * @param type
     */
    public TableClassBasicForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController,
                               IDeleteFormController deleteFormController, SegmentType type) {

        super(formController, formDataController, editFormController, deleteFormController, type);


    }


    protected void setEventHandler() {
        OnMousePressedEventHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if (t.getClickCount() == 2) {
                    ClassTable classTable = tableTV.getSelectionModel().getSelectedItems().get(0);
                    if (classTable != null) {
                        editClassControlPanelTCB.showEditControlPanel(classTable, tableTV);
                        formController.showEditControlPanel(editClassControlPanelTCB);
                    }
                }
            }
        };

    }


    @Override
    public void createForm() {
        this.setCenter(getTable());

    }

    @Override
    public Node getTable() {


        tableTV = new TableView<ClassTable>();
        tableTV.setId("classTable");
        TableColumn<ClassTable, String> nameColumn = new TableColumn<ClassTable, String>("Alias");
        TableColumn<ClassTable, String> classColumn = new TableColumn<ClassTable, String>("Class");
        TableColumn<ClassTable, String> superColumn = new TableColumn<ClassTable, String>("Super Class");
        TableColumn<ClassTable, String> existColumn = new TableColumn<ClassTable, String>("Exist");

        nameColumn.setCellValueFactory(new PropertyValueFactory("alias"));
        nameColumn.setMinWidth(150);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        classColumn.setCellValueFactory(new PropertyValueFactory("classType"));
        classColumn.setMinWidth(150);
        classColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        superColumn.setCellValueFactory(new PropertyValueFactory("superType"));
        superColumn.setMinWidth(150);
        superColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        existColumn.setCellValueFactory(new PropertyValueFactory("existString"));
        existColumn.setMinWidth(150);
        existColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //tableTV.getColumns().addAll(nameColumn, classColumn, superColumn, existColumn);
        tableTV.getColumns().addAll(nameColumn, existColumn);
        tableTV.setEditable(false);

        tableTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        BorderPane.setMargin(tableTV, new Insets(5));
        tableTV.setOnKeyReleased(event -> deleteSelected(event));
        tableTV.setOnMousePressed(OnMousePressedEventHandler);

        return tableTV;
    }

    @Override
    public void deleteSelected(KeyEvent event) {

        if (event.getCode() == KeyCode.DELETE) {
            deleteItem(tableTV);
        }

    }

    @Override
    public GridPane createControlPane() {


        return null;
    }

    @Override
    public void addItem() {
        saveData(getSegmentType());
        showEditPanel();

    }

    public void saveData(SegmentType segmentType) {
        switch (segmentType) {
            case Severity:
                formDataController.saveDataFromSeverity(tableTV, true);
                break;
            case Priority:
                formDataController.saveDataFromPriority(tableTV, true);
                break;
            case Status:
                formDataController.saveDataFromStatusForm(tableTV, true);
                break;
            case Type:
                formDataController.saveDataFromTypeForm(tableTV, true);
                break;
            case Relation:
                formDataController.saveDataFromRelationForm(tableTV, true);
                break;
            case Resolution:
                formDataController.saveDataFromResolutionForm(tableTV, true);
                break;
            default:

        }
    }


    @Override
    public void setActionSubmitButton() {
        addButton.setOnAction(event -> addItem());
        removeButton.setOnAction(event -> deleteItem(tableTV));
        editButton.setOnAction(event -> showEditPanel());
        copyButton.setOnAction(event -> copyItem(tableTV));
    }

    private void showEditPanel() {
        ClassTable table = tableTV.getSelectionModel().getSelectedItems().get(0);
        if (table != null) {
            editClassControlPanelTCB.showEditControlPanel(table, tableTV);
            formController.showEditControlPanel(editClassControlPanelTCB);
        }
    }

    public TableView<ClassTable> getTableTV() {
        return tableTV;
    }
}
