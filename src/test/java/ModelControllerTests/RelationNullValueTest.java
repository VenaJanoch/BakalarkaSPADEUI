package ModelControllerTests;

import controllers.FormController;
import controllers.FormDataController;
import SPADEPAC.Relation;
import SPADEPAC.WorkUnitRelationClass;
import SPADEPAC.WorkUnitRelationSuperClass;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.ClassTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RelationNullValueTest {

    Relation relation;
    SegmentLists lists;
    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        FormDataController formDataController = warmUp.getFormDataController();
        FormController formController = warmUp.getFormController();

        formController.createTableItem(SegmentType.Relation);
        formDataController.saveDataFromRelationForm("", new ClassTable("", WorkUnitRelationClass.UNASSIGNED.name(), WorkUnitRelationSuperClass.UNASSIGNED.name()
                ,0));
        relation = warmUp.getDataModel().getRelation(0);
    }

    @Test
    public void testName() {
        assertNull(relation.getName(), null );
    }

    @Test
    public void testIdName() {
        assertEquals("", lists.getRelationTypeObservable().get(0).getName());
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