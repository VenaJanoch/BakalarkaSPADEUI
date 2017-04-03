package forms;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import SPADEPAC.Configuration;
import SPADEPAC.Phase;
import SPADEPAC.WorkUnit;
import abstractform.BasicForm;
import abstractform.DateDescBasicForm;
import graphics.CanvasItem;
import graphics.DragAndDropCanvas;
import graphics.DragAndDropItem;
import graphics.InfoBoxSegment;
import interfaces.ISegmentForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.WindowEvent;
import services.Control;
import services.SegmentType;

public class PhaseForm extends DateDescBasicForm implements ISegmentForm {

	private Label configLB;
	private Label milestoneLB;

	private ChoiceBox<String> configCB;
	private ChoiceBox<String> milestoneCB;
	private Button newConfigBT;
	private Button editConfigBT;
	private int milestoneIndex;
	private int configIndex;
	private Phase phase;

	public PhaseForm(CanvasItem item, Control control, int[] itemArray, Phase phase, int indexForm) {
		super(item, control, itemArray, indexForm);
		this.phase = phase;
		
		setWorkUnitArray(phase.getWorkUnits());
		setConfig(phase.getConfiguration());
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
		LocalDate endDateL = getDateDP().getValue();
		String desc = getDescriptionTF().getText();

		setName(actName);
		getCanvasItem().setNameText(actName);
		getCanvasItem().getFillForms().fillPhase(form, IDs[1], desc, actName, endDateL, configIndex, milestoneIndex, x, y);
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
		getDateLB().setText("End Date: ");

		configLB = new Label("Configuration: ");
		configCB = new ChoiceBox<>(getControl().getLists().getConfigObservable());

		configCB.getSelectionModel().selectedIndexProperty().addListener(configListener);
		milestoneLB = new Label("Milestone: ");
		milestoneCB = new ChoiceBox<>(getControl().getLists().getMilestoneObservable());
		milestoneCB.getSelectionModel().selectedIndexProperty().addListener(milestoneListener);
		fillInfoPart();
	}
	
	ChangeListener<Number> milestoneListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			
			milestoneIndex = newValue.intValue();

		}
	};
	
	ChangeListener<Number> configListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			
			configIndex = newValue.intValue();

		}
	};

	private void fillInfoPart() {

		getInfoPart().add(configLB, 0, 3);
		getInfoPart().setHalignment(configLB, HPos.RIGHT);
		getInfoPart().add(configCB, 1, 3);
		

		getInfoPart().add(milestoneLB, 0, 4);
		getInfoPart().setHalignment(milestoneLB, HPos.RIGHT);
		getInfoPart().add(milestoneCB, 1, 4);
		
	}

	public ChoiceBox<String> getConfigCB() {
		return configCB;
	}

	public void setConfigCB(ChoiceBox<String> configCB) {
		this.configCB = configCB;
	}

	public ChoiceBox<String> getMilestoneCB() {
		return milestoneCB;
	}

	public void setMilestoneCB(ChoiceBox<String> milestoneCB) {
		this.milestoneCB = milestoneCB;
	}
	
	

}
