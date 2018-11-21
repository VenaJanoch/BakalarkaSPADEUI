package ModelControllerTests;

import SPADEPAC.Activity;
import SPADEPAC.Change;
import XML.ProcessGenerator;
import model.DataManipulator;
import model.IdentificatorCreater;
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

        IdentificatorCreater idCreator = new IdentificatorCreater();
        ProcessGenerator processGenerator = new ProcessGenerator();
        DataManipulator data = new DataManipulator(processGenerator, idCreator);
        this.lists = new SegmentLists();

        itemSet.add(1);
        itemSet.add(2);
        itemSet.add(3);
        data.createNewChance();
        data.addDataToChange("Jmeno", "desc", 67, 98, true, 0);

        change = data.getProject().getChanges().get(0);
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
