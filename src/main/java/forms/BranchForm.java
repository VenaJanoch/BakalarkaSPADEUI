package forms;

import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.ObjectFactory;
import abstractform.TableBasicForm;
import interfaces.ISegmentTableForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import services.*;
import model.IdentificatorCreater;
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
    private Label isMainLB;
    private boolean isMain;

    private ToggleGroup group = new ToggleGroup();

    private RadioButton rbYes;
    private RadioButton rbNo;

    private ComboBox<String> branchesCB;
    private TableView<BranchTable> tableTV;

    private String main = "YES";

    private boolean newBranch;

    /**
     * Konstruktor třídy Zinicializuje globální proměnné tříd Nastaví velikost
     * formuláře
     */
    public BranchForm(FormController formController, FormDataController formDataController, String name) {
        super(formController, formDataController, name);

        getMainPanel().setMinSize(Constans.littleformWidth, Constans.littleformHeight);
        getMainPanel().setMaxSize(Constans.littleformWidth, Constans.littleformHeight);

        getSubmitButton().setOnAction(event -> setActionSubmitButton());
        createForm();

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

        ObservableList<BranchTable> list = null;

        if (event.getCode() == KeyCode.DELETE) {
            if (selection.size() == 0) {
                Alerts.showNoItemsDeleteAlert();
            } else {
                list = Alerts.showDeleteItemBranchAlert(tableTV, selection);
                if (list != null) {
                    formDataController.deleteBranch(list);
                }

            }
        }

    }

    @Override
    public GridPane createControlPane() {

        isMainLB = new Label("Main");

        rbYes = new RadioButton("Yes");
        rbYes.setToggleGroup(group);
        rbYes.setSelected(true);
        rbYes.setId("YesRB");
        rbNo = new RadioButton("No");
        rbNo.setToggleGroup(group);
        rbNo.setId("NoRB");

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                RadioButton chk = (RadioButton) newValue.getToggleGroup().getSelectedToggle();

                if (chk.getText().contains("Yes")) {
                    isMain = true;
                    main = "YES";
                } else {
                    main = "NO";
                    isMain = false;
                }

            }
        });

        getControlPane().add(isMainLB, 2, 0);
        getControlPane().add(rbYes, 3, 0);
        getControlPane().add(rbNo, 4, 0);

        getControlPane().add(getAddBT(), 5, 0);

        getAddBT().setOnAction(event -> addItem());

        return getControlPane();
    }

    @Override
    public void addItem() {

        String nameST = getNameTF().getText();
        int id = formController.createTableItem(SegmentType.Branch);
        String idName = id + "_" + nameST;

        BranchTable branch = new BranchTable(idName, main, id);
        tableTV.getItems().add(branch);
        tableTV.sort();

        formDataController.saveDataFromBranch(nameST, idName, id, isMain);
    }

    public TableView<BranchTable> getTableTV() {
        return tableTV;
    }
}
