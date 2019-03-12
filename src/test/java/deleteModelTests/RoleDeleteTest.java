package deleteModelTests;

import SPADEPAC.*;
import controllers.DeleteFormController;
import controllers.FormController;
import controllers.FormDataController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.BasicTable;
import tables.CPRTable;
import tables.RoleTable;

import java.util.ArrayList;

import static org.junit.Assert.assertSame;

public class RoleDeleteTest {

    WorkUnit workUnit;
    Artifact artifact;
    Configuration configuration;
    ConfigPersonRelation configPersonRelation;
    Project project;
    SegmentLists lists;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        DataModel dataModel = warmUp.getDataModel();

        FormDataController formDataController = warmUp.getFormDataController();
        FormController formController = warmUp.getFormController();
        formController.createTableItem(SegmentType.Milestone);

        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        ObservableList observableList = FXCollections.observableList(list);


        formDataController.saveDataFromRoleForm("Jmeno1", 0, new RoleTable("0_Jmeno1", "NO", "", 0));
        formDataController.saveDataFromRoleForm("Jmeno1", 0, new RoleTable("0_Jmeno1", "NO", "", 1));
        DeleteFormController deleteFormController = warmUp.getDeleteFormController();

        ArrayList<BasicTable> basicTables = new ArrayList<>();
        basicTables.add(new BasicTable("Table", 0));

        dataModel.getSaveDataModel().createNewWorkUnit(0);
        dataModel.getSaveDataModel().createNewArtifact(0);
        dataModel.getSaveDataModel().createNewConfiguration(0);
        formDataController.saveDataFromCPR("Jmeno1", 1, new CPRTable("0_Jmeno1","1", 0));

        dataModel.getEditDataModel().editDataInWorkUnit("Milestone", "", "",
                0, 0, 0, 0, 0, 0, 0,0,
                true, 0);
        dataModel.getEditDataModel().editDataInArtifact("", "", null, false,  0,0, 0);
        dataModel.getEditDataModel().editDataInConfiguration("", null, false, 0,  new ArrayList<>(), list,
                 new ArrayList<>(), 0);


        deleteFormController.deleteRole(observableList, basicTables);
        workUnit = dataModel.getWorkUnit(0);
        artifact = dataModel.getArtifact(0);
        configuration = dataModel.getConfiguration(0);
        configPersonRelation = dataModel.getConfigPersonRelation(0);

        project = dataModel.getProject();
    }

    @Test
    public void testExist() {
        assertSame(1, project.getRoles().size());
    }

    @Test
    public void testList() {
        assertSame(1, lists.getRoleObservable().get(1).getId());
        assertSame(2, lists.getRoleObservable().size());

    }

    @Test
    public void testRemoveFromMilestone() {
        assertSame(-1, workUnit.getAssigneeIndex());
        assertSame(-1, workUnit.getAuthorIndex());
        assertSame(-1, artifact.getAuthorIndex());
        assertSame(-1, configuration.getAuthorIndex());
        assertSame(-1, configPersonRelation.getPersonIndex());

    }

}