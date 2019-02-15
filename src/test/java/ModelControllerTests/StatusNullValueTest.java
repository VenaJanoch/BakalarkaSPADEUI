package ModelControllerTests;

import controllers.FormController;
import controllers.FormDataController;
import SPADEPAC.Status;
import SPADEPAC.WorkUnitStatusClass;
import SPADEPAC.WorkUnitStatusSuperClass;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.ClassTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StatusNullValueTest {

        Status status;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();



            formController.createTableItem(SegmentType.Status);
            formDataController.saveDataFromStatusForm("", new ClassTable("", WorkUnitStatusClass.UNASSIGNED.name(), WorkUnitStatusSuperClass.UNASSIGNED.name()
                    ,0));
            status = warmUp.getDataModel().getStatus(0);
        }

        @Test
        public void testName() {
            assertNull(status.getName(), null );
        }

        @Test
        public void testIdName() {
        assertEquals("", lists.getStatusTypeObservable().get(0).getName());
    }


        @Test
        public void testClass() {
            assertEquals(WorkUnitStatusClass.UNASSIGNED.name(), status.getStatusClass());
        }

        @Test
        public void testSuperClass() {
            assertEquals(WorkUnitStatusSuperClass.UNASSIGNED.name(), status.getStatusSuperClass());
        }
}