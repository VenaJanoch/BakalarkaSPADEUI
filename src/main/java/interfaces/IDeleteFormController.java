package interfaces;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import tables.BasicTable;
import tables.ClassTable;
import tables.ConfigTable;
import tables.TagTable;

import java.util.ArrayList;

public interface IDeleteFormController {

    public void deleteActivityForm(int indexForm);
    public void deleteWorkUnit(ArrayList<Integer> indicesForm);
    public void deleteWorkUnit(int indexForm);
    public void deleteChange(int indexForm);
    public void deleteArtifact(int indexForm);
    public void deleteIterationForm(int formIdentificator);
    public void deletePhaseForm(int formIdentificator);
    public void deleteConfiguration(ArrayList<BasicTable> selection, TableView<ConfigTable> tableView);
    public void deleteType(ArrayList<BasicTable> selection, TableView view);
    public void deleteStatus(ArrayList<BasicTable> selection, TableView view);
    public void deleteTag(int configId, ObservableList<TagTable> list);
    public void deleteRoleType(ArrayList<BasicTable> selection, TableView tableView);
    public void deleteRole(ArrayList<BasicTable> selection, TableView tableView);
    public void deleteRelation(ArrayList<BasicTable> list, TableView<ClassTable> view);
    public void deleteResolution(ArrayList<BasicTable> selection, TableView view);
    public void deleteSeverity(ArrayList<BasicTable> selection, TableView<ClassTable> view);
    public void deletePriority(ArrayList<BasicTable> selection, TableView<ClassTable> view);
    public void deleteMilestone(ArrayList<BasicTable> selection, TableView tableView);
    public void deleteCriterion(ArrayList<BasicTable> selection, TableView tableView);
    public void deleteCPR(ArrayList<BasicTable> selection, TableView view);
    public void deleteBranch(ArrayList<BasicTable> selection, TableView view);
}
