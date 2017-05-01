package forms;

import SPADEPAC.Activity;
import abstractform.BasicForm;
import abstractform.DescriptionBasicForm;
import graphics.CanvasItem;
import graphics.InfoBoxSegment;
import interfaces.ISegmentForm;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import services.Alerts;
import services.CanvasType;
import services.Control;
import services.DeleteControl;

public class ActivityForm extends DescriptionBasicForm implements ISegmentForm {

	private Activity activity;
	
	public ActivityForm(CanvasItem item, Control control, int[] itemArray, Activity activity, int indexForm, DeleteControl deleteControl) {

		super(item, control, itemArray, indexForm, deleteControl, CanvasType.Activity);
		this.activity = activity;
		setWorkUnitArray(activity.getWorkUnits());
		setNew(true);
		this.setOnCloseRequest(e -> {

			e.consume();
			int result = Alerts.showSaveSegment();
			if (result == 1) {
				setActionSubmitButton();
			} else if (result == 0) {
				this.close();
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
		setName(actName);
		getControl().getFillForms().fillActivity(activity, IDs, desc, actName, x, y, isNew());
		setNew(false);
		
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

	@Override
	public void deleteItem(int iDs[]) {
		deleteControl.deleteActivity(iDs);
		
	}

}
