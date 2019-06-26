package modelControllerTests;

import SPADEPAC.Branch;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class BranchValuesTest {

    Branch branch;
    SegmentLists lists;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        warmUp.getDataModel().getSaveDataModel().createNewBranch(0);

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
