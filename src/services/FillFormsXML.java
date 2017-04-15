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
import SPADEPAC.Link;
import SPADEPAC.Milestone;
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
import tables.BranchTable;
import tables.CPRTable;
import tables.ClassTable;
import tables.ConfigTable;
import tables.CriterionTable;
import tables.MilestoneTable;
import tables.RoleTable;
import tables.RoleTypeTable;
import tables.TagTable;

public class FillFormsXML {

	private Control control;
	private Project project;
	private ArrayList<BasicForm> forms;

	private IdentificatorCreater idCreater;
	private SegmentLists lists;
	private FillCopyForms copyForms;
	private LinkControl linkControl;
	
	public FillFormsXML(Control control, SegmentLists lists, Project project, ArrayList<BasicForm> forms,
			FillCopyForms copyForms, IdentificatorCreater idCreator, LinkControl linkControl) {

		this.control = control;
		this.project = project;
		this.copyForms = copyForms;
		this.forms = forms;
		this.lists = lists;
		idCreater = idCreator;
		this.linkControl = linkControl;
		System.out.println("Form3 XML " + forms.toString());

	}

	public void fillProjectFromXML(ProjectForm form) {

		form.setName(project.getName());
		form.getNameTF().setText(project.getName());
		form.getDescriptionTF().setText(project.getDescription());
		form.getDateDP().setValue(control.convertDateFromXML(project.getStartDate()));
		form.getDate2DP().setValue(control.convertDateFromXML(project.getEndDate()));

		fillPrioritybservabel(project.getPriority());
		fillSeveritybservabel(project.getSeverity());
		fillTypeObservabel(project.getTypes());
		fillRelationbservabel(project.getRelation());
		fillResolutionbservabel(project.getResolution());
		fillStatusbservabel(project.getStatus());
		fillCriterionObservabel(project.getCriterions());
		fillMilestoneObservabel(project.getMilestones());
		fillBranchFromXML(project.getBranches());
		fillRoleTypeFromXML(project.getRoleType());
		fillRoleFromXML(project.getRoles());

		// fillChangeObservabel(project.getChanges());
		// fillArtifactObservabel(project.getArtifacts());
		fillCPRObservabel(project.getCpr());

		fillConfigurationFromXML(form, project.getConfiguration());

	}

	public void createLinks(List<Link> links) {

		for (int i = 0; i < links.size(); i++) {

			if (links.get(i).getType().equals(SegmentType.WorkUnit.name())) {
				fillWorkUnitLinks(links.get(i));
			} else {
				fillConfigLinks(links.get(i));
			}

		}

	}

	public void fillWorkUnitLinks(Link link) {
		int leftI = link.getLeftUnitIndex();
		int rightI = link.getRightUnitIndex();

		int leftFormI = lists.getWorkUnitFormIndex().get(leftI);
		int rightFormI = lists.getWorkUnitFormIndex().get(rightI);

		CanvasItem itemChange = control.getForms().get(leftFormI).getCanvasItem();
		CanvasItem itemArtifact = control.getForms().get(rightFormI).getCanvasItem();

		linkControl.ArrowManipulationWorkUnit(itemChange, true);
		linkControl.ArrowManipulationWorkUnit(itemArtifact, true);

	}

	public void fillConfigLinks(Link link) {
		int changeI = link.getChangeIndex();
		int artifactI = link.getArtifactIndex();

		int chFormI = lists.getChangeFormIndex().get(changeI);
		int aFormI = lists.getArtifactFormIndex().get(artifactI);

		CanvasItem itemChange = control.getForms().get(chFormI).getCanvasItem();
		CanvasItem itemArtifact = control.getForms().get(aFormI).getCanvasItem();

		linkControl.ArrowManipulation(itemChange, true);
		linkControl.ArrowManipulation(itemArtifact, true);
	}

	public void fillPhasesFromXML(BasicForm form) {

		for (int i = 0; i < project.getPhases().size(); i++) {
			Phase phase = project.getPhases().get(i);

			CanvasItem item = new CanvasItem(SegmentType.Phase, phase.getName(), control, form, 1,
					phase.getCoordinates().getXCoordinate(), phase.getCoordinates().getYCoordinate(),
					control.getContexMenu(), linkControl);

			form.getCanvas().getCanvas().getChildren().add(item);

			fillWorkUnitFromXML(control.getForms().get(item.getIDs()[0]), phase.getWorkUnits());

		}

	}

	public int[] createPhaseFormXML(CanvasItem item, BasicForm form, int[] IDs) {

		int index = IdentificatorCreater.getIndex();
		IDs[0] = index;
		IDs[1] = idCreater.createPhaseID();

		Phase phase = form.getPhaseArray().get(IDs[1]);

		PhaseForm phaseForm = new PhaseForm(item, control, Constans.phaseDragTextIndexs, phase, index);

		copyForms.copyFormPhase(phase, phaseForm);
		index++;
		IdentificatorCreater.setIndex(index);
		forms.add(IDs[0], phaseForm);

		phaseForm.getConfigCB().setValue(lists.getConfigObservable().get(phase.getConfiguration()));

		return IDs;

	}

	public void fillIterationFromXML(BasicForm form) {

		for (int i = 0; i < project.getIterations().size(); i++) {
			Iteration iteration = project.getIterations().get(i);

			CanvasItem item = new CanvasItem(SegmentType.Iteration, iteration.getName(), control, forms.get(0), 1,
					iteration.getCoordinates().getXCoordinate(), iteration.getCoordinates().getYCoordinate(),
					control.getContexMenu(), linkControl);
			form.getCanvas().getCanvas().getChildren().add(item);

			fillWorkUnitFromXML(forms.get(item.getIDs()[0]), iteration.getWorkUnits());

		}

	}

	public int[] createIterationFormXML(CanvasItem item, BasicForm form, int[] IDs) {
		int index = IdentificatorCreater.getIndex();
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
		IdentificatorCreater.setIndex(index);
		iterationForm.getConfigCB().setValue(lists.getConfigObservable().get(iteration.getConfiguration()));

		forms.add(IDs[0], iterationForm);
		return IDs;

	}

	public void fillActivityFromXML(BasicForm form) {

		for (int i = 0; i < project.getActivities().size(); i++) {
			Activity activity = project.getActivities().get(i);

			CanvasItem item = new CanvasItem(SegmentType.Activity, activity.getName(), control, forms.get(0), 1,
					activity.getCoordinates().getXCoordinate(), activity.getCoordinates().getYCoordinate(),
					control.getContexMenu(), linkControl);
			form.getCanvas().getCanvas().getChildren().add(item);

			fillWorkUnitFromXML(forms.get(item.getIDs()[0]), activity.getWorkUnits());

			item.setTranslateX(activity.getCoordinates().getXCoordinate());
			item.setTranslateY(activity.getCoordinates().getYCoordinate());

		}
	}

	public int[] createActivityFormXML(CanvasItem item, BasicForm form, int[] IDs) {
		int index = IdentificatorCreater.getIndex();
		IDs[0] = index;
		IDs[1] = idCreater.createActivityID();
		Activity activity = form.getActivityArray().get(IDs[1]);
		ActivityForm activityForm = new ActivityForm(item, control, Constans.activityDragTextIndexs, activity, index);

		activityForm.getNameTF().setText(activity.getName());
		activityForm.getDescriptionTF().setText(activity.getDescription());

		forms.add(index, activityForm);
		index++;
		IdentificatorCreater.setIndex(index);
		return IDs;

	}

	public void fillWorkUnitFromXML(BasicForm form, List<Integer> units) {

		for (int i = 0; i < units.size(); i++) {
			WorkUnit unit = lists.getWorkUnitList().get(units.get(i));

			CanvasItem item = new CanvasItem(SegmentType.WorkUnit, unit.getName(), control, form, 1,
					unit.getCoordinates().getXCoordinate(), unit.getCoordinates().getYCoordinate(),
					control.getContexMenu(), linkControl);
			form.getCanvas().getCanvas().getChildren().add(item);

			item.setTranslateX(unit.getCoordinates().getXCoordinate());
			item.setTranslateY(unit.getCoordinates().getYCoordinate());
		}

	}

	public int[] createWorkUnitFormXML(CanvasItem item, BasicForm form, int[] IDs) {
		int index = IdentificatorCreater.getIndex();

		IDs[0] = index;
		IDs[1] = idCreater.createWorkUnitID();
		IDs[2] = form.getIdCreater().createWorkUnitID();

		int tmpIndex = form.getWorkUnitArray().get(IDs[2]);

		WorkUnit unit = lists.getWorkUnitList().get(tmpIndex);
		WorkUnitForm workUnitForm = new WorkUnitForm(item, control, unit);

		workUnitForm.getNameTF().setText(unit.getName());
		workUnitForm.getDescriptionTF().setText(unit.getDescription());
		workUnitForm.getPriorityCB().setValue(lists.getPriorityObservable().get(unit.getPriorityIndex()));
		workUnitForm.getSeverityCB().setValue(lists.getSeverityTypeObservable().get(unit.getSeverityIndex()));
		workUnitForm.getTypeCB().setValue(lists.getTypeObservable().get(unit.getTypeIndex()));
		workUnitForm.getCategoryTF().setText(unit.getCategory());
		workUnitForm.getStatusCB().setValue(lists.getStatusTypeObservable().get(unit.getStatusIndex()));
		workUnitForm.getResolutionCB().setValue(lists.getResolutionTypeObservable().get(unit.getResolutionIndex()));
		workUnitForm.getEstimatedTimeTF().setText(String.valueOf(unit.getEstimatedTime()));

		index++;
		IdentificatorCreater.setIndex(index);
		forms.add(IDs[0], workUnitForm);
		lists.getWorkUnitFormIndex().add(IDs[1], IDs[0]);
		String author = control.getLists().getRoleList().get(unit.getAuthorIndex()).getName();
		String assignee = control.getLists().getRoleList().get(unit.getAssigneeIndex()).getName();
		workUnitForm.getAuthorRoleCB().setValue(author);
		workUnitForm.getAsigneeRoleCB().setValue(assignee);

		return IDs;
	}

	public void fillConfigurationFromXML(BasicForm form, List<Configuration> configs) {

		ObservableList<ConfigTable> data = FXCollections.observableArrayList();

		String name;
		String release;

		for (int i = 0; i < configs.size(); i++) {

			Configuration conf = configs.get(i);
			name = conf.getName();

			if (conf.isIsRelease()) {
				release = "YES";
			} else {
				release = "NO";
			}

			CanvasItem item = new CanvasItem(SegmentType.Configuration, name, control, form, 1, 0, 0,
					control.getContexMenu(), linkControl);
			data.add(new ConfigTable(name, release, item.getIDs()[0]));

			lists.getConfigObservable().add(item.getIDs()[1], name);
			control.getConfTableForm().getTableTV().setItems(data);
		}

	}

	public int[] createConfigurationFormXML(CanvasItem item, BasicForm form, int[] IDs) {
		int index = IdentificatorCreater.getIndex();
		IDs[0] = index;
		IDs[1] = idCreater.createConfigurationID();

		Configuration conf = lists.getConfigList().get(IDs[1]);
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

		lists.getConfigFormIndex().add(IDs[1], index);
		lists.getConfigObservable().add(conf.getName());
		forms.add(index, configForm);

		index++;
		IdentificatorCreater.setIndex(index);
		String author = control.getLists().getRoleList().get(conf.getAuthorIndex()).getName();

		configForm.getAuthorRoleCB().setValue(author);

		fillChangeFromXML(configForm, conf.getChangesIndexs());
		fillTagsFromXML(configForm, conf.getTags());
		fillArtifactFromXML(configForm, conf.getArtifactsIndexs());
		return IDs;

	}

	public void fillTagsFromXML(ConfigurationForm formc, List<String> tags) {

		ObservableList<TagTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < tags.size(); i++) {
			data.add(new TagTable(tags.get(i)));
		}

		formc.getTagForm().getTableTV().setItems(data);

	}

	public void fillRoleFromXML(List<Role> roles) {
		ObservableList<RoleTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < roles.size(); i++) {

			Role role = roles.get(i);
			data.add(new RoleTable(role.getName(), role.getDescription(),
					lists.getRoleTypeObservable().get(role.getType())));

			control.getLists().getRoleObservable().add(role.getName());
		}

		control.getRoleForm().getTableTV().setItems(data);

	}

	public void fillRoleTypeFromXML(List<RoleType> roles) {
		ObservableList<ClassTable> data = FXCollections.observableArrayList();
		for (int i = 0; i < roles.size(); i++) {

			RoleType role = roles.get(i);

			data.add(new ClassTable(role.getName(), role.getRoleClass(), role.getRoleSuperClass()));
			lists.getRoleTypeObservable().add(role.getName());
		}

		control.getRoleForm().getRoleTForm().getTableTV().setItems(data);
	}

	public void fillBranchFromXML(List<Branch> item) {

		ObservableList<BranchTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < item.size(); i++) {
			String name = item.get(i).getName();
			boolean main = item.get(i).isIsMain();

			if (main) {
				data.add(new BranchTable(name, "TRUE"));

			} else {
				data.add(new BranchTable(name, "FALSE"));
			}

			lists.getBranchObservable().add(item.get(i).getName());
		}

		control.getBranchFrom().getTableTV().setItems(data);
	}

	public void fillChangeFromXML(BasicForm form, List<Integer> changes) {

		for (int i = 0; i < changes.size(); i++) {
			Change change = control.getLists().getChangeList().get(changes.get(i));
			System.out.println("Nevim");
			CanvasItem item = new CanvasItem(SegmentType.Change, change.getName(), control, form, 1,
					change.getCoordinates().getXCoordinate(), change.getCoordinates().getYCoordinate(),
					control.getContexMenu(), linkControl);
			form.getCanvas().getCanvas().getChildren().add(item);

			item.setTranslateX(change.getCoordinates().getXCoordinate());
			item.setTranslateY(change.getCoordinates().getYCoordinate());

		}

	}

	public int[] createChangeFormXML(CanvasItem item, BasicForm form, int[] IDs) {
		int index = IdentificatorCreater.getIndex();
		IDs[0] = index;
		IDs[1] = idCreater.createChangeID();
		IDs[2] = form.getIdCreater().createChangeID();

		int index1 = form.getChangeArray().get(IDs[2]);
		Change change = control.getLists().getChangeList().get(index1);
		ChangeForm changeForm = new ChangeForm(item, control, change);

		changeForm.setName(change.getName());
		changeForm.getNameTF().setText(change.getName());
		changeForm.getDescriptionTF().setText(change.getDescriptoin());
		changeForm.getExistRB().setSelected(change.isExist());

		forms.add(index, changeForm);
		lists.getChangeFormIndex().add(index);
		index++;
		IdentificatorCreater.setIndex(index);
		return IDs;

	}

	public void fillArtifactFromXML(BasicForm form, List<Integer> artifacts) {

		for (int i = 0; i < artifacts.size(); i++) {
			Artifact artifact = control.getLists().getArtifactList().get(artifacts.get(i));

			CanvasItem item = new CanvasItem(SegmentType.Artifact, artifact.getName(), control, form, 1,
					artifact.getCoordinates().getXCoordinate(), artifact.getCoordinates().getYCoordinate(),
					control.getContexMenu(), linkControl);

			if ((form.getCanvasItem().getType() == SegmentType.Configuration)) {
				form.getCanvas().getCanvas().getChildren().add(item);
			}

			item.setTranslateX(artifact.getCoordinates().getXCoordinate());
			item.setTranslateY(artifact.getCoordinates().getYCoordinate());

		}

	}

	public int[] createArtifactFormXML(CanvasItem item, BasicForm form, int[] IDs) {
		int index = IdentificatorCreater.getIndex();
		IDs[0] = index;
		IDs[1] = idCreater.createArtifactID();
		IDs[2] = form.getIdCreater().createArtifactID();

		int index1 = form.getArtifactArray().get(IDs[2]);
		Artifact artifact = control.getLists().getArtifactList().get(index1);
		ArtifactForm artifactForm = new ArtifactForm(item, control, artifact);

		artifactForm.setName(artifact.getName());
		artifactForm.getDescriptionTF().setText(artifact.getDescriptoin());
		// artifactForm.getCreatedDP().se;
		artifactForm.getMineTypeCB().setValue(ArtifactClass.values()[artifact.getArtifactIndex()]);
		artifactForm.getDateDP().setValue(control.convertDateFromXML(artifact.getCreated()));
		artifactForm.getExistRB().setSelected(artifact.isExist());

		String author = control.getLists().getRoleList().get(artifact.getAuthorIndex()).getName();

		artifactForm.getAuthorRoleCB().setValue(author);
		artifactForm.setNew(false);

		forms.add(index, artifactForm);
		control.getLists().getArtifactFormIndex().add(index);
		index++;
		IdentificatorCreater.setIndex(index);
		return IDs;

	}

	private void fillCPRObservabel(List<ConfigPersonRelation> cprs) {

		ObservableList<CPRTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < cprs.size(); i++) {
			control.getLists().getCPRObservable().add(cprs.get(i).getName());
			String role = lists.getRoleList().get(cprs.get(i).getPersonIndex()).getName();
			data.add(new CPRTable(cprs.get(i).getName(), role));
		}

		control.getCPRForm().getTableTV().setItems(data);
	}

	private void fillPrioritybservabel(List<Priority> item) {

		ObservableList<ClassTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < item.size(); i++) {

			String name = item.get(i).getName();
			String classi = item.get(i).getPriorityClass();
			String superi = item.get(i).getPrioritySuperClass();

			control.getLists().getPriorityObservable().add(name);
			data.add(new ClassTable(name, classi, superi));
		}

		control.getPriorityForm().getTableTV().setItems(data);
	}

	private void fillTypeObservabel(List<Type> item) {

		ObservableList<ClassTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < item.size(); i++) {
			String name = item.get(i).getName();
			String classi = item.get(i).getTypeClass();
			String superi = item.get(i).getTypeSuperClass();

			control.getLists().getTypeObservable().add(name);
			data.add(new ClassTable(name, classi, superi));
		}

		control.getTypeForm().getTableTV().setItems(data);
	}

	private void fillSeveritybservabel(List<Severity> item) {

		ObservableList<ClassTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < item.size(); i++) {
			lists.getSeverityTypeObservable().add(item.get(i).getName());
			String name = item.get(i).getName();
			String classi = item.get(i).getSeverityClass();
			String superi = item.get(i).getSeveritySuperClass();

			data.add(new ClassTable(name, classi, superi));
		}

		control.getSeverityForm().getTableTV().setItems(data);
	}

	private void fillRelationbservabel(List<Relation> item) {

		ObservableList<ClassTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < item.size(); i++) {
			lists.getRelationTypeObservable().add(item.get(i).getName());
			String name = item.get(i).getName();
			String classi = item.get(i).getRelationClass();
			String superi = item.get(i).getRelationSuperClass();

			data.add(new ClassTable(name, classi, superi));
		}

		control.getRelationForm().getTableTV().setItems(data);
	}

	private void fillResolutionbservabel(List<Resolution> item) {

		ObservableList<ClassTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < item.size(); i++) {
			lists.getResolutionTypeObservable().add(item.get(i).getName());
			String name = item.get(i).getName();
			String classi = item.get(i).getResolutionClass();
			String superi = item.get(i).getResolutionSuperClass();

			data.add(new ClassTable(name, classi, superi));
		}

		control.getResolutionForm().getTableTV().setItems(data);
	}

	private void fillStatusbservabel(List<Status> item) {

		ObservableList<ClassTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < item.size(); i++) {
			lists.getStatusTypeObservable().add(item.get(i).getName());
			String name = item.get(i).getName();
			String classi = item.get(i).getStatusClass();
			String superi = item.get(i).getStatusSuperClass();

			data.add(new ClassTable(name, classi, superi));
		}

		control.getStatusForm().getTableTV().setItems(data);
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
			control.getLists().getCriterionObservable().add(criterions.get(i).getName());
			data.add(new CriterionTable(criterions.get(i).getName(), criterions.get(i).getDescription()));
		}

		control.getMilestoneForm().getCriterionForm().getTableTV().setItems(data);
	}

	/*** Getrs and Setrs ****/

	public IdentificatorCreater getIdCreater() {
		return idCreater;
	}

	public void setIdCreater(IdentificatorCreater idCreater) {
		this.idCreater = idCreater;
	}

}
