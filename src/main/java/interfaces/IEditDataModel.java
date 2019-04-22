package interfaces;

import SPADEPAC.*;
import services.SegmentType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface IEditDataModel {

      void editDataInCPR(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicators, ArrayList<Integer> roleIndex, ArrayList<Integer> roleIndicator, boolean exist, int id);
      void editDataInBranch(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> indicators, boolean isMainBranch, boolean exist, int id);
      void editDataInArtifact(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicators,
                              ArrayList<String> descForManipulator, ArrayList<Integer> descriptionIndicators,
                              ArrayList<LocalDate> createdDate, ArrayList<Integer> dateIndicator, boolean isCreate,
                              ArrayList<Integer> authorIndex, ArrayList<Integer> typeIndex, ArrayList<Integer> authorIndicator,
                              ArrayList<Integer> typeIndicator, int instanceCount, int id);
      void editDataInChange(String alias, ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator, ArrayList<Integer> artifactForManipulator,
                             ArrayList<Integer> nameIndicators, ArrayList<Integer> descIndicator, boolean selected, int id);
      void editDataInPhase(String alias, ArrayList<String> actName, ArrayList<LocalDate> endDateL, ArrayList<String> desc,
                           ArrayList<Integer> confIndex, ArrayList<Integer> milestoneIndex,  ArrayList<ArrayList<Integer>> workUnitIndexList,
                           ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                           ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator, ArrayList<Integer> milestoneIndicator, boolean exist, int id);
      void editDataInIteration(String alias, ArrayList<String> actName, ArrayList<LocalDate> endDateL,  ArrayList<LocalDate> startDateL, ArrayList<String> desc,
                               ArrayList<Integer> confIndex,  ArrayList<ArrayList<Integer>> workUnitIndexList,
                               ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                               ArrayList<Integer> startDateIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator, boolean exist, int id);
      void editDataInActivity(String alias, ArrayList<String> nameForManipulator, ArrayList<String> descriptionForManipulator, ArrayList<ArrayList<Integer>> setOfItemOnCanvas,
                              ArrayList<Integer> nameIndicators,  ArrayList<Integer> descIndicators,  ArrayList<Integer> workUnitIndicators,  ArrayList<LocalDate> endDate,
                              ArrayList<Integer> endDateIndicators, boolean exist, int id);
      void editDataInWorkUnit(String alias, List<String> nameForManipulator, List<String> description, List<String> categoryForManipulator,
                              ArrayList<Integer> assigneIndex, ArrayList<Integer> authorIndex, ArrayList<Integer> priorityIndex, ArrayList<Integer> severityIndex,
                              ArrayList<Integer> typeIndex, ArrayList<Integer> resolutionIndex, ArrayList<Integer> statusIndex,
                              ArrayList<Double> estimateForDataManipulator, List<Integer> nameIndicator, List<Integer> descriptionIndicator, List<Integer> categoryIndicator,
                              ArrayList<Integer> assigneIndicator, ArrayList<Integer> authorIndicator, ArrayList<Integer> priorityIndicator, ArrayList<Integer> severityIndicator,
                              ArrayList<Integer> typeIndicator, ArrayList<Integer> resolutionIndicator, ArrayList<Integer> statusIndicator,
                              ArrayList<Integer> estimateIndicator, boolean isExist, ArrayList<Integer> relations,  ArrayList<ArrayList<Integer>> workUnits, int id);
      void editDataInConfiguration(String alias, ArrayList<String> actName, ArrayList<LocalDate> createDate,
                                   boolean isRelease, ArrayList<Integer> authorIndex, ArrayList<ArrayList<Integer>> cprs,
                                  ArrayList<ArrayList<Integer>> changeIndexs,
                                   ArrayList<Integer> cprIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> createdIndicator,
                                   ArrayList<Integer> authorIndicator, ArrayList<Integer> changeIndicator,
                                   int instanceCount, boolean exist, int id);
      void editDataInCriterion(String alias,  ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator,
                                ArrayList<Integer> nameIndicator, ArrayList<Integer> descIndicator, boolean exist, int id);
      void editDataInPriority(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                              ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, boolean exist, int id);
      void editDataInSeverity(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                              ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, boolean exist, int id);
      void editDataInRelation(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                              ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, boolean exist, int id);
      void editDataInResolution(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, boolean exist, int id);
      void editDataInRole(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> type, ArrayList<Integer> nameIndicator,
                          ArrayList<Integer> typeIndicator, int instanceCount, boolean exist, int id);
      void editDataInMilestone(String alias, ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator,
                               ArrayList<Integer> nameIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> criterionIndicator,
                               ArrayList<ArrayList<Integer>> criterionIndex, boolean exist, int id);
      void editDataInRoleType(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator, ArrayList<String> descForManipulator, ArrayList<Integer> descIndicator,
                              ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, boolean exist, int id);
      void editDataInStatus(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                            ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, boolean exist, int id);
      void editDataInType(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                          ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, boolean exist, int id);
      void updateItemList(SegmentType formType, SegmentType elementType, ArrayList<Integer> elementIndexList);
    void updateItemList(SegmentType formType, SegmentType elementType, int elementIndexList);
      void editTagInConfiguration(String tag, int configId, int id);

    void editDataInVCSTag(String alias, ArrayList<String> nameForManipulator, ArrayList<String> descriptionForManipulator,
                          ArrayList<Integer> nameIndicator, ArrayList<Integer> descriptionIndicator, boolean exist, int id);

    void editDataInCommit(String alias, ArrayList<String> nameForManipulator,  ArrayList<Integer> nameIndicator,  ArrayList<Integer> tag, ArrayList<Integer> tagIndicator,
                          ArrayList<ArrayList<Integer>> branches, ArrayList<Integer> branchIndicator, boolean release,int instanceCount, boolean exist, int id);

      void editDataInCommitedConfiguration(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                           ArrayList<LocalDate> dateFromDatePicker, ArrayList<Integer> dateIndicator, int instanceCount, boolean exist, int id);

    void editDataInProject(ArrayList<String> nameForManipulator, ArrayList<LocalDate> startDate1, ArrayList<LocalDate> endDate1, ArrayList<String> descriptionForManipulator, ArrayList<ArrayList<Integer>> workUnitsForManipulator,
                           ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicators, ArrayList<Integer> date1Indicators,
                           ArrayList<Integer> date2Indicators, ArrayList<Integer> descIndicators);

    void editCoordinatesInCommit(int x, int y, int id);
      void editCoordinatesInCommitedConfiguration(int x, int y, int id);
      void editCoordinatesInConfiguration(int x, int y, int id);
      void editCoordinatesInArtifact(int x, int y, int id);
      void editCoordinatesInRole(int x, int y, int id);
}
