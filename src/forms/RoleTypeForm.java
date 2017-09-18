package forms;

import java.util.List;

import javax.management.relation.RoleStatus;

import SPADEPAC.RoleClass;
import SPADEPAC.RoleSuperClass;
import SPADEPAC.WorkUnitPrioritySuperClass;
import SPADEPAC.WorkUnitTypeClass;
import SPADEPAC.WorkUnitTypeSuperClass;
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
import services.DeleteControl;
import services.IdentificatorCreater;
import tables.ClassTable;
import tables.CriterionTable;
import tables.RoleTable;

/**
 * Třída představující tabulkový formulář pro výčtový typ Role-Type, odděděná od
 * třídy TableClassBasicForm a implementující ISegmentTableForm
 * 
 * @author Václav Janoch
 *
 */
public class RoleTypeForm extends TableClassBasicForm implements ISegmentTableForm {
	/**
	 * Globální proměnné třídy
	 */
	private Control control;

	private ComboBox<RoleClass> roleClassTypeCB;
	private ComboBox<RoleSuperClass> roleSuperClassTypeCB;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy Nastaví reakci na
	 * potvrzovací tlačítko
	 * 
	 * @param control
	 *            Control
	 * @param deleteControl
	 *            DeleteControl
	 * @param idCreator
	 *            IdentificatorCreater
	 */
	public RoleTypeForm(Control control, DeleteControl deleteControl, IdentificatorCreater idCreator) {
		super(control, deleteControl, idCreator);

		this.control = control;

		createForm();
		getSubmitButton().setVisible(false);

	}

	@Override
	public void createForm() {

		getFormName().setText("Role Classification Type Form");

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

		ObservableList<ClassTable> list = null;

		if (event.getCode() == KeyCode.DELETE) {
			if (selection.size() == 0) {
				Alerts.showNoItemsDeleteAlert();
			} else {
				list = Alerts.showDeleteItemAlert(getTableTV(), selection);
				if (list != null) {
					deleteControl.deleteRoleType(list);
				}

			}
		}

	}

	@Override
	public GridPane createControlPane() {

		roleClassTypeCB = new ComboBox<RoleClass>(FXCollections.observableArrayList(RoleClass.values()));
		roleClassTypeCB.getSelectionModel().selectedIndexProperty().addListener(classListener);

		roleSuperClassTypeCB = new ComboBox<RoleSuperClass>(FXCollections.observableArrayList(RoleSuperClass.values()));
		roleSuperClassTypeCB.getSelectionModel().selectedIndexProperty().addListener(superListener);

		roleClassTypeCB.setValue(RoleClass.UNASSIGNED);
		roleSuperClassTypeCB.setValue(RoleSuperClass.UNASSIGNED);

		getControlPane().add(classLB, 0, 1);
		getControlPane().add(roleClassTypeCB, 1, 1);
		getControlPane().add(superLB, 4, 1);
		getControlPane().add(roleSuperClassTypeCB, 3, 1);
		getControlPane().add(getAddBT(), 5, 0);

		getAddBT().setOnAction(event -> addItem());

		return getControlPane();
	}

	/**
	 * ChangeListener pro určení indexu prvku z comboBoxu pro Class. Zavolá
	 * metody pro mapování Class na Super Class
	 */
	ChangeListener<Number> classListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			setClassIndex(newValue.intValue());
			setSuperIndex(getSwitcher().roleClassToSupperClass(getClassIndex()));
			if (getSuperIndex() == -1) {
				roleSuperClassTypeCB.setDisable(false);
				roleSuperClassTypeCB.setValue(RoleSuperClass.values()[0]);
				superIndex = 0;
			} else {
				roleSuperClassTypeCB.setDisable(true);
				roleSuperClassTypeCB.setValue(RoleSuperClass.values()[getSuperIndex()]);
			}
		}
	};
	/**
	 * ChangeListener pro určení indexu prvku z comboBoxu pro Super Class
	 */
	ChangeListener<Number> superListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			setSuperIndex(newValue.intValue());
		}
	};

	@Override
	public void addItem() {
		String nameST = getNameTF().getText();
		String classST;
		String idName = idCreator.createRoleTypeID() + "_" + nameST;

		if (roleClassTypeCB.getValue() == null || getClassIndex() == 0) {
			classST = RoleClass.UNASSIGNED.name();
		} else {
			classST = roleClassTypeCB.getValue().name();
		}
		String superST = RoleSuperClass.values()[superIndex].name();

		ClassTable type = new ClassTable(idName, classST, superST);

		getTableTV().getItems().add(type);
		getTableTV().sort();
		getControl().getFillForms().fillRoleType(idName, formControl.fillTextMapper(nameST),
				formControl.fillTextMapper(classST), superST, Control.objF, false);

	}

	@Override
	public void setActionSubmitButton() {
		close();

	}

}
