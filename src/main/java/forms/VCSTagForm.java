package forms;

import abstractform.TableBasicForm;
import controlPanels.VCSTagControlPanel;
import controllers.CanvasController;
import controllers.FormController;
import graphics.DragAndDropItemPanel;
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
import services.SegmentType;
import tables.VCSTagTable;

/**
 * Třída představující formuláře segmentu VCSTag, děděná od
 * DescriptionBasicForm a implementující ISegmentForm
 * 
 * @author Václav Janoch
 *
 */
public class VCSTagForm extends TableBasicForm implements ISegmentTableForm {

	/**
	 * Globální proměnné třídy
	 */
	private TableView<VCSTagTable> tableTV;
	private VCSTagControlPanel editControlPanel;


	/**
	 * Konstruktor Třídy Zinicializuje globální proměnné tříd Nastaví reakci na
	 * ukončení formuláře
	 *
	 */
	public VCSTagForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController,
					  IDeleteFormController deleteFormController, SegmentType type) {

		super(formController, formDataController, editFormController, deleteFormController, type);
		editControlPanel = new VCSTagControlPanel("Edit", formDataController, editFormController, formController);
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
		tableTV = new TableView<VCSTagTable>();

		TableColumn<VCSTagTable, String> nameColumn = new TableColumn<VCSTagTable, String>("Id");
		TableColumn<VCSTagTable, String> exist = new TableColumn<VCSTagTable, String>("Exist");

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
		VCSTagTable table = tableTV.getSelectionModel().getSelectedItems().get(0);
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

		int id = formController.createTableItem(SegmentType.VCSTag);
		String idName = id + "_" + nameST;

		VCSTagTable table = new VCSTagTable(idName, "",true, id);
		formDataController.saveDataFromVCSTag(nameST,table);
		tableTV.getItems().add(table);
		tableTV.sort();
		int lastItem = tableTV.getItems().size();
		tableTV.getSelectionModel().select(lastItem - 1);
		showEditPanel();
	}

	public TableView<VCSTagTable> getTableTV() {
		return tableTV;
	}
}
