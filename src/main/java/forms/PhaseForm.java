package forms;

import abstractform.TableBasicForm;
import controlPanels.PhaseControlPanel;
import controllers.formControllers.FormController;
import controllers.graphicsComponentsControllers.CanvasController;
import graphics.panels.DragAndDropItemPanel;
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
import services.SegmentType;
import tables.PhaseTable;

/**
 * Třída představující formulář pro segment Phase, odděděná od třídy
 * TableBasicForm a implementující ISegmentTableForm
 *
 * @author Václav Janoch
 */
public class PhaseForm extends TableBasicForm implements ISegmentTableForm {


    private TableView<PhaseTable> tableTV;
    private PhaseControlPanel editControlPanel;


    /**
     * Konstruktor Třídy Zinicializuje globální proměnné tříd Nastaví reakci
     * na klik do tabulky, vytvori naplni panel a nastavi akce tlacitkum
     *
     * @param formController       instance tridy FormController
     * @param formDataController   instance tridy FormDataController
     * @param editFormController   instance tridy EditFormController
     * @param deleteFormController instace tridy DeleteFormController
     * @param type                 instace SegmentType pro urceni typu formulare
     */
    public PhaseForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, CanvasController canvasController, DragAndDropItemPanel dgItemPanel, SegmentType type, int indexForm) {

        super(formController, formDataController, editFormController, deleteFormController, type);

        editControlPanel = new PhaseControlPanel("Edit", formDataController, editFormController, formController);
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
        tableTV = new TableView<PhaseTable>();
        TableColumn<PhaseTable, String> nameColumn = new TableColumn<PhaseTable, String>("Alias");
        TableColumn<PhaseTable, String> exist = new TableColumn<PhaseTable, String>("Exist");

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
        PhaseTable phaseTable = tableTV.getSelectionModel().getSelectedItems().get(0);
        if (phaseTable != null) {
            editControlPanel.showEditControlPanel(phaseTable, tableTV);
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
        formDataController.saveDataFromPhaseForm(tableTV, true);
        showEditPanel();
    }

    public TableView<PhaseTable> getTableTV() {
        return tableTV;
    }

    public PhaseControlPanel getEditControlPanel() {
        return editControlPanel;
    }
}
