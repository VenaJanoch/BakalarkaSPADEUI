package modelControllerEditTests;

import SPADEPAC.Phase;
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
        dataModel.getSaveDataModel().createNewPhase(2);
        dataModel.getEditDataModel().editDataInPhase("Test", name, dates, name, indicators, indicators, unit, indicators,
                indicators, indicators, indicators, indicators, indicators, false, 2);
        dataModel.getEditDataModel().editDataInPhase("Test", name, dates, name, indicators, indicators, unit, indicators,
                indicators, indicators, indicators, indicators, indicators, false, 2);
        phase = dataModel.getPhase(2);
    }

    @Test
    public void testAlias() {
        assertEquals("Test", phase.getAlias() );
    }

    @Test
    public void testName() {
        assertEquals("", phase.getName().get(0) );
        assertEquals("Test2", phase.getName().get(1) );
        assertSame(2, phase.getName().size());
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, phase.getNameIndicator().get(0) );
        assertSame(0, phase.getNameIndicator().get(1) );
        assertSame(2, phase.getNameIndicator().size());
    }

    @Test
    public void testDescription() {
        assertEquals("", phase.getDescription().get(0) );
        assertEquals("Test2", phase.getDescription().get(1) );
        assertSame(2, phase.getDescription().size());
    }

    @Test
    public void testIndicatorDescription() {
        assertSame(1, phase.getDescriptionIndicator().get(0) );
        assertSame(0, phase.getDescriptionIndicator().get(1) );
        assertSame(2, phase.getDescriptionIndicator().size());
    }

    @Test
    public void testIndicatorConfiguration() {
        assertSame(1, phase.getConfiguration().get(0) );
        assertSame(0, phase.getConfiguration().get(1) );
        assertSame(2, phase.getConfiguration().size());

        assertSame(1, phase.getConfigurationIndicator().get(0) );
        assertSame(0, phase.getConfigurationIndicator().get(1) );
        assertSame(2, phase.getConfigurationIndicator().size());
    }

    @Test
    public void testWU() {
        assertSame(2, phase.getWorkUnits().size());
    }

    @Test
    public void testId() {
        assertSame(2, phase.getId());
    }

    @Test
    public void testExist() {
        assertFalse(phase.isExist());
    }


}
