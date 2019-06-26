package modelControllerCopyTests;

import SPADEPAC.WorkUnit;
import controllers.formControllers.FormDataController;
import controllers.formControllers.FormFillController;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

public class WorkUnitValueTest {

    WorkUnit workUnit;
    SegmentLists lists;
    LocalDate date;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        DataModel dataModel = warmUp.getDataModel();
        ArrayList<String> name = new ArrayList<>();
        name.add("");
        name.add("Test2");
        ArrayList<Integer> indicators = new ArrayList<>();
        indicators.add(1);
        indicators.add(0);
        ArrayList<ArrayList<Integer>> unit = new ArrayList<>();
        unit.add(indicators);
        unit.add(indicators);
        ArrayList<Double> estimate = new ArrayList<>();
        estimate.add(1.0);
        estimate.add(0.0);
        date = LocalDate.of(2018, 10, 10);
        ArrayList<LocalDate> dates = new ArrayList<>();
        dates.add(date);
        ArrayList<Integer> progress = new ArrayList<>();
        progress.add(12);
        progress.add(23);

        FormDataController formDataController = warmUp.getFormDataController();
        formDataController.saveDataFromWorkUnit(null, true);

        dataModel.getEditDataModel().editDataInWorkUnit("Test", progress, indicators, name, name, name, indicators, indicators, indicators, indicators, indicators, indicators,
                indicators, estimate, indicators, indicators, indicators, indicators, indicators, indicators, indicators, indicators, indicators, indicators, indicators,
                dates, indicators, false, indicators, unit, 0);
        dataModel.getEditDataModel().editDataInWorkUnit("Test", progress, indicators, name, name, name, indicators, indicators, indicators, indicators, indicators, indicators,
                indicators, estimate, indicators, indicators, indicators, indicators, indicators, indicators, indicators, indicators, indicators, indicators, indicators,
                dates, indicators, false, indicators, unit, 0);
        FormFillController formFillController = warmUp.getFormFillController();
        formFillController.fillWorkUnitForm(null, 0);
        workUnit = dataModel.getWorkUnit(1);
    }

    @Test
    public void testAlias() {
        assertEquals("1", workUnit.getAlias());
    }

    @Test
    public void testName() {
        assertEquals("", workUnit.getName().get(0));
        assertEquals("Test2", workUnit.getName().get(1));
        assertSame(2, workUnit.getName().size());
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, workUnit.getNameIndicator().get(0));
        assertSame(0, workUnit.getNameIndicator().get(1));
        assertSame(2, workUnit.getNameIndicator().size());
    }

    @Test
    public void testDescription() {
        assertEquals("", workUnit.getDescription().get(0));
        assertEquals("Test2", workUnit.getDescription().get(1));
        assertSame(2, workUnit.getDescription().size());
    }

    @Test
    public void testProgress() {
        assertSame(23, workUnit.getProgress().get(1));
        assertSame(2, workUnit.getProgress().size());
    }

    @Test
    public void testIndicatorDescription() {
        assertSame(1, workUnit.getDescriptionIndicator().get(0));
        assertSame(0, workUnit.getDescriptionIndicator().get(1));
        assertSame(2, workUnit.getDescriptionIndicator().size());
    }


    @Test
    public void testId() {
        assertSame(1, workUnit.getId());
    }

    @Test
    public void testExist() {
        assertFalse(workUnit.isExist());
    }


}