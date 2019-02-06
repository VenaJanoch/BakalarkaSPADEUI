package forms;

import Controllers.FormController;
import Controllers.FormDataController;
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
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import services.Alerts;
import services.Control;
import services.DeleteControl;
import model.IdentificatorCreater;
import services.SegmentType;
import tables.BasicTable;
import tables.ConfigTable;

import java.util.ArrayList;

/**
 * Třída představující dvojitý formulář pro element Configuration, vytvoří
 * tabulku s přehledem configurací a formulář pro vyplnění dat od configuraci
 * odděděná od třídy Table2BasicForm a implementující ISegmentTableForm
 * 
 * @author Václav Janoch
 *
 */
public class ConfigurationTableForm extends Table2BasicForm implements ISegmentTableForm {
	/**
	 * Globální proměnné třídy
	 */

	private TableView<ConfigTable> tableTV;

	private Label formName;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné tříd
	 *
	 */

	public ConfigurationTableForm(FormController formController, FormDataController formDataController, SegmentType type) {
		super(formController, formDataController, type);

		createForm();
		setEventHandler();
		getSubmitBT().setOnAction(event -> setActionSubmitButton());
	}

	@Override
	protected void setEventHandler() {

	}

	@Override
	public void createForm() {
		formName = new Label("Configuration form");
		formName.setFont(Font.font(25));

		getInternalPanel().setCenter(getTable());
		getInternalPanel().setLeft(new SplitPane());

		mainPanel.setTop(formName);
		mainPanel.setAlignment(formName, Pos.TOP_CENTER);

		createConfigItem();
		getNameTF().setVisible(false);
		getNameLB().setVisible(false);
	}

	/**
	 * Pomocná metoda pro vytvoření ConfigForm
	 * 
	 * @return BasicForm
	 */
	public void createConfigItem() {

		int id  = formController.createNewForm(SegmentType.Configuration, null);
		getSubmitButton().setText("Add");
		mainPanel.setCenter(formController.getMainPanelFromForm(id));
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

	/**
	 * EventHandler pro určení prvku z tabulky a zobrazní odpovídajícího
	 * formuláře
	 */
	EventHandler<MouseEvent> OnMousePressedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {

			ConfigTable config = tableTV.getSelectionModel().getSelectedItems().get(0);
			if (config != null) {
				int id = config.getIdProperty().intValue();
				mainPanel.setCenter(formController.getMainPanelFromForm(id));
			}
		}
	};

	@Override
	public void deleteSelected(KeyEvent event) {

		ObservableList<ConfigTable> selection = FXCollections
				.observableArrayList(tableTV.getSelectionModel().getSelectedItems());

		if (event.getCode() == KeyCode.DELETE) {
			if (selection.size() == 0) {
				Alerts.showNoItemsDeleteAlert();
			}
			else{
				ArrayList<BasicTable> list = new ArrayList<>(selection);
				formDataController.deleteConfiguration(list, getTableTV());
			}
		}

	}

	@Override
	public GridPane createControlPane() {

		return null;
	}

	@Override
	public void addItem() {

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
}
