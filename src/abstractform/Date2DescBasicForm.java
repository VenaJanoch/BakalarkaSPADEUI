/**
 * 
 */
package abstractform;

import java.time.LocalDate;

import graphics.CanvasItem;
import javafx.geometry.HPos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import services.Control;
import services.DeleteControl;

/**
 * @author Václav
 *
 */
public class Date2DescBasicForm extends DateDescBasicForm {

	private Label date2LB;
	private DatePicker date2DP;

	/**
	 * @param item
	 * @param control
	 * @param itemArray
	 * @param indexForm
	 */
	public Date2DescBasicForm(CanvasItem item, Control control, int[] itemArray, int indexForm, DeleteControl deleteControl) {
		super(item, control, itemArray, indexForm, deleteControl);
		fillForm();
	}

	/**
	 * @param item
	 * @param control
	 */
	public Date2DescBasicForm(CanvasItem item, Control control, DeleteControl deleteControl) {
		super(item, control, deleteControl);
		fillForm();
	}

	/**
	 * @param control
	 */
	public Date2DescBasicForm(Control control) {
		super(control);
		fillForm();
	}

	private void fillForm() {

		date2LB = new Label("Created: ");
		date2DP = new DatePicker();
		date2DP.setValue(LocalDate.now());

		getInfoPart().add(date2LB, 0, 3);
		getInfoPart().setHalignment(date2LB, HPos.RIGHT);
		getInfoPart().add(date2DP, 1, 3);

	}

	public Label getDate2LB() {
		return date2LB;
	}

	public void setDate2LB(Label date2lb) {
		date2LB = date2lb;
	}

	public DatePicker getDate2DP() {
		return date2DP;
	}

	public void setDate2DP(DatePicker date2dp) {
		date2DP = date2dp;
	}

}
