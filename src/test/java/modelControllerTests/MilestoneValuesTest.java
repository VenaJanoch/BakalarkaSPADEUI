package modelControllerTests;

import controllers.FormController;
import controllers.FormDataController;
import SPADEPAC.Milestone;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.CriterionTable;
import tables.MilestoneTable;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MilestoneValuesTest {

        Milestone milestone;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();

            for(int i = 0; i < 6; i++){
                formController.createTableItem(SegmentType.Criterion);
                formDataController.saveDataFromCriterionForm("Jmeno1", new CriterionTable("0_Jmeno1" ,"Description1", i));
            }

            formController.createTableItem(SegmentType.Milestone);
            ArrayList list = new ArrayList();
            list.add(1);
            list.add(3);
            list.add(4);
            formDataController.saveDataFromMilestoneForm("Jmeno1","Description1", list, new MilestoneTable("0_Jmeno1",
                    "Desc", "", 0));
            milestone = warmUp.getDataModel().getMilestone(0);
        }

        @Test
        public void testName() {
            assertEquals("Jmeno1", milestone.getName());
        }

        @Test
        public void testDesc() {
          assertEquals("Description1", milestone.getDescription());
        }

        @Test
        public void testIdName() {
        assertEquals("0_Jmeno1", lists.getMilestoneObservable().get(1).getName());
        }

        @Test
        public void testIndex() {
            ArrayList list1 = new ArrayList();

            list1.add(0);
            list1.add(2);
            list1.add(3);
            assertArrayEquals(list1.toArray(), milestone.getCriteriaIndexs().toArray());
        }

    }
