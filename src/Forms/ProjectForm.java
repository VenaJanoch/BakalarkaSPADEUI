package Forms;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import AbstractForm.BasicForm;
import AbstractForm.Date2DescBasicForm;
import Graphics.CanvasItem;
import Graphics.DragAndDropCanvas;
import Interfaces.ISegmentForm;
import SPADEPAC.Activity;
import SPADEPAC.Iteration;
import SPADEPAC.Phase;
import SPADEPAC.Project;
import SPADEPAC.WorkUnit;
import Services.Control;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

public class ProjectForm extends Date2DescBasicForm implements ISegmentForm {

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

		String actName = getNameTF().getText();
		LocalDate endDate = getDate2DP().getValue();
		LocalDate startDate = getDateDP().getValue();
		String desc = getDescriptionTF().getText();
		setName(actName);

		getControl().getFillForms().fillProject(desc, actName, startDate, endDate);

	}

	@Override
	public void setActionSubmitButton() {
		closeForm();
		close();
	}

	@Override
	public void createForm() {

		getDateLB().setText("Start-Date");
		getDate2LB().setText("End-Date");

	}

	/*** Getrs and setrs ***/

}
