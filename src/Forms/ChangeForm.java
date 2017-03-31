package Forms;

import java.util.ArrayList;

import AbstractForm.BasicForm;
import AbstractForm.DescriptionBasicForm;
import Graphics.CanvasItem;
import Graphics.InfoBoxSegment;
import Interfaces.ISegmentForm;
import SPADEPAC.Artifact;
import SPADEPAC.Change;
import Services.Control;
import Services.SegmentType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

public class ChangeForm extends DescriptionBasicForm implements ISegmentForm {

	private Label artifactLB;

	private ComboBox<String> artifactCB;
	private ComboBox<String> changeCB;

	private int artifactIndex;

	private boolean newChange;
	private Change change;

	public ChangeForm(CanvasItem item, Control control, Change change) {
		super(item, control);

		this.newChange = true;
		this.change = change;
		setArtifactArray(new ArrayList());
		getArtifactArray().add(change.getArtifact());

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
		int[] IDs = getCanvasItem().getIDs();
		int x = (int) getCanvasItem().getTranslateX();
		int y = (int) getCanvasItem().getTranslateY();

		setName(actName);
		getCanvasItem().setNameText(actName);
		getControl().getFillForms().fillChange(change, IDs, getDescriptionTF().getText(), actName, newChange,
				artifactIndex, x, y);

		newChange = false;
	}

	@Override
	public void setActionSubmitButton() {
		if (getControl().getArtifactList().isEmpty()) {
			getAlerts().showNoArtifactAlert();
		} else {
			closeForm();
			close();
		}
	}

	@Override
	public void createForm() {

		artifactLB = new Label("Artifact: ");
		artifactCB = new ComboBox<String>(getControl().getArtifactObservable());
		artifactCB.setVisibleRowCount(5);
		artifactCB.getSelectionModel().selectedIndexProperty().addListener(artifactListener);

		fillInfoPart();
	}

	ChangeListener<Number> artifactListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			artifactIndex = newValue.intValue();
		}
	};

	private void fillInfoPart() {

		getInfoPart().add(artifactLB, 0, 2);
		getInfoPart().setHalignment(artifactLB, HPos.RIGHT);
		getInfoPart().add(artifactCB, 1, 2);

	}

	/** Getrs and Setrs ***/

	public ComboBox<String> getArtifactCB() {
		return artifactCB;
	}

	public void setArtifactCB(ComboBox<String> artifactCB) {
		this.artifactCB = artifactCB;
	}

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

}
