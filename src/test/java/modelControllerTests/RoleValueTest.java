package modelControllerTests;

import SPADEPAC.Person;
import controllers.formControllers.FormController;
import controllers.formControllers.FormDataController;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import static org.junit.Assert.*;

public class RoleValueTest {

        Person role;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();

            warmUp.getDataModel().getSaveDataModel().createNewPerson(2);
            role = warmUp.getDataModel().getRole(2);
        }
    @Test
    public void testId() {
        assertSame(2, role.getId());
    }

    @Test
    public void testExist() {
        assertTrue(role.isExist());
    }

    @Test
    public void testCount() {
        assertSame(1, role.getCount());
    }
}