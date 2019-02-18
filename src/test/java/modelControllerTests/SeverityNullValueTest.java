package modelControllerTests;

import controllers.FormController;
import controllers.FormDataController;
import SPADEPAC.Severity;
import SPADEPAC.WorkUnitSeverityClass;
import SPADEPAC.WorkUnitSeveritySuperClass;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.ClassTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SeverityNullValueTest {

        Severity severity;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();


            formController.createTableItem(SegmentType.Severity);
            formDataController.saveDataFromSeverity("", new ClassTable("0_", WorkUnitSeverityClass.UNASSIGNED.name(), WorkUnitSeveritySuperClass.UNASSIGNED.name()
                    ,0));
            severity = warmUp.getDataModel().getSeverity(0);
        }

        @Test
        public void testName() {
            assertNull(severity.getName(), null );
        }

        @Test
        public void testIdName() {
        assertEquals("0_", lists.getSeverityTypeObservable().get(1).getName());
    }


        @Test
        public void testClass() {
            assertEquals(WorkUnitSeverityClass.UNASSIGNED.name(), severity.getSeverityClass());
        }

        @Test
        public void testSuperClass() {
            assertEquals(WorkUnitSeverityClass.UNASSIGNED.name(), severity.getSeveritySuperClass());
        }
}