package Forms;

import Grafika.CanvasItem;
import Grafika.InfoBoxSegment;
import Interfaces.ISegmentForm;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

public class ActivityForm extends BasicForm implements ISegmentForm {

	private Label workUnitsLB;
	private Label descriptionLB;

	private TextField descriptionTF;
	private TextField workUnitsTF;
	
	public ActivityForm(CanvasItem item) {
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
			workUnitsLB = new Label("Work Units: ");
		workUnitsTF = new TextField();
		descriptionLB = new Label("Description: ");
		descriptionTF = new TextField();
		
		fillInfoPart();
	}


		private void fillInfoPart() {
		
		getInfoPart().add(descriptionLB,0,1); 
		getInfoPart().setHalignment(descriptionLB, HPos.RIGHT);
		getInfoPart().add(descriptionTF,1,1);
		
		
		getInfoPart().add(workUnitsLB,0,4); 
		getInfoPart().setHalignment(workUnitsLB, HPos.RIGHT);
		getInfoPart().add(workUnitsTF,1,4);	
		
	}
	
	

}
