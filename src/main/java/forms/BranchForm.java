package forms;

import controllers.formControllers.FormController;
import abstractform.TableBasicForm;
import controlPanels.BranchControlPanel;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import interfaces.ISegmentTableForm;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import services.*;
import tables.BranchTable;

/**
 * Třída představující tabulkový formulář pro element Branch, odděděná od třídy
 * TableBasicForm a implementující ISegmentTableForm
 *
 * @author Václav Janoch
 */
public class BranchForm extends TableBasicForm implements ISegmentTableForm {

    /**
     * Globální proměnné třídy
     */

    private TableView<BranchTable> tableTV;
    private BranchControlPanel editBranchControlPanel;


    /**
     * Konstruktor třídy Zinicializuje globální proměnné tříd Nastaví velikost
     * formuláře
     */
    public BranchForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {
        super(formController, formDataController, editFormController, deleteFormController, type);

        editBranchControlPanel = new BranchControlPanel("Edit", formDataController, editFormController, formController);


        setEventHandler();
        setActionSubmitButton();
        createForm();

    }

    @Override
    protected void setEventHandler() {
        OnMousePressedEventHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if (t.getClickCount() == 2) {
                    showEditPanel();
                }
            }
        };
    }

    private void showEditPanel() {
        BranchTable branchTable = tableTV.getSelectionModel().getSelectedItems().get(0);
        if (branchTable != null) {
            editBranchControlPanel.showEditControlPanel(branchTable, tableTV);
            formController.showEditControlPanel(editBranchControlPanel);
        }
    }

    @Override
    public void setActionSubmitButton() {
        addButton.setOnAction(event -> addItem());
        removeButton.setOnAction(event -> deleteItem(tableTV));
        editButton.setOnAction(event -> showEditPanel());
        copyButton.setOnAction(event -> copyItem(tableTV));
    }

    @Override
    public void createForm() {
        this.setCenter(getTable());

    }

    @Override
    public Node getTable() {
        tableTV = new TableView<BranchTable>();
        tableTV.setId("branchTable");
        tableTV.setEditable(false);
        TableColumn<BranchTable, String> nameColumn = new TableColumn<BranchTable, String>("Alias");
        TableColumn<BranchTable, String> exist = new TableColumn<BranchTable, String>("Exist");

        nameColumn.setCellValueFactory(new PropertyValueFactory("alias"));
        nameColumn.setMinWidth(150);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        exist.setCellValueFactory(new PropertyValueFactory("existString"));
        exist.setMinWidth(150);
        exist.setCellFactory(TextFieldTableCell.forTableColumn());


        tableTV.getColumns().addAll(nameColumn, exist);

        tableTV.setEditable(false);
        tableTV.setOnMousePressed(OnMousePressedEventHandler);
        tableTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableTV.setOnKeyReleased(event -> deleteSelected(event));

        BorderPane.setMargin(tableTV, new Insets(5));

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

        formDataController.saveDataFromBranch(tableTV, true);
        showEditPanel();

    }


    public TableView<BranchTable> getTableTV() {
        return tableTV;
    }
}
