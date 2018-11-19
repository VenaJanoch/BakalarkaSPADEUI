package forms;

import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.Configuration;
import abstractform.TableBasicForm;
import interfaces.ISegmentTableForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import services.*;
import model.IdentificatorCreater;
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
	private TextField tagTF;

	private int configId;

	/**
	 * Konstruktor třídy. Zinicializuje globální proměnné tříd Nastaví velikost
	 * formuláře
	 */
	public TagForm(FormController formController, FormDataController formDataController, String name, int configFormId) {

		super(formController, formDataController, name);
		this.setTitle("Edit Tags");
		this.configId = configFormId;
		createForm();

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
					 formDataController.deleteTag(configId, list);
				}

			}
		}

	}

	@Override
	public GridPane createControlPane() {

		getNameLB().setText("Tag");
		tagTF = new TextField();

		getControlPane().add(tagTF, 1, 0);
		getControlPane().add(getAddBT(), 2, 0);

		getAddBT().setOnAction(event -> addItem());

		return getControlPane();
	}

	@Override
	public void addItem() {
		String tagST = tagTF.getText();
		int id = formController.createTableItem(SegmentType.Tag);

		String idName = id + "_" + tagST;

		TagTable tag = new TagTable(idName);
		formDataController.saveDataFromTagForm(tagST, idName, configId, id);

		tableTV.getItems().add(tag);
		tableTV.sort();

	}

	@Override
	public void setActionSubmitButton() {
		close();

	}

	@Override
	public void createForm() {

		getFormName().setText("Tag Form");

		getSubmitButton().setOnAction(event -> setActionSubmitButton());

		getMainPanel().setCenter(getTable());
		getMainPanel().setBottom(createControlPane());

	}

	/*** Getrs and Setrs */
	public TableView<TagTable> getTableTV() {
		return tableTV;
	}

	public void setTableTV(TableView<TagTable> tableTV) {
		this.tableTV = tableTV;
	}

}
