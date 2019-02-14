package interfaces;

import SPADEPAC.Configuration;
import javafx.collections.ObservableList;

import java.util.ArrayList;

  public interface IDeleteDataModel {

      void removeChange(int id);
      void removeType(ObservableList<Integer> indexList);
      void removeStatus(ObservableList<Integer> indexList);
      void removeTag(ArrayList<Integer> indexList, int configId);
      void removeRoleType(ObservableList<Integer> indexList);
      void removeMilestone(ObservableList<Integer> indexList);
      void removeRole(ObservableList<Integer> indexList);
      void removeResolution(ObservableList<Integer> indexList);
      void removeRelation(ObservableList<Integer> indexList);
      void removeSeverity(ObservableList<Integer> indexList);
      void removePriority(ObservableList<Integer> indexList);
      void removeCriterion(ObservableList<Integer> indexList);
      void removeCPR(ObservableList<Integer> indexList);
      void removeBranch(ObservableList<Integer> indexList);
      void removeArtifactChangeLink(int artifactID, int changeID);
      void removeArtifact(int indexForm);
      void removeConfiguration(ObservableList<Integer> indexList);
      void removeWorkUnit(int indexForm);
      void removeActivity(int id);
      void removeWorkUnitRelation(int startItemId, int endItemId);
      void removeIteration(int id);
      void removePhase(int id);
}
