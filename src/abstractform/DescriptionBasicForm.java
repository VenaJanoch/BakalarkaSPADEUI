package abstractform;

import graphics.CanvasItem;
import graphics.DragAndDropCanvas;
import graphics.DragAndDropItem;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import services.Alerts;
import services.CanvasType;
import services.Control;
import services.DeleteControl;
import services.IdentificatorCreater;

public class DescriptionBasicForm extends BasicForm {

	private String description;
	private Label descriptionLB;
	private TextField descriptionTF;

	public DescriptionBasicForm(CanvasItem item, Control control, int[] itemArray, int indexForm,
			DeleteControl deleteControl, CanvasType canvasType) {

		super(item, control, itemArray, indexForm, deleteControl, canvasType);
		fillForm();
	}

	public DescriptionBasicForm(CanvasItem item, Control control, DeleteControl deleteControl) {

		super(item, control, deleteControl);
		fillForm();
	}

	public DescriptionBasicForm(Control control) {
		super(control);
		fillForm();

	}

	private void fillForm() {
		descriptionLB = new Label("Description: ");
		descriptionTF = new TextField();
		descriptionTF.setId("formDesc");
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
