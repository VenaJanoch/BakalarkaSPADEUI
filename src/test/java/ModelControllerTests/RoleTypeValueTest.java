package ModelControllerTests;
import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.Project;
import SPADEPAC.RoleClass;
import SPADEPAC.RoleSuperClass;
import SPADEPAC.RoleType;
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

public class RoleTypeValueTest {

        RoleType roleType;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            FormDataController formDataController = warmUp.getFormDataController();
            Project project = warmUp.getData().getProject();
            FormController formController = warmUp.getFormController();


            formController.createTableItem(SegmentType.RoleType);
            formDataController.saveDataFromRoleTypeForm("Jmeno", "0_Jmeno", RoleClass.ANALYST.name(), RoleSuperClass.TEAM_MEMBER.name(),0);
            roleType = project.getRoleType().get(0);
        }

        @Test
        public void testName() {
            assertEquals("Jmeno", roleType.getName());
        }

        @Test
        public void testIdName() {
        assertEquals("0_Jmeno", lists.getRoleTypeObservable().get(1));
    }


        @Test
        public void testClass() {
            assertEquals(RoleClass.ANALYST.name(), roleType.getRoleClass());
        }

        @Test
        public void testSuperClass() {
            assertEquals(RoleSuperClass.TEAM_MEMBER.name(), roleType.getRoleSuperClass());
        }
}