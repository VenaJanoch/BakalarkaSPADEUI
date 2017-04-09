package forms;

import javax.management.relation.RoleStatus;

import SPADEPAC.RoleClass;
import SPADEPAC.RoleSuperClass;
import abstractform.TableBasicForm;
import abstractform.TableClassBasicForm;
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
import tables.ClassTable;
import tables.CriterionTable;
import tables.RoleTable;
import tables.RoleTypeTable;

public class RoleTypeForm extends TableClassBasicForm implements ISegmentTableForm {

	private Control control;

	private ComboBox<RoleClass> roleClassTypeCB;
	private ComboBox<RoleSuperClass> roleSuperClassTypeCB;

	private Label roleClassTypeLB;
	private Label roleSuperClassTypeLB;

	
	public RoleTypeForm(Control control) {
		super(control);

		this.control = control;
		
		createForm();
		getSubmitButton().setVisible(false);
		// getSubmitButton().setOnAction(event -> setActionSubmitButton());

	}

	@Override
	public void createForm() {
		
		getNameLB().setText("Role type");
		
		getMainPanel().setCenter(getTable());
		getMainPanel().setBottom(createControlPane());

	}

	@Override
	public Node getTable() {
		getTableTV().setOnKeyReleased(event -> deleteSelected(event));

		return getTableTV();
	}

	@Override
	public void deleteSelected(KeyEvent event) {
		ObservableList<ClassTable> selection = FXCollections
				.observableArrayList(getTableTV().getSelectionModel().getSelectedItems());

		if (event.getCode() == KeyCode.DELETE) {
			if (selection.size() == 0) {
				Alerts.showNoItemsDeleteAlert();
			} else {
				Alerts.showDeleteItemAlert(getTableTV(), selection);
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

			setClassIndex(newValue.intValue());
			setSuperIndex(getSwitcher().roleClassToSupperClass(getClassIndex()));
			roleSuperClassTypeCB.setValue(RoleSuperClass.values()[getSuperIndex()]);
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

		ClassTable type = new ClassTable(nameST, classST, superST);

		getTableTV().getItems().add(type);
		getTableTV().sort();
		getControl().getFillForms().fillRoleType(nameST, classST, superST);
		

	}

	@Override
	public void setActionSubmitButton() {
		close();

	}

}
