package forms;

import java.time.LocalDate;

import Controllers.FormController;
import SPADEPAC.Iteration;
import abstractform.BasicForm;
import abstractform.Date2DescBasicForm;
import graphics.CanvasItem;
import graphics.DragAndDropCanvas;
import graphics.DragAndDropItemPanel;
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

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné tříd Nastaví reakci na
	 * uzavření formuláře
	 * @param indexForm
	 *            DeleteControl
	 */
	public IterationForm(FormController formController, DragAndDropCanvas canvas, DragAndDropItemPanel dgItemPanel, String name, int indexForm) {

		super(formController,canvas, dgItemPanel, name);
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
		LocalDate startDate = getDateDP().getValue();
		LocalDate endDate = getDate2DP().getValue();
		String desc = getDescriptionTF().getText();
		isSave = formController.saveDataFromIterationForm(actName, startDate,endDate,desc, chooseConfigID, canvasController.getListOfItemOnCanvas(), indexForm);
	}

	@Override
	public void setActionSubmitButton() {
		closeForm();
		if (isSave){
			close();
		}
	}

	@Override
	public void createForm() {

		configLB = new Label("Configuration: ");
		configCB = new ChoiceBox<>();

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
	public void deleteItem() {

		formController.deleteIteration();

	}

	/*** Getrs and Setrs ***/

	public ChoiceBox<String> getConfigCB() {
		return configCB;
	}

	public void setConfigCB(ChoiceBox<String> configCB) {
		this.configCB = configCB;
	}

}
