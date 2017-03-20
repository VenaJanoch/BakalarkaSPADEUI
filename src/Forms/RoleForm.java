package Forms;

import Grafika.CanvasItem;
import Grafika.InfoBoxSegment;
import Interfaces.ISegmentForm;
import Obsluha.Control;
import SPADEPAC.Role;
import SPADEPAC.RoleClass;
import SPADEPAC.WorkUnitPriorityClass;
import SPADEPAC.WorkUnitSeverityClass;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

public class RoleForm extends BasicForm implements ISegmentForm {

	private Label descriptionLB;
	private Label roleTypeLB;

	private TextField descriptionTF;

	private Role role;
	private ComboBox<RoleClass> roleTypeCB;
	private int roleIndex;


	public RoleForm(CanvasItem item, Control control, Role role) {
		super(item, control);
		this.role = role;
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
		getControl().fillRole(role, getCanvasItem().getIDs()[1], descriptionTF.getText(),
				getName(), RoleClass.values()[roleIndex].name());

	}

	@Override
	public void setActionSubmitButton() {
		closeForm();
		close();
	}

	@Override
	public void createForm() {
		descriptionLB = new Label("Description: ");
		descriptionTF = new TextField();

		roleTypeLB = new Label("Type: ");
		roleTypeCB = new ComboBox<RoleClass>(FXCollections.observableArrayList(RoleClass.values()));
		roleTypeCB.getSelectionModel().selectedIndexProperty().addListener(roleListener);

		fillInfoPart();
	}
	
	ChangeListener<Number> roleListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			System.out.println(newValue.intValue());
			roleIndex = newValue.intValue();

		}
	};

	private void fillInfoPart() {

		getInfoPart().add(descriptionLB, 0, 1);
		getInfoPart().setHalignment(descriptionLB, HPos.RIGHT);
		getInfoPart().add(descriptionTF, 1, 1);
		
		getInfoPart().add(roleTypeLB, 0, 2);
		getInfoPart().setHalignment(roleTypeLB, HPos.RIGHT);
		getInfoPart().add(roleTypeCB, 1, 2);

	}

}
