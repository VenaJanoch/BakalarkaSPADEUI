package ModelControllerTests;

import controllers.FormController;
import controllers.FormDataController;
import SPADEPAC.Resolution;
import SPADEPAC.WorkUnitResolutionClass;
import SPADEPAC.WorkUnitResolutionsSuperClass;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.ClassTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ResolutionNullValueTest {

    Resolution resolution;
    SegmentLists lists;
    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        FormDataController formDataController = warmUp.getFormDataController();
        FormController formController = warmUp.getFormController();

        formController.createTableItem(SegmentType.Resolution);
        formDataController.saveDataFromResolutionForm("", new ClassTable("", WorkUnitResolutionClass.UNASSIGNED.name(), WorkUnitResolutionsSuperClass.UNASSIGNED.name()
                ,0));
        resolution = warmUp.getDataModel().getResolution(0);
    }

    @Test
    public void testName() {
        assertNull(resolution.getName(), null );
    }

    @Test
    public void testIdName() {
        assertEquals("", lists.getResolutionTypeObservable().get(0).getName());
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