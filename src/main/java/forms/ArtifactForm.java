package forms;

import abstractform.TableBasicForm;
import controlPanels.ArtifactControlPanel;
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
import tables.ArtifactTable;

/**
 * Třída představující formulář pro segment Artifact, odděděná od třídy
 * DeteDescBasicForm a implementující ISegmentForm
 *
 * @author Václav Janoch
 */
public class ArtifactForm extends TableBasicForm implements ISegmentTableForm {


    private TableView<ArtifactTable> tableTV;
    private ArtifactControlPanel editControlPanel;


    /**
     * Konstruktor třídy Zinicializuje globální proměnné tříd Nastaví velikost
     * okna a reakci na uzavření formuláři
     */
    public ArtifactForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController,
                        IDeleteFormController deleteFormController, SegmentType type) {
        super(formController, formDataController, editFormController, deleteFormController, type);

        editControlPanel = new ArtifactControlPanel("Edit", formDataController, editFormController, formController, null, 0, 0);
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
        tableTV = new TableView<ArtifactTable>();

        TableColumn<ArtifactTable, String> nameColumn = new TableColumn<ArtifactTable, String>("Name");
        TableColumn<ArtifactTable, String> descriptionColumn = new TableColumn<ArtifactTable, String>("Description");

        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        nameColumn.setMinWidth(150);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        descriptionColumn.setCellValueFactory(new PropertyValueFactory("description"));
        descriptionColumn.setMinWidth(150);
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        // tableTV.getColumns().addAll(nameColumn, descriptionColumn);
        tableTV.getColumns().add(nameColumn);
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
        ArtifactTable artifactTable = tableTV.getSelectionModel().getSelectedItems().get(0);
        if (artifactTable != null) {
            editControlPanel.showEditControlPanel();
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
        String nameST = "";// criterionControlPanel.getAlias();

        int id = formController.createTableItem(SegmentType.Artifact);
        String idName = id + "";

        ArtifactTable table = new ArtifactTable(idName, true, id);
        tableTV.getItems().add(table);
        tableTV.sort();

        int lastItem = tableTV.getItems().size();
        tableTV.getSelectionModel().select(lastItem - 1);
        showEditPanel();
    }

}
