package modelControllerTests;

import SPADEPAC.Artifact;
import SPADEPAC.ArtifactClass;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;

import static org.junit.Assert.*;

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
        dataModel.addDataToArtifact(artifact,"Jmeno", "desc", date, false, 67, 98, 7,
                0);
    }

    @Test
    public void testId(){assertSame(2, artifact.getId());}

    @Test
    public void testName() {
        assertEquals("Jmeno", artifact.getName());
    }

    @Test
    public void testDesc() {
        assertEquals("desc", artifact.getDescriptoin());
    }

    @Test
    public void testCreateDate() {
        assertEquals(artifact.getCreated().toString(), date.toString() + "T00:00:00.000+02:00");

    }

    @Test
    public void testExist() {
        assertFalse(artifact.isExist());
    }

    @Test
    public void testCoorX() {
        assertSame(artifact.getCoordinates().getXCoordinate(), 67);
    }

    @Test
    public void testCoorY() {
        assertSame(artifact.getCoordinates().getYCoordinate(), 98);
    }

    @Test
    public void testRoleIndex() {
        assertSame(7, artifact.getAuthorIndex());
    }

    @Test
    public void testTypeIndex() {
        assertEquals(ArtifactClass.values()[0].name(), artifact.getMimeType());
    }

    @Test
    public void testDate() {
        assertEquals(artifact.getCreated().toString(), date.toString() + "T00:00:00.000+02:00");
    }
}
