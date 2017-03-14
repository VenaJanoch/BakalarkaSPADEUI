package Forms;

import Grafika.CanvasItem;
import Grafika.InfoBoxSegment;
import Interfaces.ISegmentForm;
import Obsluha.Control;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.WindowEvent;

public class ConfigurationForm extends BasicForm implements ISegmentForm {

	private Label isMainLB;

	final ToggleGroup group = new ToggleGroup();

	private Label createdLB;
	private Label artifactsLB;
	private Label changesLB;
	private Label isReleaseLB;
	private Label tagsLB;
	private Label branchesLB;
	private Label authorRoleLB;

	private RadioButton rbYes;
	private RadioButton rbNo;
	private DatePicker createdDP;
	private TextField artifactsTF;
	private TextField changesTF;
	private TextField tagsTF;
	private TextField branchesTF;
	private TextField authorRoleTF;

	public ConfigurationForm(CanvasItem item, Control control) {
		super(item, control);

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

		getCanvasItem().setNameText(getNameTF().getText());

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

		artifactsLB = new Label("Artifact: ");
		artifactsTF = new TextField();

		changesLB = new Label("Changes: ");
		changesTF = new TextField();

		isReleaseLB = new Label("Release: ");
		rbNo = new RadioButton("No");
		rbNo.setToggleGroup(group);
		rbYes = new RadioButton("Yes");
		rbYes.setToggleGroup(group);

		tagsLB = new Label("Tags: ");
		tagsTF = new TextField();

		branchesLB = new Label("Branches: ");
		branchesTF = new TextField();

		authorRoleLB = new Label("Author-role: ");
		authorRoleTF = new TextField();

		fillInfoPart();
	}

	private void fillInfoPart() {

		getInfoPart().add(createdLB, 0, 1);
		getInfoPart().setHalignment(createdLB, HPos.RIGHT);
		getInfoPart().add(createdDP, 1, 1);

		getInfoPart().add(artifactsLB, 0, 2);
		getInfoPart().setHalignment(artifactsLB, HPos.RIGHT);
		getInfoPart().add(artifactsTF, 1, 2);

		getInfoPart().add(changesLB, 0, 3);
		getInfoPart().setHalignment(changesLB, HPos.RIGHT);
		getInfoPart().add(changesTF, 1, 3);

		getInfoPart().add(isReleaseLB, 0, 4);
		getInfoPart().setHalignment(isReleaseLB, HPos.RIGHT);
		getInfoPart().add(rbYes, 1, 4);
		getInfoPart().add(rbNo, 2, 4);

		getInfoPart().add(tagsLB, 0, 5);
		getInfoPart().setHalignment(tagsLB, HPos.RIGHT);
		getInfoPart().add(tagsTF, 1, 5);

		getInfoPart().add(branchesLB, 0, 6);
		getInfoPart().setHalignment(branchesLB, HPos.RIGHT);
		getInfoPart().add(branchesTF, 1, 6);

		getInfoPart().add(authorRoleLB, 0, 7);
		getInfoPart().setHalignment(authorRoleLB, HPos.RIGHT);
		getInfoPart().add(authorRoleTF, 1, 7);

	}

}
