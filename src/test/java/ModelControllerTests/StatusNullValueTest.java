package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.Project;
import SPADEPAC.Status;
import SPADEPAC.WorkUnitStatusClass;
import SPADEPAC.WorkUnitStatusSuperClass;
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

public class StatusNullValueTest {

        Status status;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            FormDataController formDataController = warmUp.getFormDataController();
            Project project = warmUp.getData().getProject();
            FormController formController = warmUp.getFormController();


            formController.createTableItem(SegmentType.Status);
            formDataController.saveDataFromStatusForm("", new ClassTable("", WorkUnitStatusClass.UNASSIGNED.name(), WorkUnitStatusSuperClass.UNASSIGNED.name()
                    ,0));
            status = project.getStatus().get(0);
        }

        @Test
        public void testName() {
            assertNull(status.getName(), null );
        }

        @Test
        public void testIdName() {
        assertEquals("", lists.getStatusTypeObservable().get(0));
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