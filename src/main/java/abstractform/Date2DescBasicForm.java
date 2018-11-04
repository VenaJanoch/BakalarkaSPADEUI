/**
 * 
 */
package abstractform;

import java.time.LocalDate;

import Controllers.CanvasController;
import Controllers.FormController;
import graphics.CanvasItem;
import graphics.DragAndDropCanvas;
import graphics.DragAndDropItemPanel;
import javafx.geometry.HPos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import services.CanvasType;
import services.Control;
import services.DeleteControl;

/**
 * Třída odděděná ze třídy DateDescBasicForm přidávající vstupní pole pro druhý Date
 * @author Václav Janoch
 *
 */
public class Date2DescBasicForm extends DateDescBasicForm {

	private Label date2LB;
	private DatePicker date2DP;

	/**
	 * Konstruktor Třídy
	 */
	public Date2DescBasicForm(FormController formController, String name) {

		super(formController, name);
		createForm();
	}

	/**
	 * Přetížený konstruktor
	 */
	public Date2DescBasicForm(FormController formController, CanvasController canvasController, DragAndDropItemPanel dgItemPanel, String name) {

		super(formController, canvasController, dgItemPanel, name);
		createForm();
	}


	/**
	 * Vytvoří vstupní pole pro druhý Date a přidá ho do GridPane
	 */
	public void createForm() {

		date2LB = new Label("Created: ");
		date2DP = new DatePicker();
		date2DP.setId("DP2");
		
		getInfoPart().add(date2LB, 0, 3);
		getInfoPart().setHalignment(date2LB, HPos.RIGHT);
		getInfoPart().add(date2DP, 1, 3);

	}

	public Label getDate2LB() {
		return date2LB;
	}

	public void setDate2LB(Label date2lb) {
		date2LB = date2lb;
	}

	public DatePicker getDate2DP() {
		return date2DP;
	}

	public void setDate2DP(DatePicker date2dp) {
		date2DP = date2dp;
	}

}
