package modelControllerTests;

import SPADEPAC.Activity;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ActivityValueTest {

    Activity activity;
    SegmentLists lists;
    LocalDate date;

    @Before
    public void setUp() throws Exception {

        this.lists = new SegmentLists();

        date = LocalDate.of(2018, 10, 10);

        WarmUp warmUp = new WarmUp();
        DataModel dataModel = warmUp.getDataModel();

        dataModel.getSaveDataModel().createNewActivity(2);
        activity = dataModel.getActivity(2);
        }

    @Test
    public void testId() {
        assertSame(2, activity.getId());
    }

    @Test
    public void testExist() {
        assertTrue(activity.isExist());
    }
}

