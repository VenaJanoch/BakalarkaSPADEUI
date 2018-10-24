package forms;

import org.controlsfx.control.CheckComboBox;

import abstractform.Table2BasicForm;
import interfaces.ISegmentTableForm;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import services.Alerts;
import services.Constans;
import services.Control;
import services.DeleteControl;
import model.IdentificatorCreater;
import tables.MilestoneTable;

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
	private Label criteriaLB;
	private Label formName;

	private CheckComboBox<String> criteriaCB;
	private TableView<MilestoneTable> tableTV;

	private CriterionForm criterionForm;

	private ObservableList<String> criterionArray;
	private ObservableList<Integer> criterionIndex;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy
	 * 
	 * @param control
	 *            Control
	 * @param deleteControl
	 *            DeleteControl
	 * @param idCreator
	 *            IdentificatorCreater
	 */
	public MilestoneForm(Control control, DeleteControl deleteControl, IdentificatorCreater idCreator) {

		super(control, deleteControl, idCreator);
		this.setTitle("Edit Milestone");
		createForm();
		getSubmitBT().setOnAction(event -> setActionSubmitButton());

	}

	@Override
	public void createForm() {

		formName = new Label("Milestone Form");
		formName.setFont(Font.font(25));

		getInternalPanel().setTop(formName);
		getInternalPanel().setAlignment(formName, Pos.CENTER);

		getInternalPanel().setCenter(getTable());
		getInternalPanel().setBottom(createControlPane());

		criterionForm = new CriterionForm(getControl(), deleteControl, idCreator);

		getMainPanel().setCenter(getInternalPanel());
		getMainPanel().setRight(criterionForm.getMainPanel());

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

		nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
		nameColumn.setMinWidth(100);
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		criterionColumn.setCellValueFactory(new PropertyValueFactory("criterion"));
		criterionColumn.setMinWidth(100);
		criterionColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		tableTV.getColumns().addAll(nameColumn, criterionColumn);

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

		ObservableList<MilestoneTable> list = null;

		if (event.getCode() == KeyCode.DELETE) {
			if (selection.size() == 0) {
				Alerts.showNoItemsDeleteAlert();
			} else {
				list = Alerts.showDeleteItemMilestoneAlert(getTableTV(), selection);
				if (list != null) {
					deleteControl.deleteMilestone(list);
				}

			}
		}

	}

	@Override
	public GridPane createControlPane() {

		criteriaLB = new Label("Criteria: ");
		criteriaCB = new CheckComboBox<String>(getControl().getLists().getCriterionObservable());
		criteriaCB.setMaxWidth(Constans.checkComboBox);
		criteriaCB.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {

			public void onChanged(ListChangeListener.Change<? extends String> c) {
				criterionIndex = criteriaCB.getCheckModel().getCheckedIndices();
				criterionArray = criteriaCB.getCheckModel().getCheckedItems();

			}
		});

		getControlPane().add(criteriaLB, 2, 0);
		getControlPane().add(criteriaCB, 3, 0);
		getControlPane().add(getAddBT(), 4, 0);

		getAddBT().setOnAction(event -> addItem());

		return getControlPane();
	}

	@Override
	public void addItem() {

		String nameST = getNameTF().getText();
		String idName = idCreator.createMilestoneID() + "_" + nameST;
		String criterion = "";
		if (criterionArray != null) {
			criterion = criterionArray.toString();
		}

		MilestoneTable milestone = new MilestoneTable(idName, criterion);
		tableTV.getItems().add(milestone);
		tableTV.sort();
		getControl().getFillForms().fillMilestone(idName, formControl.fillTextMapper(nameST), criterionIndex, Control.objF, false);
		criteriaCB.getCheckModel().clearChecks();

	}

	public TableView<MilestoneTable> getTableTV() {
		return tableTV;
	}

	public void setTableTV(TableView<MilestoneTable> tableTV) {
		this.tableTV = tableTV;
	}

	public CriterionForm getCriterionForm() {
		return criterionForm;
	}

	public void setCriterionForm(CriterionForm criterionForm) {
		this.criterionForm = criterionForm;
	}

}
