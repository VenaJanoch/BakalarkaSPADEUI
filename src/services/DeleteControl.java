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

	private Control control;
	private SegmentLists lists;

	private Project project;

	public DeleteControl(Control control, SegmentLists lists, Project project) {

		this.control = control;
		this.lists = lists;
		this.project = project;
	}

	public void deletePriority(ObservableList<ClassTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getPriorityObservable().indexOf(tables.get(i).getName());

			lists.getPriorityObservable().remove(index);
			lists.getPriorityTypeList().remove(index);

		}

	}

	public void deleteTag(Configuration conf, ObservableList<TagTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getPriorityObservable().indexOf(tables.get(i));
			conf.getTags().remove(index);
		

		}

	}

	public void deleteSeverity(ObservableList<ClassTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getSeverityTypeObservable().indexOf(tables.get(i).getName());

			lists.getSeverityTypeObservable().remove(index);
			lists.getSeverityTypeList().remove(index);

		}

	}

	public void deleteResolution(ObservableList<ClassTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getResolutionTypeObservable().indexOf(tables.get(i).getName());

			lists.getResolutionTypeObservable().remove(index);
			lists.getResolutionTypeList().remove(index);

		}

	}

	public void deleteRelation(ObservableList<ClassTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getRelationTypeObservable().indexOf(tables.get(i).getName());

			lists.getRelationTypeObservable().remove(index);
			lists.getRelationTypeList().remove(index);

		}

	}

	public void deleteStatus(ObservableList<ClassTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getStatusTypeObservable().indexOf(tables.get(i).getName());

			lists.getStatusTypeObservable().remove(index);
			lists.getStatusTypeList().remove(index);

		}

	}

	public void deleteType(ObservableList<ClassTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getTypeObservable().indexOf(tables.get(i).getName());

			lists.getTypeObservable().remove(index);
			lists.getTypeList().remove(index);

		}

	}
	
	public void deleteRole(ObservableList<RoleTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getRoleObservable().indexOf(tables.get(i).getName());

			lists.getRoleObservable().remove(index);
			lists.getRoleList().remove(index);

		}

	}

	public void deleteRoleType(ObservableList<ClassTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getRoleTypeObservable().indexOf(tables.get(i).getName());

			lists.getRoleTypeObservable().remove(index);
			lists.getRoleTypeList().remove(index);

		}

	}

	public void deleteCriterion(ObservableList<CriterionTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getCriterionObservable().indexOf(tables.get(i).getName());

			lists.getCriterionObservable().remove(index);
			lists.getCriterionList().remove(index);

		}

	}

	public void deleteMilestone(ObservableList<MilestoneTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getMilestoneObservable().indexOf(tables.get(i).getName());

			lists.getMilestoneObservable().remove(index);
			lists.getMilestoneList().remove(index);

		}

	}

	public void deleteBranch(ObservableList<BranchTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getBranchObservable().indexOf(tables.get(i).getName());

			lists.getBranchObservable().remove(index);
			lists.getBranchList().remove(index);

		}

	}

	
	public void deleteConfig(ObservableList<ConfigTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getConfigObservable().indexOf(tables.get(i).getName());

			lists.getConfigList().remove(index);
			lists.getConfigList().add(index, null);
			lists.getConfigObservable().remove(index);

		}

	}
	public void deleteCPR(ObservableList<CPRTable> tables) {
		for (int i = 0; i < tables.size(); i++) {

			int index = lists.getCPRObservable().indexOf(tables.get(i).getName());

			lists.getCPRObservable().remove(index);
			lists.getCPRList().remove(index);

		}
	}

	public void deletePhase(int[] IDs) {

		project.getPhases().remove(IDs[1]);
		project.getPhases().add(IDs[1], null);

	}

	public void deleteActivity(int[] iDs) {
		project.getActivities().remove(iDs[1]);
		project.getActivities().add(iDs[1], null);
	}

	public void deleteArtifact(Configuration conf , int[] iDs) {
		
		conf.getChangesIndexs().remove(iDs[2]);
		conf.getChangesIndexs().add(iDs[2], null);
		
		project.getArtifacts().remove(iDs[1]);
		project.getArtifacts().add(iDs[1], null);

	}

	public void deleteChange(Configuration conf , int[] iDs) {
		
		conf.getChangesIndexs().remove(iDs[2]);
		conf.getChangesIndexs().add(iDs[2], null);
		
		lists.getChangeList().remove(iDs[1]);
		lists.getChangeList().add(iDs[1], null);
	}

	public void deleteIteration(int[] iDs) {
		project.getIterations().remove(iDs[1]);
		project.getIterations().add(iDs[1], null);

	}

	public void deleteWorkUnit(int[] iDs) {
		lists.getWorkUnitList().remove(iDs[1]);
		lists.getWorkUnitList().add(iDs[1], null);
	}

}
