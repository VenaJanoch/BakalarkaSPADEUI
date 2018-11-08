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

	protected Label date2LB;
	protected DatePicker date2DP;

	/**
	 * Konstruktor Třídy
	 */
	public Date2DescBasicForm(FormController formController, String name) {

		super(formController, name);
		fillFormDate2();
	}

	/**
	 * Přetížený konstruktor
	 */
	public Date2DescBasicForm(FormController formController, CanvasController canvasController, DragAndDropItemPanel dgItemPanel, String name) {

		super(formController, canvasController, dgItemPanel, name);
		fillFormDate2();
	}


	/**
	 * Vytvoří vstupní pole pro druhý Date a přidá ho do GridPane
	 */
	public void fillFormDate2() {

		date2LB = new Label("Created: ");
		date2DP = new DatePicker();
		date2DP.setId("DP2");

		getInfoPart().add(date2LB, 0, 3);
		getInfoPart().setHalignment(date2LB, HPos.RIGHT);
		getInfoPart().add(date2DP, 1, 3);

	}

}
