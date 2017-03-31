package Forms;

import AbstractForm.BasicForm;
import Graphics.CanvasItem;
import Graphics.InfoBoxSegment;
import Interfaces.ISegmentForm;
import SPADEPAC.Milestone;
import Services.Control;
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

	public MilestoneForm(CanvasItem item, Control control, Milestone milestone) {
		super(item, control);
		setCriterionnArray(milestone.getCriteria());
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
