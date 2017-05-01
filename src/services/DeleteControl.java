package services;

import java.util.List;

import SPADEPAC.ConfigPersonRelation;
import SPADEPAC.Configuration;
import SPADEPAC.Project;
import javafx.collections.ObservableList;
import tables.BranchTable;
import tables.CPRTable;
import tables.ClassTable;
import tables.ConfigTable;
import tables.CriterionTable;
import tables.MilestoneTable;
import tables.RoleTable;
import tables.TagTable;

public class DeleteControl {

	/** Globální proměnné třídy **/
	
	private Control control;
	private SegmentLists lists;

	private Project project;

	/**
	 * Konstruktor třídy 
	 * Zinicializuje globální proměnné třídy
	 * @param control instance třídy Control
	 * @param lists instace třídy s listy
	 * @param project kořenový element
	 */
	public DeleteControl(Control control, SegmentLists lists, Project project) {

		this.control = control;
		this.lists = lists;
		this.project = project;
	}

	/**
	 * Vymazání informací o výčtovém typu Priority a smazání ze seznamů
	 * @param tables
	 */
	public void deletePriority(ObservableList<ClassTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getPriorityObservable().indexOf(tables.get(i).getName());

			lists.getPriorityObservable().remove(index);
			lists.getPriorityTypeList().remove(index);

		}

	}

	/**
	 * Vymazání informací o elementu Tag a smazání ze seznamů
	 * @param tables
	 */
	public void deleteTag(Configuration conf, ObservableList<TagTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = conf.getTags().indexOf(tables.get(i).getTag());
			conf.getTags().remove(index);
		

		}

	}

	/**
	 * Vymazání informací o výčtovém typu Severity a smazání ze seznamů
	 * @param tables
	 */
	public void deleteSeverity(ObservableList<ClassTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getSeverityTypeObservable().indexOf(tables.get(i).getName());

			lists.getSeverityTypeObservable().remove(index);
			lists.getSeverityTypeList().remove(index);

		}

	}

	/**
	 * Vymazání informací o výčtovém typu Resolution a smazání ze seznamů
	 * @param tables
	 */
	public void deleteResolution(ObservableList<ClassTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getResolutionTypeObservable().indexOf(tables.get(i).getName());

			lists.getResolutionTypeObservable().remove(index);
			lists.getResolutionTypeList().remove(index);

		}

	}

	/**
	 * Vymazání informací o výčtovém typu Relation a smazání ze seznamů
	 * @param tables
	 */
	public void deleteRelation(ObservableList<ClassTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getRelationTypeObservable().indexOf(tables.get(i).getName());

			lists.getRelationTypeObservable().remove(index);
			lists.getRelationTypeList().remove(index);

		}

	}

	/**
	 * Vymazání informací o výčtovém typu Status a smazání ze seznamů
	 * @param tables
	 */
	public void deleteStatus(ObservableList<ClassTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getStatusTypeObservable().indexOf(tables.get(i).getName());

			lists.getStatusTypeObservable().remove(index);
			lists.getStatusTypeList().remove(index);

		}

	}

	/**
	 * Vymazání informací o výčtovém typu Type a smazání ze seznamů
	 * @param tables
	 */
	public void deleteType(ObservableList<ClassTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getTypeObservable().indexOf(tables.get(i).getName());

			lists.getTypeObservable().remove(index);
			lists.getTypeList().remove(index);

		}

	}
	
	/**
	 * Vymazání informací o elementu Role a smazání ze seznamů
	 * @param tables
	 */
	public void deleteRole(ObservableList<RoleTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getRoleObservable().indexOf(tables.get(i).getName());

			lists.getRoleObservable().remove(index);
			lists.getRoleList().remove(index);

		}

	}

	/**
	 * Vymazání informací o výčtovém typu Role type a smazání ze seznamů
	 * @param tables
	 */
	public void deleteRoleType(ObservableList<ClassTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getRoleTypeObservable().indexOf(tables.get(i).getName());

			lists.getRoleTypeObservable().remove(index);
			lists.getRoleTypeList().remove(index);

		}

	}

	/**
	 * Vymazání informací o elementu Criterion a smazání ze seznamů
	 * @param tables
	 */
	public void deleteCriterion(ObservableList<CriterionTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getCriterionObservable().indexOf(tables.get(i).getName());

			lists.getCriterionObservable().remove(index);
			lists.getCriterionList().remove(index);

		}

	}

	/**
	 * Vymazání informací o elementu Milestone a smazání ze seznamů
	 * @param tables
	 */
	public void deleteMilestone(ObservableList<MilestoneTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getMilestoneObservable().indexOf(tables.get(i).getName());

			lists.getMilestoneObservable().remove(index);
			lists.getMilestoneList().remove(index);

		}

	}
	/**
	 * Vymazání informací o elementu Branch a smazání ze seznamů
	 * @param tables
	 */
	public void deleteBranch(ObservableList<BranchTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getBranchObservable().indexOf(tables.get(i).getName());

			lists.getBranchObservable().remove(index);
			lists.getBranchList().remove(index);

		}

	}

	/**
	 * Vymazání informací o elementu Configuration a smazání ze seznamů
	 * @param tables
	 */
	public void deleteConfig(ObservableList<ConfigTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getConfigObservable().indexOf(tables.get(i).getName());

			lists.getConfigList().remove(index);
			lists.getConfigObservable().remove(index);

		}

	}
	
	/**
	 * Vymazání informací o elementu CPR a smazání ze seznamů
	 * @param tables
	 */
	public void deleteCPR(ObservableList<CPRTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getCPRObservable().indexOf(tables.get(i).getName());

			lists.getCPRObservable().remove(index);
			lists.getCPRList().remove(index);

		}
	}

	/**
	 * Vymazání informací o segmentu Phase ze seznamu
	 * @param tables
	 */
	public void deletePhase(int[] IDs) {

		project.getPhases().remove(IDs[1]);
		project.getPhases().add(IDs[1], null);

	}

	/**
	 * Vymazání informací o segmentu Activity ze seznamu
	 * @param tables
	 */
	public void deleteActivity(int[] iDs) {
		project.getActivities().remove(iDs[1]);
		project.getActivities().add(iDs[1], null);
	}

	/**
	 * Vymazání informací o segmentu Artifact ze seznamu
	 * @param tables
	 */
	public void deleteArtifact(Configuration conf , int[] iDs) {
		
		conf.getChangesIndexs().remove(iDs[2]);
		conf.getChangesIndexs().add(iDs[2], null);
		
		project.getArtifacts().remove(iDs[1]);
		project.getArtifacts().add(iDs[1], null);

	}

	/**
	 * Vymazání informací o elementu Change ze seznamu
	 * @param tables
	 */
	public void deleteChange(Configuration conf , int[] iDs) {
		
		conf.getChangesIndexs().remove(iDs[2]);
		conf.getChangesIndexs().add(iDs[2], null);
		
		lists.getChangeList().remove(iDs[1]);
		lists.getChangeList().add(iDs[1], null);
	}

	/**
	 * Vymazání informací o segmentu Iteration ze seznamu
	 * @param tables
	 */
	public void deleteIteration(int[] iDs) {
		project.getIterations().remove(iDs[1]);
		project.getIterations().add(iDs[1], null);

	}

	/**
	 * Vymazání informací elementu workUnit  ze seznamu
	 * @param tables
	 */
	public void deleteWorkUnit(int[] iDs) {
		lists.getWorkUnitList().remove(iDs[1]);
		lists.getWorkUnitList().add(iDs[1], null);
	}

}
