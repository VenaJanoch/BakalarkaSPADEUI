package deleteModelTests;

import SPADEPAC.Project;
import SPADEPAC.WorkUnit;
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
import tables.ClassTable;

import java.util.ArrayList;

import static org.junit.Assert.assertSame;

public class SeverityDeleteTest {

        WorkUnit workUnit;
        Project project;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            DataModel dataModel = warmUp.getDataModel();

            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();
            formController.createTableItem(SegmentType.Severity);
            formDataController.saveDataFromSeverity("Jmeno1", new ClassTable("0_Jmeno1","Test", "Test",0));
            formDataController.saveDataFromSeverity("Jmeno1", new ClassTable("0_Jmeno1","Test", "Test",1));
            DeleteFormController deleteFormController = warmUp.getDeleteFormController();

            ArrayList<Integer> list = new ArrayList<>();
            list.add(1);
            ObservableList observableList = FXCollections.observableList(list);


            dataModel.getSaveDataModel().createNewWorkUnit(0);
            dataModel.getEditDataModel().editDataInWorkUnit("Milestone", "", "",
                    0, 0, 0, 0, 0, 0, 0,0, 0,
                    0.0, true, 0, true);

            ArrayList<BasicTable> basicTables = new ArrayList<>();
            basicTables.add(new BasicTable("Table",0));

            deleteFormController.deleteSeverity(observableList, basicTables);
            workUnit = dataModel.getWorkUnit(0);
            project = dataModel.getProject();

        }

    @Test
    public void testExist() {
        assertSame(1, project.getSeverity().size());
    }

    @Test
    public void testList() {
        assertSame(1, lists.getSeverityTypeObservable().get(1).getId());
        assertSame(2, lists.getSeverityTypeObservable().size());

    }

    @Test
    public void testRemoveFromMilestone() {
        assertSame(-1, workUnit.getSeverityIndex());
    }


}