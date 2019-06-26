package modelControllerTests;

import SPADEPAC.Iteration;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class IterationValueTest {

    Iteration iteration;
    SegmentLists lists;
    ArrayList itemSet = new ArrayList();
    LocalDate date;

    @Before
    public void setUp() throws Exception {

        this.lists = new SegmentLists();
        itemSet.add(1);
        itemSet.add(2);
        itemSet.add(3);
        date = LocalDate.of(2018, 10, 10);

        WarmUp warmUp = new WarmUp();
        DataModel dataModel = warmUp.getDataModel();

        dataModel.getSaveDataModel().createNewIteration(2);
        iteration = dataModel.getIteration(2);
    }

    @Test
    public void testId() {
        assertSame(2, iteration.getId());
    }

    @Test
    public void testExist() {
        assertTrue(iteration.isExist());
    }

}
