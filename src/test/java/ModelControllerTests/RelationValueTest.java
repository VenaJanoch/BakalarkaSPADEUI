package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.Project;
import SPADEPAC.Relation;
import SPADEPAC.WorkUnitRelationClass;
import SPADEPAC.WorkUnitRelationSuperClass;
import XML.ProcessGenerator;
import model.DataManipulator;
import model.FileManipulator;
import model.IdentificatorCreater;
import org.junit.Before;
import org.junit.Test;
import services.Alerts;
import services.DeleteControl;
import services.SegmentLists;
import services.SegmentType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RelationValueTest {

    Relation relation;
    SegmentLists lists;
    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        FormDataController formDataController = warmUp.getFormDataController();
        Project project = warmUp.getData().getProject();
        FormController formController = warmUp.getFormController();

        formController.createTableItem(SegmentType.Relation);
        formDataController.saveDataFromRelationForm("Jmeno1", "0_Jmeno1", WorkUnitRelationClass.CHILD.name(), WorkUnitRelationSuperClass.CAUSAL.name()
                ,0);
        relation = project.getRelation().get(0);
    }

    @Test
    public void testName() {
        assertNull(relation.getName(), null );
    }

    @Test
    public void testIdName() {
        assertEquals("", lists.getRelationTypeObservable().get(0));
    }


    @Test
    public void testClass() {
        assertEquals(WorkUnitRelationClass.CHILD.name(), relation.getRelationClass());
    }

    @Test
    public void testSuperClass() {
        assertEquals(WorkUnitRelationSuperClass.CAUSAL.name(), relation.getRelationSuperClass());
    }
}