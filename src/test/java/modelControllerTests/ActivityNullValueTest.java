package modelControllerTests;

import SPADEPAC.Activity;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ActivityNullValueTest {

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
        dataModel.addDataToActivity(activity ,null, null, 0, 0, new ArrayList<>());
        }

    @Test
    public void testName() {
        assertNull(activity.getName());
    }

    @Test
    public void testDesc() {
        assertNull(activity.getDescription());
    }

    @Test
    public void testCanvasItem() {
        assertNotNull(activity.getWorkUnits());
    }
}

