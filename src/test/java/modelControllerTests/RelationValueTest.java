package modelControllerTests;

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

import static org.junit.Assert.*;

public class RelationValueTest {

    Relation relation;
    SegmentLists lists;
    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        FormDataController formDataController = warmUp.getFormDataController();
        FormController formController = warmUp.getFormController();

        warmUp.getDataModel().getSaveDataModel().createNewRelation("","","",0);

        relation = warmUp.getDataModel().getRelation(0);
    }

    @Test
    public void testId() {
        assertSame(0, relation.getId());
    }

    @Test
    public void testExist() {
        assertTrue(relation.isExist());
    }
}