package modelControllerEditTests;

import SPADEPAC.ConfigPersonRelation;
import controllers.formControllers.FormController;
import controllers.formControllers.FormDataController;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class CPRValueTest {

        ConfigPersonRelation cpr;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();

            formController.createTableItem(SegmentType.Config_Person_Relation);
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
            cpr = warmUp.getDataModel().getConfigPersonRelation(0);
        }

    @Test
    public void testAlias() {
        assertEquals("Test", cpr.getAlias() );
    }

    @Test
    public void testName() {
        assertEquals("", cpr.getName().get(0) );
        assertEquals("Test2", cpr.getName().get(1) );
        assertSame(2, cpr.getName().size());
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, cpr.getNameIndicator().get(0) );
        assertSame(0, cpr.getNameIndicator().get(1) );
        assertSame(2, cpr.getNameIndicator().size());
    }

    @Test
    public void testDescription() {
        assertEquals("", cpr.getDescription().get(0) );
        assertEquals("Test2", cpr.getDescription().get(1) );
        assertSame(2, cpr.getDescription().size());
    }

    @Test
    public void testIndicatorDescription() {
        assertSame(1, cpr.getDescriptionIndicator().get(0) );
        assertSame(0, cpr.getDescriptionIndicator().get(1) );
        assertSame(2, cpr.getDescriptionIndicator().size());
    }

    @Test
    public void testIndicatorRole() {
        assertSame(1, cpr.getPersonIndex().get(0) );
        assertSame(0, cpr.getPersonIndex().get(1) );
        assertSame(2, cpr.getPersonIndex().size());

        assertSame(1, cpr.getPersonIndicator().get(0) );
        assertSame(0, cpr.getPersonIndicator().get(1) );
        assertSame(2, cpr.getPersonIndicator().size());
    }

    @Test
    public void testId() {
        assertSame(0, cpr.getId());
    }

    @Test
    public void testExist() {
        assertFalse(cpr.isExist());
    }
}