package ModelControllerTests;

import controllers.FormController;
import controllers.FormDataController;
import SPADEPAC.Type;
import SPADEPAC.WorkUnitTypeClass;
import SPADEPAC.WorkUnitTypeSuperClass;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.ClassTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TypeValueTest {

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
            type = warmUp.getDataModel().getType(0);
        }

        @Test
        public void testName() {
            assertEquals("Jmeno1", type.getName() );
        }

        @Test
        public void testIdName() {
        assertEquals("0_Jmeno1", lists.getTypeObservable().get(1).getName());
    }


        @Test
        public void testClass() {
            assertEquals(WorkUnitTypeClass.BUG.name(), type.getTypeClass());
        }

        @Test
        public void testSuperClass() {
            assertEquals(WorkUnitTypeSuperClass.CREATION.name(), type.getTypeSuperClass());
        }
}