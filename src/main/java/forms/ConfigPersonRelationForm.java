package forms;

import controllers.FormController;
import abstractform.TableBasicForm;
import controlPanels.ConfigPersonRelationControlPanel;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import interfaces.ISegmentTableForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import services.Alerts;
import services.SegmentType;
import tables.BasicTable;
import tables.CPRTable;

import java.util.ArrayList;

/**
 * Třída představující tabulkový formulář pro element Configuration-Person-Relatio, odděděná od třídy
 * TableBasicForm a implementující ISegmentTableForm
 * 
 * @author Václav Janoch
 *
 */
public class ConfigPersonRelationForm extends TableBasicForm implements ISegmentTableForm {

	/**
	 * Globální proměnné třídy
	 */

	private TableView<CPRTable> tableTV;
	private ConfigPersonRelationControlPanel editCPRControlPanel;
	/**
	 * Konstruktor třídy
	 * Zinicializuje globální proměnné tříd
	 * Nastaví vlastnost pro tlačítko OK
	 *
	 */
	
	public ConfigPersonRelationForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {
		super(formController, formDataController, editFormController, deleteFormController, type);

		editCPRControlPanel = new ConfigPersonRelationControlPanel("Edit", formDataController, editFormController, formController);

		setEventHandler();
		createForm();
		setActionSubmitButton();

	}

	@Override
		protected void setEventHandler(){
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
		CPRTable cprTable = tableTV.getSelectionModel().getSelectedItems().get(0);
		if (cprTable != null) {
			editCPRControlPanel.showEditControlPanel(cprTable, tableTV);
			formController.showEditControlPanel(editCPRControlPanel);
		}
	}


	@Override
	public void setActionSubmitButton() {
		addButton.setOnAction(event -> addItem());
		removeButton.setOnAction(event -> deleteItem(tableTV));
		editButton.setOnAction(event -> showEditPanel());
	}

	@Override
	public Node getTable() {
		tableTV = new TableView<CPRTable>();
		tableTV.setId("cprTable");
		TableColumn<CPRTable, String> nameColumn = new TableColumn<CPRTable, String>("Name");
		TableColumn<CPRTable, String> roleColumn = new TableColumn<CPRTable, String>("Role");

		nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
		nameColumn.setMinWidth(150);
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		roleColumn.setCellValueFactory(new PropertyValueFactory("role"));
		roleColumn.setMinWidth(150);
		roleColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		tableTV.getColumns().addAll(nameColumn, roleColumn);
		tableTV.setOnMousePressed(OnMousePressedEventHandler);
		tableTV.setEditable(false);

		tableTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		tableTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableTV.setOnKeyReleased(event -> deleteSelected(event));

		BorderPane.setMargin(tableTV, new Insets(5));

		return tableTV;
	}


	@Override
	public void addItem() {

		String nameST = ""; // cprControlPanel.getName();
		int id = formController.createTableItem(SegmentType.ConfigPersonRelation);
		int roleIndex = 0;// cprControlPanel.getRoleIndex();
		CPRTable cpr = formDataController.prepareCPRToTable(nameST, roleIndex, id);

		tableTV.getItems().add(cpr);
		tableTV.sort();
		formDataController.saveDataFromCPR(nameST, roleIndex, cpr);

		int lastItem = tableTV.getItems().size();
		tableTV.getSelectionModel().select(lastItem - 1);
		showEditPanel();
	}

	/** Getrs and Setrs ***/
	public TableView<CPRTable> getTableTV() {
		return tableTV;
	}

}
