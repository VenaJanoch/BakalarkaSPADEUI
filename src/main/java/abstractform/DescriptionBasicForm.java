package abstractform;

import graphics.CanvasItem;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.CanvasType;
import services.Control;
import services.DeleteControl;

/**
 * Třída odděděná ze třídy BasicForm přidávající vstupní pole pro Description
 * 
 * @author Václav Janoch
 *
 */
public class DescriptionBasicForm extends BasicForm {

	private String description;
	private Label descriptionLB;
	private TextField descriptionTF;

	/**
	 * Konstruktor třídy
	 * 
	 * @param item
	 * @param control
	 * @param itemArray
	 * @param indexForm
	 */
	public DescriptionBasicForm(CanvasItem item, Control control, int[] itemArray, int indexForm,
			DeleteControl deleteControl, CanvasType canvasType) {

		super(item, control, itemArray, indexForm, deleteControl, canvasType);
		fillForm();
	}

	/**
	 * Přetížený konstruktor třídy
	 * 
	 * @param item
	 *            CanvasItem
	 * @param control
	 *            Control
	 */
	public DescriptionBasicForm(CanvasItem item, Control control, DeleteControl deleteControl) {

		super(item, control, deleteControl);
		fillForm();
	}

	/**
	 * Přetížený konstruktor třídy
	 * 
	 * @param control
	 *            Control
	 */
	public DescriptionBasicForm(Control control) {
		super(control);
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
	public Label getDescriptionLB() {
		return descriptionLB;
	}

	public void setDescriptionLB(Label descriptionLB) {
		this.descriptionLB = descriptionLB;
	}

	public TextField getDescriptionTF() {
		return descriptionTF;
	}

	public void setDescriptionTF(TextField descriptionTF) {
		this.descriptionTF = descriptionTF;
	}

}
