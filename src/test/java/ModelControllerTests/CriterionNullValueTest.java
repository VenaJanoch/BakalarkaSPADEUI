package ModelControllerTests;
import static org.junit.Assert.*;

import java.time.LocalDate;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.Criterion;
import SPADEPAC.Project;
import XML.ProcessGenerator;
import javafx.stage.Stage;
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

public class CriterionNullValueTest {

        Criterion criterion;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            FormDataController formDataController = warmUp.getFormDataController();
            Project project = warmUp.getData().getProject();
            FormController formController = warmUp.getFormController();

            formController.createTableItem(SegmentType.Criterion);
            formDataController.saveDataFromCriterionForm("", new CriterionTable("","",0));
            criterion = project.getCriterions().get(0);
        }

        @Test
        public void testName() {
            assertNull(criterion.getName(), null );
        }

        @Test
        public void testClass() {
            assertNull(criterion.getDescription(), null);
        }

    @Test
    public void testIdName() {
        assertEquals("", lists.getCriterionObservable().get(0));
    }
    }
