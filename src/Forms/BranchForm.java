package Forms;

import Grafika.CanvasItem;
import Grafika.InfoBoxSegment;
import Interfaces.ISegmentForm;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.WindowEvent;

public class BranchForm extends BasicForm implements ISegmentForm {

	private Label isMainLB;

	private  ToggleGroup group = new ToggleGroup();

	private RadioButton rbYes;
	private RadioButton rbNo;

	public BranchForm(CanvasItem item) {
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
	
		isMainLB = new Label("Main");
		
		rbYes = new RadioButton("Yes");
		rbYes.setToggleGroup(group);
		rbYes.setSelected(true);

		rbNo = new RadioButton("No");
		rbNo.setToggleGroup(group);


		fillInfoPart();
	}

	private void fillInfoPart() {
		getInfoPart().add(isMainLB, 0,1);
		getInfoPart().setHalignment(isMainLB, HPos.RIGHT);
		getInfoPart().add(rbYes, 1, 1);
		getInfoPart().add(rbNo, 2, 1);
	
	}

}
