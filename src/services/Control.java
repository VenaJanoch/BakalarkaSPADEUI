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

import org.xml.sax.SAXException;

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
import graphics.ItemContexMenu;
import graphics.NodeLink;
import graphics.WorkUnitLink;
import javafx.geometry.Point2D;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Control {

	private FileChooser fileChooser;
	private File file;
	private boolean firstSave;

	private boolean arrow;
	private boolean startArrow;
	private DragAndDropCanvas canvas;
	private ArrayList<BasicForm> forms;
	
	
	private ClassSwitcher classSwitcher;
	
	private LinkControl linkControl;

	private ProcessGenerator procesGener;
	private IdentificatorCreater idCreater;
	private ObjectFactory objF;
	private Project project;

	private FillFormsXML fillFormsXML;
	private FillForms fillForms;
	private FillCopyForms fillCopy;

	private ManipulationControl manipulation;
	private ItemContexMenu contexMenu;

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

		ProjectForm form111 = new ProjectForm(this, project, canvas);
		getForms().add(0, form111);

		project.setDescription("Project");
		project.setName("Project");

		project.setEndDate(convertDate(form111.getDate2DP().getValue()));
		project.setStartDate(convertDate(form111.getDate2DP().getValue()));
		lists = new SegmentLists(this, project);

		linkControl = new LinkControl(this, lists, objF);

		idCreater = new IdentificatorCreater();

		fillForms = new FillForms(this, lists, project, forms, objF, idCreater);
		fillFormsXML = new FillFormsXML(this, lists, project, forms, fillCopy, idCreater, linkControl);
		fillCopy = new FillCopyForms(this, getLists(), project, forms, objF, idCreater);
		manipulation = new ManipulationControl(this, fillCopy, project, lists);
		contexMenu = new ItemContexMenu(this, manipulation, canvas);

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

		

		firstSave = true;

	}

	public void showProjectForm() {

		forms.get(0).show();
	}

	public void restartControl() {

		procesGener = new ProcessGenerator();
		objF = new ObjectFactory();

		idCreater = new IdentificatorCreater();

		forms.clear();

		linkControl.getArrows().clear();
		canvas.restart();

		configureFileChooser();

		setArrow(false);
		setStartArrow(false);

		firstSave = true;

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

	

	public Double[] calculateArrowPosition(Point2D endPoint) {

		double x = endPoint.getX() - Constans.ArrowRadius;
		double yUP = endPoint.getY() + Constans.ArrowRadius;
		double yDW = endPoint.getY() - Constans.ArrowRadius;

		Double[] position = new Double[] { endPoint.getX(), endPoint.getY(), x, yUP, x, yDW };
		return position;

	}

	public Point2D calculateCenter(Point2D startPoint, Point2D endPoint) {

		Point2D point = null;
		double x = Math.abs((startPoint.getX() - endPoint.getX()) / 2);
		double y = Math.abs((startPoint.getY() - endPoint.getY()) / 2) + Constans.relationCBOffset;

		if (startPoint.getY() <= endPoint.getY()) {

			if (startPoint.getX() <= endPoint.getX()) {
				point = new Point2D(startPoint.getX() + x, startPoint.getY() + y);
			} else {
				point = new Point2D(startPoint.getX() - x, startPoint.getY() + y);
			}

		} else {

			if (startPoint.getX() <= endPoint.getX()) {

				point = new Point2D(startPoint.getX() + x, startPoint.getY() - y);
			} else {
				point = new Point2D(startPoint.getX() - x, startPoint.getY() - y);
			}
		}

		return point;
	}

	public static SegmentType findSegmentType(String segmentName) {

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

			return fillFormsXML.createPhaseFormXML(item, form, IDs);

		case Iteration:

			return fillFormsXML.createIterationFormXML(item, form, IDs);

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

		if (firstSave) {

			saveAsFile();
			firstSave = false;

		} else {
			procesGener.saveProcess(file, project);

		}

	}

	public void saveAsFile() {

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
			restartControl();
			project = procesGener.readProcess(file);

			ProjectForm form = new ProjectForm(this, project, canvas);
			forms.add(0, form);

			lists.restartLists(project);

			fillForms = new FillForms(this, lists, project, forms, objF, idCreater);
			fillCopy = new FillCopyForms(this, getLists(), project, forms, objF, idCreater);
			manipulation.restart(fillCopy, project);

			fillFormsXML = new FillFormsXML(this, lists, project, forms, fillCopy, idCreater, linkControl);
			fillFormsXML.fillProjectFromXML(form);

			parseProject();

		}

	}

	public void validate() {

		procesGener.validate(project);

	}

	private void parseProject() {

		fillFormsXML.fillPhasesFromXML(forms.get(0));
		fillFormsXML.fillIterationFromXML(forms.get(0));
		fillFormsXML.fillActivityFromXML(forms.get(0));
		fillFormsXML.fillWorkUnitFromXML(forms.get(0), project.getWorkUnitIndexs());
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

	public boolean workUnitControl(String estimate) {

		double estimated = 0;

		try {
			estimated = Double.parseDouble(estimate);
		} catch (NumberFormatException e) {
			Alerts.showWrongEstimatedTimeAlert();
			return false;
		}

		if (lists.getPriorityTypeList().isEmpty()) {
			Alerts.showNoText("Priority");
			return false;
		} else if (lists.getSeverityTypeList().isEmpty()) {
			Alerts.showNoText("Severity");
			return false;
		} else if (lists.getResolutionTypeList().isEmpty()) {
			Alerts.showNoText("Resolution");
			return false;
		} else if (lists.getStatusTypeList().isEmpty()) {
			Alerts.showNoText("Status");
			return false;
		} else if (lists.getTypeList().isEmpty()) {
			Alerts.showNoText("Type");
			return false;
		} else if (lists.getRoleList().isEmpty()) {
			Alerts.showNoText("Role-Type");
			return false;
		}

		return true;

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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ManipulationControl getManipulation() {
		return manipulation;
	}

	public void setManipulation(ManipulationControl manipulation) {
		this.manipulation = manipulation;
	}

	public ItemContexMenu getContexMenu() {
		return contexMenu;
	}

	public void setContexMenu(ItemContexMenu contexMenu) {
		this.contexMenu = contexMenu;
	}

	public LinkControl getLinkControl() {
		return linkControl;
	}

	public void setLinkControl(LinkControl linkControl) {
		this.linkControl = linkControl;
	}
	
	

}
