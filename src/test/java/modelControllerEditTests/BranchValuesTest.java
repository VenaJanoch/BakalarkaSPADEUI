package modelControllerEditTests;

import SPADEPAC.Branch;
import controllers.formControllers.EditFormController;
import controllers.formControllers.FormDataController;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import tables.BranchTable;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class BranchValuesTest {

        Branch branch;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            warmUp.getDataModel().getSaveDataModel().createNewBranch("",0, true);
            formDataController.saveDataFromBranch(0 + "", new BranchTable("", "YES", true, true, 0));
            EditFormController editFormController = warmUp.getEditFormController();
            ArrayList<String> name = new ArrayList<>();
            name.add("");
            name.add("Test2");
            ArrayList<Integer> indicators = new ArrayList<>();
            indicators.add(1);
            indicators.add(0);
            editFormController.editDataFromBranch("Jmeno1", name, indicators, true, true, new BranchTable("Jmeno1", "YES", true, true, 0));

            branch = warmUp.getDataModel().getBranch(0);
        }

    @Test
    public void testAlias() {
        assertEquals("Jmeno1", branch.getAlias() );
    }

    @Test
    public void testName() {
        assertEquals("", branch.getName().get(0) );
        assertEquals("Test2", branch.getName().get(1) );
    }

    @Test
    public void testIndicator() {
        assertSame(1, branch.getNameIndicator().get(0) );
        assertSame(0, branch.getNameIndicator().get(1) );
    }

    @Test
    public void testIdName() {
        assertEquals("Jmeno1", lists.getBranchObservable().get(1).getAlias());
    }

    @Test
    public void testMain() {
        assertTrue(branch.isIsMain() );
    }


}
