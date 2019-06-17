package modelControllerCopyTests;

import SPADEPAC.Activity;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

public class ActivityValueCopyTest {

    Activity activity;
    SegmentLists lists;
    LocalDate date;

    @Before
    public void setUp() throws Exception {

        this.lists = new SegmentLists();

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

        dataModel.getSaveDataModel().createNewActivity(2);
        dataModel.getEditDataModel().editDataInActivity("Test", name, name, unit, indicators, indicators, indicators, dates, indicators, false,2  );
        dataModel.getSaveDataModel().createNewActivity(3);
        dataModel.getDataManipulator().copyDataFromActivity(2, 3);
        activity = dataModel.getActivity(3);
        }

    @Test
    public void testAlias() {
        assertEquals("3", activity.getAlias() );
    }

    @Test
    public void testName() {
        assertEquals("", activity.getName().get(0) );
        assertEquals("Test2", activity.getName().get(1) );
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, activity.getNameIndicator().get(0) );
        assertSame(0, activity.getNameIndicator().get(1) );
    }

    @Test
    public void testDescription() {
        assertEquals("", activity.getDescription().get(0) );
        assertEquals("Test2", activity.getDescription().get(1) );
    }

    @Test
    public void testIndicatorDescription() {
        assertSame(1, activity.getDescriptionIndicator().get(0) );
        assertSame(0, activity.getDescriptionIndicator().get(1) );
    }

    @Test
    public void testId() {
        assertSame(3, activity.getId());
    }

    @Test
    public void testExist() {
        assertFalse(activity.isExist());
    }
}

