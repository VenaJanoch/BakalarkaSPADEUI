package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import SPADEPAC.WorkUnit;
import XML.ProcessGenerator;
import model.DataManipulator;
import model.FileManipulator;
import model.IdentificatorCreater;
import org.junit.Before;
import org.junit.Test;
import services.Alerts;
import services.CanvasType;
import services.DeleteControl;
import services.SegmentLists;

import static org.junit.Assert.*;

public class WorkUnitValueTest {

    WorkUnit workUnit;
    SegmentLists lists;

    @Before
    public void setUp() throws Exception {

        IdentificatorCreater idCreator = new IdentificatorCreater();
        ProcessGenerator processGenerator = new ProcessGenerator();
        DataManipulator data = new DataManipulator(processGenerator, idCreator);
        this.lists = new SegmentLists();
        FileManipulator file = new FileManipulator(processGenerator, data);
        Alerts alerts = new Alerts(file);
        ApplicationController ap = new ApplicationController(file, data, alerts, idCreator, lists);
        DeleteControl deleteControl = new DeleteControl();
        FormController formController = new FormController(idCreator, data, ap, lists, deleteControl);
        for (int i = 0; i < 12; i++) {
            formController.getForms().add(null);
        }
        data.createNewWorkUnit();
        data.addDataToWorkUnit("Jmeno","Desc", "Category", 2, 2, 2, 2,
                2, 2, 2, 56, 65, 23, false, 0, false);
        workUnit = data.getProject().getWorkUnits().get(0);
    }

    @Test
    public void testName() {
        assertEquals("Jmeno",workUnit.getName());
    }

    @Test
    public void testDesc() {
        assertEquals("Desc", workUnit.getDescription());
    }

    @Test
    public void testCategory() {
        assertEquals("Category", workUnit.getCategory());
    }

    @Test
    public void testAssigneIndex() {
        assertSame(2, workUnit.getAssigneeIndex());
    }

    @Test
    public void testAuthorIndex() {
        assertSame(2, workUnit.getAuthorIndex());
    }

    @Test
    public void testPriorityIndex() {
        assertSame(2, workUnit.getPriorityIndex());
    }

    @Test
    public void testSeverityIndex() {
        assertSame(2, workUnit.getSeverityIndex());
    }

    @Test
    public void testTypeIndex() {
        assertSame(2, workUnit.getTypeIndex());
    }

    @Test
    public void testResolutionIndex() {
        assertSame(2, workUnit.getResolutionIndex());
    }

    @Test
    public void testStatusIndex() {
        assertSame(2, workUnit.getStatusIndex());
    }

    @Test
    public void testEstimateIndex() {
        assertEquals("23.0", workUnit.getEstimatedTime().toString());
    }

    @Test
    public void testExist() {
        assertFalse(workUnit.isExist());
    }

}