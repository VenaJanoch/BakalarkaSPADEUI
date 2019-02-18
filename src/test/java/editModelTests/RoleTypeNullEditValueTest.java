package editModelTests;

import SPADEPAC.RoleClass;
import SPADEPAC.RoleSuperClass;
import SPADEPAC.RoleType;
import controllers.EditFormController;
import controllers.FormController;
import controllers.FormDataController;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.ClassTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RoleTypeNullEditValueTest {

        RoleType roleType;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();


            formController.createTableItem(SegmentType.RoleType);
            formDataController.saveDataFromRoleTypeForm("Test", new ClassTable("Test_0", RoleClass.DEVELOPER.name(), RoleSuperClass.STAKEHOLDER.name(),0));

            EditFormController editFormController  = warmUp.getEditFormController();
            editFormController.editDataFromClass(SegmentType.RoleType,"", RoleClass.UNASSIGNED.name(), RoleSuperClass.UNASSIGNED.name(),
                    new ClassTable("0_", "","", 0),0);

            roleType = warmUp.getDataModel().getRoleType(0);
        }

        @Test
        public void testName() {
            assertNull(roleType.getName(), null );
        }

        @Test
        public void testIdName() {
        assertEquals("0_", lists.getRoleTypeObservable().get(1).getName());
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