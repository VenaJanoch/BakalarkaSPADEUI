package ModelControllerTests;

import controllers.FormController;
import controllers.FormDataController;
import SPADEPAC.*;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.ClassTable;

import static org.junit.Assert.*;

public class PriorityNullValueTest {

        Priority priority;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();

            formController.createTableItem(SegmentType.Priority);
            formDataController.saveDataFromPriority("", new ClassTable("", WorkUnitPriorityClass.UNASSIGNED.name(), WorkUnitPrioritySuperClass.UNASSIGNED.name()
                    ,0));
            priority = warmUp.getDataModel().getPriority(0);
        }

        @Test
        public void testName() {
            assertNull(priority.getName(), null );
        }

        @Test
        public void testIdName() {
        assertEquals("", lists.getPriorityTypeObservable().get(0).getName());
    }


        @Test
        public void testClass() {
            assertEquals(WorkUnitPriorityClass.UNASSIGNED.name(), priority.getPriorityClass());
        }

        @Test
        public void testSuperClass() {
            assertEquals(WorkUnitPriorityClass.UNASSIGNED.name(), priority.getPrioritySuperClass());
        }
}