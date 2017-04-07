package services;

import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


import SPADEPAC.Link;
import SPADEPAC.ObjectFactory;
import SPADEPAC.Project;
import XML.ProcessGenerator;
import abstractform.BasicForm;
import forms.BranchForm;
import forms.ConfigPersonRelationForm;
import forms.ConfigurationTableForm;
import forms.MilestoneForm;
import forms.PriorityForm;
import forms.ProjectForm;
import forms.RelationForm;
import forms.ResolutionForm;
import forms.RoleForm;
import forms.SeverityForm;
import forms.StatusForm;
import forms.TypeForm;
import graphics.CanvasItem;
import graphics.DragAndDropCanvas;
import graphics.NodeLink;
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
	private ClassSwitcher classSwitcher;
	private int id;

	private File file;
	private ProcessGenerator procesGener;
	private ObjectFactory objF;
	private Project project;

	private FillFormsXML fillFormsXML;
	private FillForms fillForms;
	private SegmentLists lists;

	private MilestoneForm milestoneForm;
	private ConfigPersonRelationForm CPRForm;
	private RoleForm roleForm;
	private PriorityForm priorityForm;
	private SeverityForm severityForm;
	private RelationForm relationForm;
	private ResolutionForm resolutionForm;
	private StatusForm statusForm;
	private BranchForm branchFrom;
	private ConfigurationTableForm confTableForm;
	private TypeForm typeForm;

	public Control() {

		procesGener = new ProcessGenerator();
		objF = new ObjectFactory();
		project = (Project) objF.createProject();
		configureFileChooser();
		classSwitcher = new ClassSwitcher(this);
		setArrow(false);
		setStartArrow(false);
		setForms(new ArrayList<>());
		getForms().add(0, new ProjectForm(this, project, canvas));

		lists = new SegmentLists(this, project);

		fillForms = new FillForms(this, lists, project, forms, objF);
		milestoneForm = new MilestoneForm(this);
		CPRForm = new ConfigPersonRelationForm(this);
		roleForm = new RoleForm(this);
		priorityForm = new PriorityForm(this);
		severityForm = new SeverityForm(this);
		relationForm = new RelationForm(this);
		resolutionForm = new ResolutionForm(this);
		statusForm = new StatusForm(this);
		branchFrom = new BranchForm(this);
		setConfTableForm(new ConfigurationTableForm(this));
		typeForm = new TypeForm(this);
		
		arrows = new ArrayList<>();

	}

	public void showProjectForm() {

		forms.get(0).show();
	}

	private void updateIndexAndID() {

		fillForms.setIndex(fillFormsXML.getIndex());
		fillForms.setIdCreater(fillFormsXML.getIdCreater());

	}

	public void restartControl() {

		procesGener = new ProcessGenerator();
		objF = new ObjectFactory();

		lists.restartLists();

		configureFileChooser();

		setArrow(false);
		setStartArrow(false);
		forms.clear();

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

			id = IdentificatorCreater.createLineID();
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
		// int changeFormIndex = link.getChange()[0];
		int artifactIndex = link.getArtifact()[1];
		Link linkP = objF.createLink();

		linkP.setArtifactIndex(artifactIndex);
		linkP.setChangeIndex(changeIndex);

		lists.getLinksList().add(linkP);

		lists.getChangeList().get(changeIndex).setArtifactIndex(artifactIndex);

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

		case Configuration:
			return fillForms.createConfigruration(item, form, IDs);
		case Change:
			return fillForms.createChange(item, form, IDs);

		case Artifact:
			return fillForms.createArtifact(item, form, IDs);
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

		case Configuration:

			return fillFormsXML.createConfigurationFormXML(item, form, IDs);

		case Change:

			return fillFormsXML.createChangeFormXML(item, form, IDs);

		case Artifact:

			return fillFormsXML.createArtifactFormXML(item, form, IDs);

		default:
			return IDs;
		}
	}

	public boolean checkConfiguration(String newConfName) {

		for (int i = 0; i < lists.getConfigObservable().size(); i++) {

			if (lists.getConfigObservable().get(i).equals(newConfName)) {
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
			ProjectForm form = new ProjectForm(this, project, canvas);
			lists.setRoleList(project.getRoles());
			lists.setChangeList(project.getChanges());
			lists.setBranchList(project.getBranches());
			lists.setArtifactList(project.getArtifacts());
			lists.setRoleTypeList(project.getRoleType());
			lists.setConfigList(project.getConfiguration());

			forms.add(0, form);
			fillFormsXML = new FillFormsXML(this, lists, project, forms);
			fillFormsXML.fillProjectFromXML(form);

			parseProject();

		}

	}

	private void parseProject() {

		fillFormsXML.fillPhasesFromXML(forms.get(0));
		fillFormsXML.fillIterationFromXML(forms.get(0));
		fillFormsXML.fillActivityFromXML(forms.get(0));
		fillFormsXML.fillWorkUnitFromXML(forms.get(0), project.getWorkUnits());
		fillFormsXML.createLinks(project.getLinks());
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

	public SegmentLists getLists() {
		return lists;
	}

	public void setLists(SegmentLists lists) {
		this.lists = lists;
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

	public DragAndDropCanvas getCanvas() {
		return canvas;
	}

	public void setCanvas(DragAndDropCanvas canvas) {
		this.canvas = canvas;
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

	public MilestoneForm getMilestoneForm() {
		return milestoneForm;
	}

	public void setMilestoneForm(MilestoneForm milestoneForm) {
		this.milestoneForm = milestoneForm;
	}

	public ConfigPersonRelationForm getCPRForm() {
		return CPRForm;
	}

	public void setCPRForm(ConfigPersonRelationForm cPRForm) {
		CPRForm = cPRForm;
	}

	public RoleForm getRoleForm() {
		return roleForm;
	}

	public PriorityForm getPriorityForm() {
		return priorityForm;
	}

	public SeverityForm getSeverityForm() {
		return severityForm;
	}

	public RelationForm getRelationForm() {
		return relationForm;
	}

	public ResolutionForm getResolutionForm() {
		return resolutionForm;
	}

	public StatusForm getStatusForm() {
		return statusForm;
	}

	public BranchForm getBranchFrom() {
		return branchFrom;
	}

	public void setBranchFrom(BranchForm branchFrom) {
		this.branchFrom = branchFrom;
	}

	public ConfigurationTableForm getConfTableForm() {
		return confTableForm;
	}

	public void setConfTableForm(ConfigurationTableForm confTableForm) {
		this.confTableForm = confTableForm;
	}

	public TypeForm getTypeForm() {
		return typeForm;
	}

	public void setTypeForm(TypeForm typeForm) {
		this.typeForm = typeForm;
	}

}
