package abstractform;

import java.util.ArrayList;

import Controllers.CanvasController;
import Controllers.FormController;
import Controllers.FormDataController;
import graphics.DragAndDropCanvas;
import graphics.DragAndDropItemPanel;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import services.Constans;

/**
 * Absraktní třídy pro formuláře volané z kreslícího plátna
 * 
 * @author Václav Janoch
 *
 */
public abstract class BasicForm extends Stage {

	/**
	 * Globální proměnné třídy
	 */
	private BorderPane mainPanel;
	protected Scene scena;
//	private Alerts alerts;
//	private String name;
	private Label nameLB;
	private Label formName;
	private TextField nameTF;
	private Button submitButton;

	private ArrayList<HBox> infoParts;
	private GridPane infoPart;
	private HBox buttonBox;

	private DragAndDropItemPanel dgItem;
	private DragAndDropCanvas canvas;
	private BorderPane dragBox;


	protected boolean isSave;
	protected int indexForm;

	protected FormController formController;
	protected FormDataController formDataController;
	protected CanvasController  canvasController;
	/**
	 * Konstruktor třídy pro formuláře s vlastním plátnem Zinicializuje globální
	 * proměnné třídy
	 */
	public BasicForm(FormController formController, FormDataController formDataController, CanvasController canvasController, DragAndDropItemPanel dgItem, String name) {
		super();
		this.formController = formController;
		this.formDataController = formDataController;

		this.setTitle("Edit " + name + " Form");
		this.canvasController = canvasController;
		this.canvas = canvasController.getCanvas();
		this.dgItem = dgItem;
		this.dragBox = new BorderPane();
		mainPanel = new BorderPane();

		this.setScene(creatSceneCanvas());

	}

	abstract void createForm();
	/**
	 * Konstruktor třídy pro prvky bez plátna
	 *
	 */

	public BasicForm(FormController formController, FormDataController formDataController, String name) {

		super();
		this.setTitle("Edit " + name);
		mainPanel = new BorderPane();
		this.formController = formController;
		this.formDataController = formDataController;
		this.setScene(creatSceneProject());

	}


	/**
	 * Vytvoří scénu s formulářem
	 * 
	 * @return Scene
	 */
	private Scene creatSceneCanvas() {

		scena = new Scene(createPanelCanvas());

		return scena;
	}

	/**
	 * Vytvoří scénu pro formulář projektu
	 * 
	 * @return Scene
	 */
	private Scene creatSceneProject() {

		scena = new Scene(creatPanelProject());

		return scena;
	}

	/**
	 * Vytvoří rozložení prvků pro plátno ve formuláři
	 * 
	 * @return BorderPane
	 */
	private Parent createPanelCanvas() {
		creatPanelProject();
		dragBox.setTop(dgItem);
		dragBox.setCenter(canvas);

		mainPanel.setCenter(dragBox);
		mainPanel.setLeft(infoPart);
		return mainPanel;
	}

	/**
	 * Vytvoří a rozloží základní prvky ve formuláři
	 * 
	 * @return BorderPane
	 */
	private Parent creatPanelProject() {

		mainPanel.setPadding(new Insets(5));
		buttonBox = new HBox(5);
		mainPanel.setMinSize(Constans.formWidth, Constans.formHeight);
		infoPart = new GridPane();
		infoPart.setAlignment(Pos.CENTER);
		infoPart.setVgap(10);
		infoPart.setHgap(10);
		nameLB = new Label("Name: ");
		nameTF = new TextField();
		nameTF.setId("formName");

		submitButton = new Button("OK");
		submitButton.setId("formSubmit");
		nameLB.setAlignment(Pos.CENTER_RIGHT);

		formName = new Label();
		formName.setAlignment(Pos.CENTER);
		formName.setFont(Font.font(25));
		formName.setId("formID");
		mainPanel.setAlignment(formName, Pos.CENTER);
		mainPanel.setTop(formName);

		HBox nameBox = new HBox(5);
		nameBox.getChildren().addAll(nameLB, nameTF);

		buttonBox.getChildren().add(submitButton);
		buttonBox.setAlignment(Pos.BOTTOM_RIGHT);

		infoPart.setPadding(new Insets(5));
		infoPart.add(nameLB, 0, 0);
		infoPart.add(nameTF, 1, 0);
		infoPart.setHalignment(nameLB, HPos.RIGHT);

		mainPanel.setCenter(infoPart);
		mainPanel.setBottom(buttonBox);

		return mainPanel;
	}

	/** Getrs and Setrs **/

	public boolean isSave() {
		return isSave;
	}
	public BorderPane getMainPanel() {
		return mainPanel;
	}

	public Label getFormName() {
		return formName;
	}

	public void setFormName(Label formName) {
		this.formName = formName;
	}

	public void setMainPanel(BorderPane mainPanel) {
		this.mainPanel = mainPanel;
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


	public DragAndDropCanvas getCanvas() {
		return canvas;
	}

	public void setCanvas(DragAndDropCanvas canvas) {
		this.canvas = canvas;
	}

	public Label getNameLB() {
		return nameLB;
	}

	public void setNameLB(Label nameLB) {
		this.nameLB = nameLB;
	}

	public BorderPane getDragBox() {
		return dragBox;
	}

	public void setDragBox(BorderPane dragBox) {
		this.dragBox = dragBox;
	}
}
