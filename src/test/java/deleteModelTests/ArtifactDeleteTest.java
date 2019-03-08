package deleteModelTests;

import SPADEPAC.Configuration;
import SPADEPAC.Project;
import controllers.DeleteFormController;
import controllers.FormController;
import controllers.FormDataController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.BasicTable;
import tables.BranchTable;

import java.util.ArrayList;

import static org.junit.Assert.assertSame;

public class ArtifactDeleteTest {

    SegmentLists lists;
    Project project;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        DataModel dataModel = warmUp.getDataModel();

        warmUp.getDataModel().getSaveDataModel().createNewArtifact(0);
        warmUp.getDataModel().getSaveDataModel().createNewArtifact(1);
        warmUp.getDataModel().getEditDataModel().editDataInArtifact("", "", null, true,  0, 0, 0);
        warmUp.getDataModel().getEditDataModel().editDataInArtifact("", "", null, true,  0, 0, 1);
        warmUp.getDataModel().getDeleteDataModel().removeArtifact(0);

        project = dataModel.getProject();
    }

    @Test
    public void testExist() {
        assertSame(1, project.getArtifacts().size());
        assertSame(1, project.getArtifacts().get(0).getId());
    }

}
