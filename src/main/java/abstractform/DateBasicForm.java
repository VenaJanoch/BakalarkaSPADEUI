package abstractform;

import Controllers.CanvasController;
import Controllers.FormController;
import Controllers.FormDataController;
import graphics.DragAndDropItemPanel;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.geometry.HPos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.SegmentType;

/**
 * Třída odděděná ze třídy DescriptionBasicForm přidávající vstupní pole pro Date
 * @author Václav Janoch
 *
 */
public class DateBasicForm extends BasicForm {

	protected Label dateLB;
	protected DatePicker dateDP;

	/**
	 * Konstruktor třídy
	 */
	public DateBasicForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, CanvasController canvasController,
						 DragAndDropItemPanel dgItemPanel, SegmentType type) {

		super(formController, formDataController, editFormController, deleteFormController, canvasController, dgItemPanel, type);
		createForm();
	}


	/**
	 * Přetížený konstruktor třídy
	 */
	public DateBasicForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {
		super(formController, formDataController, editFormController, deleteFormController, type);
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

	public DatePicker getDateDP() {
		return dateDP;
	}

}
