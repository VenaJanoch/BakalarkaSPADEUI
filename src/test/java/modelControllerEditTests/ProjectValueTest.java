package modelControllerEditTests;

import SPADEPAC.Project;
import controllers.formControllers.FormController;
import controllers.formControllers.FormDataController;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

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

        ArrayList<String> name = new ArrayList<>();
        name.add("");
        name.add("Test2");
        ArrayList<Integer> indicators = new ArrayList<>();
        indicators.add(1);
        indicators.add(0);
        ArrayList<ArrayList<Integer>> unit = new ArrayList<>();
        unit.add(indicators);
        unit.add(indicators);

        ArrayList<LocalDate> dates = new ArrayList<>();
        dates.add(date);

        warmUp.getDataModel().getEditDataModel().editDataInProject( name, dates, dates, name, unit, indicators, indicators, indicators, indicators, indicators);
        warmUp.getDataModel().getEditDataModel().editDataInProject( name, dates, dates, name, unit, indicators, indicators, indicators, indicators, indicators);

        project = warmUp.getDataModel().getProject();
    }

    @Test
    public void testName() {
        TestCase.assertEquals("", project.getName().get(0) );
        TestCase.assertEquals("Test2", project.getName().get(1) );
        assertSame(2, project.getName().size());
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, project.getNameIndicator().get(0) );
        assertSame(0, project.getNameIndicator().get(1) );
        assertSame(2, project.getNameIndicator().size());
    }

    @Test
    public void testDescription() {
        TestCase.assertEquals("", project.getDescription().get(0) );
        TestCase.assertEquals("Test2", project.getDescription().get(1) );
        assertSame(2, project.getDescription().size());
    }

    @Test
    public void testIndicatorDescription() {
        assertSame(1, project.getDescriptionIndicator().get(0) );
        assertSame(0, project.getDescriptionIndicator().get(1) );
        assertSame(2, project.getDescriptionIndicator().size());
    }

    @Test
    public void testWU() {
        assertSame(2, project.getWorkUnitIndexs().size());
    }

}

