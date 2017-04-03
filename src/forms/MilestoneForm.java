package forms;

import org.controlsfx.control.CheckComboBox;

import SPADEPAC.Configuration;
import SPADEPAC.Milestone;
import abstractform.BasicForm;
import abstractform.Table2BasicForm;
import abstractform.TableBasicForm;
import graphics.CanvasItem;
import graphics.InfoBoxSegment;
import interfaces.ISegmentForm;
import interfaces.ISegmentTableForm;
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
import services.Alerts;
import services.Constans;
import services.Control;
import services.FillForms;
import services.OrderCell;
import tables.MilestoneTable;
import tables.TagTable;

public class MilestoneForm extends Table2BasicForm implements ISegmentTableForm {

	private Label criteriaLB;
	
	private CheckComboBox<String> criteriaCB;		
	private TableView<MilestoneTable> tableTV;
	

	private CriterionForm criterionForm;

	private ObservableList<String> criterionArray;
	private ObservableList<Integer> criterionIndex;
	
	public MilestoneForm(Control control) {

		super(control);

		createForm();
		getSubmitBT().setOnAction(event -> setActionSubmitButton());

	}

	@Override
	public void createForm() {
		
		
		getInternalPanel().setCenter(getTable());
		getInternalPanel().setBottom(createControlPane());

		criterionForm = new CriterionForm(getControl());

		
		getMainPanel().setRight(criterionForm);
		

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

	@Override
	public void deleteSelected(KeyEvent event) {
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

	@Override
	public GridPane createControlPane() {
		
		criteriaLB = new Label("Criteria: ");
		criteriaCB = new CheckComboBox<String>(getControl().getLists().getCriterionObservable());
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

		if (nameST.length() == 0) {

			Alerts.showNoNameAlert();
			return;
		}

		MilestoneTable milestone = new MilestoneTable(nameST, criterionArray.toString());
		tableTV.getItems().add(milestone);
		tableTV.sort();
		getControl().getFillForms().fillMilestone(nameST, criterionIndex);

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
