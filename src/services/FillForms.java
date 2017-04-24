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
import SPADEPAC.Priority;
import SPADEPAC.Project;
import SPADEPAC.Relation;
import SPADEPAC.Resolution;
import SPADEPAC.Role;
import SPADEPAC.RoleType;
import SPADEPAC.Severity;
import SPADEPAC.Status;
import SPADEPAC.Type;
import SPADEPAC.WorkUnit;
import abstractform.BasicForm;
import forms.ActivityForm;
import forms.ArtifactForm;
import forms.ChangeForm;
import forms.ConfigurationForm;
import forms.IterationForm;
import forms.PhaseForm;
import forms.WorkUnitForm;
import graphics.CanvasItem;
import javafx.collections.ObservableList;
import tables.ConfigTable;
import tables.TagTable;

public class FillForms {

	private Control control;
	private Project project;
	private ArrayList<BasicForm> forms;
	private ObjectFactory objF;
	private SegmentLists lists;

	private IdentificatorCreater idCreater;
	private DeleteControl deleteControl;

	public FillForms(Control control, SegmentLists lists, Project project, ArrayList<BasicForm> forms,
			ObjectFactory objFac, IdentificatorCreater idCreater, DeleteControl deleteControl) {

		this.control = control;
		this.project = project;
		this.forms = forms;
		this.lists = lists;
		this.objF = objFac;
		this.idCreater = idCreater;
		this.deleteControl = deleteControl;

	}

	public void fillProject(String description, String name, LocalDate startDate, LocalDate endDate) {

		project.setDescription(description);
		project.setName(name);
		project.setEndDate(control.convertDate(endDate));
		project.setStartDate(control.convertDate(startDate));
	}

	public void fillPhase(Phase phase, int[] ID, String description, String name, LocalDate endDate, int confIndex,
			int milestoneIndex, int x, int y, boolean isNew) {

		phase.setDescription(description);
		phase.setName(name);
		phase.setEndDate(control.convertDate(endDate));
		Coordinates coord = objF.createCoordinates();
		coord.setXCoordinate(x);
		coord.setYCoordinate(y);
		phase.setCoordinates(coord);
		phase.setConfiguration(confIndex);
		phase.setMilestoneIndex(milestoneIndex);

		if (isNew) {
			BasicForm rForm = control.getForms().get(ID[3]);
			rForm.getPhaseArray().remove(ID[1]);
			rForm.getPhaseArray().add(ID[1], phase);
		}

	}

	public int[] createPhase(CanvasItem item, BasicForm form, int[] IDs) {

		int index = IdentificatorCreater.getIndex();

		Phase phase = (Phase) objF.createPhase();
		Coordinates coord = objF.createCoordinates();

		forms.add(index, new PhaseForm(item, control, Constans.phaseDragTextIndexs, phase, index, deleteControl));
		IDs[0] = index;
		IDs[1] = idCreater.createPhaseID();
		System.out.println(form.getCanvasItem().getIDs()[0]);
		IDs[3] = form.getCanvasItem().getIDs()[0];
		form.getPhaseArray().add(IDs[1], null);
		index++;
		IdentificatorCreater.setIndex(index);
		return IDs;
	}

	public void fillActivity(Activity activity, int[] ID, String description, String name, int x, int y,
			boolean isNew) {

		activity.setDescription(description);
		activity.setName(name);
		Coordinates coord = objF.createCoordinates();
		coord.setXCoordinate(x);
		coord.setYCoordinate(y);
		activity.setCoordinates(coord);

		if (isNew) {
			BasicForm rForm = control.getForms().get(ID[3]);
			rForm.getActivityArray().remove(ID[1]);
			rForm.getActivityArray().add(ID[1], activity);
		}
	}

	public int[] createActivity(CanvasItem item, BasicForm form, int[] IDs) {
		int index = IdentificatorCreater.getIndex();
		Activity activity = (Activity) objF.createActivity();
		forms.add(index,
				new ActivityForm(item, control, Constans.activityDragTextIndexs, activity, index, deleteControl));

		IDs[0] = index;
		index++;
		IdentificatorCreater.setIndex(index);
		IDs[1] = idCreater.createActivityID();
		IDs[3] = form.getCanvasItem().getIDs()[0];
		form.getActivityArray().add(IDs[1], null);

		return IDs;
	}

	public void fillIteration(Iteration iteration, int[] ID, String description, String name, LocalDate startDate,
			LocalDate endDate, int confIndex, int x, int y, boolean isNew) {

		iteration.setDescription(description);
		iteration.setName(name);
		iteration.setStartDate(control.convertDate(startDate));
		iteration.setEndDate(control.convertDate(endDate));
		iteration.setConfiguration(confIndex);
		Coordinates coord = objF.createCoordinates();
		coord.setXCoordinate(x);
		coord.setYCoordinate(y);
		iteration.setCoordinates(coord);

		if (isNew) {
			BasicForm rForm = control.getForms().get(ID[3]);
			rForm.getIterationArray().remove(ID[1]);
			rForm.getIterationArray().add(ID[1], iteration);
		}
	}

	public int[] createIteration(CanvasItem item, BasicForm form, int[] IDs) {
		int index = IdentificatorCreater.getIndex();
		Iteration iteration = (Iteration) objF.createIteration();
		forms.add(index,
				new IterationForm(item, control, Constans.iterationDragTextIndexs, iteration, index, deleteControl));
		IDs[0] = index;
		IDs[1] = idCreater.createIterationID();
		IDs[3] = form.getCanvasItem().getIDs()[0];
		form.getIterationArray().add(IDs[1], null);
		index++;
		IdentificatorCreater.setIndex(index);
		return IDs;
	}

	public void fillWorkUnit(WorkUnit workUnit, int[] ID, String description, String name, int authorIndex,
			int assigneIndex, String category, int x, int y, int priorityIndex, int severityIndex, int typeIndex,
			int resolutionIndex, int statusIndex, double estimate, boolean isNew) {

		workUnit.setDescription(description);
		workUnit.setName(name);
		workUnit.setAssigneeIndex(assigneIndex);
		workUnit.setAuthorIndex(authorIndex);
		workUnit.setPriorityIndex(priorityIndex);
		workUnit.setSeverityIndex(severityIndex);
		workUnit.setTypeIndex(typeIndex);
		workUnit.setCategory(category);
		workUnit.setResolutionIndex(resolutionIndex);
		workUnit.setStatusIndex(statusIndex);
		workUnit.setEstimatedTime(estimate);

		Coordinates coord = objF.createCoordinates();
		coord.setXCoordinate(x);
		coord.setYCoordinate(y);
		workUnit.setCoordinates(coord);

		if (isNew) {
			BasicForm rForm = control.getForms().get(ID[3]);
			rForm.getWorkUnitArray().remove(ID[2]);
			rForm.getWorkUnitArray().add(ID[2], ID[1]);
			lists.getWorkUnitList().remove(ID[1]);
			lists.getWorkUnitList().add(ID[1], workUnit);

		}
	}

	public int[] createWorkUnit(CanvasItem item, BasicForm form, int[] IDs) {
		int index = IdentificatorCreater.getIndex();
		WorkUnit unit = (WorkUnit) objF.createWorkUnit();
		forms.add(index, new WorkUnitForm(item, control, unit, deleteControl));
		IDs[0] = index;
		IDs[1] = idCreater.createWorkUnitID();
		IDs[2] = form.getIdCreater().createWorkUnitID();
		IDs[3] = form.getCanvasItem().getIDs()[0];

		lists.getWorkUnitFormIndex().add(index);
		lists.getWorkUnitList().add(IDs[1], null);
		form.getWorkUnitArray().add(IDs[2], null);
		index++;
		IdentificatorCreater.setIndex(index);
		return IDs;
	}

	public void fillMilestone(String name, ObservableList<Integer> indexs) {

		Milestone milestone = (Milestone) objF.createMilestone();
		milestone.setName(name);

		for (int i = 0; i < indexs.size(); i++) {
			milestone.getCriteriaIndexs().add(indexs.get(i));
		}

		control.getLists().getMilestoneList().add(milestone);
		control.getLists().getMilestoneObservable().add(milestone.getName());

	}

	public void fillCriterion(String name, String description) {

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

	public void fillConfiguration(Configuration conf, int[] IDs, boolean isRelase, LocalDate Ldate, String name,
			int roleIndex, boolean isNew) {

		Configuration config = conf;
		config.setIsRelease(isRelase);
		config.setCreate(control.convertDate(Ldate));
		config.setName(name);
		config.setAuthorIndex(roleIndex);

		String release = "YES";
		if (isRelase) {
			release = "YES";
		} else {
			release = "NO";
		}
		if (isNew) {
			lists.getConfigObservable().add(name);
			lists.getConfigList().add(IDs[1], conf);

			ConfigTable configTab = new ConfigTable(name, release, IDs[0]);
			control.getConfTableForm().getTableTV().getItems().add(configTab);
			control.getConfTableForm().getTableTV().sort();
			control.getConfTableForm().createConfigItem();
		} else {

			ConfigTable configTab = control.getConfTableForm().getTableTV().getItems().get(IDs[1]);
			configTab.setName(name);
			configTab.setRelease(release);
			control.getConfTableForm().getMainPanel().setCenter(control.getConfTableForm().getForm().getMainPanel());
		}

	}

	public int[] createConfigruration(CanvasItem item, BasicForm form, int[] IDs) {
		int index = IdentificatorCreater.getIndex();
		Configuration conf = (Configuration) objF.createConfiguration();
		forms.add(index,
				new ConfigurationForm(item, control, Constans.configurationDragTextIndexs, conf, index, deleteControl));
		IDs[0] = index;
		IDs[1] = idCreater.createConfigurationID();

		control.getLists().getConfigFormIndex().add(index);
		index++;
		IdentificatorCreater.setIndex(index);
		return IDs;

	}

	public void fillBranch(String name, boolean isMain) {

		Branch branch = (Branch) objF.createBranch();

		branch.setIsMain(isMain);
		branch.setName(name);

		control.getLists().getBranchList().add(branch);
		control.getLists().getBranchObservable().add(name);

	}

	public void fillChange(Change change, int[] IDs, String description, String name, boolean isNew, int x, int y,
			boolean isExist) {

		change.setName(name);
		change.setDescriptoin(description);

		Coordinates coord = objF.createCoordinates();
		coord.setXCoordinate(x);
		coord.setYCoordinate(y);
		change.setCoordinates(coord);
		change.setExist(isExist);

		if (isNew) {
			lists.getChangeFormIndex().add(IDs[0]);
			BasicForm rForm = control.getForms().get(IDs[3]);
			System.out.println(rForm);
			rForm.getChangeArray().remove(IDs[2]);
			rForm.getChangeArray().add(IDs[2], IDs[1]);
			lists.getChangeList().remove(IDs[1]);
			lists.getChangeList().add(IDs[1], change);
		}

	}

	public int[] createChange(CanvasItem item, BasicForm form, int[] IDs) {
		int index = IdentificatorCreater.getIndex();
		Change change = (Change) objF.createChange();
		Configuration conf = form.getConfigArray();
		forms.add(index, new ChangeForm(item, control, change, conf, deleteControl));
		IDs[0] = index;
		IDs[1] = idCreater.createChangeID();
		IDs[2] = form.getIdCreater().createChangeID();
		IDs[3] = form.getCanvasItem().getIDs()[0];
		form.getChangeArray().add(IDs[2], null);
		lists.getChangeList().add(IDs[1], null);
		index++;
		IdentificatorCreater.setIndex(index);
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
		artifact.setExist(isExist);
		
		if (isNew) {
			control.getLists().getArtifactObservable().add(name);
			control.getLists().getArtifactFormIndex().add(IDs[0]);

			BasicForm rForm = control.getForms().get(IDs[3]);
			rForm.getArtifactArray().remove(IDs[2]);
			rForm.getArtifactArray().add(IDs[2], IDs[1]);
			lists.getArtifactList().remove(IDs[1]);
			lists.getArtifactList().add(IDs[1], artifact);
		}

	}

	public int[] createArtifact(CanvasItem item, BasicForm form, int[] IDs) {
		int index = IdentificatorCreater.getIndex();
		Artifact artifact = (Artifact) objF.createArtifact();
		forms.add(index, new ArtifactForm(item, control, artifact, deleteControl, form.getConfigArray()));
		IDs[0] = index;
		IDs[1] = idCreater.createArtifactID();
		IDs[2] = form.getIdCreater().createArtifactID();
		IDs[3] = form.getCanvasItem().getIDs()[0];
		form.getArtifactArray().add(IDs[2], null);
		lists.getArtifactList().add(IDs[1], null);
		index++;
		IdentificatorCreater.setIndex(index);
		return IDs;
	}

	public void fillRole(String description, String name, int type) {

		Role role = objF.createRole();
		role.setName(name);
		role.setDescription(description);
		role.setType(type);

		lists.getRoleObservable().add(name);
		lists.getRoleList().add(role);
	}

	public void fillRoleType(String nameST, String classST, String superST) {

		RoleType type = objF.createRoleType();
		type.setName(nameST);
		type.setRoleClass(classST);
		type.setRoleSuperClass(superST);

		lists.getRoleTypeObservable().add(nameST);
		lists.getRoleTypeList().add(type);

	}

	public void fillTag(Configuration conf, ObservableList<TagTable> tags) {

		for (int i = 0; i < tags.size(); i++) {
			String tag = tags.get(i).getTag();
			conf.getTags().add(tag);

		}

	}

	public void fillPriorityType(String nameST, String classST, String superST) {

		Priority priority = objF.createPriority();

		priority.setName(nameST);
		priority.setPriorityClass(classST);
		priority.setPrioritySuperClass(superST);

		lists.getPriorityObservable().add(nameST);
		lists.getPriorityTypeList().add(priority);

	}

	public void fillSeverityType(String nameST, String classST, String superST) {
		Severity severity = objF.createSeverity();

		severity.setName(nameST);
		severity.setSeverityClass(classST);
		severity.setSeveritySuperClass(superST);

		lists.getSeverityTypeObservable().add(nameST);
		lists.getSeverityTypeList().add(severity);

	}

	public void fillRelationType(String nameST, String classST, String superST) {

		Relation relation = objF.createRelation();
		relation.setName(nameST);
		relation.setRelationClass(classST);
		relation.setRelationSuperClass(superST);

		lists.getRelationTypeList().add(relation);
		lists.getRelationTypeObservable().add(nameST);

	}

	public void fillResolutionType(String nameST, String classST, String superST) {

		Resolution resolution = objF.createResolution();
		resolution.setName(nameST);
		resolution.setResolutionClass(classST);
		resolution.setResolutionSuperClass(superST);

		lists.getResolutionTypeList().add(resolution);
		lists.getResolutionTypeObservable().add(nameST);

	}

	public void fillStatusType(String nameST, String classST, String superST) {

		Status status = objF.createStatus();
		status.setName(nameST);
		status.setStatusClass(classST);
		status.setStatusSuperClass(superST);

		lists.getStatusTypeList().add(status);
		lists.getStatusTypeObservable().add(nameST);

	}

	public void fillType(String nameST, String classST, String superST) {

		Type type = objF.createType();
		type.setName(nameST);
		type.setTypeClass(classST);
		type.setTypeSuperClass(superST);

		lists.getTypeList().add(type);
		lists.getTypeObservable().add(nameST);

	}

}
