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

public class TypeNullEditValueTest {

        Type type;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();

            formController.createTableItem(SegmentType.Type);
            formDataController.saveDataFromTypeForm("", new ClassTable("Test", WorkUnitTypeClass.ENHANCEMENT.name(),
                    WorkUnitTypeSuperClass.CREATION.name()
                    ,0));


            EditFormController editFormController  = warmUp.getEditFormController();
            editFormController.editDataFromClass(SegmentType.Type,"", WorkUnitTypeClass.UNASSIGNED.name(),
                    WorkUnitTypeSuperClass.UNASSIGNED.name(), new ClassTable("0_", WorkUnitTypeClass.UNASSIGNED.name(),
                            WorkUnitTypeSuperClass.UNASSIGNED.name(),0), 0);

            type = warmUp.getDataModel().getType(0);
        }

        @Test
        public void testName() {
            assertNull(type.getName(), null );
        }

        @Test
        public void testIdName() {
        assertEquals("0_", lists.getTypeObservable().get(1).getName());
    }


        @Test
        public void testClass() {
            assertEquals(WorkUnitTypeClass.UNASSIGNED.name(), type.getTypeClass());
        }

        @Test
        public void testSuperClass() {
            assertEquals(WorkUnitTypeClass.UNASSIGNED.name(), type.getTypeSuperClass());
        }
}