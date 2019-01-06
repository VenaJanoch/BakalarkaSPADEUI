package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.Project;
import SPADEPAC.Severity;
import SPADEPAC.WorkUnitSeverityClass;
import SPADEPAC.WorkUnitSeveritySuperClass;
import XML.ProcessGenerator;
import model.DataManipulator;
import model.FileManipulator;
import model.IdentificatorCreater;
import org.junit.Before;
import org.junit.Test;
import services.Alerts;
import services.DeleteControl;
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
            FormDataController formDataController = warmUp.getFormDataController();
            Project project = warmUp.getData().getProject();
            FormController formController = warmUp.getFormController();


            formController.createTableItem(SegmentType.Severity);
            formDataController.saveDataFromSeverity("", new ClassTable("", WorkUnitSeverityClass.UNASSIGNED.name(), WorkUnitSeveritySuperClass.UNASSIGNED.name()
                    ,0));
            severity = project.getSeverity().get(0);
        }

        @Test
        public void testName() {
            assertNull(severity.getName(), null );
        }

        @Test
        public void testIdName() {
        assertEquals("", lists.getSeverityTypeObservable().get(0));
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