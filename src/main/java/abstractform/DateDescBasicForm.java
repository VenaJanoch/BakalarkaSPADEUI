package abstractform;

import java.time.LocalDate;

import Controllers.CanvasController;
import Controllers.FormController;
import graphics.CanvasItem;
import graphics.DragAndDropCanvas;
import graphics.DragAndDropItem;
import graphics.DragAndDropItemPanel;
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
	public DateDescBasicForm(FormController formController, CanvasController canvasController, DragAndDropItemPanel dgItemPanel, String name) {

		super(formController, canvasController, dgItemPanel, name);
		createForm();
	}

	/**
	 * Přetížený konstruktor třídy
	 */
	public DateDescBasicForm(FormController formController, String name) {
		super(formController, name);
		createForm();
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
