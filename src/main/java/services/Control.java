package services;

import java.util.ArrayList;

import SPADEPAC.ObjectFactory;
import SPADEPAC.Project;
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
import graphics.MainWindow;
import javafx.geometry.Point2D;
import model.IdentificatorCreater;

public class Control {
	/** Globální proměnné třídy **/


	private BasicForm form;
	private ItemContexMenu contexMenu;

	private int indexForm;
	private boolean arrow;
	private boolean startArrow;


	private DragAndDropCanvas canvas;
	private ArrayList<BasicForm> forms;

	private ClassSwitcher classSwitcher;

	private LinkControl linkControl;
	private DeleteControl deleteControl;
	private IdentificatorCreater idCreater;

	private FormControl formControl;

	private FillFormsXML fillFormsXML;
	private FillForms fillForms;
	private FillCopyForms fillCopy;

	private ManipulationControl manipulation;


	/** Proměnné tabulkových formulářů */
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


	/**
	 * Konstruktor třídy Zinicializuje Globální proměnné třídy
	 * 
	 */
	public Control() {


		classSwitcher = new ClassSwitcher(this);

		setForms(new ArrayList<>());

		ProjectForm form111 = new ProjectForm();
		getForms().add(0, form111);


		formControl = new FormControl(); // Prendat jinam nemuze byt v modelu

		idCreater = new IdentificatorCreater();
		linkControl = new LinkControl(this, lists, objF, idCreater);
		deleteControl = new DeleteControl(this, lists, project);

		fillForms = new FillForms(this, lists, project, forms, objF, idCreater, deleteControl, formControl);
		fillFormsXML = new FillFormsXML(this, lists, project, forms, fillCopy, idCreater, linkControl, deleteControl,
				formControl);
		fillCopy = new FillCopyForms(this, getLists(), project, forms, objF, idCreater, deleteControl, formControl);
		manipulation = new ManipulationControl(this, fillCopy, project, lists, deleteControl, forms);
		contexMenu = new ItemContexMenu(this, manipulation, canvas);

		milestoneForm = new MilestoneForm(this, deleteControl, idCreater);
		CPRForm = new ConfigPersonRelationForm(this, deleteControl, idCreater);
		roleForm = new RoleForm(this, deleteControl, idCreater);
		priorityForm = new PriorityForm(this, deleteControl, idCreater);
		severityForm = new SeverityForm(this, deleteControl, idCreater);
		relationForm = new RelationForm(this, deleteControl, idCreater);
		resolutionForm = new ResolutionForm(this, deleteControl, idCreater);
		statusForm = new StatusForm(this, deleteControl, idCreater);
		branchFrom = new BranchForm(this, deleteControl, idCreater);
		setConfTableForm(new ConfigurationTableForm(this, deleteControl, idCreater));
		typeForm = new TypeForm(this, deleteControl, idCreater);



	}

	public CanvasItem createCanvasItem(){
		//Todo Nastavit canvasItem Do Listu vytvorit item se vsim potrebnym;

		CanvasItem ci = new CanvasItem(); //new CanvasItem(type, "New", control, control.getForms().get(indexForm), 0, x, y, contexMenu,
		//control.getLinkControl(), this);

		return ci;
	}

	/**
	 * Pomocná metoda pro zviditelnění formuláře pro Project
	 */
	public void showProjectForm() {

		forms.get(0).show();
		forms.get(0).toFront();
	}

	/**
	 * Slouží ke kontorle pozice prvku na plátně, při přejetí hranic plátna je
	 * prvek vrácen na okraj plátna
	 * 
	 * @param x
	 *            souřadnice prvku
	 * @param y
	 *            souřadnice prvku
	 * @return Point2D zkontrolovaná poloha
	 */
	public Point2D canvasItemPositionControl(double x, double y) {

		Point2D point = new Point2D(x, y);

		if (y <= 0) {
			point = new Point2D(x, 0);
		}

		if (x <= 0) {

			point = new Point2D(0, y);
		}

		if (x >= Constans.canvasMaxWidth) {
			point = new Point2D(Constans.canvasMaxWidth - Constans.offset, y);
		}

		if (y >= Constans.canvasMaxHeight) {
			point = new Point2D(x, Constans.canvasMaxHeight - Constans.offset);
		}

		return point;

	}

	/**
	 * Vymazání seznamů a znovu inicializování proměnných
	 */
	public void restartControl() {

	//	procesGener = new ProcessGenerator();
		objF = new ObjectFactory();

		idCreater = new IdentificatorCreater();

		forms.clear();

		canvas.restart();

	//	configureFileChooser();

	//	firstSave = true;

	}



	/**
	 * Vypočte polohu šipky ukazující směr propojení Work Unit
	 * 
	 * @param endPoint
	 * @return Double body s polohou
	 */
	public Double[] calculateArrowPosition(Point2D endPoint) {

		double x = endPoint.getX() - Constans.ArrowRadius;
		double yUP = endPoint.getY() + Constans.ArrowRadius;
		double yDW = endPoint.getY() - Constans.ArrowRadius;

		Double[] position = new Double[] { endPoint.getX(), endPoint.getY(), x, yUP, x, yDW };
		return position;

	}

	/**
	 * Vypočte střed spojovací čáry pro vložení boxu s výběrem relace
	 * 
	 * @param startPoint
	 *            startovní bod spojnice
	 * @param endPoint
	 *            koncový bod spojnice
	 * @return Point2D bod pro box
	 */
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

	/**
	 * Vypočte souřadnice pro zvýraznění spojnice při kliku na ni
	 * 
	 * @param startPoint
	 *            počáteční bod
	 * @param endPoint
	 *            koncový bod
	 * @return body pro vykreslení zvýraznění
	 */
	public Double[] countBackgroundPlygon(Point2D startPoint, Point2D endPoint) {

		Double[] points = new Double[8];
		points[0] = startPoint.getX();
		points[1] = startPoint.getY() + Constans.polygonHeight;
		points[2] = startPoint.getX();
		points[3] = startPoint.getY() - Constans.polygonHeight;
		points[4] = endPoint.getX();
		points[5] = endPoint.getY() - Constans.polygonHeight;
		points[6] = endPoint.getX();
		points[7] = endPoint.getY() + Constans.polygonHeight;
		return points;
	}

	/**
	 * Pomocná metoda pro určení výčtového typu SegmentType pomocí Stringu
	 * 
	 * @param segmentName
	 *            String s názvem segmentu
	 * @return SegmentType
	 */
	public static SegmentType findSegmentType(String segmentName) {

		for (int i = 0; i < SegmentType.values().length; i++) {

			if (SegmentType.values()[i].name().equals(segmentName)) {

				return SegmentType.values()[i];
			}

		}
		return null;

	}

	/**
	 * Metoda pro určení metody pro vytvoření konkrétního segmentu nebo elementu
	 * 
	 * @param item
	 *            CavasItem
	 * @param form
	 *            kořenový formulář
	 * @return identifikátory objektu pro CanvasItem
	 */
	public int[] createForm(CanvasItem item, BasicForm form) {
		SegmentType sType = item.getType();
		int[] IDs = new int[4];

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
		case Project:
			IDs[0] = 0;
			return IDs;
		default:
			return IDs;
		}
	}

	/**
	 * Metoda pro určení metody pro vytvoření a nakopírování dat z konkrétního
	 * segmentu nebo elementu v XML
	 * 
	 * @param item
	 *            CavasItem
	 * @param form
	 *            kořenový formulář
	 * @return identifikátory objektu pro CanvasItem
	 */
	public int[] createFormFromXML(CanvasItem item, BasicForm form) {
		SegmentType sType = item.getType();

		int[] IDs = new int[4];
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
		case Project:
			IDs[0] = 0;
			return IDs;

		default:
			return IDs;
		}
	}



	/** Getrs and Setrs ***/


	public ArrayList<BasicForm> getForms() {
		return forms;
	}

	public void setForms(ArrayList<BasicForm> forms) {
		this.forms = forms;
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

	public ManipulationControl getManipulation() {
		return manipulation;
	}

	public void setManipulation(ManipulationControl manipulation) {
		this.manipulation = manipulation;
	}

	public ItemContexMenu getContexMenu() {
		return contexMenu;
	}

	public LinkControl getLinkControl() {
		return linkControl;
	}

	public void setLinkControl(LinkControl linkControl) {
		this.linkControl = linkControl;
	}

	public FillCopyForms getFillCopy() {
		return fillCopy;
	}

	public void setFillCopy(FillCopyForms fillCopy) {
		this.fillCopy = fillCopy;
	}




	
	

}
