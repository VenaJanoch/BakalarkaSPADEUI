package ModelControllerTests;

import SPADEPAC.Artifact;
import SPADEPAC.ArtifactClass;
import XML.ProcessGenerator;
import model.DataManipulator;
import model.IdentificatorCreater;
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

        IdentificatorCreater idCreator = new IdentificatorCreater();
        ProcessGenerator processGenerator = new ProcessGenerator();
        DataManipulator data = new DataManipulator(processGenerator, idCreator);
        this.lists = new SegmentLists();

        date = LocalDate.of(2018, 10, 10);

        data.createNewArtifact();
        data.addDataToArtifact("Jmeno", "desc", date, false, 67, 98, 7,
                0, 0);

        artifact = data.getProject().getArtifacts().get(0);
    }

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
