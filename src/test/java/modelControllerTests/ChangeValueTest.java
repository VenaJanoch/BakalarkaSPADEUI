package modelControllerTests;

import SPADEPAC.Change;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class ChangeValueTest {

    Change change;
    SegmentLists lists;
    Set itemSet = new HashSet();

    @Before
    public void setUp() throws Exception {

        this.lists = new SegmentLists();

        itemSet.add(1);
        itemSet.add(2);
        itemSet.add(3);
        WarmUp warmUp = new WarmUp();
        DataModel dataModel = warmUp.getDataModel();

        dataModel.getSaveDataModel().createNewChange(2);
        change = dataModel.getChange(2);
        dataModel.addDataToChange(change,"Jmeno", "desc", true);

    }

    @Test
    public void testName() {
        assertEquals("Jmeno", change.getName());
    }

    @Test
    public void testDesc() {
        assertEquals("desc", change.getDescriptoin());
    }


    @Test
    public void testCoorX() {
        assertSame(change.getCoordinates().getXCoordinate(), 67);
    }

    @Test
    public void testCoorY() {
        assertSame(change.getCoordinates().getYCoordinate(), 98);
    }

    @Test
    public void testExist() {
        assertTrue(change.isExist());
    }



}
