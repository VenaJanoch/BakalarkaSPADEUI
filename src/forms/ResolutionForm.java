package forms;

import java.util.List;

import SPADEPAC.WorkUnitPriorityClass;
import SPADEPAC.WorkUnitPrioritySuperClass;
import SPADEPAC.WorkUnitResolutionClass;
import SPADEPAC.WorkUnitResolutionsSuperClass;
import SPADEPAC.WorkUnitSeverityClass;
import SPADEPAC.WorkUnitSeveritySuperClass;
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
import services.Control;
import services.DeleteControl;
import services.IdentificatorCreater;
import tables.ClassTable;

public class ResolutionForm extends TableClassBasicForm implements ISegmentTableForm {

	private Control control;

	private ComboBox<WorkUnitResolutionClass> classTypeCB;
	private ComboBox<WorkUnitResolutionsSuperClass> superClassTypeCB;

	private Label classTypeLB;
	private Label superClassTypeLB;

	public ResolutionForm(Control control, DeleteControl deleteControl, IdentificatorCreater idCreator) {
		super(control, deleteControl, idCreator);

		this.control = control;
		this.setTitle("Edit Resolutions");
		createForm();
		// getSubmitButton().setVisible(false);
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

		classTypeLB = new Label("Class: ");
		classTypeCB = new ComboBox<WorkUnitResolutionClass>(
				FXCollections.observableArrayList(WorkUnitResolutionClass.values()));
		classTypeCB.getSelectionModel().selectedIndexProperty().addListener(classListener);

		superClassTypeLB = new Label("SuperClass: ");
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
		getControl().getFillForms().fillResolutionType(idName, formControl.fillTextMapper(nameST), formControl.fillTextMapper(classST), superST);

	}

	@Override
	public void setActionSubmitButton() {
		close();

	}

}
