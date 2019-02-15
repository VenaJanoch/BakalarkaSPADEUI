package ModelControllerTests;

import SPADEPAC.Phase;
import XML.ProcessGenerator;
import model.DataManipulator;
import model.DataModel;
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

public class PhaseValueTest {

    Phase phase;
    SegmentLists lists;
    ArrayList itemSet = new ArrayList();
    LocalDate date;
    @Before
    public void setUp() throws Exception {

        this.lists = new SegmentLists();
        WarmUp warmUp = new WarmUp();
        DataModel dataModel = warmUp.getDataModel();

        itemSet.add(1);
        itemSet.add(2);
        itemSet.add(3);
        date = LocalDate.of(2018, 10, 10);

        dataModel.getSaveDataModel().createNewPhase(2);
        phase = dataModel.getPhase(2);
        dataModel.addDataToPhase(phase, "Jmeno", date,"desc", 2,3,67, 98, itemSet);


    }

    @Test
    public void testId() {
        assertSame(2, phase.getId());
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
