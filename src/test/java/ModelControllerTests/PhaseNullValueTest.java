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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class PhaseNullValueTest {

    Phase phase;
    SegmentLists lists;
    LocalDate date;

    @Before
    public void setUp() throws Exception {

        this.lists = new SegmentLists();
        WarmUp warmUp = new WarmUp();
        DataModel dataModel = warmUp.getDataModel();

        date = LocalDate.of(2018, 10, 10);

        dataModel.getSaveDataModel().createNewPhase(0);
        phase = dataModel.getPhase(0);
        dataModel.addDataToPhase(phase, null, null, null, 0, 0,0, 0, new ArrayList<>());

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

