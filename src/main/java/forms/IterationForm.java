package forms;

import java.time.LocalDate;

import abstractform.TableBasicForm;
import controlPanels.IterationControlPanel;
import controlPanels.PhaseControlPanel;
import controllers.CanvasController;
import controllers.FormController;
import abstractform.Date2DescBasicForm;
import graphics.DragAndDropItemPanel;
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
import services.Alerts;
import services.CanvasType;
import services.SegmentType;
import tables.BasicTable;
import tables.IterationTable;
import tables.PhaseTable;

/**
 * Třída představující formulář pro segment Iteration, odděděná od třídy
 * Dete2DescBasicForm a implementující ISegmentForm
 * 
 * @author Václav Janoch
 *
 */
public class IterationForm extends TableBasicForm implements ISegmentTableForm {

	/**
	 * Globální proměnné třídy
	 */
	private TableView<IterationTable> tableTV;
	private IterationControlPanel editControlPanel;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné tříd Nastaví reakci na
	 * uzavření formuláře
	 * @param indexForm
	 *            DeleteControl
	 */
	public IterationForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, CanvasController canvas, DragAndDropItemPanel dgItemPanel,
						 SegmentType type, int indexForm) {

		super(formController, formDataController, editFormController, deleteFormController, type);
		editControlPanel = new IterationControlPanel("Edit", formDataController, editFormController, formController);
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
		tableTV = new TableView<IterationTable>();

		TableColumn<IterationTable, String> nameColumn = new TableColumn<IterationTable, String>("Name");
		TableColumn<IterationTable, String> configurationColumn = new TableColumn<IterationTable, String>("Configuration");

		nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
		nameColumn.setMinWidth(150);
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		configurationColumn.setCellValueFactory(new PropertyValueFactory("configuration"));
		configurationColumn.setMinWidth(150);
		configurationColumn.setCellFactory(TextFieldTableCell.forTableColumn());


		//tableTV.getColumns().addAll(nameColumn, configurationColumn);
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
		IterationTable table = tableTV.getSelectionModel().getSelectedItems().get(0);
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
		String nameST = "";// criterionControlPanel.getName();

		int id = formController.createTableItem(SegmentType.Iteration);
		String idName = id + "_" + nameST;

		IterationTable table = new IterationTable(idName, "", id);

		tableTV.getItems().add(table);
		tableTV.sort();
		int lastItem = tableTV.getItems().size();
		tableTV.getSelectionModel().select(lastItem - 1);
		showEditPanel();
	}
}
