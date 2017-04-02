package Services;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import com.sun.glass.ui.CommonDialogs.FileChooserResult;

import AbstractForm.BasicForm;
import Forms.ActivityForm;
import Forms.ArtifactForm;
import Forms.BranchForm;
import Forms.ChangeForm;
import Forms.ConfigPersonRelationForm;
import Forms.ConfigurationForm;
import Forms.CriterionForm;
import Forms.IterationForm;
import Forms.MilestoneForm;
import Forms.PhaseForm;
import Forms.ProjectForm;
import Forms.RoleForm;
import Forms.WorkUnitForm;
import Graphics.CanvasItem;
import Graphics.DragAndDropCanvas;
import Graphics.InfoBoxSegment;
import Graphics.NodeLink;
import SPADEPAC.Activity;
import SPADEPAC.Artifact;
import SPADEPAC.ArtifactClass;
import SPADEPAC.Branch;
import SPADEPAC.Change;
import SPADEPAC.Configuration;
import SPADEPAC.Coordinates;
import SPADEPAC.Criterion;
import SPADEPAC.Iteration;
import SPADEPAC.Milestone;
import SPADEPAC.ObjectFactory;
import SPADEPAC.Phase;
import SPADEPAC.Project;
import SPADEPAC.Role;
import SPADEPAC.WorkUnit;
import SPADEPAC.WorkUnitPriorityClass;
import SPADEPAC.WorkUnitSeverityClass;
import SPADEPAC.WorkUnitTypeClass;
import XML.ProcessGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Control {

	private FileChooser fileChooser;

	private boolean arrow;
	private boolean startArrow;
	private DragAndDropCanvas canvas;
	private ArrayList<BasicForm> forms;
	private ArrayList<NodeLink> arrows;

	private NodeLink link;

	private int id;

	private File file;
	private ProcessGenerator procesGener;
	private ObjectFactory objF;
	private Project project;

	private ObservableList<String> configObservable;
	private List<Configuration> configList;
	private ArrayList<Integer> configFormIndex;

	private ObservableList<String> branchObservable;
	private List<Branch> branchList;
	private ArrayList<Integer> branchFormIndex;

	private ObservableList<String> roleObservable;
	private List<Role> roleList;
	private ArrayList<Integer> roleFormIndex;

	private ObservableList<String> changeObservable;
	private List<Change> changeList;
	private ArrayList<Integer> changeFormIndex;

	private ObservableList<String> criterionObservable;
	private List<Criterion> criterionList;
	private ArrayList<Integer> criterionFormIndex;

	private ObservableList<String> milestoneObservable;
	private List<Milestone> milestoneList;
	private ArrayList<Integer> milestoneFormIndex;

	private ObservableList<String> artifactObservable;
	private List<Artifact> artifactList;
	private ArrayList<Integer> artifactFormIndex;

	private FillFormsXML fillFormsXML;
	private FillForms fillForms;

	private MilestoneForm milestoneForm;
	public Control() {

		procesGener = new ProcessGenerator();
		objF = new ObjectFactory();
		project = (Project) objF.createProject();
		configureFileChooser();
		setArrow(false);
		setStartArrow(false);
		setForms(new ArrayList<>());
		getForms().add(0, new ProjectForm(this, project, canvas));

		fillForms = new FillForms(this, project, forms, objF);
		createLists();
		milestoneForm = new MilestoneForm(this);

		arrows = new ArrayList<>();

	}

	public void showProjectForm() {

		forms.get(0).show();
	}
	
	public void showMilestones() {
		milestoneForm.show();
	}


	private void updateIndexAndID() {

		fillForms.setIndex(fillFormsXML.getIndex());
		fillForms.setIdCreater(fillFormsXML.getIdCreater());

	}

	public void createLists() {
		configList = new ArrayList<>();
		configFormIndex = new ArrayList<>();
		configObservable = FXCollections.observableArrayList();

		roleList = project.getRoles();
		roleFormIndex = new ArrayList<>();
		setRoleObservable(FXCollections.observableArrayList());

		branchList = project.getBranches();
		branchFormIndex = new ArrayList<>();
		branchObservable = FXCollections.observableArrayList();
		branchObservable.add("New");

		changeList = project.getChanges();
		changeFormIndex = new ArrayList<>();
		changeObservable = FXCollections.observableArrayList();
		changeObservable.add("New");

		setArtifactList(project.getArtifacts());
		setArtifactFormIndex(new ArrayList<>());
		setArtifactObservable(FXCollections.observableArrayList());

		criterionList = project.getCriterions();
		criterionFormIndex = new ArrayList<>();
		criterionObservable = FXCollections.observableArrayList();

		milestoneList = project.getMilestones();
		milestoneFormIndex = new ArrayList<>();
		milestoneObservable = FXCollections.observableArrayList();

	}

	public void restartControl() {

		procesGener = new ProcessGenerator();
		objF = new ObjectFactory();
		project = (Project) objF.createProject();

		configList.clear();
		configFormIndex.clear();
		configObservable.clear();

		roleList.clear();
		roleFormIndex.clear();
		roleObservable.clear();

		branchList.clear();
		branchFormIndex.clear();
		branchObservable.clear();
		branchObservable.add("New");

		changeList.clear();
		changeFormIndex.clear();
		changeObservable.clear();
		changeObservable.add("New");

		artifactFormIndex.clear();
		artifactList.clear();
		artifactObservable.clear();

		configureFileChooser();

		setArrow(false);
		setStartArrow(false);
		forms.clear();
		getForms().add(0, new ProjectForm(this, project, canvas));

		arrows.clear();
		canvas.restart();

	}

	private void configureFileChooser() {

		fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML", "*.xml"));
	}

	public boolean changeArrow() {

		if (arrow) {

			arrow = false;

		} else {
			arrow = true;
		}

		return arrow;

	}

	public void ArrowManipulation(CanvasItem item) {

		if (!isStartArrow()) {

			// id = idCreater.createLineID();
			link = new NodeLink(id);

			sortSegment(item);

			item.getCanvas().getChildren().add(link);

			getArrows().add(id, link);

			link.setStart(new Point2D(item.getTranslateX() + (item.getWidth()),
					item.getTranslateY() + (item.getHeight() / 2)));

			item.registerStartLink(id);

			setStartArrow(true);

		} else {

			sortSegment(item);
			link.setEnd(new Point2D(item.getTranslateX(), item.getTranslateY() + (item.getHeight() / 2)));
			item.registerEndLink(id);
			setStartArrow(false);
			setChangeArtifactRelation(link);

		}

	}

	public void setChangeArtifactRelation(NodeLink link) {

		int changeIndex = link.getChange()[1];
		int changeFormIndex = link.getChange()[0];
		int artifactIndex = link.getArtifact()[1];

		changeList.get(changeIndex).setArtifactIndex(artifactIndex);
	}

	private void sortSegment(CanvasItem item) {

		if (item.getType() == SegmentType.Change) {
			link.setChange(item.getIDs());
		} else {
			link.setArtifact(item.getIDs());
		}

	}

	public SegmentType findSegmentType(String segmentName) {

		for (int i = 0; i < SegmentType.values().length; i++) {

			if (SegmentType.values()[i].name().equals(segmentName)) {

				return SegmentType.values()[i];
			}

		}
		return null;

	}

	public int[] createForm(CanvasItem item, BasicForm form) {
		SegmentType sType = item.getType();
		int[] IDs = new int[3];

		switch (sType) {
		case Phase:

			return fillForms.createPhase(item, form, IDs);

		case Iteration:

			return fillForms.createIteration(item, form, IDs);

		case Activity:

			return fillForms.createActivity(item, form, IDs);

		case WorkUnit:
			return fillForms.createWorkUnit(item, form, IDs);

		case Milestone:
			// Milestone milestone = (Milestone) objF.createMilestone();
			// forms.add(index, new MilestoneForm(item, this, milestone));
			// IDs[0] = index;
			// IDs[1] = idCreater.createMilestoneID();
			// IDs[2] = form.getIdCreater().createMilestoneID();
			// index++;
			// return IDs;
		case Criterion:

			// forms.add(index, new CriterionForm(item, this));
			// IDs[0] = index;
			// IDs[1] = idCreater.createCriterionID();
			// IDs[2] = form.getIdCreater().createCriterionID();
			// form.getCriterionnArray().add(IDs[2], (Criterion)
			// objF.createCriterion());
			// index++;
			// return IDs;

		case Configuration:
			return fillForms.createConfigruration(item, form, IDs);

		case ConfigPersonRelation:
			// forms.add(index, new ConfigPersonRelationForm(item, this));
			// IDs[0] = index;
			// index++;
			// IDs[1] = idCreater.createCPRID();
			// return IDs;
		case Branch:
			return fillForms.createBranch(item, form, IDs);
		case Change:
			return fillForms.createChange(item, form, IDs);

		case Artifact:
			return fillForms.createArtifact(item, form, IDs);
		case Role:
			return fillForms.createRole(item, form, IDs);
		default:
			return IDs;
		}
	}

	public int[] createFormFromXML(CanvasItem item, BasicForm form) {
		SegmentType sType = item.getType();

		int[] IDs = new int[3];
		int indexConfig = -1;
		switch (sType) {
		case Phase:

			return fillFormsXML.createPhaseFormXML(item, form, IDs, indexConfig);

		case Iteration:

			return fillFormsXML.createIterationFormXML(item, form, IDs, indexConfig);

		case Activity:

			return fillFormsXML.createActivityFormXML(item, form, IDs);

		case WorkUnit:

			return fillFormsXML.createWorkUnitFormXML(item, form, IDs);
		case Milestone:
			// Milestone milestone = (Milestone) objF.createMilestone();
			// forms.add(index, new MilestoneForm(item, this, milestone));
			// IDs[0] = index;
			// IDs[1] = idCreater.createMilestoneID();
			// IDs[2] = form.getIdCreater().createMilestoneID();
			// index++;
			// return IDs;
		case Criterion:

			// forms.add(index, new CriterionForm(item, this));
			// IDs[0] = index;
			// IDs[1] = idCreater.createCriterionID();
			// IDs[2] = form.getIdCreater().createCriterionID();
			// form.getCriterionnArray().add(IDs[2], (Criterion)
			// objF.createCriterion());
			// index++;
			// return IDs;

		case Configuration:

			return fillFormsXML.createConfigurationFormXML(item, form, IDs);

		case ConfigPersonRelation:
			// forms.add(index, new ConfigPersonRelationForm(item, this));
			// IDs[0] = index;
			// index++;
			// IDs[1] = idCreater.createCPRID();
			// return IDs;
		case Branch:

			return fillFormsXML.createBranchFormXML(item, form, IDs);

		case Change:

			return fillFormsXML.createChangeFormXML(item, form, IDs);

		case Artifact:

			return fillFormsXML.createArtifactFormXML(item, form, IDs);
		case Role:
			return fillFormsXML.createRoleFormXML(item, form, IDs);

		default:
			return IDs;
		}
	}

	public boolean checkConfiguration(String newConfName) {

		for (int i = 0; i < configObservable.size(); i++) {

			if (configObservable.get(i).equals(newConfName)) {
				return false;
			}

		}

		return true;
	}

	public void saveFile() {

		fileChooser.setTitle("Save Process");

		file = fileChooser.showSaveDialog(new Stage());
		System.out.println(project.getChanges().toString());
		if (file != null) {
			procesGener.saveProcess(file, project);
		}
	}

	public void openFile() {

		fileChooser.setTitle("Open Process");

		file = fileChooser.showOpenDialog(new Stage());
		if (file != null) {
			restartControl();
			project = procesGener.readProcess(file);
			forms.clear();
			ProjectForm form = new ProjectForm(this, project, canvas);
			roleList = project.getRoles();
			changeList = project.getChanges();
			branchList = project.getBranches();
			artifactList = project.getArtifacts();

			fillFormsXML = new FillFormsXML(this, project, forms);
			fillFormsXML.fillProjectFromXML(form);

			System.out.println(form.getName() + " name");
			forms.add(0, form);

			parseProject();

		}

	}

	private void parseProject() {

		fillFormsXML.fillRoleFromXML(forms.get(0), project.getRoles());
		fillFormsXML.fillPhasesFromXML(forms.get(0));
		fillFormsXML.fillIterationFromXML(forms.get(0));
		fillFormsXML.fillActivityFromXML(forms.get(0));
		fillFormsXML.fillWorkUnitFromXML(forms.get(0), project.getWorkUnits());
	}

	public XMLGregorianCalendar convertDate(LocalDate Ldate) {
		Instant instant = Instant.from(Ldate.atStartOfDay(ZoneId.systemDefault()));
		Date date = Date.from(instant);
		GregorianCalendar c = new GregorianCalendar();

		XMLGregorianCalendar dateXML = null;
		try {

			c.setTime(date);
			dateXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateXML;
	}

	public LocalDate convertDateFromXML(XMLGregorianCalendar xmlDate) {

		Date date = xmlDate.toGregorianCalendar().getTime();
		Instant instant = date.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate localDate = zdt.toLocalDate();
		return localDate;
	}

	/** Getrs and Setrs ***/

	public boolean isArrow() {
		return arrow;
	}

	public void setArrow(boolean arrow) {
		this.arrow = arrow;
	}

	public ArrayList<BasicForm> getForms() {
		return forms;
	}

	public void setForms(ArrayList<BasicForm> forms) {
		this.forms = forms;
	}

	public boolean isStartArrow() {
		return startArrow;
	}

	public void setStartArrow(boolean startArrow) {
		this.startArrow = startArrow;
	}

	public ArrayList<NodeLink> getArrows() {
		return arrows;
	}

	public void setArrows(ArrayList<NodeLink> arrows) {
		this.arrows = arrows;
	}

	public ObservableList<String> getConfigObservable() {
		return configObservable;
	}

	public void setConfigObservable(ObservableList<String> configObservable) {
		this.configObservable = configObservable;
	}

	public List<Configuration> getConfigList() {
		return configList;
	}

	public void setConfigList(ArrayList<Configuration> configList) {
		this.configList = configList;
	}

	public ObservableList<String> getRoleObservable() {
		return roleObservable;
	}

	public void setRoleObservable(ObservableList<String> roleObservable) {
		this.roleObservable = roleObservable;
	}

	public ObservableList<String> getBranchObservable() {
		return branchObservable;
	}

	public void setBranchObservable(ObservableList<String> branchObservable) {
		this.branchObservable = branchObservable;
	}

	public List<Branch> getBranchList() {
		return branchList;
	}

	public void setBranchList(ArrayList<Branch> branchList) {
		this.branchList = branchList;
	}

	public ObservableList<String> getChangeObservable() {
		return changeObservable;
	}

	public void setChangeObservable(ObservableList<String> changeObservable) {
		this.changeObservable = changeObservable;
	}

	public List<Change> getChangeList() {
		return changeList;
	}

	public void setChangeList(List<Change> changeList) {
		this.changeList = changeList;
	}

	public ObservableList<String> getArtifactObservable() {
		return artifactObservable;
	}

	public void setArtifactObservable(ObservableList<String> artifactObservable) {
		this.artifactObservable = artifactObservable;
	}

	public ArrayList<Integer> getArtifactFormIndex() {
		return artifactFormIndex;
	}

	public void setArtifactFormIndex(ArrayList<Integer> artifactFormIndex) {
		this.artifactFormIndex = artifactFormIndex;
	}

	public List<Artifact> getArtifactList() {
		return artifactList;
	}

	public void setArtifactList(List<Artifact> artifactList) {
		this.artifactList = artifactList;
	}

	public DragAndDropCanvas getCanvas() {
		return canvas;
	}

	public void setCanvas(DragAndDropCanvas canvas) {
		this.canvas = canvas;
	}

	public ArrayList<Integer> getConfigFormIndex() {
		return configFormIndex;
	}

	public void setConfigFormIndex(ArrayList<Integer> configFormIndex) {
		this.configFormIndex = configFormIndex;
	}

	public ArrayList<Integer> getBranchFormIndex() {
		return branchFormIndex;
	}

	public void setBranchFormIndex(ArrayList<Integer> branchFormIndex) {
		this.branchFormIndex = branchFormIndex;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public ArrayList<Integer> getRoleFormIndex() {
		return roleFormIndex;
	}

	public void setRoleFormIndex(ArrayList<Integer> roleFormIndex) {
		this.roleFormIndex = roleFormIndex;
	}

	public ArrayList<Integer> getChangeFormIndex() {
		return changeFormIndex;
	}

	public void setChangeFormIndex(ArrayList<Integer> changeFormIndex) {
		this.changeFormIndex = changeFormIndex;
	}

	public FillFormsXML getFillFormsXML() {
		return fillFormsXML;
	}

	public void setFillFormsXML(FillFormsXML fillFormsXML) {
		this.fillFormsXML = fillFormsXML;
	}

	public FillForms getFillForms() {
		return fillForms;
	}

	public void setFillForms(FillForms fillForms) {
		this.fillForms = fillForms;
	}

	public ObservableList<String> getCriterionObservable() {
		return criterionObservable;
	}

	public void setCriterionObservable(ObservableList<String> criterionObservable) {
		this.criterionObservable = criterionObservable;
	}

	public List<Criterion> getCriterionList() {
		return criterionList;
	}

	public void setCriterionList(List<Criterion> criterionList) {
		this.criterionList = criterionList;
	}

	public ArrayList<Integer> getCriterionFormIndex() {
		return criterionFormIndex;
	}

	public void setCriterionFormIndex(ArrayList<Integer> criterionFormIndex) {
		this.criterionFormIndex = criterionFormIndex;
	}

	public ObservableList<String> getMilestoneObservable() {
		return milestoneObservable;
	}

	public void setMilestoneObservable(ObservableList<String> milestoneObservable) {
		this.milestoneObservable = milestoneObservable;
	}

	public List<Milestone> getMilestoneList() {
		return milestoneList;
	}

	public void setMilestoneList(List<Milestone> milestoneList) {
		this.milestoneList = milestoneList;
	}

	public ArrayList<Integer> getMilestoneFormIndex() {
		return milestoneFormIndex;
	}

	public void setMilestoneFormIndex(ArrayList<Integer> milestoneFormIndex) {
		this.milestoneFormIndex = milestoneFormIndex;
	}

	
	

}
