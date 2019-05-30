package interfaces;

import controllers.formControllers.FormFillController;
import graphics.canvas.CanvasItem;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import services.SegmentType;
import tables.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IFormDataController {

    int saveDataFromPhaseForm(TableView<PhaseTable> tableView, boolean isExist);

    int saveDataFromIterationForm(TableView<IterationTable> tableView, boolean isExist);

    int saveDataFromActivityForm(TableView<ActivityTable> tableView, boolean isExist);

    int saveDataFromVCSTagForm(TableView<VCSTagTable> tableView, boolean isExist);


    int saveDataFromWorkUnit(TableView<WorkUnitTable> tableView, boolean isExist);

    boolean saveDataFromConfiguration(String actName, LocalDate createDate, boolean isRelease, int authorIndex, ArrayList<Integer> branchIndex,
                                      ArrayList<Integer> cprIndex, Map<Integer, CanvasItem> itemIndexList, boolean isNew, int indexForm);

    int saveDataFromChangeForm(TableView<ChangeTable> tableView, boolean isExist);

    boolean saveDataFromArtifact(String actName, LocalDate createdDate, String type, String desc, int authorIndex,
                                 int typeIndex, boolean selected, int indexForm);

    int saveDataFromBranch(TableView<BranchTable> tableView, boolean isExist);

    int saveDataFromCPR(TableView<CPRTable> tableView, boolean isExist);

    int saveDataFromCriterionForm(TableView<CriterionTable> tableView, boolean isExist);

    int saveDataFromMilestoneForm(TableView<MilestoneTable> tableView, boolean isExist);

    int saveDataFromPriority(TableView<ClassTable> tableView, boolean isExist);

    int saveDataFromSeverity(TableView<ClassTable> tableView, boolean isExist);

    int saveDataFromResolutionForm(TableView<ClassTable> tableView, boolean isExist);

    int saveDataFromRelationForm(TableView<ClassTable> tableView, boolean isExist);

    void saveDataFromRoleForm(String nameST, int typeIndex, PersonTable personTable);

    int saveDataFromRoleTypeForm(TableView<RoleTypeTable> tableView, boolean isExist);

    int saveDataFromStatusForm(TableView<ClassTable> tableView, boolean isExist);

    int saveDataFromTypeForm(TableView<ClassTable> tableView, boolean isExist);

    void saveDataFromProjectFrom(String nameST, LocalDate endDate, LocalDate startDate, String desc);

    MilestoneTable prepareMilestoneToTable(String nameST, String description, int id, ArrayList criterionArray);

    PersonTable prepareRoleToTable(String nameST, String description, int id, int roleTypeIndex);

    CPRTable prepareCPRToTable(String nameST, int roleIndex, int id);

    BranchTable prepareBranchToTable(String nameST, boolean main, int id);

    List[] getCriterionData(int id);

    List[] getMilestoneStringData(int id);

    ArrayList<ArrayList<Integer>> getCriterionFromMilestone(int id);

    List[] getPersonStringData(int id);

    List[] getClassStringData(SegmentType segmentType, int id);

    ObservableList<BasicTable> getRoleList();

    List[] getCPRStringData(int id);

    List[] getBranchStringData(int id);

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

    List[] getRoleTypeStringData(int id);

    void createArtifactToConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML);

    void createCommitToCommitedConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML);

    void createCommitedConfigurationToConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML);

    void createNewPersonToConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML);

    void createNewPersonToArtifactRelation(int linkId, Integer startId, Integer endId, boolean isXML);

    void createNewPersonToCommitRelation(int linkId, Integer startId, Integer endId, boolean isXML);

    void createNewPersonToCommittedConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML);

    void createCopyTableItem(ArrayList<BasicTable> list, TableView tableView, SegmentType segmentType);

    void setFormFillController(FormFillController formFillController);
}
