package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.Branch;
import SPADEPAC.ConfigPersonRelation;
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
import tables.CPRTable;

import static org.junit.Assert.*;

public class CPRNullValueTest {

        ConfigPersonRelation cpr;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            FormDataController formDataController = warmUp.getFormDataController();
            Project project = warmUp.getData().getProject();
            FormController formController = warmUp.getFormController();


            formController.createTableItem(SegmentType.ConfigPersonRelation);
            formDataController.saveDataFromCPR("", 0,1, new CPRTable("","",0));
            cpr = project.getCpr().get(0);
        }

        @Test
        public void testName() {
            assertNull(cpr.getName(), null );
        }

        @Test
        public void testIdName() {
        assertEquals("", lists.getCPRObservable().get(0));
    }


        @Test
        public void testRoleIndex() {
            assertSame(0, cpr.getPersonIndex());
        }

        @Test
        public void testConfigIndex() {
            assertSame(0, cpr.getConfigurationIndex() );
        }
}