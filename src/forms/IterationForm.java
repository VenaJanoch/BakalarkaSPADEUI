package forms;

import java.time.LocalDate;

import SPADEPAC.Iteration;
import abstractform.BasicForm;
import abstractform.Date2DescBasicForm;
import graphics.CanvasItem;
import graphics.InfoBoxSegment;
import interfaces.ISegmentForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import services.Control;
import services.SegmentType;

public class IterationForm extends Date2DescBasicForm implements ISegmentForm {

	private Label configLB;

	private ChoiceBox<String> configCB;

	private int chooseConfigID;

	public IterationForm(CanvasItem item, Control control, int[] itemArray, Iteration iteration, int indexForm) {
		super(item, control, itemArray, indexForm);
		setWorkUnitArray(iteration.getWorkUnits());


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
		BasicForm form = getCanvasItem().getForm();
		int[] IDs = getCanvasItem().getIDs();
		int x = (int) getCanvasItem().getTranslateX();
		int y = (int) getCanvasItem().getTranslateY();
		LocalDate startDate = getDateDP().getValue();
		LocalDate endDate = getDate2DP().getValue();
		String desc = getDescriptionTF().getText();
		setName(actName);
		getCanvasItem().setNameText(actName);
		getControl().getFillForms().fillIteration(form, IDs[1], desc, actName, startDate, endDate,
				chooseConfigID, x, y);
	}

	@Override
	public void setActionSubmitButton() {
		if (getControl().getLists().getConfigList().isEmpty()) {
			getAlerts().showNoConfigAlert();
		} else {
			closeForm();
			close();
		}
	}

	@Override
	public void createForm() {
		

		configLB = new Label("Configuration: ");
		configCB = new ChoiceBox<>(getControl().getLists().getConfigObservable());

		configCB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				System.out.println(newValue.intValue());
				chooseConfigID = newValue.intValue();

			}
		});
	
		fillInfoPart();
	}


	private void fillInfoPart() {

		getDateLB().setText("Start-Date");
		getDate2LB().setText("End-Date");
		
		getInfoPart().add(configLB, 0, 4);
		getInfoPart().setHalignment(configLB, HPos.RIGHT);
		getInfoPart().add(configCB, 1, 4);

	}

	/*** Getrs and Setrs ***/

	public ChoiceBox<String> getConfigCB() {
		return configCB;
	}

	public void setConfigCB(ChoiceBox<String> configCB) {
		this.configCB = configCB;
	}


}
