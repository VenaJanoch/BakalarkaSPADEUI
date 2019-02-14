package interfaces;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import tables.BasicTable;
import tables.ClassTable;
import tables.ConfigTable;
import tables.TagTable;

import java.util.ArrayList;

  public interface IDeleteFormController {

      void deleteActivityForm(int indexForm);
      void deleteWorkUnit(ArrayList<Integer> indicesForm);
      void deleteWorkUnit(int indexForm);
      void deleteChange(int indexForm);
      void deleteArtifact(int indexForm);
      void deleteIterationForm(int formIdentificator);
      void deletePhaseForm(int formIdentificator);
      void deleteConfiguration(ArrayList<BasicTable> selection, TableView<ConfigTable> tableView);
      void deleteType(ArrayList<BasicTable> selection, TableView view);
      void deleteStatus(ArrayList<BasicTable> selection, TableView view);
      void deleteTag(int configId, ObservableList<TagTable> list);
      void deleteRoleType(ArrayList<BasicTable> selection, TableView tableView);
      void deleteRole(ArrayList<BasicTable> selection, TableView tableView);
      void deleteRelation(ArrayList<BasicTable> list, TableView<ClassTable> view);
      void deleteResolution(ArrayList<BasicTable> selection, TableView view);
      void deleteSeverity(ArrayList<BasicTable> selection, TableView<ClassTable> view);
      void deletePriority(ArrayList<BasicTable> selection, TableView<ClassTable> view);
      void deleteMilestone(ArrayList<BasicTable> selection, TableView tableView);
      void deleteCriterion(ArrayList<BasicTable> selection, TableView tableView);
      void deleteCPR(ArrayList<BasicTable> selection, TableView view);
      void deleteBranch(ArrayList<BasicTable> selection, TableView view);
}
