package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
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


        formController.createTableItem(SegmentType.Resolution);
        formDataController.saveDataFromResolutionForm("", "", WorkUnitResolutionClass.UNASSIGNED.name(), WorkUnitResolutionsSuperClass.UNASSIGNED.name()
                ,0);
        resolution = data.getProject().getResolution().get(0);
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