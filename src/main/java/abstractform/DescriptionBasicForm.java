package abstractform;

import Controllers.FormController;
import graphics.CanvasItem;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.CanvasType;
import services.Control;
import services.DeleteControl;

import java.text.Normalizer;

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
	public DescriptionBasicForm(FormController formController, String name) {

		super(formController, name);
		fillForm();
	}

	/**
	 * Přetížený konstruktor třídy
	 */
	public DescriptionBasicForm(FormController formController) {

		super(formController);
		fillForm();
	}

	/**
	 * Vytvoří vstupní pole pro Description a přidá ho do GridPane
	 */
	private void fillForm() {
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
