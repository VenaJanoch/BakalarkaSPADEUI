package Forms;

import Grafika.CanvasItem;
import Grafika.InfoBoxSegment;
import Interfaces.ISegmentForm;
import Obsluha.Control;
import SPADEPAC.Branch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.WindowEvent;

public class BranchForm extends BasicForm implements ISegmentForm {

	private Label isMainLB;
	private boolean isMain;

	private ToggleGroup group = new ToggleGroup();

	private RadioButton rbYes;
	private RadioButton rbNo;

	private ComboBox<String> branchesCB;
	private Label branchesLB;

	private int branchIndex;

	private boolean newBranch;

	public BranchForm(CanvasItem item, Control control) {
		super(item, control);
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
		getControl().getFillForms().fillBranch(getCanvasItem().getForm(), getCanvasItem().getIDs()[2], isMain, getName(), newBranch,
				(int) getCanvasItem().getTranslateX(), (int) getCanvasItem().getTranslateY());
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

		branchesLB = new Label("Branches");
		branchesCB = new ComboBox<String>(getControl().getBranchObservable());
		branchesCB.setVisibleRowCount(5);
		branchesCB.getSelectionModel().selectedIndexProperty().addListener(branchListener);

		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				RadioButton chk = (RadioButton) newValue.getToggleGroup().getSelectedToggle();

				if (chk.getText().contains("Yes")) {
					isMain = true;
				} else {
					isMain = false;
				}

			}
		});

		fillInfoPart();
	}

	ChangeListener<Number> branchListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			branchIndex = newValue.intValue();
			fillFormFromList(branchIndex);
		}
	};

	private void fillFormFromList(int index) {
System.out.println(index + "Index ");
		if ((index) > 0) {

			getNameTF().setText(getControl().getBranchList().get(index - 1).getName());
			getNameTF().setDisable(true);
			newBranch = false;

			if (getControl().getBranchList().get(index - 1).isIsMain()) {
				rbYes.setSelected(true);
				rbYes.setDisable(true);
				rbNo.setDisable(true);
			} else {
				rbNo.setSelected(true);
				rbYes.setDisable(true);
				rbNo.setDisable(true);
			}

		} else {
			getNameTF().setDisable(false);
			rbYes.setDisable(false);
			rbNo.setDisable(false);
			newBranch = true;
		}

	}

	private void fillInfoPart() {
		getInfoPart().add(isMainLB, 0, 1);
		getInfoPart().setHalignment(isMainLB, HPos.RIGHT);
		getInfoPart().add(rbYes, 1, 1);
		getInfoPart().add(rbNo, 2, 1);
		getInfoPart().add(branchesLB, 3, 0);
		getInfoPart().add(branchesCB, 4, 0);

	}
	
	
	/*** Getrs and Setrs ***/

	public boolean isNewBranch() {
		return newBranch;
	}

	public void setNewBranch(boolean newBranch) {
		this.newBranch = newBranch;
	}

	public boolean isMain() {
		return isMain;
	}

	public void setMain(boolean isMain) {
		this.isMain = isMain;
	}

	public RadioButton getRbYes() {
		return rbYes;
	}

	public void setRbYes(RadioButton rbYes) {
		this.rbYes = rbYes;
	}

	public RadioButton getRbNo() {
		return rbNo;
	}

	public void setRbNo(RadioButton rbNo) {
		this.rbNo = rbNo;
	}

	public ComboBox<String> getBranchesCB() {
		return branchesCB;
	}

	public void setBranchesCB(ComboBox<String> branchesCB) {
		this.branchesCB = branchesCB;
	}
		
	

}
