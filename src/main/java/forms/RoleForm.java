package forms;

import java.time.LocalDate;

import SPADEPAC.Role;
import SPADEPAC.RoleClass;
import SPADEPAC.WorkUnitPriorityClass;
import SPADEPAC.WorkUnitSeverityClass;
import abstractform.BasicForm;
import abstractform.DescriptionBasicForm;
import abstractform.Table2BasicForm;
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
import javafx.scene.control.ChoiceBox;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import services.Alerts;
import services.ClassSwitcher;
import services.Constans;
import services.Control;
import services.DeleteControl;
import services.IdentificatorCreater;
import tables.MilestoneTable;
import tables.RoleTable;

/**
 * Třída představující dvojitý formulář pro element Role, vytvoří tabulku s
 * přehledem Rolí a tabulkový formulář pro Role-Type, odděděná od třídy
 * Table2BasicForm a implementující ISegmentTableForm
 * 
 * @author Václav Janoch
 *
 */
public class RoleForm extends Table2BasicForm implements ISegmentTableForm {

	/**
	 * Globální proměnné třídy
	 */
	private Label roleTypeLB;
	private Label descriptionLB;

	private TextField descriptionTF;
	private ChoiceBox<String> roleTypeCB;
	private TableView<RoleTable> tableTV;
	private int roleIndex;
	private Control control;
	private ClassSwitcher classSwitcher;
	private RoleTypeForm roleTForm;
	private Label formName;

	private DeleteControl deleteControl;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy Nastaví reakci
	 * potvrzovacímu tlačítku
	 * 
	 * @param control
	 *            Control
	 * @param deleteControl
	 *            DeleteControl
	 * @param idCreator
	 *            IdentificatorCreater
	 */
	public RoleForm(Control control, DeleteControl deleteControl, IdentificatorCreater idCreator) {
		super(control, deleteControl, idCreator);
		this.control = control;
		this.roleIndex = 0;
		this.deleteControl = deleteControl;
		createForm();
		getSubmitBT().setOnAction(event -> setActionSubmitButton());

	}

	@Override
	public void setActionSubmitButton() {
		close();

	}

	@Override
	public void createForm() {

		formName = new Label("Role Form");
		formName.setFont(Font.font(25));

		getInternalPanel().setTop(formName);
		getInternalPanel().setAlignment(formName, Pos.CENTER);

		getInternalPanel().setCenter(getTable());
		getInternalPanel().setBottom(createControlPane());

		roleTForm = new RoleTypeForm(control, deleteControl, idCreator);
		getMainPanel().setCenter(getInternalPanel());
		getMainPanel().setRight(roleTForm.getMainPanel());

	}

	@Override
	public Node getTable() {
		tableTV = new TableView<RoleTable>();

		TableColumn<RoleTable, String> nameColumn = new TableColumn<RoleTable, String>("Name");
		TableColumn<RoleTable, String> typeColumn = new TableColumn<RoleTable, String>("Type");

		nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
		nameColumn.setMinWidth(150);
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		typeColumn.setCellValueFactory(new PropertyValueFactory("type"));
		typeColumn.setMinWidth(150);
		typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		tableTV.getColumns().addAll(nameColumn, typeColumn);

		tableTV.setEditable(false);

		tableTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		tableTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableTV.setOnKeyReleased(event -> deleteSelected(event));

		BorderPane.setMargin(tableTV, new Insets(5));

		return tableTV;
	}

	@Override
	public void deleteSelected(KeyEvent event) {
		ObservableList<RoleTable> selection = FXCollections
				.observableArrayList(tableTV.getSelectionModel().getSelectedItems());

		ObservableList<RoleTable> list = null;

		if (event.getCode() == KeyCode.DELETE) {
			if (selection.size() == 0) {
				Alerts.showNoItemsDeleteAlert();
			} else {
				list = Alerts.showDeleteItemRoleAlert(getTableTV(), selection);
				if (list != null) {
					deleteControl.deleteRole(list);
				}

			}
		}

	}

	@Override
	public GridPane createControlPane() {

		roleTypeLB = new Label("Type: ");
		roleTypeCB = new ChoiceBox<>(getControl().getLists().getRoleTypeObservable());
		roleTypeCB.getSelectionModel().selectedIndexProperty().addListener(roleListener);
		roleTypeCB.setMaxWidth(Constans.checkComboBox);
		descriptionLB = new Label("Description: ");
		descriptionTF = new TextField();

		getControlPane().add(descriptionLB, 2, 0);
		getControlPane().add(descriptionTF, 3, 0);
		getControlPane().add(roleTypeLB, 4, 0);
		getControlPane().add(roleTypeCB, 5, 0);
		getControlPane().add(getAddBT(), 6, 0);

		getAddBT().setOnAction(event -> addItem());

		return getControlPane();
	}

	/**
	 * ChangeListener pro určení indexu prvku z comboBoxu pro Role-Type
	 */
	ChangeListener<Number> roleListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			roleIndex = newValue.intValue();

		}
	};

	@Override
	public void addItem() {
		String nameST = getNameTF().getText();
		String typeST = roleTypeCB.getValue();
		String descritpST = descriptionTF.getText();
		String idName = idCreator.createRoleID() + "_" + nameST;

		RoleTable role = new RoleTable(idName, descritpST, typeST);
		tableTV.getItems().add(role);
		tableTV.sort();
		getControl().getFillForms().fillRole(idName, formControl.fillTextMapper(descritpST),
				formControl.fillTextMapper(nameST), roleIndex, Control.objF, false);

	}

	/*** Getrs and Setrs ***/

	public ChoiceBox<String> getRoleTypeCB() {
		return roleTypeCB;
	}

	public void setRoleTypeCB(ChoiceBox<String> roleTypeCB) {
		this.roleTypeCB = roleTypeCB;
	}

	public TableView<RoleTable> getTableTV() {
		return tableTV;
	}

	public void setTableTV(TableView<RoleTable> tableTV) {
		this.tableTV = tableTV;
	}

	public RoleTypeForm getRoleTForm() {
		return roleTForm;
	}

	public void setRoleTForm(RoleTypeForm roleTForm) {
		this.roleTForm = roleTForm;
	}

}
