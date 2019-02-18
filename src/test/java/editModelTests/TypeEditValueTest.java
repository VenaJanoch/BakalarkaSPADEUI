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

public class TypeEditValueTest {

        Type type;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();


            formController.createTableItem(SegmentType.Type);
            formDataController.saveDataFromTypeForm("Jmeno1", new ClassTable("0_Jmeno1", WorkUnitTypeClass.BUG.name(), WorkUnitTypeSuperClass.CREATION.name()
                    ,0));

            EditFormController editFormController  = warmUp.getEditFormController();
            editFormController.editDataFromClass(SegmentType.Type,"Jmeno2", WorkUnitTypeClass.FEATURE.name(),
                    WorkUnitTypeSuperClass.EDITION.name(), new ClassTable("0_Jmeno2", WorkUnitTypeClass.FEATURE.name(),
                            WorkUnitTypeSuperClass.EDITION.name(),0), 0);
            type = warmUp.getDataModel().getType(0);
        }

        @Test
        public void testName() {
            assertEquals("Jmeno2", type.getName() );
        }

        @Test
        public void testIdName() {
        assertEquals("0_Jmeno2", lists.getTypeObservable().get(1).getName());
    }


        @Test
        public void testClass() {
            assertEquals(WorkUnitTypeClass.FEATURE.name(), type.getTypeClass());
        }

        @Test
        public void testSuperClass() {
            assertEquals(WorkUnitTypeSuperClass.EDITION.name(), type.getTypeSuperClass());
        }
}