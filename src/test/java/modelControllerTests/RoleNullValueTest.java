package modelControllerTests;

import controllers.FormController;
import controllers.FormDataController;
import SPADEPAC.Role;
import org.junit.Before;
import org.junit.Test;
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
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();


            formController.createTableItem(SegmentType.Role);
            formDataController.saveDataFromRoleForm("",0, new RoleTable("0_", "","", 0));
            role = warmUp.getDataModel().getRole(0);
        }

        @Test
        public void testName() {
            assertNull(role.getName());
        }

        @Test
        public void testIdName() {
        assertEquals("0_", lists.getRoleObservable().get(1).getName());
    }
}