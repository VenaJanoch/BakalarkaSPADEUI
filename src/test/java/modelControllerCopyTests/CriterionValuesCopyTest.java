package modelControllerCopyTests;

import SPADEPAC.Criterion;
import controllers.formControllers.FormDataController;
import controllers.formControllers.FormFillController;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import tables.CriterionTable;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

public class CriterionValuesCopyTest {

    Criterion criterion;
    SegmentLists lists;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        FormDataController formDataController = warmUp.getFormDataController();

        formDataController.saveDataFromCriterionForm(null, true);
        ArrayList<String> name = new ArrayList<>();
        name.add("");
        name.add("Test2");
        ArrayList<Integer> indicators = new ArrayList<>();
        indicators.add(1);
        indicators.add(0);

        warmUp.getEditFormController().editDataFromCriterion("Test", name, indicators, name, indicators, new CriterionTable("Test", false, 0), false, 0);
        warmUp.getEditFormController().editDataFromCriterion("Test", name, indicators, name, indicators, new CriterionTable("Test", false, 0), false, 0);
        FormFillController formFillController = warmUp.getFormFillController();
        formFillController.fillCriterionForm(null, 0);
        criterion = warmUp.getDataModel().getCriterion(1);
    }

    @Test
    public void testAlias() {
        assertEquals("1", criterion.getAlias());
    }

    @Test
    public void testName() {
        assertEquals("", criterion.getName().get(0));
        assertEquals("Test2", criterion.getName().get(1));
        assertSame(2, criterion.getName().size());
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, criterion.getNameIndicator().get(0));
        assertSame(0, criterion.getNameIndicator().get(1));
        assertSame(2, criterion.getNameIndicator().size());
    }

    @Test
    public void testDescription() {
        assertEquals("", criterion.getDescription().get(0));
        assertEquals("Test2", criterion.getDescription().get(1));
        assertSame(2, criterion.getDescription().size());
    }

    @Test
    public void testIndicatorDescription() {
        assertSame(1, criterion.getDescriptionIndicator().get(0));
        assertSame(0, criterion.getDescriptionIndicator().get(1));
        assertSame(2, criterion.getDescriptionIndicator().size());
    }

    @Test
    public void testId() {
        assertSame(1, criterion.getId());
    }

    @Test
    public void testExist() {
        assertFalse(criterion.isExist());
    }
}
