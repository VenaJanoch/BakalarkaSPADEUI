package forms;

import java.util.ArrayList;
import java.util.Arrays;

import SPADEPAC.RoleClass;
import SPADEPAC.WorkUnit;
import SPADEPAC.WorkUnitPriorityClass;
import SPADEPAC.WorkUnitPrioritySuperClass;
import SPADEPAC.WorkUnitSeverityClass;
import SPADEPAC.WorkUnitTypeClass;
import abstractform.BasicForm;
import abstractform.DescriptionBasicForm;
import graphics.CanvasItem;
import graphics.InfoBoxSegment;
import interfaces.ISegmentForm;
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
import services.Alerts;
import services.Control;
import services.DeleteControl;
import services.SegmentType;

public class WorkUnitForm extends DescriptionBasicForm implements ISegmentForm {

	private Label estimatedTimeLB;
	private Label priorityLB;
	private Label severityLB;
	private Label categoryLB;
	private Label statusLB;
	private Label resolutionLB;
	private Label typeLB;
	private Label asigneeRoleLB;
	private Label authorRoleLB;

	private TextField estimatedTimeTF;
	private ComboBox<String> priorityCB;
	private ComboBox<String> severityCB;
	private ComboBox<String> resolutionCB;
	private ComboBox<String> statusCB;
	private TextField categoryTF;
	private ComboBox<String> typeCB;
	private ComboBox<String> asigneeRoleCB;
	private ComboBox<String> authorRoleCB;

	private int priorityIndex;
	private int severityIndex;
	private int assigneIndex;
	private int typeIndex;
	private int authorIndex;
	private int resolutionIndex;
	private int statusIndex;

	private WorkUnit unit;

	public WorkUnitForm(CanvasItem item, Control control, WorkUnit unit, DeleteControl deleteControl) {
		super(item, control, deleteControl);
		this.unit = unit;

		setRoleArray(new ArrayList<>());
		getRoleArray().add(unit.getAssigneeIndex());
		getRoleArray().add(unit.getAuthorIndex());
		this.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				Alerts.showSaveSegment();
			}
		});

		getSubmitButton().setOnAction(event -> setActionSubmitButton());
		createForm();

	}

	@Override
	public void closeForm() {

		String actName = getNameTF().getText();
		BasicForm form = getCanvasItem().getForm();
		int[] IDs = getCanvasItem().getIDs();
		int x = (int) getCanvasItem().getTranslateX();
		int y = (int) getCanvasItem().getTranslateY();
		String category = categoryTF.getText();

		getCanvasItem().setNameText(actName);
		setName(actName);
		getControl().getFillForms().fillWorkUnit(form, IDs[1], getDescriptionTF().getText(), actName, assigneIndex,
				authorIndex, category, x, y, priorityIndex, severityIndex, typeIndex, resolutionIndex, statusIndex,
				Double.parseDouble(estimatedTimeTF.getText()));

	}

	@Override
	public void setActionSubmitButton() {

		if (getFormControl().workUnitControl(estimatedTimeTF.getText())) {
			closeForm();
			close();
		}
	}

	@Override
	public void createForm() {

		estimatedTimeLB = new Label("Estimated Time: ");
		estimatedTimeTF = new TextField();

		priorityLB = new Label("Priority: ");
		priorityCB = new ComboBox<String>(
				FXCollections.observableArrayList(getControl().getLists().getPriorityObservable()));

		priorityCB.getSelectionModel().selectedIndexProperty().addListener(priorityListener);
		priorityCB.setVisibleRowCount(5);

		severityLB = new Label("Severity: ");
		severityCB = new ComboBox<String>(
				FXCollections.observableArrayList(getControl().getLists().getSeverityTypeObservable()));
		severityCB.getSelectionModel().selectedIndexProperty().addListener(severityListener);
		severityCB.setVisibleRowCount(5);

		categoryLB = new Label("CategoryLB: ");
		categoryTF = new TextField();

		typeLB = new Label("Type: ");
		typeCB = new ComboBox<String>(getControl().getLists().getTypeObservable());
		typeCB.getSelectionModel().selectedIndexProperty().addListener(typeListener);
		typeCB.setVisibleRowCount(5);

		asigneeRoleLB = new Label("Asignee-role: ");
		asigneeRoleCB = new ComboBox<String>(getControl().getLists().getRoleObservable());
		asigneeRoleCB.setVisibleRowCount(5);
		asigneeRoleCB.getSelectionModel().selectedIndexProperty().addListener(roleListenerAsig);

		authorRoleLB = new Label("Author-role: ");
		authorRoleCB = new ComboBox<String>(getControl().getLists().getRoleObservable());
		authorRoleCB.setVisibleRowCount(5);
		authorRoleCB.getSelectionModel().selectedIndexProperty().addListener(roleListenerAut);

		resolutionLB = new Label("Resolution: ");
		resolutionCB = new ComboBox<String>(
				FXCollections.observableArrayList(getControl().getLists().getResolutionTypeObservable()));
		resolutionCB.getSelectionModel().selectedIndexProperty().addListener(resolutionListener);
		resolutionCB.setVisibleRowCount(5);

		statusLB = new Label("Status: ");
		statusCB = new ComboBox<String>(
				FXCollections.observableArrayList(getControl().getLists().getStatusTypeObservable()));
		statusCB.getSelectionModel().selectedIndexProperty().addListener(statusListener);
		statusCB.setVisibleRowCount(5);

		fillInfoPart();
	}

	ChangeListener<Number> typeListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			typeIndex = newValue.intValue();

		}
	};

	ChangeListener<Number> resolutionListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			resolutionIndex = newValue.intValue();

		}
	};

	ChangeListener<Number> statusListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			statusIndex = newValue.intValue();

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

	ChangeListener<Number> priorityListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

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

		getInfoPart().add(estimatedTimeLB, 0, 2);
		getInfoPart().setHalignment(estimatedTimeLB, HPos.RIGHT);
		getInfoPart().add(estimatedTimeTF, 1, 2);

		getInfoPart().add(categoryLB, 0, 3);
		getInfoPart().setHalignment(categoryLB, HPos.RIGHT);
		getInfoPart().add(categoryTF, 1, 3);

		getInfoPart().add(priorityLB, 0, 4);
		getInfoPart().setHalignment(priorityLB, HPos.RIGHT);
		getInfoPart().add(priorityCB, 1, 4);

		getInfoPart().add(severityLB, 0, 5);
		getInfoPart().setHalignment(severityLB, HPos.RIGHT);
		getInfoPart().add(severityCB, 1, 5);

		getInfoPart().add(typeLB, 0, 7);
		getInfoPart().setHalignment(typeLB, HPos.RIGHT);
		getInfoPart().add(typeCB, 1, 7);

		getInfoPart().add(resolutionLB, 0, 8);
		getInfoPart().setHalignment(resolutionLB, HPos.RIGHT);
		getInfoPart().add(resolutionCB, 1, 8);

		getInfoPart().add(statusLB, 0, 9);
		getInfoPart().setHalignment(statusLB, HPos.RIGHT);
		getInfoPart().add(statusCB, 1, 9);

		getInfoPart().add(asigneeRoleLB, 0, 10);
		getInfoPart().setHalignment(asigneeRoleLB, HPos.RIGHT);
		getInfoPart().add(asigneeRoleCB, 1, 10);

		getInfoPart().add(authorRoleLB, 0, 11);
		getInfoPart().setHalignment(authorRoleLB, HPos.RIGHT);
		getInfoPart().add(authorRoleCB, 1, 11);

	}

	
	@Override
	public void deleteItem(int iDs[]) {
	
		deleteControl.deleteWorkUnit(iDs);
		
	}
	/**** Gets and Setrs ***/

	public TextField getEstimatedTimeTF() {
		return estimatedTimeTF;
	}

	public void setEstimatedTimeTF(TextField estimatedTimeTF) {
		this.estimatedTimeTF = estimatedTimeTF;
	}

	public ComboBox<String> getPriorityCB() {
		return priorityCB;
	}

	public void setPriorityCB(ComboBox<String> priorityCB) {
		this.priorityCB = priorityCB;
	}

	public ComboBox<String> getSeverityCB() {
		return severityCB;
	}

	public void setSeverityCB(ComboBox<String> severityCB) {
		this.severityCB = severityCB;
	}

	public TextField getCategoryTF() {
		return categoryTF;
	}

	public void setCategoryTF(TextField categoryTF) {
		this.categoryTF = categoryTF;
	}

	public ComboBox<String> getTypeCB() {
		return typeCB;
	}

	public void setTypeCB(ComboBox<String> typeCB) {
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

	public ComboBox<String> getResolutionCB() {
		return resolutionCB;
	}

	public void setResolutionCB(ComboBox<String> resolutionCB) {
		this.resolutionCB = resolutionCB;
	}

	public ComboBox<String> getStatusCB() {
		return statusCB;
	}

	public void setStatusCB(ComboBox<String> statusCB) {
		this.statusCB = statusCB;
	}

}
