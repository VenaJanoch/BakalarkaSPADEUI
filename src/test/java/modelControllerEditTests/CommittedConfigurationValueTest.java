package modelControllerEditTests;

import SPADEPAC.CommitedConfiguration;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

public class CommittedConfigurationValueTest{

    CommitedConfiguration committedConfiguration;
    SegmentLists lists;
    LocalDate date;
    @Before
    public void setUp() throws Exception {

        this.lists = new SegmentLists();


        WarmUp warmUp = new WarmUp();
        DataModel dataModel = warmUp.getDataModel();
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
        dataModel.getSaveDataModel().createNewCommitedConfiguration(2);
        dataModel.getEditDataModel().editDataInCommitedConfiguration("Test", name,indicators, name, indicators, dates, indicators, dates, indicators,3, false,2);
        dataModel.getEditDataModel().editDataInCommitedConfiguration("Test", name,indicators, name, indicators, dates, indicators, dates, indicators,3, false,2);

        committedConfiguration = dataModel.getCommitedConfiguration(2);

    }
    @Test
    public void testAlias() {
        assertEquals("Test", committedConfiguration.getAlias() );
    }

    @Test
    public void testName() {
        assertEquals("", committedConfiguration.getName().get(0) );
        assertEquals("Test2", committedConfiguration.getName().get(1) );
        assertSame(2, committedConfiguration.getName().size());
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, committedConfiguration.getNameIndicator().get(0) );
        assertSame(0, committedConfiguration.getNameIndicator().get(1) );
        assertSame(2, committedConfiguration.getNameIndicator().size());
    }

    @Test
    public void testDescription() {
        assertEquals("", committedConfiguration.getDescription().get(0) );
        assertEquals("Test2", committedConfiguration.getDescription().get(1) );
        assertSame(2, committedConfiguration.getDescription().size());
    }

    @Test
    public void testIndicatorDescription() {
        assertSame(1, committedConfiguration.getDescriptionIndicator().get(0) );
        assertSame(0, committedConfiguration.getDescriptionIndicator().get(1) );
        assertSame(2, committedConfiguration.getDescriptionIndicator().size());
    }
    @Test
    public void testDate() {
        assertEquals(date.toString()+ "T00:00:00.000+02:00", committedConfiguration.getCreated().get(0).toString());
        assertSame(1, committedConfiguration.getCreated().size());
    }

    @Test
    public void testEndDate() {
        assertEquals(date.toString()+ "T00:00:00.000+02:00", committedConfiguration.getCommitedDay().get(0).toString());
        assertSame(1, committedConfiguration.getCommitedDay().size());
    }

    @Test
    public void testId() {
        assertSame(2, committedConfiguration.getId());
    }

    @Test
    public void testExist() {
        assertFalse(committedConfiguration.isExist());
    }


}

