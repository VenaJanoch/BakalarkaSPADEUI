package Obsluha;

import java.time.LocalDate;
import java.util.ArrayList;

import Forms.ActivityForm;
import Forms.ArtifactForm;
import Forms.BasicForm;
import Forms.BranchForm;
import Forms.ChangeForm;
import Forms.ConfigurationForm;
import Forms.IterationForm;
import Forms.PhaseForm;
import Forms.RoleForm;
import Forms.WorkUnitForm;
import Grafika.CanvasItem;
import SPADEPAC.Activity;
import SPADEPAC.Artifact;
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

public class FillForms {

	private Control control;
	private Project project;
	private ArrayList<BasicForm> forms;
	private ObjectFactory objF;

	private int index;
	private IdentificatorCreater idCreater;

	public FillForms(Control control, Project project, ArrayList<BasicForm> forms, ObjectFactory objFac) {

		this.control = control;
		this.project = project;
		this.forms = forms;

		this.objF = objFac;

		idCreater = new IdentificatorCreater();
		index = 1;
		
	}

	public void fillProject(String description, String name, LocalDate startDate, LocalDate endDate) {

		project.setDescription(description);
		project.setName(name);
		project.setEndDate(control.convertDate(endDate));
		project.setStartDate(control.convertDate(startDate));
		


	}

	public void fillPhase(BasicForm form, int ID, String description, String name, LocalDate endDate, int confIndex,
			int x, int y) {

		Phase phase = form.getPhaseArray().get(ID);
		phase.setDescription(description);
		phase.setName(name);
		phase.setEndDate(control.convertDate(endDate));
		Coordinates coord = objF.createCoordinates();
		coord.setXCoordinate(x);
		coord.setYCoordinate(y);
		phase.setCoordinates(coord);
		phase.setConfiguration(control.getConfigList().get(confIndex));
	}

	public int[] createPhase(CanvasItem item, BasicForm form, int[] IDs) {

		Phase phase = (Phase) objF.createPhase();
		forms.add(index, new PhaseForm(item, control, Constans.phaseDragTextIndexs, phase, index));
		IDs[0] = index;
		IDs[1] = idCreater.createPhaseID();

		form.getPhaseArray().add(IDs[1], phase);
		index++;
		return IDs;
	}

	public void fillActivity(BasicForm form, int ID, String description, String name, int x, int y) {

		Activity activity = form.getActivityArray().get(ID);
		activity.setDescription(description);
		activity.setName(name);
		Coordinates coord = objF.createCoordinates();
		coord.setXCoordinate(x);
		coord.setYCoordinate(y);
		activity.setCoordinates(coord);
	}

	public int[] createActivity(CanvasItem item, BasicForm form, int[] IDs) {
		Activity activity = (Activity) objF.createActivity();
		forms.add(index, new ActivityForm(item, control, Constans.activityDragTextIndexs, activity, index));
		IDs[0] = index;
		index++;
		IDs[1] = idCreater.createActivityID();
		form.getActivityArray().add(IDs[1], activity);
		return IDs;
	}

	public void fillIteration(BasicForm form, int ID, String description, String name, LocalDate startDate,
			LocalDate endDate, int confIndex, int x, int y) {

		Iteration iteration = form.getIterationArray().get(ID);
		iteration.setDescription(description);
		iteration.setName(name);
		iteration.setStartDate(control.convertDate(startDate));
		iteration.setEndDate(control.convertDate(endDate));
		iteration.setConfiguration(control.getConfigList().get(confIndex));
		Coordinates coord = objF.createCoordinates();
		coord.setXCoordinate(x);
		coord.setYCoordinate(y);
		iteration.setCoordinates(coord);
	}

	public int[] createIteration(CanvasItem item, BasicForm form, int[] IDs) {
		Iteration iteration = (Iteration) objF.createIteration();
		forms.add(index, new IterationForm(item, control, Constans.iterationDragTextIndexs, iteration, index));
		IDs[0] = index;
		IDs[1] = idCreater.createIterationID();
		form.getIterationArray().add(IDs[1], iteration);
		index++;
		return IDs;
	}

	public void fillWorkUnit(BasicForm form, int ID, String description, String name, int authorIndex, int assigneIndex,
			String priority, String severity, String type, int x, int y, int priorityIndex, int severityIndex,
			int typeIndex) {

		WorkUnit workUnit = form.getWorkUnitArray().get(ID);
		workUnit.setDescription(description);
		workUnit.setName(name);
		workUnit.setAssignee(control.getRoleList().get(assigneIndex));
		workUnit.setAuthor(control.getRoleList().get(authorIndex));
		workUnit.setPriority(priority);
		workUnit.setSeverity(severity);
		workUnit.setType(type);
		workUnit.setPriorityIndex(priorityIndex);
		workUnit.setSeverityIndex(severityIndex);
		workUnit.setTypeIndex(typeIndex);
		workUnit.setAssigneIndex(assigneIndex);
		workUnit.setAuthorIndex(authorIndex);

		Coordinates coord = objF.createCoordinates();
		coord.setXCoordinate(x);
		coord.setYCoordinate(y);
		workUnit.setCoordinates(coord);

	}

	public int[] createWorkUnit(CanvasItem item, BasicForm form, int[] IDs) {

		WorkUnit unit = (WorkUnit) objF.createWorkUnit();
		forms.add(index, new WorkUnitForm(item, control, unit));
		IDs[0] = index;
		IDs[1] = idCreater.createWorkUnitID();
		IDs[2] = form.getIdCreater().createWorkUnitID();
		form.getWorkUnitArray().add(IDs[2], unit);
		index++;
		return IDs;
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
			int roleIndex, int x, int y) {

		Configuration config = control.getConfigList().get(ID);
		config.setIsRelease(isRelase);
		config.setCreate(control.convertDate(Ldate));
		config.setName(name);
		config.setAuthor(control.getRoleList().get(roleIndex));
		config.setAuthorIndex(roleIndex);
		control.getConfigObservable().add(name);

		Coordinates coord = objF.createCoordinates();
		coord.setXCoordinate(x);
		coord.setYCoordinate(y);
		config.setCoordinates(coord);

	}

	public int[] createConfigruration(CanvasItem item, BasicForm form, int[] IDs) {
		Configuration conf = (Configuration) objF.createConfiguration();
		forms.add(index, new ConfigurationForm(item, control, Constans.configurationDragTextIndexs, conf, index));
		IDs[0] = index;
		IDs[1] = idCreater.createConfigurationID();
		System.out.println("Nevim " + IDs[1]);
		control.getConfigList().add(IDs[1], conf);
		control.getConfigFormIndex().add(index);
		index++;
		return IDs;

	}

	public void fillBranch(BasicForm form, int ID, boolean isMain, String name, boolean isNew, int x, int y) {

		Branch branch = form.getBranchArray().get(ID);
		branch.setIsMain(isMain);
		branch.setName(name);
		Coordinates coord = objF.createCoordinates();
		coord.setXCoordinate(x);
		coord.setYCoordinate(y);
		branch.setCoordinates(coord);

		if (isNew) {
			control.getBranchList().add(form.getCanvasItem().getIDs()[1], branch);
			control.getBranchFormIndex().add(form.getCanvasItem().getIDs()[0]);
			control.getBranchObservable().add(name);
		}
	}

	public int[] createBranch(CanvasItem item, BasicForm form, int[] IDs) {

		Branch branch = (Branch) objF.createBranch();
		forms.add(index, new BranchForm(item, control));
		IDs[0] = index;
		IDs[1] = idCreater.createBranchID();
		IDs[2] = form.getIdCreater().createBranchID();
		System.out.println(IDs[1] + " Branch " + form.getTitle());

		form.getBranchArray().add(IDs[2], branch);
		index++;
		return IDs;

	}

	public void fillChange(BasicForm form, int ID, String description, String name, boolean isNew, int artifactIndex,
			int x, int y) {

		Change change = form.getChangeArray().get(ID);
		change.setName(name);
		change.setDescriptoin(description);
		change.setArtifact(control.getArtifactList().get(artifactIndex));

		Coordinates coord = objF.createCoordinates();
		coord.setXCoordinate(x);
		coord.setYCoordinate(y);
		change.setCoordinates(coord);
		if (isNew) {
			control.getChangeList().add(form.getCanvasItem().getIDs()[1], form.getChangeArray().get(ID));
			control.getChangeFormIndex().add(form.getCanvasItem().getIDs()[0]);
			control.getChangeObservable().add(name);
		}

	}

	public int[] createChange(CanvasItem item, BasicForm form, int[] IDs) {

		Change change = (Change) objF.createChange();
		forms.add(index, new ChangeForm(item, control, change));
		IDs[0] = index;
		IDs[1] = idCreater.createChangeID();
		IDs[2] = form.getIdCreater().createChangeID();
		form.getChangeArray().add(IDs[2], change);
		index++;
		return IDs;

	}

	public void fillArtifact(Artifact artifact, int ID, String description, String name, LocalDate Ldate, String type,
			int roleIndex, int x, int y, int typeIndex) {

		// Artifact artifact = form.getArtifactArray().get(ID);
		artifact.setName(name);
		artifact.setDescriptoin(description);
		artifact.setCreated(control.convertDate(Ldate));
		artifact.setMimeType(type);
		artifact.setAuthor(control.getRoleList().get(roleIndex));
		artifact.setAuthorIndex(roleIndex);
		control.getArtifactObservable().add(name);
		artifact.setArtifactIndex(typeIndex);
		Coordinates coord = objF.createCoordinates();
		coord.setXCoordinate(x);
		coord.setYCoordinate(y);
		artifact.setCoordinates(coord);

	}

	public int[] createArtifact(CanvasItem item, BasicForm form, int[] IDs) {

		Artifact artifact = (Artifact) objF.createArtifact();
		forms.add(index, new ArtifactForm(item, control, artifact));
		IDs[0] = index;
		IDs[1] = idCreater.createArtifactID();
		IDs[2] = form.getIdCreater().createArtifactID();
		form.getArtifactArray().add(IDs[2], artifact);
		control.getArtifactList().add(IDs[1], artifact);
		control.getArtifactFormIndex().add(index);
		index++;
		return IDs;
	}

	public void fillRole(Role role, int ID, String description, String name, String type, int x, int y) {

		role.setName(name);
		role.setDescription(description);
		role.setType(type);

		Coordinates coord = objF.createCoordinates();
		coord.setXCoordinate(x);
		coord.setYCoordinate(y);
		role.setCoordinates(coord);

		control.getRoleObservable().add(name);

	}

	public int[] createRole(CanvasItem item, BasicForm form, int[] IDs) {
		Role role = (Role) objF.createRole();
		forms.add(index, new RoleForm(item, control, role));
		IDs[0] = index;
		IDs[1] = idCreater.createRoleID();
		control.getRoleList().add(IDs[1], role);
		control.getRoleFormIndex().add(index);
		index++;
		return IDs;
	}

}
