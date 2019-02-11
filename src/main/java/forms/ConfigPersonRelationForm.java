package forms;

import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.ConfigPersonRelation;
import SPADEPAC.ObjectFactory;
import abstractform.BasicForm;
import abstractform.TableBasicForm;
import controlPanels.ClassControlPanel;
import controlPanels.ConfigPersonRelationControlPanel;
import controlPanels.MilestoneControlPanel;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import interfaces.ISegmentTableForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
import services.Alerts;
import services.Control;
import services.DeleteControl;
import model.IdentificatorCreater;
import services.SegmentType;
import tables.BasicTable;
import tables.CPRTable;
import tables.MilestoneTable;

import java.text.Normalizer;
import java.util.ArrayList;

/**
 * Třída představující tabulkový formulář pro element Configuration-Person-Relatio, odděděná od třídy
 * TableBasicForm a implementující ISegmentTableForm
 * 
 * @author Václav Janoch
 *
 */
public class ConfigPersonRelationForm extends TableBasicForm implements ISegmentTableForm {

	/**
	 * Globální proměnné třídy
	 */

	private TableView<CPRTable> tableTV;
	private ConfigPersonRelationControlPanel cprControlPanel;
	private ConfigPersonRelationControlPanel editCPRControlPanel;
	/**
	 * Konstruktor třídy
	 * Zinicializuje globální proměnné tříd
	 * Nastaví vlastnost pro tlačítko OK
	 *
	 */
	
	public ConfigPersonRelationForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {
		super(formController, formDataController, editFormController, deleteFormController, type);

		cprControlPanel = new ConfigPersonRelationControlPanel("Add", formDataController, editFormController, formController);
		editCPRControlPanel = new ConfigPersonRelationControlPanel("Edit", formDataController, editFormController, formController);
		editCPRControlPanel.createControlPanel();

		setEventHandler();
		createForm();
		getSubmitButton().setOnAction(event -> setActionSubmitButton());

	}

	@Override
		protected void setEventHandler(){
			OnMousePressedEventHandler = new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent t) {
					if(t.getClickCount() == 2) {
						CPRTable cprTable = tableTV.getSelectionModel().getSelectedItems().get(0);
						if (cprTable != null) {
							editCPRControlPanel.showEditControlPanel(cprTable, tableTV);
						}
					}
				}
			};
		}

	@Override
	public void setActionSubmitButton() {
		close();
	}

	@Override
	public void createForm() {

		getFormName().setText("Configuration Person Relation Form");

		getMainPanel().setCenter(getTable());
		getMainPanel().setBottom(createControlPane());
	}

	@Override
	public Node getTable() {
		tableTV = new TableView<CPRTable>();
		tableTV.setId("cprTable");
		TableColumn<CPRTable, String> nameColumn = new TableColumn<CPRTable, String>("Name");
		TableColumn<CPRTable, String> roleColumn = new TableColumn<CPRTable, String>("Role");

		nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
		nameColumn.setMinWidth(150);
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		roleColumn.setCellValueFactory(new PropertyValueFactory("role"));
		roleColumn.setMinWidth(150);
		roleColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		tableTV.getColumns().addAll(nameColumn, roleColumn);
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
		ObservableList<CPRTable> selection = FXCollections
				.observableArrayList(tableTV.getSelectionModel().getSelectedItems());
		if (event.getCode() == KeyCode.DELETE) {
			if (selection.size() == 0) {
				Alerts.showNoItemsDeleteAlert();
			}
			else{
				ArrayList<BasicTable> list = new ArrayList<>(selection);
				deleteFormController.deleteCPR(list, getTableTV());
			}
		}

	}

	@Override
	public GridPane createControlPane() {

		GridPane controlPane = cprControlPanel.createControlPanel();

		add = cprControlPanel.getButton();
		add.setOnAction(event -> addItem());

		return controlPane;
	}



	@Override
	public void addItem() {

		String nameST = cprControlPanel.getName();
		int id = formController.createTableItem(SegmentType.ConfigPersonRelation);
		int roleIndex = cprControlPanel.getRoleIndex();
		CPRTable cpr = formDataController.prepareCPRToTable(nameST, roleIndex, id);

		tableTV.getItems().add(cpr);
		tableTV.sort();
		formDataController.saveDataFromCPR(nameST, roleIndex, cpr);
		cprControlPanel.clearPanel(tableTV);
	}

	/** Getrs and Setrs ***/
	public TableView<CPRTable> getTableTV() {
		return tableTV;
	}

}
