package forms;

import java.util.List;

import SPADEPAC.WorkUnitPriorityClass;
import SPADEPAC.WorkUnitPrioritySuperClass;
import SPADEPAC.WorkUnitStatusSuperClass;
import SPADEPAC.WorkUnitTypeClass;
import SPADEPAC.WorkUnitTypeSuperClass;
import abstractform.TableClassBasicForm;
import interfaces.ISegmentTableForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import services.Alerts;
import services.Constans;
import services.Control;
import services.DeleteControl;
import tables.ClassTable;

public class TypeForm extends TableClassBasicForm implements ISegmentTableForm {

	private Control control;

	private ComboBox<WorkUnitTypeClass> classTypeCB;
	private ComboBox<WorkUnitTypeSuperClass> superClassTypeCB;

	private Label classTypeLB;
	private Label superClassTypeLB;

	public TypeForm(Control control, DeleteControl deleteControl) {
		super(control, deleteControl);

		this.control = control;
		this.setTitle("Edit WorkUnit type");
		createForm();
		// getSubmitButton().setVisible();
		getSubmitButton().setOnAction(event -> setActionSubmitButton());

	}

	@Override
	public void createForm() {
		getFormName().setText("WorkUnit type form");
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
					deleteControl.deleteType(list);
				}

			}
		}

	}

	@Override
	public GridPane createControlPane() {

		classTypeLB = new Label("Class: ");
		classTypeCB = new ComboBox<WorkUnitTypeClass>(FXCollections.observableArrayList(WorkUnitTypeClass.values()));
		classTypeCB.getSelectionModel().selectedIndexProperty().addListener(classListener);
		superClassTypeLB = new Label("SuperClass: ");
		superClassTypeCB = new ComboBox<WorkUnitTypeSuperClass>(
				FXCollections.observableArrayList(WorkUnitTypeSuperClass.values()));
		superClassTypeCB.getSelectionModel().selectedIndexProperty().addListener(superListener);

		getControlPane().add(classTypeLB, 2, 0);
		getControlPane().add(classTypeCB, 3, 0);
		getControlPane().add(superClassTypeLB, 4, 0);
		getControlPane().add(superClassTypeCB, 5, 0);
		getControlPane().add(getAddBT(), 6, 0);

		getAddBT().setOnAction(event -> addItem());

		return getControlPane();
	}

	ChangeListener<Number> classListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			classIndex = newValue.intValue();

			superIndex = getSwitcher().typeClassToSupperClass(classIndex);
			if (superIndex == -1) {
				superClassTypeCB.setDisable(false);
				superClassTypeCB.setValue(WorkUnitTypeSuperClass.values()[0]);
				superIndex = 0;
			} else {
				superClassTypeCB.setDisable(true);
				superClassTypeCB.setValue(WorkUnitTypeSuperClass.values()[getSuperIndex()]);
			}
		}
	};

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
		if (classTypeCB.getValue() == null || getClassIndex() == 0) {
			classST = "";
		} else {
			classST = classTypeCB.getValue().name();
		}
		String superST = WorkUnitTypeSuperClass.values()[superIndex].name();


		ClassTable table = new ClassTable(nameST, classST, superST);

		getTableTV().getItems().add(table);
		getTableTV().sort();
		getControl().getFillForms().fillType(formControl.fillTextMapper(nameST), formControl.fillTextMapper(classST), superST);

	}

	@Override
	public void setActionSubmitButton() {
		close();

	}

}
