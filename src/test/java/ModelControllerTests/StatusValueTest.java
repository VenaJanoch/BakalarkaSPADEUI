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

public class StatusValueTest {

        Status status;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();


            formController.createTableItem(SegmentType.Status);
            formDataController.saveDataFromStatusForm("Jmeno1", new ClassTable("0_Jmeno1", WorkUnitStatusClass.UNASSIGNED.name(), WorkUnitStatusSuperClass.UNASSIGNED.name()
                    ,0));
            status = warmUp.getDataModel().getStatus(0);
        }

        @Test
        public void testName() {
            assertEquals("Jmeno1", status.getName() );
        }

        @Test
        public void testIdName() {
        assertEquals("0_Jmeno1", lists.getStatusTypeObservable().get(1).getName());
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