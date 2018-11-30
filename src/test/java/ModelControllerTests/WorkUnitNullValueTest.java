package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.WorkUnit;
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

import static org.junit.Assert.*;

public class WorkUnitNullValueTest {

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
        data.addDataToWorkUnit(null,null, null, 0, 0, 0, 0,
                0, 0, 0, 0, 0, -1, true, 0, false);
        workUnit = data.getProject().getWorkUnits().get(0);
    }

    @Test
    public void testName() {
        assertNull(workUnit.getName());
    }

    @Test
    public void testDesc() {
        assertNull(workUnit.getDescription());
    }

    @Test
    public void testCategory() {
        assertNull(workUnit.getCategory());
    }

    @Test
    public void testAssigneIndex() {
        assertSame(0, workUnit.getAssigneeIndex());
    }

    @Test
    public void testAuthorIndex() {
        assertSame(0, workUnit.getAuthorIndex());
    }

    @Test
    public void testPriorityIndex() {
        assertSame(0, workUnit.getPriorityIndex());
    }

    @Test
    public void testSeverityIndex() {
        assertSame(0, workUnit.getSeverityIndex());
    }

    @Test
    public void testTypeIndex() {
        assertSame(0, workUnit.getTypeIndex());
    }

    @Test
    public void testResolutionIndex() {
        assertSame(0, workUnit.getResolutionIndex());
    }

    @Test
    public void testStatusIndex() {
        assertSame(0, workUnit.getStatusIndex());
    }

    @Test
    public void testEstimateIndex() {
        assertEquals("-1.0", workUnit.getEstimatedTime().toString());
    }

    @Test
    public void testExist() {
        assertTrue(workUnit.isExist());
    }

}