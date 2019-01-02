package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.Project;
import SPADEPAC.Resolution;
import SPADEPAC.WorkUnitResolutionClass;
import SPADEPAC.WorkUnitResolutionsSuperClass;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ResolutionNullValueTest {

    Resolution resolution;
    SegmentLists lists;
    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        FormDataController formDataController = warmUp.getFormDataController();
        Project project = warmUp.getData().getProject();
        FormController formController = warmUp.getFormController();

        formController.createTableItem(SegmentType.Resolution);
        formDataController.saveDataFromResolutionForm("", "", WorkUnitResolutionClass.UNASSIGNED.name(), WorkUnitResolutionsSuperClass.UNASSIGNED.name()
                ,0);
        resolution = project.getResolution().get(0);
    }

    @Test
    public void testName() {
        assertNull(resolution.getName(), null );
    }

    @Test
    public void testIdName() {
        assertEquals("", lists.getResolutionTypeObservable().get(0));
    }


    @Test
    public void testClass() {
        assertEquals(WorkUnitResolutionClass.UNASSIGNED.name(), resolution.getResolutionClass());
    }

    @Test
    public void testSuperClass() {
        assertEquals(WorkUnitResolutionsSuperClass.UNASSIGNED.name(), resolution.getResolutionSuperClass());
    }
}