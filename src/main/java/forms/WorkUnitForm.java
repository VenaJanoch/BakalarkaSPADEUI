package forms;

import abstractform.TableBasicForm;
import controlPanels.PhaseControlPanel;
import controlPanels.WorkUnitControlPanel;
import controllers.CanvasController;
import controllers.FormController;
import abstractform.DescriptionBasicForm;
import interfaces.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
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
import services.*;
import tables.BasicTable;
import tables.PhaseTable;
import tables.WorkUnitTable;

/**
 * Třída představující formulář pro element Work Unit, odděděná od třídy
 * DescriptionBasicForm a implementující ISegmentForm
 * 
 * @author Václav Janoch
 *
 */
public class WorkUnitForm extends TableBasicForm implements ISegmentTableForm {


	private TableView<WorkUnitTable> tableTV;
	private WorkUnitControlPanel editControlPanel;

	/**
	 * Konstruktor třídy. Zinicializuje globální proměnné tříd Nastaví velikost
	 * okna a reakci na uzavření formulář
	 */
	public WorkUnitForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, CanvasController canvasController, SegmentType type, int indexForm) {
		super(formController, formDataController, editFormController, deleteFormController, type);

		editControlPanel = new WorkUnitControlPanel("Edit", formDataController, editFormController, formController);
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
		tableTV = new TableView<WorkUnitTable>();

		TableColumn<WorkUnitTable, String> nameColumn = new TableColumn<WorkUnitTable, String>("Name");

		nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
		nameColumn.setMinWidth(150);
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());


		tableTV.getColumns().addAll(nameColumn);
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
		WorkUnitTable phaseTable = tableTV.getSelectionModel().getSelectedItems().get(0);
		if (phaseTable != null) {
			editControlPanel.showEditControlPanel(phaseTable, tableTV);
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
		String nameST = "";// criterionControlPanel.getName();

		int id = formController.createTableItem(SegmentType.WorkUnit);
		String idName = id + "_" + nameST;

		WorkUnitTable table = new WorkUnitTable(idName, id);
		tableTV.getItems().add(table);
		tableTV.sort();
		formDataController.saveDataFromWorkUnit(nameST, table);
		int lastItem = tableTV.getItems().size();
		tableTV.getSelectionModel().select(lastItem - 1);
		showEditPanel();
	}

}
