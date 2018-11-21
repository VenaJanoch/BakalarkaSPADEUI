package ModelControllerTests;

import SPADEPAC.Phase;
import XML.ProcessGenerator;
import model.DataManipulator;
import model.IdentificatorCreater;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class PhaseValueTest {

    Phase phase;
    SegmentLists lists;
    Set itemSet = new HashSet();
    LocalDate date;
    @Before
    public void setUp() throws Exception {

        IdentificatorCreater idCreator = new IdentificatorCreater();
        ProcessGenerator processGenerator = new ProcessGenerator();
        DataManipulator data = new DataManipulator(processGenerator, idCreator);
        this.lists = new SegmentLists();

        itemSet = new HashSet();
        itemSet.add(1);
        itemSet.add(2);
        itemSet.add(3);
        date = LocalDate.of(2018, 10, 10);

        data.createNewPhase();
        data.addDataToPhase("Jmeno", date,"desc", 2,3,67, 98, itemSet, 0);

        phase = data.getProject().getPhases().get(0);
    }

    @Test
    public void testName() {
        assertEquals("Jmeno", phase.getName());
    }

    @Test
    public void testEndDate() {
        assertEquals(date.toString()+ "T00:00:00.000+02:00", phase.getEndDate().toString());
    }

    @Test
    public void testDesc() {
        assertEquals("desc", phase.getDescription());
    }


    @Test
    public void testCoorX() {
        assertSame(phase.getCoordinates().getXCoordinate(), 67);
    }

    @Test
    public void testCoorY() {
        assertSame(phase.getCoordinates().getYCoordinate(), 98);
    }

    @Test
    public void testConfigIndex() {
        assertSame(2, phase.getConfiguration());
    }

    @Test
    public void testMilestoneIndex() {
        assertSame(3, phase.getMilestoneIndex());
    }

    @Test
    public void testWorkUnits() {
        assertSame(phase.getWorkUnits().size(), itemSet.size());
    }



}
