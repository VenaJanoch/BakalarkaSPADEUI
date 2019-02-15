package ModelControllerTests;

import SPADEPAC.Activity;
import SPADEPAC.Artifact;
import SPADEPAC.ArtifactClass;
import XML.ProcessGenerator;
import model.DataManipulator;
import model.DataModel;
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

        this.lists = new SegmentLists();

        itemSet.add(1);
        itemSet.add(2);
        itemSet.add(3);
        WarmUp warmUp = new WarmUp();
        DataModel dataModel = warmUp.getDataModel();

        dataModel.getSaveDataModel().createNewActivity(2);
        activity = dataModel.getActivity(2);
        dataModel.addDataToActivity(activity,"Jmeno", "desc", 67, 98, itemSet);
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
