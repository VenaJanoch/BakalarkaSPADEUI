package deleteModelTests;

import SPADEPAC.*;
import controllers.DeleteFormController;
import controllers.FormController;
import controllers.FormDataController;
import interfaces.IEditDataModel;
import interfaces.ISaveDataModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class CPRDeleteTest {

        Configuration configuration;
        Project project;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {
deleteModelTests.WarmUp warmUp = new deleteModelTests.WarmUp();
            lists = warmUp.getLists();
            DataModel dataModel = warmUp.getDataModel();

            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();
            formController.createTableItem(SegmentType.ConfigPersonRelation);
            formDataController.saveDataFromCPR("Jmeno1", 0, new CPRTable("0_Jmeno1","-1", 0));
            formDataController.saveDataFromCPR("Jmeno1", 0, new CPRTable("0_Jmeno1","-1", 1));
            DeleteFormController deleteFormController = warmUp.getDeleteFormController();

            ArrayList<Integer> list = new ArrayList<>();
            list.add(0);
            ObservableList observableList = FXCollections.observableList(list);


            ArrayList<BasicTable> basicTables = new ArrayList<>();
            basicTables.add(new BasicTable("Branch1", 0));
            dataModel.getSaveDataModel().createNewConfiguration(0);
            dataModel.getEditDataModel().editDataInConfiguration("Table", null, false, -1, new ArrayList<>(), list,
                     new ArrayList<>(), 0);

            deleteFormController.deleteCPR(observableList, basicTables);
            configuration = dataModel.getConfiguration(0);
            project = dataModel.getProject();
        }

    @Test
    public void testExist() {
        assertSame(1, project.getCpr().size());
    }

    @Test
    public void testList() {
        assertSame(1, lists.getCPRObservable().get(1).getId());
        assertSame(2, lists.getCPRObservable().size());

    }

    @Test
    public void testRemoveFromConfiguration() {
        assertSame(0, configuration.getCPRsIndexs().size());
    }
}