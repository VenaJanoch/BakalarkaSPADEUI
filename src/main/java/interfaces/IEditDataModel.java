package interfaces;

import SPADEPAC.*;
import services.SegmentType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface IEditDataModel {

      void editDataInCPR(ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicators, ArrayList<Integer> roleIndex, ArrayList<Integer> roleIndicator, int id);
      void editDataInBranch(ArrayList<String> nameForManipulator, ArrayList<Integer> indicators, boolean isMainBranch, int id);
      void editDataInArtifact(ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicators,
                              ArrayList<String> descForManipulator, ArrayList<Integer> descriptionIndicators,
                              ArrayList<LocalDate> createdDate, ArrayList<Integer> dateIndicator, boolean isCreate,
                              ArrayList<Integer> authorIndex, ArrayList<Integer> typeIndex, ArrayList<Integer> authorIndicator,
                              ArrayList<Integer> typeIndicator, int instanceCount, int id);
      void editDataInChange( ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator,
                             ArrayList<Integer> nameIndicators, ArrayList<Integer> descIndicator, boolean selected, int id);
      void editDataInPhase(ArrayList<String> actName, ArrayList<LocalDate> endDateL, ArrayList<String> desc,
                           ArrayList<Integer> confIndex, ArrayList<Integer> milestoneIndex,  ArrayList<ArrayList<Integer>> workUnitIndexList,
                           ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                           ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator, ArrayList<Integer> milestoneIndicator, int id);
      void editDataInIteration(ArrayList<String> actName, ArrayList<LocalDate> endDateL,  ArrayList<LocalDate> startDateL, ArrayList<String> desc,
                               ArrayList<Integer> confIndex,  ArrayList<ArrayList<Integer>> workUnitIndexList,
                               ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                               ArrayList<Integer> startDateIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator, int id);
      void editDataInActivity(ArrayList<String> nameForManipulator, ArrayList<String> descriptionForManipulator, ArrayList<ArrayList<Integer>> setOfItemOnCanvas,
                              ArrayList<Integer> nameIndicators,  ArrayList<Integer> descIndicators,  ArrayList<Integer> workUnitIndicators, int id);
      void editDataInWorkUnit(List<String> nameForManipulator, List<String> description, List<String> categoryForManipulator,
                              ArrayList<Integer> assigneIndex, ArrayList<Integer> authorIndex, ArrayList<Integer> priorityIndex, ArrayList<Integer> severityIndex,
                              ArrayList<Integer> typeIndex, ArrayList<Integer> resolutionIndex, ArrayList<Integer> statusIndex,
                              ArrayList<Double> estimateForDataManipulator, List<Integer> nameIndicator, List<Integer> descriptionIndicator, List<Integer> categoryIndicator,
                              ArrayList<Integer> assigneIndicator, ArrayList<Integer> authorIndicator, ArrayList<Integer> priorityIndicator, ArrayList<Integer> severityIndicator,
                              ArrayList<Integer> typeIndicator, ArrayList<Integer> resolutionIndicator, ArrayList<Integer> statusIndicator,
                              ArrayList<Integer> estimateIndicator, boolean isExist, ArrayList<Integer> relations,  ArrayList<ArrayList<Integer>> workUnits, int id);
      void editDataInConfiguration(ArrayList<String> actName, ArrayList<LocalDate> createDate,
                                   boolean isRelease, ArrayList<Integer> authorIndex, ArrayList<ArrayList<Integer>> cprs,
                                   ArrayList<ArrayList<Integer>> branches, ArrayList<ArrayList<Integer>> changeIndexs,
                                   ArrayList<Integer> cprIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> createdIndicator,
                                   ArrayList<Integer> authorIndicator, ArrayList<Integer> branchIndicator, ArrayList<Integer> changeIndicator, int instanceCount, int id);
      void editDataInCriterion( ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator,
                                ArrayList<Integer> nameIndicator, ArrayList<Integer> descIndicator, int id);
      void editDataInPriority(ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                              ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, int id);
      void editDataInSeverity(ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                              ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, int id);
      void editDataInRelation(ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                              ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, int id);
      void editDataInResolution(ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, int id);
      void editDataInRole(ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator, ArrayList<Integer> type,
                          ArrayList<Integer> nameIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> typeIndicator, int instanceCount, int id);
      void editDataInMilestone(ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator,
                               ArrayList<Integer> nameIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> criterionIndicator,
                               ArrayList<ArrayList<Integer>> criterionIndex, int id);
      void editDataInRoleType(ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                              ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, int id);
      void editDataInStatus(ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                            ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, int id);
      void editDataInType(ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                          ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, int id);
      void updateItemList(SegmentType formType, SegmentType elementType, ArrayList<Integer> elementIndexList);
      void editTagInConfiguration(String tag, int configId, int id);

    void editDataInVCSTag(ArrayList<String> nameForManipulator, ArrayList<String> descriptionForManipulator,
                          ArrayList<Integer> nameIndicator, ArrayList<Integer> descriptionIndicator, int id);

    void editDataInCommit(ArrayList<String> nameForManipulator,  ArrayList<Integer> nameIndicator, boolean release,int instanceCount, int id);

      void editDataInCommitedConfiguration(ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                           ArrayList<LocalDate> dateFromDatePicker, ArrayList<Integer> dateIndicator, int instanceCount, int id);

    void editDataInProject(ArrayList<String> nameForManipulator, ArrayList<LocalDate> startDate1, ArrayList<LocalDate> endDate1, ArrayList<String> descriptionForManipulator, ArrayList<ArrayList<Integer>> workUnitsForManipulator,
                           ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicators, ArrayList<Integer> date1Indicators,
                           ArrayList<Integer> date2Indicators, ArrayList<Integer> descIndicators);
}
