package abstractform;

import Controllers.CanvasController;
import Controllers.FormController;
import Controllers.FormDataController;
import graphics.DragAndDropItemPanel;
import javafx.geometry.HPos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Třída odděděná ze třídy DescriptionBasicForm přidávající vstupní pole pro Date
 * @author Václav Janoch
 *
 */
public class DateBasicForm extends BasicForm {

	private Label dateLB;
	private DatePicker dateDP;

	/**
	 * Konstruktor třídy
	 */
	public DateBasicForm(FormController formController, FormDataController formDataController, CanvasController canvasController, DragAndDropItemPanel dgItemPanel, String name) {

		super(formController, formDataController, canvasController, dgItemPanel, name);
		createForm();
	}


	/**
	 * Přetížený konstruktor třídy
	 */
	public DateBasicForm(FormController formController, FormDataController formDataController, String name) {
		super(formController, formDataController, name);
		createForm();
	}

	@Override
	void createForm() {

		dateLB = new Label("Created: ");
		dateDP = new DatePicker();

		getInfoPart().add(dateLB, 0, 1);
		getInfoPart().setHalignment(dateLB, HPos.RIGHT);
		getInfoPart().add(dateDP, 1, 1);
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
