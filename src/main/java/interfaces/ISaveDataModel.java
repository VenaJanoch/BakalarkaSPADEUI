package interfaces;

import SPADEPAC.*;

import java.util.ArrayList;

public interface ISaveDataModel {

    void createNewPhase(int id);

    void createNewIteration(int id);

    void createNewActivity(int id);

    void createNewWorkUnit(int id);

    void createNewConfiguration(int id);

    void createNewChange(int id);

    void createNewArtifact(int id);

    void createNewBranch(int id);

    void createNewCPR(int id);

    void createNewCriterion(int id);

    void createNewPriority(int id);

    void createNewSeverity(int id);

    void createNewRelation(int id);

    void createNewResolution(int id);

    void createNewPerson(int id);

    void createNewMilestone(int id);

    void createNewRoleType(int id);

    void createNewStatus(int id);

    void createNewType(int id);

    void createNewVCSTag(int id);

    void createNewCommit(int id);

    void createNewCommitedConfiguration(int id);

    void createCommitedConfigurationToConfigurationRelation(int linkId, Integer startId, Integer endId);

    void createCommitToCommitedConfigurationRelation(int linkId, int startId, int endId);

    void createNewArtifacToConfigurationRelation(int linkId, int startId, int endId);

    void createNewPersonToConfigurationRelation(int linkId, int startId, int endId);

    void createNewPersonToArtifactRelation(int linkId, int startId, int endId);

    void createNewPersonToCommitRelation(int linkId, int startId, int endId);

    void createNewPersonToCommittedConfigurationRelation(int linkId, int startId, int endId);

}
