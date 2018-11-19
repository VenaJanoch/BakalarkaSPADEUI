package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.Criterion;
import SPADEPAC.Milestone;
import XML.ProcessGenerator;
import javafx.collections.ObservableList;
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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MilestoneNullValueTest {

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
            formDataController.saveDataFromMilestoneForm("", "", new ArrayList<>(), 0);
            milestone = data.getProject().getMilestones().get(0);
        }

        @Test
        public void testName() {
            assertNull(milestone.getName(), null );
        }

        @Test
        public void testClass() {
            assertNull(milestone.getDescription(), null);
        }

        @Test
        public void testIdName() {
            assertEquals("", lists.getMilestoneObservable().get(0));
        }

        @Test
        public void testCriterion() {
            assertNotNull(null, milestone.getCriteriaIndexs());
        }

        }
