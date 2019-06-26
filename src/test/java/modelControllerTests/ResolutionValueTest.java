package modelControllerTests;

import SPADEPAC.Resolution;
import controllers.formControllers.FormController;
import controllers.formControllers.FormDataController;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class ResolutionValueTest {

    Resolution resolution;
    SegmentLists lists;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        FormDataController formDataController = warmUp.getFormDataController();
        FormController formController = warmUp.getFormController();


        warmUp.getDataModel().getSaveDataModel().createNewResolution(0);

        resolution = warmUp.getDataModel().getResolution(0);
    }

    @Test
    public void testId() {
        assertSame(0, resolution.getId());
    }

    @Test
    public void testExist() {
        assertTrue(resolution.isExist());
    }
}