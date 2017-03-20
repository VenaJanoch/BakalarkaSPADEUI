package Obsluha;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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

import Forms.ActivityForm;
import Forms.ArtifactForm;
import Forms.BasicForm;
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
import Grafika.CanvasItem;
import Grafika.InfoBoxSegment;
import Grafika.NodeLink;
import SPADEPAC.Activity;
import SPADEPAC.Artifact;
import SPADEPAC.Branch;
import SPADEPAC.Change;
import SPADEPAC.Configuration;
import SPADEPAC.Criterion;
import SPADEPAC.Iteration;
import SPADEPAC.Milestone;
import SPADEPAC.ObjectFactory;
import SPADEPAC.Phase;
import SPADEPAC.Project;
import SPADEPAC.Role;
import SPADEPAC.WorkUnit;
import XML.ProcessGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Control {

	private FileChooser fileChooser;

	private boolean arrow;
	private boolean startArrow;

	private ArrayList<BasicForm> forms;
	private ArrayList<NodeLink> arrows;

	private int index;
	private NodeLink link;
	private IdentificatorCreater idCreater;
	private int id;

	private File file;
	private ProcessGenerator procesGener;
	private ObjectFactory objF;
	private Project project;

	private ObservableList<String> configObservable;
	private ArrayList<Configuration> configList;
	private ArrayList<Integer> configFormIndex;

	private ObservableList<String> branchObservable;
	private ArrayList<Branch> branchList;
	private ArrayList<Integer> branchFormIndex;

	private ObservableList<String> roleObservable;
	private ArrayList<Role> roleList;
	private ArrayList<Integer> roleFormIndex;

	private ObservableList<String> changeObservable;
	private ArrayList<Change> changeList;
	private ArrayList<Integer> changeFormIndex;

	private ObservableList<String> artifactObservable;
	private ArrayList<Artifact> artifactList;
	private ArrayList<Integer> artifactFormIndex;

	public Control() {

		idCreater = new IdentificatorCreater();
		procesGener = new ProcessGenerator();
		objF = new ObjectFactory();
		project = (Project) objF.createProject();

		configList = new ArrayList<>();
		configFormIndex = new ArrayList<>();
		configObservable = FXCollections.observableArrayList();

		roleList = new ArrayList<>();
		roleFormIndex = new ArrayList<>();
		setRoleObservable(FXCollections.observableArrayList());

		branchList = new ArrayList<>();
		branchFormIndex = new ArrayList<>();
		branchObservable = FXCollections.observableArrayList();
		branchObservable.add("New");

		setChangeList(new ArrayList<>());
		changeFormIndex = new ArrayList<>();
		changeObservable = FXCollections.observableArrayList();
		changeObservable.add("New");

		setArtifactList(new ArrayList<>());
		setArtifactFormIndex(new ArrayList<>());
		setArtifactObservable(FXCollections.observableArrayList());

		configureFileChooser();
		setArrow(false);
		setStartArrow(false);
		setForms(new ArrayList<>());
		getForms().add(0, new ProjectForm(this, project));
		arrows = new ArrayList<>();
		index = 1;
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

			id = idCreater.createLineID();
			link = new NodeLink(id);

			item.getCanvas().getChildren().add(link);

			getArrows().add(id, link);

			link.setStart(new Point2D(item.getTranslateX() + (item.getWidth()),
					item.getTranslateY() + (item.getHeight() / 2)));

			item.registerStartLink(id);

			setStartArrow(true);

		} else {

			link.setEnd(new Point2D(item.getTranslateX(), item.getTranslateY() + (item.getHeight() / 2)));
			item.registerEndLink(id);
			setStartArrow(false);

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

			Phase phase = (Phase) objF.createPhase();
			forms.add(index, new PhaseForm(item, this, Constans.phaseDragTextIndexs, phase));
			IDs[0] = index;
			IDs[1] = idCreater.createPhaseID();
			form.getPhaseArray().add(IDs[1], phase);
			index++;
			return IDs;

		case Iteration:
			Iteration iteration = (Iteration) objF.createIteration();
			forms.add(index, new IterationForm(item, this, Constans.iterationDragTextIndexs, iteration));
			IDs[0] = index;
			IDs[1] = idCreater.createIterationID();
			form.getIterationArray().add(IDs[1], iteration);
			index++;
			return IDs;

		case Activity:
			Activity activity = (Activity) objF.createActivity();
			forms.add(index, new ActivityForm(item, this, Constans.activityDragTextIndexs, activity));
			IDs[0] = index;
			index++;
			IDs[1] = idCreater.createActivityID();
			form.getActivityArray().add(IDs[1], activity);
			return IDs;

		case WorkUnit:
			WorkUnit unit = (WorkUnit) objF.createWorkUnit();
			forms.add(index, new WorkUnitForm(item, this));
			IDs[0] = index;
			IDs[1] = idCreater.createWorkUnitID();
			IDs[2] = form.getIdCreater().createWorkUnitID();
			form.getWorkUnitArray().add(IDs[2], unit);
			index++;
			return IDs;
		case Milestone:
			Milestone milestone = (Milestone) objF.createMilestone();
			forms.add(index, new MilestoneForm(item, this, milestone));
			IDs[0] = index;
			IDs[1] = idCreater.createMilestoneID();
			IDs[2] = form.getIdCreater().createMilestoneID();
			index++;
			return IDs;
		case Criterion:

			forms.add(index, new CriterionForm(item, this));
			IDs[0] = index;
			IDs[1] = idCreater.createCriterionID();
			IDs[2] = form.getIdCreater().createCriterionID();
			form.getCriterionnArray().add(IDs[2], (Criterion) objF.createCriterion());
			index++;
			return IDs;

		case Configuration:
			
			Configuration conf = (Configuration)objF.createConfiguration();
			forms.add(index, new ConfigurationForm(item, this, Constans.configurationDragTextIndexs, conf));
			IDs[0] = index;
			IDs[1] = idCreater.createConfigurationID();
			configList.add(IDs[1],conf);			
			configFormIndex.add(index);
			index++;
			return IDs;

		case ConfigPersonRelation:
			forms.add(index, new ConfigPersonRelationForm(item, this));
			IDs[0] = index;
			index++;
			IDs[1] = idCreater.createCPRID();
			return IDs;
		case Branch:
			
			Branch branch = (Branch)objF.createBranch();
			forms.add(index, new BranchForm(item, this));
			IDs[0] = index;
			IDs[1] = idCreater.createBranchID();
			IDs[2] = form.getIdCreater().createBranchID();
			form.getBranchArray().add(IDs[2], branch);
			index++;
			return IDs;
		case Change:
			
			Change change = (Change) objF.createChange();
			forms.add(index, new ChangeForm(item, this));
			IDs[0] = index;
			IDs[1] = idCreater.createChangeID();
			IDs[2] = form.getIdCreater().createChangeID();
			form.getChangeArray().add(IDs[2], change);
			index++;
			return IDs;
			
		case Artifact:
		
			Artifact artifact = (Artifact) objF.createArtifact();
			forms.add(index, new ArtifactForm(item, this, artifact));
			IDs[0] = index;
			IDs[1] = idCreater.createArtifactID();
			IDs[2] = form.getIdCreater().createArtifactID();
			form.getArtifactArray().add(IDs[2], artifact);
			artifactList.add(IDs[1],artifact);
			artifactFormIndex.add(index);
			index++;
			return IDs;
		case Role:
			Role role = (Role)objF.createRole();
			forms.add(index, new RoleForm(item, this, role));
			IDs[0] = index;
			IDs[1] = idCreater.createRoleID();
			roleList.add(IDs[1], role);
			roleFormIndex.add(index);
			index++;
			return IDs;

		default:
			return IDs;
		}
	}

	public void saveFile() {

		fileChooser.setTitle("Save Process");

		file = fileChooser.showSaveDialog(new Stage());
		if (file != null) {
			procesGener.saveProcess(file, project);
		}
	}

	public void openFile() {

		fileChooser.setTitle("Open Process");

		file = fileChooser.showOpenDialog(new Stage());
		if (file != null) {
			project = procesGener.readProcess(file);

		}

	}

	public XMLGregorianCalendar convertDate(LocalDate Ldate) {
		// Instant instant =
		// Instant.from(Ldate.atStartOfDay(ZoneId.systemDefault()));
		// Date date = Date.from(instant);
		// GregorianCalendar c = new GregorianCalendar();

		XMLGregorianCalendar dateXML = null;
		// try {
		//
		// c.setTime(date);
		// dateXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		//
		// } catch (DatatypeConfigurationException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return dateXML;
	}

	public void fillProject(String description, String name, LocalDate startDate, LocalDate endDate) {

		project.setDescription(description);
		project.setName(name);
		project.setEndDate(convertDate(endDate));
		project.setStartDate(convertDate(startDate));

	}

	public void fillPhase(BasicForm form, int ID, String description, String name, LocalDate endDate, int confIndex) {

		Phase phase = form.getPhaseArray().get(ID);
		phase.setDescription(description);
		phase.setName(name);
		phase.setEndDate(convertDate(endDate));

		System.out.println(confIndex + "fase" + ID + " fd" + configList.get(confIndex).getName());
		System.out.println(configFormIndex.toArray());
		phase.setConfiguration(configList.get(confIndex));
	}

	public void fillActivity(BasicForm form, int ID, String description, String name) {

		Activity activity = form.getActivityArray().get(ID);
		activity.setDescription(description);
		activity.setName(name);

	}

	public void fillIteration(BasicForm form, int ID, String description, String name, LocalDate startDate,
			LocalDate endDate, int confIndex) {

		Iteration iteration = form.getIterationArray().get(ID);
		iteration.setDescription(description);
		iteration.setName(name);
		iteration.setStartDate(convertDate(startDate));
		iteration.setEndDate(convertDate(endDate));
		iteration.setConfiguration(configList.get(confIndex));
	}

	public void fillWorkUnit(BasicForm form, int ID, String description, String name, int authorID, int asigneID,
			String priority, String severity, String type) {

		WorkUnit workUnit = form.getWorkUnitArray().get(ID);
		workUnit.setDescription(description);
		workUnit.setName(name);
		workUnit.setAssignee(roleList.get(asigneID));
		workUnit.setAuthor(roleList.get(authorID));
		workUnit.setPriority(priority);
		workUnit.setSeverity(severity);
		workUnit.setType(type);

	}

	public void fillMilestone(BasicForm form, int ID, String description, String name) {

		Milestone milestone = form.getMilestoneArray().get(ID);
		milestone.setDescription(description);
		milestone.setName(name);

	}

	public void fillCriterion(BasicForm form, int ID, String description, String name) {

		Criterion criterion = form.getCriterionnArray().get(ID);
		criterion.setDescription(description);
		criterion.setName(name);

	}

	public void fillConfiguration(Configuration conf, int ID, boolean isRelase, LocalDate Ldate, String name,
			int roleIndex) {

		conf.setIsRelease(isRelase);
		conf.setCreate(convertDate(Ldate));
		conf.setName(name);
		conf.setAuthor(roleList.get(roleIndex));
		configObservable.add(name);

	}

	public void fillBranch(BasicForm form, int ID, boolean isMain, String name, boolean isNew) {

		Branch branch = form.getBranchArray().get(ID);
		branch.setIsMain(isMain);
		branch.setName(name);

		if (isNew) {
			branchList.add(form.getCanvasItem().getIDs()[1], form.getBranchArray().get(ID));
			branchFormIndex.add(form.getCanvasItem().getIDs()[0]);
			branchObservable.add(name);
		}
	}

	public void fillChange(BasicForm form, int ID, String description, String name, boolean isNew, int artifactIndex) {

		Change change = form.getChangeArray().get(ID);
		change.setName(name);
		change.setDescriptoin(description);
		change.setArtifact(artifactList.get(artifactIndex));
		
		if (isNew) {
			changeList.add(form.getCanvasItem().getIDs()[1], form.getChangeArray().get(ID));
			changeFormIndex.add(form.getCanvasItem().getIDs()[0]);
			changeObservable.add(name);
		}
		
	}

	public void fillArtifact(Artifact artifact, int ID, String description, String name, LocalDate Ldate, String type,
			int roleIndex) {

	//	Artifact artifact = form.getArtifactArray().get(ID);
		artifact.setName(name);
		artifact.setDescriptoin(description);
		artifact.setCreated(convertDate(Ldate));
		artifact.setMimeType(type);
		artifact.setAuthor(roleList.get(roleIndex));
		artifactObservable.add(name);
		
		
	}

	public void fillRole(Role role, int ID, String description, String name, String type) {

		role.setName(name);
		role.setDescription(description);
		role.setType(type);

		roleObservable.add(name);
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

	public ArrayList<Configuration> getConfigList() {
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

	public ArrayList<Branch> getBranchList() {
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

	public ArrayList<Change> getChangeList() {
		return changeList;
	}

	public void setChangeList(ArrayList<Change> changeList) {
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

	public ArrayList<Artifact> getArtifactList() {
		return artifactList;
	}

	public void setArtifactList(ArrayList<Artifact> artifactList) {
		this.artifactList = artifactList;
	}

}
