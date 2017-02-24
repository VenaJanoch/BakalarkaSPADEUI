package Forms;

import Grafika.InfoBoxSegment;
import Grafika.MenuPanel;
import Obsluha.Constans;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BasicForm extends Stage{
	
	
	private BorderPane mainPanel;
	private Scene scena;
	
	private String name;
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
		MenuPanel menu = new MenuPanel();
		VBox topPanel = new VBox();
		
		topPanel.getChildren().addAll(menu);
		mainPanel.setTop(topPanel);
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
	
	
	
	
	
	
	
	

}
