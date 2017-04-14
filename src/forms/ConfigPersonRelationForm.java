package forms;

import abstractform.BasicForm;
import abstractform.TableBasicForm;
import graphics.CanvasItem;
import graphics.InfoBoxSegment;
import interfaces.ISegmentForm;
import interfaces.ISegmentTableForm;
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
import services.Alerts;
import services.Control;
import tables.CPRTable;
import tables.CriterionTable;
import tables.MilestoneTable;

public class ConfigPersonRelationForm extends TableBasicForm implements ISegmentTableForm {

	private Label configurationLB;
	private Label personRoleLB;

	private ComboBox<String> configurationCB;
	private ComboBox<String> personCB;

	private TableView<CPRTable> tableTV;

	private int roleIndex;
	private int configIndex;

	public ConfigPersonRelationForm(Control control) {
		super(control);

		createForm();
		getSubmitButton().setOnAction(event -> setActionSubmitButton());

	}

	@Override
	public void setActionSubmitButton() {
		close();
	}

	@Override
	public void createForm() {
		
		getFormName().setText("CPR form");

		getMainPanel().setCenter(getTable());
		getMainPanel().setBottom(createControlPane());
		
	}

	@Override
	public Node getTable() {
		tableTV = new TableView<CPRTable>();

		TableColumn<CPRTable, String> nameColumn = new TableColumn<CPRTable, String>("Name");
		TableColumn<CPRTable, String> roleColumn = new TableColumn<CPRTable, String>("Role");

		nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
		nameColumn.setMinWidth(150);
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		
		roleColumn.setCellValueFactory(new PropertyValueFactory("role"));
		roleColumn.setMinWidth(150);
		roleColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		tableTV.getColumns().addAll(nameColumn, roleColumn);

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

		
		
		personRoleLB = new Label("Person-Role");
		personCB = new ComboBox<String>(getControl().getLists().getRoleObservable());
		personCB.setVisibleRowCount(5);
		personCB.getSelectionModel().selectedIndexProperty().addListener(roleListener);

		
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
		String nameST = getNameTF().getText();
		String roleST = getControl().getLists().getRoleObservable().get(roleIndex);

		if (nameST.length() == 0) {

			Alerts.showNoNameAlert();
			return;
		}

		CPRTable cpr = new CPRTable(nameST, roleST);
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
