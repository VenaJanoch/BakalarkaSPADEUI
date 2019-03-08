package model;

import SPADEPAC.Configuration;
import SPADEPAC.Project;
import interfaces.IDeleteDataModel;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class DeleteDataModel implements IDeleteDataModel {

    private Project project;
    private DataModel dataModel;

    public  DeleteDataModel(Project project, DataModel dataModel){
        this.project = project;
        this.dataModel = dataModel;
    }

    public void removeType(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getTypes().remove((int)indexList.get(i));
        }
    }

    public void removeStatus(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getStatus().remove((int)indexList.get(i));
        }
    }

    public void removeTag(ArrayList<Integer> indexList, int configId) {
        Configuration configuration = project.getConfiguration().get(configId);
        for(Integer i : indexList){
            configuration.getTags().remove(i);
        }

    }

    public void removeRoleType(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getRoleType().remove((int)indexList.get(i));
        }
    }

    public void removeMilestone(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getMilestones().remove((int)indexList.get(i));
        }
    }

    public void removeRole(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getRoles().remove((int)indexList.get(i));
        }
    }

    public void removeResolution(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getResolution().remove((int)indexList.get(i));
        }
    }

    public void removeRelation(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getRelation().remove((int)indexList.get(i));
        }
    }

    public void removeSeverity(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getSeverity().remove((int)indexList.get(i));
        }
    }

    public void removePriority(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getPriority().remove((int)indexList.get(i));
        }
    }

    public void removeCriterion(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getCriterions().remove((int)indexList.get(i));
        }
    }

    public void removeCPR(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getCpr().remove((int)indexList.get(i));
        }
    }

    public void removeBranch(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getBranches().remove((int)indexList.get(i));
        }
    }

    public void removeArtifactChangeLink(int artifactID, int changeID) {


        project.getArtifacts().get(artifactID).getChangeIndex().remove(0);
        project.getChanges().get(changeID).getArtifactIndex().remove(0);

    }

    public void removeArtifact(int id) {
        project.getArtifacts().remove(dataModel.getArtifactIndexInProject(id));
    }

    public void removeConfiguration(ObservableList<Integer> indexList){
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getConfiguration().remove((int)indexList.get(i));
        }
    }

    public void removeWorkUnit(ObservableList<Integer> indexList) {
            for(int i = indexList.size() -1; i >= 0; i-- ){
                project.getWorkUnits().remove((int)indexList.get(i));
            }
    }

    public void removeActivity(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getActivities().remove((int)indexList.get(i));
        }
    }

    public void removeWorkUnitRelation(int startItemId, int endItemId) {

        project.getWorkUnits().get(startItemId).setRelationIndex(null);
        project.getWorkUnits().get(endItemId).setRelationIndex(null);

    }
    public void removeIteration(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getIterations().remove((int)indexList.get(i));
        }
    }

    public void removePhase(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getPhases().remove((int)indexList.get(i));
        }
    }

    public void removeChange(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getChanges().remove((int)indexList.get(i));
        }
    }

}
