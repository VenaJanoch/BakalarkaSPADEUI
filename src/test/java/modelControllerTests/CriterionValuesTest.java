package modelControllerTests;

import controllers.formControllers.FormController;
import controllers.formControllers.FormDataController;
import SPADEPAC.Criterion;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import static org.junit.Assert.*;

public class CriterionValuesTest {

        Criterion criterion;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();

            warmUp.getDataModel().getSaveDataModel().createNewCriterion(0);

            criterion = warmUp.getDataModel().getCriterion(0);
        }

    @Test
    public void testId() {
        assertSame(0, criterion.getId());
    }

    @Test
    public void testExist() {
        assertTrue(criterion.isExist());
    }

    }
