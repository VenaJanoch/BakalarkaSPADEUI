package forms;

import java.time.LocalDate;

import SPADEPAC.Artifact;
import SPADEPAC.ArtifactClass;
import SPADEPAC.Configuration;
import SPADEPAC.WorkUnitPriorityClass;
import abstractform.BasicForm;
import abstractform.DateDescBasicForm;
import graphics.CanvasItem;
import graphics.InfoBoxSegment;
import interfaces.ISegmentForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import services.Alerts;
import services.Control;
import services.DeleteControl;
import services.SegmentType;

public class ArtifactForm extends DateDescBasicForm implements ISegmentForm {

	private Label authorRoleLB;
	private Label mineTypeLB;


	private ComboBox<String> authorRoleCB;
	private ComboBox<ArtifactClass> mineTypeCB;
	private RadioButton existRB;

	private int typeIndex;
	private int authorIndex;

	private Artifact artifact;
	private Configuration conf;
	boolean isNew;

	public ArtifactForm(CanvasItem item, Control control, Artifact artifact, DeleteControl deleteControl, Configuration conf) {
		super(item, control, deleteControl);
		this.artifact = artifact;
		this.conf = conf;
		artifact.setExist(true);
		isNew = true;
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
		LocalDate createdDate = getDateDP().getValue();
		String type = ArtifactClass.values()[typeIndex].name();
		String desc = getDescriptionTF().getText();
		
		setName(actName);
		getCanvasItem().setNameText(actName);
		getControl().getFillForms().fillArtifact(artifact, IDs, desc, actName, createdDate, type,
				authorIndex, x, y, typeIndex, isNew, existRB.isSelected());
		isNew = false;

	}

	@Override
	public void setActionSubmitButton() {
		
			
		if(getFormControl().artifactControl()){
			closeForm();
			close();
		}
	}

	@Override
	public void createForm() {
		
		getDateLB().setText("Created: ");
		
		authorRoleLB = new Label("Author: ");
		authorRoleCB = new ComboBox<String>(getControl().getLists().getRoleObservable());
		authorRoleCB.setVisibleRowCount(5);
		authorRoleCB.getSelectionModel().selectedIndexProperty().addListener(roleListenerAut);

		mineTypeLB = new Label("Mine Type: ");
		mineTypeCB = new ComboBox<ArtifactClass>(FXCollections.observableArrayList(ArtifactClass.values()));
		mineTypeCB.getSelectionModel().selectedIndexProperty().addListener(typeListener);
		mineTypeCB.setVisibleRowCount(5);
		existRB = new RadioButton("Exist");
		existRB.setSelected(true);

		fillInfoPart();
	}


	ChangeListener<Number> roleListenerAut = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			authorIndex = newValue.intValue();

		}
	};

	ChangeListener<Number> typeListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			
			typeIndex = newValue.intValue();

		}
	};

	private void fillInfoPart() {

	
		getInfoPart().add(authorRoleLB, 0, 3);
		getInfoPart().setHalignment(authorRoleLB, HPos.RIGHT);
		getInfoPart().add(authorRoleCB, 1, 3);
		getInfoPart().add(mineTypeLB, 0, 4);
		getInfoPart().setHalignment(mineTypeLB, HPos.RIGHT);
		getInfoPart().add(mineTypeCB, 1, 4);
		getInfoPart().add(existRB, 1, 5);

	}
	
	@Override
	public void deleteItem(int iDs[]) {
	
		deleteControl.deleteArtifact(conf, iDs);
		
	}

	/** Getrs and Setrs ***/


	public ComboBox<String> getAuthorRoleCB() {
		return authorRoleCB;
	}

	public void setAuthorRoleCB(ComboBox<String> authorRoleCB) {
		this.authorRoleCB = authorRoleCB;
	}

	public ComboBox<ArtifactClass> getMineTypeCB() {
		return mineTypeCB;
	}

	public void setMineTypeCB(ComboBox<ArtifactClass> mineTypeCB) {
		this.mineTypeCB = mineTypeCB;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public RadioButton getExistRB() {
		return existRB;
	}

	public void setExistRB(RadioButton existRB) {
		this.existRB = existRB;
	}

	
}
