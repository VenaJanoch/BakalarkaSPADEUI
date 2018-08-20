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
import graphics.MainWindow;
import graphics.NodeLink;
import graphics.WorkUnitLink;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Control {
	/** Globální proměnné třídy **/
	private FileChooser fileChooser;
	private File file;
	private boolean firstSave;
	private boolean isClose;

	private DragAndDropCanvas canvas;
	private ArrayList<BasicForm> forms;

	private ClassSwitcher classSwitcher;

	private LinkControl linkControl;
	private DeleteControl deleteControl;

	private ProcessGenerator procesGener;
	private IdentificatorCreater idCreater;
	public static ObjectFactory objF;
	private Project project;

	private FormControl formControl;
	private FillFormsXML fillFormsXML;
	private FillForms fillForms;
	private FillCopyForms fillCopy;

	private ManipulationControl manipulation;
	private ItemContexMenu contexMenu;

	private SegmentLists lists;

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
	private MainWindow mainWindow;
	private boolean save = false;

	/**
	 * Konstruktor třídy Zinicializuje Globální proměnné třídy
	 * 
	 * @param mainWindow
	 *            MainWindow
	 */
	public Control(MainWindow mainWindow) {

		this.mainWindow = mainWindow;
		procesGener = new ProcessGenerator();
		objF = new ObjectFactory();
		project = (Project) objF.createProject();
		configureFileChooser();
		classSwitcher = new ClassSwitcher(this);

		setForms(new ArrayList<>());

		ProjectForm form111 = new ProjectForm(this, project, canvas);
		getForms().add(0, form111);

		lists = new SegmentLists(this, project);
		formControl = new FormControl(lists);

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

		firstSave = true;

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

		procesGener = new ProcessGenerator();
		objF = new ObjectFactory();

		idCreater = new IdentificatorCreater();

		forms.clear();

		canvas.restart();

		configureFileChooser();

		firstSave = true;

	}

	/**
	 * Metoda pro konfiguraci FileChooseru
	 */
	private void configureFileChooser() {

		fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML", "*.xml"));
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

	/**
	 * Rozhodne o vyvolání okna pro uložení nebo automatickém uložení do již
	 * zvoleného souboru
	 */
	public void saveFile() {

		if (file == null || firstSave) {

			saveAsFile();
			firstSave = false;
			save = true;
		} else {
			procesGener.saveProcess(file, project);

		}

	}

	/**
	 * Umožní zobrazení systémového okna pro výběr souboru k uložení. Načte
	 * soubor do globální proměnné file pro další polužití
	 */

	public void saveAsFile() {
		if (!save) {
			fileChooser.setTitle("Save Process");

			file = fileChooser.showSaveDialog(new Stage());

			if (file != null) {
				procesGener.saveProcess(file, project);
			}
		} else {
			save = false;
		}

	}

	/**
	 * Umožní zobrazneí systémového okna pro výběr souboru k načtení XML souboru
	 * s procesem Zavolá metody pro restartování datových struktur
	 */
	public void openFile() {

		fileChooser.setTitle("Open Process");

		file = fileChooser.showOpenDialog(new Stage());
		if (file != null) {
			mainWindow.openFile(file);
		}

	}

	/**
	 * Vytvoří nové instance s potřebnými třídamy a restartuje třídu Control
	 * 
	 * @param file
	 */
	public void openFile(File file) {
		Project tmpProject = procesGener.readProcess(file);
		if (tmpProject != null) {

			restartControl();
			project = tmpProject;
			ProjectForm form = new ProjectForm(this, project, canvas);
			forms.add(0, form);

			lists.restartLists(project);

			linkControl = new LinkControl(this, lists, objF, idCreater);
			linkControl.restart();
			deleteControl = new DeleteControl(this, lists, project);

			fillForms = new FillForms(this, lists, project, forms, objF, idCreater, deleteControl, formControl);
			fillCopy = new FillCopyForms(this, getLists(), project, forms, objF, idCreater, deleteControl, formControl);
			manipulation.restart(fillCopy, project, deleteControl, forms);

			fillFormsXML = new FillFormsXML(this, lists, project, forms, fillCopy, idCreater, linkControl,
					deleteControl, formControl);
			fillFormsXML.fillProjectFromXML(form);

			parseProject();
		}
	}

	/**
	 * Pomocná metoda pro validaci souboru
	 */

	public void validate() {

		procesGener.validate(project);

	}

	/**
	 * Pomocná metoda pro zpracování segmentů procesu
	 */

	private void parseProject() {

		fillFormsXML.fillPhasesFromXML(forms.get(0));
		fillFormsXML.fillIterationFromXML(forms.get(0));
		fillFormsXML.fillActivityFromXML(forms.get(0));
		fillFormsXML.fillWorkUnitFromXML(forms.get(0), project.getWorkUnitIndexs());
		fillFormsXML.createLinks(project.getLinks());
	}

	/**
	 * Umožní převedení data ve formátu LocalDate do formátu
	 * XMLGregorianCalendar pro uložení do XML
	 * 
	 * @param Ldate
	 *            LocalDate
	 * @return XMLGregorianCalendar
	 */
	public static XMLGregorianCalendar convertDate(LocalDate Ldate) {

		if (Ldate == null) {
			return null;
		}

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

	/**
	 * Umožní převedení data ve formátu XMLGregorianCalendar uloženého v XML do
	 * formátu LocalDate
	 * 
	 * @param xmlDate
	 *            XMLGregorianCalendar
	 * @return LocalDate
	 */
	public LocalDate convertDateFromXML(XMLGregorianCalendar xmlDate) {

		if (xmlDate == null) {
			return null;
		}

		Date date = xmlDate.toGregorianCalendar().getTime();
		Instant instant = date.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate localDate = zdt.toLocalDate();
		return localDate;
	}

	/** Getrs and Setrs ***/

	public SegmentLists getLists() {
		return lists;
	}

	public void setLists(SegmentLists lists) {
		this.lists = lists;
	}

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

	public FillCopyForms getFillCopy() {
		return fillCopy;
	}

	public void setFillCopy(FillCopyForms fillCopy) {
		this.fillCopy = fillCopy;
	}

	public boolean isClose() {
		return isClose;
	}

	public void setClose(boolean isClose) {
		this.isClose = isClose;
	}


	
	

}
