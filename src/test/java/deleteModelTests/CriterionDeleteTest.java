package deleteModelTests;

import SPADEPAC.Milestone;
import SPADEPAC.Project;
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
import tables.CriterionTable;
import tables.MilestoneTable;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class CriterionDeleteTest {

        Milestone milestone;
        Project project;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            DataModel dataModel = warmUp.getDataModel();

            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();
            formController.createTableItem(SegmentType.Criterion);
            formDataController.saveDataFromCriterionForm("Jmeno1", new CriterionTable("0_Jmeno1","NO", 0));
            formDataController.saveDataFromCriterionForm("Jmeno1", new CriterionTable("0_Jmeno1","NO", 1));
            DeleteFormController deleteFormController = warmUp.getDeleteFormController();

            ArrayList<Integer> list = new ArrayList<>();
            list.add(1);
            ObservableList observableList = FXCollections.observableList(list);

            warmUp.getFormDataController().saveDataFromMilestoneForm("Milestone", "", list , new MilestoneTable("Milestone","","",0));
            ArrayList<BasicTable> basicTables = new ArrayList<>();
            basicTables.add(new BasicTable("Table",0));

            deleteFormController.deleteCriterion(observableList, basicTables);
            milestone = dataModel.getMilestone(0);
            project = dataModel.getProject();
        }

    @Test
    public void testExist() {
        assertSame(1, project.getCriterions().size());
    }

    @Test
    public void testList() {
        assertSame(1, lists.getCriterionObservable().get(1).getId());
        assertSame(2, lists.getCriterionObservable().size());

    }

    @Test
    public void testRemoveFromMilestone() {
        assertSame(0, milestone.getCriteriaIndexs().size());
    }


}
