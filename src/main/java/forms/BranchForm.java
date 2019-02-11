package forms;

import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.ObjectFactory;
import abstractform.BasicForm;
import abstractform.TableBasicForm;
import controlPanels.BranchControlPanel;
import controlPanels.MilestoneControlPanel;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import interfaces.ISegmentTableForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import services.*;
import model.IdentificatorCreater;
import tables.BasicTable;
import tables.BranchTable;
import tables.MilestoneTable;

import java.util.ArrayList;

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
    private BranchControlPanel branchoneControlPanel;
    private BranchControlPanel editBranchControlPanel;


    /**
     * Konstruktor třídy Zinicializuje globální proměnné tříd Nastaví velikost
     * formuláře
     */
    public BranchForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {
        super(formController, formDataController, editFormController, deleteFormController, type);

        branchoneControlPanel = new BranchControlPanel("Add", formDataController, editFormController, formController);
        editBranchControlPanel = new BranchControlPanel("Edit", formDataController, editFormController, formController);
        editBranchControlPanel.createControlPanel();


        getMainPanel().setMinSize(Constans.littleformWidth, Constans.littleformHeight);
        getMainPanel().setMaxSize(Constans.littleformWidth, Constans.littleformHeight);

        setEventHandler();
        getSubmitButton().setOnAction(event -> setActionSubmitButton());
        createForm();

    }

    @Override
    protected void setEventHandler() {
        OnMousePressedEventHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if(t.getClickCount() == 2) {
                    BranchTable branchTable = tableTV.getSelectionModel().getSelectedItems().get(0);
                    if (branchTable != null) {
                        editBranchControlPanel.showEditControlPanel(branchTable, tableTV);
                    }
                }
            }
        };
    }

    @Override
    public void setActionSubmitButton() {
        close();
    }

    @Override
    public void createForm() {
        getFormName().setText("Branch Form");
        getMainPanel().setCenter(getTable());
        getMainPanel().setBottom(createControlPane());
    }

    @Override
    public Node getTable() {
        tableTV = new TableView<BranchTable>();
        tableTV.setId("branchTable");
        tableTV.setEditable(false);
        TableColumn<BranchTable, String> nameColumn = new TableColumn<BranchTable, String>("Name");
        TableColumn<BranchTable, String> mainColumn = new TableColumn<BranchTable, String>("Is Main");

        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        nameColumn.setMinWidth(150);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        mainColumn.setCellValueFactory(new PropertyValueFactory("main"));
        mainColumn.setMinWidth(150);
        mainColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        tableTV.getColumns().addAll(nameColumn, mainColumn);

        tableTV.setEditable(true);
        tableTV.setOnMousePressed(OnMousePressedEventHandler);
        tableTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableTV.setOnKeyReleased(event -> deleteSelected(event));

        BorderPane.setMargin(tableTV, new Insets(5));

        return tableTV;
    }

    @Override
    public void deleteSelected(KeyEvent event) {
        ObservableList<BranchTable> selection = FXCollections
                .observableArrayList(tableTV.getSelectionModel().getSelectedItems());



        if (event.getCode() == KeyCode.DELETE) {
            if (selection.size() == 0) {
                Alerts.showNoItemsDeleteAlert();
            }
            else{
                ArrayList<BasicTable> list = new ArrayList<>(selection);
                deleteFormController.deleteBranch(list, getTableTV());
            }
        }

    }

    @Override
    public GridPane createControlPane() {

        GridPane controlPane = branchoneControlPanel.createControlPanel();

        add = branchoneControlPanel.getButton();
        add.setOnAction(event -> addItem());

        return controlPane;
    }

    @Override
    public void addItem() {

        String nameST = branchoneControlPanel.getName();
        int id = formController.createTableItem(SegmentType.Branch);

        BranchTable branch = formDataController.prepareBranchToTable(nameST, branchoneControlPanel.isMain(), id);
        tableTV.getItems().add(branch);
        tableTV.sort();

        formDataController.saveDataFromBranch(nameST,branch);
        branchoneControlPanel.clearPanel(tableTV);
    }


    public TableView<BranchTable> getTableTV() {
        return tableTV;
    }
}
