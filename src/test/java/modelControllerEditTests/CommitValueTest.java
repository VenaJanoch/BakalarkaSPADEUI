package modelControllerEditTests;

import SPADEPAC.Commit;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

public class CommitValueTest {

    Commit commit;
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
        dataModel.getSaveDataModel().createNewCommit(2);
        dataModel.getEditDataModel().editDataInCommit("Test", name,indicators, name, indicators, dates, indicators,true, 3, 1,false,2);
        dataModel.getEditDataModel().editDataInCommit("Test", name,indicators, name, indicators, dates, indicators,true, 3, 1,false,2);

        commit = dataModel.getCommit(2);

    }
    @Test
    public void testAlias() {
        assertEquals("Test", commit.getAlias() );
    }

    @Test
    public void testName() {
        assertEquals("", commit.getName().get(0) );
        assertEquals("Test2", commit.getName().get(1) );
        assertSame(2, commit.getName().size());
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, commit.getNameIndicator().get(0) );
        assertSame(0, commit.getNameIndicator().get(1) );
        assertSame(2, commit.getNameIndicator().size());
    }

    @Test
    public void testDescription() {
        assertEquals("", commit.getDescription().get(0) );
        assertEquals("Test2", commit.getDescription().get(1) );
        assertSame(2, commit.getDescription().size());
    }

    @Test
    public void testIndicatorDescription() {
        assertSame(1, commit.getDescriptionIndicator().get(0) );
        assertSame(0, commit.getDescriptionIndicator().get(1) );
        assertSame(2, commit.getDescriptionIndicator().size());
    }
    @Test
    public void testDate() {
        assertEquals(date.toString()+ "T00:00:00.000+02:00", commit.getCreated().get(0).toString());
        assertSame(1, commit.getCreated().size());
    }

    @Test
    public void testId() {
        assertSame(2, commit.getId());
    }

    @Test
    public void testExist() {
        assertFalse(commit.isExist());
    }


}

