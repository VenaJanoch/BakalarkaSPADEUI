package Forms;

import java.util.ArrayList;
import java.util.List;

import Grafika.CanvasItem;
import Grafika.DragAndDropCanvas;
import Grafika.DragAndDropItem;
import Grafika.InfoBoxSegment;
import Grafika.MenuPanel;
import Obsluha.Constans;
import Obsluha.Control;
import Obsluha.IdentificatorCreater;
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

public class BasicForm extends Stage {

	private BorderPane mainPanel;
	private Scene scena;

	private String name;
	private Label nameLB;
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
	
	private List<Phase> phaseArray;
	private List<Iteration> iterationArray;
	private List<Activity> activityArray;
	private List<WorkUnit> workUnitArray;
	private List<Milestone> milestoneArray;
	private List<Criterion> criterionnArray;
	private Configuration configArray;
	private List<Branch> branchArray;
	private List<Change> changeArray;
	private List<Artifact> artifactArray;
	private List<Role> roleArray;
	private List<ConfigPersonRelation> confPRArray;
	
	private IdentificatorCreater idCreater;

	public BasicForm(CanvasItem item, Control control, int[] itemArray, int indexForm) {

		super();
		this.control = control;
		this.item = item;
		this.itemArray = itemArray;
		this.setIdCreater(new IdentificatorCreater());
		this.setTitle("Edit " + item.getType().name());
		this.dgItem = new DragAndDropItem(control, itemArray);
		this.canvas = new DragAndDropCanvas(control, indexForm);
		this.dragBox = new BorderPane();

		mainPanel = new BorderPane();

		this.setScene(creatSceneCanvas());

	}

	public BasicForm(CanvasItem item, Control control) {

		super();
		this.control = control;
		this.item = item;
		this.setIdCreater(new IdentificatorCreater());
		this.setTitle("Edit " + item.getType().name());
		mainPanel = new BorderPane();

		this.setScene(creatSceneProject());

	}

	public BasicForm(Control control) {
		super();
		this.control = control;
		this.setTitle("Edit Project");
		this.setIdCreater(new IdentificatorCreater());

		mainPanel = new BorderPane();

		this.setScene(creatSceneProject());

	}
	

	private Scene creatSceneCanvas() {

		scena = new Scene(createPanelCanvas(), Constans.formWidth, Constans.formHeight);

		return scena;
	}

	private Scene creatSceneProject() {

		scena = new Scene(creatPanelProject(), Constans.formWidth, Constans.formHeight);

		return scena;
	}

	private Parent createPanelCanvas() {
		creatPanelProject();
		dragBox.setTop(dgItem);
		dragBox.setCenter(canvas);
		
		mainPanel.setCenter(dragBox);
		mainPanel.setLeft(infoPart);
		return mainPanel;
	}

	private Parent creatPanelProject() {
		mainPanel.setPadding(new Insets(5));
		buttonBox = new HBox(5);
		infoPart = new GridPane();
		infoPart.setAlignment(Pos.CENTER);
		nameLB = new Label("Name: ");
		nameTF = new TextField();
		submitButton = new Button("OK");
		nameLB.setAlignment(Pos.CENTER_RIGHT);
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

	
	@Override
	public String toString() {
		
		return getName();
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

	public List<WorkUnit> getWorkUnitArray() {
		return workUnitArray;
	}

	public void setWorkUnitArray(List<WorkUnit> workUnitArray) {
		this.workUnitArray = workUnitArray;
	}

	public IdentificatorCreater getIdCreater() {
		return idCreater;
	}

	public void setIdCreater(IdentificatorCreater idCreater) {
		this.idCreater = idCreater;
	}

	public List<Milestone> getMilestoneArray() {
		return milestoneArray;
	}

	public void setMilestoneArray(List<Milestone> milestoneArray) {
		this.milestoneArray = milestoneArray;
	}

	public List<Criterion> getCriterionnArray() {
		return criterionnArray;
	}

	public void setCriterionnArray(List<Criterion> criterionnArray) {
		this.criterionnArray = criterionnArray;
	}

	
	public int[] getItemArray() {
		return itemArray;
	}

	public void setItemArray(int[] itemArray) {
		this.itemArray = itemArray;
	}

	public List<Branch> getBranchArray() {
		return branchArray;
	}

	public void setBranchArray(List<Branch> branchArray) {
		this.branchArray = branchArray;
	}

	public List<Change> getChangeArray() {
		return changeArray;
	}

	public void setChangeArray(List<Change> changeArray) {
		this.changeArray = changeArray;
	}

	public List<Artifact> getArtifactArray() {
		return artifactArray;
	}

	public void setArtifactArray(List<Artifact> artifactArray) {
		this.artifactArray = artifactArray;
	}

	public List<Role> getRoleArray() {
		return roleArray;
	}

	public void setRoleArray(List<Role> roleArray) {
		this.roleArray = roleArray;
	}

	public List<ConfigPersonRelation> getConfPRArray() {
		return confPRArray;
	}

	public void setConfPRArray(List<ConfigPersonRelation> confPRArray) {
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
	
	

}
