package Forms;

import java.time.LocalDate;

import Grafika.CanvasItem;
import Grafika.InfoBoxSegment;
import Interfaces.ISegmentForm;
import Obsluha.Control;
import Obsluha.SegmentType;
import SPADEPAC.Artifact;
import SPADEPAC.ArtifactClass;
import SPADEPAC.WorkUnitPriorityClass;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

public class ArtifactForm extends BasicForm implements ISegmentForm {

	private Label createdLB;
	private Label authorRoleLB;
	private Label mineTypeLB;
	private Label descriptionLB;

	private TextField descriptionTF;
	private DatePicker createdDP;
	private ComboBox<String> authorRoleCB;
	private ComboBox<ArtifactClass> mineTypeCB;

	private int typeIndex;
	private int authorIndex;

	private Artifact artifact;

	private Button newRoleBT;
	private Button editRoleBT;

	boolean isNew;

	public ArtifactForm(CanvasItem item, Control control, Artifact artifact) {
		super(item, control);
		this.artifact = artifact;
		
		isNew = true;
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
		getControl().getFillForms().fillArtifact(artifact, getCanvasItem().getIDs()[2], descriptionTF.getText(),
				getName(), createdDP.getValue(), ArtifactClass.values()[typeIndex].name(), authorIndex,
				(int) getCanvasItem().getTranslateX(), (int) getCanvasItem().getTranslateY(), typeIndex, isNew);
		isNew = false;

	}

	@Override
	public void setActionSubmitButton() {
		if (getControl().getRoleList().isEmpty()) {
			getAlerts().showNoAuthorAlert();
		} else {
			closeForm();
			close();
		}
	}

	@Override
	public void createForm() {
		createdLB = new Label("Created: ");
		createdDP = new DatePicker();
		createdDP.setValue(LocalDate.now());

		authorRoleLB = new Label("Author: ");
		authorRoleCB = new ComboBox<String>(getControl().getRoleObservable());
		authorRoleCB.setVisibleRowCount(5);
		authorRoleCB.getSelectionModel().selectedIndexProperty().addListener(roleListenerAut);

		mineTypeLB = new Label("Mine Type: ");
		mineTypeCB = new ComboBox<ArtifactClass>(FXCollections.observableArrayList(ArtifactClass.values()));
		mineTypeCB.getSelectionModel().selectedIndexProperty().addListener(typeListener);
		mineTypeCB.setVisibleRowCount(5);

		descriptionLB = new Label("Description: ");
		descriptionTF = new TextField();

		newRoleBT = new Button("New");
		newRoleBT.setOnAction(event -> roleBTAction());

		editRoleBT = new Button("Edit");
		editRoleBT.setOnAction(event -> editRoleBTAction());

		fillInfoPart();
	}

	private void editRoleBTAction() {
		if (getControl().getRoleObservable().isEmpty()) {
			roleBTAction();
		}else{
			getControl().getForms().get(getControl().getRoleFormIndex().get(authorIndex)).show();			
		}
	}

	private void roleBTAction() {
		CanvasItem item = new CanvasItem(SegmentType.Role, "Name", getControl(), this, true);
		getControl().getForms().get(item.getIDs()[0]).show();

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

			System.out.println(newValue.intValue());
			typeIndex = newValue.intValue();

		}
	};

	private void fillInfoPart() {

		getInfoPart().add(descriptionLB, 0, 1);
		getInfoPart().setHalignment(descriptionLB, HPos.RIGHT);
		getInfoPart().add(descriptionTF, 1, 1);

		getInfoPart().add(createdLB, 0, 2);
		getInfoPart().setHalignment(createdLB, HPos.RIGHT);
		getInfoPart().add(createdDP, 1, 2);

		getInfoPart().add(authorRoleLB, 0, 3);
		getInfoPart().setHalignment(authorRoleLB, HPos.RIGHT);
		getInfoPart().add(authorRoleCB, 1, 3);
		getInfoPart().add(editRoleBT, 2, 3);
		getInfoPart().add(newRoleBT, 3, 3);
		getInfoPart().add(mineTypeLB, 0, 4);
		getInfoPart().setHalignment(mineTypeLB, HPos.RIGHT);
		getInfoPart().add(mineTypeCB, 1, 4);

	}

	/** Getrs and Setrs ***/

	public TextField getDescriptionTF() {
		return descriptionTF;
	}

	public void setDescriptionTF(TextField descriptionTF) {
		this.descriptionTF = descriptionTF;
	}

	public DatePicker getCreatedDP() {
		return createdDP;
	}

	public void setCreatedDP(DatePicker createdDP) {
		this.createdDP = createdDP;
	}

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

}
