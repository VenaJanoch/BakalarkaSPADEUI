package modelControllerTests;

import controllers.FormDataController;
import SPADEPAC.Project;
import org.junit.Before;
import org.junit.Test;

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
        project = warmUp.getDataModel().getProject();

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

