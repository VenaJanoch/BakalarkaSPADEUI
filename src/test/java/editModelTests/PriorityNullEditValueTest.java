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
import static org.junit.Assert.assertNull;

public class PriorityNullEditValueTest {

        Priority priority;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();

            formController.createTableItem(SegmentType.Priority);
            formDataController.saveDataFromPriority("jk", new ClassTable("jk", WorkUnitPriorityClass.UNASSIGNED.name(), WorkUnitPrioritySuperClass.UNASSIGNED.name()
                    ,0));
            EditFormController editFormController  = warmUp.getEditFormController();
            editFormController.editDataFromClass(SegmentType.Priority,"", WorkUnitPriorityClass.UNASSIGNED.name(),
                    WorkUnitPrioritySuperClass.UNASSIGNED.name(), new ClassTable("0_", WorkUnitPriorityClass.UNASSIGNED.name(),
                    WorkUnitPrioritySuperClass.UNASSIGNED.name(),0), 0);
            priority = warmUp.getDataModel().getPriority(0);
        }

        @Test
        public void testName() {
            assertNull(priority.getName(), null );
        }

        @Test
        public void testIdName() {
        assertEquals("0_", lists.getPriorityTypeObservable().get(1).getName());
    }

}