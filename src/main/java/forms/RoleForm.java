package forms;

import Controllers.FormController;
import Controllers.FormDataController;
import abstractform.Table2BasicForm;
import interfaces.ISegmentTableForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
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
import services.*;
import model.IdentificatorCreater;
import tables.BasicTable;
import tables.RoleTable;

import java.util.ArrayList;

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
	private ChoiceBox<BasicTable> roleTypeCB;
	private TableView<RoleTable> tableTV;
	private int roleIndex;
	private ClassSwitcher classSwitcher;
	private RoleTypeForm roleTForm;
	private Label formName;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy Nastaví reakci
	 * potvrzovacímu tlačítku
	 *
	 */
	public RoleForm(FormController formController, FormDataController formDataController, String name) {
		super(formController, formDataController, name);
		this.roleIndex = 0;
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

		roleTForm = (RoleTypeForm) formController.getForms().get(Constans.roleTypeIndex);
		getMainPanel().setCenter(getInternalPanel());
		getMainPanel().setRight(roleTForm.getMainPanel());

	}

	@Override
	public Node getTable() {
		tableTV = new TableView<RoleTable>();

		TableColumn<RoleTable, String> nameColumn = new TableColumn<RoleTable, String>("Name");
		TableColumn<RoleTable, String> descColumn = new TableColumn<RoleTable, String>("Description");
		TableColumn<RoleTable, String> typeColumn = new TableColumn<RoleTable, String>("Type");

		nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
		nameColumn.setMinWidth(150);
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		descColumn.setCellValueFactory(new PropertyValueFactory("desc"));
		descColumn.setMinWidth(150);
		descColumn.setCellFactory(TextFieldTableCell.forTableColumn());


		typeColumn.setCellValueFactory(new PropertyValueFactory("type"));
		typeColumn.setMinWidth(150);
		typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		tableTV.getColumns().addAll(nameColumn,descColumn, typeColumn);

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

		if (event.getCode() == KeyCode.DELETE) {
			if (selection.size() == 0) {
				Alerts.showNoItemsDeleteAlert();
			}
			else{
				ArrayList<BasicTable> list = new ArrayList<>(selection);
				formDataController.deleteRoleObservable(list, getTableTV());
			}
		}

	}

	@Override
	public GridPane createControlPane() {

		roleTypeLB = new Label("Type: ");
		roleTypeCB = new ChoiceBox<>(formController.getRoleTypeObservable());
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
		BasicTable typeBT = roleTypeCB.getValue();
		String descritpST = descriptionTF.getText();
		int id = formController.createTableItem(SegmentType.Role);
		String idName = id + "_" + nameST;
		String typeST = "";
		if(typeBT != null){
			typeST = typeBT.getName();
		}

		RoleTable role = new RoleTable(idName, descritpST, typeST, id);
		tableTV.getItems().add(role);
		tableTV.sort();
		formDataController.saveDataFromRoleForm(nameST, roleIndex, role);

	}

	/*** Getrs and Setrs ***/

	public ChoiceBox<BasicTable> getRoleTypeCB() {
		return roleTypeCB;
	}

	public void setRoleTypeCB(ChoiceBox<BasicTable> roleTypeCB) {
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
