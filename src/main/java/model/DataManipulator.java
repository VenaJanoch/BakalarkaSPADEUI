package model;

import Controllers.LinkControl;
import SPADEPAC.*;
import XML.ProcessGenerator;
import forms.*;
import interfaces.ISaveDataModel;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import services.*;
import tables.BasicTable;
import tables.BranchTable;
import tables.MilestoneTable;
import tables.RoleTable;

import javax.swing.text.html.HTML;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class DataManipulator{


    private Project project;
    private ProcessGenerator procesGener;
    private DataModel dataModel;
    private ObjectFactory objF;

    public DataManipulator(ProcessGenerator processGenerator, DataModel dataModel) {
        this.procesGener = processGenerator;
        this.dataModel = dataModel;
        this.project = dataModel.getProject();
        this.objF = dataModel.getObjF();
    }


    public void setRelationIndexToLink(int id, int relationIndex) {
    project.getLinks().get(id).setRelationIndex(relationIndex);
    }

    public void copyDataFromActivity(int oldActivityId, int newActivityId) {
        Activity oldActivity = project.getActivities().get(oldActivityId);
        Activity newActivity = project.getActivities().get(newActivityId);

        newActivity.setCoordinates(oldActivity.getCoordinates());
        newActivity.setName(oldActivity.getName());
        newActivity.setDescription(oldActivity.getDescription());

       int workUnitSize = project.getWorkUnits().size();
        for (int i =0; i < oldActivity.getWorkUnits().size(); i++){
            copyDataFromWorkUnit(oldActivity.getWorkUnits().get(i));
            newActivity.getWorkUnits().add(workUnitSize + i);
        }
    }

    public void copyDataFromWorkUnit(int oldWUId){
        WorkUnit oldWu = project.getWorkUnits().get(oldWUId);
        WorkUnit newWu = objF.createWorkUnit();
        copyDataFromWorkUnit(oldWu, newWu);
        project.getWorkUnits().add(newWu);

    }

    public void copyDataFromWorkUnit(int oldWUId, int newWUId){
        WorkUnit oldWu = project.getWorkUnits().get(oldWUId);
        WorkUnit newWu = project.getWorkUnits().get(newWUId);
        copyDataFromWorkUnit(oldWu, newWu);

    }

    public  void copyDataFromWorkUnit(WorkUnit oldWu, WorkUnit newWu){
        newWu.setResolutionIndex(oldWu.getResolutionIndex());
        newWu.setStatusIndex(oldWu.getStatusIndex());
        newWu.setTypeIndex(oldWu.getTypeIndex());
        newWu.setSeverityIndex(oldWu.getSeverityIndex());
        newWu.setPriorityIndex(oldWu.getPriorityIndex());
        newWu.setName(oldWu.getName());
        newWu.setExist(oldWu.isExist());
        newWu.setEstimatedTime(oldWu.getEstimatedTime());
        newWu.setDescription(oldWu.getDescription());
        newWu.setCoordinates(oldWu.getCoordinates());
        newWu.setCategory(oldWu.getCategory());
        newWu.setAuthorIndex(oldWu.getAuthorIndex());
        newWu.setAssigneeIndex(oldWu.getAssigneeIndex());
        newWu.setRelationIndex(oldWu.getRelationIndex());
    }

    public void copyDataFromIteration(int oldIteratationId, int newIterationId) {
        Iteration oldIteration = project.getIterations().get(oldIteratationId);
        Iteration newIteration = project.getIterations().get(newIterationId);

        newIteration.setCoordinates(oldIteration.getCoordinates());
        newIteration.setName(oldIteration.getName());
        newIteration.setDescription(oldIteration.getDescription());
        newIteration.setStartDate(oldIteration.getStartDate());
        newIteration.setEndDate(oldIteration.getEndDate());
        newIteration.setConfiguration(oldIteration.getConfiguration());

        int workUnitSize = project.getWorkUnits().size();
        for (int i =0; i < oldIteration.getWorkUnits().size(); i++){
            copyDataFromWorkUnit(oldIteration.getWorkUnits().get(i));
            newIteration.getWorkUnits().add(workUnitSize + i);
        }

    }


    public void copyDataFromPhase(int phaseId, int newPhaseId) {
        Phase oldPhase = project.getPhases().get(phaseId);
        Phase newPhase = project.getPhases().get(newPhaseId);

        newPhase.setCoordinates(oldPhase.getCoordinates());
        newPhase.setName(oldPhase.getName());
        newPhase.setDescription(oldPhase.getDescription());
        newPhase.setEndDate(oldPhase.getEndDate());
        newPhase.setConfiguration(oldPhase.getConfiguration());
        newPhase.setConfiguration(oldPhase.getConfiguration());
        newPhase.setMilestoneIndex(oldPhase.getMilestoneIndex());

        int workUnitSize = project.getWorkUnits().size();
        for (int i =0; i < oldPhase.getWorkUnits().size(); i++){
            copyDataFromWorkUnit(oldPhase.getWorkUnits().get(i));
            newPhase.getWorkUnits().add(workUnitSize + i);
        }

    }

    public void copyDataFromChange(int changeId, int newchangeId) {

        Change oldChange = project.getChanges().get(changeId);
        Change newChange = project.getChanges().get(newchangeId);

        newChange.setCoordinates(oldChange.getCoordinates());
        newChange.setName(oldChange.getName());
        newChange.setDescriptoin(oldChange.getDescriptoin());
        newChange.setExist(oldChange.isExist());
        }

    public void copyDataFromArtifact(int artifactId, int newArtifactId) {
        Artifact oldArtifact = project.getArtifacts().get(artifactId);
        Artifact newArtifact = project.getArtifacts().get(newArtifactId);

        newArtifact.setCoordinates(oldArtifact.getCoordinates());
        newArtifact.setName(oldArtifact.getName());
        newArtifact.setDescriptoin(oldArtifact.getDescriptoin());
        newArtifact.setExist(oldArtifact.isExist());
        newArtifact.setMimeType(oldArtifact.getMimeType());
        newArtifact.setCreated(oldArtifact.getCreated());
        newArtifact.setAuthorIndex(oldArtifact.getAuthorIndex());
        newArtifact.setArtifactIndex(oldArtifact.getArtifactIndex());
    }

    public String[] getCriterionData(int id) {
        String[] data = new String[2];
        Criterion criterion = project.getCriterions().get(getCriterionIndexInProject(id));
        data[0] = criterion.getName();
        data[1] = criterion.getDescription();
        return data;
    }

    private int getCriterionIndexInProject(int id) {
        List<Criterion> items = project.getCriterions();
        for ( int i = 0; i < items.size(); i++){

            if (items.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

    public String[] getMilestoneData(int id) {
        String[] data = new String[2];
        Milestone milestone = project.getMilestones().get(id);
        data[0] = milestone.getName();
        data[1] = milestone.getDescription();
        return data;
    }

    public List getCriterionFromMilestone(int id) {
        Milestone milestone = project.getMilestones().get(id);
        return milestone.getCriteriaIndexs();
    }


    public String[] getRoleData(int id) {
        String[] data = new String[3];
        Role role = dataModel.getRole(id);
        data[0] = role.getName();
        data[1] = role.getDescription();
        data[2] = role.getType().toString();
        return data;

    }

    public String[] getRoleTypeData(int id) {
        String[] data = new String[3];
        RoleType roleType = dataModel.getRoleType(id);
        data[0] = roleType.getName();
        data[1] = roleType.getRoleClass();
        data[2] = roleType.getRoleSuperClass();
        return data;
    }


    public String[] getSeverityData(int id) {
        String[] data = new String[3];
        Severity severity = dataModel.getSeverity(id);
        data[0] = severity.getName();
        data[1] = severity.getSeverityClass();
        data[2] = severity.getSeveritySuperClass();
        return data;
    }

    public String[] getPriorityData(int id) {
        String[] data = new String[3];
        Priority priority = dataModel.getPriority(id);
        data[0] = priority.getName();
        data[1] = priority.getPriorityClass();
        data[2] = priority.getPrioritySuperClass();
        return data;
    }

    public String[] getStatusData(int id) {
        String[] data = new String[3];
        Status status = dataModel.getStatus(id);
        data[0] = status.getName();
        data[1] = status.getStatusClass();
        data[2] = status.getStatusSuperClass();
        return data;
    }

    public String[] getTypeData(int id) {
        String[] data = new String[3];
        Type type = dataModel.getType(id);
        data[0] = type.getName();
        data[1] = type.getTypeClass();
        data[2] = type.getTypeSuperClass();
        return data;
    }


    public String[] getRelationData(int id) {
        String[] data = new String[3];
        Relation relation = dataModel.getRelation(id);
        data[0] = relation.getName();
        data[1] = relation.getRelationClass();
        data[2] = relation.getRelationSuperClass();
        return data;
    }

    public String[] getResolutionData(int id) {
        String[] data = new String[3];
        Resolution resolution = dataModel.getResolution(id);
        data[0] = resolution.getName();
        data[1] = resolution.getResolutionClass();
        data[2] = resolution.getResolutionSuperClass();
        return data;
    }

    public String[] getCPRData(int id) {
        String[] data = new String[2];
        ConfigPersonRelation cpr = dataModel.getConfigPersonRelation(id);
        data[0] = cpr.getName();
        data[1] = cpr.getPersonIndex().toString();
        return data;
    }

    public String[] getBranchStringData(int id) {
        String[] data = new String[2];
        Branch branch = dataModel.getBranch(id);;
        data[0] = branch.getName();
        data[1] = branch.isIsMain().toString();
        return data;
    }


    public String getTagStringData(int id, int configId) {
        Configuration configuration = dataModel.getConfiguration(configId);
        return configuration.getTags().get(id);
    }
}
