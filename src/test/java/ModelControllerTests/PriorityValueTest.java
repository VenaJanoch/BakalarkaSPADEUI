package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.Priority;
import SPADEPAC.Project;
import SPADEPAC.WorkUnitPriorityClass;
import SPADEPAC.WorkUnitPrioritySuperClass;
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
import static org.junit.Assert.assertNull;

public class PriorityValueTest {

        Priority priority;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            FormDataController formDataController = warmUp.getFormDataController();
            Project project = warmUp.getData().getProject();
            FormController formController = warmUp.getFormController();

            formController.createTableItem(SegmentType.Priority);
            formDataController.saveDataFromPriority("Jmeno1", "0_Jmeno1", WorkUnitPriorityClass.HIGH.name(), WorkUnitPrioritySuperClass.HIGH.name()
                    ,0);
            priority = project.getPriority().get(0);

        }

        @Test
        public void testName() {
            assertEquals("Jmeno1",priority.getName());
        }

        @Test
        public void testIdName() {
        assertEquals("", lists.getPriorityTypeObservable().get(0));
    }

        @Test
        public void testClass() {
            assertEquals(WorkUnitPriorityClass.HIGH.name(), priority.getPriorityClass());
        }

        @Test
        public void testSuperClass() {
            assertEquals(WorkUnitPriorityClass.HIGH.name(), priority.getPrioritySuperClass());
        }
}