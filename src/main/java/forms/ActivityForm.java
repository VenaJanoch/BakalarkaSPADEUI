package forms;

import Controllers.CanvasController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.Activity;
import SPADEPAC.ObjectFactory;
import abstractform.BasicForm;
import abstractform.DescriptionBasicForm;
import graphics.CanvasItem;
import graphics.DragAndDropItemPanel;
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
	 */
	public ActivityForm(FormController formController, FormDataController formDataController, CanvasController canvasController, DragAndDropItemPanel dgItemPanel, String name, int indexForm) {

		super(formController, formDataController, canvasController, dgItemPanel, name);
		this. indexForm = indexForm;
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

    public void setDataToForm(String name, String description) {
		getNameTF().setText(name);
		getDescriptionTF().setText(description);
    }

    @Override
	public void closeForm() {

		String actName = getNameTF().getText();
		String desc = getDescriptionTF().getText();
		formDataController.saveDataFromActivityForm(actName, desc, canvasController.getListOfItemOnCanvas(), indexForm);
	}

	@Override
	public void setActionSubmitButton() {
		closeForm();
		close();
	}

	public void createForm() {

	}

	@Override
	public void deleteItem() {
		formController.deleteActivityForm(indexForm);
	}

}
