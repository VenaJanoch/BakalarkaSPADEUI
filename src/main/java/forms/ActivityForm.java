package forms;

import controllers.CanvasController;
import controllers.FormController;
import SPADEPAC.Activity;
import abstractform.DescriptionBasicForm;
import graphics.DragAndDropItemPanel;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import interfaces.ISegmentForm;
import services.*;

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
	public ActivityForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, CanvasController canvasController,
						DragAndDropItemPanel dgItemPanel, SegmentType type, int indexForm) {

		super(formController, formDataController, editFormController, deleteFormController, canvasController, dgItemPanel, type);
		this. indexForm = indexForm;
		/*this.setOnCloseRequest(e -> {

			e.consume();
			int result = Alerts.showSaveSegment();
			if (result == 1) {
				setActionSubmitButton();
			} else if (result == 0) {
				this.close();
			}
		});
		*/
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
		//close();
	}

	public void createForm() {

	}

	@Override
	public void deleteItem() {
		deleteFormController.deleteActivityForm(indexForm);
	}

}
