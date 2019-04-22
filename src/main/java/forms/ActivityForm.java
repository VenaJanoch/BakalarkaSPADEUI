package forms;

import abstractform.TableBasicForm;
import controlPanels.ActivityControlPanel;
import controllers.graphicsComponentsControllers.CanvasController;
import controllers.formControllers.FormController;
import graphics.panels.DragAndDropItemPanel;
import interfaces.*;
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
import tables.ActivityTable;

/**
 * Třída představující formuláře segmentu Activity, děděná od
 * DescriptionBasicForm a implementující ISegmentForm
 * 
 * @author Václav Janoch
 *
 */
public class ActivityForm extends TableBasicForm implements ISegmentTableForm {

	/**
	 * Globální proměnné třídy
	 */
	private TableView<ActivityTable> tableTV;
	private ActivityControlPanel editControlPanel;


	/**
	 * Konstruktor Třídy Zinicializuje globální proměnné tříd Nastaví reakci na
	 * ukončení formuláře
	 *
	 */
	public ActivityForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, CanvasController canvasController,
						DragAndDropItemPanel dgItemPanel, SegmentType type, int indexForm) {

		super(formController, formDataController, editFormController, deleteFormController, type);
		editControlPanel = new ActivityControlPanel("Edit", formDataController, editFormController, formController);
		setEventHandler();
		createForm();
		setActionSubmitButton();
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
	public void createForm() {

		this.setCenter(getTable());

	}

	@Override
	public Node getTable() {
		tableTV = new TableView<ActivityTable>();

		TableColumn<ActivityTable, String> nameColumn = new TableColumn<ActivityTable, String>("Alias");
		TableColumn<ActivityTable, String> exist = new TableColumn<ActivityTable, String>("Exist");

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

	private void showEditPanel(){
		ActivityTable table = tableTV.getSelectionModel().getSelectedItems().get(0);
		if (table != null) {
			editControlPanel.showEditControlPanel(table, tableTV);
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

		int id = formController.createTableItem(SegmentType.Activity);

		ActivityTable table = new ActivityTable(String.valueOf(id), true, id);

		tableTV.getItems().add(table);
		tableTV.sort();
		int lastItem = tableTV.getItems().size();
		tableTV.getSelectionModel().select(lastItem - 1);
		showEditPanel();
	}

	public TableView<ActivityTable> getTableTV() {
		return tableTV;
	}
}
