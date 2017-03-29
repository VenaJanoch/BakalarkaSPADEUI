package Forms;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import Grafika.CanvasItem;
import Grafika.DragAndDropCanvas;
import Interfaces.ISegmentForm;
import Obsluha.Control;
import SPADEPAC.Activity;
import SPADEPAC.Iteration;
import SPADEPAC.Phase;
import SPADEPAC.Project;
import SPADEPAC.WorkUnit;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

public class ProjectForm extends BasicForm implements ISegmentForm {

	private Label descriptionLB;
	private Label startDateLB;
	private Label endDateLB;

	private TextField descriptionTF;
	private DatePicker startDateDP;
	private DatePicker endDateDP;

	
	
	public ProjectForm(Control control, Project project, DragAndDropCanvas canvas) {
		super(control);
		
		setPhaseArray(project.getPhases());
		setIterationArray(project.getIterations());
		setActivityArray(project.getActivities());
		setWorkUnitArray(project.getWorkUnits());
		
		setCanvas(canvas);
		
	
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
		
			
		getControl().getFillForms().fillProject(descriptionTF.getText(), getNameTF().getText(),startDateDP.getValue(),endDateDP.getValue());

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
		startDateLB = new Label("Start date: ");
		startDateDP = new DatePicker();
		startDateDP.setValue(LocalDate.now());
		endDateDP = new DatePicker();
		endDateDP.setValue(LocalDate.now());
		endDateLB = new Label("End date: ");

		fillInfoPart();
	}

	private void fillInfoPart() {

		getInfoPart().add(descriptionLB, 0, 1);
		getInfoPart().setHalignment(descriptionLB, HPos.RIGHT);
		getInfoPart().add(descriptionTF, 1, 1);

		getInfoPart().add(startDateLB, 0, 2);
		getInfoPart().setHalignment(startDateLB, HPos.RIGHT);
		getInfoPart().add(startDateDP, 1, 2);

		getInfoPart().add(endDateLB, 0, 3);
		getInfoPart().setHalignment(endDateLB, HPos.RIGHT);
		getInfoPart().add(endDateDP, 1, 3);

	}
	
	

	/*** Getrs and setrs ***/

	public TextField getDescriptionTF() {
		return descriptionTF;
	}

	public void setDescriptionTF(TextField descriptionTF) {
		this.descriptionTF = descriptionTF;
	}

	public DatePicker getStartDateDP() {
		return startDateDP;
	}

	public void setStartDateDP(DatePicker startDateDP) {
		this.startDateDP = startDateDP;
	}

	public DatePicker getEndDateDP() {
		return endDateDP;
	}

	public void setEndDateDP(DatePicker endDateDP) {
		this.endDateDP = endDateDP;
	}

	

	
	

}
