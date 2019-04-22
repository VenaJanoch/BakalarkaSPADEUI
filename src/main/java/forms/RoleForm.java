package forms;

import abstractform.TableBasicForm;
import controllers.formControllers.FormController;
import controlPanels.PersonControlPanel;
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
import tables.PersonTable;

/**
 * Třída představující dvojitý formulář pro element Person, vytvoří tabulku s
 * přehledem Rolí a tabulkový formulář pro Person-Type, odděděná od třídy
 * Table2BasicForm a implementující ISegmentTableForm
 * 
 * @author Václav Janoch
 *
 */
public class RoleForm extends TableBasicForm implements ISegmentTableForm {

	/**
	 * Globální proměnné třídy
	 */
	private TableView<PersonTable> tableTV;
	private PersonControlPanel editPersonControlPanel;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy Nastaví reakci
	 * potvrzovacímu tlačítku
	 *
	 */
	public RoleForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController,
					IDeleteFormController deleteFormController, SegmentType type) {
		super(formController, formDataController, editFormController, deleteFormController, type);

		editPersonControlPanel = new PersonControlPanel("Edit", formDataController, editFormController, formController, null,0, 0);

		setEventHandler();
		createForm();
		setActionSubmitButton();

	}

	@Override
	public void createForm() {

		this.setCenter(getTable());
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
	public Node getTable() {
		tableTV = new TableView<PersonTable>();

		TableColumn<PersonTable, String> nameColumn = new TableColumn<PersonTable, String>("Name");
		TableColumn<PersonTable, String> descColumn = new TableColumn<PersonTable, String>("Description");
		TableColumn<PersonTable, String> typeColumn = new TableColumn<PersonTable, String>("Type");

		nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
		nameColumn.setMinWidth(150);
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		descColumn.setCellValueFactory(new PropertyValueFactory("description"));
		descColumn.setMinWidth(150);
		descColumn.setCellFactory(TextFieldTableCell.forTableColumn());


		typeColumn.setCellValueFactory(new PropertyValueFactory("type"));
		typeColumn.setMinWidth(150);
		typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());

	//	tableTV.getColumns().addAll(nameColumn,descColumn, typeColumn);
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

	private void showEditPanel(){
		PersonTable personTable = tableTV.getSelectionModel().getSelectedItems().get(0);
		if (personTable != null) {
			editPersonControlPanel.showEditControlPanel();
			formController.showEditControlPanel(editPersonControlPanel);
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
		String nameST = ""; // roleControlPanel.getAlias();
		String descritpST = ""; //roleControlPanel.getDescriptionText();
		int roleIndex = 0;// roleControlPanel.getRoleTypeIndex();
		int id = formController.createTableItem(SegmentType.Person);

		PersonTable role = formDataController.prepareRoleToTable(nameST, descritpST, id, roleIndex );
		tableTV.getItems().add(role);
		tableTV.sort();
		formDataController.saveDataFromRoleForm(nameST, roleIndex, role);
	}

	public TableView<PersonTable> getTableTV() {
		return tableTV;
	}
}
