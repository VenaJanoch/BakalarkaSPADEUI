package editModelTests;

import SPADEPAC.Role;
import controllers.EditFormController;
import controllers.FormController;
import controllers.FormDataController;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.RoleTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RoleNullEditValueTest {

        Role role;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();


            formController.createTableItem(SegmentType.Role);
            formDataController.saveDataFromRoleForm("Test",0, new RoleTable("0_Test", "Desc","2", 0));

            EditFormController editFormController  = warmUp.getEditFormController();
            editFormController.editDataFromRole("", "", 0, new RoleTable("0_", "","", 0), 0);

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