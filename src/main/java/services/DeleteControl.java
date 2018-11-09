package services;

import java.util.ArrayList;
import java.util.List;

import SPADEPAC.*;
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
	 */
	public DeleteControl() {

		this.control = control;
		this.lists = lists;
		this.project = project;
	}

	/**
	 * Vymazání informací o výčtovém typu Priority a smazání ze seznamů
	 * @param tables
	 */
	public ArrayList deletePriority(ObservableList<ClassTable> tables) {
		ArrayList indexForDelete = new ArrayList();

		for (ClassTable table : tables) {

			int index = lists.getPriorityTypeObservable().indexOf(table.getName());
			indexForDelete.add(index);
		}
		return indexForDelete;

		}
	/**
	 * Vymazání informací o elementu Tag a smazání ze seznamů
	 * @param tables
	 */
	public ArrayList deleteTag(ObservableList<TagTable> tables) {
		ArrayList indexForDelete = new ArrayList();

		for (TagTable table : tables) {

			int index = lists.getPriorityTypeObservable().indexOf(table.getTag());
			indexForDelete.add(index);
		}
		return indexForDelete;
	}

	/**
	 * Vymazání informací o výčtovém typu Severity a smazání ze seznamů
	 * @param tables
	 */
	public ArrayList deleteSeverity(ObservableList<ClassTable> tables) {
		ArrayList indexForDelete = new ArrayList();

		for (ClassTable table : tables) {

			int index = lists.getSeverityTypeObservable().indexOf(table.getName());
			indexForDelete.add(index);
		}
		return indexForDelete;
}

	/**
	 * Vymazání informací o výčtovém typu Resolution a smazání ze seznamů
	 * @param tables
	 */
	public ArrayList deleteResolution(ObservableList<ClassTable> tables) {
		ArrayList indexForDelete = new ArrayList();

		for (ClassTable table : tables) {

			int index = lists.getResolutionTypeObservable().indexOf(table.getName());
			indexForDelete.add(index);
		}
		return indexForDelete;
	}

	/**
	 * Vymazání informací o výčtovém typu Relation a smazání ze seznamů
	 * @param tables
	 */
	public ArrayList deleteRelation(ObservableList<ClassTable> tables) {
		ArrayList indexForDelete = new ArrayList();

		for (ClassTable table : tables) {

			int index = lists.getRelationTypeObservable().indexOf(table.getName());
			indexForDelete.add(index);
		}
		return indexForDelete;

	}

	/**
	 * Vymazání informací o výčtovém typu Status a smazání ze seznamů
	 * @param tables
	 */
	public ArrayList deleteStatus(ObservableList<ClassTable> tables) {
		ArrayList indexForDelete = new ArrayList();

		for (ClassTable table : tables) {

			int index = lists.getStatusTypeObservable().indexOf(table.getName());
			indexForDelete.add(index);
		}
		return indexForDelete;

	}

	/**
	 * Vymazání informací o výčtovém typu Type a smazání ze seznamů
	 * @param tables
	 */
	public ArrayList deleteType(ObservableList<ClassTable> tables) {
		ArrayList indexForDelete = new ArrayList();

		for (ClassTable table : tables) {

			int index = lists.getTypeObservable().indexOf(table.getName());
			indexForDelete.add(index);
		}
		return indexForDelete;

	}
	
	/**
	 * Vymazání informací o elementu Role a smazání ze seznamů
	 * @param tables
	 */
	public ArrayList deleteRole(ObservableList<RoleTable> tables) {
		ArrayList indexForDelete = new ArrayList();

		for (RoleTable table : tables) {

			int index = lists.getRelationTypeObservable().indexOf(table.getName());
			indexForDelete.add(index);
		}
		return indexForDelete;

	}

	/**
	 * Vymazání informací o výčtovém typu Role type a smazání ze seznamů
	 * @param tables
	 */
	public ArrayList deleteRoleType(ObservableList<ClassTable> tables) {
		ArrayList indexForDelete = new ArrayList();

		for (ClassTable table : tables) {

			int index = lists.getRelationTypeObservable().indexOf(table.getName());
			indexForDelete.add(index);
		}
		return indexForDelete;

	}

	/**
	 * Vymazání informací o elementu Criterion a smazání ze seznamů
	 * @param tables
	 */
	public ArrayList deleteCriterion(ObservableList<CriterionTable> tables) {
		ArrayList indexForDelete = new ArrayList();

		for (CriterionTable table : tables) {

			int index = lists.getBranchObservable().indexOf(table.getName());
			indexForDelete.add(index);
		}
		return indexForDelete;
	}

	/**
	 * Vymazání informací o elementu Milestone a smazání ze seznamů
	 * @param tables
	 */
	public ArrayList deleteMilestone(ObservableList<MilestoneTable> tables) {
		ArrayList indexForDelete = new ArrayList();

		for (MilestoneTable table : tables) {

			int index = lists.getMilestoneObservable().indexOf(table.getName());
			indexForDelete.add(index);
		}
		return indexForDelete;
	}
	/**
	 * Vymazání informací o elementu Branch a smazání ze seznamů
	 * @param tables
	 */
	public ArrayList<Integer> deleteBranch(ObservableList<BranchTable> tables) {
		ArrayList indexForDelete = new ArrayList();

		for (BranchTable table : tables) {

			int index = lists.getBranchObservable().indexOf(table.getName());
			indexForDelete.add(index);
			}
			return indexForDelete;
	}

	/**
	 * Vymazání informací o elementu Configuration a smazání ze seznamů
	 * @param tables
	 */
	public ArrayList deleteConfig(ObservableList<ConfigTable> tables) {
		ArrayList indexForDelete = new ArrayList();

		for (ConfigTable table : tables) {

			int index = lists.getBranchObservable().indexOf(table.getName());
			indexForDelete.add(index);
		}
		return indexForDelete;
	}
	
	/**
	 * Vymazání informací o elementu CPR a smazání ze seznamů
	 * @param tables
	 */
	public ArrayList deleteCPR(ObservableList<CPRTable> tables) {
		ArrayList indexForDelete = new ArrayList();

		for (CPRTable table : tables) {

			int index = lists.getBranchObservable().indexOf(table.getName());
			indexForDelete.add(index);
		}
		return indexForDelete;
	}
}
