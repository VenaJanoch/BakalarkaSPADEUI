package Forms;

import Grafika.InfoBoxSegment;
import Interfaces.ISegmentForm;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.WindowEvent;

public class PhaseForm extends BasicForm implements ISegmentForm {

	private Label configurationLB;
	private Label workUnitsLB;
	private Label milestonesLB;
	private Label endDateLB;
	private Label descriptionLB;

	private TextField descriptionTF;
	private DatePicker endDate;
	private TextField configurationTF;
	private TextField workUnitsTF;
	private TextField milestonesTF;

	public PhaseForm(InfoBoxSegment infoBox) {
		super(infoBox);

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

		getInfoBox().setNameText(getNameTF().getText());

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

		milestonesLB = new Label("Milestones: ");
		milestonesTF = new TextField();

		endDateLB = new Label("End Date: ");
		endDate = new DatePicker();

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
		getInfoPart().add(endDate,1,2);
		
		getInfoPart().add(milestonesLB,0,3); 
		getInfoPart().setHalignment(milestonesLB, HPos.RIGHT);	
		getInfoPart().add(milestonesTF,1,3);
		
		getInfoPart().add(workUnitsLB,0,4); 
		getInfoPart().setHalignment(workUnitsLB, HPos.RIGHT);
		getInfoPart().add(workUnitsTF,1,4);
		
		getInfoPart().add(configurationLB,0,5); 
		getInfoPart().setHalignment(configurationLB, HPos.RIGHT);		
		getInfoPart().add(configurationTF,1,5);	
		
		
	}

}
