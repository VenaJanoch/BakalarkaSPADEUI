package abstractform;
import interfaces.ISegmentTableForm;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import services.Alerts;
import services.Constans;
import services.Control;

public class Table2BasicForm extends Stage{

	private Label nameLB;
	private TextField nameTF;
	private BorderPane mainPanel;
	private Scene scena;
	private Control control;
	private BorderPane internalPanel;
	private GridPane controlPane;
	private Button addBT;
	private Button submitBT;
	
	
	public Table2BasicForm(Control control) {
		this.control = control;
		this.setScene(creatScene());

	}

	private Scene creatScene() {
	
		scena = new Scene(creatPanel(), Constans.milestoneFormWidth, Constans.milestoneFormHeight);

		return scena;
	}

	private Parent creatPanel() {

		mainPanel = new BorderPane();
		mainPanel.setPadding(new Insets(5));

		controlPane = new GridPane();
		internalPanel = new BorderPane();
		
		internalPanel.setBottom(controlPane);
		
		submitBT = new Button("OK");
		submitBT.setAlignment(Pos.BOTTOM_CENTER);

		nameLB = new Label("Name");
		nameTF = new TextField();

		addBT = new Button("Add");

		addBT.setPrefWidth(60);
		addBT.setPrefHeight(60);

		controlPane.add(nameLB, 0, 0);
		controlPane.add(nameTF, 1, 0);

		controlPane.setHgap(3);
		controlPane.setVgap(3);

		controlPane.setAlignment(Pos.CENTER);
		controlPane.setPadding(new Insets(5));

		mainPanel.setBottom(submitBT);
		mainPanel.setAlignment(submitBT, Pos.TOP_RIGHT);
	//	mainPanel.setCenter(internalPanel);
		

		return mainPanel;
	}
	
	
	
	/**** Getrs and Setrs ***/


	public TextField getNameTF() {
		return nameTF;
	}


	public Control getControl() {
		return control;
	}

	public void setControl(Control control) {
		this.control = control;
	}

	public void setNameTF(TextField nameTF) {
		this.nameTF = nameTF;
	}


	public GridPane getControlPane() {
		return controlPane;
	}


	public void setControlPane(GridPane controlPane) {
		this.controlPane = controlPane;
	}


	public Button getAddBT() {
		return addBT;
	}


	public void setAddBT(Button addBT) {
		this.addBT = addBT;
	}


	public Button getSubmitBT() {
		return submitBT;
	}


	public void setSubmitBT(Button submitBT) {
		this.submitBT = submitBT;
	}


	public BorderPane getMainPanel() {
		return mainPanel;
	}


	public void setMainPanel(BorderPane mainPanel) {
		this.mainPanel = mainPanel;
	}


	public BorderPane getInternalPanel() {
		return internalPanel;
	}


	public void setInternalPanel(BorderPane internalPanel) {
		this.internalPanel = internalPanel;
	}

	public Label getNameLB() {
		return nameLB;
	}

	public void setNameLB(Label nameLB) {
		this.nameLB = nameLB;
	}
	
	

	
	
}
