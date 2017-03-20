package Forms;

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

	private Configuration configuration;

	public ConfigurationForm(CanvasItem item, Control control, int[] itemArray, Configuration conf) {
		super(item, control, itemArray);
		this.configuration = conf;

		setBranchArray(conf.getBranches());
		setChangeArray(conf.getChanges());
		setArtifactArray(conf.getArtifacts());

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
		getControl().fillConfiguration(configuration, getCanvasItem().getIDs()[2], isRelease, createdDP.getValue(),
				getName(), authorIndex);

	}

	@Override
	public void setActionSubmitButton() {
		closeForm();
		close();
	}

	@Override
	public void createForm() {

		createdLB = new Label("Created: ");
		createdDP = new DatePicker();

		isReleaseLB = new Label("Release: ");
		rbNo = new RadioButton("No");
		rbNo.setToggleGroup(group);
		rbYes = new RadioButton("Yes");
		rbYes.setToggleGroup(group);

		authorRoleLB = new Label("Author-role: ");
		authorRoleCB = new ComboBox<String>(getControl().getRoleObservable());
		authorRoleCB.setVisibleRowCount(5);
		authorRoleCB.getSelectionModel().selectedIndexProperty().addListener(roleListenerAut);
		newRoleBT = new Button("New");
		newRoleBT.setOnAction(event -> artifactBTAction());

		fillInfoPart();
	}

	private void artifactBTAction() {
		CanvasItem item = new CanvasItem(SegmentType.Role, "Name", getControl(), this);

		getControl().createForm(item, this);
		getControl().getForms().get(item.getIDs()[0]).show();

	}

	private void fillInfoPart() {

		getInfoPart().add(createdLB, 0, 1);
		getInfoPart().setHalignment(createdLB, HPos.RIGHT);
		getInfoPart().add(createdDP, 1, 1);

		getInfoPart().add(authorRoleLB, 0, 2);
		getInfoPart().setHalignment(authorRoleLB, HPos.RIGHT);
		getInfoPart().add(authorRoleCB, 1, 2);
		getInfoPart().add(newRoleBT, 2, 2);

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

}
