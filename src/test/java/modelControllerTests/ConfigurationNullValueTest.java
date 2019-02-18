package modelControllerTests;

import SPADEPAC.Configuration;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ConfigurationNullValueTest {

    Configuration configuration;
    SegmentLists lists;
    LocalDate date;

    @Before
    public void setUp() throws Exception {

        this.lists = new SegmentLists();

        date = LocalDate.of(2018, 10, 10);

        WarmUp warmUp = new WarmUp();
        DataModel dataModel = warmUp.getDataModel();

        dataModel.getSaveDataModel().createNewConfiguration(2);
        configuration = dataModel.getConfiguration(2);
        dataModel.addDataToConfiguration(configuration, null, null, false,0,new ArrayList<>(), new ArrayList<>(),
                new ArrayList(), new ArrayList());
    }

    @Test
    public void testName() {
        assertNull(configuration.getName());
    }

    @Test
    public void testDesc() {
        assertNull(configuration.getCreate());
    }

    @Test
    public void testRelease() {
        assertFalse(configuration.isIsRelease());
    }

    @Test
    public void testAuthorIndex() {
        assertNotNull(configuration.getAuthorIndex());
    }

    @Test
    public void testBranchIndex() {
        assertNotNull(configuration.getBranchesIndexs());
    }

    @Test
    public void testCPRIndex() {
        assertNotNull(configuration.getCPRsIndexs());
    }

    @Test
    public void testArtifactIndex() {
        assertNotNull(configuration.getArtifactsIndexs());
    }

    @Test
    public void testChangeIndex() {
        assertNotNull(configuration.getChangesIndexs());
    }


}

