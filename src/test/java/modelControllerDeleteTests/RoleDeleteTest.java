package modelControllerDeleteTests;

import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import static org.junit.Assert.assertSame;

public class RoleDeleteTest {

    DataModel dataModel;
    SegmentLists lists;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        dataModel = warmUp.getDataModel();
        warmUp.getDataModel().getSaveDataModel().createNewArtifact(0);
        warmUp.getDataModel().getSaveDataModel().createNewArtifact(1);
        warmUp.getDataModel().getDeleteDataModel().removeArtifact(0);
    }

    @Test
    public void testListSize() {
        assertSame(1, dataModel.getArtifacts().size());
    }

    @Test
    public void testDeleteCorectItem() {
        assertSame(1, dataModel.getArtifacts().get(0).getId());
    }
}