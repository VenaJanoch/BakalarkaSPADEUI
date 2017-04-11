package forms;

import java.util.ArrayList;

import SPADEPAC.Artifact;
import SPADEPAC.Change;
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
import services.Control;
import services.SegmentType;

public class ChangeForm extends DescriptionBasicForm implements ISegmentForm {

	private ComboBox<String> changeCB;
	private RadioButton existRB;
	


	private boolean newChange;
	private Change change;

	public ChangeForm(CanvasItem item, Control control, Change change) {
		super(item, control);

		this.newChange = true;
		this.change = change;
		change.setExist(true);
		setArtifactArray(new ArrayList());
		getArtifactArray().add(change.getArtifactIndex());

		this.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				Alerts.showSaveSegment();
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
				existRB.isSelected());

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
