package modelControllerDeleteTests;

import SPADEPAC.ConfigPersonRelation;
import SPADEPAC.Configuration;
import controllers.formControllers.DeleteFormController;
import controllers.formControllers.FormController;
import controllers.formControllers.FormDataController;
import database.CPRDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.MapperTableToObject;
import services.SegmentLists;
import services.SegmentType;
import tables.BasicTable;
import tables.BranchTable;
import tables.CPRTable;
import tables.ChangeTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

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
            FormController formController = warmUp.getFormController();

            DeleteFormController deleteFormController = warmUp.getDeleteFormController();
            FormDataController formDataController = warmUp.getFormDataController();
            CPRTable table1 = new CPRTable("", "",true, 0);
            formController.createTableItem(SegmentType.Config_Person_Relation);
            TableView tableView = new TableView();
            formDataController.saveDataFromCPR(tableView, true);
            formController.createTableItem(SegmentType.Config_Person_Relation);
            formDataController.saveDataFromCPR(tableView, true);

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
                    indicators, indicators, indicators, indicators, indicators, 3, 1,false, 2);
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
        Set instacies =  mapperTableToObject.getConfigurationToCPRMapper().keySet();
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