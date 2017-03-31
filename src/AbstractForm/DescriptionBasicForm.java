package AbstractForm;

import Graphics.CanvasItem;
import Graphics.DragAndDropCanvas;
import Graphics.DragAndDropItem;
import Services.Alerts;
import Services.Control;
import Services.IdentificatorCreater;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class DescriptionBasicForm extends BasicForm {

	private String description;
	private Label descriptionLB;
	private TextField descriptionTF;

	public DescriptionBasicForm(CanvasItem item, Control control, int[] itemArray, int indexForm) {

		super(item, control, itemArray, indexForm);
		fillForm();
	}

	public DescriptionBasicForm(CanvasItem item, Control control) {

		super(item, control);
		fillForm();
	}

	public DescriptionBasicForm(Control control) {
		super(control);
		fillForm();

	}

	private void fillForm() {
		descriptionLB = new Label("Description: ");
		descriptionTF = new TextField();

		getInfoPart().add(descriptionLB, 0, 1);
		getInfoPart().setHalignment(descriptionLB, HPos.RIGHT);
		getInfoPart().add(descriptionTF, 1, 1);

	}

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
