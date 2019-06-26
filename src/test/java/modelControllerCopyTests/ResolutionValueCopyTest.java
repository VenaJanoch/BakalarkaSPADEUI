package modelControllerCopyTests;

import SPADEPAC.Resolution;
import controllers.formControllers.FormDataController;
import controllers.formControllers.FormFillController;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.ClassTable;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

public class ResolutionValueCopyTest {

    Resolution resolution;
    SegmentLists lists;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        FormDataController formDataController = warmUp.getFormDataController();
        formDataController.saveDataFromResolutionForm(null, true);
        ArrayList<String> name = new ArrayList<>();
        name.add("");
        name.add("Test2");
        ArrayList<Integer> indicators = new ArrayList<>();
        indicators.add(1);
        indicators.add(0);
        warmUp.getEditFormController().editDataFromClass(SegmentType.Resolution, "Test", name, indicators, indicators, indicators, name,
                name, new ClassTable("Test", "nevim", "nevim", false, 0), false, 0);
        FormFillController formFillController = warmUp.getFormFillController();
        formFillController.fillResolutionForm(null, 0);
        resolution = warmUp.getDataModel().getResolution(1);

    }

    @Test
    public void testAlias() {
        assertEquals("1", resolution.getAlias());
    }

    @Test
    public void testName() {
        assertEquals("", resolution.getName().get(0));
        assertEquals("Test2", resolution.getName().get(1));
        assertSame(2, resolution.getName().size());
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, resolution.getNameIndicator().get(0));
        assertSame(0, resolution.getNameIndicator().get(1));
        assertSame(2, resolution.getNameIndicator().size());
    }

    @Test
    public void testIndicatorRole() {
        assertSame(1, resolution.getResolutionClassIndex().get(0));
        assertSame(1, resolution.getResolutionSuperClassIndex().get(0));
    }

    @Test
    public void testId() {
        assertSame(1, resolution.getId());
    }

    @Test
    public void testExist() {
        assertFalse(resolution.isExist());
    }
}