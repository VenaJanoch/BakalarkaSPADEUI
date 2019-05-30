package modelControllerTests;

import controllers.formControllers.FormController;
import controllers.formControllers.FormDataController;
import SPADEPAC.Status;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import static org.junit.Assert.*;

public class StatusValueTest {

        Status status;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();


            warmUp.getDataModel().getSaveDataModel().createNewStatus(0);

            status = warmUp.getDataModel().getStatus(0);
        }

    @Test
    public void testId() {
        assertSame(0, status.getId());
    }

    @Test
    public void testExist() {
        assertTrue(status.isExist());
    }
}