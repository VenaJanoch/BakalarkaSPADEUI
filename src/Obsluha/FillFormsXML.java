package Obsluha;

import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.impl.ior.GenericTaggedComponent;

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
import SPADEPAC.ArtifactClass;
import SPADEPAC.Branch;
import SPADEPAC.Change;
import SPADEPAC.Configuration;
import SPADEPAC.Iteration;
import SPADEPAC.Phase;
import SPADEPAC.Project;
import SPADEPAC.Role;
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
		PhaseForm phaseForm = new PhaseForm(item, control, Constans.phaseDragTextIndexs, phase);
		phaseForm.getDescriptionTF().setText(phase.getDescription());
		phaseForm.getNameTF().setText(phase.getName());
		phaseForm.setName(phase.getName());

		
		index++;
		forms.add(IDs[0], phaseForm);

		if (phase.getConfiguration() != null) {
			indexConfig = fillConfigurationFromXML(phaseForm, phase.getConfiguration());
			phaseForm.getConfigCB().setValue(control.getConfigObservable().get(indexConfig));
		}

		// form.getPhaseArray().add(IDs[1], phase);
		return IDs;

	}

	public int[] createIterationFormXML(CanvasItem item, BasicForm form, int[] IDs, int indexConfig) {

		IDs[0] = index;
		IDs[1] = idCreater.createIterationID();
		Iteration iteration = form.getIterationArray().get(IDs[1]);
		IterationForm iterationForm = new IterationForm(item, control, Constans.iterationDragTextIndexs, iteration);

		iterationForm.getNameTF().setText(iteration.getName());
		iterationForm.getDescriptionTF().setText(iteration.getDescription());

		index++;
		indexConfig = fillConfigurationFromXML(iterationForm, iteration.getConfiguration());

		iterationForm.getConfigCB().setValue(control.getConfigObservable().get(indexConfig));

		forms.add(IDs[0], iterationForm);
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
		ActivityForm activityForm = new ActivityForm(item, control, Constans.activityDragTextIndexs, activity);

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
		fillRoleFromXML(workUnitForm, workUnitForm.getRoleArray());

		workUnitForm.getAuthorRoleCB().setValue(unit.getAuthor().getName());
		workUnitForm.getAuthorRoleCB().setValue(unit.getAssignee().getName());

		return IDs;
	}

	public int fillConfigurationFromXML(BasicForm form, Configuration config) {

		CanvasItem item = new CanvasItem(SegmentType.Configuration, config.getName(), control, form, false);

		control.getConfigList().add(item.getIDs()[1], config);
		control.getConfigObservable().add(item.getIDs()[1], config.getName());

		return item.getIDs()[1];

	}

	public int[] createConfigurationFormXML(CanvasItem item, BasicForm form, int[] IDs) {

		IDs[0] = index;
		IDs[1] = idCreater.createConfigurationID();
		System.out.println(index + " Config " + IDs[1]);
		Configuration conf = form.getConfig();
		ConfigurationForm configForm = new ConfigurationForm(item, control, Constans.configurationDragTextIndexs, conf);

		configForm.getNameTF().setText(conf.getName());
		configForm.setName(conf.getName());

		if (conf.isIsRelease()) {
			configForm.getRbYes().setSelected(true);
		} else {
			configForm.getRbYes().setSelected(true);
		}

		control.getConfigFormIndex().add(IDs[1], index);
		forms.add(index, configForm);
		System.out.println(forms.get(index).getTitle() + " " + index + " " + IDs[1]);
		index++;

		fillRoleFromXML(configForm, configForm.getRoleArray());
		configForm.getAuthorRoleCB().setValue(conf.getAuthor().getName());

		fillBranchFromXML(configForm, conf.getBranches());
		fillChangeFromXML(configForm, conf.getChanges());
		fillArtifactFromXML(configForm, conf.getArtifacts());

		return IDs;

	}

	public void fillRoleFromXML(BasicForm form, List<Role> roles) {

		for (int i = 0; i < roles.size(); i++) {

			Role role = roles.get(i);
			CanvasItem item = new CanvasItem(SegmentType.Role, role.getName(), control, form, false);

			control.getRoleObservable().add(item.getIDs()[1], role.getName());
			control.getRoleList().add(item.getIDs()[1], role);
		}

	}

	public int[] createRoleFormXML(CanvasItem item, BasicForm form, int[] IDs) {
		System.out.println(form.getTitle() + " roole");

		IDs[0] = index;
		System.out.println(index + " role");
		IDs[1] = idCreater.createRoleID();
		IDs[2] = form.getIdCreater().createRoleID();
		Role role = form.getRoleArray().get(IDs[2]);
		RoleForm roleForm = new RoleForm(item, control, role);

		roleForm.setName(role.getName());
		roleForm.getNameTF().setText(role.getName());
		roleForm.getDescriptionTF().setText(role.getDescription());

		forms.add(IDs[0], roleForm);
		control.getRoleFormIndex().add(index);
		index++;

		return IDs;
	}

	public void fillBranchFromXML(BasicForm form, List<Branch> branchs) {
		for (int i = 0; i < branchs.size(); i++) {

			Branch branch = branchs.get(i);
			CanvasItem item = new CanvasItem(SegmentType.Branch, branch.getName(), control, form, false);
			form.getCanvas().getChildren().add(item);

			item.setTranslateX(branch.getCoordinates().getXCoordinate());
			item.setTranslateY(branch.getCoordinates().getYCoordinate());

			control.getBranchObservable().add(branch.getName());
			control.getBranchList().add(item.getIDs()[1], branch);

		}
	}

	public int[] createBranchFormXML(CanvasItem item, BasicForm form, int[] IDs) {

		IDs[0] = index;
		System.out.println(index + "Branch");
		IDs[1] = idCreater.createBranchID();
		IDs[2] = form.getIdCreater().createBranchID();
		// System.out.println(form.getTitle());
		Branch branch = form.getBranchArray().get(IDs[2]);
		BranchForm branchForm = new BranchForm(item, control);

		branchForm.getNameTF().setText(branch.getName());
		branchForm.getBranchesCB().setValue(branch.getName());

		if (branch.isIsMain()) {
			branchForm.getRbYes().setSelected(true);
		} else {
			branchForm.getRbYes().setSelected(true);
		}

		control.getBranchFormIndex().add(IDs[1], index);
		forms.add(IDs[0], branchForm);
		index++;

		return IDs;

	}

	private void fillChangeFromXML(BasicForm form, List<Change> changes) {

		for (int i = 0; i < changes.size(); i++) {
			Change change = changes.get(i);

			CanvasItem item = new CanvasItem(SegmentType.Change, change.getName(), control, form, false);
			form.getCanvas().getChildren().add(item);

			item.setTranslateX(change.getCoordinates().getXCoordinate());
			item.setTranslateY(change.getCoordinates().getYCoordinate());

			control.getChangeObservable().add(change.getName());
			control.getChangeList().add(item.getIDs()[1], change);

		}

	}

	public int[] createChangeFormXML(CanvasItem item, BasicForm form, int[] IDs) {
		IDs[0] = index;
		IDs[1] = idCreater.createChangeID();
		IDs[2] = form.getIdCreater().createChangeID();

		Change change = form.getChangeArray().get(IDs[2]);
		ChangeForm changeForm = new ChangeForm(item, control, change);
		System.out.println(changeForm.getTitle());

		changeForm.setName(change.getName());
		changeForm.getNameTF().setText(change.getName());
		changeForm.getChangeCB().setValue(change.getName());
		changeForm.getDescriptionTF().setText(change.getDescriptoin());
		changeForm.getArtifactCB().setValue(change.getArtifact().getName());

		forms.add(index, changeForm);
		index++;

		fillArtifactFromXML(changeForm, changeForm.getArtifactArray());

		return IDs;

	}

	private void fillArtifactFromXML(BasicForm form, List<Artifact> artifacts) {
		for (int i = 0; i < artifacts.size(); i++) {
			Artifact artifact = artifacts.get(i);
			CanvasItem item = new CanvasItem(SegmentType.Artifact, artifact.getName(), control, form, false);

			if ((form.getCanvasItem().getType() == SegmentType.Configuration)) {
				System.out.println("Canvas");
				form.getCanvas().getChildren().add(item);
			}

			item.setTranslateX(artifact.getCoordinates().getXCoordinate());
			item.setTranslateY(artifact.getCoordinates().getYCoordinate());
			control.getArtifactList().add(item.getIDs()[1], artifact);
			control.getArtifactObservable().add(item.getIDs()[1], artifact.getName());
		}

	}
	
	public int[] createArtifactFormXML(CanvasItem item, BasicForm form, int[] IDs){
		IDs[0] = index;
		IDs[1] = idCreater.createArtifactID();
		IDs[2] = form.getIdCreater().createArtifactID();

		Artifact artifact = form.getArtifactArray().get(IDs[2]);
		ArtifactForm artifactForm = new ArtifactForm(item, control, artifact);
		System.out.println(artifactForm.getTitle());

		artifactForm.setName(artifact.getName());
		artifactForm.getDescriptionTF().setText(artifact.getDescriptoin());
		// artifactForm.getCreatedDP().se;
		artifactForm.getMineTypeCB().setValue(ArtifactClass.values()[artifact.getArtifactIndex()]);

		forms.add(index, artifactForm);
		control.getArtifactList().add(IDs[1], artifact);
		control.getArtifactFormIndex().add(index);
		index++;
		return IDs;
		
	}

}
