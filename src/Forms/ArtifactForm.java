package Forms;

import javax.swing.text.html.MinimalHTMLWriter;

import Grafika.CanvasItem;
import Grafika.InfoBoxSegment;
import Interfaces.ISegmentForm;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
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
	private TextField authorRoleTF;
	private TextField mineTypeTF;

	public ArtifactForm(CanvasItem item) {
		super(item);
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
		
		authorRoleLB = new Label("Author: ");
		authorRoleTF = new TextField();

		mineTypeLB = new Label("Mine Type: ");
		mineTypeTF = new TextField();

		descriptionLB = new Label("Description: ");
		descriptionTF = new TextField();

		fillInfoPart();
	}

	private void fillInfoPart() {

		getInfoPart().add(descriptionLB, 0, 1);
		getInfoPart().setHalignment(descriptionLB, HPos.RIGHT);
		getInfoPart().add(descriptionTF, 1, 1);

		getInfoPart().add(createdLB, 0, 2);
		getInfoPart().setHalignment(createdLB, HPos.RIGHT);
		getInfoPart().add(createdDP, 1, 2);

		getInfoPart().add(authorRoleLB, 0, 3);
		getInfoPart().setHalignment(authorRoleLB, HPos.RIGHT);
		getInfoPart().add(authorRoleTF, 1, 3);

		getInfoPart().add(mineTypeLB, 0, 4);
		getInfoPart().setHalignment(mineTypeLB, HPos.RIGHT);
		getInfoPart().add(mineTypeTF, 1, 4);

		
	}

}
