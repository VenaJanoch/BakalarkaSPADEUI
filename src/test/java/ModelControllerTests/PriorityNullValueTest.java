package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.ConfigPersonRelation;
import SPADEPAC.Priority;
import SPADEPAC.WorkUnitPriorityClass;
import SPADEPAC.WorkUnitPrioritySuperClass;
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

public class PriorityNullValueTest {

        Priority priority;
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


            formController.createTableItem(SegmentType.Priority);
            formDataController.saveDataFromPriority("", "", WorkUnitPriorityClass.UNASSIGNED.name(), WorkUnitPrioritySuperClass.UNASSIGNED.name()
                    ,0);
            priority = data.getProject().getPriority().get(0);
        }

        @Test
        public void testName() {
            assertNull(priority.getName(), null );
        }

        @Test
        public void testIdName() {
        assertEquals("", lists.getPriorityTypeObservable().get(0));
    }


        @Test
        public void testClass() {
            assertEquals(WorkUnitPriorityClass.UNASSIGNED.name(), priority.getPriorityClass());
        }

        @Test
        public void testSuperClass() {
            assertEquals(WorkUnitPriorityClass.UNASSIGNED.name(), priority.getPrioritySuperClass());
        }
}