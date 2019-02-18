package modelControllerTests;
import static org.junit.Assert.*;

import controllers.FormController;
import controllers.FormDataController;
import SPADEPAC.Criterion;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.CriterionTable;

public class CriterionNullValueTest {

        Criterion criterion;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();

            formController.createTableItem(SegmentType.Criterion);
            formDataController.saveDataFromCriterionForm("", new CriterionTable("0_","",0));
            criterion = warmUp.getDataModel().getCriterion(0);
        }

        @Test
        public void testName() {
            assertNull(criterion.getName(), null );
        }

        @Test
        public void testClass() {
            assertNull(criterion.getDescription(), null);
        }

    @Test
    public void testIdName() {
        assertEquals("0_", lists.getCriterionObservable().get(1).getName());
    }
    }
