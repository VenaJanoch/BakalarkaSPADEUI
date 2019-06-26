package modelControllerDeleteTests;

import SPADEPAC.Configuration;
import controllers.formControllers.DeleteFormController;
import controllers.formControllers.FormDataController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.MapperTableToObject;
import services.SegmentLists;
import tables.BasicTable;
import tables.ChangeTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertSame;

public class ChangeDeleteTest {

    DataModel dataModel;
    SegmentLists lists;
    LocalDate date;
    Configuration configuration;
    MapperTableToObject mapperTableToObject;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        dataModel = warmUp.getDataModel();

        DeleteFormController deleteFormController = warmUp.getDeleteFormController();
        FormDataController formDataController = warmUp.getFormDataController();
        ChangeTable table1 = new ChangeTable("", true, 0);
        formDataController.saveDataFromChangeForm(null, true);
        formDataController.saveDataFromChangeForm(null, true);

        date = LocalDate.of(2018, 10, 10);
        lists = warmUp.getLists();
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
        dataModel.getSaveDataModel().createNewConfiguration(2);
        dataModel.getEditDataModel().editDataInConfiguration("Test", name, name, dates, true, unit, unit, unit, indicators, indicators, indicators,
                indicators, indicators, indicators, indicators, indicators, 3, 1, false, 2);
        configuration = dataModel.getConfiguration(2);
        mapperTableToObject = warmUp.getMapperTableToObject();
        mapperTableToObject.mapTableToConfiguration(new ArrayList<>(), new ArrayList<>(), unit, new ArrayList<>(), "Test", 2);

        ObservableList list = FXCollections.observableArrayList();
        list.add(0);

        ArrayList<BasicTable> tables = new ArrayList<>();
        tables.add(table1);
        deleteFormController.deleteChange(list, tables);

    }

    @Test
    public void testMapper() {
        Set instacies = mapperTableToObject.getConfigurationToChangeMapper().keySet();
        assertSame(1, instacies.size());
        assertSame(1, instacies.iterator().next());
    }

    @Test
    public void testListSize() {
        assertSame(1, dataModel.getChanges().size());
    }

    @Test
    public void testDeleteCorectItem() {
        assertSame(1, dataModel.getChanges().get(0).getId());
    }

    @Test
    public void testSegmentList() {
        assertSame(2, lists.getChangeObservable().size());
        assertSame(1, lists.getChangeObservable().get(1).getId());
    }

    @Test
    public void testConfiguration() {
        assertSame(1, configuration.getChangesIndexs().get(0).getChanges().size());
        int index = configuration.getChangesIndexs().get(0).getChanges().get(0);
        assertSame(1, dataModel.getChangeId(index));
    }
}
