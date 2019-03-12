package modelControllerTests;

import SPADEPAC.Configuration;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertSame;

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
        dataModel.addDataToConfiguration(configuration,"Jmeno1", date, true,2 ,itemSet, itemSet,
                itemSet);

    }

    @Test
    public void testId() {
        assertSame(2,configuration.getId());
    }


    @Test
    public void testName() {
        assertEquals("Jmeno1",configuration.getName());
    }

    @Test
    public void testDate() {
        assertEquals(date.toString()+ "T00:00:00.000+02:00", configuration.getCreate().toString());
    }

    @Test
    public void testRelease() {
        assertTrue(configuration.isIsRelease());
    }

    @Test
    public void testAuthorIndex() {
        assertSame(2,configuration.getAuthorIndex());
    }

    @Test
    public void testBranchIndex() {
        assertSame(itemSet.size() ,configuration.getBranchesIndexs().size());
    }

    @Test
    public void testCPRIndex() {
        assertSame(itemSet.size() ,configuration.getCPRsIndexs().size());
    }

    @Test
    public void testArtifactIndex() {
        assertSame(itemSet.size() ,configuration.getArtifactsIndexs().size());
    }

    @Test
    public void testChangeIndex() {
        assertSame(itemSet.size() ,configuration.getArtifactsIndexs().size());
    }

}

