package deleteModelTests;

import SPADEPAC.Project;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import static org.junit.Assert.assertSame;

public class ChangeDeleteTest {

    SegmentLists lists;
    Project project;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        DataModel dataModel = warmUp.getDataModel();

        warmUp.getDataModel().getSaveDataModel().createNewChange(0);
        warmUp.getDataModel().getSaveDataModel().createNewChange(1);
        warmUp.getDataModel().getEditDataModel().editDataInChange("", "", true, 0);
        warmUp.getDataModel().getEditDataModel().editDataInChange("", "", true, 0);
        warmUp.getDataModel().getDeleteDataModel().removeChange(0);

        project = dataModel.getProject();
    }

    @Test
    public void testExist() {
        assertSame(1, project.getChanges().size());
        assertSame(1, project.getChanges().get(0).getId());
    }

}
