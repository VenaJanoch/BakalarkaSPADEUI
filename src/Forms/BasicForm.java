package Forms;


import java.util.ArrayList;

import Grafika.InfoBoxSegment;
import Grafika.MenuPanel;
import Obsluha.Constans;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BasicForm extends Stage{
	
	
	private BorderPane mainPanel;
	private Scene scena;
	
	private String name;
	private Label nameLB;
	private TextField nameTF;
	private Button submitButton;
	
	
	private ArrayList<HBox> infoParts;
	private GridPane infoPart;
	private HBox buttonBox;

	
	private InfoBoxSegment infoBox;
	
	public BasicForm(InfoBoxSegment infoBox) {

		super();
		
		this.infoBox = infoBox;
		
		this.setTitle("Edit " + infoBox.getSegmentName().getText());
		
		mainPanel = new BorderPane();
		
		this.setScene(creatScene());
		this.show();
	}

	private Scene creatScene() {

		scena = new Scene(creatPanel(), Constans.formWidth, Constans.formHeight);
		
		return scena;
	}

	private Parent creatPanel() {
		mainPanel.setPadding(new Insets(5)); 
		buttonBox = new HBox(5);
		infoPart = new GridPane();
		
		nameLB = new Label("Name: ");
		nameTF = new TextField();
		submitButton = new Button("OK");
		nameLB.setAlignment(Pos.CENTER_RIGHT);
		HBox nameBox = new HBox(5);
		nameBox.getChildren().addAll(nameLB,nameTF);
		
		buttonBox.getChildren().add(submitButton);
		buttonBox.setAlignment(Pos.CENTER_RIGHT);
	
		infoPart.setPadding(new Insets(5));
		infoPart.add(nameLB, 0, 0);
		infoPart.add(nameTF, 1, 0);
		infoPart.setHalignment(nameLB, HPos.RIGHT);
		
		mainPanel.setCenter(infoPart);
		mainPanel.setBottom(buttonBox);
		
		return mainPanel;
	}

	
	
	/** Getrs and Setrs **/

	public BorderPane getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(BorderPane mainPanel) {
		this.mainPanel = mainPanel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InfoBoxSegment getInfoBox() {
		return infoBox;
	}

	public void setInfoBox(InfoBoxSegment infoBox) {
		this.infoBox = infoBox;
	}

	public TextField getNameTF() {
		return nameTF;
	}

	public void setNameTF(TextField nameTF) {
		this.nameTF = nameTF;
	}

	public Button getSubmitButton() {
		return submitButton;
	}

	public void setSubmitButton(Button submitButton) {
		this.submitButton = submitButton;
	}

	

	public void setButtonBox(HBox buttonBox) {
		this.buttonBox = buttonBox;
	}

	public GridPane getInfoPart() {
		return infoPart;
	}

	public void setInfoPart(GridPane infoParts) {
		this.infoPart = infoPart;
	}
	
	
	
	
	
	
	
	

}
