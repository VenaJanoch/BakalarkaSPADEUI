package deleteModelTests;

import SPADEPAC.Project;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.util.ArrayList;

import static org.junit.Assert.assertSame;

public class IterationDeleteTest {

    SegmentLists lists;
    Project project;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        DataModel dataModel = warmUp.getDataModel();

        warmUp.getDataModel().getSaveDataModel().createNewIteration(0);
        warmUp.getDataModel().getSaveDataModel().createNewIteration(1);
        warmUp.getDataModel().getEditDataModel().editDataInIteration("", null, null, "", 0,
                 new ArrayList<>(), 0);
        warmUp.getDataModel().getEditDataModel().editDataInIteration("", null, null, "", 0,
                new ArrayList<>(), 1);
        warmUp.getDataModel().getDeleteDataModel().removeIteration(0);

        project = dataModel.getProject();
    }

    @Test
    public void testExist() {
        assertSame(1, project.getIterations().size());
        assertSame(1, project.getIterations().get(0).getId());
    }

}
