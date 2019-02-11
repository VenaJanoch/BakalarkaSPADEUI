package forms;

import Controllers.FormController;
import Controllers.FormDataController;
import abstractform.Table2BasicForm;
import com.sun.org.apache.bcel.internal.generic.TABLESWITCH;
import controlPanels.RoleControlPanel;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import interfaces.ISegmentTableForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import services.*;
import tables.BasicTable;
import tables.RoleTable;

import java.util.ArrayList;

/**
 * Třída představující dvojitý formulář pro element Role, vytvoří tabulku s
 * přehledem Rolí a tabulkový formulář pro Role-Type, odděděná od třídy
 * Table2BasicForm a implementující ISegmentTableForm
 * 
 * @author Václav Janoch
 *
 */
public class RoleForm extends Table2BasicForm implements ISegmentTableForm {

	/**
	 * Globální proměnné třídy
	 */
	private TableView<RoleTable> tableTV;
	private RoleTypeForm roleTForm;
	private Label formName;

	private RoleControlPanel roleControlPanel;
	private RoleControlPanel editRoleControlPanel;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy Nastaví reakci
	 * potvrzovacímu tlačítku
	 *
	 */
	public RoleForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {
		super(formController, formDataController, editFormController, deleteFormController, type);

		roleControlPanel = new RoleControlPanel("Add", formDataController, editFormController, formController);
		editRoleControlPanel = new RoleControlPanel("Edit", formDataController, editFormController, formController);
		editRoleControlPanel.createControlPanel();

		setEventHandler();
		createForm();
		getSubmitBT().setOnAction(event -> setActionSubmitButton());

	}

	@Override
	protected void setEventHandler() {
		OnMousePressedEventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				if(t.getClickCount() == 2) {
					RoleTable roleTable = tableTV.getSelectionModel().getSelectedItems().get(0);
					if (roleTable != null) {
						editRoleControlPanel.showEditControlPanel(roleTable, tableTV);
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

		formName = new Label("Role Form");
		formName.setFont(Font.font(25));

		internalPanel.setTop(formName);
		internalPanel.setAlignment(formName, Pos.CENTER);

		internalPanel.setCenter(getTable());
		internalPanel.setBottom(createControlPane());

		roleTForm = (RoleTypeForm) formController.getForms().get(Constans.roleTypeIndex);
		mainPanel.setRight(roleTForm.getMainPanel());

	}

	@Override
	public Node getTable() {
		tableTV = new TableView<RoleTable>();

		TableColumn<RoleTable, String> nameColumn = new TableColumn<RoleTable, String>("Name");
		TableColumn<RoleTable, String> descColumn = new TableColumn<RoleTable, String>("Description");
		TableColumn<RoleTable, String> typeColumn = new TableColumn<RoleTable, String>("Type");

		nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
		nameColumn.setMinWidth(150);
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		descColumn.setCellValueFactory(new PropertyValueFactory("description"));
		descColumn.setMinWidth(150);
		descColumn.setCellFactory(TextFieldTableCell.forTableColumn());


		typeColumn.setCellValueFactory(new PropertyValueFactory("type"));
		typeColumn.setMinWidth(150);
		typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		tableTV.getColumns().addAll(nameColumn,descColumn, typeColumn);
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
		ObservableList<RoleTable> selection = FXCollections
				.observableArrayList(tableTV.getSelectionModel().getSelectedItems());

		if (event.getCode() == KeyCode.DELETE) {
			if (selection.size() == 0) {
				Alerts.showNoItemsDeleteAlert();
			}
			else{
				ArrayList<BasicTable> list = new ArrayList<>(selection);
				deleteFormController.deleteRole(list, tableTV);
			}
		}

	}

	@Override
	public GridPane createControlPane() {

		GridPane controlPanel = roleControlPanel.createControlPanel();

		add = roleControlPanel.getButton();
		add.setOnAction(event -> addItem());

		return controlPanel;
	}


	@Override
	public void addItem() {
		String nameST = roleControlPanel.getName();
		String descritpST = roleControlPanel.getDescriptionText();
		int roleIndex = roleControlPanel.getRoleTypeIndex();
		int id = formController.createTableItem(SegmentType.Role);

		RoleTable role = formDataController.prepareRoleToTable(nameST, descritpST, id, roleIndex );
		tableTV.getItems().add(role);
		tableTV.sort();
		formDataController.saveDataFromRoleForm(nameST, roleIndex, role);
		roleControlPanel.clearPanel(tableTV);
	}

	public TableView<RoleTable> getTableTV() {
		return tableTV;
	}
}
