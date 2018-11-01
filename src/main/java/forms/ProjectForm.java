package forms;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import Controllers.FormController;
import SPADEPAC.Activity;
import SPADEPAC.Iteration;
import SPADEPAC.Phase;
import SPADEPAC.Project;
import SPADEPAC.WorkUnit;
import abstractform.BasicForm;
import abstractform.Date2DescBasicForm;
import graphics.CanvasItem;
import graphics.DragAndDropCanvas;
import interfaces.ISegmentForm;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import services.Alerts;
import services.Constans;
import services.Control;
import services.SegmentType;

/**
 * Třída představující formulář pro kořenový Element Project, odděděná od třídy
 * Dete2DescBasicForm a implementující ISegmentForm
 * 
 * @author Václav Janoch
 *
 */
public class ProjectForm extends Date2DescBasicForm {

	/**
	 * Konstruktor třídy Nastaví velikost okna, reakci na uzavření okna
	 * formuláře a zinicializuje globální proměnné tříd
	 * 

	 *            Project
	 * @param canvas
	 *            DragAndDropCanvas
	 */
	public ProjectForm(DragAndDropCanvas canvas, FormController formController) {
		super(formController);

		setCanvas(canvas);

		getMainPanel().setMinSize(Constans.littleformWidth, Constans.littleformHeight);
		getMainPanel().setMaxSize(Constans.littleformWidth, Constans.littleformHeight);

	//	setCanvasItem(new CanvasItem(SegmentType.Project, "", control, this, 0, 0, 0, control.getContexMenu(),
	//			control.getLinkControl(), control.getChooseCanvas()));

		this.setOnCloseRequest(e -> {

			e.consume();
			int result = Alerts.showSaveSegment();
			if (result == 1) {
				setActionSubmitButton();
			} else if (result == 0) {
				this.close();
			}
		});

		getSubmitButton().setOnAction(event -> setActionSubmitButton());
		createForm();
		getFormName().setText(getTitle());
	}

	@Override
	public void closeForm() {

		String actName = getNameTF().getText();
		LocalDate endDate = getDate2DP().getValue();
		LocalDate startDate = getDateDP().getValue();
		String desc = getDescriptionTF().getText();
		//setName(actName);

		//getControl().getFillForms().fillProject(desc, actName, startDate, endDate);

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

	@Override
	public void deleteItem(int[] IDs) {

	}

}
