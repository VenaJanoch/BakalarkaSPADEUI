package Forms;

import Grafika.CanvasItem;
import Grafika.InfoBoxSegment;
import Interfaces.ISegmentForm;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

public class MilestoneForm extends BasicForm implements ISegmentForm {

	private Label criteriaLB;
	private Label descriptionLB;

	private TextField descriptionTF;
	private TextField criteriaTF;

	public MilestoneForm(CanvasItem item) {
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
		criteriaLB = new Label("Criteria: ");
		criteriaTF = new TextField();
		descriptionLB = new Label("Description: ");
		descriptionTF = new TextField();

		fillInfoPart();
	}

	private void fillInfoPart() {

		getInfoPart().add(descriptionLB, 0, 1);
		getInfoPart().setHalignment(descriptionLB, HPos.RIGHT);
		getInfoPart().add(descriptionTF, 1, 1);

		getInfoPart().add(criteriaLB, 0, 4);
		getInfoPart().setHalignment(criteriaLB, HPos.RIGHT);
		getInfoPart().add(criteriaTF, 1, 4);

	}

}
