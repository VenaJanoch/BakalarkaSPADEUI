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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class PhaseNullValueTest {

    Phase phase;
    SegmentLists lists;
    LocalDate date;

    @Before
    public void setUp() throws Exception {

        IdentificatorCreater idCreator = new IdentificatorCreater();
        ProcessGenerator processGenerator = new ProcessGenerator();
        DataManipulator data = new DataManipulator(processGenerator, idCreator);
        this.lists = new SegmentLists();

        date = LocalDate.of(2018, 10, 10);

        data.createNewPhase();

        data.addDataToPhase(null, null, null, 0, 0,0, 0, new HashSet<>(),0);

        phase = data.getProject().getPhases().get(0);
    }

    @Test
    public void testName() {
        assertNull(phase.getName());
    }
    @Test
    public void testEndDate() {
        assertNull(phase.getEndDate());
    }


    @Test
    public void testDesc() {
        assertNull(phase.getDescription());
    }

    @Test
    public void testCanvasItem() {
        assertNotNull(phase.getWorkUnits());
    }
}

