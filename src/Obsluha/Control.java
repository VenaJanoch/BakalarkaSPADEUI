package Obsluha;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import SPADEPAC.Configuration;
import SPADEPAC.Iteration;
import SPADEPAC.ObjectFactory;
import SPADEPAC.Phase;
import SPADEPAC.Project;
import SPADEPAC.WorkUnit;
import XML.ProcessGenerator;
import javafx.geometry.Point2D;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Control {

	private Desktop desktop = Desktop.getDesktop();
	private FileChooser fileChooser;

	private boolean arrow;
	private boolean startArrow;

	private ArrayList<BasicForm> forms;

	private List<Phase> phaseArray;
	private List<Iteration> iterationArray;
	private List<Activity> activityArray;
	private List<WorkUnit> workUnitArray;

	private ArrayList<NodeLink> arrows;

	private int index;
	private NodeLink link;
	private IdentificatorCreater idCreater;
	private int id;

	private File file;
	private ProcessGenerator procesGener;
	private ObjectFactory objF;
	private Project project;

	public Control() {

		idCreater = new IdentificatorCreater();
		procesGener = new ProcessGenerator();
		objF = new ObjectFactory();
		project = (Project) objF.createProject();

		phaseArray = project.getPhases();
		iterationArray = project.getIterations();
		activityArray = project.getActivities();
		workUnitArray = project.getWorkUnits();

		configureFileChooser();
		setArrow(false);
		setStartArrow(false);
		setForms(new ArrayList<>());
		getForms().add(0, new ProjectForm(this));
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

	public int[] createForm(CanvasItem item) {
		SegmentType sType = item.getType();
		int[] IDs = new int[2];
		switch (sType) {
		case Phase:
			forms.add(index, new PhaseForm(item, this));
			index++;
			IDs[0] = index - 1;
			IDs[1] = idCreater.createPhaseID();
			phaseArray.add(IDs[1], objF.createPhase());
			return IDs;
		case Iteration:
			forms.add(index, new IterationForm(item, this));
			IDs[0] = index;
			index++;
			IDs[1] = idCreater.createIterationID();
			iterationArray.add(IDs[1], objF.createIteration());
			return IDs;
		case Activity:
			forms.add(index, new ActivityForm(item, this));
			IDs[0] = index;
			index++;
			IDs[1] = idCreater.createActivityID();
			activityArray.add(IDs[1], objF.createActivity());
			return IDs;
		case WorkUnit:
			forms.add(index, new WorkUnitForm(item, this));
			IDs[0] = index;
			index++;
			IDs[1] = idCreater.createWorkUnitID();
			workUnitArray.add(IDs[1], objF.createWorkUnit());
			return IDs;
		case Milestone:
			forms.add(index, new MilestoneForm(item, this));
			IDs[0] = index;
			index++;
			IDs[1] = idCreater.createMilestoneID();
			return IDs;
		case Criterion:
			forms.add(index, new CriterionForm(item, this));
			IDs[0] = index;
			index++;
			IDs[1] = idCreater.createCriterionID();
			return IDs;
		case Configuration:
			forms.add(index, new ConfigurationForm(item, this));
			IDs[0] = index;
			index++;
			IDs[1] = idCreater.createConfigurationID();
			return IDs;
		case ConfigPersonRelation:
			forms.add(index, new ConfigPersonRelationForm(item, this));
			IDs[0] = index;
			index++;
			IDs[1] = idCreater.createCPRID();
			return IDs;
		case Branch:
			forms.add(index, new BranchForm(item, this));
			IDs[0] = index;
			index++;
			IDs[1] = idCreater.createBranchID();
			return IDs;
		case Change:
			forms.add(index, new ChangeForm(item, this));
			IDs[0] = index;
			index++;
			IDs[1] = idCreater.createChangeID();
			return IDs;
		case Artifact:
			forms.add(index, new ArtifactForm(item, this));
			IDs[0] = index;
			index++;
			IDs[1] = idCreater.createArtifactID();
			return IDs;
		case Role:
			forms.add(index, new RoleForm(item, this));
			IDs[0] = index;
			index++;
			IDs[1] = idCreater.createRoleID();
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

		}

	}

	public void fillProject(String description, String name) {

		project.setDescription(description);
		project.setName(name);
	}

	public void fillPhase(int ID, String description, String name) {

		Phase phase = phaseArray.get(ID);
		phase.setDescription(description);
		phase.setName(name);
		System.out.println(phaseArray.get(ID).getName());
	}

	public void fillActivity(int ID, String description, String name) {

		Activity activity = activityArray.get(ID);
		activity.setDescription(description);
		activity.setName(name);

	}

	public void fillIteration(int ID, String description, String name) {

		Iteration iteration = iterationArray.get(ID);
		iteration.setDescription(description);
		iteration.setName(name);

	}

	public void fillWorkUnit(int ID, String description, String name) {

		WorkUnit workUnit = workUnitArray.get(ID);
		workUnit.setDescription(description);
		workUnit.setName(name);

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

}
