package Forms;

import java.util.ArrayList;

import Grafika.CanvasItem;
import Grafika.InfoBoxSegment;
import Interfaces.ISegmentForm;
import Obsluha.Control;
import Obsluha.SegmentType;
import SPADEPAC.Artifact;
import SPADEPAC.Change;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

public class ChangeForm extends BasicForm implements ISegmentForm {

	private Label descriptionLB;
	private Label artifactLB;
	private Label changeLB;

	private ComboBox<String> artifactCB;
	private ComboBox<String> changeCB;
	private TextField descriptionTF;

	private int changeIndex;
	private int artifactIndex;
	private Button newArtifactBT;
	
	private boolean newChange;

	public ChangeForm(CanvasItem item, Control control, Change change) {
		super(item, control);
		
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
		setName(getNameTF().getText());
		getCanvasItem().setNameText(getName());
		getControl().getFillForms().fillChange(getCanvasItem().getForm(), getCanvasItem().getIDs()[2], descriptionTF.getText(),
				getName(), newChange, artifactIndex, (int) getCanvasItem().getTranslateX(), (int) getCanvasItem().getTranslateY());

	}

	@Override
	public void setActionSubmitButton() {
		closeForm();
		close();
	}

	@Override
	public void createForm() {
		descriptionLB = new Label("Description: ");
		descriptionTF = new TextField();

		artifactLB = new Label("Artifact: ");
		artifactCB = new ComboBox<String>(getControl().getArtifactObservable());
		artifactCB.setVisibleRowCount(5);
		artifactCB.getSelectionModel().selectedIndexProperty().addListener(artifactListener);

		changeLB = new Label("Change");
		changeCB = new ComboBox<String>(getControl().getChangeObservable());
		changeCB.setVisibleRowCount(5);
		changeCB.getSelectionModel().selectedIndexProperty().addListener(changeListener);

		newArtifactBT = new Button("New");
		newArtifactBT.setOnAction(event -> artifactBTAction());
		fillInfoPart();
	}
	
	private void artifactBTAction(){
		CanvasItem item = new CanvasItem(SegmentType.Artifact, "Name", getControl(), this, true);
		
		getControl().createForm(item, this);
		getControl().getForms().get(item.getIDs()[0]).show();
		
	}
	ChangeListener<Number> changeListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			changeIndex = newValue.intValue();
			fillFormFromList(changeIndex);
		}
	};
	
	ChangeListener<Number> artifactListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			artifactIndex = newValue.intValue();
		}
	};

	private void fillFormFromList(int index) {

		if ((index-1) > 0) {

			getNameTF().setText(getControl().getChangeList().get(index - 1).getName());
			getNameTF().setDisable(true);
			artifactCB.setValue(getControl().getChangeList().get(index - 1).getArtifact().getName());
			artifactCB.setDisable(true);
			newChange = false;

		} else {
			getNameTF().setDisable(false);
			artifactCB.setDisable(false);
			newChange = true;
		}

	}

	private void fillInfoPart() {

		getInfoPart().add(descriptionLB, 0, 1);
		getInfoPart().setHalignment(descriptionLB, HPos.RIGHT);
		getInfoPart().add(descriptionTF, 1, 1);

		getInfoPart().add(artifactLB, 0, 2);
		getInfoPart().setHalignment(artifactLB, HPos.RIGHT);
		getInfoPart().add(artifactCB, 1, 2);
		getInfoPart().add(newArtifactBT, 2, 2);

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

	public TextField getDescriptionTF() {
		return descriptionTF;
	}

	public void setDescriptionTF(TextField descriptionTF) {
		this.descriptionTF = descriptionTF;
	}
	

}
