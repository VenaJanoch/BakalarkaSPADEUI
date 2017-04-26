package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	private DeleteControl deleteControl;
	private FormControl formControl;
	private int oldUnit;

	public FillCopyForms(Control control, SegmentLists lists, Project project, ArrayList<BasicForm> forms,
			ObjectFactory objFac, IdentificatorCreater idCreator, DeleteControl deleteControl, FormControl formControl) {

		this.control = control;
		this.forms = forms;
		this.lists = lists;
		this.objF = objFac;
		this.idCreater = idCreator;
		this.deleteControl = deleteControl;
		this.formControl = formControl;
	}

	public Phase fillPhase(BasicForm form, int[] newIDs, Phase copyPhase, int[] oldIDs) {

		Phase phase = (Phase) objF.createPhase();
		phase.setDescription(copyPhase.getDescription());
		phase.setName(copyPhase.getName());
		phase.setEndDate(copyPhase.getEndDate());

		phase.setCoordinates(copyPhase.getCoordinates());
		phase.setConfiguration(copyPhase.getConfiguration());
		phase.setMilestoneIndex(copyPhase.getMilestoneIndex());

		return phase;

	}

	public int[] createPhase(CanvasItem item, BasicForm form, Phase oldPhase, int[] IDs, int[] oldIDs) {

		int index = IdentificatorCreater.getIndex();
		IDs[0] = index;
		IDs[1] = idCreater.createPhaseID();
		IDs[3] = form.getCanvasItem().getIDs()[0];

		Phase phase = fillPhase(form, item.getIDs(), oldPhase, oldIDs);
		PhaseForm phaseForm = new PhaseForm(item, control, Constans.phaseDragTextIndexs, phase, index, deleteControl);

		forms.add(index, copyFormPhase(phase, phaseForm));

		index++;
		IdentificatorCreater.setIndex(index);

		fillWorkUnitFromCopy(phaseForm, oldPhase.getWorkUnits());

		form.getPhaseArray().add(IDs[1], phase);
		return IDs;
	}

	public void fillWorkUnitFromCopy(BasicForm form, List<Integer> units) {

		for (int i = 0; i < units.size(); i++) {
			WorkUnit unit = lists.getWorkUnitList().get(units.get(i));
			oldUnit = units.get(i);

			CanvasItem item = new CanvasItem(SegmentType.WorkUnit, unit.getName(), control, form, 3,
					unit.getCoordinates().getXCoordinate(), unit.getCoordinates().getYCoordinate(),
					control.getContexMenu(), control.getLinkControl(), form.getCanvas());

			form.getCanvas().getCanvas().getChildren().add(item);

			item.setTranslateX(unit.getCoordinates().getXCoordinate());
			item.setTranslateY(unit.getCoordinates().getYCoordinate());

		}

	}

	public Activity fillActivity(BasicForm form, int[] newIDs, Activity copyActivity, int[] oldIDs) {

		Activity activity = (Activity) objF.createActivity();
		activity.setDescription(copyActivity.getDescription());
		activity.setName(copyActivity.getName());
		activity.setCoordinates(copyActivity.getCoordinates());

		return activity;

	}

	public int[] createActivity(CanvasItem item, BasicForm form, Activity oldActivity, int[] IDs, int[] oldIDs) {

		int index = IdentificatorCreater.getIndex();

		IDs[0] = index;
		IDs[1] = idCreater.createActivityID();
		IDs[3] = form.getCanvasItem().getIDs()[0];

		Activity activity = fillActivity(form, item.getIDs(), oldActivity, oldIDs);
		ActivityForm activityForm = new ActivityForm(item, control, Constans.phaseDragTextIndexs, activity, index,
				deleteControl);

		forms.add(index, copyFormActivity(activity, activityForm));
		index++;
		IdentificatorCreater.setIndex(index);

		fillWorkUnitFromCopy(activityForm, oldActivity.getWorkUnits());
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

		return iteration;
	}

	public int[] createIteration(CanvasItem item, BasicForm form, Iteration oldIteration, int[] IDs, int[] oldIDs) {
		int index = IdentificatorCreater.getIndex();
		IDs[0] = index;
		IDs[1] = idCreater.createIterationID();
		IDs[3] = form.getCanvasItem().getIDs()[0];

		Iteration iteration = fillIteration(form, item.getIDs(), oldIteration, oldIDs);
		IterationForm iterationForm = new IterationForm(item, control, Constans.iterationDragTextIndexs, iteration,
				index, deleteControl);
		forms.add(index, copyFormIteration(iteration, iterationForm));
		index++;
		IdentificatorCreater.setIndex(index);

		fillWorkUnitFromCopy(iterationForm, oldIteration.getWorkUnits());
		form.getIterationArray().add(IDs[1], iteration);
		return IDs;
	}

	public int fillWorkUnit(int index) {

		WorkUnit workUnit = (WorkUnit) objF.createWorkUnit();
		WorkUnit oldUnit = lists.getWorkUnitList().get(index);

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
		workUnit.setExist(oldUnit.isExist());
		workUnit.setCoordinates(oldUnit.getCoordinates());

		lists.getWorkUnitList().add(workUnit);
		return lists.getWorkUnitList().indexOf(workUnit);

	}

	public int[] createCopyWorkUnit(CanvasItem item, BasicForm form, int[] IDs) {
		int index = IdentificatorCreater.getIndex();
		IDs[0] = index;
		IDs[1] = idCreater.createWorkUnitID();
		IDs[2] = form.getIdCreater().createWorkUnitID();
		IDs[3] = form.getFormID();

		WorkUnit unit = lists.getWorkUnitList().get(fillWorkUnit(oldUnit));
		WorkUnitForm unitForm = new WorkUnitForm(item, control, unit, deleteControl);

		forms.add(index, copyFormWorkUnit(unit, unitForm, item));
		// lists.getWorkUnitList().add(unit);
		lists.getWorkUnitFormIndex().add(index);
		form.getWorkUnitArray().add(IDs[2], IDs[1]);
		index++;
		IdentificatorCreater.setIndex(index);
		return IDs;
	}

	public int[] createWorkUnit(CanvasItem item, BasicForm form, WorkUnit oldUnit, int[] IDs, int[] oldIDs) {
		int index = IdentificatorCreater.getIndex();

		WorkUnit unit = lists.getWorkUnitList().get(fillWorkUnit(oldIDs[1]));
		WorkUnitForm unitForm = new WorkUnitForm(item, control, unit, deleteControl);

		forms.add(index, copyFormWorkUnit(unit, unitForm, item));
		IDs[0] = index;
		IDs[1] = idCreater.createWorkUnitID();
		IDs[2] = form.getIdCreater().createWorkUnitID();
		IDs[3] = form.getFormID();

		// lists.getWorkUnitList().add(unit);
		lists.getWorkUnitFormIndex().add(index);
		form.getWorkUnitArray().add(IDs[2], IDs[1]);

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
		Configuration conf = form.getConfigArray();
		ChangeForm changeForm = new ChangeForm(item, control, change, conf, deleteControl);

		forms.add(index, copyFormChange(oldChange, changeForm));

		IDs[0] = index;
		IDs[1] = idCreater.createChangeID();
		IDs[2] = form.getIdCreater().createChangeID();
		IDs[3] = form.getCanvasItem().getIDs()[0];

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
		ArtifactForm artifactForm = new ArtifactForm(item, control, artifact, deleteControl, form.getConfigArray());
		forms.add(index, copyFormArtifact(artifact, artifactForm));
		IDs[0] = index;
		IDs[1] = idCreater.createArtifactID();
		IDs[2] = form.getIdCreater().createArtifactID();
		IDs[3] = form.getCanvasItem().getIDs()[0];
		form.getArtifactArray().add(IDs[2], IDs[1]);

		control.getLists().getArtifactList().add(IDs[1], artifact);

		index++;
		IdentificatorCreater.setIndex(index);
		return IDs;
	}

	public PhaseForm copyFormPhase(Phase phase, PhaseForm phaseForm) {

		phaseForm.getDescriptionTF().setText(phase.getDescription());
		phaseForm.getNameTF().setText(phase.getName());
		phaseForm.setName(phase.getName());
		phaseForm.getDateDP().setValue(control.convertDateFromXML(phase.getEndDate()));
		if (phase.getMilestoneIndex() != null) {
			phaseForm.getMilestoneCB()
					.setValue(control.getLists().getMilestoneObservable().get(phase.getMilestoneIndex()+1));
		}
		if (phase.getConfiguration() != null) {
			phaseForm.getConfigCB().setValue(control.getLists().getConfigObservable().get(phase.getConfiguration()+1));
		}

		phaseForm.setNew(false);
		return phaseForm;
	}

	public ActivityForm copyFormActivity(Activity activity, ActivityForm form) {

		form.getDescriptionTF().setText(activity.getDescription());
		form.getNameTF().setText(activity.getName());
		form.setName(activity.getName());
		form.setNew(false);
		return form;

	}

	public IterationForm copyFormIteration(Iteration iteration, IterationForm form) {

		form.getDescriptionTF().setText(iteration.getDescription());
		form.getNameTF().setText(iteration.getName());
		form.setName(iteration.getName());

		if (iteration.getConfiguration() != null) {
			form.getConfigCB().setValue(control.getLists().getConfigObservable().get(iteration.getConfiguration()+1));
		}
		
		form.getDateDP().setValue(control.convertDateFromXML(iteration.getEndDate()));
		form.getDate2DP().setValue(control.convertDateFromXML(iteration.getStartDate()));
		form.setNew(false);
		return form;

	}

	public WorkUnitForm copyFormWorkUnit(WorkUnit unit, WorkUnitForm form, CanvasItem item) {

		form.setName(unit.getName());
		form.getNameTF().setText(unit.getName());
		form.getDescriptionTF().setText(unit.getDescription());
		formControl.workUnitFormControl(form, unit);
		
		if (!unit.isExist()) {
			item.getSegmentInfo().setRectangleColor(Constans.nonExistRectangleBorderColor);
		}
		form.setNew(false);
		return form;

	}

	public ChangeForm copyFormChange(Change change, ChangeForm changeForm) {

		changeForm.setName(change.getName());
		changeForm.getNameTF().setText(change.getName());
		changeForm.getDescriptionTF().setText(change.getDescriptoin());
		changeForm.getExistRB().setSelected(change.isExist());

		if (!change.isExist()) {
			changeForm.getCanvasItem().getSegmentInfo().setRectangleColor(Constans.nonExistRectangleBorderColor);

		}
		changeForm.setNew(false);

		return changeForm;
	}

	public ArtifactForm copyFormArtifact(Artifact artifact, ArtifactForm artifactForm) {

		artifactForm.setName(artifact.getName());
		artifactForm.getNameTF().setText(artifact.getName());
		artifactForm.getDescriptionTF().setText(artifact.getDescriptoin());
		artifactForm.getMineTypeCB().setValue(ArtifactClass.values()[artifact.getArtifactIndex()]);
		artifactForm.getDateDP().setValue(control.convertDateFromXML(artifact.getCreated()));
		artifactForm.getExistRB().setSelected(artifact.isExist());

		if (artifact.getAuthorIndex() != null) {
			String author = lists.getRoleList().get(artifact.getAuthorIndex()).getName();
			artifactForm.getAuthorRoleCB().setValue(author);			
		}

		artifactForm.setNew(false);

		if (!artifact.isExist()) {
			artifactForm.getCanvasItem().getSegmentInfo().setRectangleColor(Constans.nonExistRectangleBorderColor);
		}

		return artifactForm;
	}

	public IdentificatorCreater getIdCreater() {
		return idCreater;
	}

	public void setIdCreater(IdentificatorCreater idCreater) {
		this.idCreater = idCreater;
	}

}
