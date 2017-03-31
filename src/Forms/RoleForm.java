package Forms;

import java.time.LocalDate;

import AbstractForm.BasicForm;
import AbstractForm.DescriptionBasicForm;
import Graphics.CanvasItem;
import Graphics.InfoBoxSegment;
import Interfaces.ISegmentForm;
import SPADEPAC.Role;
import SPADEPAC.RoleClass;
import SPADEPAC.WorkUnitPriorityClass;
import SPADEPAC.WorkUnitSeverityClass;
import Services.Control;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

public class RoleForm extends DescriptionBasicForm implements ISegmentForm {

	private Label roleTypeLB;


	private Role role;
	private ComboBox<RoleClass> roleTypeCB;
	private int roleIndex;

	private boolean isNew;

	public RoleForm(CanvasItem item, Control control, Role role) {
		super(item, control);
		this.role = role;
		isNew = true;
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
		int[] IDs = getCanvasItem().getIDs();
		String roleS = RoleClass.values()[roleIndex].name();

		setName(actName);
		getCanvasItem().setNameText(actName);
		getControl().getFillForms().fillRole(role, IDs[1], getDescriptionTF().getText(), actName, roleS, isNew);

		isNew = false;
	}

	@Override
	public void setActionSubmitButton() {
		closeForm();
		close();
	}

	@Override
	public void createForm() {
	
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

		getInfoPart().add(roleTypeLB, 0, 2);
		getInfoPart().setHalignment(roleTypeLB, HPos.RIGHT);
		getInfoPart().add(roleTypeCB, 1, 2);

	}

	/*** Getrs and Setrs ***/

	public ComboBox<RoleClass> getRoleTypeCB() {
		return roleTypeCB;
	}

	public void setRoleTypeCB(ComboBox<RoleClass> roleTypeCB) {
		this.roleTypeCB = roleTypeCB;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

}
