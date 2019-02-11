package forms;

import java.util.ArrayList;
import java.util.Arrays;

import Controllers.CanvasController;
import Controllers.FormController;
import Controllers.FormDataController;
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
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
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
import services.*;
import tables.BasicTable;

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
	private ComboBox<BasicTable> priorityCB;
	private ComboBox<BasicTable> severityCB;
	private ComboBox<BasicTable> resolutionCB;
	private ComboBox<BasicTable> statusCB;
	private TextField categoryTF;
	private ComboBox<BasicTable> typeCB;
	private ComboBox<BasicTable> asigneeRoleCB;
	private ComboBox<BasicTable> authorRoleCB;

	private int priorityIndex;
	private int severityIndex;
	private int assigneIndex;
	private int typeIndex;
	private int authorIndex;
	private int resolutionIndex;
	private int statusIndex;

	/**
	 * Konstruktor třídy. Zinicializuje globální proměnné tříd Nastaví velikost
	 * okna a reakci na uzavření formulář
	 */
	public WorkUnitForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, CanvasController canvasController, SegmentType type, int indexForm) {
		super(formController, formDataController, editFormController, deleteFormController, canvasController, type);
		getMainPanel().setMinSize(Constans.workUnitformWidth, Constans.workUnitformHeight);
		getMainPanel().setMaxSize(Constans.workUnitformWidth, Constans.workUnitformHeight);
		this.indexForm = indexForm;
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
		String category = categoryTF.getText();
		String desc = getDescriptionTF().getText();
		isSave = formDataController.saveDataFromWorkUnit(actName,desc, category, assigneIndex, authorIndex, priorityIndex, severityIndex, typeIndex,
				resolutionIndex, statusIndex, estimatedTimeTF.getText(), getExistRB().isSelected(), indexForm, canvasController.getCanvasType());
	}

	@Override
	public void setActionSubmitButton() {

		closeForm();
		if(isSave){
			close();
		}
	}

	public void createForm() {

		estimatedTimeLB = new Label("Estimated Time: ");
		estimatedTimeTF = new TextField();

		priorityLB = new Label("Priority: ");
		priorityCB = new ComboBox<BasicTable>();

		priorityCB.getSelectionModel().selectedIndexProperty().addListener(priorityListener);
		priorityCB.setVisibleRowCount(5);

		severityLB = new Label("Severity: ");
		severityCB = new ComboBox<BasicTable>();
		severityCB.getSelectionModel().selectedIndexProperty().addListener(severityListener);
		severityCB.setVisibleRowCount(5);

		categoryLB = new Label("Category: ");
		categoryTF = new TextField();

		typeLB = new Label("Type: ");
		typeCB = new ComboBox<BasicTable>();
		typeCB.getSelectionModel().selectedIndexProperty().addListener(typeListener);
		typeCB.setVisibleRowCount(5);

		asigneeRoleLB = new Label("Asignee-role: ");
		asigneeRoleCB = new ComboBox<>();
		asigneeRoleCB.setVisibleRowCount(5);
		asigneeRoleCB.getSelectionModel().selectedIndexProperty().addListener(roleListenerAsig);

		authorRoleLB = new Label("Author-role: ");
		authorRoleCB = new ComboBox<>();
		authorRoleCB.setVisibleRowCount(5);
		authorRoleCB.getSelectionModel().selectedIndexProperty().addListener(roleListenerAut);

		resolutionLB = new Label("Resolution: ");
		resolutionCB = new ComboBox<BasicTable>();
		resolutionCB.getSelectionModel().selectedIndexProperty().addListener(resolutionListener);
		resolutionCB.setVisibleRowCount(5);

		statusLB = new Label("Status: ");
		statusCB = new ComboBox<BasicTable>();
		statusCB.getSelectionModel().selectedIndexProperty().addListener(statusListener);
		statusCB.setVisibleRowCount(5);

		existRB = new RadioButton("Exist");
		existRB.setSelected(true);

		fillInfoPart();
	}

	@Override
	public void deleteItem() {
		deleteFormController.deleteWorkUnit(indexForm);
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

	public void setDataToForm(String name, int assigneeIndex, int authorIndex, String category, String description, String estimatedTime,
							  int priorityIndex, int resolutionIndex, int severityIndex,int statusIndex, int typeIndex, boolean isExist){
		getNameTF().setText(name);
		getDescriptionTF().setText(description);
		getCategoryTF().setText(category);
		asigneeRoleCB.getSelectionModel().select(assigneeIndex);
		authorRoleCB.getSelectionModel().select(authorIndex);
		estimatedTimeTF.setText(estimatedTime);
		priorityCB.getSelectionModel().select(priorityIndex);
		resolutionCB.getSelectionModel().select(resolutionIndex);
		severityCB.getSelectionModel().select(severityIndex);
		statusCB.getSelectionModel().select(statusIndex);
		typeCB.getSelectionModel().select(typeIndex);
		existRB.setSelected(isExist);
		isSave = true;
	}

	/**** Gets and Setrs ***/

	public TextField getEstimatedTimeTF() {
		return estimatedTimeTF;
	}

	public void setEstimatedTimeTF(TextField estimatedTimeTF) {
		this.estimatedTimeTF = estimatedTimeTF;
	}

	public ComboBox<BasicTable> getPriorityCB() {
		return priorityCB;
	}

	public void setPriorityCB(ComboBox<BasicTable> priorityCB) {
		this.priorityCB = priorityCB;
	}

	public ComboBox<BasicTable> getSeverityCB() {
		return severityCB;
	}

	public void setSeverityCB(ComboBox<BasicTable> severityCB) {
		this.severityCB = severityCB;
	}

	public TextField getCategoryTF() {
		return categoryTF;
	}

	public void setCategoryTF(TextField categoryTF) {
		this.categoryTF = categoryTF;
	}

	public ComboBox<BasicTable> getTypeCB() {
		return typeCB;
	}

	public void setTypeCB(ComboBox<BasicTable> typeCB) {
		this.typeCB = typeCB;
	}

	public ComboBox<BasicTable> getAsigneeRoleCB() {
		return asigneeRoleCB;
	}

	public void setAsigneeRoleCB(ComboBox<BasicTable> asigneeRoleCB) {
		this.asigneeRoleCB = asigneeRoleCB;
	}

	public ComboBox<BasicTable> getAuthorRoleCB() {
		return authorRoleCB;
	}

	public void setAuthorRoleCB(ComboBox<BasicTable> authorRoleCB) {
		this.authorRoleCB = authorRoleCB;
	}

	public ComboBox<BasicTable> getResolutionCB() {
		return resolutionCB;
	}

	public void setResolutionCB(ComboBox<BasicTable> resolutionCB) {
		this.resolutionCB = resolutionCB;
	}

	public ComboBox<BasicTable> getStatusCB() {
		return statusCB;
	}

	public void setStatusCB(ComboBox<BasicTable> statusCB) {
		this.statusCB = statusCB;
	}

	public RadioButton getExistRB() {
		return existRB;
	}

	public void setExistRB(RadioButton existRB) {
		this.existRB = existRB;
	}

}
