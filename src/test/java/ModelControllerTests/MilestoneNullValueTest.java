package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.Criterion;
import SPADEPAC.Milestone;
import SPADEPAC.Project;
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
import tables.MilestoneTable;

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

            WarmUp warmUp = new WarmUp();
            FormDataController formDataController = warmUp.getFormDataController();
            Project project = warmUp.getData().getProject();
            FormController formController = warmUp.getFormController();

            formController.createTableItem(SegmentType.Milestone);
            formDataController.saveDataFromMilestoneForm("","", new ArrayList<>(), new MilestoneTable("","", "", 0));
            milestone = project.getMilestones().get(0);
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
