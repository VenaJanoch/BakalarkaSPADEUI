package forms;

import SPADEPAC.WorkUnitPriorityClass;
import SPADEPAC.WorkUnitPrioritySuperClass;
import SPADEPAC.WorkUnitTypeClass;
import SPADEPAC.WorkUnitTypeSuperClass;
import abstractform.TableClassBasicForm;
import interfaces.ISegmentTableForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import services.Alerts;
import services.Control;
import services.DeleteControl;
import tables.ClassTable;
import java.util.List;

public class PriorityForm extends TableClassBasicForm implements ISegmentTableForm {

	private Control control;

	private ChoiceBox<WorkUnitPriorityClass> classTypeCB;
	private ChoiceBox<WorkUnitPrioritySuperClass> superClassTypeCB;

	private Label classTypeLB;
	private Label superClassTypeLB;

	public PriorityForm(Control control, DeleteControl deleteControl) {
		super(control, deleteControl);

		this.control = control;
		this.setTitle("Edit Priority");
		createForm();
		// getSubmitButton().setVisible();
		getSubmitButton().setOnAction(event -> setActionSubmitButton());

	}

	@Override
	public void createForm() {
		getFormName().setText("Priority Form");
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
					deleteControl.deletePriority(list);
				}

			}
		}

	}

	@Override
	public GridPane createControlPane() {

		classTypeLB = new Label("Class: ");
		classTypeCB = new ChoiceBox<WorkUnitPriorityClass>(
				FXCollections.observableArrayList(WorkUnitPriorityClass.values()));
		classTypeCB.getSelectionModel().selectedIndexProperty().addListener(classListener);

		superClassTypeLB = new Label("SuperClass: ");
		superClassTypeCB = new ChoiceBox<WorkUnitPrioritySuperClass>(
				FXCollections.observableArrayList(WorkUnitPrioritySuperClass.values()));
		superClassTypeCB.getSelectionModel().selectedIndexProperty().addListener(superListener);
		
		classTypeCB.setValue(WorkUnitPriorityClass.UNASSIGNED);
		superClassTypeCB.setValue(WorkUnitPrioritySuperClass.UNASSIGNED);
		
		getControlPane().add(classLB, 2, 0);
		getControlPane().add(classTypeCB, 3, 0);
		getControlPane().add(superLB, 4, 0);
		getControlPane().add(superClassTypeCB, 5, 0);
		getControlPane().add(getAddBT(), 6, 0);

		getAddBT().setOnAction(event -> addItem());

		return getControlPane();
	}

	ChangeListener<Number> classListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			setClassIndex(newValue.intValue());

			setSuperIndex(getSwitcher().priorityClassToSupperClass(getClassIndex()));
			if(getSuperIndex() == -1){
				superClassTypeCB.setDisable(false);
				superClassTypeCB.setValue(WorkUnitPrioritySuperClass.values()[0]);
				superIndex = 0;
			}else{
				superClassTypeCB.setDisable(true);
				superClassTypeCB.setValue(WorkUnitPrioritySuperClass.values()[getSuperIndex()]);
			}
		}
	};

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
		
		if (classTypeCB.getValue() == null || classIndex == 0) {
		classST = WorkUnitPriorityClass.UNASSIGNED.name();	
		}else{
			classST = classTypeCB.getValue().name();			
		}
		String superST = WorkUnitPrioritySuperClass.values()[getSuperIndex()].name();

		ClassTable table = new ClassTable(nameST, classST, superST);

		getTableTV().getItems().add(table);
		getTableTV().sort();
		getControl().getFillForms().fillPriorityType(formControl.fillTextMapper(nameST), formControl.fillTextMapper(classST), superST);

	}

	@Override
	public void setActionSubmitButton() {
		close();

	}

}
