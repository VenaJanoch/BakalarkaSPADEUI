package modelControllerTests;

import SPADEPAC.Artifact;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ArtefaktNullValueTest {

        Artifact artifact;
        SegmentLists lists;
        LocalDate date;
        @Before
        public void setUp() throws Exception {

            this.lists =  new SegmentLists();

            date = LocalDate.of(2018,10, 10);

            WarmUp warmUp = new WarmUp();
            DataModel dataModel = warmUp.getDataModel();

            dataModel.getSaveDataModel().createNewArtifact(2);
            artifact = dataModel.getArtifact(2);
            dataModel.addDataToArtifact(artifact,null, null, null, true, 0,0,0,
                    0);
        }

        @Test
        public void testName() {
            assertNull(artifact.getName());
        }

        @Test
        public void testDesc() {
            assertNull(artifact.getDescriptoin());
        }

        @Test
        public void testCreateDate() {
            assertNull(artifact.getCreated());
        }

        @Test
        public void testExist() {
            assertTrue(artifact.isExist());
        }
}