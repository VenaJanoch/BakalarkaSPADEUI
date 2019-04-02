package modelControllerTests;

import controllers.FormController;
import controllers.FormDataController;
import SPADEPAC.Project;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ProjectValueTest {

    Project project;
    SegmentLists lists;
    LocalDate date;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        FormDataController formDataController = warmUp.getFormDataController();
        project = warmUp.getDataModel().getProject();
        FormController formController = warmUp.getFormController();

        date = LocalDate.of(2018, 10, 10);
        formDataController.saveDataFromProjectFrom("Jmeno", date,date ,"desc");

    }

    @Test
    public void testName() {
        assertEquals("Jmeno", project.getName());
    }
    
    @Test
    public void testClass() {
        assertEquals("desc", project.getDescription());
    }

    @Test
    public void testStartDate() {
        assertEquals(date.toString()+ "T00:00:00.000+02:00", project.getStartDate().toString());
    }
    @Test
    public void testEndDate() {
        assertEquals(date.toString()+ "T00:00:00.000+02:00", project.getEndDate().toString());
    }

}

