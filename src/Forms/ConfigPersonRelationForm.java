package Forms;

import AbstractForm.BasicForm;
import AbstractForm.TableBasicForm;
import Graphics.CanvasItem;
import Graphics.InfoBoxSegment;
import Interfaces.ISegmentForm;
import Interfaces.ISegmentTableForm;
import Services.Alerts;
import Services.Control;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
import javafx.stage.WindowEvent;
import tables.CPRTable;
import tables.CriterionTable;
import tables.MilestoneTable;

public class ConfigPersonRelationForm extends TableBasicForm implements ISegmentTableForm {

	private Label configurationLB;
	private Label personRoleLB;
	private Label nameLB;

	private TextField nameTF;
	private ComboBox<String> configurationCB;
	private ComboBox<String> personCB;

	private TableView<CPRTable> tableTV;

	private int roleIndex;
	private int configIndex;

	public ConfigPersonRelationForm(Control control) {
		super(control);

		createForm();

	}

	@Override
	public void setActionSubmitButton() {
		close();
	}

	@Override
	public void createForm() {
	}

	@Override
	public Node getTable() {
		tableTV = new TableView<CPRTable>();

		TableColumn<CPRTable, String> nameColumn = new TableColumn<CPRTable, String>("Name");
		TableColumn<CPRTable, String> confColumn = new TableColumn<CPRTable, String>("Configuration");
		TableColumn<CPRTable, String> roleColumn = new TableColumn<CPRTable, String>("Role");

		nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
		nameColumn.setMinWidth(150);
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		confColumn.setCellValueFactory(new PropertyValueFactory("configuration"));
		confColumn.setMinWidth(150);
		confColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		confColumn.setCellValueFactory(new PropertyValueFactory("role"));
		confColumn.setMinWidth(150);
		confColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		tableTV.getColumns().addAll(nameColumn, confColumn, roleColumn);

		tableTV.setEditable(false);

		tableTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		tableTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableTV.setOnKeyReleased(event -> deleteSelected(event));

		BorderPane.setMargin(tableTV, new Insets(5));

		return tableTV;
	}

	@Override
	public void deleteSelected(KeyEvent event) {
		ObservableList<CPRTable> selection = FXCollections
				.observableArrayList(tableTV.getSelectionModel().getSelectedItems());

		if (event.getCode() == KeyCode.DELETE) {
			if (selection.size() == 0) {
				Alerts.showNoItemsDeleteAlert();
			} else {
				Alerts.showDeleteItemAlert(tableTV, selection);
			}
		}

	}

	@Override
	public GridPane createControlPane() {

		nameLB = new Label("Name");
		nameTF = new TextField();

		configurationLB = new Label("Configuration: ");
		configurationCB = new ComboBox<String>(getControl().getConfigObservable());
		configurationCB.setVisibleRowCount(5);
		configurationCB.getSelectionModel().selectedIndexProperty().addListener(configListener);

		personRoleLB = new Label("Person-Role");
		personCB = new ComboBox<String>(getControl().getRoleObservable());
		personCB.setVisibleRowCount(5);
		personCB.getSelectionModel().selectedIndexProperty().addListener(roleListener);

		getControlPane().add(nameLB, 0, 0);
		getControlPane().add(nameTF, 1, 0);
		getControlPane().add(configurationLB, 2, 0);
		getControlPane().add(configurationCB, 3, 0);

		getControlPane().add(personRoleLB, 4, 0);
		getControlPane().add(personCB, 5, 0);

		getControlPane().add(getAddBT(), 6, 0);

		getAddBT().setOnAction(event -> addItem());

		return getControlPane();
	}

	ChangeListener<Number> roleListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			roleIndex = newValue.intValue();

		}
	};

	ChangeListener<Number> configListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			configIndex = newValue.intValue();

		}
	};

	@Override
	public void addItem() {
		String nameST = nameTF.getText();
		String configST = getControl().getConfigObservable().get(configIndex);
		String roleST = getControl().getRoleObservable().get(roleIndex);

		if (nameST.length() == 0) {

			Alerts.showNoNameAlert();
			return;
		}

		CPRTable cpr = new CPRTable(nameST, configST, roleST);
		tableTV.getItems().add(cpr);
		tableTV.sort();
		getControl().getFillForms().fillCPR(nameST, configIndex, roleIndex);
	}

	public TableView<CPRTable> getTableTV() {
		return tableTV;
	}

	public void setTableTV(TableView<CPRTable> tableTV) {
		this.tableTV = tableTV;
	}
	
	
	

}
