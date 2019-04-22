package forms;

import controllers.formControllers.FormController;
import abstractform.TableBasicForm;
import controlPanels.CriterionControlPanel;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import interfaces.ISegmentTableForm;
import javafx.event.EventHandler;
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
import services.SegmentType;
import tables.*;

/**
 * Třída představující tabulkový formulář pro element Criterion, odděděná od
 * třídy TableBasicForm a implementující ISegmentTableForm
 * 
 * @author Václav Janoch
 *
 */
public class CriterionForm extends TableBasicForm implements ISegmentTableForm {
	/**
	 * Globální proměnné třídy
	 */
	private TableView<CriterionTable> tableTV;
	private CriterionControlPanel editCriterionControlPanel;
	/**
	 * Konstruktor třídy Zinicializuje globální proměnné tříd
	 *
	 *
	 */

	 public CriterionForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController,
						  SegmentType type) {
		super(formController, formDataController, editFormController, deleteFormController, type);
		editCriterionControlPanel = new CriterionControlPanel("Edit", formDataController, editFormController);
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
		tableTV = new TableView<CriterionTable>();

		TableColumn<CriterionTable, String> nameColumn = new TableColumn<CriterionTable, String>("Id");
		TableColumn<CriterionTable, String> exist = new TableColumn<CriterionTable, String>("Exist");

		nameColumn.setCellValueFactory(new PropertyValueFactory("idString"));
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
		CriterionTable criterionTable = tableTV.getSelectionModel().getSelectedItems().get(0);
		if (criterionTable != null) {
			editCriterionControlPanel.showEditControlPanel(criterionTable, tableTV);
			formController.showEditControlPanel(editCriterionControlPanel);
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
		String descriptionST = "" ;// criterionControlPanel.getDescriptionText();

		int id = formController.createTableItem(SegmentType.Criterion);
		String idName = id + "_" + nameST;

		CriterionTable criterion = new CriterionTable(idName,true, id);
		tableTV.getItems().add(criterion);
		tableTV.sort();
		formDataController.saveDataFromCriterionForm(nameST, criterion);
		int lastItem = tableTV.getItems().size();
		tableTV.getSelectionModel().select(lastItem - 1);
		showEditPanel();
	 }

	public TableView<CriterionTable> getTableTV() {
		return tableTV;
	}

	public void setTableTV(TableView<CriterionTable> tableTV) {
		this.tableTV = tableTV;
	}

}
