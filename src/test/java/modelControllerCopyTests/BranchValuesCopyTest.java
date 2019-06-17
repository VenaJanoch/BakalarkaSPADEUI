package modelControllerCopyTests;

import SPADEPAC.Branch;
import controllers.formControllers.EditFormController;
import controllers.formControllers.FormDataController;
import controllers.formControllers.FormFillController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.BasicTable;
import tables.BranchTable;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class BranchValuesCopyTest {

        Branch branch;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            formDataController.saveDataFromBranch(null, true);

            EditFormController editFormController = warmUp.getEditFormController();
            ArrayList<String> name = new ArrayList<>();
            name.add("");
            name.add("Test2");
            ArrayList<Integer> indicators = new ArrayList<>();
            indicators.add(1);
            indicators.add(0);
            BranchTable branchTable = new BranchTable("Jmeno1", "YES", true, true, 0);
            editFormController.editDataFromBranch("Jmeno1", name, indicators, true, true, branchTable );
            FormFillController formFillController = warmUp.getFormFillController();
            formFillController.fillBranchForm(null, 0);
            branch = warmUp.getDataModel().getBranch(1);
        }

    @Test
    public void testAlias() {
        assertEquals("1", branch.getAlias() );
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
        assertFalse(branch.isIsMain() );
    }


}
