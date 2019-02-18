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

public class CriterionEditValuesTest {

        Criterion criterion;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();

            formController.createTableItem(SegmentType.Criterion);
            formDataController.saveDataFromCriterionForm("Test", new CriterionTable("0_Test" ,"Descriion1", 0));

            EditFormController editFormController = warmUp.getEditFormController();
            editFormController.editDataFromCriterion("Jmeno1", "Description1", new CriterionTable("","", 0), 0);


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
