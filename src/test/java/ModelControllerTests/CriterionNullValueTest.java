package ModelControllerTests;
import static org.junit.Assert.*;

import java.time.LocalDate;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.Criterion;
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

public class CriterionNullValueTest {

        Criterion criterion;
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
            formController.createTableItem(SegmentType.Criterion);
            formDataController.saveDataFromCriterionForm("", "","", 0);
            criterion = data.getProject().getCriterions().get(0);
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
