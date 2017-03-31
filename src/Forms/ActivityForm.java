package Forms;

import AbstractForm.BasicForm;
import AbstractForm.DescriptionBasicForm;
import Graphics.CanvasItem;
import Graphics.InfoBoxSegment;
import Interfaces.ISegmentForm;
import SPADEPAC.Activity;
import Services.Control;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

public class ActivityForm extends DescriptionBasicForm implements ISegmentForm {


	public ActivityForm(CanvasItem item, Control control, int[] itemArray, Activity activity, int indexForm) {

		super(item, control, itemArray, indexForm);
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

		String actName = getNameTF().getText();
		BasicForm form = getCanvasItem().getForm();
		int[] IDs = getCanvasItem().getIDs();
		int x = (int) getCanvasItem().getTranslateX();
		int y = (int) getCanvasItem().getTranslateY();
		String desc = getDescriptionTF().getText();
		getCanvasItem().setNameText(actName);
		
		getControl().getFillForms().fillActivity(form, IDs[1], desc, actName, x, y);
	}

	@Override
	public void setActionSubmitButton() {
		closeForm();
		close();
	}

	@Override
	public void createForm() {


	}

	private void fillInfoPart() {

	}

}
