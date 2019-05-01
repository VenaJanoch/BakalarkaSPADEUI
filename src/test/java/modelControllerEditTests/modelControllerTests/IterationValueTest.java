package modelControllerEditTests.modelControllerTests;

import SPADEPAC.Iteration;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
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
        ArrayList<String> name = new ArrayList<>();
        name.add("");
        name.add("Test2");
        ArrayList<Integer> indicators = new ArrayList<>();
        indicators.add(1);
        indicators.add(0);
        ArrayList<ArrayList<Integer>> unit = new ArrayList<>();
        unit.add(indicators);
        unit.add(indicators);

        ArrayList<LocalDate> dates = new ArrayList<>();
        dates.add(date);
        dataModel.getSaveDataModel().createNewIteration(2);
        dataModel.getEditDataModel().editDataInIteration("Test", name, dates, dates, name, indicators, unit, indicators, indicators, indicators, indicators, indicators,
                indicators,false,2);
        dataModel.getEditDataModel().editDataInIteration("Test", name, dates, dates, name, indicators, unit, indicators, indicators, indicators, indicators, indicators,
                indicators,false,2);
        iteration = dataModel.getIteration(2);
    }

    @Test
    public void testAlias() {
        assertEquals("Test", iteration.getAlias() );
    }

    @Test
    public void testName() {
        assertEquals("", iteration.getName().get(0) );
        assertEquals("Test2", iteration.getName().get(1) );
        assertSame(2, iteration.getName().size());
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, iteration.getNameIndicator().get(0) );
        assertSame(0, iteration.getNameIndicator().get(1) );
        assertSame(2, iteration.getNameIndicator().size());
    }

    @Test
    public void testDescription() {
        assertEquals("", iteration.getDescription().get(0) );
        assertEquals("Test2", iteration.getDescription().get(1) );
        assertSame(2, iteration.getDescription().size());
    }

    @Test
    public void testIndicatorDescription() {
        assertSame(1, iteration.getDescriptionIndicator().get(0) );
        assertSame(0, iteration.getDescriptionIndicator().get(1) );
        assertSame(2, iteration.getDescriptionIndicator().size());
    }

    @Test
    public void testIndicatorTag() {
        assertSame(1, iteration.getConfiguration().get(0) );
        assertSame(0, iteration.getConfiguration().get(1) );
        assertSame(2, iteration.getConfiguration().size());

        assertSame(1, iteration.getConfigurationIndicator().get(0) );
        assertSame(0, iteration.getConfigurationIndicator().get(1) );
        assertSame(2, iteration.getConfigurationIndicator().size());
    }

    @Test
    public void testWU() {
        assertSame(2, iteration.getWorkUnits().size());
    }

    @Test
    public void testId() {
        assertSame(2, iteration.getId());
    }

    @Test
    public void testExist() {
        assertFalse(iteration.isExist());
    }


}
