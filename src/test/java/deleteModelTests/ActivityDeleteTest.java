package deleteModelTests;

import SPADEPAC.Project;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.util.ArrayList;

import static org.junit.Assert.assertSame;

public class ActivityDeleteTest {

    SegmentLists lists;
    Project project;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        DataModel dataModel = warmUp.getDataModel();

        warmUp.getDataModel().getSaveDataModel().createNewActivity(0);
        warmUp.getDataModel().getSaveDataModel().createNewActivity(1);
        warmUp.getDataModel().getEditDataModel().editDataInActivity("", "", new ArrayList<>(), 0);
        warmUp.getDataModel().getEditDataModel().editDataInActivity("", "", new ArrayList<>(), 1);

        warmUp.getDataModel().getDeleteDataModel().removeActivity(0);

        project = dataModel.getProject();
    }

    @Test
    public void testExist() {
        assertSame(1, project.getActivities().size());
        assertSame(1, project.getActivities().get(0).getId());
    }

}
