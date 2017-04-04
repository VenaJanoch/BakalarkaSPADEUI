package services;

import java.util.ArrayList;
import java.util.List;

import SPADEPAC.Artifact;
import SPADEPAC.Branch;
import SPADEPAC.Change;
import SPADEPAC.ConfigPersonRelation;
import SPADEPAC.Configuration;
import SPADEPAC.Criterion;
import SPADEPAC.Milestone;
import SPADEPAC.Priority;
import SPADEPAC.Project;
import SPADEPAC.Relation;
import SPADEPAC.Resolution;
import SPADEPAC.Role;
import SPADEPAC.RoleType;
import SPADEPAC.Severity;
import SPADEPAC.Status;
import forms.ConfigPersonRelationForm;
import forms.MilestoneForm;
import forms.RoleForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SegmentLists {

	private ObservableList<String> configObservable;
	private List<Configuration> configList;
	private ArrayList<Integer> configFormIndex;

	private ObservableList<String> branchObservable;
	private List<Branch> branchList;
	private ArrayList<Integer> branchFormIndex;

	private ObservableList<String> roleObservable;
	private List<Role> roleList;
	private ArrayList<Integer> roleFormIndex;

	private ObservableList<String> changeObservable;
	private List<Change> changeList;
	private ArrayList<Integer> changeFormIndex;

	private ObservableList<String> criterionObservable;
	private List<Criterion> criterionList;
	private ArrayList<Integer> criterionFormIndex;

	private ObservableList<String> milestoneObservable;
	private List<Milestone> milestoneList;
	private ArrayList<Integer> milestoneFormIndex;

	private ObservableList<String> artifactObservable;
	private List<Artifact> artifactList;
	private ArrayList<Integer> artifactFormIndex;

	private ObservableList<String> CPRObservable;
	private List<ConfigPersonRelation> CPRList;

	private ObservableList<String> roleTypeObservable;
	private List<RoleType> roleTypeList;

	private ObservableList<String> priorityTypeObservable;
	private List<Priority> priorityTypeList;

	private ObservableList<String> severityTypeObservable;
	private List<Severity> severityTypeList;

	private ObservableList<String> relationTypeObservable;
	private List<Relation> relationTypeList;

	private ObservableList<String> resolutionTypeObservable;
	private List<Resolution> resolutionTypeList;

	private ObservableList<String> statusTypeObservable;
	private List<Status> statusTypeList;

	private Control control;
	private Project project;

	public SegmentLists(Control control, Project project) {

		this.control = control;
		this.project = project;
		createLists();

	}

	public void restartLists() {

		configList.clear();
		configFormIndex.clear();
		configObservable.clear();

		roleList.clear();
		roleFormIndex.clear();
		roleObservable.clear();

		branchList.clear();
		branchFormIndex.clear();
		branchObservable.clear();
		branchObservable.add("New");

		changeList.clear();
		changeFormIndex.clear();
		changeObservable.clear();
		changeObservable.add("New");

		artifactFormIndex.clear();
		artifactList.clear();
		artifactObservable.clear();

		criterionList.clear();
		criterionObservable.clear();

		milestoneList.clear();
		milestoneObservable.clear();

		CPRList.clear();
		CPRObservable.clear();

		roleTypeList.clear();
		roleTypeObservable.clear();

		priorityTypeList.clear();
		priorityTypeObservable.clear();

		severityTypeList.clear();
		severityTypeObservable.clear();

		relationTypeList.clear();
		relationTypeObservable.clear();

		resolutionTypeList.clear();
		resolutionTypeObservable.clear();

		statusTypeList.clear();
		statusTypeObservable.clear();
	}

	public void createLists() {
		configList = new ArrayList<>();
		configFormIndex = new ArrayList<>();
		configObservable = FXCollections.observableArrayList();

		roleList = project.getRoles();
		roleFormIndex = new ArrayList<>();
		setRoleObservable(FXCollections.observableArrayList());

		branchList = project.getBranches();
		branchFormIndex = new ArrayList<>();
		branchObservable = FXCollections.observableArrayList();
		branchObservable.add("New");

		changeList = project.getChanges();
		changeFormIndex = new ArrayList<>();
		changeObservable = FXCollections.observableArrayList();
		changeObservable.add("New");

		setArtifactList(project.getArtifacts());
		setArtifactFormIndex(new ArrayList<>());
		setArtifactObservable(FXCollections.observableArrayList());

		criterionList = project.getCriterions();
		criterionFormIndex = new ArrayList<>();
		criterionObservable = FXCollections.observableArrayList();

		milestoneList = project.getMilestones();
		milestoneFormIndex = new ArrayList<>();
		milestoneObservable = FXCollections.observableArrayList();

		setCPRList(project.getCpr());
		CPRObservable = FXCollections.observableArrayList();

		setRoleTypeList(project.getRoleType());
		setRoleTypeObservable(FXCollections.observableArrayList());

		priorityTypeList = project.getPriority();
		priorityTypeObservable = FXCollections.observableArrayList();

		setSeverityTypeList(project.getSeverity());
		setSeverityTypeObservable(FXCollections.observableArrayList());

		setRelationTypeList(project.getRelation());
		relationTypeObservable = FXCollections.observableArrayList();

		resolutionTypeList = project.getResolution();
		resolutionTypeObservable = FXCollections.observableArrayList();

		statusTypeList = project.getStatus();
		statusTypeObservable = FXCollections.observableArrayList();

	}

	public ObservableList<String> getConfigObservable() {
		return configObservable;
	}

	public void setConfigObservable(ObservableList<String> configObservable) {
		this.configObservable = configObservable;
	}

	public List<Configuration> getConfigList() {
		return configList;
	}

	public void setConfigList(ArrayList<Configuration> configList) {
		this.configList = configList;
	}

	public ObservableList<String> getRoleObservable() {
		return roleObservable;
	}

	public void setRoleObservable(ObservableList<String> roleObservable) {
		this.roleObservable = roleObservable;
	}

	public ObservableList<String> getBranchObservable() {
		return branchObservable;
	}

	public void setBranchObservable(ObservableList<String> branchObservable) {
		this.branchObservable = branchObservable;
	}

	public List<Branch> getBranchList() {
		return branchList;
	}

	public void setBranchList(List<Branch> list) {
		this.branchList = list;
	}

	public ObservableList<String> getChangeObservable() {
		return changeObservable;
	}

	public void setChangeObservable(ObservableList<String> changeObservable) {
		this.changeObservable = changeObservable;
	}

	public List<Change> getChangeList() {
		return changeList;
	}

	public void setChangeList(List<Change> changeList) {
		this.changeList = changeList;
	}

	public ObservableList<String> getArtifactObservable() {
		return artifactObservable;
	}

	public void setArtifactObservable(ObservableList<String> artifactObservable) {
		this.artifactObservable = artifactObservable;
	}

	public ArrayList<Integer> getArtifactFormIndex() {
		return artifactFormIndex;
	}

	public void setArtifactFormIndex(ArrayList<Integer> artifactFormIndex) {
		this.artifactFormIndex = artifactFormIndex;
	}

	public List<Artifact> getArtifactList() {
		return artifactList;
	}

	public void setArtifactList(List<Artifact> artifactList) {
		this.artifactList = artifactList;
	}

	public ArrayList<Integer> getConfigFormIndex() {
		return configFormIndex;
	}

	public void setConfigFormIndex(ArrayList<Integer> configFormIndex) {
		this.configFormIndex = configFormIndex;
	}

	public ArrayList<Integer> getBranchFormIndex() {
		return branchFormIndex;
	}

	public void setBranchFormIndex(ArrayList<Integer> branchFormIndex) {
		this.branchFormIndex = branchFormIndex;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public ArrayList<Integer> getRoleFormIndex() {
		return roleFormIndex;
	}

	public void setRoleFormIndex(ArrayList<Integer> roleFormIndex) {
		this.roleFormIndex = roleFormIndex;
	}

	public ArrayList<Integer> getChangeFormIndex() {
		return changeFormIndex;
	}

	public void setChangeFormIndex(ArrayList<Integer> changeFormIndex) {
		this.changeFormIndex = changeFormIndex;
	}

	public ObservableList<String> getCriterionObservable() {
		return criterionObservable;
	}

	public void setCriterionObservable(ObservableList<String> criterionObservable) {
		this.criterionObservable = criterionObservable;
	}

	public List<Criterion> getCriterionList() {
		return criterionList;
	}

	public void setCriterionList(List<Criterion> criterionList) {
		this.criterionList = criterionList;
	}

	public ArrayList<Integer> getCriterionFormIndex() {
		return criterionFormIndex;
	}

	public void setCriterionFormIndex(ArrayList<Integer> criterionFormIndex) {
		this.criterionFormIndex = criterionFormIndex;
	}

	public ObservableList<String> getMilestoneObservable() {
		return milestoneObservable;
	}

	public void setMilestoneObservable(ObservableList<String> milestoneObservable) {
		this.milestoneObservable = milestoneObservable;
	}

	public List<Milestone> getMilestoneList() {
		return milestoneList;
	}

	public void setMilestoneList(List<Milestone> milestoneList) {
		this.milestoneList = milestoneList;
	}

	public ArrayList<Integer> getMilestoneFormIndex() {
		return milestoneFormIndex;
	}

	public void setMilestoneFormIndex(ArrayList<Integer> milestoneFormIndex) {
		this.milestoneFormIndex = milestoneFormIndex;
	}

	public List<ConfigPersonRelation> getCPRList() {
		return CPRList;
	}

	public void setCPRList(List<ConfigPersonRelation> cPRList) {
		CPRList = cPRList;
	}

	public ObservableList<String> getCPRObservable() {
		return CPRObservable;
	}

	public void setCPRObservable(ObservableList<String> cPRObservable) {
		CPRObservable = cPRObservable;
	}

	public ObservableList<String> getRoleTypeObservable() {
		return roleTypeObservable;
	}

	public void setRoleTypeObservable(ObservableList<String> roleTypeObservable) {
		this.roleTypeObservable = roleTypeObservable;
	}

	public List<RoleType> getRoleTypeList() {
		return roleTypeList;
	}

	public void setRoleTypeList(List<RoleType> roleTypeList) {
		this.roleTypeList = roleTypeList;
	}

	public ObservableList<String> getPriorityObservable() {
		return priorityTypeObservable;
	}

	public void setPriorityTypeObservable(ObservableList<String> priorityObservable) {
		this.priorityTypeObservable = priorityObservable;
	}

	public List<Priority> getPriorityTypeList() {
		return priorityTypeList;
	}

	public void setPriorityTypeList(List<Priority> priorityTypeList) {
		this.priorityTypeList = priorityTypeList;
	}

	public ObservableList<String> getSeverityTypeObservable() {
		return severityTypeObservable;
	}

	public void setSeverityTypeObservable(ObservableList<String> severityTypeObservable) {
		this.severityTypeObservable = severityTypeObservable;
	}

	public List<Severity> getSeverityTypeList() {
		return severityTypeList;
	}

	public void setSeverityTypeList(List<Severity> severityTypeList) {
		this.severityTypeList = severityTypeList;
	}

	public ObservableList<String> getRelationTypeObservable() {
		return relationTypeObservable;
	}

	public void setRelationTypeObservable(ObservableList<String> relationTypeObservable) {
		this.relationTypeObservable = relationTypeObservable;
	}

	public ObservableList<String> getResolutionTypeObservable() {
		return resolutionTypeObservable;
	}

	public void setResolutionTypeObservable(ObservableList<String> resolutionTypeObservable) {
		this.resolutionTypeObservable = resolutionTypeObservable;
	}

	public ObservableList<String> getStatusTypeObservable() {
		return statusTypeObservable;
	}

	public void setStatusTypeObservable(ObservableList<String> statusTypeObservable) {
		this.statusTypeObservable = statusTypeObservable;
	}

	public List<Status> getStatusTypeList() {
		return statusTypeList;
	}

	public void setStatusTypeList(List<Status> statusTypeList) {
		this.statusTypeList = statusTypeList;
	}

	public List<Resolution> getResolutionTypeList() {
		return resolutionTypeList;
	}

	public void setResolutionTypeList(List<Resolution> resolutionTypeList) {
		this.resolutionTypeList = resolutionTypeList;
	}

	public List<Relation> getRelationTypeList() {
		return relationTypeList;
	}

	public void setRelationTypeList(List<Relation> relationTypeList) {
		this.relationTypeList = relationTypeList;
	}

}
