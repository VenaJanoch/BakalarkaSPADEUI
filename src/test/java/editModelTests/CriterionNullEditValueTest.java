package editModelTests;

import SPADEPAC.Criterion;
import controllers.EditFormController;
import controllers.FormController;
import controllers.FormDataController;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.CriterionTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CriterionNullEditValueTest {

        Criterion criterion;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();

            formController.createTableItem(SegmentType.Criterion);
            formDataController.saveDataFromCriterionForm("Jmeno", new CriterionTable("jmeno","nevim",0));

            EditFormController editFormController = warmUp.getEditFormController();
            editFormController.editDataFromCriterion("", "", new CriterionTable("","", 0), 0);


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
