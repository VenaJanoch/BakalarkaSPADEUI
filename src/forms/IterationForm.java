package forms;

import java.time.LocalDate;

import SPADEPAC.Iteration;
import abstractform.BasicForm;
import abstractform.Date2DescBasicForm;
import graphics.CanvasItem;
import graphics.InfoBoxSegment;
import interfaces.ISegmentForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import services.Alerts;
import services.CanvasType;
import services.Control;
import services.DeleteControl;
import services.SegmentType;

/**
 * Třída představující formulář pro segment Iteration, odděděná od třídy
 * Dete2DescBasicForm a implementující ISegmentForm
 * 
 * @author Václav Janoch
 *
 */
public class IterationForm extends Date2DescBasicForm implements ISegmentForm {

	/**
	 * Globální proměnné třídy
	 */
	private Label configLB;

	private ChoiceBox<String> configCB;

	private int chooseConfigID;
	private Iteration iteration;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné tříd Nastaví reakci na
	 * uzavření formuláře
	 * 
	 * @param item
	 *            CanvasItem
	 * @param control
	 *            Control
	 * @param itemArray
	 * @param iteration
	 *            Iteration
	 * @param indexForm
	 * @param deleteControl
	 *            DeleteControl
	 */
	public IterationForm(CanvasItem item, Control control, int[] itemArray, Iteration iteration, int indexForm,
			DeleteControl deleteControl) {
		super(item, control, itemArray, indexForm, deleteControl, CanvasType.Iteration);
		setWorkUnitArray(iteration.getWorkUnits());
		this.iteration = iteration;
		setNew(true);
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
		LocalDate startDate = getDateDP().getValue();
		LocalDate endDate = getDate2DP().getValue();
		String desc = getDescriptionTF().getText();
		setName(actName);
		getCanvasItem().setNameText(actName);
		getControl().getFillForms().fillIteration(iteration, IDs, desc, actName, startDate, endDate, chooseConfigID, x,
				y, isNew());
		setNew(false);
	}

	@Override
	public void setActionSubmitButton() {
		closeForm();
		close();
	}

	@Override
	public void createForm() {

		configLB = new Label("Configuration: ");
		configCB = new ChoiceBox<>(getControl().getLists().getConfigObservable());

		configCB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				chooseConfigID = newValue.intValue();

			}
		});

		fillInfoPart();
	}

	/**
	 * Pomocná metoda pro přidání prvků do GridPane
	 */
	private void fillInfoPart() {

		getDateLB().setText("Start-Date");
		getDate2LB().setText("End-Date");

		getInfoPart().add(configLB, 0, 4);
		getInfoPart().setHalignment(configLB, HPos.RIGHT);
		getInfoPart().add(configCB, 1, 4);

	}

	@Override
	public void deleteItem(int iDs[]) {

		deleteControl.deleteIteration(iDs);

	}

	/*** Getrs and Setrs ***/

	public ChoiceBox<String> getConfigCB() {
		return configCB;
	}

	public void setConfigCB(ChoiceBox<String> configCB) {
		this.configCB = configCB;
	}

}
