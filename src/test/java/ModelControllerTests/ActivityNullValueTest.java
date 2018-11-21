package ModelControllerTests;

import SPADEPAC.Activity;
import SPADEPAC.Artifact;
import XML.ProcessGenerator;
import model.DataManipulator;
import model.IdentificatorCreater;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ActivityNullValueTest {

    Activity activity;
    SegmentLists lists;
    LocalDate date;

    @Before
    public void setUp() throws Exception {

        IdentificatorCreater idCreator = new IdentificatorCreater();
        ProcessGenerator processGenerator = new ProcessGenerator();
        DataManipulator data = new DataManipulator(processGenerator, idCreator);
        this.lists = new SegmentLists();

        date = LocalDate.of(2018, 10, 10);

        data.createNewActivity();

        data.addDataToActivity(null, null, 0, 0, new HashSet<>(), 0);

        activity = data.getProject().getActivities().get(0);
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

