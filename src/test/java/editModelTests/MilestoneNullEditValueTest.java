package editModelTests;

import SPADEPAC.Milestone;
import controllers.EditFormController;
import controllers.FormController;
import controllers.FormDataController;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.CriterionTable;
import tables.MilestoneTable;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MilestoneNullEditValueTest {

        Milestone milestone;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();

            formController.createTableItem(SegmentType.Milestone);
            for(int i = 0; i < 6; i++){
                formController.createTableItem(SegmentType.Criterion);
                formDataController.saveDataFromCriterionForm("Jmeno1", new CriterionTable("0_Jmeno1" ,"Description1", i));
            }

            formController.createTableItem(SegmentType.Milestone);
            ArrayList list = new ArrayList();
            list.add(1);
            list.add(3);
            list.add(4);
            formDataController.saveDataFromMilestoneForm("Test","Test", list, new MilestoneTable("","", "", 0));


            EditFormController editFormController  = warmUp.getEditFormController();
            editFormController.editDataFromMilestone("", new MilestoneTable("0_", "", "", 0), new ArrayList<>(), 0);

            milestone = warmUp.getDataModel().getMilestone(0);
        }

        @Test
        public void testName() {
            assertNull(milestone.getName(), null );
        }

        @Test
        public void testClass() {
            assertNull(milestone.getDescription(), null);
        }

        @Test
        public void testIdName() {
            assertEquals("0_", lists.getMilestoneObservable().get(1).getName());
        }

        @Test
        public void testCriterion() {
            assertNotNull(null, milestone.getCriteriaIndexs());
        }

        }
