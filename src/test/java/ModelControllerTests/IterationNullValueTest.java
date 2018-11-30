package ModelControllerTests;

import SPADEPAC.Activity;
import SPADEPAC.Iteration;
import XML.ProcessGenerator;
import model.DataManipulator;
import model.IdentificatorCreater;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class IterationNullValueTest {

    Iteration iteration;
    SegmentLists lists;
    LocalDate date;

    @Before
    public void setUp() throws Exception {

        IdentificatorCreater idCreator = new IdentificatorCreater();
        ProcessGenerator processGenerator = new ProcessGenerator();
        DataManipulator data = new DataManipulator(processGenerator, idCreator);
        this.lists = new SegmentLists();

        date = LocalDate.of(2018, 10, 10);

        data.createNewIteration();

        data.addDataToIteration(null, null, null, null, 0,0, 0, new ArrayList<>(),0);

        iteration = data.getProject().getIterations().get(0);
    }

    @Test
    public void testName() {
        assertNull(iteration.getName());
    }

    @Test
    public void testStartDate() {
        assertNull(iteration.getStartDate());
    }

    @Test
    public void testEndDate() {
        assertNull(iteration.getEndDate());
    }


    @Test
    public void testDesc() {
        assertNull(iteration.getDescription());
    }

    @Test
    public void testCanvasItem() {
        assertNotNull(iteration.getWorkUnits());
    }
}

