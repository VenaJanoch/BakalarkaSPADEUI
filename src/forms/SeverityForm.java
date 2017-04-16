package forms;


import java.util.List;

import SPADEPAC.WorkUnitPriorityClass;
import SPADEPAC.WorkUnitPrioritySuperClass;
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
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import services.Alerts;
import services.Control;
import services.DeleteControl;
import tables.ClassTable;

public class SeverityForm extends TableClassBasicForm implements ISegmentTableForm {

	private Control control;

	private ComboBox<WorkUnitSeverityClass> classTypeCB;
	private ComboBox<WorkUnitSeveritySuperClass> superClassTypeCB;

	private Label classTypeLB;
	private Label superClassTypeLB;

	
	public SeverityForm(Control control, DeleteControl deleteControl) {
		super(control, deleteControl);

		this.control = control;
		this.setTitle("Edit Severities");
		createForm();
		//getSubmitButton().setVisible(false);
		 getSubmitButton().setOnAction(event -> setActionSubmitButton());

	}

	@Override
	public void createForm() {
		getFormName().setText("Severity form");

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

		classTypeLB = new Label("Class: ");
		classTypeCB = new ComboBox<WorkUnitSeverityClass>(
				FXCollections.observableArrayList(WorkUnitSeverityClass.values()));
		classTypeCB.getSelectionModel().selectedIndexProperty().addListener(classListener);

		superClassTypeLB = new Label("SuperClass: ");
		superClassTypeCB = new ComboBox<WorkUnitSeveritySuperClass>(
				FXCollections.observableArrayList(WorkUnitSeveritySuperClass.values()));
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
			
			superIndex = getSwitcher().priorityClassToSupperClass(classIndex);
			superClassTypeCB.setValue(WorkUnitSeveritySuperClass.values()[superIndex]);
		}
	};

	ChangeListener<Number> superListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			// superIndex = newValue.intValue();
			// int index = classSwitcher.roleSuperClassToClass(superIndex);
			// roleClassTypeCB.setValue(RoleClass.values()[index]);

		}
	};

	@Override
	public void addItem() {
		String nameST = getNameTF().getText();
		String classST = classTypeCB.getValue().name();
		String superST = WorkUnitPrioritySuperClass.values()[superIndex].name();

		if (nameST.length() == 0) {

			Alerts.showNoNameAlert();
			return;
		}

		ClassTable table = new ClassTable(nameST, classST, superST);

		getTableTV().getItems().add(table);
		getTableTV().sort();
		getControl().getFillForms().fillSeverityType(nameST, classST, superST);

	}

	@Override
	public void setActionSubmitButton() {
		close();

	}

}
