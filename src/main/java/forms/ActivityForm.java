package forms;

import SPADEPAC.Activity;
import SPADEPAC.ObjectFactory;
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

/**
 * Třída představující formuláře segmentu Activity, děděná od
 * DescriptionBasicForm a implementující ISegmentForm
 * 
 * @author Václav Janoch
 *
 */
public class ActivityForm extends DescriptionBasicForm implements ISegmentForm {
	/** Globální proměnné třídy **/
	private Activity activity;

	/**
	 * Konstruktor Třídy Zinicializuje globální proměnné tříd Nastaví reakci na
	 * ukončení formuláře
	 * 
	 * @param item
	 *            CanvasItem
	 * @param control
	 *            Control
	 * @param itemArray
	 * @param activity
	 *            Activity
	 * @param indexForm
	 * @param deleteControl
	 *            DeleteControl
	 */
	public ActivityForm(CanvasItem item, Control control, int[] itemArray, Activity activity, int indexForm,
			DeleteControl deleteControl) {

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
		getControl().getFillForms().fillActivity(activity, IDs, desc, actName, x, y, isNew(), new ObjectFactory());
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

	@Override
	public void deleteItem(int iDs[]) {
		deleteControl.deleteActivity(iDs);

	}

}
