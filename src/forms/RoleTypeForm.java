package forms;

import javax.management.relation.RoleStatus;

import SPADEPAC.RoleClass;
import SPADEPAC.RoleSuperClass;
import abstractform.TableBasicForm;
import interfaces.ISegmentTableForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
import services.Alerts;
import services.ClassSwitcher;
import services.Control;
import tables.CriterionTable;
import tables.RoleTable;
import tables.RoleTypeTable;

public class RoleTypeForm extends TableBasicForm implements ISegmentTableForm {

	private TableView<RoleTypeTable> tableTV;
	private Control control;

	private ComboBox<RoleClass> roleClassTypeCB;
	private ComboBox<RoleSuperClass> roleSuperClassTypeCB;

	private Label roleClassTypeLB;
	private Label roleSuperClassTypeLB;

	private int classIndex;
	private int superIndex;
	private ClassSwitcher classSwitcher;
	
	public RoleTypeForm(Control control) {
		super(control);

		this.control = control;
		classSwitcher = new ClassSwitcher(control);
		
		createForm();
		getSubmitButton().setVisible(false);
		// getSubmitButton().setOnAction(event -> setActionSubmitButton());

	}

	@Override
	public void createForm() {
		getMainPanel().setCenter(getTable());
		getMainPanel().setBottom(createControlPane());

	}

	@Override
	public Node getTable() {

		tableTV = new TableView<RoleTypeTable>();

		TableColumn<RoleTypeTable, String> nameColumn = new TableColumn<RoleTypeTable, String>("Name");
		TableColumn<RoleTypeTable, String> classColumn = new TableColumn<RoleTypeTable, String>("Class");
		TableColumn<RoleTypeTable, String> superColumn = new TableColumn<RoleTypeTable, String>("SuperClass");

		nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
		nameColumn.setMinWidth(150);
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		classColumn.setCellValueFactory(new PropertyValueFactory("classType"));
		classColumn.setMinWidth(150);
		classColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		superColumn.setCellValueFactory(new PropertyValueFactory("superClassType"));
		superColumn.setMinWidth(150);
		superColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		tableTV.getColumns().addAll(nameColumn, classColumn, superColumn);

		tableTV.setEditable(false);

		tableTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		tableTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableTV.setOnKeyReleased(event -> deleteSelected(event));

		BorderPane.setMargin(tableTV, new Insets(5));

		return tableTV;
	}

	@Override
	public void deleteSelected(KeyEvent event) {
		ObservableList<RoleTypeTable> selection = FXCollections
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

		roleClassTypeLB = new Label("Class: ");
		roleClassTypeCB = new ComboBox<RoleClass>(FXCollections.observableArrayList(RoleClass.values()));
		roleClassTypeCB.getSelectionModel().selectedIndexProperty().addListener(classListener);

		roleSuperClassTypeLB = new Label("SuperClass: ");
		roleSuperClassTypeCB = new ComboBox<RoleSuperClass>(FXCollections.observableArrayList(RoleSuperClass.values()));
		roleSuperClassTypeCB.getSelectionModel().selectedIndexProperty().addListener(superListener);

		getControlPane().add(roleClassTypeLB, 0, 1);
		getControlPane().add(roleClassTypeCB, 1, 1);
		getControlPane().add(roleSuperClassTypeLB, 2, 1);
		getControlPane().add(roleSuperClassTypeCB, 3, 1);
		getControlPane().add(getAddBT(), 5, 0);

		getAddBT().setOnAction(event -> addItem());

		return getControlPane();
	}

	ChangeListener<Number> classListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			classIndex = newValue.intValue();
			System.out.println(classIndex);
			superIndex = classSwitcher.roleClassToSupperClass(classIndex);
			roleSuperClassTypeCB.setValue(RoleSuperClass.values()[superIndex]);
		}
	};

	ChangeListener<Number> superListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

	//		superIndex = newValue.intValue();
//			int index = classSwitcher.roleSuperClassToClass(superIndex); 
//			roleClassTypeCB.setValue(RoleClass.values()[index]);

		}
	};

	@Override
	public void addItem() {
		String nameST = getNameTF().getText();
		String classST = roleClassTypeCB.getValue().name();
		String superST = roleSuperClassTypeCB.getValue().name();

		if (nameST.length() == 0) {

			Alerts.showNoNameAlert();
			return;
		}

		RoleTypeTable type = new RoleTypeTable(nameST, classST, superST);

		tableTV.getItems().add(type);
		tableTV.sort();
		getControl().getFillForms().fillRoleType(nameST, classST, superST);
		System.out.println(control.getLists().getRoleTypeObservable());

	}

	@Override
	public void setActionSubmitButton() {
		close();

	}

}
