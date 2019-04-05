package forms;

import java.time.LocalDate;

import abstractform.TableBasicForm;
import controlPanels.CriterionControlPanel;
import controlPanels.PhaseControlPanel;
import controllers.CanvasController;
import controllers.FormController;
import abstractform.DateDescBasicForm;
import graphics.DragAndDropItemPanel;
import interfaces.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
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
import services.*;
import tables.BasicTable;
import tables.CriterionTable;
import tables.PhaseTable;

/**
 * Třída představující formulář pro segment Phase, odděděná od třídy
 * DeteDescBasicForm a implementující ISegmentForm
 *
 * @author Václav Janoch
 */
public class PhaseForm extends TableBasicForm implements ISegmentTableForm {


    private TableView<PhaseTable> tableTV;
    private PhaseControlPanel editControlPanel;


    /**
     * Konstruktor třídy Zinicializuje globální proměnné tříd Nastaví reakci na
     * uzavření okna formuláře
     *
     * @param indexForm
     */
    public PhaseForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, CanvasController canvasController, DragAndDropItemPanel dgItemPanel, SegmentType type, int indexForm) {

        super(formController, formDataController, editFormController, deleteFormController, type);

        editControlPanel = new PhaseControlPanel("Edit", formDataController, editFormController, formController);
        setEventHandler();
        createForm();
        setActionSubmitButton();
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


    @Override
    public void createForm() {

        this.setCenter(getTable());

    }

    @Override
    public Node getTable() {
        tableTV = new TableView<PhaseTable>();
        TableColumn<PhaseTable, String> nameColumn = new TableColumn<PhaseTable, String>("Id");
        TableColumn<PhaseTable, String> exist = new TableColumn<PhaseTable, String>("Exist");

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

    private void showEditPanel() {
        PhaseTable phaseTable = tableTV.getSelectionModel().getSelectedItems().get(0);
        if (phaseTable != null) {
            editControlPanel.showEditControlPanel(phaseTable, tableTV);
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

        int id = formController.createTableItem(SegmentType.Phase);
        String idName = id + "_" + nameST;

        PhaseTable table = new PhaseTable(idName,true, id);
        tableTV.getItems().add(table);
        tableTV.sort();

        int lastItem = tableTV.getItems().size();
        tableTV.getSelectionModel().select(lastItem - 1);
        showEditPanel();
    }

    public TableView<PhaseTable> getTableTV() {
        return tableTV;
    }

    public PhaseControlPanel getEditControlPanel() {
        return editControlPanel;
    }
}
