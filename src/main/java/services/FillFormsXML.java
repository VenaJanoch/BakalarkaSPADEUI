package services;

import java.util.ArrayList;
import java.util.List;

import Controllers.LinkControl;
import SPADEPAC.Activity;
import SPADEPAC.Artifact;
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
import abstractform.BasicForm;
import forms.ActivityForm;
import forms.ArtifactForm;
import forms.ChangeForm;
import forms.ConfigurationForm;
import forms.IterationForm;
import forms.PhaseForm;
import forms.ProjectForm;
import forms.WorkUnitForm;
import graphics.CanvasItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.IdentificatorCreater;
import tables.BranchTable;
import tables.CPRTable;
import tables.ClassTable;
import tables.ConfigTable;
import tables.CriterionTable;
import tables.MilestoneTable;
import tables.RoleTable;
import tables.TagTable;

public class FillFormsXML {

	/** Globální proměnné třídy */
	private Control control;
	private Project project;
	private ArrayList<BasicForm> forms;

	private model.IdentificatorCreater idCreater;
	private SegmentLists lists;
	private FillCopyForms copyForms;
	private Controllers.LinkControl linkControl;
	private DeleteControl deleteControl;
	private FormControl formControl;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy
	 * 
	 * @param control
	 *            instance třídy Control
	 * @param lists
	 *            instance třídy SegmentLists
	 * @param project
	 *            kořenový element
	 * @param forms
	 *            seznam formulářů
	 * @param copyForms
	 *            instance třídy CopyForm
	 * @param idCreator
	 *            instance třídy IdentificatorCreater
	 * @param linkControl
	 *            instance třídy LinkControl
	 * @param deleteControl
	 *            instance třídy DeleteControl
	 * @param formControl
	 *            instace třídy FormControl
	 */
	public FillFormsXML(Control control, SegmentLists lists, Project project, ArrayList<BasicForm> forms,
                        FillCopyForms copyForms, model.IdentificatorCreater idCreator, LinkControl linkControl,
                        DeleteControl deleteControl, FormControl formControl) {

		this.control = control;
		this.project = project;
		this.formControl = formControl;
		this.copyForms = copyForms;
		this.forms = forms;
		this.lists = lists;
		idCreater = idCreator;
		this.linkControl = linkControl;
		this.deleteControl = deleteControl;

	}

	/**
	 * Vyplní informace o projektu do formuláře. Zavolá ostatní metody metody
	 * pro načtení procesu do paměti
	 * 
	 * @param form
	 *            formulář projectu
	 */
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

		fillCPRObservabel(project.getCpr());

		fillConfigurationFromXML(form, project.getConfiguration());

	}

	/**
	 * Rozhodne o zavolání metody pro vytvoření linků
	 * 
	 * @param links
	 *            seznam spojení
	 */

	public void createLinks(List<Link> links) {

		for (int i = 0; i < links.size(); i++) {

			if (links.get(i).getType().equals(SegmentType.WorkUnit.name())) {

				fillWorkUnitLinks(links.get(i));
			} else {
				fillConfigLinks(links.get(i));
			}

		}

	}

	/**
	 * Vytvoří propojení mezi objekty Work Unit
	 * 
	 * @param link
	 *            link
	 */
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

	/**
	 * Vytvoří spojení mezi elementy Change a Artifact
	 * 
	 * @param link
	 *            link
	 */
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

	/**
	 * Vytvoří nový prvek Phase na plátně, zavolá metodu pro vytvoření a
	 * naplnění formuláře
	 * 
	 * @param form
	 *            kořenový formulář
	 */
	public void fillPhasesFromXML(BasicForm form) {

		for (int i = 0; i < project.getPhases().size(); i++) {
			Phase phase = project.getPhases().get(i);

			CanvasItem item = new CanvasItem(SegmentType.Phase, phase.getName(), control, form, 1,
					phase.getCoordinates().getXCoordinate(), phase.getCoordinates().getYCoordinate(),
					control.getContexMenu(), linkControl, control.getCanvas());

			form.getCanvas().getCanvas().getChildren().add(item);

			fillWorkUnitFromXML(control.getForms().get(item.getIDs()[0]), phase.getWorkUnits());

		}

	}

	/**
	 * Vytvoří nový formulář pro Phase s identifikacemi a naplní daný formulář
	 * daty z XML
	 * 
	 * @param item
	 *            položka plátna
	 * @param form
	 *            kořenový formulář
	 * @param IDs
	 *            instance identifikaci o prvku
	 * @return identifikátory
	 */
	public int[] createPhaseFormXML(CanvasItem item, BasicForm form, int[] IDs) {

		int index = model.IdentificatorCreater.getIndex();
		IDs[0] = index;
		IDs[1] = idCreater.createPhaseID();
		IDs[3] = form.getCanvasItem().getIDs()[0];
		Phase phase = form.getPhaseArray().get(IDs[1]);

		PhaseForm phaseForm = new PhaseForm(item, control, Constans.phaseDragTextIndexs, phase, index, deleteControl);

		copyForms.copyFormPhase(phase, phaseForm);

		index++;
		model.IdentificatorCreater.setIndex(index);
		forms.add(IDs[0], phaseForm);
		return IDs;

	}

	/**
	 * Vytvoří nový prvek Iteration na plátně, zavolá metodu pro vytvoření a
	 * naplnění formuláře
	 * 
	 * @param form
	 *            kořenový formulář
	 */
	public void fillIterationFromXML(BasicForm form) {

		for (int i = 0; i < project.getIterations().size(); i++) {
			Iteration iteration = project.getIterations().get(i);

			CanvasItem item = new CanvasItem(SegmentType.Iteration, iteration.getName(), control, forms.get(0), 1,
					iteration.getCoordinates().getXCoordinate(), iteration.getCoordinates().getYCoordinate(),
					control.getContexMenu(), linkControl, control.getCanvas());
			form.getCanvas().getCanvas().getChildren().add(item);

			fillWorkUnitFromXML(forms.get(item.getIDs()[0]), iteration.getWorkUnits());

		}

	}

	/**
	 * Vytvoří nový formulář pro Iteration s identifikacemi a naplní daný
	 * formulář daty z XML
	 * 
	 * @param item
	 *            položka plátna
	 * @param form
	 *            kořenový formulář
	 * @param IDs
	 *            instance identifikaci o prvku
	 * @return identifikátory
	 */
	public int[] createIterationFormXML(CanvasItem item, BasicForm form, int[] IDs) {
		int index = model.IdentificatorCreater.getIndex();
		IDs[0] = index;
		IDs[1] = idCreater.createIterationID();
		IDs[3] = form.getCanvasItem().getIDs()[0];
		Iteration iteration = form.getIterationArray().get(IDs[1]);
		IterationForm iterationForm = new IterationForm(item, control, Constans.iterationDragTextIndexs, iteration,
				index, deleteControl);

		copyForms.copyFormIteration(iteration, iterationForm);

		index++;
		model.IdentificatorCreater.setIndex(index);

		forms.add(IDs[0], iterationForm);
		return IDs;

	}

	/**
	 * Vytvoří nový prvek Activity na plátně, zavolá metodu pro vytvoření a
	 * naplnění formuláře
	 * 
	 * @param form
	 *            kořenový formulář
	 */
	public void fillActivityFromXML(BasicForm form) {

		for (int i = 0; i < project.getActivities().size(); i++) {
			Activity activity = project.getActivities().get(i);

			CanvasItem item = new CanvasItem(SegmentType.Activity, activity.getName(), control, forms.get(0), 1,
					activity.getCoordinates().getXCoordinate(), activity.getCoordinates().getYCoordinate(),
					control.getContexMenu(), linkControl, control.getCanvas());
			form.getCanvas().getCanvas().getChildren().add(item);

			fillWorkUnitFromXML(forms.get(item.getIDs()[0]), activity.getWorkUnits());

			item.setTranslateX(activity.getCoordinates().getXCoordinate());
			item.setTranslateY(activity.getCoordinates().getYCoordinate());

		}
	}

	/**
	 * Vytvoří nový formulář pro Activity s identifikacemi a naplní daný
	 * formulář daty z XML
	 * 
	 * @param item
	 *            položka plátna
	 * @param form
	 *            kořenový formulář
	 * @param IDs
	 *            instance identifikaci o prvku
	 * @return identifikátory
	 */
	public int[] createActivityFormXML(CanvasItem item, BasicForm form, int[] IDs) {
		int index = model.IdentificatorCreater.getIndex();
		IDs[0] = index;
		IDs[1] = idCreater.createActivityID();
		IDs[3] = form.getCanvasItem().getIDs()[0];
		Activity activity = form.getActivityArray().get(IDs[1]);
		ActivityForm activityForm = new ActivityForm(item, control, Constans.activityDragTextIndexs, activity, index,
				deleteControl);

		copyForms.copyFormActivity(activity, activityForm);

		forms.add(index, activityForm);
		index++;
		model.IdentificatorCreater.setIndex(index);
		return IDs;

	}

	/**
	 * Vytvoří nový prvek Work Unit na plátně, zavolá metodu pro vytvoření a
	 * naplnění formuláře
	 * 
	 * @param form
	 *            kořenový formulář
	 */
	public void fillWorkUnitFromXML(BasicForm form, List<Integer> units) {

		for (int i = 0; i < units.size(); i++) {
			WorkUnit unit = lists.getWorkUnitList().get(units.get(i));

			CanvasItem item = new CanvasItem(SegmentType.WorkUnit, unit.getName(), control, form, 1,
					unit.getCoordinates().getXCoordinate(), unit.getCoordinates().getYCoordinate(),
					control.getContexMenu(), linkControl, form.getCanvas());
			form.getCanvas().getCanvas().getChildren().add(item);

			item.setTranslateX(unit.getCoordinates().getXCoordinate());
			item.setTranslateY(unit.getCoordinates().getYCoordinate());
		}

	}

	/**
	 * Vytvoří nový formulář pro Work Unit s identifikacemi a naplní daný
	 * formulář daty z XML
	 * 
	 * @param item
	 *            položka plátna
	 * @param form
	 *            kořenový formulář
	 * @param IDs
	 *            instance identifikaci o prvku
	 * @return identifikátory
	 */
	public int[] createWorkUnitFormXML(CanvasItem item, BasicForm form, int[] IDs) {
		int index = model.IdentificatorCreater.getIndex();

		IDs[0] = index;
		IDs[1] = idCreater.createWorkUnitID();
		IDs[2] = form.getIdCreater().createWorkUnitID();

		IDs[3] = form.getFormID();

		int tmpIndex = form.getWorkUnitArray().get(IDs[2]);

		WorkUnit unit = lists.getWorkUnitList().get(tmpIndex);
		WorkUnitForm workUnitForm = new WorkUnitForm(item, control, unit, deleteControl);

		copyForms.copyFormWorkUnit(unit, workUnitForm, item);

		index++;
		model.IdentificatorCreater.setIndex(index);
		forms.add(IDs[0], workUnitForm);
		lists.getWorkUnitFormIndex().add(tmpIndex, IDs[0]);
		return IDs;
	}

	/**
	 * Vytvoří nový prvek Configuration, zavolá metodu pro vytvoření a naplnění
	 * formuláře
	 * 
	 * @param form
	 *            kořenový formulář
	 */
	public void fillConfigurationFromXML(BasicForm form, List<Configuration> configs) {

		ObservableList<ConfigTable> data = FXCollections.observableArrayList();

		String name;
		String release;

		for (int i = 0; i < configs.size(); i++) {

			Configuration conf = configs.get(i);
			name = formControl.fillTextFromXMLMapper(conf.getName());

			if (conf.isIsRelease()) {
				release = "YES";
			} else {
				release = "NO";
			}

			CanvasItem item = new CanvasItem(SegmentType.Configuration, name, control, form, 1, 0, 0,
					control.getContexMenu(), linkControl, form.getCanvas());
			data.add(new ConfigTable(item.getID() + "_" + name, release, item.getIDs()[0]));

			lists.getConfigObservable().add(item.getID() + "_" + name);
			control.getConfTableForm().getTableTV().setItems(data);
		}

	}

	/**
	 * Vytvoří nový formulář pro Configuration s identifikacemi a naplní daný
	 * formulář daty z XML
	 * 
	 * @param item
	 *            položka plátna
	 * @param form
	 *            kořenový formulář
	 * @param IDs
	 *            instance identifikaci o prvku
	 * @return identifikátory
	 */
	public int[] createConfigurationFormXML(CanvasItem item, BasicForm form, int[] IDs) {
		int index = model.IdentificatorCreater.getIndex();
		IDs[0] = index;
		IDs[1] = idCreater.createConfigurationID();
		IDs[3] = form.getCanvasItem().getIDs()[0];
		Configuration conf = lists.getConfigList().get(IDs[1]);
		ConfigurationForm configForm = new ConfigurationForm(item, control, Constans.configurationDragTextIndexs, conf,
				index, deleteControl, idCreater);

		configForm.getNameTF().setText(conf.getName());
		configForm.setName(conf.getName());
		configForm.getCreatedDP().setValue(control.convertDateFromXML(conf.getCreate()));
		if (conf.getAuthorIndex() != null) {
			configForm.getAuthorRoleCB().setValue(lists.getRoleObservable().get(conf.getAuthorIndex() + 1));
			String author = control.getLists().getRoleList().get(conf.getAuthorIndex()).getName();
			configForm.getAuthorRoleCB().setValue(author);
		}

		configForm.setNew(false);

		if (conf.isIsRelease()) {
			configForm.getRbYes().setSelected(true);
		} else {
			configForm.getRbYes().setSelected(true);
		}

		lists.getConfigFormIndex().add(IDs[1], index);
		forms.add(index, configForm);

		index++;
		model.IdentificatorCreater.setIndex(index);

		fillChangeFromXML(configForm, conf.getChangesIndexs(), IDs[0]);
		fillTagsFromXML(configForm, conf.getTags());
		fillArtifactFromXML(configForm, conf.getArtifactsIndexs(), IDs[0]);
		return IDs;

	}

	/**
	 * Vytvoří nové prvky Tag do příslušné tabulky
	 * 
	 * @param form
	 *            kořenový formulář
	 * @param tags
	 *            tagy
	 */
	public void fillTagsFromXML(ConfigurationForm formc, List<String> tags) {

		ObservableList<TagTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < tags.size(); i++) {
			String id = idCreater.createTagID() + "_" + formControl.fillTextFromXMLMapper(tags.get(i));
			data.add(new TagTable(id));
		}

		formc.getTagForm().getTableTV().setItems(data);

	}

	/**
	 * Vytvoří nové prvky Role a vloží je do tabulky
	 * 
	 * @param roles
	 *            seznam rolí pro přidání
	 */
	public void fillRoleFromXML(List<Role> roles) {
		ObservableList<RoleTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < roles.size(); i++) {

			Role role = roles.get(i);
			String name = idCreater.createRoleID() + "_" + formControl.fillTextFromXMLMapper(role.getName());
			int roleI = 0;
			if (role.getType() != null) {
				roleI = role.getType();
			}
			data.add(new RoleTable(name, role.getDescription(), lists.getRoleTypeObservable().get(roleI)));

			control.getLists().getRoleObservable().add(name);
		}

		control.getRoleForm().getTableTV().setItems(data);

	}

	/**
	 * Vytvoří a vyplní nové výčtové typy Role Type a vloží je do tabulky
	 * 
	 * @param item
	 *            seznam Role type pro přidání
	 */
	public void fillRoleTypeFromXML(List<RoleType> roles) {
		ObservableList<ClassTable> data = FXCollections.observableArrayList();
		for (int i = 0; i < roles.size(); i++) {

			RoleType role = roles.get(i);
			String id = idCreater.createRoleTypeID() + "_" + formControl.fillTextFromXMLMapper(role.getName());
			String classI = "";
			if (role.getRoleClass() != null) {
				classI = role.getRoleClass();
			}

			data.add(new ClassTable(id, classI, role.getRoleSuperClass()));
			lists.getRoleTypeObservable().add(id);
		}

		control.getRoleForm().getRoleTForm().getTableTV().setItems(data);
	}

	/**
	 * Vytvoří a vyplní nové prvky Branch a vloží je do tabulky
	 * 
	 * @param item
	 *            seznam branch pro přidání
	 */
	public void fillBranchFromXML(List<Branch> item) {

		ObservableList<BranchTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < item.size(); i++) {
			String name = item.get(i).getName();
			String id = idCreater.createBranchID() + "_" + formControl.fillTextFromXMLMapper(name);
			boolean main = item.get(i).isIsMain();

			if (main) {
				data.add(new BranchTable(id, "TRUE"));

			} else {
				data.add(new BranchTable(id, "FALSE"));
			}

			lists.getBranchObservable().add(id);
		}

		control.getBranchFrom().getTableTV().setItems(data);
	}

	/**
	 * Vytvoří nový prvek Change na plátně, zavolá metodu pro vytvoření a
	 * naplnění formuláře
	 * 
	 * @param form
	 *            kořenový formulář
	 */
	public void fillChangeFromXML(BasicForm form, List<Integer> changes, int formId) {

		for (int i = 0; i < changes.size(); i++) {
			Change change = lists.getChangeList().get(changes.get(i));

			CanvasItem item = new CanvasItem(SegmentType.Change, change.getName(), control, form, 1,
					change.getCoordinates().getXCoordinate(), change.getCoordinates().getYCoordinate(),
					control.getContexMenu(), linkControl, form.getCanvas());

			item.getIDs()[3] = formId;

			form.getCanvas().getCanvas().getChildren().add(item);

			item.setTranslateX(change.getCoordinates().getXCoordinate());
			item.setTranslateY(change.getCoordinates().getYCoordinate());

		}

	}

	/**
	 * Vytvoří nový formulář pro Change s identifikacemi a naplní daný formulář
	 * daty z XML
	 * 
	 * @param item
	 *            položka plátna
	 * @param form
	 *            kořenový formulář
	 * @param IDs
	 *            instance identifikaci o prvku
	 * @return identifikátory
	 */
	public int[] createChangeFormXML(CanvasItem item, BasicForm form, int[] IDs) {
		int index = model.IdentificatorCreater.getIndex();
		IDs[0] = index;
		IDs[1] = idCreater.createChangeID();
		IDs[2] = form.getIdCreater().createChangeID();

		int index1 = form.getChangeArray().get(IDs[2]);
		Change change = lists.getChangeList().get(index1);
		Configuration conf = form.getConfigArray();
		ChangeForm changeForm = new ChangeForm(item, control, change, conf, deleteControl);

		copyForms.copyFormChange(change, changeForm);

		forms.add(index, changeForm);
		lists.getChangeFormIndex().add(index1, index);
		index++;
		model.IdentificatorCreater.setIndex(index);
		return IDs;

	}

	/**
	 * Vytvoří nový prvek Artifact na plátně, zavolá metodu pro vytvoření a
	 * naplnění formuláře
	 * 
	 * @param form
	 *            kořenový formulář
	 */
	public void fillArtifactFromXML(BasicForm form, List<Integer> artifacts, int formId) {

		for (int i = 0; i < artifacts.size(); i++) {
			Artifact artifact = control.getLists().getArtifactList().get(artifacts.get(i));

			CanvasItem item = new CanvasItem(SegmentType.Artifact, artifact.getName(), control, form, 1,
					artifact.getCoordinates().getXCoordinate(), artifact.getCoordinates().getYCoordinate(),
					control.getContexMenu(), linkControl, form.getCanvas());
			item.getIDs()[3] = formId;
			if ((form.getCanvasItem().getType() == SegmentType.Configuration)) {
				form.getCanvas().getCanvas().getChildren().add(item);
			}

			item.setTranslateX(artifact.getCoordinates().getXCoordinate());
			item.setTranslateY(artifact.getCoordinates().getYCoordinate());

		}

	}

	/**
	 * Vytvoří nový formulář pro Artifact s identifikacemi a naplní daný
	 * formulář daty z XML
	 * 
	 * @param item
	 *            položka plátna
	 * @param form
	 *            kořenový formulář
	 * @param IDs
	 *            instance identifikaci o prvku
	 * @return identifikátory
	 */
	public int[] createArtifactFormXML(CanvasItem item, BasicForm form, int[] IDs) {
		int index = model.IdentificatorCreater.getIndex();
		IDs[0] = index;
		IDs[1] = idCreater.createArtifactID();
		IDs[2] = form.getIdCreater().createArtifactID();

		int index1 = form.getArtifactArray().get(IDs[2]);
		Artifact artifact = control.getLists().getArtifactList().get(index1);
		ArtifactForm artifactForm = new ArtifactForm(item, control, artifact, deleteControl, form.getConfigArray());

		copyForms.copyFormArtifact(artifact, artifactForm);

		forms.add(index, artifactForm);
		control.getLists().getArtifactFormIndex().add(index1, index);
		index++;
		model.IdentificatorCreater.setIndex(index);
		return IDs;

	}

	/**
	 * Vytvoří a vyplní nové prvky CPR a vloží je do tabulky
	 * 
	 * @param cprs
	 *            seznam CPR pro přidání
	 */
	private void fillCPRObservabel(List<ConfigPersonRelation> cprs) {

		ObservableList<CPRTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < cprs.size(); i++) {
			String name = cprs.get(i).getName();
			String id = idCreater.createCPRID() + "_" + formControl.fillTextFromXMLMapper(name);
			control.getLists().getCPRObservable().add(id);

			String classI = "";
			if (cprs.get(i).getPersonIndex() != null) {
				classI = lists.getRoleObservable().get(cprs.get(i).getPersonIndex() + 1);
			}

			data.add(new CPRTable(id, classI));
		}

		control.getCPRForm().getTableTV().setItems(data);
	}

	/**
	 * Vytvoří a vyplní nové výčtové typy Priority a vloží je do tabulky
	 * 
	 * @param item
	 *            seznam priorit pro přidání
	 */
	private void fillPrioritybservabel(List<Priority> item) {

		ObservableList<ClassTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < item.size(); i++) {

			String name = item.get(i).getName();
			String id = idCreater.createPriorityID() + "_" + formControl.fillTextFromXMLMapper(name);
			String superi = item.get(i).getPrioritySuperClass();

			String classi = "";
			if (item.get(i).getPriorityClass() != null) {
				classi = item.get(i).getPriorityClass();
			}

			control.getLists().getPriorityObservable().add(id);
			data.add(new ClassTable(id, classi, superi));
		}

		control.getPriorityForm().getTableTV().setItems(data);
	}

	/**
	 * Vytvoří a vyplní nové výčtové typy Work Unit Type a vloží je do tabulky
	 * 
	 * @param item
	 *            seznam Type pro přidání
	 */
	private void fillTypeObservabel(List<Type> item) {

		ObservableList<ClassTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < item.size(); i++) {
			String name = item.get(i).getName();
			String id = idCreater.createTypeID() + "_" + formControl.fillTextFromXMLMapper(name);

			String classi = "";
			if (item.get(i).getTypeClass() != null) {
				classi = item.get(i).getTypeClass();
			}
			String superi = item.get(i).getTypeSuperClass();
			control.getLists().getTypeObservable().add(id);
			data.add(new ClassTable(id, classi, superi));
		}

		control.getTypeForm().getTableTV().setItems(data);
	}

	/**
	 * Vytvoří a vyplní nové výčtové typy Severity a vloží je do tabulky
	 * 
	 * @param item
	 *            seznam Severit pro přidání
	 */
	private void fillSeveritybservabel(List<Severity> item) {

		ObservableList<ClassTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < item.size(); i++) {
			String name = item.get(i).getName();
			String id = idCreater.createSeverityID() + "_" + formControl.fillTextFromXMLMapper(name);
			lists.getSeverityTypeObservable().add(id);
			String classi = "";
			if (item.get(i).getSeverityClass() != null) {
				classi = item.get(i).getSeverityClass();
			}
			String superi = item.get(i).getSeveritySuperClass();

			data.add(new ClassTable(id, classi, superi));
		}

		control.getSeverityForm().getTableTV().setItems(data);
	}

	/**
	 * Vytvoří a vyplnínové výčtové typy Relation a vloží je do tabulky
	 * 
	 * @param item
	 *            seznam Relation pro přidání
	 */
	private void fillRelationbservabel(List<Relation> item) {

		ObservableList<ClassTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < item.size(); i++) {
			String name = item.get(i).getName();
			String id = idCreater.createRelationID() + "_" + formControl.fillTextFromXMLMapper(name);
			lists.getRelationTypeObservable().add(id);

			String classi = "";
			if (item.get(i).getRelationClass() != null) {
				classi = item.get(i).getRelationClass();
			}
			String superi = item.get(i).getRelationSuperClass();

			data.add(new ClassTable(id, classi, superi));
		}

		control.getRelationForm().getTableTV().setItems(data);
	}

	/**
	 * Vytvoří a vyplní nové výčtové typy Resolution a vloží je do tabulky
	 * 
	 * @param item
	 *            seznam Resolution pro přidání
	 */
	private void fillResolutionbservabel(List<Resolution> item) {

		ObservableList<ClassTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < item.size(); i++) {
			String name = item.get(i).getName();
			String classi = "";
			String id = idCreater.createResolutionID() + "_" + formControl.fillTextFromXMLMapper(name);
			lists.getResolutionTypeObservable().add(id);
			if (item.get(i).getResolutionClass() != null) {
				classi = item.get(i).getResolutionClass();
			}
			String superi = item.get(i).getResolutionSuperClass();

			data.add(new ClassTable(id, classi, superi));
		}

		control.getResolutionForm().getTableTV().setItems(data);
	}

	/**
	 * Vytvoří a vyplní nové výčtové typy Status a vloží je do tabulky
	 * 
	 * @param item
	 *            seznam Status pro přidání
	 */
	private void fillStatusbservabel(List<Status> item) {

		ObservableList<ClassTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < item.size(); i++) {
			String name = item.get(i).getName();
			String classi = "";
			String id = idCreater.createStatusID() + "_" + formControl.fillTextFromXMLMapper(name);
			lists.getStatusTypeObservable().add(id);

			if (item.get(i).getStatusClass() != null) {
				classi = item.get(i).getStatusClass();
			}
			String superi = item.get(i).getStatusSuperClass();

			data.add(new ClassTable(id, classi, superi));
		}

		control.getStatusForm().getTableTV().setItems(data);
	}

	/**
	 * Vytvoří a vyplní nové elementy Milestone a vloží je do tabulky
	 * 
	 * @param item
	 *            seznam Mileston pro přidání
	 */
	private void fillMilestoneObservabel(List<Milestone> milestones) {

		ObservableList<MilestoneTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < milestones.size(); i++) {
			String name = milestones.get(i).getName();
			String id = idCreater.createMilestoneID() + "_" + formControl.fillTextFromXMLMapper(name);

			control.getLists().getMilestoneObservable().add(id);

			String classi = "";
			if (milestones.get(i).getCriteriaIndexs() != null) {
				classi = createCriterionsString(milestones.get(i).getCriteriaIndexs());
			}
			data.add(new MilestoneTable(id, classi));
		}

		control.getMilestoneForm().getTableTV().setItems(data);
	}

	/**
	 * Vytvoří jednotný string se jmény criterii přídávané do Mileston
	 * 
	 * @param item
	 *            indexi Criterii pro přidání
	 */
	private String createCriterionsString(List<Integer> indexs) {

		String tmp = "[ ";
		for (int i = 0; i < indexs.size(); i++) {
			tmp += control.getLists().getCriterionObservable().get(indexs.get(i) + 1) + ", ";
		}

		tmp += " ]";

		return tmp;
	}

	/**
	 * Vytvoří a vyplní nové elementy Criterion a vloží je do tabulky
	 * 
	 * @param item
	 *            seznam priorit pro přidání
	 */
	private void fillCriterionObservabel(List<Criterion> criterions) {

		ObservableList<CriterionTable> data = FXCollections.observableArrayList();

		for (int i = 0; i < criterions.size(); i++) {
			String name = criterions.get(i).getName();
			String id = idCreater.createCriterionID() + "_" + formControl.fillTextFromXMLMapper(name);

			control.getLists().getCriterionObservable().add(id);

			data.add(new CriterionTable(id, criterions.get(i).getDescription()));
		}

		control.getMilestoneForm().getCriterionForm().getTableTV().setItems(data);
	}

	/*** Getrs and Setrs ****/

	public model.IdentificatorCreater getIdCreater() {
		return idCreater;
	}

	public void setIdCreater(IdentificatorCreater idCreater) {
		this.idCreater = idCreater;
	}

}
