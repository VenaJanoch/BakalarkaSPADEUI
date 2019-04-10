package abstractform;

import controllers.CanvasController;
import controllers.FormController;
import graphics.panels.DragAndDropItemPanel;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.SegmentType;

/**
 * Třída odděděná ze třídy BasicForm přidávající vstupní pole pro Description
 * 
 * @author Václav Janoch
 *
 */
public class DescriptionBasicForm extends BasicForm {

	private Label descriptionLB;
	private TextField descriptionTF;


	/**
	 * Konstruktor třídy
	 *
	 */
	public DescriptionBasicForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, CanvasController canvasController,
								DragAndDropItemPanel dgItem, SegmentType type) {

		super(formController, formDataController, editFormController, deleteFormController, canvasController, dgItem, type);
		createForm();
	}

	/**
	 * Konstruktor třídy
	 *
	 */
	public DescriptionBasicForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, CanvasController canvasController, SegmentType type) {

		super(formController, formDataController, editFormController, deleteFormController, canvasController, type);
		createForm();
	}

	/**
	 * Přetížený konstruktor třídy
	 */
	public DescriptionBasicForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {

		super(formController, formDataController, editFormController, deleteFormController, type);
		createForm();
	}

	@Override
	void createForm() {
		descriptionLB = new Label("Description: ");
		descriptionTF = new TextField();
		descriptionTF.setId("formDesc");
		getInfoPart().add(descriptionLB, 0, 1);
		getInfoPart().setHalignment(descriptionLB, HPos.RIGHT);
		getInfoPart().add(descriptionTF, 1, 1);
	}
	
	/**
	 * Getrs and Setrs
	 */
	public TextField getDescriptionTF() {
		return descriptionTF;
	}
}
