package interfaces;

import services.SegmentType;
import tables.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface IEditFormController {

   void editDataFromClass(SegmentType segmentType, ArrayList<String> name, ArrayList<Integer> nameIndicator,  ArrayList<Integer> classIndices,  ArrayList<Integer> superClassIndices, ArrayList<String> classString,  ArrayList<String> superSting
           , ClassTable classTable, boolean exist, int id);
 //   void editDataFromResolution(ArrayList<String> name, ArrayList<Integer> nameIndicator, String className, String superClassName, ClassTable classTable, int id);
 //   void editDataFromRelation(ArrayList<String> name, ArrayList<Integer> nameIndicator, String className, String superClassName, ClassTable classTable, int id);
 //void editDataFromType(ArrayList<String> name, ArrayList<Integer> nameIndicator, String className, String superClassName, ClassTable classTable, int id);
// void editDataFromStatus(ArrayList<String> name, ArrayList<Integer> nameIndicator, String className, String superClassName, ClassTable classTable, int id);
//   void editDataFromPriority(ArrayList<String> name, ArrayList<Integer> nameIndicator, String className, String superClassName, ClassTable classTable, int id);
//   void editDataFromTag(String tag, TagTable tagTable, int configFormId, int id);
 //void editDataFromRoleType(ArrayList<String> name, ArrayList<Integer> nameIndicator, String className, String superClassName, ClassTable classTable, int id);
   void editDataFromPerson(ArrayList<String> name, ArrayList<Integer> nameIndicator, String count, ArrayList<Integer> roleTypeIndex,
                           ArrayList<Integer> roleTypeIndicators, PersonTable personTable, boolean exist, int id);
//    private void editDataFromSeverity(String nameST, String className, String superClassName, ClassTable classTable, int id);
   void editDataFromMilestone(ArrayList<String> nameST, ArrayList<Integer> nameIndicators, ArrayList<String>description, ArrayList<Integer> descriptionIndicators,
                              MilestoneTable milestoneTable, ArrayList<ArrayList<Integer>> criterionIndex,
                              ArrayList<Integer> criterionIndicators, boolean exist,  int id);
   void editDataFromCriterion(ArrayList<String> nameST, ArrayList<Integer> nameIndicators, ArrayList<String>description, ArrayList<Integer> descriptionIndicators,
                              CriterionTable criterionTable, boolean exist, int id);
   void editDataFromCPR(ArrayList<String> nameST, ArrayList<Integer> nameIndicators, ArrayList<Integer> roleIndex,
                        ArrayList<Integer> roleIndicators, boolean exist, CPRTable cprTable);
   void editDataFromBranch(ArrayList<String> nameST, ArrayList<Integer> nameIndicators,  boolean isMainBranch, boolean exist, BranchTable branchTable);

    void editDataFromPhase(ArrayList<String> actName, ArrayList<LocalDate> endDateL, ArrayList<String> desc,
                           ArrayList<Integer> confIndex, ArrayList<Integer> milestoneIndex,  ArrayList<ArrayList<Integer>> workUnitIndexList,
                           ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                           ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator, ArrayList<Integer> milestoneIndicator,
                           PhaseTable phaseTable, boolean exist, int id);

    void editDataFromIteration(ArrayList<String> actName, ArrayList<LocalDate> endDateL,  ArrayList<LocalDate> startDateL, ArrayList<String> desc,
                               ArrayList<Integer> confIndex,  ArrayList<ArrayList<Integer>> workUnitIndexList,
                               ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                               ArrayList<Integer> startDateIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator,
                               IterationTable iterationTable, boolean exist, int id);
    void editDataFromActivity(ArrayList<String> name, ArrayList<String> description,  ArrayList<ArrayList<Integer>> workUnits,
                              ArrayList<Integer> nameIndicators,  ArrayList<Integer> descIndicators,  ArrayList<Integer> workUnitIndicators,
                              ArrayList<LocalDate> endDate,  ArrayList<Integer> endDateIndicators, ActivityTable activityTable, boolean exist, int id);

    void editDataFromChange(ArrayList<String> name, ArrayList<Integer> nameIndicator,  ArrayList<String> description, ArrayList<Integer> artifacts,
                            ArrayList<Integer> descriptionIndicator, boolean exist, ChangeTable changeTable,
                            int id);

    void editDataFromArtifact(ArrayList<String> name, ArrayList<String> description, boolean exist,
                              ArrayList<Integer> roleIndex, ArrayList<Integer> typeIndex, ArrayList<LocalDate> localDate,
                              ArrayList<Integer> nameIndicator, ArrayList<Integer> descriptionIndicator,
                              ArrayList<Integer> roleIndicator,  ArrayList<Integer> typeIndicator, ArrayList<Integer> dateIndicator,
                              ArtifactTable artifactTable, String count, int id);

    void editDataFromWorkUnit(ArrayList<String> name, ArrayList<String> description, ArrayList<String> category,
                              ArrayList<Integer> assigneIndex, ArrayList<Integer> authorIndex, ArrayList<Integer> priorityIndex, ArrayList<Integer> severityIndex,
                              ArrayList<Integer> typeIndex, ArrayList<Integer> resolutionIndex, ArrayList<Integer> statusIndex,
                              ArrayList<String> estimatedTime, List<Integer> nameIndicator, List<Integer> descriptionIndicator, List<Integer> categoryIndicator,
                              ArrayList<Integer> assigneIndicator, ArrayList<Integer> authorIndicator, ArrayList<Integer> priorityIndicator, ArrayList<Integer> severityIndicator,
                              ArrayList<Integer> typeIndicator, ArrayList<Integer> resolutionIndicator, ArrayList<Integer> statusIndicator,
                              ArrayList<Integer> estimateIndicator, boolean isExist, ArrayList<Integer> relations,  ArrayList<ArrayList<Integer>> workUnits, WorkUnitTable workUnitTable, int id);

    void editDataFromConfiguration(ArrayList<String> actName, ArrayList<LocalDate> createDate,
                                   boolean isRelease, ArrayList<Integer> authorIndex, ArrayList<ArrayList<Integer>> cprs,
                                    ArrayList<ArrayList<Integer>> changeIndexs,
                                   ArrayList<Integer> cprIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> createdIndicator,
                                   ArrayList<Integer> authorIndicator, ArrayList<Integer> changeIndicator,
                                   String instanceCount, boolean exist, int configId);

    void editDataFromVCSTag(ArrayList<String> name, ArrayList<String> description,
                            ArrayList<Integer> nameIndicator, ArrayList<Integer> descriptionIndicator, VCSTagTable tagTable, boolean exist, int id);

    void editDataFromCommit(ArrayList<String> name, ArrayList<Integer> nameIndicator, ArrayList<Integer> tag, ArrayList<Integer> tagIndicator, ArrayList<ArrayList<Integer>> branches,
                            ArrayList<Integer> branchIndicator,boolean release, String count, boolean exist, int id);

    void editDataFromCommitedConfiguration(ArrayList<String> name, ArrayList<LocalDate>  dateFromDatePicker, ArrayList<Integer> nameIndicator,
                                           ArrayList<Integer> dateIndicator, String count, boolean exist, int commitedConfigurationId);

    void editDataFromProject(ArrayList<String> name, ArrayList<LocalDate> startDate, ArrayList<LocalDate> endDate, ArrayList<String> desc, ArrayList<ArrayList<Integer>> workUnit, ArrayList<Integer> workUnitIndicators,
                             ArrayList<Integer> nameIndicators, ArrayList<Integer> date1Indicators, ArrayList<Integer> date2Indicators,
                             ArrayList<Integer> descIndicators);

    void editCoordsInCommit(double x, double y, int id);
    void editCoordsInCommitedConfiguration(double x, double y, int id);
    void editCoordsInConfiguration(double x, double y, int id);
    void editCoordsInArtifact(double x, double y, int id);
    void editCoordsInRole(double x, double y, int id);


    void editDataFromRoleType(ArrayList<String> name, ArrayList<Integer> nameIndicators, ArrayList<String> description, ArrayList<Integer> descriptionIndicators,
                              ArrayList<Integer> classListIndex, ArrayList<Integer> superClassListIndex, ArrayList<String> classList,
                              ArrayList<String> superClassList, RoleTypeTable table, boolean exist, int id);

}
