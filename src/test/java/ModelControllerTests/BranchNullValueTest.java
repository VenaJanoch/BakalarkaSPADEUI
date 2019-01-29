package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.Branch;
import SPADEPAC.Criterion;
import SPADEPAC.Project;
import XML.ProcessGenerator;
import model.DataManipulator;
import model.FileManipulator;
import model.IdentificatorCreater;
import org.junit.Before;
import org.junit.Test;
import services.Alerts;
import services.DeleteControl;
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
            FormDataController formDataController = warmUp.getFormDataController();
            Project project = warmUp.getData().getProject();
            FormController formController = warmUp.getFormController();
            formController.createTableItem(SegmentType.Branch);
            formDataController.saveDataFromBranch("", new BranchTable("","YES", true, 0));
            branch = project.getBranches().get(0);
        }

        @Test
        public void testName() {
            assertNull(branch.getName(), null );
        }

        @Test
        public void testIdName() {
        assertEquals("", lists.getCriterionObservable().get(0));
    }


        @Test
        public void testMain() {
            assertTrue(branch.isIsMain() );
        }
}