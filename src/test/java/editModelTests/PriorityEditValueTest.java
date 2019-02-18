package editModelTests;

import SPADEPAC.Priority;
import SPADEPAC.WorkUnitPriorityClass;
import SPADEPAC.WorkUnitPrioritySuperClass;
import controllers.EditFormController;
import controllers.FormController;
import controllers.FormDataController;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.ClassTable;

import static org.junit.Assert.assertEquals;

public class PriorityEditValueTest {

        Priority priority;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();

            formController.createTableItem(SegmentType.Priority);
            formDataController.saveDataFromPriority("Jmeno", new ClassTable("0_Jmeno", WorkUnitPriorityClass.UNASSIGNED.name(), WorkUnitPrioritySuperClass.UNASSIGNED.name()
                    ,0));

            EditFormController editFormController  = warmUp.getEditFormController();
            editFormController.editDataFromClass(SegmentType.Priority,"Jmeno1", WorkUnitPriorityClass.HIGH.name(),
                    WorkUnitPrioritySuperClass.HIGH.name(), new ClassTable("0_Jmeno1", WorkUnitPriorityClass.HIGH.name(),
                            WorkUnitPrioritySuperClass.HIGH.name(),0), 0);
            priority = warmUp.getDataModel().getPriority(0);

        }

        @Test
        public void testName() {
            assertEquals("Jmeno1",priority.getName());
        }

        @Test
        public void testIdName() {
        assertEquals("0_Jmeno1", lists.getPriorityTypeObservable().get(1).getName());
    }

        @Test
        public void testClass() {
            assertEquals(WorkUnitPriorityClass.HIGH.name(), priority.getPriorityClass());
        }

        @Test
        public void testSuperClass() {
            assertEquals(WorkUnitPriorityClass.HIGH.name(), priority.getPrioritySuperClass());
        }
}