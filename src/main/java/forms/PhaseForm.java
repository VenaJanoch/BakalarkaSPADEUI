package forms;

import java.time.LocalDate;
import java.util.List;

import Controllers.CanvasController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.ObjectFactory;
import SPADEPAC.Phase;
import abstractform.BasicForm;
import abstractform.DateDescBasicForm;
import graphics.CanvasItem;
import graphics.DragAndDropCanvas;
import graphics.DragAndDropItemPanel;
import interfaces.ISegmentForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import org.junit.experimental.theories.FromDataPoints;
import services.Alerts;
import services.CanvasType;
import services.Control;
import services.DeleteControl;

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

	private ChoiceBox<String> configCB;
	private ChoiceBox<String> milestoneCB;
	private int milestoneIndex;
	private int configIndex;
	//private Phase phase;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné tříd Nastaví reakci na
	 * uzavření okna formuláře
	 *
	 * @param indexForm
	 */
	public PhaseForm(FormController formController, FormDataController formDataController, CanvasController canvasController, DragAndDropItemPanel dgItemPanel, String name, int indexForm) {

		super(formController, formDataController, canvasController, dgItemPanel, name);
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

		formController.deletePhaseForm(indexForm);

	}

	/**
	 * Getrs and Setrs
	 */
	public ChoiceBox<String> getConfigCB() {
		return configCB;
	}

	public void setConfigCB(ChoiceBox<String> configCB) {
		this.configCB = configCB;
	}

	public ChoiceBox<String> getMilestoneCB() {
		return milestoneCB;
	}

	public void setMilestoneCB(ChoiceBox<String> milestoneCB) {
		this.milestoneCB = milestoneCB;
	}

	public void setDataToForm(String name, LocalDate endDate, String description, Integer milestoneIndex, Integer configuration) {
		getNameTF().setText(name);
		getDescriptionTF().setText(description);
		dateDP.setValue(endDate);
		getMilestoneCB().getSelectionModel().select(milestoneIndex);
		getConfigCB().getSelectionModel().select(configuration);

	}
}
