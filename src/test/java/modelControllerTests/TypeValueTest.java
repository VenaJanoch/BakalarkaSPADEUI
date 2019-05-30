package modelControllerTests;

import controllers.formControllers.FormController;
import controllers.formControllers.FormDataController;
import SPADEPAC.Type;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import static org.junit.Assert.*;

public class TypeValueTest {

        Type type;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();


            warmUp.getDataModel().getSaveDataModel().createNewType(0);

            type = warmUp.getDataModel().getType(0);
        }

    @Test
    public void testId() {
        assertSame(0, type.getId());
    }

    @Test
    public void testExist() {
        assertTrue(type.isExist());
    }
}