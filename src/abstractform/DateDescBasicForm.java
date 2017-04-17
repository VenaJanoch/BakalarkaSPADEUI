/**
 * 
 */
package abstractform;

import java.time.LocalDate;

import graphics.CanvasItem;
import javafx.geometry.HPos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import services.CanvasType;
import services.Control;
import services.DeleteControl;

/**
 * @author V�clav
 *
 */
public class DateDescBasicForm extends DescriptionBasicForm {

	private Label dateLB;
	private DatePicker dateDP;

	/**
	 * @param item
	 * @param control
	 * @param itemArray
	 * @param indexForm
	 */
	public DateDescBasicForm(CanvasItem item, Control control, int[] itemArray, int indexForm, DeleteControl deleteControl, CanvasType canvasType) {

		super(item, control, itemArray, indexForm, deleteControl, canvasType);
		fillForm();
	}

	/**
	 * @param item
	 * @param control
	 */
	public DateDescBasicForm(CanvasItem item, Control control, DeleteControl deleteControl) {
		super(item, control, deleteControl);
		fillForm();
	}

	/**
	 * @param control
	 */
	public DateDescBasicForm(Control control) {
		super(control);
		fillForm();
	}

	private void fillForm() {

		dateLB = new Label("Created: ");
		dateDP = new DatePicker();
		dateDP.setValue(LocalDate.now());

		getInfoPart().add(dateLB, 0, 2);
		getInfoPart().setHalignment(dateLB, HPos.RIGHT);
		getInfoPart().add(dateDP, 1, 2);

	}

	public Label getDateLB() {
		return dateLB;
	}

	public void setDateLB(Label dateLB) {
		this.dateLB = dateLB;
	}

	public DatePicker getDateDP() {
		return dateDP;
	}

	public void setDateDP(DatePicker dateDP) {
		this.dateDP = dateDP;
	}

}
