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

import static org.junit.Assert.assertEquals;

public class SeverityValueTest {

        Severity severity;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            FormDataController formDataController = warmUp.getFormDataController();
            Project project = warmUp.getData().getProject();
            FormController formController = warmUp.getFormController();


            formController.createTableItem(SegmentType.Severity);
            formDataController.saveDataFromSeverity("Jmeno1", "0_Jmeno1", WorkUnitSeverityClass.CRITICAL.name(), WorkUnitSeveritySuperClass.MAJOR.name()
                    ,0);
            severity = project.getSeverity().get(0);
            
        }

        @Test
        public void testName() {
            assertEquals("Jmeno1",severity.getName());
        }

        @Test
        public void testIdName() {
        assertEquals("", lists.getSeverityTypeObservable().get(0));
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