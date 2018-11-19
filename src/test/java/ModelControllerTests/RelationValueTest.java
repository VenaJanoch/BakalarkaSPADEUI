package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
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

        IdentificatorCreater idCreator = new IdentificatorCreater();
        ProcessGenerator processGenerator = new ProcessGenerator();
        DataManipulator data =  new DataManipulator(processGenerator,idCreator);
        this.lists =  new SegmentLists();
        FileManipulator file = new FileManipulator(processGenerator,data);
        Alerts alerts = new Alerts(file);
        ApplicationController ap = new ApplicationController(file, data, alerts, idCreator, lists);
        DeleteControl deleteControl = new DeleteControl();
        FormController formController = new FormController(idCreator, data, ap, lists, deleteControl);
        for(int i = 0; i < 12; i++){
            formController.getForms().add(null);
        }
        FormDataController formDataController = new FormDataController(formController, deleteControl, lists, data, idCreator);


        formController.createTableItem(SegmentType.Relation);
        formDataController.saveDataFromRelationForm("Jmeno1", "0_Jmeno1", WorkUnitRelationClass.CHILD.name(), WorkUnitRelationSuperClass.CAUSAL.name()
                ,0);
        relation = data.getProject().getRelation().get(0);
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