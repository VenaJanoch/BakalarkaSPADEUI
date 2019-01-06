package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
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
import tables.CriterionTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CriterionValuesTest {

        Criterion criterion;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            FormDataController formDataController = warmUp.getFormDataController();
            Project project = warmUp.getData().getProject();
            FormController formController = warmUp.getFormController();

            formController.createTableItem(SegmentType.Criterion);
            formDataController.saveDataFromCriterionForm("Jmeno1", new CriterionTable("0_Jmeno1" ,"Description1", 0));
            criterion = project.getCriterions().get(0);
        }

        @Test
        public void testName() {
            assertEquals("Jmeno1", criterion.getName() );
        }

        @Test
        public void testDesc() {
            assertEquals("Description1", criterion.getDescription());
        }

        @Test
        public void testIdName() {
        assertEquals("0_Jmeno1", lists.getCriterionObservable().get(1));
    }


    }
