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

	/** Globální proměnné třídy */
	private Control control;
	private Project project;
	private ArrayList<BasicForm> forms;
	private ObjectFactory objF;
	private SegmentLists lists;

	private IdentificatorCreater idCreater;
	private DeleteControl deleteControl;
	private FormControl formControl;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy
	 * 
	 * @param control
	 * @param lists
	 * @param project
	 * @param forms
	 * @param objFac
	 * @param idCreater
	 * @param deleteControl
	 * @param formControl
	 */
	public FillForms(Control control, SegmentLists lists, Project project, ArrayList<BasicForm> forms,
			ObjectFactory objFac, IdentificatorCreater idCreater, DeleteControl deleteControl,
			FormControl formControl) {

		this.formControl = formControl;
		this.control = control;
		this.project = project;
		this.forms = forms;
		this.lists = lists;
		this.objF = objFac;
		this.idCreater = idCreater;
		this.deleteControl = deleteControl;

	}

	/**
	 * Vyplní data z formuláře do datové struktury pro Project
	 * 
	 * @param description
	 *            popis description
	 * @param name
	 *            data z pole name
	 * @param startDate
	 *            data z kalendaře
	 * @param endDate
	 *            data z kalendaře
	 */
	public void fillProject(String description, String name, LocalDate startDate, LocalDate endDate) {

		project.setDescription(formControl.fillTextMapper(description));
		project.setName(formControl.fillTextMapper(name));
		project.setEndDate(control.convertDate(endDate));
		project.setStartDate(control.convertDate(startDate));
	}

	/**
	 * Vyplní data z formuláře Phase do datové struktury pro Phase
	 * 
	 * @param phase
	 *            instance třídy phase
	 * @param ID
	 *            identifikátory dané phase
	 * @param description
	 * @param name
	 * @param endDate
	 * @param confIndex
	 * @param milestoneIndex
	 * @param x
	 * @param y
	 * @param isNew
	 */
	public void fillPhase(Phase phase, int[] ID, String description, String name, LocalDate endDate, int confIndex,
			int milestoneIndex, int x, int y, boolean isNew) {

		phase.setDescription(formControl.fillTextMapper(description));
		phase.setName(formControl.fillTextMapper(name));
		phase.setEndDate(control.convertDate(endDate));
		Coordinates coord = objF.createCoordinates();
		coord.setXCoordinate(x);
		coord.setYCoordinate(y);
		phase.setCoordinates(coord);
		formControl.phaseControl(phase, milestoneIndex, confIndex);

		if (isNew) {
			BasicForm rForm = control.getForms().get(ID[3]);
			rForm.getPhaseArray().remove(ID[1]);
			rForm.getPhaseArray().add(ID[1], phase);
		}

	}

	/**
	 * Vytvoří nový formulář pro Phase s identifikacemi
	 * 
	 * @param item
	 *            položka plátna
	 * @param form
	 *            kořenový formulář
	 * @param IDs
	 *            instance identifikaci o prvku
	 * @return identifikátory
	 */

	public int[] createPhase(CanvasItem item, BasicForm form, int[] IDs) {

		int index = IdentificatorCreater.getIndex();

		Phase phase = (Phase) objF.createPhase();
		Coordinates coord = objF.createCoordinates();

		forms.add(index, new PhaseForm(item, control, Constans.phaseDragTextIndexs, phase, index, deleteControl));
		IDs[0] = index;
		IDs[1] = idCreater.createPhaseID();

		IDs[3] = form.getCanvasItem().getIDs()[0];
		form.getPhaseArray().add(IDs[1], null);
		index++;
		IdentificatorCreater.setIndex(index);
		return IDs;
	}

	/**
	 * Vyplní data z formuláře Activity do datových struktur pro Activity
	 * 
	 * @param activity
	 *            instance activity
	 * @param ID
	 *            identifikátory dané activity
	 * @param description
	 * @param name
	 * @param x
	 * @param y
	 * @param isNew
	 */
	public void fillActivity(Activity activity, int[] ID, String description, String name, int x, int y,
			boolean isNew) {

		activity.setDescription(formControl.fillTextMapper(description));
		activity.setName(formControl.fillTextMapper(name));
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

	/**
	 * Vytvoří nový formulář pro Activity s identifikacemi
	 * 
	 * @param item
	 *            položka plátna
	 * @param form
	 *            kořenový formulář
	 * @param IDs
	 *            instance identifikaci o prvku
	 * @return identifikátory
	 */
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

	/**
	 * Vyplní informace z formuláře Iteration do datových struktru pro Iteration
	 * 
	 * @param iteration
	 *            instance Iteration
	 * @param ID
	 *            identifikátory dané iteration
	 * @param description
	 * @param name
	 * @param startDate
	 * @param endDate
	 * @param confIndex
	 * @param x
	 * @param y
	 * @param isNew
	 */
	public void fillIteration(Iteration iteration, int[] ID, String description, String name, LocalDate startDate,
			LocalDate endDate, int confIndex, int x, int y, boolean isNew) {

		iteration.setDescription(formControl.fillTextMapper(description));
		iteration.setName(formControl.fillTextMapper(name));
		iteration.setStartDate(control.convertDate(startDate));
		iteration.setEndDate(control.convertDate(endDate));
		formControl.iterationControl(iteration, confIndex);
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

	/**
	 * Vytvoří nový formulář pro Iteration s identifikacemi
	 * 
	 * @param item
	 *            položka plátna
	 * @param form
	 *            kořenový formulář
	 * @param IDs
	 *            instance identifikaci o prvku
	 * @return identifikátory
	 */
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

	/**
	 * Vyplní data z formuláře Work Unit do datových struktur pro WorkUnit
	 * 
	 * @param workUnit
	 *            instatnce Work Unit
	 * @param ID
	 *            identifikátory daného Work Unitu
	 * @param description
	 * @param name
	 * @param authorIndex
	 * @param assigneIndex
	 * @param category
	 * @param x
	 * @param y
	 * @param priorityIndex
	 * @param severityIndex
	 * @param typeIndex
	 * @param resolutionIndex
	 * @param statusIndex
	 * @param estimate
	 * @param isNew
	 * @param isExist
	 */
	public void fillWorkUnit(WorkUnit workUnit, int[] ID, String description, String name, int authorIndex,
			int assigneIndex, String category, int x, int y, int priorityIndex, int severityIndex, int typeIndex,
			int resolutionIndex, int statusIndex, double estimated, boolean isNew, boolean isExist) {

		workUnit.setDescription(formControl.fillTextMapper(description));
		workUnit.setName(formControl.fillTextMapper(name));
		if (estimated == -1.0) {
			workUnit.setEstimatedTime(null);
		} else {
			workUnit.setEstimatedTime(estimated);
		}
		formControl.workUnitControl(workUnit, priorityIndex, severityIndex, typeIndex, resolutionIndex, statusIndex,
				authorIndex, assigneIndex);

		workUnit.setExist(isExist);

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

	/**
	 * Vytvoří nový formulář pro Work Unit s identifikacemi
	 * 
	 * @param item
	 *            položka plátna
	 * @param form
	 *            kořenový formulář
	 * @param IDs
	 *            instance identifikaci o prvku
	 * @return identifikátory
	 */
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

	/**
	 * Vyplní data o Milestone z formuláře do datové struktury pro Milestone
	 * 
	 * @param id
	 *            identifikátor pro výběrové seznamy
	 * @param name
	 *            jméno milestone
	 * @param indexs
	 *            indexi s criterii
	 */
	public void fillMilestone(String id, String name, ObservableList<Integer> indexs) {

		Milestone milestone = (Milestone) objF.createMilestone();
		milestone.setName(formControl.fillTextMapper(name));
		if (indexs != null) {
			for (int i = 0; i < indexs.size(); i++) {
				milestone.getCriteriaIndexs().add(indexs.get(i) - 1);
			}

		}
		control.getLists().getMilestoneList().add(milestone);
		control.getLists().getMilestoneObservable().add(id);

	}

	/**
	 * Vyplní data o Criterion z formuláře do datových struktur pro Criterion
	 * 
	 * @param id
	 *            identifikátor pro výběrové seznamy
	 * @param name
	 * @param description
	 */
	public void fillCriterion(String id, String name, String description) {

		Criterion criterion = objF.createCriterion();
		criterion.setDescription(formControl.fillTextMapper(description));
		criterion.setName(formControl.fillTextMapper(name));

		control.getLists().getCriterionList().add(criterion);
		control.getLists().getCriterionObservable().add(id);

	}

	/**
	 * Vyplní data o Config Person Relation z formuláře do datových struktur
	 * 
	 * @param id
	 * @param name
	 * @param conf
	 * @param role
	 */
	public void fillCPR(String id, String name, int conf, int role) {

		ConfigPersonRelation cpr = objF.createConfigPersonRelation();
		cpr.setName(formControl.fillTextMapper(name));
		if (conf != 0) {
			cpr.setConfigurationIndex(conf - 1);
		}
		if (role != 0) {
			cpr.setPersonIndex(role - 1);
		}

		control.getLists().getCPRList().add(cpr);
		control.getLists().getCPRObservable().add(id);

	}

	/**
	 * Vyplní data o Configuration z formuláře do datových struktur
	 * 
	 * @param conf
	 *            instance třídy Configuration
	 * @param IDs
	 *            identifikátory dané Configuration
	 * @param isRelase
	 * @param Ldate
	 * @param name
	 * @param roleIndex
	 * @param isNew
	 * @param item
	 */
	public void fillConfiguration(Configuration conf, int[] IDs, boolean isRelase, LocalDate Ldate, String name,
			int roleIndex, boolean isNew, CanvasItem item) {

		Configuration config = conf;
		config.setIsRelease(isRelase);
		config.setCreate(control.convertDate(Ldate));
		config.setName(formControl.fillTextMapper(name));

		formControl.configControl(conf, roleIndex);

		String release = "YES";
		if (isRelase) {
			release = "YES";
		} else {
			release = "NO";
		}
		if (isNew) {
			lists.getConfigObservable().add(item.getID() + "_" + name);
			lists.getConfigList().add(IDs[1], conf);

			ConfigTable configTab = new ConfigTable(item.getID() + "_" + name, release, IDs[0]);
			control.getConfTableForm().getTableTV().getItems().add(configTab);
			control.getConfTableForm().getTableTV().sort();
			control.getConfTableForm().createConfigItem();
		} else {

			ConfigTable configTab = control.getConfTableForm().getTableTV().getItems().get(IDs[1]);
			configTab.setName(item.getID() + "_" + name);
			configTab.setRelease(release);
			control.getConfTableForm().getMainPanel().setCenter(control.getConfTableForm().getForm().getMainPanel());
		}

	}

	/**
	 * Vytvoří nový formulář pro Configuration s identifikacemi
	 * 
	 * @param item
	 *            položka plátna
	 * @param form
	 *            kořenový formulář
	 * @param IDs
	 *            instance identifikaci o prvku
	 * @return identifikátory
	 */
	public int[] createConfigruration(CanvasItem item, BasicForm form, int[] IDs) {
		int index = IdentificatorCreater.getIndex();
		Configuration conf = (Configuration) objF.createConfiguration();
		forms.add(index, new ConfigurationForm(item, control, Constans.configurationDragTextIndexs, conf, index,
				deleteControl, idCreater));
		IDs[0] = index;
		IDs[1] = idCreater.createConfigurationID();

		control.getLists().getConfigFormIndex().add(index);
		index++;
		IdentificatorCreater.setIndex(index);
		return IDs;

	}

	/**
	 * Vyplní data o Branch z formuláře do datových struktur
	 * 
	 * @param name
	 * @param id
	 *            identifikace pro výběrové seznamy
	 * @param isMain
	 */
	public void fillBranch(String name, String id, boolean isMain) {

		Branch branch = (Branch) objF.createBranch();

		branch.setIsMain(isMain);
		branch.setName(formControl.fillTextMapper(name));

		control.getLists().getBranchList().add(branch);
		control.getLists().getBranchObservable().add(id);

	}

	/**
	 * Vyplní data o Change z formuláře do datových struktur
	 * 
	 * @param change
	 *            Instance Change
	 * @param IDs
	 *            identifikátory dané Change
	 * @param description
	 * @param name
	 * @param isNew
	 * @param x
	 * @param y
	 * @param isExist
	 */
	public void fillChange(Change change, int[] IDs, String description, String name, boolean isNew, int x, int y,
			boolean isExist) {

		change.setName(formControl.fillTextMapper(name));
		change.setDescriptoin(formControl.fillTextMapper(description));

		Coordinates coord = objF.createCoordinates();
		coord.setXCoordinate(x);
		coord.setYCoordinate(y);
		change.setCoordinates(coord);
		change.setExist(isExist);

		if (isNew) {
			lists.getChangeFormIndex().add(IDs[0]);
			BasicForm rForm = control.getForms().get(IDs[3]);
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

	/**
	 * Vyplní data o Artifact z formuláře do datových struktur
	 * 
	 * @param artifact
	 *            instance artifact
	 * @param IDs
	 *            identifikátory daného Artifact
	 * @param description
	 * @param name
	 * @param Ldate
	 * @param type
	 * @param roleIndex
	 * @param x
	 * @param y
	 * @param typeIndex
	 * @param isNew
	 * @param isExist
	 */
	public void fillArtifact(Artifact artifact, int[] IDs, String description, String name, LocalDate Ldate,
			String type, int roleIndex, int x, int y, int typeIndex, boolean isNew, boolean isExist) {

		artifact.setName(formControl.fillTextMapper(name));
		artifact.setDescriptoin(formControl.fillTextMapper(description));
		artifact.setCreated(control.convertDate(Ldate));
		artifact.setMimeType(type);
		formControl.artifactControl(artifact, roleIndex);
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

	/**
	 * Vyplní data o Role z formuláře do datových struktur pro Role
	 * 
	 * @param id
	 *            identifikátor pro výběrové seznamy
	 * @param description
	 * @param name
	 * @param type
	 */
	public void fillRole(String id, String description, String name, int type) {

		Role role = objF.createRole();
		role.setName(formControl.fillTextMapper(name));
		role.setDescription(formControl.fillTextMapper(description));
		if (type != 0) {
			role.setType(type - 1);
		}

		lists.getRoleObservable().add(id);
		lists.getRoleList().add(role);
	}

	/**
	 * Vyplní data o Role-Type z formuláře do datových struktur o Role-Type
	 * 
	 * @param id
	 *            identifikátor pro výběrový seznam
	 * @param nameST
	 * @param classST
	 * @param superST
	 */
	public void fillRoleType(String id, String nameST, String classST, String superST) {

		RoleType type = objF.createRoleType();
		type.setName(formControl.fillTextMapper(nameST));
		type.setRoleClass(formControl.fillTextMapper(classST));
		type.setRoleSuperClass(formControl.fillTextMapper(superST));

		lists.getRoleTypeObservable().add(id);
		lists.getRoleTypeList().add(type);

	}

	/**
	 * Vyplní data o Tag z formuláře do datových struktur
	 * 
	 * @param conf
	 *            Instance Configuration pro přídání tagů
	 * @param tags
	 *            tagy
	 */
	public void fillTag(Configuration conf, ObservableList<TagTable> tags) {

		for (int i = 0; i < tags.size(); i++) {
			String tag = tags.get(i).getTag();
			conf.getTags().add(tag);

		}

	}

	/**
	 * Vyplní data o Priority z formuláře do datových struktur
	 * 
	 * @param id
	 *            identifikátor pro výběrový seznam
	 * @param nameST
	 * @param classST
	 * @param superST
	 */
	public void fillPriorityType(String id, String nameST, String classST, String superST) {

		Priority priority = objF.createPriority();

		priority.setName(formControl.fillTextMapper(nameST));
		priority.setPriorityClass(formControl.fillTextMapper(classST));
		priority.setPrioritySuperClass(formControl.fillTextMapper(superST));

		lists.getPriorityObservable().add(id);
		lists.getPriorityTypeList().add(priority);

	}

	/**
	 * Vyplní data o Severity z formuláře do datových struktur
	 * 
	 * @param id
	 *            identifikátor pro výběrový seznam
	 * @param nameST
	 * @param classST
	 * @param superST
	 */
	public void fillSeverityType(String id, String nameST, String classST, String superST) {
		Severity severity = objF.createSeverity();

		severity.setName(formControl.fillTextMapper(nameST));
		severity.setSeverityClass(formControl.fillTextMapper(classST));
		severity.setSeveritySuperClass(formControl.fillTextMapper(superST));

		lists.getSeverityTypeObservable().add(id);
		lists.getSeverityTypeList().add(severity);

	}

	/**
	 * Vyplní data o Relation z formuláře do datových struktur
	 * 
	 * @param id
	 *            identifikátor pro výběrový seznam
	 * @param nameST
	 * @param classST
	 * @param superST
	 */
	public void fillRelationType(String id, String nameST, String classST, String superST) {

		Relation relation = objF.createRelation();
		relation.setName(formControl.fillTextMapper(nameST));
		relation.setRelationClass(formControl.fillTextMapper(classST));
		relation.setRelationSuperClass(formControl.fillTextMapper(superST));

		lists.getRelationTypeList().add(relation);
		lists.getRelationTypeObservable().add(id);

	}

	/**
	 * Vyplní data o Resolution z formuláře do datových struktur
	 * 
	 * @param id
	 *            identifikátor pro výběrový seznam
	 * @param nameST
	 * @param classST
	 * @param superST
	 */
	public void fillResolutionType(String id, String nameST, String classST, String superST) {

		Resolution resolution = objF.createResolution();
		resolution.setName(formControl.fillTextMapper(nameST));
		resolution.setResolutionClass(formControl.fillTextMapper(classST));
		resolution.setResolutionSuperClass(formControl.fillTextMapper(superST));

		lists.getResolutionTypeList().add(resolution);
		lists.getResolutionTypeObservable().add(id);

	}

	/**
	 * Vyplní data o Status z formuláře do datových struktur
	 * 
	 * @param id
	 *            identifikátor pro výběrový seznam
	 * @param nameST
	 * @param classST
	 * @param superST
	 */
	public void fillStatusType(String id, String nameST, String classST, String superST) {

		Status status = objF.createStatus();
		status.setName(formControl.fillTextMapper(nameST));
		status.setStatusClass(formControl.fillTextMapper(classST));
		status.setStatusSuperClass(formControl.fillTextMapper(superST));

		lists.getStatusTypeList().add(status);
		lists.getStatusTypeObservable().add(id);

	}

	/**
	 * Vyplní data o Type z formuláře do datových struktur
	 * 
	 * @param id
	 *            identifikátor pro výběrový seznam
	 * @param nameST
	 * @param classST
	 * @param superST
	 */
	public void fillType(String id, String nameST, String classST, String superST) {

		Type type = objF.createType();
		type.setName(formControl.fillTextMapper(nameST));
		type.setTypeClass(formControl.fillTextMapper(classST));
		type.setTypeSuperClass(formControl.fillTextMapper(superST));

		lists.getTypeList().add(type);
		lists.getTypeObservable().add(id);

	}

}
