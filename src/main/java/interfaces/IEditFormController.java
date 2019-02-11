package interfaces;

import Controllers.InputController;
import services.SegmentType;
import tables.*;

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
   void editDataFromRole(String nameST, String description, RoleTable roleTable, int roleTypeIndex, int id);
//    private void editDataFromSeverity(String nameST, String className, String superClassName, ClassTable classTable, int id);
   void editDataFromMilestone(String nameST, MilestoneTable milestoneTable, ArrayList<Integer> criterionIndex, int id);
   void editDataFromCriterion(String nameST, String description, CriterionTable criterionTable, int id);
   void editDataFromCPR(String nameST, int roleIndex, CPRTable cprTable);
   void editDataFromBranch(String nameST, boolean isMainBranch, BranchTable branchTable);

}
