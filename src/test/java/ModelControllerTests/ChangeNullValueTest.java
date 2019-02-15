package ModelControllerTests;

import SPADEPAC.Activity;
import SPADEPAC.Change;
import XML.ProcessGenerator;
import model.DataManipulator;
import model.DataModel;
import model.IdentificatorCreater;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class ChangeNullValueTest {

    Change change;
    SegmentLists lists;
    LocalDate date;

    @Before
    public void setUp() throws Exception {

        this.lists = new SegmentLists();

        date = LocalDate.of(2018, 10, 10);

        WarmUp warmUp = new WarmUp();
        DataModel dataModel = warmUp.getDataModel();

        dataModel.getSaveDataModel().createNewChance(2);
        change = dataModel.getChange(2);
        dataModel.addDataToChange(change,null, null, 0, 0, false);

    }

    @Test
    public void testName() {
        assertNull(change.getName());
    }

    @Test
    public void testDesc() {
        assertNull(change.getDescriptoin());
    }

    @Test
    public void testCanvasItem() {
        assertFalse(change.isExist());
    }
}

