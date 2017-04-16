package abstractform;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import services.Constans;
import services.Control;
import services.DeleteControl;

public class TableBasicForm  extends Stage{

	private BorderPane mainPanel;
	private Scene scena;
	private Control control;
	private GridPane controlPane;
	private Button add;
	private Button submitButton;
	private Label nameLB;
	private TextField nameTF;
	private Label formName;
	protected DeleteControl deleteControl;
	public TableBasicForm(Control control, DeleteControl deleteControl) {
		super();
		this.control = control;
		this.deleteControl = deleteControl;
		this.setScene(creatSceneProject());

	}

	private Scene creatSceneProject() {

		scena = new Scene(creatPanel(), Constans.formWidth, Constans.formHeight);

		return scena;
	}

	private Parent creatPanel() {

		mainPanel = new BorderPane();
		mainPanel.setPadding(new Insets(5));

		
		controlPane = new GridPane();
		submitButton = new Button("OK");
		
		nameLB = new Label("Name");
		nameTF = new TextField();

		add = new Button("Add");

		add.setPrefWidth(60);
		add.setPrefHeight(60);

		controlPane.add(nameLB, 0, 0);
		controlPane.add(nameTF, 1, 0);
		
	
		controlPane.setHgap(3);
		controlPane.setVgap(3);

		controlPane.setAlignment(Pos.CENTER);
		controlPane.setPadding(new Insets(5));

		formName = new Label();
		formName.setAlignment(Pos.CENTER);
		formName.setFont(Font.font(25));
		
		mainPanel.setAlignment(formName, Pos.CENTER);
		mainPanel.setRight(submitButton);
		mainPanel.setAlignment(submitButton, Pos.BOTTOM_CENTER);
		mainPanel.setTop(formName);

		return mainPanel;
	}

	/*** Getrs and Setrs ***/

	public BorderPane getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(BorderPane mainPanel) {
		this.mainPanel = mainPanel;
	}

	public Control getControl() {
		return control;
	}

	public void setControl(Control control) {
		this.control = control;
	}

	public GridPane getControlPane() {
		return controlPane;
	}

	public void setControlPane(GridPane controlPane) {
		this.controlPane = controlPane;
	}

	public Button getAddBT() {
		return add;
	}

	public void setAddBT(Button add) {
		this.add = add;
	}

	public Button getSubmitButton() {
		return submitButton;
	}

	public void setSubmitButton(Button submitButton) {
		this.submitButton = submitButton;
	}

	public TextField getNameTF() {
		return nameTF;
	}

	public void setNameTF(TextField nameTF) {
		this.nameTF = nameTF;
	}

	public Label getNameLB() {
		return nameLB;
	}

	public void setNameLB(Label nameLB) {
		this.nameLB = nameLB;
	}

	public Label getFormName() {
		return formName;
	}

	public void setFormName(Label formName) {
		this.formName = formName;
	}
	
	

}
