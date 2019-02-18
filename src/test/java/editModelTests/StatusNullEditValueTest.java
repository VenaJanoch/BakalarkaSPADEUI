package editModelTests;

import SPADEPAC.*;
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

public class StatusNullEditValueTest {

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


            EditFormController editFormController  = warmUp.getEditFormController();
            editFormController.editDataFromClass(SegmentType.Status,"", WorkUnitStatusClass.UNASSIGNED.name(),
                    WorkUnitStatusSuperClass.UNASSIGNED.name(), new ClassTable("0_", WorkUnitStatusClass.UNASSIGNED.name(),
                            WorkUnitStatusSuperClass.UNASSIGNED.name(),0), 0);

            status = warmUp.getDataModel().getStatus(0);
        }

        @Test
        public void testName() {
            assertNull(status.getName(), null );
        }

        @Test
        public void testIdName() {
        assertEquals("0_", lists.getStatusTypeObservable().get(1).getName());
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