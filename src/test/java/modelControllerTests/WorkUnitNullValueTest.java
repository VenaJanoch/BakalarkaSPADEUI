package modelControllerTests;

import SPADEPAC.WorkUnit;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import static org.junit.Assert.*;

public class WorkUnitNullValueTest {

    WorkUnit workUnit;
    SegmentLists lists;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        warmUp.getDataModel().getSaveDataModel().createNewWorkUnit(2);
        workUnit = warmUp.getDataModel().getWorkUnit(2);
        warmUp.getDataModel().addDataToWorkUnit(workUnit, null,null, null, 0, 0, 0, 0,
                0, 0, 0, 0, 0, -1, true, 0, false);

    }

    @Test
    public void testName() {
        assertNull(workUnit.getName());
    }

    @Test
    public void testDesc() {
        assertNull(workUnit.getDescription());
    }

    @Test
    public void testCategory() {
        assertNull(workUnit.getCategory());
    }

    @Test
    public void testAssigneIndex() {
        assertSame(0, workUnit.getAssigneeIndex());
    }

    @Test
    public void testAuthorIndex() {
        assertSame(0, workUnit.getAuthorIndex());
    }

    @Test
    public void testPriorityIndex() {
        assertSame(0, workUnit.getPriorityIndex());
    }

    @Test
    public void testSeverityIndex() {
        assertSame(0, workUnit.getSeverityIndex());
    }

    @Test
    public void testTypeIndex() {
        assertSame(0, workUnit.getTypeIndex());
    }

    @Test
    public void testResolutionIndex() {
        assertSame(0, workUnit.getResolutionIndex());
    }

    @Test
    public void testStatusIndex() {
        assertSame(0, workUnit.getStatusIndex());
    }

    @Test
    public void testEstimateIndex() {
        assertEquals("-1.0", workUnit.getEstimatedTime().toString());
    }

    @Test
    public void testExist() {
        assertTrue(workUnit.isExist());
    }

}