package modelControllerTests;

import controllers.FormController;
import controllers.FormDataController;
import SPADEPAC.Priority;
import SPADEPAC.WorkUnitPriorityClass;
import SPADEPAC.WorkUnitPrioritySuperClass;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.ClassTable;

import static org.junit.Assert.*;

public class PriorityValueTest {

        Priority priority;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();

            warmUp.getDataModel().getSaveDataModel().createNewPriority("","","",0);

            priority = warmUp.getDataModel().getPriority(0);

        }
    @Test
    public void testId() {
        assertSame(0, priority.getId());
    }

    @Test
    public void testExist() {
        assertTrue(priority.isExist());
    }
}