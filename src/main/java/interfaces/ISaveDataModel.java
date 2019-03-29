package interfaces;

import SPADEPAC.*;

import java.util.ArrayList;

  public interface ISaveDataModel {

      void createChangeArtifactRelation(int artifactIndex, int changeIndex);
      void createCommitToCommitedConfigurationRelation(int startId, int endId);

      void createWorkUnitRelation(int startIndex, Integer endIndex);

      void createNewPhase(int id);
      void createNewIteration(int id);
      void createNewActivity(int id);
      void createNewWorkUnit(int id);
      void createNewConfiguration(int id);
      void createNewChange(int id);
      void createNewArtifact(int id);
      void createNewBranch(String nameForManipulator, int id, boolean isMain);
      void createNewCPR(int id);
      void createNewCriterion(String nameForManipulator, String descForManipulator, int id);
      void createNewPriority(String nameForManipulator, String classST, String superST, int id);
      void createNewSeverity(String nameForManipulator, String classST, String superST, int id);
      void createNewRelation(String nameForManipulator, String classST, String superST, int id);
      void createNewResolution(String nameForManipulator, String classST, String superST, int id);
      void crateNewRole(int id);
      void createNewMilestone(String nameForManipulator, String description, ArrayList<Integer> criterionIndex, int id);
      void createNewRoleType(String nameForManipulator, String classST, String superST, int id);
      void addTagToConfiguration(String tag, int configId, int index);
      void crateNewStatus(String nameForManipulator, String classST, String superST, int id);
      void createNewType(String nameForManipulator, String classST, String superST, int id);

      void createNewVCSTag(int id);

      void createNewCommit(int id);

      void createNewCommitedConfiguration(int id);
  }
