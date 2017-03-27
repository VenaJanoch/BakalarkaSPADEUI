package Forms;

import Grafika.CanvasItem;
import Grafika.InfoBoxSegment;
import Interfaces.ISegmentForm;
import Obsluha.Control;
import SPADEPAC.Activity;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

public class ActivityForm extends BasicForm implements ISegmentForm {

	private Label descriptionLB;

	private TextField descriptionTF;

	public ActivityForm(CanvasItem item, Control control, int[] itemArray, Activity activity) {

		super(item, control, itemArray);
		setWorkUnitArray(activity.getWorkUnits());
		this.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				closeForm();
			}
		});

		getSubmitButton().setOnAction(event -> setActionSubmitButton());
		createForm();

	}

	@Override
	public void closeForm() {

		getCanvasItem().setNameText(getNameTF().getText());
		getControl().getFillForms().fillActivity(getCanvasItem().getForm(), getCanvasItem().getIDs()[1], descriptionTF.getText(),
				getNameTF().getText(), (int) getCanvasItem().getTranslateX(), (int) getCanvasItem().getTranslateY());
	}

	@Override
	public void setActionSubmitButton() {
		closeForm();
		close();
	}

	@Override
	public void createForm() {
		descriptionLB = new Label("Description: ");
		descriptionTF = new TextField();

		fillInfoPart();
	}

	private void fillInfoPart() {

		getInfoPart().add(descriptionLB, 0, 1);
		getInfoPart().setHalignment(descriptionLB, HPos.RIGHT);
		getInfoPart().add(descriptionTF, 1, 1);

	}


	/*** Getrs and Setrs ****/
	
	public TextField getDescriptionTF() {
		return descriptionTF;
	}

	public void setDescriptionTF(TextField descriptionTF) {
		this.descriptionTF = descriptionTF;
	}

	
	
}
