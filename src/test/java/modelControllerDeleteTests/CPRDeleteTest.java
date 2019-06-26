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
import tables.CPRTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertSame;

public class CPRDeleteTest {

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
        CPRTable table1 = new CPRTable("", "", true, 0);
        formDataController.saveDataFromCPR(null, true);
        formDataController.saveDataFromCPR(null, true);

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
        ObservableList list = FXCollections.observableArrayList();
        list.add(0);
        mapperTableToObject = warmUp.getMapperTableToObject();
        mapperTableToObject.mapTableToConfiguration(new ArrayList<>(), unit, new ArrayList<>(), new ArrayList<>(), "Test", 2);

        ArrayList<BasicTable> tables = new ArrayList<>();
        tables.add(table1);
        deleteFormController.deleteCPR(list, tables);
    }

    @Test
    public void testMapper() {
        Set instacies = mapperTableToObject.getConfigurationToCPRMapper().keySet();
        assertSame(1, instacies.size());
        assertSame(1, instacies.iterator().next());
    }

    @Test
    public void testListSize() {
        assertSame(1, dataModel.getConfigPersonRelations().size());
    }

    @Test
    public void testDeleteCorectItem() {
        assertSame(1, dataModel.getConfigPersonRelations().get(0).getId());
    }

    @Test
    public void testSegmentList() {
        assertSame(2, lists.getCPRObservable().size());
        assertSame(1, lists.getCPRObservable().get(1).getId());
    }

    @Test
    public void testConfiguration() {
        assertSame(1, configuration.getCPRsIndexs().get(0).getCPRs().size());
        int index = configuration.getCPRsIndexs().get(0).getCPRs().get(0);
        assertSame(1, dataModel.getCPRId(index));
    }
}