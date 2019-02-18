package editModelTests;

import SPADEPAC.Branch;
import controllers.EditFormController;
import controllers.FormController;
import controllers.FormDataController;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.BranchTable;

import static org.junit.Assert.*;

public class BranchNullEditValueTest {

        Branch branch;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();
            formController.createTableItem(SegmentType.Branch);
            formDataController.saveDataFromBranch("Test", new BranchTable("Test","YES", true, 0));

            EditFormController editFormController = warmUp.getEditFormController();
            editFormController.editDataFromBranch("", false, new BranchTable("","NO", false, 0));

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
            assertFalse(branch.isIsMain());
        }
}