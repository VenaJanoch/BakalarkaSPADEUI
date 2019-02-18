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

public class RoleTypeEditValueTest {

        RoleType roleType;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();

            formController.createTableItem(SegmentType.RoleType);
            formDataController.saveDataFromRoleTypeForm("Jmeno",new ClassTable("0_Jmeno", RoleClass.UNASSIGNED.name(),
                    RoleSuperClass.UNASSIGNED.name(),0));

            EditFormController editFormController  = warmUp.getEditFormController();
            editFormController.editDataFromClass(SegmentType.RoleType,"Jmeno", RoleClass.ANALYST.name(), RoleSuperClass.TEAM_MEMBER.name(),
                    new ClassTable("0_Jmeno", RoleClass.ANALYST.name(), RoleSuperClass.TEAM_MEMBER.name(), 0),0);


            roleType = warmUp.getDataModel().getRoleType(0);
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