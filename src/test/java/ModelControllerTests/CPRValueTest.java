package ModelControllerTests;

import SPADEPAC.RoleClass;
import SPADEPAC.RoleSuperClass;
import controllers.FormController;
import controllers.FormDataController;
import SPADEPAC.ConfigPersonRelation;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.CPRTable;
import tables.ClassTable;
import tables.CriterionTable;
import tables.RoleTable;

import static org.junit.Assert.*;

public class CPRValueTest {

        ConfigPersonRelation cpr;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();

            for(int i = 0; i < 6; i++){
                formController.createTableItem(SegmentType.RoleType);
                formDataController.saveDataFromRoleTypeForm("Jmeno",new ClassTable("0_Jmeno", RoleClass.ANALYST.name(), RoleSuperClass.TEAM_MEMBER.name(),0));

                formController.createTableItem(SegmentType.Role);
                formDataController.saveDataFromRoleForm("Jmeno", 1, new RoleTable("0_Jmeno", "desc","",0));
            }

            formController.createTableItem(SegmentType.ConfigPersonRelation);
            formDataController.saveDataFromCPR("Jmeno1", 3, new CPRTable("0_Jmeno1","3",0));
            cpr = warmUp.getDataModel().getConfigPersonRelation(0);
        }

        @Test
        public void testName() {
            assertEquals("Jmeno1", cpr.getName() );
        }

        @Test
        public void testIdName() {
        assertEquals("0_Jmeno1", lists.getCPRObservable().get(1).getName());
        }


        @Test
        public void testRoleIndex() {
            assertSame(2, cpr.getPersonIndex());
        }

}