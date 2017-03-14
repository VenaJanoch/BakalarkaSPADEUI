package Forms;

import Grafika.CanvasItem;
import Grafika.InfoBoxSegment;
import Interfaces.ISegmentForm;
import Obsluha.Control;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

public class ChangeForm extends BasicForm implements ISegmentForm{

		private Label descriptionLB;
		private Label artifactLB;

		private TextField artifactTF;
		private TextField descriptionTF;
		
		public ChangeForm(CanvasItem item, Control control) {
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
			descriptionLB = new Label("Description: ");
			descriptionTF = new TextField();
			
			artifactLB = new Label("Artifact: ");
			artifactTF = new TextField();

			
			fillInfoPart();
		}

		private void fillInfoPart() {

			getInfoPart().add(descriptionLB, 0, 1);
			getInfoPart().setHalignment(descriptionLB, HPos.RIGHT);
			getInfoPart().add(descriptionTF, 1, 1);

			getInfoPart().add(artifactLB, 0, 1);
			getInfoPart().setHalignment(artifactLB, HPos.RIGHT);
			getInfoPart().add(artifactTF, 1, 1);

		
		}



}
