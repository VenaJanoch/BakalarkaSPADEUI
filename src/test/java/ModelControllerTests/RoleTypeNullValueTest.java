package ModelControllerTests;
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

public class RoleTypeNullValueTest {

        RoleType roleType;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();


            formController.createTableItem(SegmentType.RoleType);
            formDataController.saveDataFromRoleTypeForm("", new ClassTable("", RoleClass.UNASSIGNED.name(), RoleSuperClass.UNASSIGNED.name(),0));
            roleType = warmUp.getDataModel().getRoleType(0);
        }

        @Test
        public void testName() {
            assertNull(roleType.getName(), null );
        }

        @Test
        public void testIdName() {
        assertEquals("", lists.getRoleTypeObservable().get(0).getName());
    }


        @Test
        public void testClass() {
            assertEquals(RoleClass.UNASSIGNED.name(), roleType.getRoleClass());
        }

        @Test
        public void testSuperClass() {
            assertEquals(RoleSuperClass.UNASSIGNED.name(), roleType.getRoleSuperClass());
        }
}