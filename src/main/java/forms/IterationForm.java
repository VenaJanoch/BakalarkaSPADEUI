package forms;

import abstractform.TableBasicForm;
import controlPanels.IterationControlPanel;
import controllers.graphicsComponentsControllers.CanvasController;
import controllers.formControllers.FormController;
import graphics.panels.DragAndDropItemPanel;
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
import tables.IterationTable;

/**
 * Třída představující formulář pro segment Iteration, odděděná od třídy
 * Dete2DescBasicForm a implementující ISegmentForm
 *
 * @author Václav Janoch
 */
public class IterationForm extends TableBasicForm implements ISegmentTableForm {

    /**
     * Globální proměnné třídy
     */
    private TableView<IterationTable> tableTV;
    private IterationControlPanel editControlPanel;

    /**
     * Konstruktor Třídy Zinicializuje globální proměnné tříd Nastaví reakci
     * na klik do tabulky, vytvori naplni panel a nastavi akce tlacitkum
     * @param formController instance tridy FormController
     * @param formDataController instance tridy FormDataController
     * @param editFormController instance tridy EditFormController
     * @param deleteFormController instace tridy DeleteFormController
     * @param type instace SegmentType pro urceni typu formulare
     */
    public IterationForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, CanvasController canvas, DragAndDropItemPanel dgItemPanel,
                         SegmentType type, int indexForm) {

        super(formController, formDataController, editFormController, deleteFormController, type);
        editControlPanel = new IterationControlPanel("Edit", formDataController, editFormController, formController);
        setEventHandler();
        createForm();
        setActionSubmitButton();

    }

    /**
     * Metoda nastavi event handler pro tabulku
     * Pokud je na radek dvakrat kliknuto mysi
     * zavola se metoda pro zobrazeni panelu a nastaveni
     * prislusneho formulare do panelu
     */
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

    /**
     * Metoda pro přídání prvku do interního gridPanelu
     */
    @Override
    public void createForm() {

        this.setCenter(getTable());

    }

    /**
     * Metoda pro přídání TableView do formuláře
     */
    @Override
    public Node getTable() {
        tableTV = new TableView<IterationTable>();

        TableColumn<IterationTable, String> nameColumn = new TableColumn<IterationTable, String>("Alias");
        TableColumn<IterationTable, String> exist = new TableColumn<IterationTable, String>("Exist");

        nameColumn.setCellValueFactory(new PropertyValueFactory("alias"));
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
    /**
     * Metoda pro nastavení reakce na klávesu delete
     */
    @Override
    public void deleteSelected(KeyEvent event) {

        if (event.getCode() == KeyCode.DELETE) {
            deleteItem(tableTV);
        }
    }
    /**
     * Metoda pro přídání prvku do gridPanelu
     */
    @Override
    public GridPane createControlPane() {
        return null;
    }

    /**
     * Metoda pro vyvolani postraniho panelu
     * Jsou zavolany potrebne kontrolery
     */
    private void showEditPanel() {
        IterationTable table = tableTV.getSelectionModel().getSelectedItems().get(0);
        if (table != null) {
            editControlPanel.showEditControlPanel(table, tableTV);
            formController.showEditControlPanel(editControlPanel);
        }
    }

    /**
     * Metoda pro určení reakce stisknutí tlačítka pro potvrzení formuláře
     */
    @Override
    public void setActionSubmitButton() {
        addButton.setOnAction(event -> addItem());
        removeButton.setOnAction(event -> deleteItem(tableTV));
        editButton.setOnAction(event -> showEditPanel());
        copyButton.setOnAction(event -> copyItem(tableTV));
    }
    /**
     * Metoda pro přídání prvku dané tabulky
     */
    @Override
    public void addItem() {
        formDataController.saveDataFromIterationForm(tableTV, true);
        showEditPanel();
    }

    public TableView<IterationTable> getTableTV() {
        return tableTV;
    }
}
