package modelControllerTests;

import SPADEPAC.Phase;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

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

    }

    @Test
    public void testId() {
        assertSame(2, phase.getId());
    }

    @Test
    public void testExist() {
        assertTrue(phase.isExist());
    }


}
