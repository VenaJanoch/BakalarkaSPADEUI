package services;

import java.time.LocalDate;
import java.util.ArrayList;

import SPADEPAC.Activity;
import SPADEPAC.Artifact;
import SPADEPAC.ArtifactClass;
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
import forms.BranchForm;
import forms.ChangeForm;
import forms.ConfigurationForm;
import forms.ConfigurationTableForm;
import forms.IterationForm;
import forms.PhaseForm;
import forms.RoleForm;
import forms.TagForm;
import forms.WorkUnitForm;
import graphics.CanvasItem;
import javafx.collections.ObservableList;
import tables.ConfigTable;
import tables.MilestoneTable;
import tables.TagTable;

public class FillCopyForms {

	private Control control;

	private ArrayList<BasicForm> forms;
	private ObjectFactory objF;
	private SegmentLists lists;
	private IdentificatorCreater idCreater;

	public FillCopyForms(Control control, SegmentLists lists, Project project, ArrayList<BasicForm> forms,
			ObjectFactory objFac, IdentificatorCreater idCreator) {

		this.control = control;
		this.forms = forms;
		this.lists = lists;
		this.objF = objFac;
		System.out.println("Form5 copy" + forms.toString());
		this.idCreater = idCreator;

	}

	public Phase fillPhase(BasicForm form, int[] newIDs, Phase copyPhase, int[] oldIDs) {

		Phase phase = (Phase) objF.createPhase();
		phase.setDescription(copyPhase.getDescription());
		phase.setName(copyPhase.getName());
		phase.setEndDate(copyPhase.getEndDate());

		phase.setCoordinates(copyPhase.getCoordinates());
		phase.setConfiguration(copyPhase.getConfiguration());
		phase.setMilestoneIndex(copyPhase.getMilestoneIndex());
		for (int i = 0; i < copyPhase.getWorkUnits().size(); i++) {

			phase.getWorkUnits().add(fillWorkUnit(copyPhase.getWorkUnits().get(i)));
		}
		return phase;

	}

	public int[] createPhase(CanvasItem item, BasicForm form, Phase oldPhase, int[] IDs, int[] oldIDs) {

		int index = IdentificatorCreater.getIndex();
		IDs[0] = index;
		IDs[1] = idCreater.createPhaseID();

		Phase phase = fillPhase(form, item.getIDs(), oldPhase, oldIDs);
		PhaseForm phaseForm = new PhaseForm(item, control, Constans.phaseDragTextIndexs, phase, index);

		forms.add(index, copyFormPhase(phase, phaseForm));

		index++;
		IdentificatorCreater.setIndex(index);

		control.getFillFormsXML().fillWorkUnitFromXML(phaseForm, oldPhase.getWorkUnits());

		form.getPhaseArray().add(IDs[1], phase);
		return IDs;
	}

	public Activity fillActivity(BasicForm form, int[] newIDs, Activity copyActivity, int[] oldIDs) {

		Activity activity = (Activity) objF.createActivity();
		activity.setDescription(copyActivity.getDescription());
		activity.setName(copyActivity.getName());
		activity.setCoordinates(copyActivity.getCoordinates());

		for (int i = 0; i < copyActivity.getWorkUnits().size(); i++) {
			activity.getWorkUnits().add(fillWorkUnit(copyActivity.getWorkUnits().get(i)));
		}

		return activity;

	}

	public int[] createActivity(CanvasItem item, BasicForm form, Activity oldActivity, int[] IDs, int[] oldIDs) {

		int index = IdentificatorCreater.getIndex();

		IDs[0] = index;
		IDs[1] = idCreater.createActivityID();

		Activity activity = fillActivity(form, item.getIDs(), oldActivity, oldIDs);
		ActivityForm activityForm = new ActivityForm(item, control, Constans.phaseDragTextIndexs, activity, index);

		forms.add(index, copyFormActivity(activity, activityForm));
		index++;
		IdentificatorCreater.setIndex(index);

		control.getFillFormsXML().fillWorkUnitFromXML(activityForm, oldActivity.getWorkUnits());

		form.getActivityArray().add(IDs[1], activity);
		return IDs;
	}

	public Iteration fillIteration(BasicForm form, int[] newIDs, Iteration oldIteration, int[] oldIDs) {

		Iteration iteration = (Iteration) objF.createIteration();
		iteration.setDescription(oldIteration.getDescription());
		iteration.setName(oldIteration.getName());
		iteration.setStartDate(oldIteration.getStartDate());
		iteration.setEndDate(oldIteration.getEndDate());
		iteration.setConfiguration(oldIteration.getConfiguration());

		iteration.setCoordinates(oldIteration.getCoordinates());
		iteration.getWorkUnits().addAll(oldIteration.getWorkUnits());

		return iteration;
	}

	public int[] createIteration(CanvasItem item, BasicForm form, Iteration oldIteration, int[] IDs, int[] oldIDs) {
		int index = IdentificatorCreater.getIndex();
		IDs[0] = index;
		IDs[1] = idCreater.createIterationID();

		Iteration iteration = fillIteration(form, item.getIDs(), oldIteration, oldIDs);
		IterationForm iterationForm = new IterationForm(item, control, Constans.iterationDragTextIndexs, iteration,
				index);
		forms.add(index, copyFormIteration(iteration, iterationForm));
		index++;
		IdentificatorCreater.setIndex(index);

		control.getFillFormsXML().fillWorkUnitFromXML(iterationForm, oldIteration.getWorkUnits());

		form.getIterationArray().add(IDs[1], iteration);
		return IDs;
	}

	public WorkUnit fillWorkUnit(WorkUnit oldUnit) {

		WorkUnit workUnit = (WorkUnit) objF.createWorkUnit();

		workUnit.setDescription(oldUnit.getDescription());
		workUnit.setName(oldUnit.getName());
		workUnit.setAssigneeIndex(oldUnit.getAssigneeIndex());
		workUnit.setAuthorIndex(oldUnit.getAuthorIndex());
		workUnit.setPriorityIndex(oldUnit.getPriorityIndex());
		workUnit.setSeverityIndex(oldUnit.getSeverityIndex());
		workUnit.setTypeIndex(oldUnit.getTypeIndex());
		workUnit.setCategory(oldUnit.getCategory());
		workUnit.setResolutionIndex(oldUnit.getResolutionIndex());
		workUnit.setStatusIndex(oldUnit.getStatusIndex());
		workUnit.setEstimatedTime(oldUnit.getEstimatedTime());

		workUnit.setCoordinates(oldUnit.getCoordinates());

		return workUnit;

	}

	public int[] createWorkUnit(CanvasItem item, BasicForm form, WorkUnit oldUnit, int[] IDs, int[] oldIDs) {
		int index = IdentificatorCreater.getIndex();
		WorkUnit unit = fillWorkUnit(oldUnit);
		WorkUnitForm unitForm = new WorkUnitForm(item, control, unit);

		forms.add(index, copyFormWorkUnit(unit, unitForm));
		IDs[0] = index;
		IDs[1] = idCreater.createWorkUnitID();
		IDs[2] = form.getIdCreater().createWorkUnitID();
		form.getWorkUnitArray().add(IDs[2], unit);
		index++;
		IdentificatorCreater.setIndex(index);
		return IDs;
	}

	public Change fillChange(Change oldChange) {

		Change change = (Change) objF.createChange();

		change.setName(oldChange.getName());
		change.setDescriptoin(oldChange.getDescriptoin());

		change.setCoordinates(oldChange.getCoordinates());
		change.setExist(oldChange.isExist());

		return change;

	}

	public int[] createChange(CanvasItem item, BasicForm form, Change oldChange, int[] IDs, int[] oldIDs) {
		int index = IdentificatorCreater.getIndex();
		Change change = fillChange(oldChange);
		;
		ChangeForm changeForm = new ChangeForm(item, control, change);

		forms.add(index, copyFormChagne(oldChange, changeForm));

		IDs[0] = index;
		IDs[1] = idCreater.createChangeID();
		IDs[2] = form.getIdCreater().createChangeID();

		form.getChangeArray().add(IDs[2], IDs[1]);
		lists.getChangeList().add(IDs[1], change);
		index++;
		IdentificatorCreater.setIndex(index);
		return IDs;

	}

	public Artifact fillArtifact(Artifact oldArtifact) {

		Artifact artifact = objF.createArtifact();
		artifact.setName(oldArtifact.getName());
		artifact.setDescriptoin(oldArtifact.getDescriptoin());
		artifact.setCreated(oldArtifact.getCreated());
		artifact.setMimeType(oldArtifact.getMimeType());
		artifact.setAuthorIndex(oldArtifact.getAuthorIndex());
		artifact.setArtifactIndex(oldArtifact.getArtifactIndex());

		artifact.setCoordinates(oldArtifact.getCoordinates());
		artifact.setExist(oldArtifact.isExist());

		return artifact;
	}

	public int[] createArtifact(CanvasItem item, BasicForm form, Artifact oldArtifact, int[] IDs, int[] oldIDs) {
		int index = IdentificatorCreater.getIndex();
		Artifact artifact = fillArtifact(oldArtifact);
		ArtifactForm artifactForm = new ArtifactForm(item, control, artifact);
		forms.add(index, copyFormArtifact(artifact, artifactForm));
		IDs[0] = index;
		IDs[1] = idCreater.createArtifactID();
		IDs[2] = form.getIdCreater().createArtifactID();

		form.getArtifactArray().add(IDs[2], IDs[1]);

		control.getLists().getArtifactList().add(IDs[1], artifact);

		index++;
		IdentificatorCreater.setIndex(index);
		return IDs;
	}

	public PhaseForm copyFormPhase(Phase phase, PhaseForm phaseForm) {

		System.out.println("CreatePhase");

		phaseForm.getDescriptionTF().setText(phase.getDescription());
		phaseForm.getNameTF().setText(phase.getName());
		phaseForm.setName(phase.getName());
		phaseForm.getDateDP().setValue(control.convertDateFromXML(phase.getEndDate()));
		phaseForm.getMilestoneCB().setValue(control.getLists().getMilestoneObservable().get(phase.getMilestoneIndex()));
		phaseForm.getConfigCB().setValue(control.getLists().getConfigObservable().get(phase.getConfiguration()));

		return phaseForm;
	}

	public ActivityForm copyFormActivity(Activity activity, ActivityForm form) {

		System.out.println("CreateActivity");

		form.getDescriptionTF().setText(activity.getDescription());
		form.getNameTF().setText(activity.getName());
		form.setName(activity.getName());

		return form;

	}

	public IterationForm copyFormIteration(Iteration iteration, IterationForm form) {

		System.out.println("CreateIteration");

		form.getDescriptionTF().setText(iteration.getDescription());
		form.getNameTF().setText(iteration.getName());
		form.setName(iteration.getName());

		form.getConfigCB().setValue(control.getLists().getConfigObservable().get(iteration.getConfiguration()));
		form.getDateDP().setValue(control.convertDateFromXML(iteration.getEndDate()));
		form.getDate2DP().setValue(control.convertDateFromXML(iteration.getStartDate()));

		return form;

	}

	public WorkUnitForm copyFormWorkUnit(WorkUnit unit, WorkUnitForm form) {

		System.out.println("CreateUnit");

		form.getNameTF().setText(unit.getName());
		form.getDescriptionTF().setText(unit.getDescription());
		form.getPriorityCB().setValue(lists.getPriorityObservable().get(unit.getPriorityIndex()));
		form.getSeverityCB().setValue(lists.getSeverityTypeObservable().get(unit.getSeverityIndex()));
		form.getTypeCB().setValue(lists.getTypeObservable().get(unit.getTypeIndex()));
		form.getCategoryTF().setText(unit.getCategory());
		form.getStatusCB().setValue(lists.getStatusTypeObservable().get(unit.getStatusIndex()));
		form.getResolutionCB().setValue(lists.getResolutionTypeObservable().get(unit.getResolutionIndex()));
		form.getEstimatedTimeTF().setText(String.valueOf(unit.getEstimatedTime()));

		String author = control.getLists().getRoleList().get(unit.getAuthorIndex()).getName();
		String assignee = control.getLists().getRoleList().get(unit.getAssigneeIndex()).getName();

		form.getAuthorRoleCB().setValue(author);
		form.getAsigneeRoleCB().setValue(assignee);

		return form;

	}

	private ChangeForm copyFormChagne(Change change, ChangeForm changeForm) {

		changeForm.setName(change.getName());
		changeForm.getNameTF().setText(change.getName());
		changeForm.getDescriptionTF().setText(change.getDescriptoin());
		changeForm.getExistRB().setSelected(change.isExist());

		return null;
	}

	private ArtifactForm copyFormArtifact(Artifact artifact, ArtifactForm artifactForm) {

		artifactForm.setName(artifact.getName());
		artifactForm.getDescriptionTF().setText(artifact.getDescriptoin());
		artifactForm.getMineTypeCB().setValue(ArtifactClass.values()[artifact.getArtifactIndex()]);
		artifactForm.getDateDP().setValue(control.convertDateFromXML(artifact.getCreated()));
		artifactForm.getExistRB().setSelected(artifact.isExist());

		String author = control.getLists().getRoleList().get(artifact.getAuthorIndex()).getName();

		artifactForm.getAuthorRoleCB().setValue(author);
		artifactForm.setNew(false);

		return artifactForm;
	}

	public IdentificatorCreater getIdCreater() {
		return idCreater;
	}

	public void setIdCreater(IdentificatorCreater idCreater) {
		this.idCreater = idCreater;
	}

}
