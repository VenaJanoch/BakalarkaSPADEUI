package Forms;

import java.util.ArrayList;
import java.util.List;

import Grafika.CanvasItem;
import Grafika.DragAndDropCanvas;
import Grafika.DragAndDropItem;
import Grafika.InfoBoxSegment;
import Interfaces.ISegmentForm;
import Obsluha.Control;
import Obsluha.SegmentType;
import SPADEPAC.Configuration;
import SPADEPAC.Phase;
import SPADEPAC.WorkUnit;
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

public class PhaseForm extends BasicForm implements ISegmentForm {

	private Label endDateLB;
	private Label descriptionLB;
	private Label configLB;

	private TextField descriptionTF;
	private DatePicker endDate;
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
		setName(getNameTF().getText());
		getCanvasItem().setNameText(getName());
		getCanvasItem().getFillForms().fillPhase(getCanvasItem().getForm(), getCanvasItem().getIDs()[1],
				descriptionTF.getText(), getName(), endDate.getValue(), chooseConfigID,
				(int) getCanvasItem().getTranslateX(), (int) getCanvasItem().getTranslateY());
	}

	@Override
	public void setActionSubmitButton() {
		closeForm();
		close();
	}

	@Override
	public void createForm() {
		endDateLB = new Label("End Date: ");
		endDate = new DatePicker();

		descriptionLB = new Label("Description: ");
		descriptionTF = new TextField();

		configLB = new Label("Configuration: ");
		configCB = new ChoiceBox<>(getControl().getConfigObservable());

		configCB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				System.out.println(newValue.intValue());
				chooseConfigID = newValue.intValue();

			}
		});
		newConfigBT = new Button("New");
		newConfigBT.setOnAction(event -> configBTAction());

		editConfigBT = new Button("Edit");
		editConfigBT.setOnAction(event -> editBTAction());

		fillInfoPart();
	}


	private void editBTAction() {
		getControl().getForms().get(getControl().getConfigFormIndex().get(chooseConfigID)).show();
	}

	private void configBTAction() {
		CanvasItem item = new CanvasItem(SegmentType.Configuration, "Name", getControl(), this, true);
		getControl().getForms().get(item.getIDs()[0]).show();

	}

	private void fillInfoPart() {

		getInfoPart().add(descriptionLB, 0, 1);
		getInfoPart().setHalignment(descriptionLB, HPos.RIGHT);
		getInfoPart().add(descriptionTF, 1, 1);

		getInfoPart().add(endDateLB, 0, 2);
		getInfoPart().setHalignment(endDateLB, HPos.RIGHT);
		getInfoPart().add(endDate, 1, 2);

		getInfoPart().add(configLB, 0, 3);
		getInfoPart().setHalignment(configLB, HPos.RIGHT);
		getInfoPart().add(configCB, 1, 3);
		getInfoPart().add(editConfigBT, 2, 3);
		getInfoPart().add(newConfigBT, 3, 3);

	}

	/** Getrs And Setrs **/
	public TextField getDescriptionTF() {
		return descriptionTF;
	}

	public void setDescriptionTF(TextField descriptionTF) {
		this.descriptionTF = descriptionTF;
	}

	public DatePicker getEndDate() {
		return endDate;
	}

	public void setEndDate(DatePicker endDate) {
		this.endDate = endDate;
	}

	public ChoiceBox<String> getConfigCB() {
		return configCB;
	}

	public void setConfigCB(ChoiceBox<String> configCB) {
		this.configCB = configCB;
	}

}
