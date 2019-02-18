package forms;

import controllers.FormController;
import controlPanels.MilestoneControlPanel;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import abstractform.Table2BasicForm;
import interfaces.ISegmentTableForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import services.*;
import tables.BasicTable;
import tables.MilestoneTable;

import java.util.ArrayList;

/**
 * Třída představující dvojitý formulář pro element Milestone, vytvoří tabulku s
 * přehledem Milestonů a tabulkový formulář pro Criterion, odděděná od třídy
 * Table2BasicForm a implementující ISegmentTableForm
 * 
 * @author Václav Janoch
 *
 */
public class MilestoneForm extends Table2BasicForm implements ISegmentTableForm {

	/**
	 * Globální proměnné třídy
	 */

	private Label formName;

	private TableView<MilestoneTable> tableTV;

	private CriterionForm criterionForm;

	private MilestoneControlPanel milestoneControlPanel;
	private MilestoneControlPanel editMilestoneControlPanel;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy
	 *
	 *
	 */
	public MilestoneForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {

		super(formController, formDataController, editFormController, deleteFormController, type);

		milestoneControlPanel = new MilestoneControlPanel("Add", formDataController, editFormController, formController);
		editMilestoneControlPanel = new MilestoneControlPanel("Edit", formDataController, editFormController, formController);
		editMilestoneControlPanel.createControlPanel();
		setEventHandler();
		createForm();
		getSubmitBT().setOnAction(event -> setActionSubmitButton());
	}

	protected void setEventHandler(){
		OnMousePressedEventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				if(t.getClickCount() == 2) {
					MilestoneTable milestoneTable = tableTV.getSelectionModel().getSelectedItems().get(0);
					if (milestoneTable != null) {
						editMilestoneControlPanel.showEditControlPanel(milestoneTable, tableTV);
					}
				}
			}
		};
	}

	@Override
	public void createForm() {

		formName = new Label("Milestone Form");
		formName.setFont(Font.font(25));

		internalPanel.setTop(formName);
		internalPanel.setAlignment(formName, Pos.CENTER);

		internalPanel.setCenter(getTable());
		internalPanel.setBottom(createControlPane());

		criterionForm = (CriterionForm) formController.getForms().get(Constans.criterionFormIndex);
		setEventHandler();
		mainPanel.setRight(criterionForm.getMainPanel());
	}

	@Override
	public void setActionSubmitButton() {
		close();

	}

	@Override
	public Node getTable() {
		tableTV = new TableView<MilestoneTable>();

		TableColumn<MilestoneTable, String> nameColumn = new TableColumn<MilestoneTable, String>("Name");
		TableColumn<MilestoneTable, String> criterionColumn = new TableColumn<MilestoneTable, String>("Criterion");
		TableColumn<MilestoneTable, String> descriptionColumn = new TableColumn<MilestoneTable, String>("Description");

		nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
		nameColumn.setMinWidth(100);
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		descriptionColumn.setCellValueFactory(new PropertyValueFactory("description"));
		descriptionColumn.setMinWidth(100);
		descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());


		criterionColumn.setCellValueFactory(new PropertyValueFactory("criterion"));
		criterionColumn.setMinWidth(100);
		criterionColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		tableTV.getColumns().addAll(nameColumn, descriptionColumn, criterionColumn);
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
		ObservableList<MilestoneTable> selection = FXCollections
				.observableArrayList(tableTV.getSelectionModel().getSelectedItems());

		if (event.getCode() == KeyCode.DELETE) {
			if (selection.size() == 0) {
				Alerts.showNoItemsDeleteAlert();
			}
			else{
				ArrayList<BasicTable> list = new ArrayList<>(selection);
				deleteFormController.deleteMilestoneWithDialog(list, getTableTV());
			}
		}

	}

	@Override
	public GridPane createControlPane() {

		GridPane controlPane = milestoneControlPanel.createControlPanel();

		add = milestoneControlPanel.getButton();
		add.setOnAction(event -> addItem());

		return controlPane;

	}

	@Override
	public void addItem() {

		String nameST = milestoneControlPanel.getName();
		String descriptionST = milestoneControlPanel.getDescriptionText();

		int id = formController.createTableItem(SegmentType.Milestone);

		ArrayList criterionList = new ArrayList<>(milestoneControlPanel.getCriterionIndex());
		MilestoneTable milestone = formDataController.prepareMilestoneToTable(nameST, descriptionST, id, criterionList);

		tableTV.getItems().add(milestone);
		tableTV.sort();

		formDataController.saveDataFromMilestoneForm(nameST, descriptionST, criterionList, milestone);
		milestoneControlPanel.clearPanelCB(tableTV);
	}

	public TableView<MilestoneTable> getTableTV() {
		return tableTV;
	}

}
