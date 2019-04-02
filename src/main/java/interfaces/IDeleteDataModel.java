package interfaces;

import SPADEPAC.Configuration;
import javafx.collections.ObservableList;

import java.util.ArrayList;

  public interface IDeleteDataModel {

      void removeChange(ObservableList<Integer> indexList);
      void removeType(ObservableList<Integer> indexList);
      void removeStatus(ObservableList<Integer> indexList);
      void removeTag(ArrayList<Integer> indexList, int configId);
      void removeRoleType(ObservableList<Integer> indexList);
      void removeMilestone(ObservableList<Integer> indexList);
      void removeRole(int id);
      void removeResolution(ObservableList<Integer> indexList);
      void removeRelation(ObservableList<Integer> indexList);
      void removeSeverity(ObservableList<Integer> indexList);
      void removePriority(ObservableList<Integer> indexList);
      void removeCriterion(ObservableList<Integer> indexList);
      void removeCPR(ObservableList<Integer> indexList);
      void removeBranch(ObservableList<Integer> indexList);
      void removeVCSTag(ObservableList<Integer> indexList);
      void removeArtifactChangeLink(int artifactID, int changeID);
      void removeArtifact(int id);
      void removeConfiguration(int id);
      void removeWorkUnit(ObservableList<Integer> indexList);
      void removeActivity(ObservableList<Integer> indexList);
      void removeWorkUnitRelation(int startItemId, int endItemId);
      void removeIteration(ObservableList<Integer> indexList);
      void removePhase(ObservableList<Integer> indexList);

      void removeCommitedConfiguration(int id);

      void removeCommit(int id);
  }
