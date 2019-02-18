package modelControllerTests;

import SPADEPAC.WorkUnit;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import static org.junit.Assert.*;

public class WorkUnitValueTest {

    WorkUnit workUnit;
    SegmentLists lists;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
      //  FormDataController formDataController = warmUp.getFormDataController();
       // Project project = warmUp.getData().getProject();
       // FormController formController = warmUp.getFormController();

        warmUp.getDataModel().getSaveDataModel().createNewWorkUnit(2);
        workUnit = warmUp.getDataModel().getWorkUnit(2);
        warmUp.getDataModel().addDataToWorkUnit( workUnit,"Jmeno","Desc", "Category", 2, 2, 2, 2,
                2, 2, 2, 56, 65, 23, false, 0, false);

    }

    @Test
    public void testId() {
        assertSame(2,workUnit.getId());
    }

    @Test
    public void testName() {
        assertEquals("Jmeno",workUnit.getName());
    }

    @Test
    public void testDesc() {
        assertEquals("Desc", workUnit.getDescription());
    }

    @Test
    public void testCategory() {
        assertEquals("Category", workUnit.getCategory());
    }

    @Test
    public void testAssigneIndex() {
        assertSame(2, workUnit.getAssigneeIndex());
    }

    @Test
    public void testAuthorIndex() {
        assertSame(2, workUnit.getAuthorIndex());
    }

    @Test
    public void testPriorityIndex() {
        assertSame(2, workUnit.getPriorityIndex());
    }

    @Test
    public void testSeverityIndex() {
        assertSame(2, workUnit.getSeverityIndex());
    }

    @Test
    public void testTypeIndex() {
        assertSame(2, workUnit.getTypeIndex());
    }

    @Test
    public void testResolutionIndex() {
        assertSame(2, workUnit.getResolutionIndex());
    }

    @Test
    public void testStatusIndex() {
        assertSame(2, workUnit.getStatusIndex());
    }

    @Test
    public void testEstimateIndex() {
        assertEquals("23.0", workUnit.getEstimatedTime().toString());
    }

    @Test
    public void testExist() {
        assertFalse(workUnit.isExist());
    }

}