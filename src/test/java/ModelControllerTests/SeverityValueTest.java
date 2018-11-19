package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.Severity;
import SPADEPAC.WorkUnitSeverityClass;
import SPADEPAC.WorkUnitSeveritySuperClass;
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

public class SeverityValueTest {

        Severity severity;
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


            formController.createTableItem(SegmentType.Severity);
            formDataController.saveDataFromSeverity("Jmeno1", "0_Jmeno1", WorkUnitSeverityClass.CRITICAL.name(), WorkUnitSeveritySuperClass.MAJOR.name()
                    ,0);
            severity = data.getProject().getSeverity().get(0);
            
        }

        @Test
        public void testName() {
            assertEquals("Jmeno1",severity.getName());
        }

        @Test
        public void testIdName() {
        assertEquals("", lists.getSeverityTypeObservable().get(0));
    }

        @Test
        public void testClass() {
            assertEquals(WorkUnitSeverityClass.CRITICAL.name(), severity.getSeverityClass());
        }

        @Test
        public void testSuperClass() {
            assertEquals(WorkUnitSeveritySuperClass.MAJOR.name(), severity.getSeveritySuperClass());
        }
}