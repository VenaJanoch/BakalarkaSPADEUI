package abstractform;

import java.util.ArrayList;

import Controllers.FormController;
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

//	private CanvasItem item;
//	private Control control;
//	private int[] itemArray;
	private DragAndDropItemPanel dgItem;
	private DragAndDropCanvas canvas;
	private BorderPane dragBox;

//	private FormControl formControl;
//	protected DeleteControl deleteControl;
//	private List<Phase> phaseArray;
//	private List<Iteration> iterationArray;
//	private List<Activity> activityArray;
//	private List<Integer> workUnitArray;
//	private List<Integer> milestoneArray;
//	private List<Integer> criterionnArray;
//	private Configuration configArray;
//	private List<Integer> branchArray;
//	private List<Integer> changeArray;
//	private List<Integer> artifactArray;
//	private List<Integer> roleArray;
//	private List<String> TagArray;
//	private List<Integer> confPRArray;

//	private IdentificatorCreater idCreater;
//	private String type;
	protected boolean isSave;
//	private int indexForm;

	private FormController formController;
	/**
	 * Konstruktor třídy pro formuláře s vlastním plátnem Zinicializuje globální
	 * proměnné třídy
	 */
	public BasicForm(FormController formController, DragAndDropCanvas canvas, DragAndDropItemPanel dgItem, String name) {
		super();
		this.formController = formController;

	//	type = item.getType().name();
	//	this.deleteControl = deleteControl;
	//	this.control = control;
	//	this.item = item;
	//	this.itemArray = itemArray;
	//	this.alerts = new Alerts();
	//	this.indexForm = indexForm;
	//	this.setIdCreater(new IdentificatorCreater());
		this.setTitle("Edit " + name + " Form");
		this.canvas = canvas;
		this.dgItem = dgItem;
		this.dragBox = new BorderPane();
	//	this.setFormControl(new FormControl());
		mainPanel = new BorderPane();

		this.setScene(creatSceneCanvas());

	}

	public abstract boolean isSave();

	/**
	 * Konstruktor třídy pro prvky bez plátna
	 *
	 */

	public BasicForm(FormController formController, String name) {

		super();
	//	type = item.getType().name();
	//	this.deleteControl = deleteControl;
	//	this.control = control;
	//	this.item = item;
	//	this.alerts = new Alerts();
	//	this.setIdCreater(new IdentificatorCreater());
		this.setTitle("Edit " + name);
		mainPanel = new BorderPane();
		this.formController = formController;
		this.setScene(creatSceneProject());

	}

	/**
	 * Konstruktor pro projektový formulář
	 *
	 */
	public BasicForm(FormController formController) {
		super();
		//type = "Project";
		//this.control = control;
		//this.alerts = new Alerts();
		//this.indexForm = 0;

		this.setTitle("Edit Project");
		this.formController = formController;

		mainPanel = new BorderPane();

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


	abstract void createForm();

	/** Getrs and Setrs **/

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
