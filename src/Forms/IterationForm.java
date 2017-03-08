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

public class IterationForm extends BasicForm implements ISegmentForm {

	private Label configurationLB;
	private Label workUnitsLB;
	private Label startDateLB;
	private Label endDateLB;
	private Label descriptionLB;

	private TextField descriptionTF;
	private DatePicker endDateTF;
	private DatePicker startDateTF;
	private TextField configurationTF;
	private TextField workUnitsTF;
	
	public IterationForm(CanvasItem item) {
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
		configurationLB = new Label("Configuration: ");
		configurationTF = new TextField();
		workUnitsLB = new Label("Work Units: ");
		workUnitsTF = new TextField();

		startDateLB = new Label("Start Date: ");
		startDateTF = new DatePicker();

	
		endDateLB = new Label("End Date: ");
		endDateTF = new DatePicker();

		descriptionLB = new Label("Description: ");
		descriptionTF = new TextField();
		
		fillInfoPart();
	}


		private void fillInfoPart() {
		
		getInfoPart().add(descriptionLB,0,1); 
		getInfoPart().setHalignment(descriptionLB, HPos.RIGHT);
		getInfoPart().add(descriptionTF,1,1);
		
		getInfoPart().add(endDateLB,0,2);
		getInfoPart().setHalignment(endDateLB, HPos.RIGHT);
		getInfoPart().add(endDateTF,1,2);
		
		getInfoPart().add(startDateLB,0,3); 
		getInfoPart().setHalignment(startDateLB, HPos.RIGHT);	
		getInfoPart().add(startDateTF,1,3);
		
		getInfoPart().add(workUnitsLB,0,4); 
		getInfoPart().setHalignment(workUnitsLB, HPos.RIGHT);
		getInfoPart().add(workUnitsTF,1,4);
		
		getInfoPart().add(configurationLB,0,5); 
		getInfoPart().setHalignment(configurationLB, HPos.RIGHT);		
		getInfoPart().add(configurationTF,1,5);	
		
		
	}


}
