package deleteModelTests;

import SPADEPAC.Iteration;
import SPADEPAC.Phase;
import SPADEPAC.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import tables.BasicTable;

import java.util.ArrayList;

import static org.junit.Assert.assertSame;

public class ConfigurationDeleteTest {

    SegmentLists lists;
    Project project;
    Phase phase;
    Iteration iteration;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        DataModel dataModel = warmUp.getDataModel();

        warmUp.getDataModel().getSaveDataModel().createNewConfiguration(0);
        warmUp.getDataModel().getSaveDataModel().createNewConfiguration(1);
        warmUp.getDataModel().getEditDataModel().editDataInConfiguration("", null,true, 0, new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(),0);
        warmUp.getDataModel().getEditDataModel().editDataInConfiguration("", null,true, 0, new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(),1);


        dataModel.getSaveDataModel().createNewPhase(0);
        dataModel.getEditDataModel().editDataInPhase("Test", null, "", 0, 0, 0,
                0, new ArrayList<>(), 0);

        dataModel.getSaveDataModel().createNewIteration(0);
        dataModel.getEditDataModel().editDataInIteration("Test", null, null,"", 0, 0, 0,
                 new ArrayList<>(), 0);

        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        ObservableList observableList = FXCollections.observableList(list);
        ArrayList<BasicTable> basicTables = new ArrayList<>();
        basicTables.add(new BasicTable("Table", 0));

        warmUp.getDeleteFormController().deleteConfiguration(observableList, basicTables);

        phase = dataModel.getPhase(0);
        iteration = dataModel.getIteration(0);
        project = dataModel.getProject();
    }

    @Test
    public void testExist() {
        assertSame(1, project.getConfiguration().size());
    }



    @Test
    public void testRemoveFromSegment() {
        assertSame(-1, phase.getConfiguration());
        assertSame(-1, iteration.getConfiguration());

    }

}
