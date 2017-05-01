package abstractform;

import java.util.ArrayList;
import java.util.List;

import SPADEPAC.Activity;
import SPADEPAC.Artifact;
import SPADEPAC.Branch;
import SPADEPAC.Change;
import SPADEPAC.ConfigPersonRelation;
import SPADEPAC.Configuration;
import SPADEPAC.Criterion;
import SPADEPAC.Iteration;
import SPADEPAC.Milestone;
import SPADEPAC.Phase;
import SPADEPAC.Role;
import SPADEPAC.WorkUnit;
import graphics.CanvasItem;
import graphics.DragAndDropCanvas;
import graphics.DragAndDropItem;
import graphics.InfoBoxSegment;
import graphics.MenuPanel;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import services.Alerts;
import services.CanvasType;
import services.Constans;
import services.Control;
import services.DeleteControl;
import services.FormControl;
import services.IdentificatorCreater;

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
	private Alerts alerts;
	private String name;
	private Label nameLB;
	private Label formName;
	private TextField nameTF;
	private Button submitButton;

	private ArrayList<HBox> infoParts;
	private GridPane infoPart;
	private HBox buttonBox;

	private CanvasItem item;
	private Control control;
	private int[] itemArray;
	private DragAndDropItem dgItem;
	private DragAndDropCanvas canvas;
	private BorderPane dragBox;

	private FormControl formControl;
	protected DeleteControl deleteControl;
	private List<Phase> phaseArray;
	private List<Iteration> iterationArray;
	private List<Activity> activityArray;
	private List<Integer> workUnitArray;
	private List<Integer> milestoneArray;
	private List<Integer> criterionnArray;
	private Configuration configArray;
	private List<Integer> branchArray;
	private List<Integer> changeArray;
	private List<Integer> artifactArray;
	private List<Integer> roleArray;
	private List<String> TagArray;
	private List<Integer> confPRArray;

	private IdentificatorCreater idCreater;
	private String type;
	private boolean isNew;
	private int indexForm;

	/**
	 * Konstruktor třídy pro formuláře s vlastním plátnem Zinicializuje globální
	 * proměnné třídy
	 * 
	 * @param item
	 *            CanvasItem
	 * @param control
	 *            Control
	 * @param itemArray
	 * @param indexForm
	 * @param deleteControl
	 *            DeleteControl
	 * @param canvasType
	 *            CanvasType
	 */
	public BasicForm(CanvasItem item, Control control, int[] itemArray, int indexForm, DeleteControl deleteControl,
			CanvasType canvasType) {

		super();
		type = item.getType().name();
		this.deleteControl = deleteControl;
		this.control = control;
		this.item = item;
		this.itemArray = itemArray;
		this.alerts = new Alerts();
		this.indexForm = indexForm;
		this.setIdCreater(new IdentificatorCreater());
		this.setTitle("Edit " + item.getType().name() + " Form");
		this.canvas = new DragAndDropCanvas(control, indexForm, control.getContexMenu(), canvasType);
		this.dgItem = new DragAndDropItem(control, itemArray, this);
		this.dragBox = new BorderPane();
		this.setFormControl(new FormControl(control.getLists()));
		mainPanel = new BorderPane();

		this.setScene(creatSceneCanvas());

	}

	/**
	 * Konstruktor třídy pro prvky bez plátna
	 * 
	 * @param item
	 *            CanvasItem
	 * @param control
	 *            Control
	 * @param deleteControl
	 *            DeleteControl
	 */

	public BasicForm(CanvasItem item, Control control, DeleteControl deleteControl) {

		super();
		type = item.getType().name();
		this.deleteControl = deleteControl;
		this.control = control;
		this.item = item;
		this.alerts = new Alerts();
		this.setIdCreater(new IdentificatorCreater());
		this.setTitle("Edit " + item.getType().name());
		mainPanel = new BorderPane();
		this.setFormControl(new FormControl(control.getLists()));

		this.setScene(creatSceneProject());

	}

	/**
	 * Konstruktor pro projektový formulář
	 * 
	 * @param control
	 *            Control
	 */
	public BasicForm(Control control) {
		super();
		type = "Project";
		this.control = control;
		this.alerts = new Alerts();
		this.indexForm = 0;
		this.setTitle("Edit Project");
		this.setIdCreater(new IdentificatorCreater());
		this.setFormControl(new FormControl(control.getLists()));

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

	/**
	 * Pomocná metoda pro smazání prvku z plátna
	 * 
	 * @param i
	 *            Identifikátory
	 */
	public void deleteItem(int[] i) {

	}

	@Override
	public String toString() {

		return type + " " + getName();
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CanvasItem getCanvasItem() {
		return item;
	}

	public void setCanvasItem(CanvasItem item) {
		this.item = item;
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

	public Control getControl() {
		return control;
	}

	public void setControl(Control control) {
		this.control = control;
	}

	public List<Phase> getPhaseArray() {
		return phaseArray;
	}

	public void setPhaseArray(List<Phase> phaseArray) {
		this.phaseArray = phaseArray;
	}

	public List<Iteration> getIterationArray() {
		return iterationArray;
	}

	public void setIterationArray(List<Iteration> iterationArray) {
		this.iterationArray = iterationArray;
	}

	public List<Activity> getActivityArray() {
		return activityArray;
	}

	public void setActivityArray(List<Activity> activityArray) {
		this.activityArray = activityArray;
	}

	public List<Integer> getWorkUnitArray() {
		return workUnitArray;
	}

	public void setWorkUnitArray(List<Integer> workUnitArray) {
		this.workUnitArray = workUnitArray;
	}

	public IdentificatorCreater getIdCreater() {
		return idCreater;
	}

	public void setIdCreater(IdentificatorCreater idCreater) {
		this.idCreater = idCreater;
	}

	public int[] getItemArray() {
		return itemArray;
	}

	public void setItemArray(int[] itemArray) {
		this.itemArray = itemArray;
	}

	public List<Integer> getMilestoneArray() {
		return milestoneArray;
	}

	public void setMilestoneArray(List<Integer> milestoneArray) {
		this.milestoneArray = milestoneArray;
	}

	public List<Integer> getCriterionnArray() {
		return criterionnArray;
	}

	public void setCriterionnArray(List<Integer> criterionnArray) {
		this.criterionnArray = criterionnArray;
	}

	public Configuration getConfigArray() {
		return configArray;
	}

	public void setConfigArray(Configuration configArray) {
		this.configArray = configArray;
	}

	public List<Integer> getBranchArray() {
		return branchArray;
	}

	public void setBranchArray(List<Integer> branchArray) {
		this.branchArray = branchArray;
	}

	public List<Integer> getChangeArray() {
		return changeArray;
	}

	public void setChangeArray(List<Integer> changeArray) {
		this.changeArray = changeArray;
	}

	public List<Integer> getArtifactArray() {
		return artifactArray;
	}

	public void setArtifactArray(List<Integer> artifactArray) {
		this.artifactArray = artifactArray;
	}

	public List<Integer> getRoleArray() {
		return roleArray;
	}

	public void setRoleArray(List<Integer> roleArray) {
		this.roleArray = roleArray;
	}

	public List<Integer> getConfPRArray() {
		return confPRArray;
	}

	public void setConfPRArray(List<Integer> confPRArray) {
		this.confPRArray = confPRArray;
	}

	public Configuration getConfig() {
		return configArray;
	}

	public void setConfig(Configuration configArray) {
		this.configArray = configArray;
	}

	public DragAndDropCanvas getCanvas() {
		return canvas;
	}

	public void setCanvas(DragAndDropCanvas canvas) {
		this.canvas = canvas;
	}

	public List<String> getTagArray() {
		return TagArray;
	}

	public void setTagArray(List<String> tagArray) {
		TagArray = tagArray;
	}

	public Label getNameLB() {
		return nameLB;
	}

	public void setNameLB(Label nameLB) {
		this.nameLB = nameLB;
	}

	public Alerts getAlerts() {
		return alerts;
	}

	public void setAlerts(Alerts alerts) {
		this.alerts = alerts;

	}

	public BorderPane getDragBox() {
		return dragBox;
	}

	public void setDragBox(BorderPane dragBox) {
		this.dragBox = dragBox;
	}

	public FormControl getFormControl() {
		return formControl;
	}

	public void setFormControl(FormControl formControl) {
		this.formControl = formControl;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public int getFormID() {
		return indexForm;
	}

	public void setFormID(int formID) {
		this.indexForm = formID;
	}

}
