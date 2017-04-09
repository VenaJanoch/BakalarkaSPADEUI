package forms;


import SPADEPAC.WorkUnitPriorityClass;
import SPADEPAC.WorkUnitPrioritySuperClass;
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
import tables.ClassTable;

public class PriorityForm extends TableClassBasicForm implements ISegmentTableForm {

	private Control control;

	private ComboBox<WorkUnitPriorityClass> classTypeCB;
	private ComboBox<WorkUnitPrioritySuperClass> superClassTypeCB;

	private Label classTypeLB;
	private Label superClassTypeLB;


	public PriorityForm(Control control) {
		super(control);

		this.control = control;
		this.setTitle("Edit Priority");
		createForm();
		//getSubmitButton().setVisible();
		 getSubmitButton().setOnAction(event -> setActionSubmitButton());

	}

	@Override
	public void createForm() {
		getFormName().setText("Priority form");
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

		classTypeLB = new Label("Class: ");
		classTypeCB = new ComboBox<WorkUnitPriorityClass>(
				FXCollections.observableArrayList(WorkUnitPriorityClass.values()));
		classTypeCB.getSelectionModel().selectedIndexProperty().addListener(classListener);

		superClassTypeLB = new Label("SuperClass: ");
		superClassTypeCB = new ComboBox<WorkUnitPrioritySuperClass>(
				FXCollections.observableArrayList(WorkUnitPrioritySuperClass.values()));
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

			setClassIndex(newValue.intValue());
			
			setSuperIndex(getSwitcher().priorityClassToSupperClass(getClassIndex()));
			superClassTypeCB.setValue(WorkUnitPrioritySuperClass.values()[getSuperIndex()]);
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
		String superST = WorkUnitPrioritySuperClass.values()[getSuperIndex()].name();

		if (nameST.length() == 0) {

			Alerts.showNoNameAlert();
			return;
		}

		ClassTable table = new ClassTable(nameST, classST, superST);

		getTableTV().getItems().add(table);
		getTableTV().sort();
		getControl().getFillForms().fillPriorityType(nameST, classST, superST);

	}

	@Override
	public void setActionSubmitButton() {
		close();

	}

}
