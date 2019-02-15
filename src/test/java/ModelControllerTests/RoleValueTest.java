package ModelControllerTests;

import SPADEPAC.RoleClass;
import SPADEPAC.RoleSuperClass;
import controllers.FormController;
import controllers.FormDataController;
import SPADEPAC.Role;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.ClassTable;
import tables.RoleTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class RoleValueTest {

        Role role;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();

            formController.createTableItem(SegmentType.RoleType);
            formDataController.saveDataFromRoleTypeForm("Jmeno",new ClassTable("0_Jmeno", RoleClass.ANALYST.name(), RoleSuperClass.TEAM_MEMBER.name(),0));

            formController.createTableItem(SegmentType.Role);
            formDataController.saveDataFromRoleForm("Jmeno", 1, new RoleTable("0_Jmeno", "desc","",0));
            role = warmUp.getDataModel().getRole(0);
        }

        @Test
        public void testName() {
            assertEquals("Jmeno",role.getName());
        }

        @Test
        public void testIdName() {
        assertEquals("0_Jmeno", lists.getRoleObservable().get(1).getName());
    }

        @Test
        public void testType() {
        assertSame(0, role.getType());
    }
}