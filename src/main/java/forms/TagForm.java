package forms;

import controlPanels.VCSTagControlPanel;
import controllers.FormController;
import abstractform.TableBasicForm;
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
import services.*;
import tables.TagTable;

/**
 * Třída představující tabulkový formulář pro element Tag, odděděná od třídy
 * TableBasicForm a implementující ISegmentTableForm
 * 
 * @author Václav Janoch
 *
 */
public class TagForm extends TableBasicForm implements ISegmentTableForm {
	/**
	 * Globální proměnné třídy
	 */
	private TableView<TagTable> tableTV;
	private VCSTagControlPanel tagControlPanel;
	private VCSTagControlPanel editTagControlPanel;
	private int configId;

	/**
	 * Konstruktor třídy. Zinicializuje globální proměnné tříd Nastaví velikost
	 * formuláře
	 */
	public TagForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type, int configFormId) {

		super(formController, formDataController, editFormController, deleteFormController, type);
//		tagControlPanel = new VCSTagControlPanel("Add", formDataController, editFormController);
//		editTagControlPanel = new VCSTagControlPanel("Edit", formDataController, editFormController);
//		editTagControlPanel.createControlPanel();

	//this.setTitle("Edit Tags");
		this.configId = configFormId;
		createForm();
		setEventHandler();
	}

	@Override
	protected void setEventHandler() {
		OnMousePressedEventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				if(t.getClickCount() == 2) {
					TagTable tagTable = tableTV.getSelectionModel().getSelectedItems().get(0);
					if (tagTable != null) {
		//				editTagControlPanel.showEditControlPanel(tagTable, configId, tableTV);
					}
				}
			}
		};
	}


	@Override
	public Node getTable() {
		tableTV = new TableView<TagTable>();

		TableColumn<TagTable, Integer> numberColumn = new TableColumn<TagTable, Integer>("Order");
		TableColumn<TagTable, String> tagColumn = new TableColumn<TagTable, String>("Tag");

		numberColumn.setMinWidth(40);
		numberColumn.setMaxWidth(40);
		numberColumn.setEditable(false);
		numberColumn.setSortable(false);
		numberColumn.setCellFactory(column -> {
			return new OrderCell();
		});

		tagColumn.setCellValueFactory(new PropertyValueFactory("tag"));
		tagColumn.setMinWidth(150);
		tagColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		tableTV.getColumns().addAll(numberColumn, tagColumn);
		tableTV.setOnMousePressed(OnMousePressedEventHandler);
		tableTV.setEditable(true);

		tableTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		tableTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableTV.setOnKeyReleased(event -> deleteSelected(event));

		BorderPane.setMargin(tableTV, new Insets(5));

		return tableTV;
	}

	@Override
	public void deleteSelected(KeyEvent event) {
		ObservableList<TagTable> selection = FXCollections
				.observableArrayList(tableTV.getSelectionModel().getSelectedItems());

		ObservableList<TagTable> list = null;

		if (event.getCode() == KeyCode.DELETE) {
			if (selection.size() == 0) {
				Alerts.showNoItemsDeleteAlert();
			} else {
				list = Alerts.showDeleteItemTagAlert(getTableTV(), selection);
				if (list != null) {
					 deleteFormController.deleteTag(configId, list);
				}

			}
		}

	}

	@Override
	public GridPane createControlPane() {

//		GridPane controlPane = tagControlPanel.createControlPanel();
//
//		addButton = tagControlPanel.getButton();
//		addButton.setOnAction(event -> addItem());
//
//		return controlPane;
		return null;
	}

	@Override
	public void addItem() {
		String tagST = tagControlPanel.getName();
		int id = formController.createTableItem(SegmentType.Tag);

		String idName = id + "_" + tagST;

		TagTable tag = new TagTable(idName, id);
		formDataController.saveDataFromTagForm(tagST, configId, id);

		tableTV.getItems().add(tag);
		tableTV.sort();
	//	tagControlPanel.clearPanel(tableTV);

	}

	@Override
	public void setActionSubmitButton() {
	//	close();

	}

	@Override
	public void createForm() {

		getFormName().setText("Tag Form");

		getSubmitButton().setOnAction(event -> setActionSubmitButton());

		this.setCenter(getTable());
		this.setBottom(createControlPane());

	}

	/*** Getrs and Setrs */
	public TableView<TagTable> getTableTV() {
		return tableTV;
	}

	public void setTableTV(TableView<TagTable> tableTV) {
		this.tableTV = tableTV;
	}

}
