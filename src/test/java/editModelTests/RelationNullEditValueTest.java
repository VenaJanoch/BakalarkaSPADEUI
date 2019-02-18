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

public class RelationNullEditValueTest {

    Relation relation;
    SegmentLists lists;
    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        FormDataController formDataController = warmUp.getFormDataController();
        FormController formController = warmUp.getFormController();

        formController.createTableItem(SegmentType.Relation);
        formDataController.saveDataFromRelationForm("Test", new ClassTable("Test", WorkUnitRelationClass.BLOCKS.name(),
                WorkUnitRelationSuperClass.GENERAL.name()
                ,0));


        EditFormController editFormController  = warmUp.getEditFormController();
        editFormController.editDataFromClass(SegmentType.Relation,"", WorkUnitRelationClass.UNASSIGNED.name(),
                WorkUnitRelationSuperClass.UNASSIGNED.name(), new ClassTable("0_", WorkUnitPriorityClass.UNASSIGNED.name(),
                        WorkUnitPrioritySuperClass.UNASSIGNED.name(),0), 0);

        relation = warmUp.getDataModel().getRelation(0);
    }

    @Test
    public void testName() {
        assertNull(relation.getName(), null );
    }

    @Test
    public void testIdName() {
        assertEquals("0_", lists.getRelationTypeObservable().get(1).getName());
    }


    @Test
    public void testClass() {
        assertEquals(WorkUnitRelationClass.UNASSIGNED.name(), relation.getRelationClass());
    }

    @Test
    public void testSuperClass() {
        assertEquals(WorkUnitRelationSuperClass.UNASSIGNED.name(), relation.getRelationSuperClass());
    }
}