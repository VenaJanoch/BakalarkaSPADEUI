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

	private Button newConfigBT;
	private int chooseConfigID;

	public IterationForm(CanvasItem item, Control control, int[] itemArray, Iteration iteration) {
		super(item, control, itemArray);
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
		setName(getNameTF().getText());
		getCanvasItem().setNameText(getName());
		getControl().fillIteration(getCanvasItem().getForm(), getCanvasItem().getIDs()[1], descriptionTF.getText(),
				getName(), startDateDP.getValue(), endDateDP.getValue(), chooseConfigID);
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
		newConfigBT = new Button("New Configuration");
		newConfigBT.setOnAction(event -> getControl()
				.createForm(new CanvasItem(SegmentType.Configuration, "Name", getControl(), this), this));

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

		getInfoPart().add(configLB, 0, 4);
		getInfoPart().setHalignment(configLB, HPos.RIGHT);
		getInfoPart().add(configCB, 1, 4);
		getInfoPart().add(newConfigBT, 2, 4);

	}

}
