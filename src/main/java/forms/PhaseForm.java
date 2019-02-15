package forms;

import java.time.LocalDate;

import controllers.CanvasController;
import controllers.FormController;
import abstractform.DateDescBasicForm;
import graphics.DragAndDropItemPanel;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import interfaces.ISegmentForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import services.*;
import tables.BasicTable;

/**
 * Třída představující formulář pro segment Phase, odděděná od třídy
 * DeteDescBasicForm a implementující ISegmentForm
 * 
 * @author Václav Janoch
 *
 */
public class PhaseForm extends DateDescBasicForm implements ISegmentForm {
	/**
	 * Globální proměnné třídy
	 */
	private Label configLB;
	private Label milestoneLB;

	private ChoiceBox<BasicTable> configCB;
	private ChoiceBox<BasicTable> milestoneCB;
	private int milestoneIndex;
	private int configIndex;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné tříd Nastaví reakci na
	 * uzavření okna formuláře
	 *
	 * @param indexForm
	 */
	public PhaseForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, CanvasController canvasController, DragAndDropItemPanel dgItemPanel, SegmentType type, int indexForm) {

		super(formController, formDataController, editFormController, deleteFormController, canvasController, dgItemPanel, type);
		this. indexForm = indexForm;
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
		fillForm();

	}
	
	@Override
	public void closeForm() {

		String actName = getNameTF().getText();
		LocalDate endDateL = dateDP.getValue();
		String desc = getDescriptionTF().getText();

		isSave = formDataController.saveDataFromPhaseForm(actName, endDateL, desc, configIndex, milestoneIndex, canvasController.getListOfItemOnCanvas(), indexForm);

	}

	@Override
	public void setActionSubmitButton() {
		closeForm();
		if(isSave){
			close();
		}

	}

	public void fillForm() {
		dateLB.setText("End-Date");

		configLB = new Label("Configuration: ");
		configCB = new ChoiceBox<>();

		configCB.getSelectionModel().selectedIndexProperty().addListener(configListener);

		milestoneLB = new Label("Milestone: ");
		milestoneCB = new ChoiceBox<>();
		milestoneCB.getSelectionModel().selectedIndexProperty().addListener(milestoneListener);
		fillInfoPart();
	}

	/**
	 * ChangeListener pro určení indexu prvku z comboBoxu pro Milestone
	 */
	ChangeListener<Number> milestoneListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			milestoneIndex = newValue.intValue();

		}
	};

	/**
	 * ChangeListener pro určení indexu prvku z comboBoxu pro Configuration
	 */
	ChangeListener<Number> configListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			configIndex = newValue.intValue();

		}
	};

	/**
	 * Pomocná metoda pro vyplnění prvků do GridPane
	 */

	private void fillInfoPart() {

		getInfoPart().add(configLB, 0, 3);
		getInfoPart().setHalignment(configLB, HPos.RIGHT);
		getInfoPart().add(configCB, 1, 3);

		getInfoPart().add(milestoneLB, 0, 4);
		getInfoPart().setHalignment(milestoneLB, HPos.RIGHT);
		getInfoPart().add(milestoneCB, 1, 4);

	}

	@Override
	public void deleteItem() {

		deleteFormController.deletePhaseForm(indexForm);

	}

	public void setDataToForm(String name, LocalDate endDate, String description, Integer milestoneIndex, Integer configuration) {
		getNameTF().setText(name);
		getDescriptionTF().setText(description);
		dateDP.setValue(endDate);
		getMilestoneCB().getSelectionModel().select(milestoneIndex);
		getConfigCB().getSelectionModel().select(configuration);

	}

	/**
	 * Getrs and Setrs
	 */
	public ChoiceBox<BasicTable> getConfigCB() {
		return configCB;
	}

	public ChoiceBox<BasicTable> getMilestoneCB() {
		return milestoneCB;
	}


}
