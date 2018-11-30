package ModelControllerTests;

import SPADEPAC.Activity;
import SPADEPAC.Artifact;
import SPADEPAC.ArtifactClass;
import XML.ProcessGenerator;
import model.DataManipulator;
import model.IdentificatorCreater;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ActivityValueTest {

    Activity activity;
    SegmentLists lists;
    ArrayList itemSet = new ArrayList();

    @Before
    public void setUp() throws Exception {

        IdentificatorCreater idCreator = new IdentificatorCreater();
        ProcessGenerator processGenerator = new ProcessGenerator();
        DataManipulator data = new DataManipulator(processGenerator, idCreator);
        this.lists = new SegmentLists();

        itemSet.add(1);
        itemSet.add(2);
        itemSet.add(3);
        data.createNewActivity();
        data.addDataToActivity("Jmeno", "desc", 67, 98, itemSet, 0);

        activity = data.getProject().getActivities().get(0);
    }

    @Test
    public void testName() {
        assertEquals("Jmeno", activity.getName());
    }

    @Test
    public void testDesc() {
        assertEquals("desc", activity.getDescription());
    }


    @Test
    public void testCoorX() {
        assertSame(activity.getCoordinates().getXCoordinate(), 67);
    }

    @Test
    public void testCoorY() {
        assertSame(activity.getCoordinates().getYCoordinate(), 98);
    }

    @Test
    public void testWorkUnits() {
        assertSame(activity.getWorkUnits().size(), itemSet.size());
    }



}
