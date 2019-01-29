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
import tables.*;

public class SegmentLists {
    /**
     * Globální proměnné třídy
     **/
    private ObservableList<String> configObservable;

    private ObservableList<BasicTable> branchObservable;

    private ObservableList<BasicTable> roleObservable;

    private ObservableList<BasicTable> criterionObservable;

    private ObservableList<BasicTable> milestoneObservable;

    private ObservableList<String> artifactObservable;

    private ObservableList<BasicTable> CPRObservable;

    private ObservableList<BasicTable> roleTypeObservable;

    private ObservableList<BasicTable> priorityTypeObservable;

    private ObservableList<BasicTable> severityTypeObservable;

    private ObservableList<BasicTable> relationTypeObservable;

    private ObservableList<BasicTable> resolutionTypeObservable;

    private ObservableList<BasicTable> statusTypeObservable;

    private ObservableList<BasicTable> typeObservable;

    private ArrayList<NodeLink> arrows;

    /**
     * Konstruktor třídy Zinicializuje globální proměnné a zavolá metodu pro
     * vytvoření seznamů
     */
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
        roleObservable.add(new RoleTable("","","",-1));

        branchObservable = FXCollections.observableArrayList();
        branchObservable.add(new BranchTable("","", false,-1));


        artifactObservable = FXCollections.observableArrayList();

        criterionObservable = FXCollections.observableArrayList();
        criterionObservable.add(new CriterionTable("","",-1));

        milestoneObservable = FXCollections.observableArrayList();
        milestoneObservable.add(new MilestoneTable("","",-1));

        CPRObservable = FXCollections.observableArrayList();
        CPRObservable.add(new CPRTable("", "", -1));

        roleTypeObservable = FXCollections.observableArrayList();
        roleTypeObservable.add(new ClassTable("", "", "",-1));

        priorityTypeObservable = FXCollections.observableArrayList();
        priorityTypeObservable.add(new ClassTable("", "", "",-1));

        severityTypeObservable = FXCollections.observableArrayList();
        severityTypeObservable.add(new ClassTable("", "", "",-1));

        relationTypeObservable = FXCollections.observableArrayList();
        relationTypeObservable.add(new ClassTable("", "", "",-1));

        resolutionTypeObservable = FXCollections.observableArrayList();
        resolutionTypeObservable.add(new ClassTable("", "", "",-1));

        statusTypeObservable = FXCollections.observableArrayList();
        statusTypeObservable.add(new ClassTable("", "", "",-1));

        typeObservable = FXCollections.observableArrayList();
        typeObservable.add(new ClassTable("", "", "",-1));

        arrows = new ArrayList<>();
    }

    public ArrayList<NodeLink> getArrows() {
        return arrows;
    }


    public void addLinkToList(WorkUnitLink wuLink) {
        getArrows().add(wuLink);

    }

    public void addLinkToList(ChangeArtifactLink caLink) {
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

        WorkUnitLink link = (WorkUnitLink) getArrows().get(linkId);
        link.repaintComboBox();
    }

    public void repaintWorkUnitRelationEndPoint(int linkId, double x, double y) {

        WorkUnitLink link = (WorkUnitLink) getArrows().get(linkId);
        link.repaintEndPolygon(x, y);

    }

    public ArrayList<Integer> removeItemFromObservableList(SegmentType segmentType, ArrayList indexList) {

        switch (segmentType) {
            case Branch:
                removeDataFromListTest(branchObservable, indexList);
                break;
            case Priority:
                return removeDataFromListTest(priorityTypeObservable, indexList);
            case Severity:
                return removeDataFromListTest(severityTypeObservable, indexList);
            case Milestone:
                return removeDataFromListTest(milestoneObservable, indexList);
            case Criterion:
                removeDataFromListTest(criterionObservable, indexList);
                break;
            case Role:
                return removeDataFromListTest(roleObservable, indexList);
            case RoleType:
                removeDataFromListTest(roleTypeObservable, indexList);
                break;
            case ConfigPersonRelation:
                removeDataFromListTest(CPRObservable, indexList);
                break;
            case Relation:
                removeDataFromListTest(relationTypeObservable, indexList);
                break;
            case Resolution:
                removeDataFromListTest(resolutionTypeObservable, indexList);
                break;
            case Status:
                removeDataFromListTest(statusTypeObservable, indexList);
                break;
            case Type:
                removeDataFromListTest(typeObservable, indexList);
                break;
            case Configuration:
                removeDataFromLis(configObservable, indexList);
                break;
            default:

        }
        return null;
    }

    public void addItemToObservableList(SegmentType segmentType, String segmentInfo, BasicTable basicTable) {

        switch (segmentType) {
            case Branch:
                branchObservable.add(basicTable);
            case Priority:
                priorityTypeObservable.add(basicTable);
            case Severity:
                severityTypeObservable.add(basicTable);
            case Milestone:
                milestoneObservable.add(basicTable);
            case Criterion:
                criterionObservable.add(basicTable);
            case Role:
                roleObservable.add(basicTable);
            case RoleType:
                roleTypeObservable.add(basicTable);
            case ConfigPersonRelation:
                CPRObservable.add(basicTable);
            case Relation:
                relationTypeObservable.add(basicTable);
            case Resolution:
                resolutionTypeObservable.add(basicTable);
            case Status:
                statusTypeObservable.add(basicTable);
            case Type:
                typeObservable.add(basicTable);
            case Configuration:
                configObservable.add(segmentInfo);
            default:

        }

    }


    private void removeDataFromLis(ObservableList<String> observableList, ArrayList<Integer> indexList) {

        for (int i = indexList.size() - 1; i >= 0; i--) {
            observableList.remove(indexList.get(i) + 1);
        }

    }

    private ArrayList<Integer> removeDataFromListTest(ObservableList<BasicTable> observableList, ArrayList<Integer> indexList) {
        ArrayList<Integer> tableIndeces = new ArrayList<>();

        for (int i = indexList.size() - 1; i >= 0; i--) {
            int itemId = indexList.get(i);
            for (int j = 0; j < observableList.size(); j++){
                if(observableList.get(j).getId() == itemId){
                    observableList.remove(j);
                    tableIndeces.add(j-1);
                }
            }
        }
        return tableIndeces;
    }

    /**
     * Getrs and Setrs
     **/


    public ObservableList<String> getConfigObservable() {
        return configObservable;
    }

    public ObservableList<BasicTable> getRoleObservable() {
        return roleObservable;
    }

    public void setRoleObservable(ObservableList<BasicTable> roleObservable) {
        this.roleObservable = roleObservable;
    }

    public ObservableList<BasicTable> getBranchObservable() {
        return branchObservable;
    }


    public ObservableList<BasicTable> getCriterionObservable() {
        return criterionObservable;
    }

    public ObservableList<BasicTable> getMilestoneObservable() {
        return milestoneObservable;
    }

    public ObservableList<String> getArtifactObservable() {
        return artifactObservable;
    }

    public ObservableList<BasicTable> getCPRObservable() {
        return CPRObservable;
    }

    public ObservableList<BasicTable> getRoleTypeObservable() {
        return roleTypeObservable;
    }

    public ObservableList<BasicTable> getPriorityTypeObservable() {
        return priorityTypeObservable;
    }

    public ObservableList<BasicTable> getSeverityTypeObservable() {
        return severityTypeObservable;
    }

    public ObservableList<BasicTable> getRelationTypeObservable() {
        return relationTypeObservable;
    }

    public ObservableList<BasicTable> getResolutionTypeObservable() {
        return resolutionTypeObservable;
    }

    public ObservableList<BasicTable> getStatusTypeObservable() {
        return statusTypeObservable;
    }

    public ObservableList<BasicTable> getTypeObservable() {
        return typeObservable;
    }

}
