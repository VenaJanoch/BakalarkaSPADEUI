package modelControllerTests;

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

public class ResolutionValueTest {

    Resolution resolution;
    SegmentLists lists;
    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        FormDataController formDataController = warmUp.getFormDataController();
        FormController formController = warmUp.getFormController();


        formController.createTableItem(SegmentType.Resolution);
        formDataController.saveDataFromResolutionForm("Jmeno1", new ClassTable("0_Jmeno1", WorkUnitResolutionClass.DUPLICATE.name(), WorkUnitResolutionsSuperClass.UNFINISHED.name()
                ,0));
        resolution = warmUp.getDataModel().getResolution(0);
    }

    @Test
    public void testName() {
        assertEquals("Jmeno1", resolution.getName() );
    }

    @Test
    public void testIdName() {
        assertEquals("0_Jmeno1", lists.getResolutionTypeObservable().get(1).getName());
    }


    @Test
    public void testClass() {
        assertEquals(WorkUnitResolutionClass.DUPLICATE.name(), resolution.getResolutionClass());
    }

    @Test
    public void testSuperClass() {
        assertEquals(WorkUnitResolutionsSuperClass.UNFINISHED.name(), resolution.getResolutionSuperClass());
    }
}