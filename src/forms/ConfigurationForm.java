package forms;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.controlsfx.control.CheckComboBox;

import SPADEPAC.Configuration;
import abstractform.BasicForm;
import graphics.CanvasItem;
import graphics.InfoBoxSegment;
import interfaces.ISegmentForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.shape.Line;
import javafx.stage.WindowEvent;
import services.Alerts;
import services.CanvasType;
import services.Constans;
import services.Control;
import services.DeleteControl;
import services.SegmentType;

public class ConfigurationForm extends BasicForm implements ISegmentForm {

	private boolean isRelease;

	private Button addTag;
	private boolean isNew;

	final ToggleGroup group = new ToggleGroup();

	private Label createdLB;
	private Label isReleaseLB;
	private Label authorRoleLB;
	private Label cprLB;
	private Label branchLB;

	private RadioButton rbYes;
	private RadioButton rbNo;
	private DatePicker createdDP;
	private CheckComboBox<String> branchCB;
	private ComboBox<String> authorRoleCB;
	private CheckComboBox<String> cprCB;

	private int authorIndex;

	private ObservableList<String> branchArray;
	private List<Integer> branchIndex;
	private List<Integer> cprIndex;

	private Configuration configuration;
	private TagForm tagForm;

	public ConfigurationForm(CanvasItem item, Control control, int[] itemArray, Configuration conf, int indexForm,
			DeleteControl deleteControl) {
		super(item, control, itemArray, indexForm, deleteControl, CanvasType.Configuration);
		this.configuration = conf;
		setConfigArray(conf);
		this.tagForm = new TagForm(conf, control, deleteControl);
		isNew = true;
		isRelease = true;

		branchIndex = conf.getBranchesIndexs();
		cprIndex = conf.getCPRsIndexs();

		setConfig(conf);
		setBranchArray(conf.getBranchesIndexs());
		setChangeArray(conf.getChangesIndexs());
		setArtifactArray(conf.getArtifactsIndexs());
		setTagArray(conf.getTags());

		setRoleArray(new ArrayList<>());
		getRoleArray().add(conf.getAuthorIndex());

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
		LocalDate createDate = createdDP.getValue();

		setName(actName);
		getCanvasItem().setNameText(actName);

		getControl().getFillForms().fillConfiguration(configuration, IDs, isRelease, createDate, actName, authorIndex,
				isNew);

		getSubmitButton().setText("Save");

		isNew = false;
	}

	@Override
	public void setActionSubmitButton() {

		if (getFormControl().configControl()) {

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
		authorRoleCB = new ComboBox<String>(getControl().getLists().getRoleObservable());
		authorRoleCB.setVisibleRowCount(5);
		authorRoleCB.getSelectionModel().selectedIndexProperty().addListener(roleListenerAut);
		
		cprLB = new Label("Conf-Person: ");
		cprCB = new CheckComboBox<String>(getControl().getLists().getCPRObservable());
		cprCB.getCheckModel().getCheckedItems().addListener(cprListener);
		cprCB.setMaxWidth(Constans.checkComboBox);
		
		branchLB = new Label("Branches");
		branchCB = new CheckComboBox<String>(getControl().getLists().getBranchObservable());
		branchCB.setMaxWidth(Constans.checkComboBox);
		branchCB.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
		
			public void onChanged(ListChangeListener.Change<? extends String> c) {
				branchIndex.addAll(branchCB.getCheckModel().getCheckedIndices());
				branchArray = branchCB.getCheckModel().getCheckedItems();

			}
		});

		addTag = new Button("Add Tag");
		addTag.setOnAction(event -> tagForm.show());

		fillInfoPart();
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

		getInfoPart().add(cprLB, 0, 4);
		getInfoPart().setHalignment(cprLB, HPos.RIGHT);
		getInfoPart().add(cprCB, 1, 4);

		getInfoPart().add(branchLB, 0, 5);
		getInfoPart().setHalignment(branchLB, HPos.RIGHT);
		getInfoPart().add(branchCB, 1, 5);

		getInfoPart().add(addTag, 1, 6);

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

	ListChangeListener<String> cprListener = new ListChangeListener<String>() {

		public void onChanged(ListChangeListener.Change<? extends String> c) {
			cprIndex.addAll(cprCB.getCheckModel().getCheckedIndices());

		}
	};

	/*** Getrs and Setrs ***/

	public Configuration getConfiguration() {
		return configuration;
	}

	public TagForm getTagForm() {
		return tagForm;
	}

	public void setTagForm(TagForm tagForm) {
		this.tagForm = tagForm;
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
