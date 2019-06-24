package services;

import java.util.ArrayList;

import graphics.canvas.ElementsLink;
import graphics.canvas.NodeLink;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tables.*;

/**
 * Třída uchovavajici prehledove seznamy urcene pro comboboxy
 *
 * @author Václav Janoch
 */
public class SegmentLists {
    /**
     * Globální proměnné třídy
     **/
    private ObservableList<BasicTable> configObservable;

    private ObservableList<BasicTable> commitObservable;

    private ObservableList<BasicTable> commitedConfigurationObservable;

    private ObservableList<BasicTable> workUnitsObservable;

    private ObservableList<BasicTable> branchObservable;

    private ObservableList<BasicTable> roleObservable;

    private ObservableList<BasicTable> criterionObservable;

    private ObservableList<BasicTable> milestoneObservable;

    private ObservableList<BasicTable> artifactObservable;

    private ObservableList<BasicTable> changeObservable;

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
        configObservable.add(new ConfigTable("", "", -1, true, -1));

        commitObservable = FXCollections.observableArrayList();
        commitObservable.add(new CommitTable("", "", true, true, -1));

        commitedConfigurationObservable = FXCollections.observableArrayList();
        commitedConfigurationObservable.add(new CommitedConfigurationTable("", "", true, -1));


        VCSTagObservable = FXCollections.observableArrayList();
        VCSTagObservable.add(new VCSTagTable("", true, -1));


        workUnitsObservable = FXCollections.observableArrayList();
        workUnitsObservable.add(new WorkUnitTable("", true, -1));


        roleObservable = FXCollections.observableArrayList();
        roleObservable.add(new PersonTable("", true, -1));

        branchObservable = FXCollections.observableArrayList();
        branchObservable.add(new BranchTable("", "", false, true, -1));


        artifactObservable = FXCollections.observableArrayList();
        artifactObservable.add(new ArtifactTable("", true, -1));

        changeObservable = FXCollections.observableArrayList();
        changeObservable.add(new ChangeTable("", true, -1));


        criterionObservable = FXCollections.observableArrayList();
        criterionObservable.add(new CriterionTable("", true, -1));

        milestoneObservable = FXCollections.observableArrayList();
        milestoneObservable.add(new MilestoneTable("", true, -1));

        CPRObservable = FXCollections.observableArrayList();
        CPRObservable.add(new CPRTable("", "", true, -1));

        roleTypeObservable = FXCollections.observableArrayList();
        roleTypeObservable.add(new ClassTable("", "", "", true, -1));

        priorityTypeObservable = FXCollections.observableArrayList();
        priorityTypeObservable.add(new ClassTable("", "", "", true, -1));

        severityTypeObservable = FXCollections.observableArrayList();
        severityTypeObservable.add(new ClassTable("", "", "", true, -1));

        relationTypeObservable = FXCollections.observableArrayList();
        relationTypeObservable.add(new ClassTable("", "", "", true, -1));

        resolutionTypeObservable = FXCollections.observableArrayList();
        resolutionTypeObservable.add(new ClassTable("", "", "", true, -1));

        statusTypeObservable = FXCollections.observableArrayList();
        statusTypeObservable.add(new ClassTable("", "", "", true, -1));

        typeObservable = FXCollections.observableArrayList();
        typeObservable.add(new ClassTable("", "", "", true, -1));

        arrows = new ArrayList<>();
    }

    public ArrayList<NodeLink> getArrows() {
        return arrows;
    }


    /**
     * Metoda pro pridani spojnice do seznamu
     * @param caLink
     */
    public void addLinkToList(ElementsLink caLink) {
        getArrows().add(caLink);

    }

    /**
     * Metoda pro odstraneni spojnice ze seznamu
     * @param linkId
     */
    public void removeArrow(int linkId) {

        for (int i = 0; i < arrows.size(); i++){
            NodeLink link = arrows.get(i);
            if (link != null && link.getLinkId() == linkId) {
                getArrows().remove(i);
                getArrows().add(i, null);
            }
        }


    }

    /**
     * Metoda pro odstraneni prvku ze seznamu podle indexu v seznamu
     * @param segmentType typ seznamu
     * @param indexList index v seznamu
     * @return identifikator mazaneho prvku
     */
    public int removeItemFromObservableList(SegmentType segmentType, int indexList) {
        switch (segmentType) {
            case Branch:
                return removeDataFromListTest(branchObservable, indexList);
            case Artifact:
                return removeDataFromListTest(artifactObservable, indexList);
            case Change:
                return removeDataFromListTest(changeObservable, indexList);
            case Priority:
                return removeDataFromListTest(priorityTypeObservable, indexList);
            case Severity:
                return removeDataFromListTest(severityTypeObservable, indexList);
            case Milestone:
                return removeDataFromListTest(milestoneObservable, indexList);
            case Criterion:
                return removeDataFromListTest(criterionObservable, indexList);

            case Person:
                return removeDataFromListTest(roleObservable, indexList);
            case Role_Type:
                return removeDataFromListTest(roleTypeObservable, indexList);
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
            case Committed_Configuration:
                return removeDataFromListTest(commitedConfigurationObservable, indexList);
            case Commit:
                return removeDataFromListTest(commitObservable, indexList);
            case VCSTag:
                return removeDataFromListTest(getVCSTag(), indexList);
            case Work_Unit:
                return removeDataFromListTest(workUnitsObservable, indexList);


            default:
        }
        return -1;
    }
    /**
     * Pretizena metoda pro odstraneni prvku ze seznamu podle seznamu indexu v seznamu
     * @param segmentType typ seznamu
     * @param indexList index v seznamu
     * @return identifikator mazaneho prvku
     */
    public ArrayList<Integer> removeItemFromObservableList(SegmentType segmentType, ArrayList indexList) {

        switch (segmentType) {
            case Branch:
                removeDataFromListTest(branchObservable, indexList);
                break;
            case Work_Unit:
                removeDataFromListTest(workUnitsObservable, indexList);
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
            case Change:
                removeDataFromListTest(changeObservable, indexList);
                break;
            case VCSTag:
                removeDataFromListTest(VCSTagObservable, indexList);
                break;
            default:

        }
        return null;
    }

    /**
     * Metoda pro upravu prvku v seznamu podle identifikatoru
     * @param segmentType typ seznamu
     * @param id identifikator prvku
     * @param table instace tridy BasicTable pro nahrazeni v seznamu
     */
    public void updateListItem(SegmentType segmentType, int id, BasicTable table) {
        int listIndex = removeItemFromObservableList(segmentType, id);
        addItemToObservableList(segmentType, listIndex, table);
    }

    /**
     * Metoda pro pridani prvku do seznamu
     * @param segmentType typ seznamu
     * @param index poradi v seznamu
     * @param basicTable nstace tridy BasicTable pro vlozeni do seznamu
     */
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

    /**
     * Metoda pro smazani prvku ze seznamu v zavislosit na typu seznamu a seznamu indexu
     * @param observableList seznam BasicTable
     * @param indexList seznam indexu v seznamu
     * @return seznam identifikatoru pro smazani
     */
    private ArrayList<Integer> removeDataFromListTest(ObservableList<BasicTable> observableList, ArrayList<Integer> indexList) {
        ArrayList<Integer> listIndicies = new ArrayList<>();

        for (int i = indexList.size() - 1; i >= 0; i--) {

            listIndicies.add(removeDataFromListTest(observableList, indexList.get(i)));

        }

        return listIndicies;
    }

    /**
     * Pretizena metoda pro smazani prvku ze seznamu v zavislosit na typu seznamu a indexu
     * @param observableList seznam BasicTable
     * @param id index v seznamu
     * @return seznam identifikatoru pro smazani
     */
    private int removeDataFromListTest(ObservableList<BasicTable> observableList, int id) {
        for (int j = 0; j < observableList.size(); j++) {
            if (observableList.get(j).getId() == id) {
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

    public ObservableList<BasicTable> getCommitObservable() {
        return commitObservable;
    }

    public ObservableList<BasicTable> getCommitedConfigurationObservable() {
        return commitedConfigurationObservable;
    }

    public ObservableList<BasicTable> getVCSTagObservable() {
        return VCSTagObservable;
    }

    public ObservableList<BasicTable> getPersonObservable() {
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

    public ObservableList<BasicTable> getChangeObservable() {
        return changeObservable;
    }

    public ObservableList<BasicTable> getWorkUnitsObservable() {
        return workUnitsObservable;
    }

    public BasicTable getArtifactTable(int id) {

        for (BasicTable table : artifactObservable) {
            if (table.getId() == id) {
                return table;
            }
        }

        return null;
    }

    public BasicTable getConfigurationTable(int id) {

        for (BasicTable table : configObservable) {
            if (table.getId() == id) {
                return table;
            }
        }

        return null;
    }

    public BasicTable getCommitedConfigurationTable(int id) {

        for (BasicTable table : commitedConfigurationObservable) {
            if (table.getId() == id) {
                return table;
            }
        }

        return null;
    }

    public BasicTable getCommitTable(int id) {

        for (BasicTable table : commitObservable) {
            if (table.getId() == id) {
                return table;
            }
        }

        return null;
    }

    public BasicTable getPersonTable(int id) {
        for (BasicTable table : roleObservable) {
            if (table.getId() == id) {
                return table;
            }
        }
        return null;
    }

    public ObservableList<BasicTable> getVCSTag() {
        return VCSTagObservable;
    }

    public NodeLink getArrow(int linkId) {
        for (int i =0; i < arrows.size(); i++ ){
            NodeLink link = arrows.get(i);
            if (link != null && link.getLinkId() == linkId ){
                return link;
            }
        }
        return null;
    }


}
