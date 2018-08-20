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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import services.Alerts;
import services.Constans;
import services.Control;
import services.DeleteControl;
import services.SegmentType;

/**
 * Třída představující formulář pro element Work Unit, odděděná od třídy
 * DescriptionBasicForm a implementující ISegmentForm
 * 
 * @author Václav Janoch
 *
 */
public class WorkUnitForm extends DescriptionBasicForm implements ISegmentForm {

	/**
	 * Globální proměnné třídy
	 */
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
	private RadioButton existRB;
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
	private Double estimated;

	/**
	 * Konstruktor třídy. Zinicializuje globální proměnné tříd Nastaví velikost
	 * okna a reakci na uzavření formuláře
	 * 
	 * @param item
	 *            CanvasItem
	 * @param control
	 *            Control
	 * @param unit
	 *            WorkUnit
	 * 
	 * @param deleteControl
	 *            DeleteControl
	 * @param conf
	 *            Configuration
	 */
	public WorkUnitForm(CanvasItem item, Control control, WorkUnit unit, DeleteControl deleteControl) {
		super(item, control, deleteControl);
		this.unit = unit;

		getMainPanel().setMinSize(Constans.workUnitformWidth, Constans.workUnitformHeight);
		getMainPanel().setMaxSize(Constans.workUnitformWidth, Constans.workUnitformHeight);
		setNew(true);
		setRoleArray(new ArrayList<>());
		getRoleArray().add(unit.getAssigneeIndex());
		getRoleArray().add(unit.getAuthorIndex());
		this.setOnCloseRequest(e -> {

			e.consume();
			int result = Alerts.showSaveSegment();
			if (result == 1) {
				setActionSubmitButton();
			} else if (result == 0) {
				this.close();
			}
		});

		getSubmitButton().setOnAction(event -> setActionSubmitButton());
		createForm();

	}

	@Override
	public void closeForm() {

		String actName = getNameTF().getText();
		int[] IDs = getCanvasItem().getIDs();
		int x = (int) getCanvasItem().getTranslateX();
		int y = (int) getCanvasItem().getTranslateY();
		String category = categoryTF.getText();

		getCanvasItem().setNameText(actName);
		setName(actName);
		getControl().getFillForms().fillWorkUnit(unit, IDs, getDescriptionTF().getText(), actName, assigneIndex,
				authorIndex, category, x, y, priorityIndex, severityIndex, typeIndex, resolutionIndex, statusIndex,
				estimated, isNew(), existRB.isSelected(), Control.objF);

		if (!existRB.isSelected()) {
			getCanvasItem().getSegmentInfo().setRectangleColor(Constans.nonExistRectangleBorderColor);
		} else {
			getCanvasItem().getSegmentInfo().setRectangleColor(Constans.rectangleBorderColor);
		}
		setNew(false);

	}

	@Override
	public void setActionSubmitButton() {
		estimated = -1.0;
		try {
			if (!estimatedTimeTF.getText().equals("")) {
				estimated = Double.parseDouble(estimatedTimeTF.getText());
			}

			closeForm();
			close();
		} catch (NumberFormatException e) {
			Alerts.showWrongEstimatedTimeAlert();

		}

	}

	@Override
	public void createForm() {

		estimatedTimeLB = new Label("Estimated Time: ");
		estimatedTimeTF = new TextField();

		priorityLB = new Label("Priority: ");
		priorityCB = new ComboBox<String>(getControl().getLists().getPriorityObservable());

		priorityCB.getSelectionModel().selectedIndexProperty().addListener(priorityListener);
		priorityCB.setVisibleRowCount(5);

		severityLB = new Label("Severity: ");
		severityCB = new ComboBox<String>(getControl().getLists().getSeverityTypeObservable());
		severityCB.getSelectionModel().selectedIndexProperty().addListener(severityListener);
		severityCB.setVisibleRowCount(5);

		categoryLB = new Label("Category: ");
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
		resolutionCB = new ComboBox<String>(getControl().getLists().getResolutionTypeObservable());
		resolutionCB.getSelectionModel().selectedIndexProperty().addListener(resolutionListener);
		resolutionCB.setVisibleRowCount(5);

		statusLB = new Label("Status: ");
		statusCB = new ComboBox<String>(getControl().getLists().getStatusTypeObservable());
		statusCB.getSelectionModel().selectedIndexProperty().addListener(statusListener);
		statusCB.setVisibleRowCount(5);

		existRB = new RadioButton("Exist");
		existRB.setSelected(true);

		fillInfoPart();
	}

	/**
	 * ChangeListener pro určení indexu prvku z comboBoxu pro Work Unit - Type
	 */
	ChangeListener<Number> typeListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			typeIndex = newValue.intValue();

		}
	};
	/**
	 * ChangeListener pro určení indexu prvku z comboBoxu pro Resolution
	 */
	ChangeListener<Number> resolutionListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			resolutionIndex = newValue.intValue();

		}
	};

	/**
	 * ChangeListener pro určení indexu prvku z comboBoxu pro Status
	 */
	ChangeListener<Number> statusListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			statusIndex = newValue.intValue();

		}
	};
	/**
	 * ChangeListener pro určení indexu prvku z comboBoxu pro Role-Assiggne
	 */
	ChangeListener<Number> roleListenerAsig = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			assigneIndex = newValue.intValue();

		}
	};

	/**
	 * ChangeListener pro určení indexu prvku z comboBoxu pro Role-Author
	 */
	ChangeListener<Number> roleListenerAut = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			authorIndex = newValue.intValue();

		}
	};
	/**
	 * ChangeListener pro určení indexu prvku z comboBoxu pro Priority
	 */
	ChangeListener<Number> priorityListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			priorityIndex = newValue.intValue();

		}
	};
	/**
	 * ChangeListener pro určení indexu prvku z comboBoxu pro Severity
	 */
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

		getInfoPart().add(typeLB, 0, 6);
		getInfoPart().setHalignment(typeLB, HPos.RIGHT);
		getInfoPart().add(typeCB, 1, 6);

		getInfoPart().add(resolutionLB, 0, 7);
		getInfoPart().setHalignment(resolutionLB, HPos.RIGHT);
		getInfoPart().add(resolutionCB, 1, 7);

		getInfoPart().add(statusLB, 0, 8);
		getInfoPart().setHalignment(statusLB, HPos.RIGHT);
		getInfoPart().add(statusCB, 1, 8);

		getInfoPart().add(asigneeRoleLB, 0, 9);
		getInfoPart().setHalignment(asigneeRoleLB, HPos.RIGHT);
		getInfoPart().add(asigneeRoleCB, 1, 9);

		getInfoPart().add(authorRoleLB, 0, 10);
		getInfoPart().setHalignment(authorRoleLB, HPos.RIGHT);
		getInfoPart().add(authorRoleCB, 1, 10);

		getInfoPart().add(existRB, 1, 11);

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

	public RadioButton getExistRB() {
		return existRB;
	}

	public void setExistRB(RadioButton existRB) {
		this.existRB = existRB;
	}

}
