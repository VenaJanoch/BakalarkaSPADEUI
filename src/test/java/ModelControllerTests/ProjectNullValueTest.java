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
    LocalDate date;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        FormDataController formDataController = warmUp.getFormDataController();
        project = warmUp.getData().getProject();

        date = LocalDate.of(2018, 10, 10);
        formDataController.saveDataFromProjectFrom("", null,null ,"");

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

