package model;

import SPADEPAC.*;
import interfaces.IDeleteDataModel;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class DeleteDataModel implements IDeleteDataModel {

    private DataModel dataModel;

    public DeleteDataModel(DataModel dataModel) {

        this.dataModel = dataModel;
    }

    public void removeType(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getTypes().remove((int) indexList.get(i));
        }
    }

    public void removeStatus(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getStatuses().remove((int) indexList.get(i));
        }
    }


    public void removeRoleType(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getRoleTypes().remove((int) indexList.get(i));
        }
    }

    public void removeMilestone(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getMilestones().remove((int) indexList.get(i));
        }
    }

    public void removeRole(int id) {
        int dataModelIndex = dataModel.getRoleIndexInProject(id);
        dataModel.getPersons().remove(dataModelIndex);

    }

    public void removeResolution(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getResolutions().remove((int) indexList.get(i));
        }
    }

    public void removeRelation(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getRelations().remove((int) indexList.get(i));
        }
    }

    public void removeSeverity(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getSeverities().remove((int) indexList.get(i));
        }
    }

    public void removePriority(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getPriorities().remove((int) indexList.get(i));
        }
    }

    public void removeCriterion(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getCriterions().remove((int) indexList.get(i));
        }
    }

    public void removeCPR(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getConfigPersonRelations().remove((int) indexList.get(i));
        }
    }

    public void removeBranch(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getBranches().remove((int) indexList.get(i));
        }
    }

    public void removeVCSTag(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getVCSTags().remove((int) indexList.get(i));
        }
    }

    public void removePersonArtifactLink(int arrowId, int personId, int artifactID, boolean isModeleDelete) {

        int linkIndexInProject = dataModel.getLinkIndexInProject(arrowId);
        dataModel.getLinks().remove(linkIndexInProject);
       if (!isModeleDelete){
           Artifact artifact = dataModel.getArtifact(artifactID);
           List<Integer> list = artifact.getAuthorIndex();
           ArrayList<Integer> personIds = removeItemFromList(list, personId);
           for (int i : personIds){
               removeItemFromList(dataModel.getPerson(i).getArtifacts(), artifactID);
           }
       }

    }

    public void removePersonCommittedConfigurationLink(int arrowId, int personId, int committedID, boolean isModelDelete) {

        int linkIndexInProject = dataModel.getLinkIndexInProject(arrowId);
        dataModel.getLinks().remove(linkIndexInProject);
        if (!isModelDelete) {
            CommitedConfiguration committedConfiguration = dataModel.getCommitedConfiguration(committedID);
            List<Integer> list = committedConfiguration.getRole();
            ArrayList<Integer> personIds = removeItemFromList(list, personId);
            for (int i : personIds) {
                removeItemFromList(dataModel.getPerson(i).getCommittedConfiguration(), committedID);
            }
        }
    }

    public void removePersonCommitLink(int arrowId, int personId, int commitID, boolean isModelDelete) {

        int linkIndexInProject = dataModel.getLinkIndexInProject(arrowId);
        dataModel.getLinks().remove(linkIndexInProject);
        if (!isModelDelete) {
            Commit commit = dataModel.getCommit(commitID);
            List<Integer> list = commit.getAuthor();
            ArrayList<Integer> personIds = removeItemFromList(list, personId);
            for (int i : personIds) {
                removeItemFromList(dataModel.getPerson(i).getCommit(), commitID);
            }
        }
    }

    public ArrayList<Integer> removeItemFromList(List<Integer> list, int commitId) {
        ArrayList<Integer> deleteIds = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            int commitIndex = list.get(i);
            if (commitIndex == commitId) {
                list.remove(i);
                deleteIds.add(commitIndex);
            }
        }
        return deleteIds;
    }

    public void removeArtifactConfigurationLink(int arrowId, int artifactId, int configurationID, boolean isModelDelete) {

        int linkIndexInProject = dataModel.getLinkIndexInProject(arrowId);
        dataModel.getLinks().remove(linkIndexInProject);
        if (!isModelDelete){
            Configuration configuration = dataModel.getConfiguration(configurationID);
            List<Integer> list = configuration.getArtifactsIndexs();
            ArrayList<Integer> Ids = removeItemFromList(list, artifactId);
            for (int i : Ids){
                removeItemFromList(dataModel.getArtifact(i).getConfigurations(), configurationID);
            }
        }


    }

    public void removePersonConfigurationLink(int arrowId, int personId, int configurationID, boolean isModelDelete) {

        int linkIndexInProject = dataModel.getLinkIndexInProject(arrowId);
        List link = dataModel.getProject().getLinks();
        link.remove(linkIndexInProject);
        if(!isModelDelete) {
            Configuration configuration = dataModel.getConfiguration(configurationID);
            List<Integer> list = configuration.getAuthorIndex();
            ArrayList<Integer> personIds = removeItemFromList(list, personId);
            for (int i : personIds) {
                removeItemFromList(dataModel.getPerson(i).getConfigurations(), configurationID);
            }
        }

    }


    public void removeCommitedConfigurationConfigurationLink(int arrowId, int commitedConfigurationId, int configurationID, boolean isModelDelete) {

        int linkIndexInProject = dataModel.getLinkIndexInProject(arrowId);
        dataModel.getLinks().remove(linkIndexInProject);
        if (!isModelDelete) {
            Configuration configuration = dataModel.getConfiguration(configurationID);
            List<Integer> list = configuration.getCommitedConfiguration();
            ArrayList<Integer> personIds = removeItemFromList(list, commitedConfigurationId);
            for (int i : personIds) {
                removeItemFromList(dataModel.getConfiguration(i).getCommitedConfiguration(), commitedConfigurationId);
            }
        }
    }

    public void removeCommitCommitedConfigurationLink(int arrowId, int commitedConfigurationId, int commitID, boolean isModelDelete) {

        int linkIndexInProject = dataModel.getLinkIndexInProject(arrowId);
        dataModel.getLinks().remove(linkIndexInProject);
        if (isModelDelete) {
            CommitedConfiguration configuration = dataModel.getCommitedConfiguration(commitedConfigurationId);
            List<Integer> list = configuration.getCommit();
            ArrayList<Integer> personIds = removeItemFromList(list, commitID);
            for (int i : personIds) {
                removeItemFromList(dataModel.getCommit(i).getCommitedConfiguration(), commitedConfigurationId);
            }
        }
    }

    public void removeArtifact(int id) {
        dataModel.getArtifacts().remove(dataModel.getArtifactIndexInProject(id));
    }

    public void removeConfiguration(int id) {
        int dataModelIndex = dataModel.getConfigurationIndexInProject(id);
        dataModel.getConfigurations().remove(dataModelIndex);
    }

    public void removeWorkUnit(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getWorkUnits().remove((int) indexList.get(i));
        }
    }

    public void removeActivity(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getActivities().remove((int) indexList.get(i));
        }
    }

    public void removeWorkUnitRelation(int startItemId, int endItemId) {

        //     dataModel.getWorkUnits().get(startItemId).setRelationIndex(null);
        //     dataModel.getWorkUnits().get(endItemId).setRelationIndex(null);

    }

    public void removeIteration(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getIterations().remove((int) indexList.get(i));
        }
    }

    public void removePhase(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getPhases().remove((int) indexList.get(i));
        }
    }

    public void removeChange(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getChanges().remove((int) indexList.get(i));
        }
    }

    public void removeCommitedConfiguration(int id) {
        int dataModelIndex = dataModel.getCommitedConfigurationIndexInProject(id);
        dataModel.getCommitedConfiguration().remove(dataModelIndex);
    }

    public void removeCommit(int id) {
        int dataModelIndex = dataModel.getCommitIndexInProject(id);
        dataModel.getCommits().remove(dataModelIndex);
    }

}
