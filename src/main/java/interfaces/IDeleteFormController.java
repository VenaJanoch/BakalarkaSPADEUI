package interfaces;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import services.SegmentType;
import tables.BasicTable;
import tables.ClassTable;

import java.util.ArrayList;

public interface IDeleteFormController {


    void deleteActivityWithDialog(ArrayList<BasicTable> selection, TableView tableView);

    void deleteWorkUnitWithDialog(ArrayList<BasicTable> selection, TableView tableView);

    void deleteChangeWithDialog(ArrayList<BasicTable> selection, TableView tableView);

    boolean deleteArtifact(int indexForm);

    void deleteIterationWithDialog(ArrayList<BasicTable> selection, TableView tableView);

    void deletePhaseForm(ObservableList phaseList, ArrayList<BasicTable> selection);

    boolean deleteConfigurationWithDialog(int formIndex);

    boolean deleteCommitWithDialog(int formIndex);

    boolean deleteCommitedConfigurationWithDialog(int formIndex);

    void deleteTypeWithDialog(ArrayList<BasicTable> selection, TableView view);

    void deleteStatusWithDialog(ArrayList<BasicTable> selection, TableView view);

    void deleteRoleTypeWithDialog(ArrayList<BasicTable> selection, TableView tableView);

    boolean deletePersonWithDialog(int indexForm);

    void deleteRelationWithDialog(ArrayList<BasicTable> list, TableView<ClassTable> view);

    void deleteResolutionWithDialog(ArrayList<BasicTable> selection, TableView view);

    void deleteSeverityWithDialog(ArrayList<BasicTable> selection, TableView<ClassTable> view);

    void deletePriorityWithDialog(ArrayList<BasicTable> selection, TableView<ClassTable> view);

    void deleteMilestoneWithDialog(ArrayList<BasicTable> selection, TableView tableView);

    void deleteCriterionWithDialog(ArrayList<BasicTable> selection, TableView tableView);

    void deleteCPRWithDialog(ArrayList<BasicTable> selection, TableView view);

    void deleteBranchDialog(ArrayList<BasicTable> selection, TableView view);

    void deleteItemWithDialog(ArrayList<BasicTable> list, TableView tableTV, SegmentType segmentType);

    void removePersonArtifactLink(int arrowId, int startId, int endId);

    void removePersonConfigurationLink(int arrowId, int startId, int endId);

    void removeArtifactConfiguraionLink(int arrowId, int startId, int endId);

    void removeCommitedConfigurationConfigurationLink(int arrowId, int startId, int endId);

    void removeCommitComiitedConfigurationLink(int arrowId, int startId, int endId);

    void removePersonCommitLink(int arrowId, int startId, int endId);

    void removePersonCommittedConfigurationLink(int arrowId, int startId, int endId);
}
