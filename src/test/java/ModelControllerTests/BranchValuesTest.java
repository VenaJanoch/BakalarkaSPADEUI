package ModelControllerTests;

import controllers.FormController;
import controllers.FormDataController;
import SPADEPAC.Branch;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.BranchTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BranchValuesTest {

        Branch branch;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();
            formController.createTableItem(SegmentType.Branch);
            formDataController.saveDataFromBranch("Jmeno1", new BranchTable("0_Jmeno1","NO", false, 0));
            branch = warmUp.getDataModel().getBranch(0);
        }

        @Test
        public void testName() {
            assertEquals("Jmeno1", branch.getName() );
        }

        @Test
        public void testIdName() {
        assertEquals("0_Jmeno1", lists.getBranchObservable().get(1).getName());
    }

        @Test
        public void testMain() {
        assertFalse(branch.isIsMain() );
    }


}
