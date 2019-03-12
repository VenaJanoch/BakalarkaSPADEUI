package deleteModelTests;

import SPADEPAC.Branch;
import SPADEPAC.Configuration;
import SPADEPAC.Project;
import controllers.DeleteFormController;
import controllers.EditFormController;
import controllers.FormController;
import controllers.FormDataController;
import deleteModelTests.WarmUp;
import interfaces.IDeleteDataModel;
import interfaces.IEditDataModel;
import interfaces.ISaveDataModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.DataModel;
import model.DeleteDataModel;
import model.SaveDataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.BasicTable;
import tables.BranchTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class BranchDeleteTest {

    Configuration configuration;
    SegmentLists lists;
    Project project;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        DataModel dataModel = warmUp.getDataModel();

        FormDataController formDataController = warmUp.getFormDataController();
        FormController formController = warmUp.getFormController();
        formController.createTableItem(SegmentType.Branch);
        formDataController.saveDataFromBranch("Jmeno1", new BranchTable("0_Jmeno1","NO", false, 0));
        formDataController.saveDataFromBranch("Jmeno1", new BranchTable("0_Jmeno1","NO", false, 1));
        DeleteFormController deleteFormController = warmUp.getDeleteFormController();

        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        ObservableList observableList = FXCollections.observableList(list);

        dataModel.getSaveDataModel().createNewConfiguration(0);
        ArrayList<BasicTable> basicTables = new ArrayList<>();
        basicTables.add(new BasicTable("Table", 0));
        dataModel.getSaveDataModel().createNewConfiguration(0);
        dataModel.getEditDataModel().editDataInConfiguration("Config", null, false, -1, list, new ArrayList<>(),
                 new ArrayList<>(), 0);

        deleteFormController.deleteBranch(observableList, basicTables);
        configuration = dataModel.getConfiguration(0);
        project = dataModel.getProject();
    }

    @Test
    public void testExist() {
        assertSame(1, project.getBranches().size());
    }

    @Test
    public void testList() {
        assertSame(1, lists.getBranchObservable().get(1).getId());
        assertSame(2, lists.getBranchObservable().size());

    }

    @Test
    public void testRemoveFromConfiguration() {
        assertSame(0, configuration.getBranchesIndexs().size());
    }


}
