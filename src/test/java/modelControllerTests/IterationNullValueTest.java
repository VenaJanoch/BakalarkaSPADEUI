package modelControllerTests;

import SPADEPAC.Iteration;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class IterationNullValueTest {

    Iteration iteration;
    SegmentLists lists;
    LocalDate date;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        DataModel dataModel = warmUp.getDataModel();
        this.lists = new SegmentLists();

        date = LocalDate.of(2018, 10, 10);

        dataModel.getSaveDataModel().createNewIteration(2);
        iteration = dataModel.getIteration(2);
        dataModel.addDataToIteration(iteration, null, null, null, null, 0, new ArrayList<>());
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

