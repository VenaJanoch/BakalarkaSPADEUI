package modelControllerTests;

import controllers.formControllers.FormController;
import controllers.formControllers.FormDataController;
import SPADEPAC.Severity;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class SeverityValueTest {

        Severity severity;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();

            FormController formController = warmUp.getFormController();


            warmUp.getDataModel().getSaveDataModel().createNewSeverity(0);

            severity = warmUp.getDataModel().getSeverity(0);
            
        }

    @Test
    public void testId() {
        assertSame(0, severity.getId());
    }

    @Test
    public void testExist() {
        assertTrue(severity.isExist());
    }

}