package editModelTests;

import SPADEPAC.*;
import controllers.EditFormController;
import controllers.FormController;
import controllers.FormDataController;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.ClassTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ResolutionNullEditValueTest {

    Resolution resolution;
    SegmentLists lists;
    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        FormDataController formDataController = warmUp.getFormDataController();
        FormController formController = warmUp.getFormController();

        formController.createTableItem(SegmentType.Resolution);
        formDataController.saveDataFromResolutionForm("Test", new ClassTable("Test", WorkUnitResolutionClass.UNASSIGNED.name(), WorkUnitResolutionsSuperClass.UNASSIGNED.name()
                ,0));

        EditFormController editFormController  = warmUp.getEditFormController();
        editFormController.editDataFromClass(SegmentType.Resolution,"", WorkUnitPriorityClass.UNASSIGNED.name(),
                WorkUnitPrioritySuperClass.UNASSIGNED.name(), new ClassTable("0_", WorkUnitPriorityClass.UNASSIGNED.name(),
                        WorkUnitPrioritySuperClass.UNASSIGNED.name(),0), 0);
        resolution = warmUp.getDataModel().getResolution(0);
    }

    @Test
    public void testName() {
        assertNull(resolution.getName(), null );
    }

    @Test
    public void testIdName() {
        assertEquals("0_", lists.getResolutionTypeObservable().get(1).getName());
    }

}