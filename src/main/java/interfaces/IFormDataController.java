package interfaces;

import controlPanels.CriterionControlPanel;
import graphics.CanvasItem;
import javafx.collections.ObservableList;
import services.CanvasType;
import services.SegmentType;
import tables.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IFormDataController {
    boolean saveDataFromPhaseForm(String actName, LocalDate endDateL, String desc, int confIndex, int milestoneIndex, Map<Integer, CanvasItem> itemIndexList,
                                  int indexForm);

    boolean saveDataFromIterationForm(String actName, LocalDate startDate, LocalDate endDate, String desc, int chooseConfigID, Map<Integer, CanvasItem> itemIndexList, int indexForm);

    boolean saveDataFromActivityForm(String actName, String desc, Map<Integer, CanvasItem> mapOfItemOnCanvas, int indexForm);

    boolean saveDataFromWorkUnit(String actName, String description, String category, int assigneIndex, int authorIndex, int priorityIndex, int severityIndex,
                                 int typeIndex, int resolutionIndex, int statusIndex, String estimated, boolean selected, int indexForm, CanvasType canvasType);

    boolean saveDataFromConfiguration(String actName, LocalDate createDate, boolean isRelease, int authorIndex, ArrayList<Integer> branchIndex,
                                      ArrayList<Integer> cprIndex, Map<Integer, CanvasItem> itemIndexList, boolean isNew, int indexForm);

    void setEditItemInConfigurationTable(ConfigTable configTable);

    boolean saveDataFromChange(String actName, String desc, boolean selected, int indexForm);

    boolean saveDataFromArtifact(String actName, LocalDate createdDate, String type, String desc, int authorIndex,
                                 int typeIndex, boolean selected, int indexForm);

    void saveDataFromBranch(String nameST, BranchTable branchTable);

    void saveDataFromCPR(String nameST, int roleIndex, CPRTable cprTable);

    void saveDataFromCriterionForm(String nameST, CriterionTable criterionTable);

    void saveDataFromMilestoneForm(String nameST, String description, ArrayList<Integer> criterionIndex, MilestoneTable milestoneTable);

    void saveDataFromPriority(String nameST, ClassTable tableItem);

    void saveDataFromSeverity(String nameST, ClassTable tableItem);

    void saveDataFromResolutionForm(String nameST, ClassTable classTable);

    void saveDataFromRelationForm(String nameST, ClassTable classTable);

    void saveDataFromRoleForm(String nameST, int typeIndex, RoleTable roleTable);

    void saveDataFromRoleTypeForm(String nameST, ClassTable classTable);

    void saveDataFromTagForm(String tag, int configId, int id);

    void saveDataFromStatusForm(String nameST, ClassTable classTable);

    void saveDataFromTypeForm(String nameST, ClassTable classTable);

    void saveDataFromProjectFrom(String nameST, LocalDate endDate, LocalDate startDate, String desc);

    MilestoneTable prepareMilestoneToTable(String nameST, String description, int id, ArrayList criterionArray);

    RoleTable prepareRoleToTable(String nameST, String description, int id, int roleTypeIndex);

    CPRTable prepareCPRToTable(String nameST, int roleIndex, int id);

    BranchTable prepareBranchToTable(String nameST, boolean main, int id);

    List[] getCriterionData(int id);

    List[] getMilestoneStringData(int id);

    ArrayList<ArrayList<Integer>>  getCriterionFromMilestone(int id);

    List[] getRoleStringData(int id);

    List[] getClassStringData(SegmentType segmentType, int id);

    ObservableList<BasicTable> getRoleList();

    List[] getCPRStringData(int id);

    List[] getBranchStringData(int id);

    String getTagData(int id, int configFormId);

    List[] getPhaseStringData(int id);

    ArrayList<ArrayList<Integer>> getWorkUnitFromSegment(int id, SegmentType phase);

    List[] getIterationStringData(int id);

    List[] getActivityStringData(int id);

    List[] getChangeStringData(int id);

    List[] getArtifactStringData(int id);

    List[] getWorkUnitStringData(int id);

    List[] getConfigurationStringData(int configId);

    ArrayList<ArrayList<Integer>> getCPRFromConfiguration(int configId);

    ArrayList<ArrayList<Integer>> getBranchesFromConfiguration(int configId);

    ArrayList<ArrayList<Integer>> getChangesFromConfiguration(int configId);

    List[] getVCSTagStringData(int tagId);

    List[] getCommitStringData(int commitId);

    List[] getCommitedConfigurationStringData(int commitedConfigurationId);

    List[] getProjectStringData();
}
