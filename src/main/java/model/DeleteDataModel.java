package model;

import SPADEPAC.Artifact;
import SPADEPAC.Commit;
import SPADEPAC.CommitedConfiguration;
import SPADEPAC.Configuration;
import interfaces.IDeleteDataModel;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Trida predstavujici implementaci rozhrani IDeleteDataModel
 *
 * @author Václav Janoch
 */
public class DeleteDataModel implements IDeleteDataModel {

    /**
     * instance datoveho modelu
     **/
    private DataModel dataModel;

    /**
     * Konstruktor tridy, zinicializuje globalni promennou
     *
     * @param dataModel instace datoveho modelu
     */
    public DeleteDataModel(DataModel dataModel) {

        this.dataModel = dataModel;
    }

    /**
     * Metoda projde od zadu seznam indexu pro smazani a smaze konkretni instace Type na dane pozici
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    public void removeType(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getTypes().remove((int) indexList.get(i));
        }
    }

    /**
     * Metoda projde od zadu seznam indexu pro smazani a smaze konkretni instace Status na dane pozici
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    public void removeStatus(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getStatuses().remove((int) indexList.get(i));
        }
    }

    /**
     * Metoda projde od zadu seznam indexu pro smazani a smaze konkretni instace Role type na dane pozici
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    public void removeRoleType(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getRoleTypes().remove((int) indexList.get(i));
        }
    }

    /**
     * Metoda projde od zadu seznam indexu pro smazani a smaze konkretni instace Milestone na dane pozici
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    public void removeMilestone(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getMilestones().remove((int) indexList.get(i));
        }
    }

    /**
     * Metoda odebere instaci Person z datoveho modelu dle zadaneho identifikatoru
     *
     * @param id identifikator instace pro smazani
     */
    public void removePerson(int id) {
        int dataModelIndex = dataModel.getPersonIndexInProject(id);
        dataModel.getPersons().remove(dataModelIndex);

    }

    /**
     * Metoda projde od zadu seznam indexu pro smazani a smaze konkretni instace Resolution na dane pozici
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    public void removeResolution(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getResolutions().remove((int) indexList.get(i));
        }
    }

    /**
     * Metoda projde od zadu seznam indexu pro smazani a smaze konkretni instace Relation na dane pozici
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    public void removeRelation(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getRelations().remove((int) indexList.get(i));
        }
    }

    /**
     * Metoda projde od zadu seznam indexu pro smazani a smaze konkretni instace Severity na dane pozici
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    public void removeSeverity(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getSeverities().remove((int) indexList.get(i));
        }
    }

    /**
     * Metoda projde od zadu seznam indexu pro smazani a smaze konkretni instace Priority na dane pozici
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    public void removePriority(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getPriorities().remove((int) indexList.get(i));
        }
    }

    /**
     * Metoda projde od zadu seznam indexu pro smazani a smaze konkretni instace Criterion na dane pozici
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    public void removeCriterion(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getCriterions().remove((int) indexList.get(i));
        }
    }

    /**
     * Metoda projde od zadu seznam indexu pro smazani a smaze konkretni instace Configuration Person na dane pozici
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    public void removeCPR(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getConfigPersonRelations().remove((int) indexList.get(i));
        }
    }

    /**
     * Metoda projde od zadu seznam indexu pro smazani a smaze konkretni instace Branch na dane pozici
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    public void removeBranch(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getBranches().remove((int) indexList.get(i));
        }
    }

    /**
     * Metoda projde od zadu seznam indexu pro smazani a smaze konkretni instace VCSTag na dane pozici
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    public void removeVCSTag(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getVCSTags().remove((int) indexList.get(i));
        }
    }

    /**
     * Metoda odstani instaci link z datoveho modelu a v pripade existence zavisloti mezi elementy smaze spojeni mezi nimi
     *
     * @param arrowId        identifikator spojnice
     * @param personId       identifikator person
     * @param artifactID     identifikatoru artifact
     * @param isModeleDelete informace o tom, zda uz byla spojnice smazana
     */
    public void removePersonArtifactLink(int arrowId, int personId, int artifactID, boolean isModeleDelete) {

        int linkIndexInProject = dataModel.getLinkIndexInProject(arrowId);
        dataModel.getLinks().remove(linkIndexInProject);
        if (!isModeleDelete) {
            Artifact artifact = dataModel.getArtifact(artifactID);
            List<Integer> list = artifact.getAuthorIndex();
            ArrayList<Integer> personIds = removeItemFromList(list, personId);
            for (int i : personIds) {
                removeItemFromList(dataModel.getPerson(i).getArtifacts(), artifactID);
            }
        }

    }

    /**
     * Metoda odstani instaci link z datoveho modelu a v pripade existence zavisloti mezi elementy smaze spojeni mezi nimi
     *
     * @param arrowId       identifikator spojnice
     * @param personId      identifikator person
     * @param committedID   identifikatoru committed configuration
     * @param isModelDelete informace o tom, zda uz byla spojnice smazana
     */
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

    /**
     * Metoda odstani instaci link z datoveho modelu a v pripade existence zavisloti mezi elementy smaze spojeni mezi nimi
     *
     * @param arrowId       identifikator spojnice
     * @param personId      identifikator person
     * @param commitID      identifikatoru commit
     * @param isModelDelete informace o tom, zda uz byla spojnice smazana
     */
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

    /**
     * Metoda pro odstraneni identifikatoru jednoho elementu z druheho
     *
     * @param list     list identifikatoru
     * @param commitId identifikator prvku
     * @return seznam smazanych indexu
     */
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

    /**
     * Metoda odstani instaci link z datoveho modelu a v pripade existence zavisloti mezi elementy smaze spojeni mezi nimi
     *
     * @param arrowId         identifikator spojnice
     * @param artifactId      identifikator artifact
     * @param configurationID identifikatoru configuration
     * @param isModelDelete   informace o tom, zda uz byla spojnice smazana
     */
    public void removeArtifactConfigurationLink(int arrowId, int artifactId, int configurationID, boolean isModelDelete) {

        int linkIndexInProject = dataModel.getLinkIndexInProject(arrowId);
        dataModel.getLinks().remove(linkIndexInProject);
        if (!isModelDelete) {
            Configuration configuration = dataModel.getConfiguration(configurationID);
            List<Integer> list = configuration.getArtifactsIndexs();
            ArrayList<Integer> Ids = removeItemFromList(list, artifactId);
            for (int i : Ids) {
                removeItemFromList(dataModel.getArtifact(i).getConfigurations(), configurationID);
            }
        }


    }

    /**
     * Metoda odstani instaci link z datoveho modelu a v pripade existence zavisloti mezi elementy smaze spojeni mezi nimi
     *
     * @param arrowId         identifikator spojnice
     * @param personId        identifikator person
     * @param configurationId identifikatoru configuration
     * @param isModelDelete   informace o tom, zda uz byla spojnice smazana
     */
    public void removePersonConfigurationLink(int arrowId, int personId, int configurationId, boolean isModelDelete) {

        int linkIndexInProject = dataModel.getLinkIndexInProject(arrowId);
        List link = dataModel.getProject().getLinks();
        link.remove(linkIndexInProject);
        if (!isModelDelete) {
            Configuration configuration = dataModel.getConfiguration(configurationId);
            List<Integer> list = configuration.getAuthorIndex();
            ArrayList<Integer> personIds = removeItemFromList(list, personId);
            for (int i : personIds) {
                removeItemFromList(dataModel.getPerson(i).getConfigurations(), configurationId);
            }
        }

    }

    /**
     * Metoda odstani instaci link z datoveho modelu a v pripade existence zavisloti mezi elementy smaze spojeni mezi nimi
     *
     * @param arrowId                 identifikator spojnice
     * @param commitedConfigurationId identifikator committed configuration
     * @param configurationID         identifikatoru configuration
     * @param isModelDelete           informace o tom, zda uz byla spojnice smazana
     */
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

    /**
     * Metoda odstani instaci link z datoveho modelu a v pripade existence zavisloti mezi elementy smaze spojeni mezi nimi
     *
     * @param arrowId                 identifikator spojnice
     * @param commitedConfigurationId identifikator committed configuration
     * @param commitID                identifikatoru commit
     * @param isModelDelete           informace o tom, zda uz byla spojnice smazana
     */
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

    /**
     * Metoda odebere instaci Artifact z datoveho modelu dle zadaneho identifikatoru
     *
     * @param id identifikator instace pro smazani
     */
    public void removeArtifact(int id) {
        dataModel.getArtifacts().remove(dataModel.getArtifactIndexInProject(id));
    }

    /**
     * Metoda odebere instaci Configuration z datoveho modelu dle zadaneho identifikatoru
     *
     * @param id identifikator instace pro smazani
     */
    public void removeConfiguration(int id) {
        int dataModelIndex = dataModel.getConfigurationIndexInProject(id);
        dataModel.getConfigurations().remove(dataModelIndex);
    }

    /**
     * Metoda projde od zadu seznam indexu pro smazani a smaze konkretni instace Worku Unit na dane pozici
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    public void removeWorkUnit(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getWorkUnits().remove((int) indexList.get(i));
        }
    }

    /**
     * Metoda projde od zadu seznam indexu pro smazani a smaze konkretni instace Activity na dane pozici
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    public void removeActivity(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getActivities().remove((int) indexList.get(i));
        }
    }

    /**
     * Metoda projde od zadu seznam indexu pro smazani a smaze konkretni instace Iteration na dane pozici
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    public void removeIteration(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getIterations().remove((int) indexList.get(i));
        }
    }

    /**
     * Metoda projde od zadu seznam indexu pro smazani a smaze konkretni instace Phase na dane pozici
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    public void removePhase(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getPhases().remove((int) indexList.get(i));
        }
    }

    /**
     * Metoda projde od zadu seznam indexu pro smazani a smaze konkretni instace Change na dane pozici
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    public void removeChange(ObservableList<Integer> indexList) {
        for (int i = indexList.size() - 1; i >= 0; i--) {
            dataModel.getChanges().remove((int) indexList.get(i));
        }
    }

    /**
     * Metoda odebere instaci Committed Configuration z datoveho modelu dle zadaneho identifikatoru
     *
     * @param id identifikator instace pro smazani
     */
    public void removeCommitedConfiguration(int id) {
        int dataModelIndex = dataModel.getCommitedConfigurationIndexInProject(id);
        dataModel.getCommitedConfiguration().remove(dataModelIndex);
    }

    /**
     * Metoda odebere instaci Commit z datoveho modelu dle zadaneho identifikatoru
     *
     * @param id identifikator instace pro smazani
     */
    public void removeCommit(int id) {
        int dataModelIndex = dataModel.getCommitIndexInProject(id);
        dataModel.getCommits().remove(dataModelIndex);
    }

}
