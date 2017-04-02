package Forms;

import org.controlsfx.control.CheckComboBox;

import AbstractForm.BasicForm;
import Graphics.CanvasItem;
import Graphics.InfoBoxSegment;
import Interfaces.ISegmentForm;
import SPADEPAC.Configuration;
import SPADEPAC.Milestone;
import Services.Alerts;
import Services.Constans;
import Services.Control;
import Services.FillForms;
import Services.MilestoneTable;
import Services.OrderCell;
import Services.TagTable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MilestoneForm extends Stage implements ISegmentForm {

	private Label criteriaLB;
	private Label nameLB;

	private TextField nameTF;
	private CheckComboBox<String> criteriaCB;
	private BorderPane mainPanel;
	private BorderPane milestonePanel;
	private Scene scena;
	private Alerts alerts;
	private Control control;
	private TableView<MilestoneTable> tableTV;
	private GridPane controlPane;
	private Button addBT;
	private Button submitBT;

	private CriterionForm criterionForm;

	private ObservableList<String> criterionArray;
	private ObservableList<Integer> criterionIndex;

	public MilestoneForm(Control control) {

		this.control = control;
		this.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				closeForm();
			}
		});

		createForm();
		submitBT.setOnAction(event -> setActionSubmitButton());
		this.setScene(creatScene());
	}

	@Override
	public void closeForm() {

	}

	@Override
	public void setActionSubmitButton() {
		closeForm();
		close();
	}

	@Override
	public void createForm() {

		milestonePanel = new BorderPane();
		milestonePanel.setPadding(new Insets(5));
		controlPane = new GridPane();

		submitBT = new Button("OK");
		submitBT.setOnAction(event -> setActionSubmitButton());

		milestonePanel.setCenter(getTable());
		milestonePanel.setBottom(createControlPane());

		criterionForm = new CriterionForm(control);

		mainPanel = new BorderPane();
		mainPanel.setCenter(milestonePanel);
		mainPanel.setRight(criterionForm);
		mainPanel.setBottom(submitBT);

	}

	private Scene creatScene() {
		createForm();
		scena = new Scene(mainPanel, Constans.milestoneFormWidth, Constans.milestoneFormHeight);

		return scena;
	}

	private Node getTable() {
		tableTV = new TableView<MilestoneTable>();

		TableColumn<MilestoneTable, String> nameColumn = new TableColumn<MilestoneTable, String>("Name");
		TableColumn<MilestoneTable, String> criterionColumn = new TableColumn<MilestoneTable, String>("Criterion");

		nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
		nameColumn.setMinWidth(150);
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		criterionColumn.setCellValueFactory(new PropertyValueFactory("criterion"));
		criterionColumn.setMinWidth(150);
		criterionColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		tableTV.getColumns().addAll(nameColumn, criterionColumn);

		tableTV.setEditable(false);

		tableTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		tableTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableTV.setOnKeyReleased(event -> deleteSelected(event));

		BorderPane.setMargin(tableTV, new Insets(5));

		return tableTV;
	}

	private void deleteSelected(KeyEvent event) {
		ObservableList<MilestoneTable> selection = FXCollections
				.observableArrayList(tableTV.getSelectionModel().getSelectedItems());

		if (event.getCode() == KeyCode.DELETE) {
			if (selection.size() == 0) {
				Alerts.showNoItemsDeleteAlert();
			} else {
				Alerts.showDeleteItemAlert(tableTV, selection);
			}
		}
	}

	private GridPane createControlPane() {

		controlPane = new GridPane();
		Label nameLB = new Label("Name: ");
		nameTF = new TextField();

		addBT = new Button("Add");

		criteriaLB = new Label("Criteria: ");
		criteriaCB = new CheckComboBox<String>(control.getCriterionObservable());
		criteriaCB.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {

			public void onChanged(ListChangeListener.Change<? extends String> c) {
				System.out.println(criteriaCB.getCheckModel().getCheckedIndices());
				criterionIndex = criteriaCB.getCheckModel().getCheckedIndices();
				criterionArray = criteriaCB.getCheckModel().getCheckedItems();

			}
		});

		controlPane.add(nameLB, 0, 0);
		controlPane.add(nameTF, 1, 0);
		controlPane.add(criteriaLB, 2, 0);
		controlPane.add(criteriaCB, 3, 0);
		controlPane.add(addBT, 4, 0);

		addBT.setPrefWidth(60);
		addBT.setPrefHeight(60);
		addBT.setOnAction(event -> addMilestone());

		controlPane.setHgap(3);
		controlPane.setVgap(3);

		controlPane.setAlignment(Pos.CENTER);
		controlPane.setPadding(new Insets(5));

		return controlPane;
	}

	private void addMilestone() {
		String nameST = nameTF.getText();

		if (nameST.length() == 0) {

			Alerts.showNoNameAlert();
			return;
		}

		
		MilestoneTable milestone = new MilestoneTable(nameST, criterionArray.toString());
		tableTV.getItems().add(milestone);
		tableTV.sort();
		control.getFillForms().fillMilestone(nameST, criterionIndex);

	}

}
