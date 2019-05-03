package modelControllerDeleteTests;

import SPADEPAC.Configuration;
import SPADEPAC.Iteration;
import SPADEPAC.Phase;
import controllers.formControllers.DeleteFormController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import tables.BasicTable;
import tables.ChangeTable;
import tables.ConfigTable;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class ConfigurationDeleteTest {

    DataModel dataModel;
    SegmentLists lists;
    LocalDate date;
    Iteration iteration;
    Phase phase;
    
    @Before
    public void setUp() throws Exception {

        this.lists = new SegmentLists();


        WarmUp warmUp = new WarmUp();
        dataModel = warmUp.getDataModel();
        DeleteFormController deleteFormController = warmUp.getDeleteFormController();
        warmUp.getDataModel().getSaveDataModel().createNewConfiguration(0);
        warmUp.getDataModel().getSaveDataModel().createNewConfiguration(1);
        date = LocalDate.of(2018, 10, 10);
        ArrayList<String> name = new ArrayList<>();
        name.add("");
        name.add("Test2");
        ArrayList<Integer> indicators = new ArrayList<>();
        indicators.add(0);
        indicators.add(1);
        ArrayList<ArrayList<Integer>> unit = new ArrayList<>();
        unit.add(indicators);
        unit.add(indicators);

        ArrayList<LocalDate> dates = new ArrayList<>();
        dates.add(date);

        dataModel.getSaveDataModel().createNewIteration(2);
        dataModel.getEditDataModel().editDataInIteration("Test", name, dates, dates, name, indicators, unit, indicators, indicators, indicators, indicators, indicators,
                indicators,false,2);
        dataModel.getSaveDataModel().createNewPhase(2);
        dataModel.getEditDataModel().editDataInPhase("Test", name, dates, name, indicators, indicators, unit, indicators,
                indicators, indicators, indicators, indicators, indicators, false, 2);

        deleteFormController.deleteConfiguration(0);
        iteration = dataModel.getIteration(2);
        phase = dataModel.getPhase(2);

    }
    @Test
    public void testListSize() {
        assertSame(1, dataModel.getConfigurations().size());
    }

    @Test
    public void testDeleteCorectItem() {
        assertSame(1, dataModel.getConfigurations().get(0).getId());
    }

//    @Test
//    public void testSegmentList() {
//        assertSame(2, lists.getConfigObservable().size());
//        assertSame(1, lists.getConfigObservable().get(1).getId());
//    }

    @Test
    public void testIteration() {
        assertSame(1, iteration.getConfiguration().size());
        int index = iteration.getConfiguration().get(0);
        assertSame(1, dataModel.getConfigurationId(index));
    }

    @Test
    public void testPhase() {
        assertSame(1, phase.getConfiguration().size());
        int index = phase.getConfiguration().get(0);
        assertSame(1, dataModel.getConfigurationId(index));
    }
}

