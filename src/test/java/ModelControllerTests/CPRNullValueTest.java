package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.Branch;
import SPADEPAC.ConfigPersonRelation;
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

import static org.junit.Assert.*;

public class CPRNullValueTest {

        ConfigPersonRelation cpr;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            IdentificatorCreater idCreator = new IdentificatorCreater();
            ProcessGenerator processGenerator = new ProcessGenerator();
            DataManipulator data =  new DataManipulator(processGenerator,idCreator);
            this.lists =  new SegmentLists();
            FileManipulator file = new FileManipulator(processGenerator,data);
            Alerts alerts = new Alerts(file);
            ApplicationController ap = new ApplicationController(file, data, alerts, idCreator, lists);
            DeleteControl deleteControl = new DeleteControl();
            FormController formController = new FormController(idCreator, data, ap, lists, deleteControl);
            for(int i = 0; i < 12; i++){
                formController.getForms().add(null);
            }
            FormDataController formDataController = new FormDataController(formController, deleteControl, lists, data, idCreator);


            formController.createTableItem(SegmentType.ConfigPersonRelation);
            formDataController.saveDataFromCPR("", "",1, 1,0);
            cpr = data.getProject().getCpr().get(0);
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