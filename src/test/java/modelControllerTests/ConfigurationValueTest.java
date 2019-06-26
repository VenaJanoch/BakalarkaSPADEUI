package modelControllerTests;

import SPADEPAC.Configuration;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class ConfigurationValueTest {

    Configuration configuration;
    SegmentLists lists;
    LocalDate date;
    ArrayList itemSet;

    @Before
    public void setUp() throws Exception {

        this.lists = new SegmentLists();


        WarmUp warmUp = new WarmUp();
        DataModel dataModel = warmUp.getDataModel();

        itemSet = new ArrayList();
        itemSet.add(1);
        itemSet.add(2);
        itemSet.add(3);
        date = LocalDate.of(2018, 10, 10);

        dataModel.getSaveDataModel().createNewConfiguration(2);
        configuration = dataModel.getConfiguration(2);

    }

    @Test
    public void testId() {
        assertSame(2, configuration.getId());
    }

    @Test
    public void testExist() {
        assertTrue(configuration.isExist());
    }
}

