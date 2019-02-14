package interfaces;

import SPADEPAC.*;
import services.SegmentType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface IEditDataModel {

      void editDataInCPR(String nameForManipulator, int roleIndex, int id);
      void editDataInBranch(String nameForManipulator, boolean isMainBranch, int id);
      void editDataInArtifact(String nameForManipulator, String descForManipulator, LocalDate createdDate, boolean isCreate, int x, int y, int authorIndex, int typeIndex,
                                   int id);
      void editDataInChange(String nameForManipulator, String descForManipulator, int x, int y, boolean selected, int id);
      void editDataInPhase(String actName, LocalDate endDateL, String desc, int confIndex, int milestoneIndex, int x, int y,
                                ArrayList<Integer> itemIndexList, int id);
      void editDataInIteration(String nameForManipulator, LocalDate startDate, LocalDate endDate, String descriptionForManipulator,
                                    int configIndex, int x, int y, ArrayList<Integer> itemIndexList, int id);
      void editDataInActivity(String nameForManipulator, String descriptionForManipulator, int x, int y, ArrayList<Integer> setOfItemOnCanvas, int id);
      void editDataInWorkUnit(String nameForManipulator,String description, String categoryForManipulator, int assigneIndex, int authorIndex,
                                   int priorityIndex, int severityIndex, int typeIndex, int resolutionIndex, int statusIndex,
                                   int x, int y, double estimateForDataManipulator,boolean isExist, int id, boolean isProjectCanvas);
      void editDataInConfiguration(String actName, LocalDate createDate, boolean isRelease, int authorIndex,
                                        List<Integer> branches, List<Integer> cprs, ArrayList artifactIndexs, ArrayList changeIndexs, int id);
      void editDataInCriterion(String nameForManipulator, String descForManipulator, int id);
      void editDataInPriority(String nameForManipulator, String classST, String superST, int id);
      void editDataInSeverity(String nameForManipulator, String classST, String superST, int id);
      void editDataInRelation(String nameForManipulator, String classST, String superST, int id);
      void editDataInResolution(String nameForManipulator, String classST, String superST, int id);
      void editDataInRole(String nameForManipulator, String descForManipulator, int type, int id);
      void editDataInMilestone(String nameForManipulator, String descForManipulator, ArrayList<Integer> criterionIndex, int id);
      void editDataInRoleType(String nameForManipulator, String classST, String superST, int id);
      void editDataInStatus(String nameForManipulator, String classST, String superST, int id);
      void editDataInType(String nameForManipulator, String classST, String superST, int id);
      void updateItemList(SegmentType formType, SegmentType elementType, ArrayList<Integer> elementIndexList);
      void editTagInConfiguration(String tag, int configId, int id);
}
