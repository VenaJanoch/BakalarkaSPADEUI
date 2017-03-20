package Forms;

import java.util.ArrayList;
import java.util.List;

import Grafika.CanvasItem;
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
	private int chooseConfigID;

	public PhaseForm(CanvasItem item, Control control, int[] itemArray, Phase phase) {
		super(item, control, itemArray);

		setWorkUnitArray(phase.getWorkUnits());
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
		getCanvasItem().getControl().fillPhase(getCanvasItem().getForm(), getCanvasItem().getIDs()[1],
				descriptionTF.getText(), getName(), endDate.getValue(), chooseConfigID);
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
		newConfigBT = new Button("New Configuration");
		newConfigBT.setOnAction(event -> artifactBTAction());
		fillInfoPart();
	}

	private void artifactBTAction() {
		CanvasItem item = new CanvasItem(SegmentType.Configuration, "Name", getControl(), this);

		getControl().createForm(item, this);
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
		getInfoPart().add(newConfigBT, 2, 3);

	}

}
