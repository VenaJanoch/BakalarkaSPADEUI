package services;

import java.time.LocalDate;
import java.util.ArrayList;

import SPADEPAC.Activity;
import SPADEPAC.Artifact;
import SPADEPAC.Branch;
import SPADEPAC.Change;
import SPADEPAC.ConfigPersonRelation;
import SPADEPAC.Configuration;
import SPADEPAC.Coordinates;
import SPADEPAC.Criterion;
import SPADEPAC.Iteration;
import SPADEPAC.Milestone;
import SPADEPAC.ObjectFactory;
import SPADEPAC.Phase;
import SPADEPAC.Project;
import SPADEPAC.Role;
import SPADEPAC.RoleType;
import SPADEPAC.WorkUnit;
import abstractform.BasicForm;
import forms.ActivityForm;
import forms.ArtifactForm;
import forms.BranchForm;
import forms.ChangeForm;
import forms.ConfigurationForm;
import forms.IterationForm;
import forms.PhaseForm;
import forms.RoleForm;
import forms.TagForm;
import forms.WorkUnitForm;
import graphics.CanvasItem;
import javafx.collections.ObservableList;
import tables.TagTable;

public class FillForms {

	private Control control;
	private Project project;
	private ArrayList<BasicForm> forms;
	private ObjectFactory objF;
	private SegmentLists lists;
	private int index;
	private IdentificatorCreater idCreater;

	public FillForms(Control control, SegmentLists lists, Project project, ArrayList<BasicForm> forms, ObjectFactory objFac) {

		this.control = control;
		this.project = project;
		this.forms = forms;
		this.lists = lists;
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
			int milestoneIndex, int x, int y) {

		Phase phase = form.getPhaseArray().get(ID);
		phase.setDescription(description);
		phase.setName(name);
		phase.setEndDate(control.convertDate(endDate));
		Coordinates coord = objF.createCoordinates();
		coord.setXCoordinate(x);
		coord.setYCoordinate(y);
		phase.setCoordinates(coord);
		phase.setConfiguration(control.getLists().getConfigList().get(confIndex));
		phase.setMilestoneIndex(milestoneIndex);

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
		iteration.setConfiguration(control.getLists().getConfigList().get(confIndex));
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
			String category, int x, int y, int priorityIndex, int severityIndex, int typeIndex) {

		WorkUnit workUnit = form.getWorkUnitArray().get(ID);
		workUnit.setDescription(description);
		workUnit.setName(name);
		workUnit.setAssigneeIndex(assigneIndex);
		workUnit.setAuthorIndex(authorIndex);
		workUnit.setPriorityIndex(priorityIndex);
		workUnit.setSeverityIndex(severityIndex);
		workUnit.setTypeIndex(typeIndex);
		workUnit.setCategory(category);

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

	public void fillMilestone(String name, ObservableList<Integer> indexs) {
		
		 Milestone milestone = (Milestone)objF.createMilestone();
		 milestone.setName(name);
		 
		 for (int i = 0; i < indexs.size(); i++) {
			milestone.getCriteriaIndexs().add(indexs.get(i));
		}
		 
		control.getLists().getMilestoneList().add(milestone);
		control.getLists().getMilestoneObservable().add(milestone.getName());
		 

	}

	public void fillCriterion( String description, String name) {

		 Criterion criterion = objF.createCriterion(); 
		 criterion.setDescription(description);
		 criterion.setName(name);
		 
		 control.getLists().getCriterionList().add(criterion);
		 control.getLists().getCriterionObservable().add(criterion.getName());

	}
	
	public void fillCPR(String name, int conf, int role) {

		 ConfigPersonRelation cpr = objF.createConfigPersonRelation(); 
		 cpr.setName(name);
		 cpr.setConfigurationIndex(conf);
		 cpr.setPersonIndex(role);
		 
		 control.getLists().getCPRList().add(cpr);
		 control.getLists().getCPRObservable().add(name);

	}

	public void fillConfiguration(Configuration conf, int ID, boolean isRelase, LocalDate Ldate, String name,
			int roleIndex, boolean isNew) {

		Configuration config = control.getLists().getConfigList().get(ID);
		config.setIsRelease(isRelase);
		config.setCreate(control.convertDate(Ldate));
		config.setName(name);
		config.setAuthorIndex(roleIndex);

		if (isNew) {
			control.getLists().getConfigObservable().add(name);
		}

	}

	public int[] createConfigruration(CanvasItem item, BasicForm form, int[] IDs) {
		Configuration conf = (Configuration) objF.createConfiguration();
		forms.add(index, new ConfigurationForm(item, control, Constans.configurationDragTextIndexs, conf, index));
		IDs[0] = index;
		IDs[1] = idCreater.createConfigurationID();
		System.out.println("Nevim " + IDs[1]);
		control.getLists().getConfigList().add(IDs[1], conf);
		control.getLists().getConfigFormIndex().add(index);
		index++;
		return IDs;

	}

	public void fillBranch(Branch branch, int[] IDs, boolean isMain, String name, boolean isNew, int x, int y) {

		branch.setIsMain(isMain);
		branch.setName(name);
		Coordinates coord = objF.createCoordinates();

		if (isNew) {
			control.getLists().getBranchList().add(IDs[1], branch);
			control.getLists().getBranchFormIndex().add(IDs[0]);
			control.getLists().getBranchObservable().add(name);
		}
	}

	public int[] createBranch(CanvasItem item, BasicForm form, int[] IDs) {

		Branch branch = (Branch) objF.createBranch();
		forms.add(index, new BranchForm(item, control, branch));
		IDs[0] = index;
		IDs[1] = idCreater.createBranchID();
		IDs[2] = form.getIdCreater().createBranchID();

		form.getBranchArray().add(IDs[2], IDs[1]);
		index++;
		return IDs;

	}

	public void fillChange(Change change, int[] IDs, String description, String name, boolean isNew, int x, int y, boolean isExist) {

		change.setName(name);
		change.setDescriptoin(description);

		Coordinates coord = objF.createCoordinates();
		coord.setXCoordinate(x);
		coord.setYCoordinate(y);
		change.setCoordinates(coord);

		if (isNew) {

			control.getLists().getChangeList().add(IDs[1], change);
			control.getLists().getChangeFormIndex().add(IDs[0]);
			control.getLists().getChangeObservable().add(name);
		}

	}

	public int[] createChange(CanvasItem item, BasicForm form, int[] IDs) {

		Change change = (Change) objF.createChange();
		forms.add(index, new ChangeForm(item, control, change));
		IDs[0] = index;
		IDs[1] = idCreater.createChangeID();
		IDs[2] = form.getIdCreater().createChangeID();
		form.getChangeArray().add(IDs[2], IDs[1]);
		index++;
		return IDs;

	}

	public void fillArtifact(Artifact artifact, int[] IDs, String description, String name, LocalDate Ldate,
			String type, int roleIndex, int x, int y, int typeIndex, boolean isNew, boolean isExist) {

		// Artifact artifact = form.getArtifactArray().get(ID);
		artifact.setName(name);
		artifact.setDescriptoin(description);
		artifact.setCreated(control.convertDate(Ldate));
		artifact.setMimeType(type);
		artifact.setAuthorIndex(roleIndex);
		artifact.setArtifactIndex(typeIndex);
		Coordinates coord = objF.createCoordinates();
		coord.setXCoordinate(x);
		coord.setYCoordinate(y);
		artifact.setCoordinates(coord);
		if (isNew) {
			control.getLists().getArtifactObservable().add(name);
			control.getLists().getArtifactList().add(IDs[1], artifact);
			control.getLists().getArtifactFormIndex().add(IDs[0]);
		}

	}

	public int[] createArtifact(CanvasItem item, BasicForm form, int[] IDs) {

		Artifact artifact = (Artifact) objF.createArtifact();
		forms.add(index, new ArtifactForm(item, control, artifact));
		IDs[0] = index;
		IDs[1] = idCreater.createArtifactID();
		IDs[2] = form.getIdCreater().createArtifactID();
		form.getArtifactArray().add(IDs[2], IDs[1]);

		index++;
		return IDs;
	}

	public void fillRole(String description, String name, int type) {
		
		Role role = objF.createRole();
		role.setName(name);
		role.setDescription(description);
		role.setType(type);
		
		control.getLists().getRoleObservable().add(name);
		control.getLists().getRoleList().add(role);
	}
	
	public void fillRoleType(String nameST, String classST, String superST) {

		RoleType type = objF.createRoleType();
		type.setName(nameST);
		type.setRoleClass(classST);
		type.setRoleSuperClass(superST);
		
		lists.getRoleTypeObservable().add(nameST);
		lists.getRoleTypeList().add(type);
		
	}

//	public int[] createRole(CanvasItem item, BasicForm form, int[] IDs) {
//		Role role = (Role) objF.createRole();
//		forms.add(index, new RoleForm(item, control, role));
//		IDs[0] = index;
//		IDs[1] = idCreater.createRoleID();
//		control.getRoleList().add(IDs[1], role);
//		control.getRoleFormIndex().add(index);
//		index++;
//		return IDs;
//	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public IdentificatorCreater getIdCreater() {
		return idCreater;
	}

	public void setIdCreater(IdentificatorCreater idCreater) {
		this.idCreater = idCreater;
	}

	public void fillTag(Configuration conf, ObservableList<TagTable> tags) {

		for (int i = 0; i < tags.size(); i++) {
			String tag = tags.get(i).getTag();
			conf.getTags().add(tag);

		}

	}

	

}
