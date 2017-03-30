package Forms;

import java.time.LocalDate;
import java.util.ArrayList;

import Grafika.CanvasItem;
import Grafika.InfoBoxSegment;
import Interfaces.ISegmentForm;
import Obsluha.Control;
import Obsluha.SegmentType;
import SPADEPAC.Configuration;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.WindowEvent;

public class ConfigurationForm extends BasicForm implements ISegmentForm {

	private boolean isRelease;

	private Label isMainLB;

	private boolean isNew;

	final ToggleGroup group = new ToggleGroup();

	private Label createdLB;
	private Label isReleaseLB;
	private Label authorRoleLB;

	private RadioButton rbYes;
	private RadioButton rbNo;
	private DatePicker createdDP;
	private ComboBox<String> authorRoleCB;

	private int authorIndex;
	private Button newRoleBT;
	private Button editRoleBT;

	private Configuration configuration;

	public ConfigurationForm(CanvasItem item, Control control, int[] itemArray, Configuration conf, int indexForm) {
		super(item, control, itemArray, indexForm);
		this.configuration = conf;

		isNew = true;
		setConfig(conf);
		setBranchArray(conf.getBranches());
		setChangeArray(conf.getChanges());
		setArtifactArray(conf.getArtifacts());
		setTagArray(conf.getTags());

		setRoleArray(new ArrayList<>());
		getRoleArray().add(conf.getAuthor());

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
		getControl().getFillForms().fillConfiguration(configuration, getCanvasItem().getIDs()[1], isRelease,
				createdDP.getValue(), getName(), authorIndex, (int) getCanvasItem().getTranslateX(),
				(int) getCanvasItem().getTranslateY(), isNew);

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

		isReleaseLB = new Label("Release: ");
		rbNo = new RadioButton("No");
		rbNo.setToggleGroup(group);
		rbYes = new RadioButton("Yes");
		rbYes.setToggleGroup(group);
		rbYes.setSelected(true);

		authorRoleLB = new Label("Author-role: ");
		authorRoleCB = new ComboBox<String>(getControl().getRoleObservable());
		authorRoleCB.setVisibleRowCount(5);
		authorRoleCB.getSelectionModel().selectedIndexProperty().addListener(roleListenerAut);
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

	private void fillInfoPart() {

		getInfoPart().add(createdLB, 0, 1);
		getInfoPart().setHalignment(createdLB, HPos.RIGHT);
		getInfoPart().add(createdDP, 1, 1);

		getInfoPart().add(isReleaseLB, 0, 2);
		getInfoPart().setHalignment(isReleaseLB, HPos.RIGHT);
		getInfoPart().add(rbYes, 1, 2);
		getInfoPart().add(rbNo, 2, 2);

		getInfoPart().add(authorRoleLB, 0, 3);
		getInfoPart().setHalignment(authorRoleLB, HPos.RIGHT);
		getInfoPart().add(authorRoleCB, 1, 3);
		getInfoPart().add(newRoleBT, 3, 3);
		getInfoPart().add(editRoleBT, 2, 3);

		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				RadioButton chk = (RadioButton) newValue.getToggleGroup().getSelectedToggle();

				if (chk.getText().contains("Yes")) {
					isRelease = true;
				} else {
					isRelease = false;
				}

			}
		});

	}

	ChangeListener<Number> roleListenerAut = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			authorIndex = newValue.intValue();

		}
	};

	/*** Getrs and Setrs ***/

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public boolean isRelease() {
		return isRelease;
	}

	public void setRelease(boolean isRelease) {
		this.isRelease = isRelease;
	}

	public RadioButton getRbYes() {
		return rbYes;
	}

	public void setRbYes(RadioButton rbYes) {
		this.rbYes = rbYes;
	}

	public RadioButton getRbNo() {
		return rbNo;
	}

	public void setRbNo(RadioButton rbNo) {
		this.rbNo = rbNo;
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

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	
	

}
