package Forms;

import java.util.ArrayList;
import java.util.Arrays;

import Grafika.CanvasItem;
import Grafika.InfoBoxSegment;
import Interfaces.ISegmentForm;
import Obsluha.Control;
import Obsluha.SegmentType;
import SPADEPAC.WorkUnit;
import SPADEPAC.WorkUnitPriorityClass;
import SPADEPAC.WorkUnitPrioritySuperClass;
import SPADEPAC.WorkUnitSeverityClass;
import SPADEPAC.WorkUnitTypeClass;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

public class WorkUnitForm extends BasicForm implements ISegmentForm {

	private Label descriptionLB;
	private Label estimatedTimeLB;
	private Label priorityLB;
	private Label severityLB;
	private Label categoryLB;
	private Label typeLB;
	private Label asigneeRoleLB;
	private Label authorRoleLB;

	private TextField descriptionTF;
	private TextField estimatedTimeTF;
	private ComboBox<WorkUnitPriorityClass> priorityCB;
	private ComboBox<WorkUnitSeverityClass> severityCB;
	private ComboBox<String> categoryCB;
	private ComboBox<WorkUnitTypeClass> typeCB;
	private ComboBox<String> asigneeRoleCB;
	private ComboBox<String> authorRoleCB;

	private Button newRoleBT;
	private Button editRoleBT1;
	private Button editRoleBT2;

	private int priorityIndex;
	private int severityIndex;
	private int categoryIndex;
	private int assigneIndex;
	private int typeIndex;
	private int authorIndex;
	private WorkUnit unit;

	public WorkUnitForm(CanvasItem item, Control control, WorkUnit unit) {
		super(item, control);
		this.unit = unit;
		setRoleArray(new ArrayList<>());
		getRoleArray().add(unit.getAssignee());
		getRoleArray().add(unit.getAuthor());
		this.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				closeForm();
			}
		});

		getSubmitButton().setOnAction(event -> setActionSubmitButton());
		createForm();

	}

	@Override
	public void closeForm() {

		getCanvasItem().setNameText(getNameTF().getText());
		setName(getNameTF().getText());
		getControl().getFillForms().fillWorkUnit(getCanvasItem().getForm(), getCanvasItem().getIDs()[2],
				descriptionTF.getText(), getName(), assigneIndex, authorIndex,
				WorkUnitPriorityClass.values()[priorityIndex].name(),
				WorkUnitSeverityClass.values()[severityIndex].name(), WorkUnitTypeClass.values()[typeIndex].name(),
				(int) getCanvasItem().getTranslateX(), (int) getCanvasItem().getTranslateY(), priorityIndex,
				severityIndex, typeIndex);

	}

	@Override
	public void setActionSubmitButton() {
		if (getControl().getRoleList().isEmpty()) {
			getAlerts().showNoAuthorAlert();
			getAlerts().showNoAssigneeAlert();
		} else {
			closeForm();
			close();
		}
	}

	@Override
	public void createForm() {

		estimatedTimeLB = new Label("Estimated Time: ");
		estimatedTimeTF = new TextField();

		priorityLB = new Label("Priority: ");
		priorityCB = new ComboBox<WorkUnitPriorityClass>(
				FXCollections.observableArrayList(WorkUnitPriorityClass.values()));
		priorityCB.getSelectionModel().selectedIndexProperty().addListener(priorityListener);
		priorityCB.setVisibleRowCount(5);

		severityLB = new Label("Severity: ");
		severityCB = new ComboBox<WorkUnitSeverityClass>(
				FXCollections.observableArrayList(WorkUnitSeverityClass.values()));
		severityCB.getSelectionModel().selectedIndexProperty().addListener(severityListener);
		severityCB.setVisibleRowCount(5);
		categoryLB = new Label("CategoryLB: ");
		// categoryCB = new ChoiceBox<>();
		// categoryCB.getSelectionModel().selectedIndexProperty().addListener(categoryListener);

		typeLB = new Label("Type: ");
		typeCB = new ComboBox<WorkUnitTypeClass>(FXCollections.observableArrayList(WorkUnitTypeClass.values()));
		typeCB.getSelectionModel().selectedIndexProperty().addListener(typeListener);
		typeCB.setVisibleRowCount(5);

		asigneeRoleLB = new Label("Asignee-role: ");
		asigneeRoleCB = new ComboBox<String>(getControl().getRoleObservable());
		asigneeRoleCB.setVisibleRowCount(5);
		asigneeRoleCB.getSelectionModel().selectedIndexProperty().addListener(roleListenerAsig);

		authorRoleLB = new Label("Author-role: ");
		authorRoleCB = new ComboBox<String>(getControl().getRoleObservable());
		authorRoleCB.setVisibleRowCount(5);
		authorRoleCB.getSelectionModel().selectedIndexProperty().addListener(roleListenerAut);

		descriptionLB = new Label("Description: ");
		descriptionTF = new TextField();

		newRoleBT = new Button("New");
		newRoleBT.setOnAction(event -> roleBTAction());

		editRoleBT1 = new Button("Edit");
		editRoleBT1.setOnAction(event -> editRoleBTAction1());

		editRoleBT2 = new Button("Edit");
		editRoleBT2.setOnAction(event -> editRoleBTAction2());

		fillInfoPart();
	}

	private void editRoleBTAction2() {
		if (getControl().getRoleObservable().isEmpty()) {
			roleBTAction();
		}else{
			getControl().getForms().get(getControl().getRoleFormIndex().get(authorIndex)).show();						
		}
	}

	private void editRoleBTAction1() {
		if (getControl().getRoleObservable().isEmpty()) {
			roleBTAction();
		}else{
		getControl().getForms().get(getControl().getRoleFormIndex().get(assigneIndex)).show();
		}
	}

	private void roleBTAction() {
		CanvasItem item = new CanvasItem(SegmentType.Role, "Name", getControl(), this, true);

		getControl().getForms().get(item.getIDs()[0]).show();

	}

	ChangeListener<Number> typeListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			System.out.println(newValue.intValue());
			typeIndex = newValue.intValue();

		}
	};

	ChangeListener<Number> roleListenerAsig = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			assigneIndex = newValue.intValue();

		}
	};

	ChangeListener<Number> roleListenerAut = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			authorIndex = newValue.intValue();

		}
	};

	ChangeListener<Number> categoryListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			System.out.println(newValue.intValue());
			categoryIndex = newValue.intValue();

		}
	};

	ChangeListener<Number> priorityListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			System.out.println(newValue.intValue());
			priorityIndex = newValue.intValue();

		}
	};

	ChangeListener<Number> severityListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			severityIndex = newValue.intValue();

		}
	};

	private void fillInfoPart() {

		getInfoPart().add(descriptionLB, 0, 1);
		getInfoPart().setHalignment(descriptionLB, HPos.RIGHT);
		getInfoPart().add(descriptionTF, 1, 1);

		getInfoPart().add(estimatedTimeLB, 0, 2);
		getInfoPart().setHalignment(estimatedTimeLB, HPos.RIGHT);
		getInfoPart().add(estimatedTimeTF, 1, 2);

		getInfoPart().add(priorityLB, 0, 3);
		getInfoPart().setHalignment(priorityLB, HPos.RIGHT);
		getInfoPart().add(priorityCB, 1, 3);

		getInfoPart().add(severityLB, 0, 4);
		getInfoPart().setHalignment(severityLB, HPos.RIGHT);
		getInfoPart().add(severityCB, 1, 4);
		//
		// getInfoPart().add(categoryLB, 0, 5);
		// getInfoPart().setHalignment(categoryLB, HPos.RIGHT);
		// getInfoPart().add(categoryCB, 1, 5);

		getInfoPart().add(typeLB, 0, 6);
		getInfoPart().setHalignment(typeLB, HPos.RIGHT);
		getInfoPart().add(typeCB, 1, 6);

		getInfoPart().add(asigneeRoleLB, 0, 7);
		getInfoPart().setHalignment(asigneeRoleLB, HPos.RIGHT);
		getInfoPart().add(asigneeRoleCB, 1, 7);
		getInfoPart().add(newRoleBT, 3, 7);
		getInfoPart().add(editRoleBT1, 2, 7);
		
		getInfoPart().add(authorRoleLB, 0, 8);
		getInfoPart().setHalignment(authorRoleLB, HPos.RIGHT);
		getInfoPart().add(authorRoleCB, 1, 8);
		getInfoPart().add(editRoleBT2, 2, 8);

	}

	/**** Gets and Setrs ***/

	public TextField getDescriptionTF() {
		return descriptionTF;
	}

	public void setDescriptionTF(TextField descriptionTF) {
		this.descriptionTF = descriptionTF;
	}

	public TextField getEstimatedTimeTF() {
		return estimatedTimeTF;
	}

	public void setEstimatedTimeTF(TextField estimatedTimeTF) {
		this.estimatedTimeTF = estimatedTimeTF;
	}

	public ComboBox<WorkUnitPriorityClass> getPriorityCB() {
		return priorityCB;
	}

	public void setPriorityCB(ComboBox<WorkUnitPriorityClass> priorityCB) {
		this.priorityCB = priorityCB;
	}

	public ComboBox<WorkUnitSeverityClass> getSeverityCB() {
		return severityCB;
	}

	public void setSeverityCB(ComboBox<WorkUnitSeverityClass> severityCB) {
		this.severityCB = severityCB;
	}

	public ComboBox<String> getCategoryCB() {
		return categoryCB;
	}

	public void setCategoryCB(ComboBox<String> categoryCB) {
		this.categoryCB = categoryCB;
	}

	public ComboBox<WorkUnitTypeClass> getTypeCB() {
		return typeCB;
	}

	public void setTypeCB(ComboBox<WorkUnitTypeClass> typeCB) {
		this.typeCB = typeCB;
	}

	public ComboBox<String> getAsigneeRoleCB() {
		return asigneeRoleCB;
	}

	public void setAsigneeRoleCB(ComboBox<String> asigneeRoleCB) {
		this.asigneeRoleCB = asigneeRoleCB;
	}

	public ComboBox<String> getAuthorRoleCB() {
		return authorRoleCB;
	}

	public void setAuthorRoleCB(ComboBox<String> authorRoleCB) {
		this.authorRoleCB = authorRoleCB;
	}

}
