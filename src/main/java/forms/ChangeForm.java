package forms;

import abstractform.TableBasicForm;
import controlPanels.ChangeControlPanel;
import controllers.formControllers.FormController;
import interfaces.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import services.SegmentType;
import tables.ChangeTable;

/**
 * Třída představující formulář pro element Change, odděděná od třídy BasicForm
 * a implementující ISegmentForm
 *
 * @author Václav Janoch
 */
public class ChangeForm extends TableBasicForm implements ISegmentTableForm {

    /**
     * Globální proměnné třídy
     */
    private TableView<ChangeTable> tableTV;
    private ChangeControlPanel editControlPanel;

    /**
     * Konstruktor třídy Zinicializuje globální proměnné třídy Nastaví velikost
     * formuláře a reakci na uzavření formuláře
     */
    public ChangeForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController,
                      IDeleteFormController deleteFormController, SegmentType type) {
        super(formController, formDataController, editFormController, deleteFormController, type);
        editControlPanel = new ChangeControlPanel("Edit", formDataController, editFormController, formController);
        setEventHandler();
        createForm();
        setActionSubmitButton();
    }

    @Override
    protected void setEventHandler() {
        OnMousePressedEventHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if(t.getClickCount() == 2) {
                    showEditPanel();
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
        tableTV = new TableView<ChangeTable>();

        TableColumn<ChangeTable, String> nameColumn = new TableColumn<ChangeTable, String>("Id");
        TableColumn<ChangeTable, String> exist = new TableColumn<ChangeTable, String>("Exist");

        nameColumn.setCellValueFactory(new PropertyValueFactory("idString"));
        nameColumn.setMinWidth(150);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        exist.setCellValueFactory(new PropertyValueFactory("existString"));
        exist.setMinWidth(150);
        exist.setCellFactory(TextFieldTableCell.forTableColumn());


        tableTV.getColumns().addAll(nameColumn, exist);
        tableTV.setOnMousePressed(OnMousePressedEventHandler);
        tableTV.setEditable(false);

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

    private void showEditPanel(){
        ChangeTable table = tableTV.getSelectionModel().getSelectedItems().get(0);
        if (table != null) {
            editControlPanel.showEditControlPanel(table, tableTV);
            formController.showEditControlPanel(editControlPanel);
        }
    }


    @Override
    public void setActionSubmitButton() {
        addButton.setOnAction(event -> addItem());
        removeButton.setOnAction(event -> deleteItem(tableTV));
        editButton.setOnAction(event -> showEditPanel());
    }

    @Override
    public void addItem() {
        String nameST = "";// criterionControlPanel.getName();

        int id = formController.createTableItem(SegmentType.Change);
        String idName = id + "_" + nameST;

        ChangeTable table = new ChangeTable(idName, true, id);
        formDataController.saveDataFromChange(table);

        tableTV.getItems().add(table);
        tableTV.sort();
        int lastItem = tableTV.getItems().size();
        tableTV.getSelectionModel().select(lastItem - 1);
        showEditPanel();
    }

    public TableView<ChangeTable> getTableTV() {
        return tableTV;
    }
}
