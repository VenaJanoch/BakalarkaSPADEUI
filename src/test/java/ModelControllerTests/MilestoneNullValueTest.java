package ModelControllerTests;

import controllers.FormController;
import controllers.FormDataController;
import SPADEPAC.Milestone;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.MilestoneTable;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MilestoneNullValueTest {

        Milestone milestone;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();

            formController.createTableItem(SegmentType.Milestone);
            formDataController.saveDataFromMilestoneForm("","", new ArrayList<>(), new MilestoneTable("","", "", 0));
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
            assertEquals("", lists.getMilestoneObservable().get(0).getName());
        }

        @Test
        public void testCriterion() {
            assertNotNull(null, milestone.getCriteriaIndexs());
        }

        }
