package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.Criterion;
import SPADEPAC.Milestone;
import SPADEPAC.Project;
import XML.ProcessGenerator;
import model.DataManipulator;
import model.FileManipulator;
import model.IdentificatorCreater;
import org.junit.Before;
import org.junit.Test;
import services.Alerts;
import services.DeleteControl;
import services.SegmentLists;
import services.SegmentType;
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
            FormDataController formDataController = warmUp.getFormDataController();
            Project project = warmUp.getData().getProject();
            FormController formController = warmUp.getFormController();

            formController.createTableItem(SegmentType.Milestone);
            ArrayList list = new ArrayList();
            list.add(1);
            list.add(3);
            list.add(4);
            formDataController.saveDataFromMilestoneForm("Jmeno1","", list, new MilestoneTable("0_Jmeno1","Desc",
                    "", 0));
            milestone = project.getMilestones().get(0);
        }

        @Test
        public void testName() {
            assertEquals("Jmeno1", milestone.getName() );
        }

        @Test
        public void testDesc() {
         //TODO upravit po konzultaci   assertEquals("Description1", milestone.getDescription());
        }

        @Test
        public void testIdName() {
        assertEquals("0_Jmeno1", lists.getMilestoneObservable().get(1));
        }

        @Test
        public void testIndex() {
            ArrayList list1 = new ArrayList();

            list1.add(1);
            list1.add(3);
            list1.add(4);
            assertArrayEquals(list1.toArray(), milestone.getCriteriaIndexs().toArray());
        }

    }
