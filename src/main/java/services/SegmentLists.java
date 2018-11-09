package services;

import java.util.ArrayList;
import java.util.List;

import SPADEPAC.Artifact;
import SPADEPAC.Branch;
import SPADEPAC.Change;
import SPADEPAC.ConfigPersonRelation;
import SPADEPAC.Configuration;
import SPADEPAC.Criterion;
import SPADEPAC.Link;
import SPADEPAC.Milestone;
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
import forms.ConfigPersonRelationForm;
import forms.MilestoneForm;
import forms.RoleForm;
import graphics.ChangeArtifactLink;
import graphics.NodeLink;
import graphics.WorkUnitLink;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;

public class SegmentLists {
	/** Globální proměnné třídy **/
	private ObservableList<String> configObservable;

	private ObservableList<String> branchObservable;

	private ObservableList<String> roleObservable;

	private ObservableList<String> criterionObservable;

	private ObservableList<String> milestoneObservable;

	private ObservableList<String> artifactObservable;

	private ObservableList<String> CPRObservable;

	private ObservableList<String> roleTypeObservable;

	private ObservableList<String> priorityTypeObservable;

	private ObservableList<String> severityTypeObservable;

	private ObservableList<String> relationTypeObservable;

	private ObservableList<String> resolutionTypeObservable;

	private ObservableList<String> statusTypeObservable;

	private ObservableList<String> typeObservable;

	private ArrayList<NodeLink> arrows;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné a zavolá metodu pro
	 * vytvoření seznamů
	 *  */
	public SegmentLists() {

		createLists();

	}


	/**
	 * Zinicializuje veškeré seznami pro práci se segmenty a elementy
	 */
	public void createLists() {

		configObservable = FXCollections.observableArrayList();
		configObservable.add("");

		roleObservable = FXCollections.observableArrayList();
		roleObservable.add("");

		branchObservable = FXCollections.observableArrayList();
		branchObservable.add("");


		artifactObservable = FXCollections.observableArrayList();

		criterionObservable = FXCollections.observableArrayList();
		criterionObservable.add("");

		milestoneObservable = FXCollections.observableArrayList();
		milestoneObservable.add("");

		CPRObservable = FXCollections.observableArrayList();
		CPRObservable.add("");

		roleTypeObservable = FXCollections.observableArrayList();
		roleTypeObservable.add("");

		priorityTypeObservable = FXCollections.observableArrayList();
		priorityTypeObservable.add("");

		severityTypeObservable = FXCollections.observableArrayList();
		severityTypeObservable.add("");

		relationTypeObservable = FXCollections.observableArrayList();
		relationTypeObservable.add("");

		resolutionTypeObservable = FXCollections.observableArrayList();
		resolutionTypeObservable.add("");

		statusTypeObservable = FXCollections.observableArrayList();
		statusTypeObservable.add("");

		typeObservable = FXCollections.observableArrayList();
		typeObservable.add("");

		arrows = new ArrayList<>();
	}

	public ArrayList<NodeLink> getArrows() {
		return arrows;
	}



	public void addLinkToList(WorkUnitLink wuLink){
		getArrows().add(wuLink);

	}

	public void addLinkToList(ChangeArtifactLink caLink){
		getArrows().add(caLink);

	}

	public void removeArrow(int linkId) {

		if (getArrows().get(linkId) != null) {
			getArrows().remove(linkId);
			getArrows().add(linkId, null);
		}

	}

	public void repaintArrowStartPoint(int linkId, double newWidth, double newHeight) {

		NodeLink link = getArrows().get(linkId);
		if (link != null) {
			link.setStartPoint(new Point2D(newWidth, newHeight));
		}
	}

	public void repaintArrowEndPoint(int linkId, double newWidth, double newHeight) {

		NodeLink link = getArrows().get(linkId);
		if (link != null) {
			link.setEndPoint(new Point2D(newWidth, newHeight));
		}
	}

	public void repaintWorkUnitComboBox(int linkId) {

		WorkUnitLink link = (WorkUnitLink)getArrows().get(linkId);
		link.repaintComboBox();
	}

	public void repaintWorkUnitRelationEndPoint(int linkId, double x, double y) {

		WorkUnitLink link = (WorkUnitLink)getArrows().get(linkId);
		link.repaintEndPolygon(x, y);

	}

	public void removeItemFromObservableList(SegmentType segmentType, ArrayList indexList){

		switch (segmentType) {
			case Branch:
				 removeDataFromLis(branchObservable, indexList);
			case Priority:
				removeDataFromLis(priorityTypeObservable, indexList);
			case Severity:
				removeDataFromLis(severityTypeObservable, indexList);
			case Milestone:
				removeDataFromLis(milestoneObservable, indexList);
			case Criterion:
				removeDataFromLis(criterionObservable, indexList);
			case Role:
				removeDataFromLis(roleObservable, indexList);
			case RoleType:
				removeDataFromLis(roleTypeObservable, indexList);
			case ConfigPersonRelation:
				removeDataFromLis(CPRObservable, indexList);
			case Relation:
				removeDataFromLis(relationTypeObservable, indexList);
			case Resolution:
				removeDataFromLis(resolutionTypeObservable, indexList);
			case Status:
				removeDataFromLis(statusTypeObservable, indexList);
			case Type:
				removeDataFromLis(typeObservable, indexList);
			case Configuration:
				removeDataFromLis(configObservable, indexList);
			default:

		}

	}

	public void addItemToObservableList(SegmentType segmentType, String segmentInfo){

		switch (segmentType) {
			case Branch:
				branchObservable.add(segmentInfo) ;
			case Priority:
				priorityTypeObservable.add(segmentInfo);
			case Severity:
				severityTypeObservable.add(segmentInfo);
			case Milestone:
				milestoneObservable.add(segmentInfo);
			case Criterion:
				criterionObservable.add(segmentInfo);
			case Role:
				roleObservable.add(segmentInfo);
			case RoleType:
				roleTypeObservable.add(segmentInfo);
			case ConfigPersonRelation:
				CPRObservable.add(segmentInfo);
			case Relation:
				relationTypeObservable.add(segmentInfo);
			case Resolution:
				resolutionTypeObservable.add(segmentInfo);
			case Status:
				statusTypeObservable.add(segmentInfo);
			case Type:
				typeObservable.add(segmentInfo);
			case Configuration:
				configObservable.add(segmentInfo);
			default:

		}

	}


	private void removeDataFromLis(ObservableList<String> observableList, ArrayList<Integer> indexList) {

		for (Integer i : indexList){
			observableList.remove(i);
		}

	}


	/** Getrs and Setrs **/



	public ObservableList<String> getConfigObservable() {
		return configObservable;
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


	public ObservableList<String> getCriterionObservable() {
		return criterionObservable;
	}

	public ObservableList<String> getMilestoneObservable() {
		return milestoneObservable;
	}

	public ObservableList<String> getArtifactObservable() {
		return artifactObservable;
	}

	public ObservableList<String> getCPRObservable() {
		return CPRObservable;
	}

	public ObservableList<String> getRoleTypeObservable() {
		return roleTypeObservable;
	}

	public ObservableList<String> getPriorityTypeObservable() {
		return priorityTypeObservable;
	}

	public ObservableList<String> getSeverityTypeObservable() {
		return severityTypeObservable;
	}

	public ObservableList<String> getRelationTypeObservable() {
		return relationTypeObservable;
	}

	public ObservableList<String> getResolutionTypeObservable() {
		return resolutionTypeObservable;
	}

	public ObservableList<String> getStatusTypeObservable() {
		return statusTypeObservable;
	}

	public ObservableList<String> getTypeObservable() {
		return typeObservable;
	}

}
