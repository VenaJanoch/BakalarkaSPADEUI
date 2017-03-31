package Forms;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import AbstractForm.BasicForm;
import AbstractForm.DateDescBasicForm;
import Graphics.CanvasItem;
import Graphics.DragAndDropCanvas;
import Graphics.DragAndDropItem;
import Graphics.InfoBoxSegment;
import Interfaces.ISegmentForm;
import SPADEPAC.Configuration;
import SPADEPAC.Phase;
import SPADEPAC.WorkUnit;
import Services.Control;
import Services.SegmentType;
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

public class PhaseForm extends DateDescBasicForm implements ISegmentForm {

	private Label configLB;

	private ChoiceBox<String> configCB;
	private Button newConfigBT;
	private Button editConfigBT;
	private int chooseConfigID;
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
		getCanvasItem().getFillForms().fillPhase(form, IDs[1], desc, actName, endDateL, chooseConfigID, x, y);
	}

	@Override
	public void setActionSubmitButton() {

		if (getControl().getConfigList().isEmpty()) {
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
		configCB = new ChoiceBox<>(getControl().getConfigObservable());

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

		getInfoPart().add(configLB, 0, 3);
		getInfoPart().setHalignment(configLB, HPos.RIGHT);
		getInfoPart().add(configCB, 1, 3);
		getInfoPart().add(editConfigBT, 2, 3);
		getInfoPart().add(newConfigBT, 3, 3);

	}

	public ChoiceBox<String> getConfigCB() {
		return configCB;
	}

	public void setConfigCB(ChoiceBox<String> configCB) {
		this.configCB = configCB;
	}

}
