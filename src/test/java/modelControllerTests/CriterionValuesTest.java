package modelControllerTests;

import controllers.FormController;
import controllers.FormDataController;
import SPADEPAC.Criterion;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.CriterionTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CriterionValuesTest {

        Criterion criterion;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();

            formController.createTableItem(SegmentType.Criterion);
            formDataController.saveDataFromCriterionForm("Jmeno1", new CriterionTable("0_Jmeno1" ,"Description1", 0));
            criterion = warmUp.getDataModel().getCriterion(0);
        }

        @Test
        public void testName() {
            assertEquals("Jmeno1", criterion.getName() );
        }

        @Test
        public void testDesc() {
            assertEquals("Description1", criterion.getDescription());
        }

        @Test
        public void testIdName() {
        assertEquals("0_Jmeno1", lists.getCriterionObservable().get(1).getName());
    }


    }
