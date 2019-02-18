package forms;

import controllers.FormController;
import abstractform.TableBasicForm;
import controlPanels.CriterionControlPanel;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import interfaces.ISegmentTableForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import services.Alerts;
import services.SegmentType;
import tables.*;

import java.util.ArrayList;

/**
 * Třída představující tabulkový formulář pro element Criterion, odděděná od
 * třídy TableBasicForm a implementující ISegmentTableForm
 * 
 * @author Václav Janoch
 *
 */
public class CriterionForm extends TableBasicForm implements ISegmentTableForm {
	/**
	 * Globální proměnné třídy
	 */
	private Label descriptionLB;
	private TextField descriptionTF;
	private TableView<CriterionTable> tableTV;
	private CriterionControlPanel criterionControlPanel;
	private CriterionControlPanel editCriterionControlPanel;
	/**
	 * Konstruktor třídy Zinicializuje globální proměnné tříd
	 *
	 *
	 */

	 public CriterionForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController,
						  SegmentType type) {
		super(formController, formDataController, editFormController, deleteFormController, type);
		criterionControlPanel = new CriterionControlPanel("Add", formDataController, editFormController);
		editCriterionControlPanel = new CriterionControlPanel("Edit", formDataController, editFormController);
		editCriterionControlPanel.createControlPanel();
		getSubmitButton().setVisible(false);
		 setEventHandler();
		createForm();
	}

	@Override
	protected void setEventHandler() {
		OnMousePressedEventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				if(t.getClickCount() == 2) {
					CriterionTable criterionTable = tableTV.getSelectionModel().getSelectedItems().get(0);
					if (criterionTable != null) {
						editCriterionControlPanel.showEditControlPanel(criterionTable, tableTV);
					}
				}
			}
		};
	}

	@Override
	public void createForm() {

		getFormName().setText("Criterion Form");

		getMainPanel().setCenter(getTable());
		getMainPanel().setBottom(createControlPane());

	}

	@Override
	public Node getTable() {
		tableTV = new TableView<CriterionTable>();

		TableColumn<CriterionTable, String> nameColumn = new TableColumn<CriterionTable, String>("Name");
		TableColumn<CriterionTable, String> descriptionColumn = new TableColumn<CriterionTable, String>("Description");

		nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
		nameColumn.setMinWidth(150);
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		descriptionColumn.setCellValueFactory(new PropertyValueFactory("description"));
		descriptionColumn.setMinWidth(150);
		descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		tableTV.getColumns().addAll(nameColumn, descriptionColumn);
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
		ObservableList<CriterionTable> selection = FXCollections
				.observableArrayList(tableTV.getSelectionModel().getSelectedItems());

		if (event.getCode() == KeyCode.DELETE) {
			if (selection.size() == 0) {
				Alerts.showNoItemsDeleteAlert();
			}
			else{
				ArrayList<BasicTable> list = new ArrayList<>(selection);
				deleteFormController.deleteCriterionWithDialog(list, getTableTV());
			}
		}
	}

	@Override
	public void setActionSubmitButton() {

	}

	/**
	 * EventHandler pro určení prvku z tabulky a zobrazní odpovídajícího
	 * formuláře
	 */
	EventHandler<MouseEvent> OnMousePressedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			if(t.getClickCount() == 2) {
				CriterionTable criterionTable = tableTV.getSelectionModel().getSelectedItems().get(0);
				if (criterionTable != null) {
					editCriterionControlPanel.showEditControlPanel(criterionTable, tableTV);
				}
			}
		}
	};


	@Override
	public GridPane createControlPane() {

		GridPane controlPanel = criterionControlPanel.createControlPanel();

		add = criterionControlPanel.getButton();
		add.setOnAction(event -> addItem());

		return controlPanel;
	}

	@Override
	public void addItem() {
		String nameST = criterionControlPanel.getName();
		String descriptionST = criterionControlPanel.getDescriptionText();

		int id = formController.createTableItem(SegmentType.Criterion);
		String idName = id + "_" + nameST;

		CriterionTable criterion = new CriterionTable(idName, descriptionST, id);
		tableTV.getItems().add(criterion);
		tableTV.sort();
		formDataController.saveDataFromCriterionForm(nameST, criterion);
		criterionControlPanel.clearPanel(tableTV);
	}

	public TableView<CriterionTable> getTableTV() {
		return tableTV;
	}

	public void setTableTV(TableView<CriterionTable> tableTV) {
		this.tableTV = tableTV;
	}

}
