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

        WarmUp warmUp = new WarmUp();
        DataModel dataModel = warmUp.getDataModel();

        dataModel.getSaveDataModel().createNewChange(2);
        change = dataModel.getChange(2);

    }

    @Test
    public void testId() {
        assertSame(2, change.getId());
    }

    @Test
    public void testExist() {
        assertTrue(change.isExist());
    }


}
