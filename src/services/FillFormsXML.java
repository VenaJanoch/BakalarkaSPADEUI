package services;

import java.util.ArrayList;
import java.util.List;

import SPADEPAC.Activity;
import SPADEPAC.Artifact;
import SPADEPAC.ArtifactClass;
import SPADEPAC.Branch;
import SPADEPAC.Change;
import SPADEPAC.ConfigPersonRelation;
import SPADEPAC.Configuration;
import SPADEPAC.Criterion;
import SPADEPAC.Iteration;
import SPADEPAC.Milestone;
import SPADEPAC.Phase;
import SPADEPAC.Project;
import SPADEPAC.Role;
import SPADEPAC.RoleType;
import SPADEPAC.WorkUnit;
import SPADEPAC.WorkUnitPriorityClass;
import SPADEPAC.WorkUnitSeverityClass;
import SPADEPAC.WorkUnitTypeClass;
import abstractform.BasicForm;
import forms.ActivityForm;
import forms.ArtifactForm;
import forms.BranchForm;
import forms.ChangeForm;
import forms.ConfigurationForm;
import forms.IterationForm;
import forms.PhaseForm;
import forms.ProjectForm;
import forms.RoleForm;
import forms.TagForm;
import forms.WorkUnitForm;
import graphics.CanvasItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tables.CPRTable;
import tables.CriterionTable;
import tables.MilestoneTable;

public class FillFormsXML {

	private Control control;
	private Project project;
	private ArrayList<BasicForm> forms;

	private int index;
	private IdentificatorCreater idCreater;
	private SegmentLists lists;

	public FillFormsXML(Control control, SegmentLists lists, Project project, ArrayList<BasicForm> forms) {

		this.control = control;
		this.project = project;
		this.forms = forms;
		this.lists = lists;
		idCreater = new IdentificatorCreater();
		index = 1;

	}

	public void fillProjectFromXML(ProjectForm form) {

		form.setName(project.getName());
		form.getNameTF().setText(project.getName());
		form.getDescriptionTF().setText(project.getDescription());
		form.getDateDP().setValue(control.convertDateFromXML(project.getStartDate()));
		form.getDate2DP().setValue(control.convertDateFromXML(project.getEndDate()));

		fillCriterionObservabel(project.getCriterions());
		fillMilestoneObservabel(project.getMilestones());
		fillBranchObservabel(project.getBranches());
		fillRoleTypeFromXML(project.getRoleType());
		fillRoleFromXML(project.getRoles());
		fillChangeObservabel(project.getChanges());
		fillArtifactObservabel(project.getArtifacts());
		fillCPRObservabel(project.getCpr());

	}

	public void fillPhasesFromXML(BasicForm form) {

		for (int i = 0; i < project.getPhases().size(); i++) {
			Phase phase = project.getPhases().get(i);

			CanvasItem item = new CanvasItem(SegmentType.Phase, phase.getName(), control, form, false);
			form.getCanvas().getChildren().add(item);

			fillWorkUnitFromXML(control.getForms().get(item.getIDs()[0]), phase.getWorkUnits());

			item.setTranslateX(phase.getCoordinates().getXCoordinate());
			item.setTranslateY(phase.getCoordinates().getYCoordinate());
		}

	}

	public int[] createPhaseFormXML(CanvasItem item, BasicForm form, int[] IDs, int indexConfig) {

		IDs[0] = index;
		IDs[1] = idCreater.createPhaseID();
		Phase phase = form.getPhaseArray().get(IDs[1]);
		PhaseForm phaseForm = new PhaseForm(item, control, Constans.phaseDragTextIndexs, phase, index);
		phaseForm.getDescriptionTF().setText(phase.getDescription());
		phaseForm.getNameTF().setText(phase.getName());
		phaseForm.setName(phase.getName());
		phaseForm.getDateDP().setValue(control.convertDateFromXML(phase.getEndDate()));
		phaseForm.getMilestoneCB().setValue(control.getLists().getMilestoneObservable().get(phase.getMilestoneIndex()));

		index++;
		forms.add(IDs[0], phaseForm);

		if (phase.getConfiguration() != null) {
			fillConfigurationFromXML(phaseForm, phase.getConfiguration());
			phaseForm.getConfigCB().setValue(phase.getConfiguration().getName());
		}

		return IDs;

	}

	public void fillIterationFromXML(BasicForm form) {

		for (int i = 0; i < project.getIterations().size(); i++) {
			Iteration iteration = project.getIterations().get(i);

			CanvasItem item = new CanvasItem(SegmentType.Iteration, iteration.getName(), control, forms.get(0), false);
			form.getCanvas().getChildren().add(item);

			fillWorkUnitFromXML(forms.get(item.getIDs()[0]), iteration.getWorkUnits());

			item.setTranslateX(iteration.getCoordinates().getXCoordinate());
			item.setTranslateY(iteration.getCoordinates().getYCoordinate());
		}

	}

	public int[] createIterationFormXML(CanvasItem item, BasicForm form, int[] IDs, int indexConfig) {

		IDs[0] = index;
		IDs[1] = idCreater.createIterationID();
		Iteration iteration = form.getIterationArray().get(IDs[1]);
		IterationForm iterationForm = new IterationForm(item, control, Constans.iterationDragTextIndexs, iteration,
				index);

		iterationForm.getNameTF().setText(iteration.getName());
		iterationForm.getDescriptionTF().setText(iteration.getDescription());
		iterationForm.getDateDP().setValue(control.convertDateFromXML(iteration.getEndDate()));
		iterationForm.getDate2DP().setValue(control.convertDateFromXML(iteration.getStartDate()));

		index++;
		fillConfigurationFromXML(iterationForm, iteration.getConfiguration());

		iterationForm.getConfigCB().setValue(iteration.getConfiguration().getName());

		forms.add(IDs[0], iterationForm);
		return IDs;

	}

	public void fillActivityFromXML(BasicForm form) {

		for (int i = 0; i < project.getActivities().size(); i++) {
			Activity activity = project.getActivities().get(i);

			CanvasItem item = new CanvasItem(SegmentType.Activity, activity.getName(), control, forms.get(0), false);
			form.getCanvas().getChildren().add(item);

			fillWorkUnitFromXML(forms.get(item.getIDs()[0]), activity.getWorkUnits());

			item.setTranslateX(activity.getCoordinates().getXCoordinate());
			item.setTranslateY(activity.getCoordinates().getYCoordinate());

		}
	}

	public int[] createActivityFormXML(CanvasItem item, BasicForm form, int[] IDs) {

		IDs[0] = index;
		IDs[1] = idCreater.createActivityID();
		Activity activity = form.getActivityArray().get(IDs[1]);
		ActivityForm activityForm = new ActivityForm(item, control, Constans.activityDragTextIndexs, activity, index);

		activityForm.getNameTF().setText(activity.getName());
		activityForm.getDescriptionTF().setText(activity.getDescription());

		forms.add(index, activityForm);
		index++;

		return IDs;

	}

	public void fillWorkUnitFromXML(BasicForm form, List<WorkUnit> units) {

		for (int i = 0; i < units.size(); i++) {
			WorkUnit unit = units.get(i);

			CanvasItem item = new CanvasItem(SegmentType.WorkUnit, unit.getName(), control, form, false);
			form.getCanvas().getChildren().add(item);

			item.setTranslateX(unit.getCoordinates().getXCoordinate());
			item.setTranslateY(unit.getCoordinates().getYCoordinate());
		}

	}

	public int[] createWorkUnitFormXML(CanvasItem item, BasicForm form, int[] IDs) {

		IDs[0] = index;
		IDs[1] = idCreater.createWorkUnitID();
		IDs[2] = form.getIdCreater().createWorkUnitID();

		WorkUnit unit = form.getWorkUnitArray().get(IDs[2]);
		WorkUnitForm workUnitForm = new WorkUnitForm(item, control, unit);

		workUnitForm.getNameTF().setText(unit.getName());
		workUnitForm.getDescriptionTF().setText(unit.getDescription());
		workUnitForm.getPriorityCB().setValue(WorkUnitPriorityClass.values()[unit.getPriorityIndex()]);
		workUnitForm.getSeverityCB().setValue(WorkUnitSeverityClass.values()[unit.getSeverityIndex()]);
		workUnitForm.getTypeCB().setValue(WorkUnitTypeClass.values()[unit.getTypeIndex()]);
		// workUnitForm.getCategoryCB().setValue(WorkUnitPriorityClass.values()[unit.getPriorityIndex()]);

		index++;
		forms.add(IDs[0], workUnitForm);

		String author = control.getLists().getRoleList().get(unit.getAuthorIndex()).getName();
		String assignee = control.getLists().getRoleList().get(unit.getAssigneeIndex()).getName();
		workUnitForm.getAuthorRoleCB().setValue(author);
		workUnitForm.getAsigneeRoleCB().setValue(assignee);

		return IDs;
	}

	public void fillConfigurationFromXML(BasicForm form, Configuration config) {
		if (control.checkConfiguration(config.getName())) {

			CanvasItem item = new CanvasItem(SegmentType.Configuration, config.getName(), control, form, false);

			control.getLists().getConfigList().add(item.getIDs()[1], config);
			control.getLists().getConfigObservable().add(item.getIDs()[1], config.getName());
		}

	}

	public int[] createConfigurationFormXML(CanvasItem item, BasicForm form, int[] IDs) {

		IDs[0] = index;
		IDs[1] = idCreater.createConfigurationID();
		System.out.println(index + " Config " + IDs[1]);
		Configuration conf = form.getConfig();
		ConfigurationForm configForm = new ConfigurationForm(item, control, Constans.configurationDragTextIndexs, conf,
				index);

		configForm.getNameTF().setText(conf.getName());
		configForm.setName(conf.getName());
		configForm.getCreatedDP().setValue(control.convertDateFromXML(conf.getCreate()));
		configForm.setNew(false);
		if (conf.isIsRelease()) {
			configForm.getRbYes().setSelected(true);
		} else {
			configForm.getRbYes().setSelected(true);
		}

		control.getLists().getConfigFormIndex().add(IDs[1], index);
		forms.add(index, configForm);
		System.out.println(forms.get(index).getTitle() + " " + index + " " + IDs[1]);
		index++;

		String author = control.getLists().getRoleList().get(conf.getAuthorIndex()).getName();

		configForm.getAuthorRoleCB().setValue(author);

		fillBranchFromXML(configForm, conf.getBranchesIndexs());
		fillChangeFromXML(configForm, conf.getChangesIndexs());
		fillArtifactFromXML(configForm, conf.getArtifactsIndexs());
		fillTagFromXML(configForm, conf.getTags());

		return IDs;

	}

	public void fillRoleFromXML(List<Role> roles) {

		for (int i = 0; i < roles.size(); i++) {

			Role role = roles.get(i);

			control.getLists().getRoleObservable().add(role.getName());
		}

	}

	public void fillRoleTypeFromXML(List<RoleType> roles) {

		for (int i = 0; i < roles.size(); i++) {

			RoleType role = roles.get(i);

			lists.getRoleTypeObservable().add(role.getName());
		}

	}

	// public int[] createRoleFormXML(CanvasItem item, BasicForm form, int[]
	// IDs) {
	// System.out.println(form.getTitle() + " roole");
	//
	// IDs[0] = index;
	// System.out.println(index + " role");
	// IDs[1] = idCreater.createRoleID();
	// IDs[2] = form.getIdCreater().createRoleID();
	// Role role = project.getRoles().get(IDs[1]);
	// RoleForm roleForm = new RoleForm(item, control, role);
	//
	// roleForm.setName(role.getName());
	// roleForm.getNameTF().setText(role.getName());
	// roleForm.getDescriptionTF().setText(role.getDescription());
	// roleForm.setNew(false);
	// forms.add(IDs[0], roleForm);
	// control.getRoleFormIndex().add(index);
	// index++;
	//
	// return IDs;
	// }

	public void fillBranchFromXML(BasicForm form, List<Integer> branchs) {
		for (int i = 0; i < branchs.size(); i++) {

			Branch branch = control.getLists().getBranchList().get(branchs.get(i));

			CanvasItem item = new CanvasItem(SegmentType.Branch, branch.getName(), control, form, false);
			form.getCanvas().getChildren().add(item);

		}
	}

	public int[] createBranchFormXML(CanvasItem item, BasicForm form, int[] IDs) {

		IDs[0] = index;
		System.out.println(index + "Branch");
		IDs[1] = idCreater.createBranchID();
		IDs[2] = form.getIdCreater().createBranchID();
		// System.out.println(form.getTitle());
		int index = form.getBranchArray().get(IDs[2]);
		Branch branch = control.getLists().getBranchList().get(index);
		BranchForm branchForm = new BranchForm(item, control, branch);
		branchForm.setNewBranch(false);
		branchForm.getBranchesCB().setValue(branch.getName());

		control.getLists().getBranchFormIndex().add(IDs[1], index);
		forms.add(IDs[0], branchForm);
		index++;

		return IDs;

	}

	public void fillChangeFromXML(BasicForm form, List<Integer> changes) {

		for (int i = 0; i < changes.size(); i++) {
			Change change = control.getLists().getChangeList().get(changes.get(i));

			CanvasItem item = new CanvasItem(SegmentType.Change, change.getName(), control, form, false);
			form.getCanvas().getChildren().add(item);

			item.setTranslateX(change.getCoordinates().getXCoordinate());
			item.setTranslateY(change.getCoordinates().getYCoordinate());

		}

	}

	public int[] createChangeFormXML(CanvasItem item, BasicForm form, int[] IDs) {
		IDs[0] = index;
		IDs[1] = idCreater.createChangeID();
		IDs[2] = form.getIdCreater().createChangeID();

		int index = form.getChangeArray().get(IDs[2]);
		Change change = control.getLists().getChangeList().get(index);
		ChangeForm changeForm = new ChangeForm(item, control, change);
		
		changeForm.getChangeCB().setValue(change.getName());
		changeForm.setNewChange(false);

		forms.add(index, changeForm);
		index++;

		return IDs;

	}

	public void fillArtifactFromXML(BasicForm form, List<Integer> artifacts) {
		for (int i = 0; i < artifacts.size(); i++) {
			Artifact artifact = control.getLists().getArtifactList().get(artifacts.get(i));

			CanvasItem item = new CanvasItem(SegmentType.Artifact, artifact.getName(), control, form, false);

			if ((form.getCanvasItem().getType() == SegmentType.Configuration)) {
				form.getCanvas().getChildren().add(item);
			}

			item.setTranslateX(artifact.getCoordinates().getXCoordinate());
			item.setTranslateY(artifact.getCoordinates().getYCoordinate());

		}

	}

	public void fillTagFromXML(BasicForm form, List<String> list) {
		for (int i = 0; i < list.size(); i++) {
			String tag = form.getTagArray().get(i);
			CanvasItem item = new CanvasItem(SegmentType.Tag, "", control, form, false);
			form.getCanvas().getChildren().add(item);
		}

	}

	public int[] createArtifactFormXML(CanvasItem item, BasicForm form, int[] IDs) {
		IDs[0] = index;
		IDs[1] = idCreater.createArtifactID();
		IDs[2] = form.getIdCreater().createArtifactID();

		int index = form.getArtifactArray().get(IDs[2]);
		Artifact artifact = control.getLists().getArtifactList().get(index);
		ArtifactForm artifactForm = new ArtifactForm(item, control, artifact);
		System.out.println(artifactForm.getTitle());

		artifactForm.setName(artifact.getName());
		artifactForm.getDescriptionTF().setText(artifact.getDescriptoin());
		// artifactForm.getCreatedDP().se;
		artifactForm.getMineTypeCB().setValue(ArtifactClass.values()[artifact.getArtifactIndex()]);
		artifactForm.getDateDP().setValue(control.convertDateFromXML(artifact.getCreated()));

		String author = control.getLists().getRoleList().get(artifact.getAuthorIndex()).getName();

		artifactForm.getAuthorRoleCB().setValue(author);
		artifactForm.setNew(false);

		forms.add(index, artifactForm);
		control.getLists().getArtifactList().add(IDs[1], artifact);
		control.getLists().getArtifactFormIndex().add(index);
		index++;
		return IDs;

	}

	private void fillBranchObservabel(List<Branch> branchs) {
		for (int i = 0; i < branchs.size(); i++) {
			control.getLists().getBranchObservable().add(branchs.get(i).getName());
		}
	}

	private void fillChangeObservabel(List<Change> changes) {

		for (int i = 0; i < changes.size(); i++) {

			control.getLists().getChangeObservable().add(changes.get(i).getName());
		}
	}

	private void fillArtifactObservabel(List<Artifact> artifacts) {
		for (int i = 0; i < artifacts.size(); i++) {
			control.getLists().getArtifactObservable().add(artifacts.get(i).getName());
		}
	}

	private void fillCPRObservabel(List<ConfigPersonRelation> cprs) {

		ObservableList<CPRTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < cprs.size(); i++) {
			control.getLists().getArtifactObservable().add(cprs.get(i).getName());
			data.add(new CPRTable(cprs.get(i).getName(), "", ""));
		}

		control.getCPRForm().getTableTV().setItems(data);
	}

	private void fillMilestoneObservabel(List<Milestone> milestones) {

		ObservableList<MilestoneTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < milestones.size(); i++) {
			String name = milestones.get(i).getName();
			control.getLists().getMilestoneObservable().add(name);
			String criterion = createCriterionsString(milestones.get(i).getCriteriaIndexs());

			data.add(new MilestoneTable(milestones.get(i).getName(), criterion));
		}

		control.getMilestoneForm().getTableTV().setItems(data);
	}

	private String createCriterionsString(List<Integer> indexs) {

		String tmp = "[ ";
		for (int i = 0; i < indexs.size(); i++) {
			tmp += control.getLists().getCriterionObservable().get(indexs.get(i)) + ", ";
		}

		tmp += " ]";

		return tmp;
	}

	private void fillCriterionObservabel(List<Criterion> criterions) {

		ObservableList<CriterionTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < criterions.size(); i++) {
			control.getLists().getArtifactObservable().add(criterions.get(i).getName());
			data.add(new CriterionTable(criterions.get(i).getName(), criterions.get(i).getDescription()));
		}

		control.getMilestoneForm().getCriterionForm().getTableTV().setItems(data);
	}

	/*** Getrs and Setrs ****/

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

}
