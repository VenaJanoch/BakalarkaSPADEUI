package ModelControllerTests;

import controllers.FormController;
import controllers.FormDataController;
import SPADEPAC.ConfigPersonRelation;
import org.junit.Before;
import org.junit.Test;
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
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();


            formController.createTableItem(SegmentType.ConfigPersonRelation);
            formDataController.saveDataFromCPR("", 0, new CPRTable("","",0));
            cpr = warmUp.getDataModel().getConfigPersonRelation(0);
        }

        @Test
        public void testName() {
            assertNull(cpr.getName(), null );
        }

        @Test
        public void testIdName() {
        assertEquals("", lists.getCPRObservable().get(0).getName());
    }


        @Test
        public void testRoleIndex() {
            assertSame(-1, cpr.getPersonIndex());
        }

       // @Test
       // public void testConfigIndex() {
       //     assertSame(0, cpr.getConfigurationId() );
       // }
}