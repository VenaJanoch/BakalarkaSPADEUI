package forms;

import Controllers.FormController;
import abstractform.TableBasicForm;
import interfaces.ISegmentTableForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import services.Alerts;
import services.Control;
import services.DeleteControl;
import model.IdentificatorCreater;
import services.SegmentType;
import tables.BranchTable;
import tables.CriterionTable;

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

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné tříd
	 *
	 *
	 */

	 public CriterionForm(FormController formController, String name) {
		super(formController, name);

		getSubmitButton().setVisible(false);
		createForm();

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

		ObservableList<CriterionTable> list = null;

		if (event.getCode() == KeyCode.DELETE) {
			if (selection.size() == 0) {
				Alerts.showNoItemsDeleteAlert();
			} else {
				list = Alerts.showDeleteItemCriterionAlert(getTableTV(), selection);
				if (list != null) {
					formController.deleteCriterion(list);
				}

			}
		}
	}

	@Override
	public void setActionSubmitButton() {

	}

	@Override
	public GridPane createControlPane() {

		descriptionLB = new Label("Description: ");
		descriptionTF = new TextField();

		add.setOnAction(event -> addItem());

		controlPane.add(descriptionLB, 2, 0);
		controlPane.add(descriptionTF, 3, 0);
		controlPane.add(add, 4, 0);

		controlPane.setHgap(3);
		controlPane.setVgap(3);

		controlPane.setAlignment(Pos.CENTER);
		controlPane.setPadding(new Insets(5));

		return controlPane;
	}

	@Override
	public void addItem() {
		String nameST = nameTF.getText();
		String descriptionST = descriptionTF.getText();

		int id = formController.createTableItem(SegmentType.Criterion);
		String idName = id + "_" + nameST;

		CriterionTable criterion = new CriterionTable(idName, descriptionST, id);
		tableTV.getItems().add(criterion);
		tableTV.sort();
		formController.saveDataFromCriterionForm(nameST, descriptionST, id);
	}

	public TableView<CriterionTable> getTableTV() {
		return tableTV;
	}

	public void setTableTV(TableView<CriterionTable> tableTV) {
		this.tableTV = tableTV;
	}

}
