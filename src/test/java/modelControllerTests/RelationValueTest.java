package modelControllerTests;

import controllers.formControllers.FormController;
import controllers.formControllers.FormDataController;
import SPADEPAC.Relation;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

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