package forms;

import Controllers.FormController;
import SPADEPAC.WorkUnitSeverityClass;
import SPADEPAC.WorkUnitSeveritySuperClass;
import abstractform.TableClassBasicForm;
import interfaces.ISegmentTableForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import services.Alerts;
import services.Control;
import services.DeleteControl;
import model.IdentificatorCreater;
import tables.ClassTable;

/**
 * Třída představující tabulkový formulář pro výčtový typ Severity, odděděná od
 * třídy TableClassBasicForm a implementující ISegmentTableForm
 * 
 * @author Václav Janoch
 *
 */
public class SeverityForm extends TableClassBasicForm implements ISegmentTableForm {

	/**
	 * Globální proměnné třídy
	 */
	private Control control;
	private ComboBox<WorkUnitSeverityClass> classTypeCB;
	private ComboBox<WorkUnitSeveritySuperClass> superClassTypeCB;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy Nastaví reakci na
	 * potvrzovací tlačítko
	 *
	 */
	public SeverityForm(FormController formController, String name) {
		super(formController, name);

		this.control = control;
		this.setTitle("Edit Severities");
		createForm();
		getSubmitButton().setOnAction(event -> setActionSubmitButton());

	}

	@Override
	public void createForm() {
		getFormName().setText("Severity Form");

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
					deleteControl.deleteSeverity(list);
				}

			}
		}

	}

	@Override
	public GridPane createControlPane() {

		classTypeCB = new ComboBox<WorkUnitSeverityClass>(
				FXCollections.observableArrayList(WorkUnitSeverityClass.values()));
		classTypeCB.getSelectionModel().selectedIndexProperty().addListener(classListener);

		superClassTypeCB = new ComboBox<WorkUnitSeveritySuperClass>(
				FXCollections.observableArrayList(WorkUnitSeveritySuperClass.values()));
		superClassTypeCB.getSelectionModel().selectedIndexProperty().addListener(superListener);

		classTypeCB.setValue(WorkUnitSeverityClass.UNASSIGNED);
		superClassTypeCB.setValue(WorkUnitSeveritySuperClass.UNASSIGNED);
		getControlPane().add(classLB, 2, 0);
		getControlPane().add(classTypeCB, 3, 0);
		getControlPane().add(superLB, 4, 0);
		getControlPane().add(superClassTypeCB, 5, 0);
		getControlPane().add(getAddBT(), 6, 0);

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

			classIndex = newValue.intValue();

			superIndex = getSwitcher().priorityClassToSupperClass(classIndex);
			if (getSuperIndex() == -1) {
				superClassTypeCB.setDisable(false);
				superClassTypeCB.setValue(WorkUnitSeveritySuperClass.values()[0]);
				superIndex = 0;
			} else {
				superClassTypeCB.setDisable(true);
				superClassTypeCB.setValue(WorkUnitSeveritySuperClass.values()[getSuperIndex()]);
			}

		}
	};
	/**
	 * ChangeListener pro určení indexu prvku z comboBoxu pro Super Class
	 */
	ChangeListener<Number> superListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			superIndex = newValue.intValue();

		}
	};

	@Override
	public void addItem() {
		String nameST = getNameTF().getText();
		String classST;
		String idName = idCreator.createSeverityID() + "_" + nameST;

		if (classTypeCB.getValue() == null || classIndex == 0) {
			classST = WorkUnitSeverityClass.UNASSIGNED.name();
		} else {
			classST = classTypeCB.getValue().name();
		}
		String superST = WorkUnitSeveritySuperClass.values()[superIndex].name();

		ClassTable table = new ClassTable(idName, classST, superST);

		getTableTV().getItems().add(table);
		getTableTV().sort();
		//getControl().getFillForms().fillSeverityType(idName, formControl.fillTextMapper(nameST),
		//		formControl.fillTextMapper(classST), superST, false);

	}

	@Override
	public void setActionSubmitButton() {
		close();

	}

}
