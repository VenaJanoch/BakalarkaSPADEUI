package modelControllerDeleteTests;

import SPADEPAC.Artifact;
import controllers.formControllers.DeleteFormController;
import controllers.formControllers.FormController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class ArtefaktDeleteTest {


    SegmentLists lists;
    LocalDate date;
    DataModel dataModel;
    @Before
    public void setUp() throws Exception {

        this.lists = new SegmentLists();

        date = LocalDate.of(2018, 10, 10);

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
