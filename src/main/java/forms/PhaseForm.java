package forms;

import java.time.LocalDate;

import SPADEPAC.ObjectFactory;
import SPADEPAC.Phase;
import abstractform.BasicForm;
import abstractform.DateDescBasicForm;
import graphics.CanvasItem;
import interfaces.ISegmentForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
	private Phase phase;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné tříd Nastaví reakci na
	 * uzavření okna formuláře
	 * 
	 * @param item
	 *            CanvasItem
	 * @param control
	 *            Control
	 * @param itemArray
	 * @param phase
	 *            Phase
	 * @param indexForm
	 * @param deleteControl
	 *            DeleteControl
	 */
	public PhaseForm(CanvasItem item, Control control, int[] itemArray, Phase phase, int indexForm,
			DeleteControl deleteControl) {
		super(item, control, itemArray, indexForm, deleteControl, CanvasType.Phase);
		this.phase = phase;
		setNew(true);
		setWorkUnitArray(phase.getWorkUnits());
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
		BasicForm form = getCanvasItem().getForm();
		int[] IDs = getCanvasItem().getIDs();
		int x = (int) getCanvasItem().getTranslateX();
		int y = (int) getCanvasItem().getTranslateY();
		LocalDate endDateL = getDateDP().getValue();
		String desc = getDescriptionTF().getText();

		setName(actName);
		getCanvasItem().setNameText(actName);
		getCanvasItem().getFillForms().fillPhase(phase, IDs, desc, actName, endDateL, configIndex, milestoneIndex, x, y,
				isNew(), new ObjectFactory());

		setNew(false);

	}

	@Override
	public void setActionSubmitButton() {
		closeForm();
		close();
	}

	@Override
	public void createForm() {
		getDateLB().setText("End Date: ");

		configLB = new Label("Configuration: ");
		configCB = new ChoiceBox<>(getControl().getLists().getConfigObservable());
		configCB.getSelectionModel().selectedIndexProperty().addListener(configListener);

		milestoneLB = new Label("Milestone: ");
		milestoneCB = new ChoiceBox<>(getControl().getLists().getMilestoneObservable());
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
	public void deleteItem(int iDs[]) {

		deleteControl.deletePhase(iDs);

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

}
