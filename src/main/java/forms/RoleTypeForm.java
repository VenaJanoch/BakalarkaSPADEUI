package forms;

import SPADEPAC.RoleClass;
import SPADEPAC.RoleSuperClass;
import abstractform.TableBasicForm;
import controlPanels.RoleTypeControlPanel;
import controllers.formControllers.FormController;
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
import tables.RoleTypeTable;

import java.util.ArrayList;

/**
 * Třída představující tabulkový formulář pro výčtový typ Role-Type, odděděná od
 * třídy TableClassBasicForm
 *
 * @author Václav Janoch
 */
public class RoleTypeForm extends TableBasicForm implements ISegmentTableForm {

    private ArrayList<String> classArray = new ArrayList<>();
    private ArrayList<String> superClassArray = new ArrayList<>();
    private RoleTypeControlPanel roleTypeControlPanel;
    private TableView<RoleTypeTable> tableTV;

    /**
     * Konstruktor Třídy Zinicializuje globální proměnné tříd Nastaví reakci
     * na klik do tabulky, naplni panel a nastavi akce tlacitkum
     *
     * @param formController       instance tridy FormController
     * @param formDataController   instance tridy FormDataController
     * @param editFormController   instance tridy EditFormController
     * @param deleteFormController instace tridy DeleteFormController
     * @param type                 instace SegmentType pro urceni typu formulare
     */
    public RoleTypeForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {
        super(formController, formDataController, editFormController, deleteFormController, type);

        roleTypeControlPanel = new RoleTypeControlPanel("Edit", SegmentType.Role_Type, formDataController, editFormController, formController);
        int i = 0;
        for (RoleClass roleClass : RoleClass.values()) {
            classArray.add(roleClass.name());
            i++;
        }
        i = 0;
        for (RoleSuperClass roleSuperClass : RoleSuperClass.values()) {
            superClassArray.add(roleSuperClass.name());
            i++;
        }

        roleTypeControlPanel.createControlPanel(classArray, superClassArray);

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
        tableTV = new TableView<RoleTypeTable>();

        TableColumn<RoleTypeTable, String> nameColumn = new TableColumn<RoleTypeTable, String>("Alias");
        TableColumn<RoleTypeTable, String> exist = new TableColumn<RoleTypeTable, String>("Exist");

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
        RoleTypeTable phaseTable = tableTV.getSelectionModel().getSelectedItems().get(0);
        if (phaseTable != null) {
            roleTypeControlPanel.showEditControlPanelRole(phaseTable, tableTV);
            formController.showEditControlPanel(roleTypeControlPanel);
        }
    }


    @Override
    public void setActionSubmitButton() {
        addButton.setOnAction(event -> addItem());
        removeButton.setOnAction(event -> deleteItem(tableTV));
        editButton.setOnAction(event -> showEditPanel());
        copyButton.setOnAction(event -> copyItem(tableTV));
    }

    public TableView<RoleTypeTable> getTableTV() {
        return tableTV;
    }

    @Override
    public void addItem() {
        formDataController.saveDataFromRoleTypeForm(tableTV, true);
        showEditPanel();
    }


}
