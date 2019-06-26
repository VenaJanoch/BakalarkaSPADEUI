package modelControllerTests;

import SPADEPAC.Priority;
import controllers.formControllers.FormController;
import controllers.formControllers.FormDataController;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class PriorityValueTest {

    Priority priority;
    SegmentLists lists;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        FormDataController formDataController = warmUp.getFormDataController();
        FormController formController = warmUp.getFormController();

        warmUp.getDataModel().getSaveDataModel().createNewPriority(0);

        priority = warmUp.getDataModel().getPriority(0);

    }

    @Test
    public void testId() {
        assertSame(0, priority.getId());
    }

    @Test
    public void testExist() {
        assertTrue(priority.isExist());
    }
}