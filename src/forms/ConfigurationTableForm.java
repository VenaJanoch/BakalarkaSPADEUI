package forms;

import abstractform.BasicForm;
import abstractform.Table2BasicForm;
import graphics.CanvasItem;
import interfaces.ISegmentTableForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.util.Callback;
import services.Alerts;
import services.Constans;
import services.Control;
import services.SegmentType;
import tables.ConfigTable;
import tables.MilestoneTable;

public class ConfigurationTableForm extends Table2BasicForm implements ISegmentTableForm {

	private TableView<ConfigTable> tableTV;
	private BasicForm form;
	
	private Label formName;
	
	public ConfigurationTableForm(Control control) {
		super(control);

		createForm();
		getSubmitBT().setOnAction(event -> setActionSubmitButton());
	}

	@Override
	public void createForm() {
		formName = new Label("Configuration form");
		formName.setFont(Font.font(25));
		
		getInternalPanel().setCenter(getTable());

		getMainPanel().setRight(getInternalPanel());
		getMainPanel().setTop(formName);
		getMainPanel().setAlignment(formName, Pos.TOP_CENTER);
		
		createConfigItem();
		getNameTF().setVisible(false);
		getNameLB().setVisible(false);

	}

	public BasicForm createConfigItem() {

		CanvasItem item = new CanvasItem(SegmentType.Configuration, "Name", getControl(),
				getControl().getForms().get(0), true);

		form = getControl().getForms().get(item.getIDs()[0]);
		form.getSubmitButton().setText("Add");
		getMainPanel().setCenter(form.getMainPanel());

		return form;

	}

	@Override
	public Node getTable() {
		tableTV = new TableView<ConfigTable>();

		TableColumn<ConfigTable, String> nameColumn = new TableColumn<ConfigTable, String>("Name");
		TableColumn<ConfigTable, String> releaseColumn = new TableColumn<ConfigTable, String>("Release");

		nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
		nameColumn.setMinWidth(150);
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		releaseColumn.setCellValueFactory(new PropertyValueFactory("release"));
		releaseColumn.setMinWidth(150);
		releaseColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		tableTV.setOnMousePressed(OnMousePressedEventHandler);
		tableTV.getColumns().addAll(nameColumn, releaseColumn);

		tableTV.setEditable(false);
					
		tableTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		tableTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableTV.setOnKeyReleased(event -> deleteSelected(event));

		BorderPane.setMargin(tableTV, new Insets(5));

		return tableTV;
	}
	
	EventHandler<MouseEvent> OnMousePressedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			
				ConfigTable config = tableTV.getSelectionModel().getSelectedItems().get(0);
				int id = config.getId().intValue();
			getMainPanel().setCenter(getControl().getForms().get(id).getMainPanel());
		}
	};

	

	@Override
	public void deleteSelected(KeyEvent event) {

		ObservableList<ConfigTable> selection = FXCollections
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

		return null;
	}

	@Override
	public void addItem() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setActionSubmitButton() {
		close();

	}

	public TableView<ConfigTable> getTableTV() {
		return tableTV;
	}

	public void setTableTV(TableView<ConfigTable> tableTV) {
		this.tableTV = tableTV;
	}

	public BasicForm getForm() {
		return form;
	}

	public void setForm(BasicForm form) {
		this.form = form;
	}
	
	

}
