package Forms;

import Interfaces.ISegmentForm;
import SPADEPAC.Configuration;
import Services.Alerts;
import Services.Constans;
import Services.Control;
import Services.FillForms;
import Services.OrderCell;
import Services.TagTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class TagForm extends Stage implements ISegmentForm {

	private BorderPane mainPanel;
	private Scene scena;
	private Alerts alerts;
	private Control control;
	private TableView<TagTable> tableTV;
	private GridPane controlPane;
	private TextField tagTF;
	private Button addBT;
	private Button submitBT;
	private int[] confIDs;
	private FillForms fillForms;
	private Configuration config;

	public TagForm(Configuration configuration, Control control) {

		super();
		// this.confIDs = confIDs;
		this.control = control;
		this.alerts = new Alerts();
		this.config = configuration;
		mainPanel = new BorderPane();

		this.setScene(creatScene());

	}

	private Scene creatScene() {
		createForm();
		scena = new Scene(mainPanel, Constans.formWidth, Constans.formHeight);

		return scena;
	}

	private Node getTable() {
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

	private void deleteSelected(KeyEvent event) {
		ObservableList<TagTable> selection = FXCollections
				.observableArrayList(tableTV.getSelectionModel().getSelectedItems());

		if (event.getCode() == KeyCode.DELETE) {
			if (selection.size() == 0) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Deleting items");
				alert.setHeaderText("No items for deleting were selected!");
				alert.setContentText("Please select items for deleting.");
				alert.show();
			} else {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Deleting selection");
				alert.setHeaderText("Do you want to delete selected elements?");
				alert.setGraphic(new ListView<TagTable>(selection));
				alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> {
					tableTV.getItems().removeAll(selection);
					tableTV.getSelectionModel().clearSelection();
				});
			}
		}
	}

	private GridPane createControlPane() {

		Label tagLB = new Label("Tag: ");
		tagTF = new TextField();
		addBT = new Button("Add");

		controlPane.add(tagLB, 0, 0);
		controlPane.add(tagTF, 1, 0);
		controlPane.add(addBT, 2, 0);

		addBT.setPrefWidth(60);
		addBT.setPrefHeight(60);
		addBT.setOnAction(event -> addTag());

		controlPane.setHgap(3);
		controlPane.setVgap(3);

		controlPane.setAlignment(Pos.CENTER);
		controlPane.setPadding(new Insets(5));

		return controlPane;
	}

	private void addTag() {
		String tagST = tagTF.getText();

		if (tagST.length() == 0) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Insert error");
			alert.setHeaderText("No tag is provided!");
			alert.setContentText("Please provide a tag in corresponding filed!");
			alert.showAndWait();
			return;
		}

		TagTable tag = new TagTable(tagST);
		tableTV.getItems().add(tag);
		System.out.println(tagST + " " + FXCollections.observableArrayList(tableTV.getItems()));
		tableTV.sort();

	}

	@Override
	public void closeForm() {

		control.getFillForms().fillTag(config, FXCollections.observableArrayList(tableTV.getItems()));

	}

	@Override
	public void setActionSubmitButton() {
		closeForm();
		close();

	}

	@Override
	public void createForm() {
		mainPanel.setPadding(new Insets(5));
		controlPane = new GridPane();

		submitBT = new Button("OK");
		submitBT.setOnAction(event -> setActionSubmitButton());

		mainPanel.setCenter(getTable());
		mainPanel.setBottom(createControlPane());
		mainPanel.setRight(submitBT);

	}

}
