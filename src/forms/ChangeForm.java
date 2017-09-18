package forms;

import java.util.ArrayList;

import SPADEPAC.Artifact;
import SPADEPAC.Change;
import SPADEPAC.Configuration;
import abstractform.BasicForm;
import abstractform.DescriptionBasicForm;
import graphics.CanvasItem;
import graphics.InfoBoxSegment;
import interfaces.ISegmentForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import services.Alerts;
import services.Constans;
import services.Control;
import services.DeleteControl;
import services.SegmentType;

/**
 * Třída představující formulář pro element Change, odděděná od třídy BasicForm
 * a implementující ISegmentForm
 * 
 * @author Václav Janoch
 *
 */
public class ChangeForm extends DescriptionBasicForm implements ISegmentForm {

	/**
	 * Globální proměnné třídy
	 */
	private ComboBox<String> changeCB;
	private RadioButton existRB;

	private boolean newChange;
	private Change change;
	private Configuration conf;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy Nastaví velikost
	 * formuláře a reakci na uzavření formuláře
	 * 
	 * @param item
	 *            CanvasItem
	 * @param control
	 *            Control
	 * @param change
	 *            Change
	 * @param conf
	 *            Configuration
	 * @param deleteControl
	 *            DeleteControl
	 */
	public ChangeForm(CanvasItem item, Control control, Change change, Configuration conf,
			DeleteControl deleteControl) {
		super(item, control, deleteControl);

		this.newChange = true;
		this.change = change;
		this.conf = conf;
		change.setExist(true);
		setArtifactArray(new ArrayList());

		getMainPanel().setMinSize(Constans.littleformWidth, Constans.littleformHeight);
		getMainPanel().setMaxSize(Constans.littleformWidth, Constans.littleformHeight);

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
		int[] IDs = getCanvasItem().getIDs();
		int x = (int) getCanvasItem().getTranslateX();
		int y = (int) getCanvasItem().getTranslateY();

		setName(actName);
		getCanvasItem().setNameText(actName);
		getControl().getFillForms().fillChange(change, IDs, getDescriptionTF().getText(), actName, newChange, x, y,
				existRB.isSelected(), Control.objF);

		if (!existRB.isSelected()) {
			getCanvasItem().getSegmentInfo().setRectangleColor(Constans.nonExistRectangleBorderColor);
		} else {
			getCanvasItem().getSegmentInfo().setRectangleColor(Constans.rectangleBorderColor);
		}

		newChange = false;
	}

	@Override
	public void setActionSubmitButton() {

		closeForm();
		close();

	}

	@Override
	public void createForm() {

		existRB = new RadioButton("Exist");
		existRB.setSelected(true);
		getInfoPart().add(existRB, 1, 3);

	}

	@Override
	public void deleteItem(int iDs[]) {

		deleteControl.deleteChange(conf, iDs);

	}

	/** Getrs and Setrs ***/

	public ComboBox<String> getChangeCB() {
		return changeCB;
	}

	public void setChangeCB(ComboBox<String> changeCB) {
		this.changeCB = changeCB;
	}

	public boolean isNewChange() {
		return newChange;
	}

	public void setNewChange(boolean newChange) {
		this.newChange = newChange;
	}

	public RadioButton getExistRB() {
		return existRB;
	}

	public void setExistRB(RadioButton existRB) {
		this.existRB = existRB;
	}

}
