package modelControllerEditTests;

import SPADEPAC.Configuration;
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

public class ConfigurationValueTest {

    Configuration configuration;
    SegmentLists lists;
    LocalDate date;
    ArrayList itemSet;
    @Before
    public void setUp() throws Exception {

        this.lists = new SegmentLists();


        WarmUp warmUp = new WarmUp();
        DataModel dataModel = warmUp.getDataModel();

        itemSet = new ArrayList();
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
        dataModel.getSaveDataModel().createNewConfiguration(2);
        dataModel.getEditDataModel().editDataInConfiguration("Test", name, name, dates, true, unit, unit, unit, indicators, indicators, indicators,
                indicators, indicators, indicators, indicators, indicators,3, 1,false,2);
        dataModel.getEditDataModel().editDataInConfiguration("Test", name, name, dates, true, unit, unit, unit, indicators, indicators, indicators,
                indicators, indicators, indicators, indicators, indicators,3, 1,false,2);

        configuration = dataModel.getConfiguration(2);

    }
    @Test
    public void testAlias() {
        assertEquals("Test", configuration.getAlias() );
    }

    @Test
    public void testName() {
        assertEquals("", configuration.getName().get(0) );
        assertEquals("Test2", configuration.getName().get(1) );
        assertSame(2, configuration.getName().size());
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, configuration.getNameIndicator().get(0) );
        assertSame(0, configuration.getNameIndicator().get(1) );
        assertSame(2, configuration.getNameIndicator().size());
    }

    @Test
    public void testDescription() {
        assertEquals("", configuration.getDescription().get(0) );
        assertEquals("Test2", configuration.getDescription().get(1) );
        assertSame(2, configuration.getDescription().size());
    }

    @Test
    public void testIndicatorDescription() {
        assertSame(1, configuration.getDescriptionIndicator().get(0) );
        assertSame(0, configuration.getDescriptionIndicator().get(1) );
        assertSame(2, configuration.getDescriptionIndicator().size());
    }

    @Test
    public void testIndicatorTag() {
        assertSame(1, configuration.getTagIndex().get(0) );
        assertSame(0, configuration.getTagIndex().get(1) );
        assertSame(2, configuration.getTagIndex().size());

        assertSame(1, configuration.getTagsIndicator().get(0) );
        assertSame(0, configuration.getTagsIndicator().get(1) );
        assertSame(2, configuration.getTagsIndicator().size());
    }

    @Test
    public void testChange() {
        assertSame(2, configuration.getChangesIndexs().size());
    }

    @Test
    public void testBranch() {
        assertSame(2, configuration.getBranchIndexs().size());
    }

    @Test
    public void testCPR() {
        assertSame(2, configuration.getCPRsIndexs().size());
    }

    @Test
    public void testDate() {
        assertEquals(date.toString()+ "T00:00:00.000+02:00", configuration.getCreated().get(0).toString());
        assertSame(1, configuration.getCreated().size());
    }

    @Test
    public void testId() {
        assertSame(2, configuration.getId());
    }

    @Test
    public void testExist() {
        assertFalse(configuration.isExist());
    }


}

