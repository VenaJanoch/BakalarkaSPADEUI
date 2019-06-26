package modelControllerTests;

import SPADEPAC.Artifact;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class ArtefaktValueTest {

    Artifact artifact;
    SegmentLists lists;
    LocalDate date;

    @Before
    public void setUp() throws Exception {

        this.lists = new SegmentLists();

        date = LocalDate.of(2018, 10, 10);

        WarmUp warmUp = new WarmUp();
        DataModel dataModel = warmUp.getDataModel();

        dataModel.getSaveDataModel().createNewArtifact(2);
        artifact = dataModel.getArtifact(2);
    }

    @Test
    public void testId() {
        assertSame(2, artifact.getId());
    }

    @Test
    public void testCount() {
        assertSame(1, artifact.getCount());
    }

    @Test
    public void testExist() {
        assertTrue(artifact.isExist());
    }
}
