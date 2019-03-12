package interfaces;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import services.SegmentType;
import tables.*;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IEditFormController {

   void editDataFromClass(SegmentType segmentType, String name, String className, String superClassName, ClassTable classTable, int id);
//    private void editDataFromResolution(String name, String className, String superClassName, ClassTable classTable, int id);
//   private void editDataFromRelation(String name, String className, String superClassName, ClassTable classTable, int id);
//    private void editDataFromType(String name, String className, String superClassName, ClassTable classTable, int id);
//    private void editDataFromStatus(String name, String className, String superClassName, ClassTable classTable, int id);
//   void editDataFromPriority(String nameST, String className, String superClassName, ClassTable classTable, int id);
   void editDataFromTag(String tag, TagTable tagTable, int configFormId, int id);
//    private void editDataFromRoleType(String nameST, String className, String superClassName, ClassTable classTable, int id);
   void editDataFromRole(String nameST, String description, int roleTypeIndex, RoleTable roleTable, int id);
//    private void editDataFromSeverity(String nameST, String className, String superClassName, ClassTable classTable, int id);
   void editDataFromMilestone(String nameST, MilestoneTable milestoneTable, ArrayList<Integer> criterionIndex, int id);
   void editDataFromCriterion(String nameST, String description, CriterionTable criterionTable, int id);
   void editDataFromCPR(String nameST, int roleIndex, CPRTable cprTable);
   void editDataFromBranch(String nameST, boolean isMainBranch, BranchTable branchTable);

    void editDataFromPhase(String name, String description, LocalDate endDate, int milestonIndex, int configurationIndex, ArrayList<Integer> workUnits,
                           PhaseTable phaseTable, int id);

    void editDataFromIteration(String name, String desc, LocalDate date, LocalDate date2, int configIndex, ArrayList<Integer> integers, IterationTable iterationTable, int id);

    void editDataFromActivity(String name, String desc, ArrayList<Integer> integers, ActivityTable activityTable, int id);

    void editDataFromChange(String name, String desc, boolean exist, ChangeTable changeTable, int id);

    void editDataFromArtifact(String nameTFText, String description, boolean exist, int roleIndex, int typeIndex, LocalDate date, ArtifactTable artifactTable, int id);

    void editDataFromWorkUnit(String name, String description, String estimatedTime, int priorityIndex, int severityIndex, int resolutionIndex,
                              int status, String category, int typeIndex, int assigneIndex, int authorIndex, boolean selected, WorkUnitTable workUnitTable, int id);

    void editDataFromConfiguration(String name, LocalDate createDate, int autohorIndex, boolean isRelease, ObservableList<Integer> cprsIndicies,
                                   ObservableList<Integer> branchIndicies, ObservableList<Integer> changeIndicies, int configId);

    void editDataFromVCSTag(String nameTFText, String desc, VCSTagTable tagTable, int id);

    void editDataFromCommit(String text, boolean isRelease, int id);

    void editDataFromCommitedConfiguration(String nameTFText, LocalDate dateFromDatePicker, int commitedConfigurationId);
}
