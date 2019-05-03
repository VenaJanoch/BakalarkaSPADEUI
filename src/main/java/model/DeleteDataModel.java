package model;

import SPADEPAC.Artifact;
import SPADEPAC.CommitedConfiguration;
import SPADEPAC.Configuration;
import SPADEPAC.Project;
import interfaces.IDeleteDataModel;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class DeleteDataModel implements IDeleteDataModel {
    
    private DataModel dataModel;

    public  DeleteDataModel(DataModel dataModel){
       
        this.dataModel = dataModel;
    }

    public void removeType(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            dataModel.getTypes().remove((int)indexList.get(i));
        }
    }

    public void removeStatus(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            dataModel.getStatuses().remove((int)indexList.get(i));
        }
    }


    public void removeRoleType(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            dataModel.getRoleTypes().remove((int)indexList.get(i));
        }
    }

    public void removeMilestone(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            dataModel.getMilestones().remove((int)indexList.get(i));
        }
    }

    public void removeRole(int id) {
        int dataModelIndex = dataModel.getRoleIndexInProject(id);
            dataModel.getPersons().remove(dataModelIndex);

    }

    public void removeResolution(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            dataModel.getResolutions().remove((int)indexList.get(i));
        }
    }

    public void removeRelation(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            dataModel.getRelations().remove((int)indexList.get(i));
        }
    }

    public void removeSeverity(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            dataModel.getSeverities().remove((int)indexList.get(i));
        }
    }

    public void removePriority(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            dataModel.getPriorities().remove((int)indexList.get(i));
        }
    }

    public void removeCriterion(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            dataModel.getCriterions().remove((int)indexList.get(i));
        }
    }

    public void removeCPR(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            dataModel.getConfigPersonRelations().remove((int)indexList.get(i));
        }
    }

    public void removeBranch(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            dataModel.getBranches().remove((int)indexList.get(i));
        }
    }

    public void removeVCSTag(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            dataModel.getVCSTags().remove((int)indexList.get(i));
        }
    }

    public void removePersonArtifactLink(int arrowId, int personId, int artifactID) {

        int linkIndexInProject = dataModel.getLinkIndexInProject(arrowId);
        dataModel.getLinks().remove(linkIndexInProject);
        Artifact artifact = dataModel.getArtifact(artifactID);
        List<Integer> list = artifact.getAuthorIndex();
        removePersonFromList(list, personId);
    }

    public void removePersonFromList(List<Integer> list, int personId){
        for (int i =0; i > list.size(); i++){
            int roleIndex = list.get(i);
            int roleId = dataModel.getRoleId(roleIndex);
            if (roleId == personId){
                list.remove(i);
            }
        }
    }

    public void removeArtifactFromList(List<Integer> list, int artifactId){
        for (int i =0; i > list.size(); i++){
            int artifactIndex = list.get(i);
            int artifactIdloc = dataModel.getArtifactId(artifactIndex);
            if (artifactIdloc == artifactId){
                list.remove(i);
            }
        }
    }

    public void removeCommitFromList(List<Integer> list, int commitId){
        for (int i =0; i > list.size(); i++){
            int commitIndex = list.get(i);
            int commitIdloc = dataModel.getCommitId(commitIndex);
            if (commitIdloc == commitId){
                list.remove(i);
            }
        }
    }

    public void removeCommitedConfigurationFromList(List<Integer> list, int commitedConfigurationId){
        for (int i =0; i > list.size(); i++){
            int commitedConfigurationindex = list.get(i);
            int commitedConfigurationIdloc = dataModel.getCommitedConfigurationId(commitedConfigurationindex);
            if (commitedConfigurationIdloc == commitedConfigurationId){
                list.remove(i);
            }
        }
    }
    
    public void removeArtifactConfigurationLink(int arrowId, int artifactId, int configurationID) {

        int linkIndexInProject = dataModel.getLinkIndexInProject(arrowId);
        dataModel.getLinks().remove(linkIndexInProject);
        Configuration configuration = dataModel.getConfiguration(configurationID);
        List<Integer> list = configuration.getAuthorIndex();
        removeArtifactFromList(list, artifactId);

    }

    public void removePersonConfigurationLink(int arrowId, int personId, int configurationID) {

        int linkIndexInProject = dataModel.getLinkIndexInProject(arrowId);
        List link = dataModel.getProject().getLinks();
        link.remove(linkIndexInProject);
        Configuration configuration = dataModel.getConfiguration(configurationID);
        List<Integer> list = configuration.getAuthorIndex();
        removePersonFromList(list, personId);

    }
    
    

    public void removeCommitedConfigurationConfigurationLink(int arrowId, int commitedConfigurationId, int configurationID) {

        int linkIndexInProject = dataModel.getLinkIndexInProject(arrowId);
        dataModel.getLinks().remove(linkIndexInProject);
        Configuration configuration = dataModel.getConfiguration(configurationID);
        List<Integer> list = configuration.getCommitedConfiguration();
        removeCommitedConfigurationFromList(list, commitedConfigurationId);

    }

    public void removeCommitCommitedConfigurationLink(int arrowId, int commitedConfigurationId, int configurationID) {

        int linkIndexInProject = dataModel.getLinkIndexInProject(arrowId);
        dataModel.getLinks().remove(linkIndexInProject);
        CommitedConfiguration configuration = dataModel.getCommitedConfiguration(commitedConfigurationId);
        List<Integer> list = configuration.getCommit();
        removeCommitFromList(list, commitedConfigurationId);

    }
    
    public void removeArtifact(int id) {
        dataModel.getArtifacts().remove(dataModel.getArtifactIndexInProject(id));
    }

    public void removeConfiguration(int id){
        int dataModelIndex = dataModel.getConfigurationIndexInProject(id);
        dataModel.getConfigurations().remove(dataModelIndex);
    }

    public void removeWorkUnit(ObservableList<Integer> indexList) {
            for(int i = indexList.size() -1; i >= 0; i-- ){
                dataModel.getWorkUnits().remove((int)indexList.get(i));
            }
    }

    public void removeActivity(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            dataModel.getActivities().remove((int)indexList.get(i));
        }
    }

    public void removeWorkUnitRelation(int startItemId, int endItemId) {

   //     dataModel.getWorkUnits().get(startItemId).setRelationIndex(null);
   //     dataModel.getWorkUnits().get(endItemId).setRelationIndex(null);

    }
    public void removeIteration(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            dataModel.getIterations().remove((int)indexList.get(i));
        }
    }

    public void removePhase(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            dataModel.getPhases().remove((int)indexList.get(i));
        }
    }

    public void removeChange(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            dataModel.getChanges().remove((int)indexList.get(i));
        }
    }

    public void removeCommitedConfiguration(int id){
        int dataModelIndex = dataModel.getCommitedConfigurationIndexInProject(id);
        dataModel.getCommitedConfiguration().remove(dataModelIndex);
    }

    public void removeCommit(int id){
        int dataModelIndex = dataModel.getCommitIndexInProject(id);
        dataModel.getCommits().remove(dataModelIndex);
    }

}
