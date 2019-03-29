package services;

import java.util.ArrayList;

import graphics.ElementsLink;
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
    private ObservableList<BasicTable> configObservable;

    private ObservableList<BasicTable> workUnitsObservable;

    private ObservableList<BasicTable> branchObservable;

    private ObservableList<BasicTable> roleObservable;

    private ObservableList<BasicTable> criterionObservable;

    private ObservableList<BasicTable> milestoneObservable;

    private ObservableList<BasicTable> artifactObservable;

    private ObservableList<BasicTable> CPRObservable;

    private ObservableList<BasicTable> roleTypeObservable;

    private ObservableList<BasicTable> priorityTypeObservable;

    private ObservableList<BasicTable> severityTypeObservable;

    private ObservableList<BasicTable> relationTypeObservable;

    private ObservableList<BasicTable> resolutionTypeObservable;

    private ObservableList<BasicTable> statusTypeObservable;

    private ObservableList<BasicTable> typeObservable;

    private ObservableList<BasicTable> VCSTagObservable;

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
        configObservable.add(new ConfigTable("", "", -1, true,-1));

        VCSTagObservable = FXCollections.observableArrayList();
        VCSTagObservable.add(new VCSTagTable("",  "",true, -1));


        workUnitsObservable = FXCollections.observableArrayList();
        workUnitsObservable.add(new WorkUnitTable("", true,-1));


        roleObservable = FXCollections.observableArrayList();
        roleObservable.add(new PersonTable("","","",true,-1));

        branchObservable = FXCollections.observableArrayList();
        branchObservable.add(new BranchTable("","", false,true,-1));


        artifactObservable = FXCollections.observableArrayList();
        artifactObservable.add(new ArtifactTable("","",true,-1));

        criterionObservable = FXCollections.observableArrayList();
        criterionObservable.add(new CriterionTable("","",true,-1));

        milestoneObservable = FXCollections.observableArrayList();
        milestoneObservable.add(new MilestoneTable("","","",true,-1));

        CPRObservable = FXCollections.observableArrayList();
        CPRObservable.add(new CPRTable("", "", true,-1));

        roleTypeObservable = FXCollections.observableArrayList();
        roleTypeObservable.add(new ClassTable("", "", "",true,-1));

        priorityTypeObservable = FXCollections.observableArrayList();
        priorityTypeObservable.add(new ClassTable("", "", "",true,-1));

        severityTypeObservable = FXCollections.observableArrayList();
        severityTypeObservable.add(new ClassTable("", "", "",true,-1));

        relationTypeObservable = FXCollections.observableArrayList();
        relationTypeObservable.add(new ClassTable("", "", "",true,-1));

        resolutionTypeObservable = FXCollections.observableArrayList();
        resolutionTypeObservable.add(new ClassTable("", "", "",true,-1));

        statusTypeObservable = FXCollections.observableArrayList();
        statusTypeObservable.add(new ClassTable("", "", "",true,-1));

        typeObservable = FXCollections.observableArrayList();
        typeObservable.add(new ClassTable("", "", "",true,-1));

        arrows = new ArrayList<>();
    }

    public ArrayList<NodeLink> getArrows() {
        return arrows;
    }


    public void addLinkToList(WorkUnitLink wuLink) {
        getArrows().add(wuLink);

    }

    public void addLinkToList(ElementsLink caLink) {
        getArrows().add(caLink);

    }

    public void removeArrow(int linkId) {

        if (getArrows().get(linkId) != null) {
            getArrows().remove(linkId);
            getArrows().add(linkId, null);
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

    public int removeItemFromObservableList(SegmentType segmentType, int indexList) {
        switch (segmentType) {
            case Branch:
               return removeDataFromListTest(branchObservable, indexList);
            case Artifact:
                return removeDataFromListTest(artifactObservable, indexList);
            case Priority:
                return removeDataFromListTest(priorityTypeObservable, indexList);
            case Severity:
                return  removeDataFromListTest(severityTypeObservable, indexList);
            case Milestone:
                return  removeDataFromListTest(milestoneObservable, indexList);
            case Criterion:
                return removeDataFromListTest(criterionObservable, indexList);

            case Person:
                return removeDataFromListTest(roleObservable, indexList);
            case Role_Type:
                return  removeDataFromListTest(roleTypeObservable, indexList);

            case Config_Person_Relation:
                return removeDataFromListTest(CPRObservable, indexList);

            case Relation:
                return removeDataFromListTest(relationTypeObservable, indexList);

            case Resolution:
                return removeDataFromListTest(resolutionTypeObservable, indexList);

            case Status:
                return removeDataFromListTest(statusTypeObservable, indexList);

            case Type:
                return removeDataFromListTest(typeObservable, indexList);

            case Configuration:
                return removeDataFromListTest(configObservable, indexList);
            case Work_Unit:
                return removeDataFromListTest(workUnitsObservable, indexList);

            default:
        }
        return -1;
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
            case Person:
                return removeDataFromListTest(roleObservable, indexList);
            case Role_Type:
                removeDataFromListTest(roleTypeObservable, indexList);
                break;
            case Config_Person_Relation:
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
                removeDataFromListTest(configObservable, indexList);
                break;
            default:

        }
        return null;
    }

    public void updateListItem(SegmentType segmentType, int id, BasicTable table) {
       int listIndex =  removeItemFromObservableList(segmentType, id);
        addItemToObservableList(segmentType, listIndex, table);
    }

    public void addItemToObservableList(SegmentType segmentType, int index, BasicTable basicTable) {

        switch (segmentType) {
            case Branch:
                branchObservable.add(index, basicTable);
                break;
            case Priority:
                priorityTypeObservable.add(index, basicTable);
                break;
            case Severity:
                severityTypeObservable.add(index, basicTable);
                break;
            case Milestone:
                milestoneObservable.add(index, basicTable);
                break;
            case Criterion:
                criterionObservable.add(index, basicTable);
                break;
            case Person:
                roleObservable.add(index, basicTable);
                break;
            case Role_Type:
                roleTypeObservable.add(index, basicTable);
                break;
            case Config_Person_Relation:
                CPRObservable.add(index, basicTable);
                break;
            case Relation:
                relationTypeObservable.add(index, basicTable);
                break;
            case Resolution:
                resolutionTypeObservable.add(index, basicTable);
                break;
            case Status:
                statusTypeObservable.add(index, basicTable);
                break;
            case Type:
                typeObservable.add(index, basicTable);
                break;
            case Configuration:
                configObservable.add(index, basicTable);
                break;
            case Work_Unit:
                workUnitsObservable.add(index, basicTable);
                break;
            default:

        }

    }

    private ArrayList<Integer> removeDataFromListTest(ObservableList<BasicTable> observableList, ArrayList<Integer> indexList) {
        ArrayList<Integer> listIndicies = new ArrayList<>();

        for (int i = indexList.size() - 1; i >= 0; i--) {

            listIndicies.add(removeDataFromListTest(observableList, indexList.get(i)));

        }

        return listIndicies;
    }

    private int removeDataFromListTest(ObservableList<BasicTable> observableList, int id) {
            for (int j = 0; j < observableList.size(); j++){
                if(observableList.get(j).getId() == id){
                    observableList.remove(j);
                    return j;
                }
            }
            return -1;
    }

    /**
     * Getrs and Setrs
     **/


    public ObservableList<BasicTable> getConfigObservable() {
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

    public ObservableList<BasicTable> getArtifactObservable() {
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


    public ObservableList<BasicTable> getWorkUnitsObservable() {
        return workUnitsObservable;
    }

    public BasicTable getArtifactTable(int id) {

        for (BasicTable table : artifactObservable){
            if (table.getId() == id ){
                return table;
            }
        }

        return null;
    }

    public ObservableList<BasicTable> getVCSTag() {
        return VCSTagObservable;
    }

    public NodeLink getArrow(int linkId) {
        return arrows.get(linkId);
    }
}
