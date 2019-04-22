package modelControllerTests;

import controllers.formControllers.FormController;
import controllers.formControllers.FormDataController;
import SPADEPAC.ConfigPersonRelation;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;

import static org.junit.Assert.*;

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
            cpr = warmUp.getDataModel().getConfigPersonRelation(0);
        }

    @Test
    public void testId() {
        assertSame(0, cpr.getId());
    }

    @Test
    public void testExist() {
        assertTrue(cpr.isExist());
    }
}