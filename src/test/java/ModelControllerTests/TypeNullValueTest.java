package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.Project;
import SPADEPAC.Type;
import SPADEPAC.WorkUnitTypeClass;
import SPADEPAC.WorkUnitTypeSuperClass;
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

public class TypeNullValueTest {

        Type type;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            FormDataController formDataController = warmUp.getFormDataController();
            Project project = warmUp.getData().getProject();
            FormController formController = warmUp.getFormController();

            formController.createTableItem(SegmentType.Type);
            formDataController.saveDataFromTypeForm("", "", WorkUnitTypeClass.UNASSIGNED.name(), WorkUnitTypeSuperClass.UNASSIGNED.name()
                    ,0);
            type = project.getTypes().get(0);
        }

        @Test
        public void testName() {
            assertNull(type.getName(), null );
        }

        @Test
        public void testIdName() {
        assertEquals("", lists.getTypeObservable().get(0));
    }


        @Test
        public void testClass() {
            assertEquals(WorkUnitTypeClass.UNASSIGNED.name(), type.getTypeClass());
        }

        @Test
        public void testSuperClass() {
            assertEquals(WorkUnitTypeClass.UNASSIGNED.name(), type.getTypeSuperClass());
        }
}