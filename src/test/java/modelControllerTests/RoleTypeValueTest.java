package modelControllerTests;
import controllers.formControllers.FormController;
import controllers.formControllers.FormDataController;
import SPADEPAC.RoleType;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import static org.junit.Assert.*;

public class RoleTypeValueTest {

        RoleType roleType;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();

            warmUp.getDataModel().getSaveDataModel().createNewRoleType(0);
            roleType =warmUp.getDataModel().getRoleType(0);
        }

    @Test
    public void testId() {
        assertSame(0, roleType.getId());
    }

    @Test
    public void testExist() {
        assertTrue(roleType.isExist());
    }
}