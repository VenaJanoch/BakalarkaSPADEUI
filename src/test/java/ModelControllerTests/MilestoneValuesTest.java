package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.Criterion;
import SPADEPAC.Milestone;
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

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MilestoneValuesTest {

        Milestone milestone;
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
            formController.createTableItem(SegmentType.Milestone);
            ArrayList list = new ArrayList();
            list.add(1);
            list.add(3);
            list.add(4);
            formDataController.saveDataFromMilestoneForm("Jmeno1", "0_Jmeno1" ,list, 0);
            milestone = data.getProject().getMilestones().get(0);
        }

        @Test
        public void testName() {
            assertEquals("Jmeno1", milestone.getName() );
        }

        @Test
        public void testDesc() {
         //TODO upravit po konzultaci   assertEquals("Description1", milestone.getDescription());
        }

        @Test
        public void testIdName() {
        assertEquals("0_Jmeno1", lists.getMilestoneObservable().get(1));
        }

        @Test
        public void testIndex() {
            ArrayList list1 = new ArrayList();

            list1.add(1);
            list1.add(3);
            list1.add(4);
            assertArrayEquals(list1.toArray(), milestone.getCriteriaIndexs().toArray());
        }

    }
