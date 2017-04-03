package AbstractForm;

import Services.Constans;
import Services.Control;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TableBasicForm extends Stage {

	private BorderPane mainPanel;
	private Scene scena;
	private Control control;
	private GridPane controlPane;
	private Button add;
	private Button submitButton;

	public TableBasicForm(Control control) {
		super();
		this.control = control;

		mainPanel = new BorderPane();
		mainPanel.setPadding(new Insets(5));

		controlPane = new GridPane();

		this.setScene(creatSceneProject());

	}

	private Scene creatSceneProject() {

		scena = new Scene(creatPanel(), Constans.formWidth, Constans.formHeight);

		return scena;
	}

	private Parent creatPanel() {

		controlPane = new GridPane();
		submitButton = new Button("OK");
		submitButton.setAlignment(Pos.BOTTOM_CENTER);
		
		add = new Button("Add");
		
		add.setPrefWidth(60);
		add.setPrefHeight(60);
		
		controlPane.setHgap(3);
		controlPane.setVgap(3);

		controlPane.setAlignment(Pos.CENTER);
		controlPane.setPadding(new Insets(5));
		
		mainPanel.setRight(submitButton);
		mainPanel.setBottom(controlPane);

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
	
	
	
}
