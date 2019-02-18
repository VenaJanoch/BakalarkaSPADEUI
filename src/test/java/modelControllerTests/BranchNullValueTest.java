package modelControllerTests;

import controllers.FormController;
import controllers.FormDataController;
import SPADEPAC.Branch;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.BranchTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class BranchNullValueTest {

        Branch branch;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();
            formController.createTableItem(SegmentType.Branch);
            formDataController.saveDataFromBranch("", new BranchTable("0_","YES", true, 0));
            branch = warmUp.getDataModel().getBranch(0);
        }

        @Test
        public void testName() {
            assertNull(branch.getName(), null );
        }

        @Test
        public void testIdName() {
        assertEquals("0_", lists.getBranchObservable().get(1).getName());
    }


        @Test
        public void testMain() {
            assertTrue(branch.isIsMain() );
        }


}