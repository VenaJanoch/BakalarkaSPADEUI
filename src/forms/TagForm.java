package forms;

import SPADEPAC.Configuration;
import abstractform.TableBasicForm;
import interfaces.ISegmentForm;
import interfaces.ISegmentTableForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import services.Alerts;
import services.Constans;
import services.Control;
import services.DeleteControl;
import services.FillForms;
import services.OrderCell;
import tables.ClassTable;
import tables.TagTable;

public class TagForm extends TableBasicForm implements ISegmentTableForm {

	private Control control;
	private TableView<TagTable> tableTV;
	private TextField tagTF;
	private Configuration config;
	

	public TagForm(Configuration configuration, Control control, DeleteControl deleteControl) {

		super(control, deleteControl);
		// this.confIDs = confIDs;
		this.control = control;
		this.config = configuration;
		this.setTitle("Edit Tags");
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
					deleteControl.deleteTag(config ,list);
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

		if (tagST.length() == 0) {
			Alerts.showNoText("Tag");
			return;
		}

		TagTable tag = new TagTable(tagST);
		config.getTags().add(tagST);
		tableTV.getItems().add(tag);
		tableTV.sort();

	}

	@Override
	public void setActionSubmitButton() {
		close();

	}

	@Override
	public void createForm() {

		getFormName().setText("Tag form");

		getSubmitButton().setOnAction(event -> setActionSubmitButton());

		getMainPanel().setCenter(getTable());
		getMainPanel().setBottom(createControlPane());

	}



	public TableView<TagTable> getTableTV() {
		return tableTV;
	}



	public void setTableTV(TableView<TagTable> tableTV) {
		this.tableTV = tableTV;
	}

	
	
}
