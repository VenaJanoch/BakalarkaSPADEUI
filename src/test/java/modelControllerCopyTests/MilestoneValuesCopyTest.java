package modelControllerCopyTests;

import SPADEPAC.Milestone;
import controllers.formControllers.FormDataController;
import controllers.formControllers.FormFillController;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

public class MilestoneValuesCopyTest {

    Milestone milestone;
    SegmentLists lists;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        FormDataController formDataController = warmUp.getFormDataController();

        ArrayList<String> name = new ArrayList<>();
        name.add("");
        name.add("Test2");
        ArrayList<Integer> indicators = new ArrayList<>();
        indicators.add(1);
        indicators.add(0);
        ArrayList<ArrayList<Integer>> unit = new ArrayList<>();
        unit.add(indicators);
        unit.add(indicators);

        formDataController.saveDataFromMilestoneForm(null, true);

        warmUp.getDataModel().getEditDataModel().editDataInMilestone("Test", name, name, indicators, indicators, indicators, unit, false, 0);
        warmUp.getDataModel().getEditDataModel().editDataInMilestone("Test", name, name, indicators, indicators, indicators, unit, false, 0);
        FormFillController formFillController = warmUp.getFormFillController();
        formFillController.fillMilestoneForm(null, 0);
        milestone = warmUp.getDataModel().getMilestone(1);
    }

    @Test
    public void testAlias() {
        assertEquals("1", milestone.getAlias());
    }

    @Test
    public void testName() {
        assertEquals("", milestone.getName().get(0));
        assertEquals("Test2", milestone.getName().get(1));
        assertSame(2, milestone.getName().size());
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, milestone.getNameIndicator().get(0));
        assertSame(0, milestone.getNameIndicator().get(1));
        assertSame(2, milestone.getNameIndicator().size());
    }

    @Test
    public void testDescription() {
        assertEquals("", milestone.getDescription().get(0));
        assertEquals("Test2", milestone.getDescription().get(1));
        assertSame(2, milestone.getDescription().size());
    }

    @Test
    public void testIndicatorDescription() {
        assertSame(1, milestone.getDescriptionIndicator().get(0));
        assertSame(0, milestone.getDescriptionIndicator().get(1));
        assertSame(2, milestone.getDescriptionIndicator().size());
    }

    @Test
    public void testId() {
        assertSame(1, milestone.getId());
    }

    @Test
    public void testExist() {
        assertFalse(milestone.isExist());
    }
}
