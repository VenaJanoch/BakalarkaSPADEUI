package deleteModelTests;

import SPADEPAC.Milestone;
import SPADEPAC.Phase;
import SPADEPAC.Project;
import controllers.DeleteFormController;
import controllers.EditFormController;
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
import tables.CriterionTable;
import tables.MilestoneTable;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class MilestoneDeleteTest {

    Phase phase;
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


        formDataController.saveDataFromMilestoneForm("Jmeno1", "", list, new MilestoneTable("0_Jmeno1", "NO", "", 0));
        formDataController.saveDataFromMilestoneForm("Jmeno1", "", list, new MilestoneTable("0_Jmeno1", "NO", "", 1));

        DeleteFormController deleteFormController = warmUp.getDeleteFormController();

        ArrayList<BasicTable> basicTables = new ArrayList<>();
        basicTables.add(new BasicTable("Table", 0));
        dataModel.getSaveDataModel().createNewPhase(0);
        dataModel.getEditDataModel().editDataInPhase("Test", null, "", 0, 0, 0,
                0, new ArrayList<>(), 0);


        deleteFormController.deleteMilestone(observableList, basicTables);
        phase = dataModel.getPhase(0);
        project = dataModel.getProject();
    }

    @Test
    public void testExist() {
        assertSame(1, project.getMilestones().size());
    }

    @Test
    public void testList() {
        assertSame(1, lists.getMilestoneObservable().get(1).getId());
        assertSame(2, lists.getMilestoneObservable().size());

    }

    @Test
    public void testRemoveFromMilestone() {
        assertSame(-1, phase.getMilestoneIndex());

    }

}