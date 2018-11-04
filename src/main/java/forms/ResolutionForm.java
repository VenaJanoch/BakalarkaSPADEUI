package forms;

import Controllers.FormController;
import SPADEPAC.WorkUnitResolutionClass;
import SPADEPAC.WorkUnitResolutionsSuperClass;
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
 * Třída představující tabulkový formulář pro výčtový typ Resolution, odděděná od třídy
 * TableClassBasicForm a implementující ISegmentTableForm
 * 
 * @author Václav Janoch
 *
 */
public class ResolutionForm extends TableClassBasicForm implements ISegmentTableForm {
	/**
	 * Globální proměnné třídy
	 */
	private Control control;

	private ComboBox<WorkUnitResolutionClass> classTypeCB;
	private ComboBox<WorkUnitResolutionsSuperClass> superClassTypeCB;
	/**
	 * Konstruktor třídy
	 * Zinicializuje globální proměnné třídy
	 * Nastaví reakci na potvrzovací tlačítko
	 */
	public ResolutionForm(FormController formController, String name) {
		super(formController, name);

		this.control = control;
		this.setTitle("Edit Resolutions");
		createForm();
		getSubmitButton().setOnAction(event -> setActionSubmitButton());

	}

	@Override
	public void createForm() {
		getFormName().setText("Resolution Form");

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
					deleteControl.deleteResolution(list);
				}

			}
		}

	}

	@Override
	public GridPane createControlPane() {

		classTypeCB = new ComboBox<WorkUnitResolutionClass>(
				FXCollections.observableArrayList(WorkUnitResolutionClass.values()));
		classTypeCB.getSelectionModel().selectedIndexProperty().addListener(classListener);

		superClassTypeCB = new ComboBox<WorkUnitResolutionsSuperClass>(
				FXCollections.observableArrayList(WorkUnitResolutionsSuperClass.values()));
		superClassTypeCB.getSelectionModel().selectedIndexProperty().addListener(superListener);

		classTypeCB.setValue(WorkUnitResolutionClass.UNASSIGNED);
		superClassTypeCB.setValue(WorkUnitResolutionsSuperClass.UNASSIGNED);
		
		getControlPane().add(classLB, 2, 0);
		getControlPane().add(classTypeCB, 3, 0);
		getControlPane().add(superLB, 4, 0);
		getControlPane().add(superClassTypeCB, 5, 0);
		getControlPane().add(getAddBT(), 6, 0);

		getAddBT().setOnAction(event -> addItem());

		return getControlPane();
	}

	/**
	 * ChangeListener pro určení indexu prvku z comboBoxu pro Class. Zavolá metody pro mapování Class na Super Class
	 */
	ChangeListener<Number> classListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			setClassIndex(newValue.intValue());
			setSuperIndex(getSwitcher().resolutionClassToSupperClass(getClassIndex()));
			if (superIndex == -1) {
				superClassTypeCB.setDisable(false);
				superClassTypeCB.setValue(WorkUnitResolutionsSuperClass.values()[0]);
				superIndex = 0;
			} else {
				superClassTypeCB.setDisable(true);
				superClassTypeCB.setValue(WorkUnitResolutionsSuperClass.values()[getSuperIndex()]);
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
		String idName = idCreator.createResolutionID() + "_" + nameST;

		if (classTypeCB.getValue() == null || getClassIndex() == 0) {
			classST = WorkUnitResolutionClass.UNASSIGNED.name();
		} else {
			classST = classTypeCB.getValue().name();
		}
		String superST = WorkUnitResolutionsSuperClass.values()[getSuperIndex()].name();

		ClassTable table = new ClassTable(idName, classST, superST);

		getTableTV().getItems().add(table);
		getTableTV().sort();
		getControl().getFillForms().fillResolutionType(idName, formControl.fillTextMapper(nameST), formControl.fillTextMapper(classST), superST, false);

	}

	@Override
	public void setActionSubmitButton() {
		close();

	}

}
