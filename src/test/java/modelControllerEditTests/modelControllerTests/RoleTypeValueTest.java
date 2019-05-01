package modelControllerEditTests.modelControllerTests;

import SPADEPAC.RoleType;
import controllers.formControllers.FormController;
import controllers.formControllers.FormDataController;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.ClassTable;
import tables.RoleTypeTable;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class RoleTypeValueTest {

        RoleType roleType;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();
            formController.createTableItem(SegmentType.Role_Type);
            formDataController.saveDataFromRoleTypeForm("Test", new ClassTable("Test", "nevim", "nevim", true, 0));
            ArrayList<String> name = new ArrayList<>();
            name.add("");
            name.add("Test2");
            ArrayList<Integer> indicators = new ArrayList<>();
            indicators.add(1);
            indicators.add(0);
            warmUp.getEditFormController().editDataFromRoleType("Test", name, indicators, name, indicators, indicators, indicators, name,
                    name, new RoleTypeTable("Test", "nevim",  false, "nevim", 0), false, 0 );
            roleType = warmUp.getDataModel().getRoleType(0);

        }
    @Test
    public void testAlias() {
        assertEquals("Test", roleType.getAlias() );
    }

    @Test
    public void testName() {
        assertEquals("", roleType.getName().get(0) );
        assertEquals("Test2", roleType.getName().get(1) );
        assertSame(2, roleType.getName().size());
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, roleType.getNameIndicator().get(0) );
        assertSame(0, roleType.getNameIndicator().get(1) );
        assertSame(2, roleType.getNameIndicator().size());
    }

    @Test
    public void testIndicatorRole() {
        assertSame(1, roleType.getRoleTypeClassIndex().get(0) );
        assertSame(1, roleType.getRoleTypeSuperClassIndex().get(0) );
    }

    @Test
    public void testId() {
        assertSame(0, roleType.getId());
    }

    @Test
    public void testExist() {
        assertFalse(roleType.isExist());
    }
}