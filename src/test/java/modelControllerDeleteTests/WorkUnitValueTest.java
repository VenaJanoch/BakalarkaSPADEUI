package modelControllerDeleteTests;

import SPADEPAC.Activity;
import SPADEPAC.Iteration;
import SPADEPAC.Phase;
import controllers.formControllers.DeleteFormController;
import controllers.formControllers.FormDataController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.MapperTableToObject;
import services.SegmentLists;
import services.SegmentType;
import services.TableToObjectInstanc;
import tables.BasicTable;
import tables.WorkUnitTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertSame;

public class WorkUnitValueTest {

    DataModel dataModel;
    SegmentLists lists;
    LocalDate date;
    Iteration iteration;
    Phase phase;
    Activity activity;
    MapperTableToObject mapperTableToObject;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        dataModel = warmUp.getDataModel();
        lists = warmUp.getLists();
        DeleteFormController deleteFormController = warmUp.getDeleteFormController();
        FormDataController formDataController = warmUp.getFormDataController();

        WorkUnitTable table1 = new WorkUnitTable("", true, 0);
        formDataController.saveDataFromWorkUnit(null, true);
        formDataController.saveDataFromWorkUnit(null, true);
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
                indicators, false, 2);
        dataModel.getSaveDataModel().createNewPhase(2);
        dataModel.getEditDataModel().editDataInPhase("Test", name, dates, name, indicators, indicators, unit, indicators,
                indicators, indicators, indicators, indicators, indicators, false, 2);
        dataModel.getSaveDataModel().createNewActivity(2);
        dataModel.getEditDataModel().editDataInActivity("Test", name, name, unit, indicators, indicators, indicators, dates, indicators, false, 2);


        ObservableList list = FXCollections.observableArrayList();
        list.add(0);
        ArrayList<BasicTable> branchTables = new ArrayList<>();
        branchTables.add(table1);

        mapperTableToObject = warmUp.getMapperTableToObject();
        mapperTableToObject.mapTableToPhase(indicators, indicators, indicators, "Text", 2);

        mapperTableToObject = warmUp.getMapperTableToObject();
        mapperTableToObject.mapTableToIteration(indicators, indicators, "Text", 2);

        mapperTableToObject.mapTableToObject(SegmentType.Activity, indicators, new TableToObjectInstanc("Test", 2, SegmentType.Activity));


        deleteFormController.deleteWorkUnit(list, branchTables);
        iteration = dataModel.getIteration(2);
        phase = dataModel.getPhase(2);
        activity = dataModel.getActivity(2);
    }

    @Test
    public void testMapper() {
        Set instacies = mapperTableToObject.getPhaseToWUMapper().keySet();
        assertSame(1, instacies.size());
        assertSame(1, instacies.iterator().next());
        instacies = mapperTableToObject.getIterationToWUMapper().keySet();
        assertSame(1, instacies.size());
        assertSame(1, instacies.iterator().next());
        instacies = mapperTableToObject.getActivityToWUMapper().keySet();
        assertSame(1, instacies.size());
        assertSame(1, instacies.iterator().next());
    }


    @Test
    public void testListSize() {
        assertSame(1, dataModel.getWorkUnits().size());
    }

    @Test
    public void testDeleteCorectItem() {
        assertSame(1, dataModel.getWorkUnits().get(0).getId());
    }

    @Test
    public void testSegmentList() {
        assertSame(2, lists.getWorkUnitsObservable().size());
        assertSame(1, lists.getWorkUnitsObservable().get(1).getId());
    }

    @Test
    public void testIteration() {

        assertSame(1, iteration.getWorkUnits().get(0).getWorkUnits().size());
        int index = iteration.getWorkUnits().get(0).getWorkUnits().get(0);
        assertSame(1, dataModel.getWorkUnitId(index));
    }

    @Test
    public void testPhase() {
        assertSame(1, phase.getWorkUnits().get(0).getWorkUnits().size());
        int index = phase.getWorkUnits().get(0).getWorkUnits().get(0);
        assertSame(1, dataModel.getWorkUnitId(index));
    }

    @Test
    public void testActivity() {
        assertSame(1, activity.getWorkUnits().get(0).getWorkUnits().size());
        int index = activity.getWorkUnits().get(0).getWorkUnits().get(0);
        assertSame(1, dataModel.getWorkUnitId(index));
    }

}