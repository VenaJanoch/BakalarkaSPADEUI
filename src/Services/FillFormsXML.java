package Services;

import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.impl.ior.GenericTaggedComponent;

import AbstractForm.BasicForm;
import Forms.ActivityForm;
import Forms.ArtifactForm;
import Forms.BranchForm;
import Forms.ChangeForm;
import Forms.ConfigurationForm;
import Forms.IterationForm;
import Forms.PhaseForm;
import Forms.ProjectForm;
import Forms.RoleForm;
import Forms.TagForm;
import Forms.WorkUnitForm;
import Graphics.CanvasItem;
import SPADEPAC.Activity;
import SPADEPAC.Artifact;
import SPADEPAC.ArtifactClass;
import SPADEPAC.Branch;
import SPADEPAC.Change;
import SPADEPAC.Configuration;
import SPADEPAC.Iteration;
import SPADEPAC.Phase;
import SPADEPAC.Project;
import SPADEPAC.Role;
import SPADEPAC.Tag;
import SPADEPAC.WorkUnit;
import SPADEPAC.WorkUnitPriorityClass;
import SPADEPAC.WorkUnitSeverityClass;
import SPADEPAC.WorkUnitTypeClass;

public class FillFormsXML {

	private Control control;
	private Project project;
	private ArrayList<BasicForm> forms;

	private int index;
	private IdentificatorCreater idCreater;

	public FillFormsXML(Control control, Project project, ArrayList<BasicForm> forms) {

		this.control = control;
		this.project = project;
		this.forms = forms;

		idCreater = new IdentificatorCreater();
		index = 1;

	}

	public void fillProjectFromXML(ProjectForm form) {

		form.setName(project.getName());
		form.getNameTF().setText(project.getName());
		form.getDescriptionTF().setText(project.getDescription());
		form.getDateDP().setValue(control.convertDateFromXML(project.getStartDate()));
		form.getDate2DP().setValue(control.convertDateFromXML(project.getEndDate()));

		fillBranchObservabel(project.getBranches());

		fillChangeObservabel(project.getChanges());

		fillArtifactObservabel(project.getArtifacts());

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

		String author = control.getRoleList().get(unit.getAuthorIndex()).getName();
		String assignee = control.getRoleList().get(unit.getAssigneeIndex()).getName();
		workUnitForm.getAuthorRoleCB().setValue(author);
		workUnitForm.getAsigneeRoleCB().setValue(assignee);

		return IDs;
	}

	public void fillConfigurationFromXML(BasicForm form, Configuration config) {
		if (control.checkConfiguration(config.getName())) {

			CanvasItem item = new CanvasItem(SegmentType.Configuration, config.getName(), control, form, false);

			control.getConfigList().add(item.getIDs()[1], config);
			control.getConfigObservable().add(item.getIDs()[1], config.getName());
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

		control.getConfigFormIndex().add(IDs[1], index);
		forms.add(index, configForm);
		System.out.println(forms.get(index).getTitle() + " " + index + " " + IDs[1]);
		index++;

		String author = control.getRoleList().get(conf.getAuthorIndex()).getName();

		configForm.getAuthorRoleCB().setValue(author);

		fillBranchFromXML(configForm, conf.getBranchesIndexs());
		fillChangeFromXML(configForm, conf.getChangesIndexs());
		fillArtifactFromXML(configForm, conf.getArtifactsIndexs());
		fillTagFromXML(configForm, conf.getTags());

		return IDs;

	}

	public void fillRoleFromXML(BasicForm form, List<Role> roles) {
		System.out.println(roles.size() + " Role size");
		for (int i = 0; i < roles.size(); i++) {

			Role role = roles.get(i);
			CanvasItem item = new CanvasItem(SegmentType.Role, role.getName(), control, form, false);

			control.getRoleObservable().add(item.getIDs()[1], role.getName());
			// control.getRoleList().add(item.getIDs()[1], role);
		}

	}

	public int[] createRoleFormXML(CanvasItem item, BasicForm form, int[] IDs) {
		System.out.println(form.getTitle() + " roole");

		IDs[0] = index;
		System.out.println(index + " role");
		IDs[1] = idCreater.createRoleID();
		IDs[2] = form.getIdCreater().createRoleID();
		Role role = project.getRoles().get(IDs[1]);
		RoleForm roleForm = new RoleForm(item, control, role);

		roleForm.setName(role.getName());
		roleForm.getNameTF().setText(role.getName());
		roleForm.getDescriptionTF().setText(role.getDescription());
		roleForm.setNew(false);
		forms.add(IDs[0], roleForm);
		control.getRoleFormIndex().add(index);
		index++;

		return IDs;
	}

	public void fillBranchFromXML(BasicForm form, List<Integer> branchs) {
		for (int i = 0; i < branchs.size(); i++) {

			Branch branch = control.getBranchList().get(branchs.get(i));

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
		Branch branch = control.getBranchList().get(index);
		BranchForm branchForm = new BranchForm(item, control, branch);
		branchForm.setNewBranch(false);
		branchForm.getBranchesCB().setValue(branch.getName());

		control.getBranchFormIndex().add(IDs[1], index);
		forms.add(IDs[0], branchForm);
		index++;

		return IDs;

	}

	public void fillChangeFromXML(BasicForm form, List<Integer> changes) {

		for (int i = 0; i < changes.size(); i++) {
			Change change = control.getChangeList().get(changes.get(i));

			// control.getChangeObservable().add(change.getName());

			CanvasItem item = new CanvasItem(SegmentType.Change, change.getName(), control, form, false);
			form.getCanvas().getChildren().add(item);
			System.out.println("Change1");
			item.setTranslateX(change.getCoordinates().getXCoordinate());
			item.setTranslateY(change.getCoordinates().getYCoordinate());

			// control.getChangeList().add(item.getIDs()[1], change);

		}

	}

	public int[] createChangeFormXML(CanvasItem item, BasicForm form, int[] IDs) {
		IDs[0] = index;
		IDs[1] = idCreater.createChangeID();
		IDs[2] = form.getIdCreater().createChangeID();
		
		int index = form.getChangeArray().get(IDs[2]);
		Change change = control.getChangeList().get(index);
		ChangeForm changeForm = new ChangeForm(item, control, change);
		System.out.println(changeForm.getTitle());

		changeForm.getChangeCB().setValue(change.getName());
		changeForm.setNewChange(false);

		forms.add(index, changeForm);
		index++;

		return IDs;

	}

	public void fillArtifactFromXML(BasicForm form, List<Integer> artifacts) {
		for (int i = 0; i < artifacts.size(); i++) {
			Artifact artifact = control.getArtifactList().get(artifacts.get(i));

			// control.getArtifactObservable().add(artifact.getName());
			CanvasItem item = new CanvasItem(SegmentType.Artifact, artifact.getName(), control, form, false);

			if ((form.getCanvasItem().getType() == SegmentType.Configuration)) {
				form.getCanvas().getChildren().add(item);
			}

			item.setTranslateX(artifact.getCoordinates().getXCoordinate());
			item.setTranslateY(artifact.getCoordinates().getYCoordinate());
			// control.getArtifactList().add(item.getIDs()[1], artifact);
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
		Artifact artifact = control.getArtifactList().get(index);
		ArtifactForm artifactForm = new ArtifactForm(item, control, artifact);
		System.out.println(artifactForm.getTitle());

		artifactForm.setName(artifact.getName());
		artifactForm.getDescriptionTF().setText(artifact.getDescriptoin());
		// artifactForm.getCreatedDP().se;
		artifactForm.getMineTypeCB().setValue(ArtifactClass.values()[artifact.getArtifactIndex()]);
		artifactForm.getDateDP().setValue(control.convertDateFromXML(artifact.getCreated()));

		String author = control.getRoleList().get(artifact.getAuthorIndex()).getName();

		artifactForm.getAuthorRoleCB().setValue(author);
		artifactForm.setNew(false);

		forms.add(index, artifactForm);
		control.getArtifactList().add(IDs[1], artifact);
		control.getArtifactFormIndex().add(index);
		index++;
		return IDs;

	}

	private void fillBranchObservabel(List<Branch> branchs) {
		for (int i = 0; i < branchs.size(); i++) {
			control.getBranchObservable().add(branchs.get(i).getName());
		}
	}

	private void fillChangeObservabel(List<Change> changes) {

		for (int i = 0; i < changes.size(); i++) {

			control.getChangeObservable().add(changes.get(i).getName());
		}
	}

	private void fillArtifactObservabel(List<Artifact> artifacts) {
		for (int i = 0; i < artifacts.size(); i++) {
			control.getArtifactObservable().add(artifacts.get(i).getName());
		}
	}

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
