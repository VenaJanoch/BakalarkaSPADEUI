package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.Artifact;
import SPADEPAC.Branch;
import XML.ProcessGenerator;
import model.DataManipulator;
import model.FileManipulator;
import model.IdentificatorCreater;
import org.junit.Before;
import org.junit.Test;
import services.Alerts;
import services.DeleteControl;
import services.SegmentLists;
import services.SegmentType;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ArtefaktNullValueTest {

        Artifact artifact;
        SegmentLists lists;
        LocalDate date;
        @Before
        public void setUp() throws Exception {

            IdentificatorCreater idCreator = new IdentificatorCreater();
            ProcessGenerator processGenerator = new ProcessGenerator();
            DataManipulator data =  new DataManipulator(processGenerator,idCreator);
            this.lists =  new SegmentLists();

            date = LocalDate.of(2018,10, 10);

            data.createNewArtifact();
            data.addDataToArtifact(null, null, null, true, 0,0,0,
                    0,0);

            artifact = data.getProject().getArtifacts().get(0);
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