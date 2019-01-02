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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BranchValuesTest {

        Branch branch;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            FormDataController formDataController = warmUp.getFormDataController();
            Project project = warmUp.getData().getProject();
            FormController formController = warmUp.getFormController();
            formController.createTableItem(SegmentType.Branch);
            formDataController.saveDataFromBranch("Jmeno1", "0_Jmeno1", 0, false);
            branch = project.getBranches().get(0);
        }

        @Test
        public void testName() {
            assertEquals("Jmeno1", branch.getName() );
        }

        @Test
        public void testIdName() {
        assertEquals("0_Jmeno1", lists.getBranchObservable().get(1));
    }

        @Test
        public void testMain() {
        assertFalse(branch.isIsMain() );
    }


}
