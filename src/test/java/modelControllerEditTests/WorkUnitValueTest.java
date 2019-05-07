package modelControllerEditTests;

import SPADEPAC.WorkUnit;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

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
        dataModel.getSaveDataModel().createNewWorkUnit(2);
        dataModel.getEditDataModel().editDataInWorkUnit("Test", progress, indicators, name,name, name, indicators, indicators, indicators, indicators, indicators, indicators,
                indicators,estimate, indicators,  indicators, indicators, indicators, indicators,indicators, indicators,indicators, indicators,indicators, indicators,
                dates, indicators, false, indicators, unit, 2);
        dataModel.getEditDataModel().editDataInWorkUnit("Test", progress, indicators, name,name, name, indicators, indicators, indicators, indicators, indicators, indicators,
                indicators,estimate, indicators,  indicators, indicators, indicators, indicators,indicators, indicators,indicators, indicators,indicators, indicators,
                dates, indicators, false, indicators, unit, 2);
        workUnit = dataModel.getWorkUnit(2);
    }

    @Test
    public void testAlias() {
        assertEquals("Test", workUnit.getAlias() );
    }

    @Test
    public void testName() {
        assertEquals("", workUnit.getName().get(0) );
        assertEquals("Test2", workUnit.getName().get(1) );
        assertSame(2, workUnit.getName().size());
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, workUnit.getNameIndicator().get(0) );
        assertSame(0, workUnit.getNameIndicator().get(1) );
        assertSame(2, workUnit.getNameIndicator().size());
    }

    @Test
    public void testDescription() {
        assertEquals("", workUnit.getDescription().get(0) );
        assertEquals("Test2", workUnit.getDescription().get(1) );
        assertSame(2, workUnit.getDescription().size());
    }

    @Test
    public void testProgress() {
        assertSame(23, workUnit.getProgress().get(1) );
        assertSame(2, workUnit.getProgress().size());
    }

    @Test
    public void testIndicatorDescription() {
        assertSame(1, workUnit.getDescriptionIndicator().get(0) );
        assertSame(0, workUnit.getDescriptionIndicator().get(1) );
        assertSame(2, workUnit.getDescriptionIndicator().size());
    }

    @Test
    public void testPriority() {
        assertSame(1, workUnit.getPriorityIndex().get(0) );
        assertSame(0, workUnit.getPriorityIndex().get(1) );
        assertSame(2, workUnit.getPriorityIndex().size());

        assertSame(1, workUnit.getPriorityIndicator().get(0) );
        assertSame(0, workUnit.getPriorityIndicator().get(1) );
        assertSame(2, workUnit.getPriorityIndicator().size());
    }

    @Test
    public void testSeverity() {
        assertSame(1, workUnit.getSeverityIndex().get(0) );
        assertSame(0, workUnit.getSeverityIndex().get(1) );
        assertSame(2, workUnit.getSeverityIndex().size());

        assertSame(1, workUnit.getSeverityIndicator().get(0) );
        assertSame(0, workUnit.getSeverityIndicator().get(1) );
        assertSame(2, workUnit.getSeverityIndicator().size());
    }

    @Test
    public void testStatus() {
        assertSame(1, workUnit.getStatusIndex().get(0) );
        assertSame(0, workUnit.getStatusIndex().get(1) );
        assertSame(2, workUnit.getStatusIndex().size());

        assertSame(1, workUnit.getStatusIndicator().get(0) );
        assertSame(0, workUnit.getStatusIndicator().get(1) );
        assertSame(2, workUnit.getStatusIndicator().size());
    }

    @Test
    public void testResolution() {
        assertSame(1, workUnit.getResolutionIndex().get(0) );
        assertSame(0, workUnit.getResolutionIndex().get(1) );
        assertSame(2, workUnit.getResolutionIndex().size());

        assertSame(1, workUnit.getResolutionIndicator().get(0) );
        assertSame(0, workUnit.getResolutionIndicator().get(1) );
        assertSame(2, workUnit.getResolutionIndicator().size());
    }

    @Test
    public void testRelation() {
        assertSame(1, workUnit.getRelationIndex().get(0) );
        assertSame(0, workUnit.getRelationIndex().get(1) );
        assertSame(2, workUnit.getRelationIndex().size());
    }

    @Test
    public void testType() {
        assertSame(1, workUnit.getTypeIndex().get(0) );
        assertSame(0, workUnit.getTypeIndex().get(1) );
        assertSame(2, workUnit.getTypeIndex().size());

        assertSame(1, workUnit.getTypeIndicator().get(0) );
        assertSame(0, workUnit.getTypeIndicator().get(1) );
        assertSame(2, workUnit.getTypeIndicator().size());
    }
    
    @Test
    public void testWU() {
        assertSame(2, workUnit.getWorkUnits().size());
    }

    @Test
    public void testId() {
        assertSame(2, workUnit.getId());
    }

    @Test
    public void testExist() {
        assertFalse(workUnit.isExist());
    }


}