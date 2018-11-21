package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.Configuration;
import SPADEPAC.Project;
import SPADEPAC.WorkUnitStatusClass;
import SPADEPAC.WorkUnitStatusSuperClass;
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

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class ProjectNullValueTest {

    Project project;
    SegmentLists lists;
    LocalDate date;

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

        date = LocalDate.of(2018, 10, 10);
        formDataController.saveDataFromProjectFrom("", null,null ,"");
        project = data.getProject();
    }

    @Test
    public void testName() {
        assertNull(project.getName());
    }

    @Test
    public void testClass() {
        assertNull(project.getDescription());
    }

    @Test
    public void testStartDate() {
        assertNull(project.getStartDate());
    }

    @Test
    public void testEndDate() {
        assertNull(project.getEndDate());
    }

}

