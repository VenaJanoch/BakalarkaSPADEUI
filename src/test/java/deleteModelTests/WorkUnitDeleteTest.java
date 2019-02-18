package deleteModelTests;

import SPADEPAC.Project;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.util.ArrayList;

import static org.junit.Assert.assertSame;

public class WorkUnitDeleteTest {

    SegmentLists lists;
    Project project;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        DataModel dataModel = warmUp.getDataModel();

        warmUp.getDataModel().getSaveDataModel().createNewWorkUnit(0);
        warmUp.getDataModel().getSaveDataModel().createNewWorkUnit(1);
        dataModel.getEditDataModel().editDataInWorkUnit("Milestone", "", "",
                0, 0, 0, 0, 0, 0, 0,0, 0,
                0.0, true, 0, true);
        dataModel.getEditDataModel().editDataInWorkUnit("Milestone", "", "",
                0, 0, 0, 0, 0, 0, 0,0, 0,
                0.0, true, 1, true);
        warmUp.getDataModel().getDeleteDataModel().removeWorkUnit(0);

        project = dataModel.getProject();
    }

    @Test
    public void testExist() {
        assertSame(1, project.getWorkUnits().size());
        assertSame(1, project.getWorkUnits().get(0).getId());
    }

}
