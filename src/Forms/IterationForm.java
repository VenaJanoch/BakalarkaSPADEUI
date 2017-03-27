package Forms;

import Grafika.CanvasItem;
import Grafika.InfoBoxSegment;
import Interfaces.ISegmentForm;
import Obsluha.Control;
import Obsluha.SegmentType;
import SPADEPAC.Iteration;
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

public class IterationForm extends BasicForm implements ISegmentForm {

	private Label workUnitsLB;
	private Label startDateLB;
	private Label endDateLB;
	private Label descriptionLB;
	private Label configLB;

	private ChoiceBox<String> configCB;
	private TextField descriptionTF;
	private DatePicker endDateDP;
	private DatePicker startDateDP;

	private Button editConfigBT;
	private Button newConfigBT;
	private int chooseConfigID;

	public IterationForm(CanvasItem item, Control control, int[] itemArray, Iteration iteration, int indexForm) {
		super(item, control, itemArray, indexForm);
		setWorkUnitArray(iteration.getWorkUnits());
		setConfig(iteration.getConfiguration());
		
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
		getControl().getFillForms().fillIteration(getCanvasItem().getForm(), getCanvasItem().getIDs()[1], descriptionTF.getText(),
				getName(), startDateDP.getValue(), endDateDP.getValue(), chooseConfigID,
				(int) getCanvasItem().getTranslateX(), (int) getCanvasItem().getTranslateY());
	}

	@Override
	public void setActionSubmitButton() {
		closeForm();
		close();
	}

	@Override
	public void createForm() {
		startDateLB = new Label("Start Date: ");
		startDateDP = new DatePicker();

		endDateLB = new Label("End Date: ");
		endDateDP = new DatePicker();

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

		getInfoPart().add(startDateLB, 0, 2);
		getInfoPart().setHalignment(startDateLB, HPos.RIGHT);
		getInfoPart().add(startDateDP, 1, 2);

		getInfoPart().add(endDateLB, 0, 3);
		getInfoPart().setHalignment(endDateLB, HPos.RIGHT);
		getInfoPart().add(endDateDP, 1, 3);

		getInfoPart().add(configLB, 0, 4);
		getInfoPart().setHalignment(configLB, HPos.RIGHT);
		getInfoPart().add(configCB, 1, 4);
		getInfoPart().add(editConfigBT, 2, 4);
		getInfoPart().add(newConfigBT, 3, 4);

	}

	/*** Getrs and Setrs ***/
	
	public ChoiceBox<String> getConfigCB() {
		return configCB;
	}

	public void setConfigCB(ChoiceBox<String> configCB) {
		this.configCB = configCB;
	}

	public TextField getDescriptionTF() {
		return descriptionTF;
	}

	public void setDescriptionTF(TextField descriptionTF) {
		this.descriptionTF = descriptionTF;
	}

	public DatePicker getEndDateDP() {
		return endDateDP;
	}

	public void setEndDateDP(DatePicker endDateDP) {
		this.endDateDP = endDateDP;
	}

	public DatePicker getStartDateDP() {
		return startDateDP;
	}

	public void setStartDateDP(DatePicker startDateDP) {
		this.startDateDP = startDateDP;
	}

	
	
}
