package ModelControllerTests;

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

public class SeverityValueTest {

        Severity severity;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();

            FormController formController = warmUp.getFormController();


            formController.createTableItem(SegmentType.Severity);
            formDataController.saveDataFromSeverity("Jmeno1", new ClassTable("0_Jmeno1", WorkUnitSeverityClass.CRITICAL.name(), WorkUnitSeveritySuperClass.MAJOR.name()
                    ,0));
            severity = warmUp.getDataModel().getSeverity(0);
            
        }

        @Test
        public void testName() {
            assertEquals("Jmeno1",severity.getName());
        }

        @Test
        public void testIdName() {
        assertEquals("0_Jmeno1", lists.getSeverityTypeObservable().get(1).getName());
    }

        @Test
        public void testClass() {
            assertEquals(WorkUnitSeverityClass.CRITICAL.name(), severity.getSeverityClass());
        }

        @Test
        public void testSuperClass() {
            assertEquals(WorkUnitSeveritySuperClass.MAJOR.name(), severity.getSeveritySuperClass());
        }
}