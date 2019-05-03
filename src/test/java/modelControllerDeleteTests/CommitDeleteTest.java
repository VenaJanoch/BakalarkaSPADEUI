package modelControllerDeleteTests;

import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;

import static org.junit.Assert.assertSame;

public class CommitDeleteTest {


    SegmentLists lists;
    LocalDate date;
    DataModel dataModel;
    @Before
    public void setUp() throws Exception {

        this.lists = new SegmentLists();

        date = LocalDate.of(2018, 10, 10);

        WarmUp warmUp = new WarmUp();
        dataModel = warmUp.getDataModel();
        warmUp.getDataModel().getSaveDataModel().createNewCommit(0);
        warmUp.getDataModel().getSaveDataModel().createNewCommit(1);
        warmUp.getDataModel().getDeleteDataModel().removeCommit(0);

    }

    @Test
    public void testListSize() {
        assertSame(1, dataModel.getCommits().size());
    }

    @Test
    public void testDeleteCorectItem() {
        assertSame(1, dataModel.getCommits().get(0).getId());
    }
}
