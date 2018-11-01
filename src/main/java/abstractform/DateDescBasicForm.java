package abstractform;

import java.time.LocalDate;

import Controllers.FormController;
import graphics.CanvasItem;
import javafx.geometry.HPos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import services.CanvasType;
import services.Control;
import services.DeleteControl;

/**
 * Třída odděděná ze třídy DescriptionBasicForm přidávající vstupní pole pro Date
 * @author Václav Janoch
 *
 */
public class DateDescBasicForm extends DescriptionBasicForm {

	private Label dateLB;
	private DatePicker dateDP;

	/**
	 * Konstruktor třídy
	 */
	public DateDescBasicForm(FormController formController, String name) {

		super(formController, name);
		fillForm();
	}

	/**
	 * Přetížený konstruktor třídy
	 */
	public DateDescBasicForm(FormController formController) {
		super(formController);
		fillForm();
	}

	/**
	 * Vytvoří vstupní pole pro Date a přidá ho do GridPane
	 */
	private void fillForm() {

		dateLB = new Label("Created: ");
		dateDP = new DatePicker();
		dateDP.setId("DP1");

		getInfoPart().add(dateLB, 0, 2);
		getInfoPart().setHalignment(dateLB, HPos.RIGHT);
		getInfoPart().add(dateDP, 1, 2);

	}

	
	/** Getrs and Setrs **/
	public Label getDateLB() {
		return dateLB;
	}

	public void setDateLB(Label dateLB) {
		this.dateLB = dateLB;
	}

	public DatePicker getDateDP() {
		return dateDP;
	}

	public void setDateDP(DatePicker dateDP) {
		this.dateDP = dateDP;
	}

}
