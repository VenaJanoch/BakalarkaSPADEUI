package Forms;

import Grafika.CanvasItem;
import Grafika.InfoBoxSegment;
import Interfaces.ISegmentForm;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

public class ConfigPersonRelationForm extends BasicForm implements ISegmentForm{

	private Label configurationLB;
	private Label personRoleLB;

	private TextField configurationTF;
	private TextField personTF;
	
	public ConfigPersonRelationForm(CanvasItem item) {
		super(item);
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

		getCanvasItem().setNameText(getNameTF().getText());

	}

	@Override
	public void setActionSubmitButton() {
		closeForm();
		close();
	}

	@Override
	public void createForm() {
		configurationLB = new Label("Configuration: ");
		configurationTF = new TextField();

		personRoleLB  = new Label("Person-Role");
		personTF = new TextField();
		
		fillInfoPart();
	}

	private void fillInfoPart() {

		getInfoPart().add(configurationLB, 0, 1);
		getInfoPart().setHalignment(configurationLB, HPos.RIGHT);
		getInfoPart().add(configurationTF, 1, 1);

		getInfoPart().add(personRoleLB, 0, 2);
		getInfoPart().setHalignment(personRoleLB, HPos.RIGHT);
		getInfoPart().add(personTF, 1, 2);

	
	}


}
