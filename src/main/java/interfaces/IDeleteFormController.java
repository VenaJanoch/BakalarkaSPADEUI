package interfaces;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import services.SegmentType;
import tables.BasicTable;
import tables.ClassTable;
import tables.ConfigTable;
import tables.TagTable;

import java.util.ArrayList;

  public interface IDeleteFormController {

      void deleteActivityWithDialog(ArrayList<BasicTable> selection, TableView tableView);
      void deleteWorkUnit(ArrayList<Integer> indicesForm);
      void deleteWorkUnitWithDialog(ArrayList<BasicTable> selection, TableView tableView);
      void deleteChangeWithDialog(ArrayList<BasicTable> selection, TableView tableView);
      void deleteArtifact(int indexForm);
      void deleteIterationWithDialog(ArrayList<BasicTable> selection, TableView tableView);
      void deletePhaseForm(ObservableList phaseList, ArrayList<BasicTable> selection);
      void deleteConfigurationWithDialog(ArrayList<BasicTable> selection, TableView<ConfigTable> tableView);
      void deleteTypeWithDialog(ArrayList<BasicTable> selection, TableView view);
      void deleteStatusWithDialog(ArrayList<BasicTable> selection, TableView view);
      void deleteTag(int configId, ObservableList<TagTable> list);
      void deleteRoleTypeWithDialog(ArrayList<BasicTable> selection, TableView tableView);
      void deleteRoleWithDialog(ArrayList<BasicTable> selection, TableView tableView);
      void deleteRelationWithDialog(ArrayList<BasicTable> list, TableView<ClassTable> view);
      void deleteResolutionWithDialog(ArrayList<BasicTable> selection, TableView view);
      void deleteSeverityWithDialog(ArrayList<BasicTable> selection, TableView<ClassTable> view);
      void deletePriorityWithDialog(ArrayList<BasicTable> selection, TableView<ClassTable> view);
      void deleteMilestoneWithDialog(ArrayList<BasicTable> selection, TableView tableView);
      void deleteCriterionWithDialog(ArrayList<BasicTable> selection, TableView tableView);
      void deleteCPRWithDialog(ArrayList<BasicTable> selection, TableView view);
      void deleteBranchDialog(ArrayList<BasicTable> selection, TableView view);

      void deleteItemWithDialog(ArrayList<BasicTable> list, TableView tableTV, SegmentType segmentType);
  }
