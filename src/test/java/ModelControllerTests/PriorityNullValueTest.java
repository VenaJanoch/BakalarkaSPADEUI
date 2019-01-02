package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.*;
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

import static org.junit.Assert.*;

public class PriorityNullValueTest {

        Priority priority;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            FormDataController formDataController = warmUp.getFormDataController();
            Project project = warmUp.getData().getProject();
            FormController formController = warmUp.getFormController();

            formController.createTableItem(SegmentType.Priority);
            formDataController.saveDataFromPriority("", "", WorkUnitPriorityClass.UNASSIGNED.name(), WorkUnitPrioritySuperClass.UNASSIGNED.name()
                    ,0);
            priority = project.getPriority().get(0);
        }

        @Test
        public void testName() {
            assertNull(priority.getName(), null );
        }

        @Test
        public void testIdName() {
        assertEquals("", lists.getPriorityTypeObservable().get(0));
    }


        @Test
        public void testClass() {
            assertEquals(WorkUnitPriorityClass.UNASSIGNED.name(), priority.getPriorityClass());
        }

        @Test
        public void testSuperClass() {
            assertEquals(WorkUnitPriorityClass.UNASSIGNED.name(), priority.getPrioritySuperClass());
        }
}