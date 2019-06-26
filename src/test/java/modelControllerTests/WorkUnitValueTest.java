package modelControllerTests;

import SPADEPAC.WorkUnit;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

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

    }

    @Test
    public void testId() {
        assertSame(2, workUnit.getId());
    }

    @Test
    public void testExist() {
        assertTrue(workUnit.isExist());
    }

}