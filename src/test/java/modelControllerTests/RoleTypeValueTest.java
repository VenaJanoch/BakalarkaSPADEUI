package modelControllerTests;
import controllers.FormController;
import controllers.FormDataController;
import SPADEPAC.RoleClass;
import SPADEPAC.RoleSuperClass;
import SPADEPAC.RoleType;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.ClassTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RoleTypeValueTest {

        RoleType roleType;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();

            formController.createTableItem(SegmentType.RoleType);
            formDataController.saveDataFromRoleTypeForm("Jmeno",new ClassTable("0_Jmeno", RoleClass.ANALYST.name(), RoleSuperClass.TEAM_MEMBER.name(),0));
            roleType =warmUp.getDataModel().getRoleType(0);
        }

        @Test
        public void testName() {
            assertEquals("Jmeno", roleType.getName());
        }

        @Test
        public void testIdName() {
        assertEquals("0_Jmeno", lists.getRoleTypeObservable().get(1).getName());
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