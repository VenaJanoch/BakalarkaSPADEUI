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

public class SeverityEditValueTest {

        Severity severity;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();

            FormController formController = warmUp.getFormController();


            formController.createTableItem(SegmentType.Severity);
            formDataController.saveDataFromSeverity("Jmeno", new ClassTable("0_Jmeno", WorkUnitSeverityClass.UNASSIGNED.name(),
                    WorkUnitSeveritySuperClass.UNASSIGNED.name()
                    ,0));

            EditFormController editFormController  = warmUp.getEditFormController();
            editFormController.editDataFromClass(SegmentType.Severity,"Jmeno1", WorkUnitSeverityClass.CRITICAL.name(),
                    WorkUnitSeveritySuperClass.MAJOR.name(), new ClassTable("0_", WorkUnitPriorityClass.HIGH.name(),
                            WorkUnitPrioritySuperClass.HIGH.name(),0), 0);

            severity = warmUp.getDataModel().getSeverity(0);
            
        }

        @Test
        public void testName() {
            assertEquals("Jmeno1",severity.getName());
        }

        @Test
        public void testIdName() {
        assertEquals("0_", lists.getSeverityTypeObservable().get(1).getName());
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