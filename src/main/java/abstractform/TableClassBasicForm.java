package abstractform;

import controlPanels.ClassControlPanel;
import controllers.FormController;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import interfaces.ISegmentTableForm;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import services.SegmentType;
import tables.ClassTable;
import javafx.geometry.Insets;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;

/**
 * Třída sloužící k vytvoření tabulkového formuláře výčtových typů. Odděděná od
 * třídy TableBasicForm
 * 
 * @author Václav Janoch
 *
 */
public abstract class TableClassBasicForm extends TableBasicForm implements ISegmentTableForm {
	/**
	 * Globální proměnné třídy
	 */

	protected TableView<ClassTable> tableTV;
	protected EventHandler<MouseEvent> OnMousePressedEventHandler;
	protected ClassControlPanel editClassControlPanelTCB;


	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy
	 */
	public TableClassBasicForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController,
							   IDeleteFormController deleteFormController, SegmentType type) {

		super(formController, formDataController, editFormController, deleteFormController, type);


	}

	protected void setEventHandler() {
		OnMousePressedEventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				if(t.getClickCount() == 2) {
					ClassTable classTable = tableTV.getSelectionModel().getSelectedItems().get(0);
					if (classTable != null) {
						editClassControlPanelTCB.showEditControlPanel(classTable, tableTV);
						formController.showEditControlPanel(editClassControlPanelTCB);
					}
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


		tableTV = new TableView<ClassTable>();
		tableTV.setId("classTable");
		TableColumn<ClassTable, String> nameColumn = new TableColumn<ClassTable, String>("Name");
		TableColumn<ClassTable, String> classColumn = new TableColumn<ClassTable, String>("Class");
		TableColumn<ClassTable, String> superColumn = new TableColumn<ClassTable, String>("Super Class");
		TableColumn<ClassTable, String> existColumn = new TableColumn<ClassTable, String>("Exist");

		nameColumn.setCellValueFactory(new PropertyValueFactory("idString"));
		nameColumn.setMinWidth(150);
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		classColumn.setCellValueFactory(new PropertyValueFactory("classType"));
		classColumn.setMinWidth(150);
		classColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		superColumn.setCellValueFactory(new PropertyValueFactory("superType"));
		superColumn.setMinWidth(150);
		superColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		superColumn.setCellValueFactory(new PropertyValueFactory("existString"));
		superColumn.setMinWidth(150);
		superColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		//tableTV.getColumns().addAll(nameColumn, classColumn, superColumn, existColumn);
		tableTV.getColumns().addAll(nameColumn, existColumn);
		tableTV.setEditable(false);

		tableTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		tableTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		BorderPane.setMargin(tableTV, new Insets(5));
		tableTV.setOnKeyReleased(event -> deleteSelected(event));
		tableTV.setOnMousePressed(OnMousePressedEventHandler);

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

	@Override
	public void addItem() {
		String nameST = ""; // classControlPanel.getName();

		int id = formController.createTableItem(getSegmentType());
		String idName = id + "_" + nameST;

		String classST = ""; // classControlPanel.getClassName();
		String superST = ""; //classControlPanel.getSuperClassName();

		ClassTable table = new ClassTable(idName, classST, superST, true, id);
		saveData(getSegmentType(), nameST, table);
		tableTV.getItems().add(table);
		tableTV.sort();

		int lastItem = tableTV.getItems().size();
		tableTV.getSelectionModel().select(lastItem - 1);
		showEditPanel();

	}

	public void saveData(SegmentType segmentType, String nameST, ClassTable table) {
		switch (segmentType){
			case Severity:
				formDataController.saveDataFromSeverity(nameST, table);
				break;
			case Priority:
				formDataController.saveDataFromPriority(nameST, table);
				break;
			case Status:
				formDataController.saveDataFromStatusForm(nameST, table);
				break;
			case Type:
				formDataController.saveDataFromTypeForm(nameST, table);
				break;
			case Relation:
				formDataController.saveDataFromRelationForm(nameST, table);
				break;
			case Resolution:
				formDataController.saveDataFromResolutionForm(nameST, table);
				break;
			default:

		}
	}


	@Override
	public void setActionSubmitButton() {
		addButton.setOnAction(event -> addItem());
		removeButton.setOnAction(event -> deleteItem(tableTV));
		editButton.setOnAction(event -> showEditPanel());
	}

	private void showEditPanel(){
		ClassTable table = tableTV.getSelectionModel().getSelectedItems().get(0);
		if (table != null) {
			editClassControlPanelTCB.showEditControlPanel(table, tableTV);
			formController.showEditControlPanel(editClassControlPanelTCB);
		}
	}

	public TableView<ClassTable> getTableTV() {
		return tableTV;
	}
}
