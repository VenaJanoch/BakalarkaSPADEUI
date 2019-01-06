package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.Project;
import SPADEPAC.Role;
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
import tables.RoleTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RoleNullValueTest {

        Role role;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            FormDataController formDataController = warmUp.getFormDataController();
            Project project = warmUp.getData().getProject();
            FormController formController = warmUp.getFormController();


            formController.createTableItem(SegmentType.Role);
            formDataController.saveDataFromRoleForm("",0, new RoleTable("", "","", 0));
            role = project.getRoles().get(0);
        }

        @Test
        public void testName() {
            assertNull(role.getName());
        }

        @Test
        public void testIdName() {
        assertEquals("", lists.getRoleTypeObservable().get(0));
    }
}