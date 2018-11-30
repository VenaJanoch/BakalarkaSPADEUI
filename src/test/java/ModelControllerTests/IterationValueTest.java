package ModelControllerTests;

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
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class IterationValueTest {

    Iteration iteration;
    SegmentLists lists;
    ArrayList itemSet = new ArrayList();
    LocalDate date;
    @Before
    public void setUp() throws Exception {

        IdentificatorCreater idCreator = new IdentificatorCreater();
        ProcessGenerator processGenerator = new ProcessGenerator();
        DataManipulator data = new DataManipulator(processGenerator, idCreator);
        this.lists = new SegmentLists();

        itemSet.add(1);
        itemSet.add(2);
        itemSet.add(3);
        date = LocalDate.of(2018, 10, 10);

        data.createNewIteration();
        data.addDataToIteration("Jmeno", date,date,"desc", 0,67, 98, itemSet, 0);

        iteration = data.getProject().getIterations().get(0);
    }

    @Test
    public void testName() {
        assertEquals("Jmeno", iteration.getName());
    }

    @Test
    public void testStartDate() {
        assertEquals(date.toString()+ "T00:00:00.000+02:00", iteration.getStartDate().toString());
    }

    @Test
    public void testEndDate() {
        assertEquals(date.toString()+ "T00:00:00.000+02:00", iteration.getEndDate().toString());
    }

    @Test
    public void testDesc() {
        assertEquals("desc", iteration.getDescription());
    }


    @Test
    public void testCoorX() {
        assertSame(iteration.getCoordinates().getXCoordinate(), 67);
    }

    @Test
    public void testCoorY() {
        assertSame(iteration.getCoordinates().getYCoordinate(), 98);
    }

    @Test
    public void testWorkUnits() {
        assertSame(iteration.getWorkUnits().size(), itemSet.size());
    }



}
