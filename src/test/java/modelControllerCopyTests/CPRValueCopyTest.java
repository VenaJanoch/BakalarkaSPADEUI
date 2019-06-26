package modelControllerCopyTests;

import SPADEPAC.ConfigPersonRelation;
import controllers.formControllers.FormController;
import controllers.formControllers.FormDataController;
import controllers.formControllers.FormFillController;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

public class CPRValueCopyTest {

    ConfigPersonRelation cpr;
    SegmentLists lists;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        FormDataController formDataController = warmUp.getFormDataController();
        FormController formController = warmUp.getFormController();

        formDataController.saveDataFromCPR(null, true);
        ArrayList<String> name = new ArrayList<>();
        name.add("");
        name.add("Test2");
        ArrayList<Integer> indicators = new ArrayList<>();
        indicators.add(1);
        indicators.add(0);
        ArrayList<ArrayList<Integer>> unit = new ArrayList<>();
        unit.add(indicators);
        unit.add(indicators);
        warmUp.getDataModel().getEditDataModel().editDataInCPR("Test", name, indicators, name, indicators, indicators, indicators, false, 0);
        warmUp.getDataModel().getEditDataModel().editDataInCPR("Test", name, indicators, name, indicators, indicators, indicators, false, 0);
        FormFillController formFillController = warmUp.getFormFillController();
        formFillController.fillCPRForm(null, 0);
        cpr = warmUp.getDataModel().getConfigPersonRelation(1);
    }

    @Test
    public void testAlias() {
        assertEquals("1", cpr.getAlias());
    }

    @Test
    public void testName() {
        assertEquals("", cpr.getName().get(0));
        assertEquals("Test2", cpr.getName().get(1));
        assertSame(2, cpr.getName().size());
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, cpr.getNameIndicator().get(0));
        assertSame(0, cpr.getNameIndicator().get(1));
        assertSame(2, cpr.getNameIndicator().size());
    }

    @Test
    public void testDescription() {
        assertEquals("", cpr.getDescription().get(0));
        assertEquals("Test2", cpr.getDescription().get(1));
        assertSame(2, cpr.getDescription().size());
    }

    @Test
    public void testIndicatorDescription() {
        assertSame(1, cpr.getDescriptionIndicator().get(0));
        assertSame(0, cpr.getDescriptionIndicator().get(1));
        assertSame(2, cpr.getDescriptionIndicator().size());
    }

    @Test
    public void testId() {
        assertSame(1, cpr.getId());
    }

    @Test
    public void testExist() {
        assertFalse(cpr.isExist());
    }
}