package modelControllerTests;

import controllers.FormController;
import controllers.FormDataController;
import SPADEPAC.Branch;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.BranchTable;

import static org.junit.Assert.*;

public class BranchValuesTest {

        Branch branch;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();
            warmUp.getDataModel().getSaveDataModel().createNewBranch("",0, true);

            branch = warmUp.getDataModel().getBranch(0);
        }

    @Test
    public void testId() {
        assertSame(0, branch.getId());
    }

    @Test
    public void testExist() {
        assertTrue(branch.isExist());
    }


}
