package modelControllerDeleteTests;

import SPADEPAC.Configuration;
import controllers.formControllers.DeleteFormController;
import controllers.formControllers.FormController;
import controllers.formControllers.FormDataController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.MapperTableToObject;
import services.SegmentLists;
import services.SegmentType;
import services.TableToObjectInstanc;
import tables.BasicTable;
import tables.BranchTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class BranchDeleteTest {

        DataModel dataModel;
        SegmentLists lists;
        LocalDate date;
        Configuration configuration;
        MapperTableToObject mapperTableToObject;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            dataModel = warmUp.getDataModel();
            mapperTableToObject = warmUp.getMapperTableToObject();
            FormController formController = warmUp.getFormController();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            DeleteFormController deleteFormController = warmUp.getDeleteFormController();
            BranchTable table1 = new BranchTable("", "YES", true, true, 0);
            formController.createTableItem(SegmentType.Branch);
            TableView tableView = new TableView();
            formDataController.saveDataFromBranch(tableView, true);
            formController.createTableItem(SegmentType.Branch);
            formDataController.saveDataFromBranch(tableView, true);


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
            dataModel.getSaveDataModel().createNewConfiguration(2);
            dataModel.getEditDataModel().editDataInConfiguration("Test", name, name, dates, true, unit, unit, unit, indicators, indicators, indicators,
                    indicators, indicators, indicators, indicators, indicators,3, 1,false,2);
            mapperTableToObject.mapTableToConfiguration(unit, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "Test", 2);

            configuration = dataModel.getConfiguration(2);

            ObservableList list = FXCollections.observableArrayList();
            list.add(0);
            ArrayList<BasicTable> branchTables = new ArrayList<>();
            branchTables.add(table1);
            deleteFormController.deleteBranch(list, branchTables);
        }

    @Test
    public void testListSize() {
        assertSame(1, dataModel.getBranches().size());
    }

    @Test
    public void testMapper() {
        Set instacies =  mapperTableToObject.getConfigurationToBranchMapper().keySet();
        assertSame(1, instacies.size());
        assertSame(1, instacies.iterator().next());
    }


    @Test
    public void testDeleteCorectItem() {
        assertSame(1, dataModel.getBranches().get(0).getId());
    }

    @Test
    public void testSegmentList() {
        assertSame(2, lists.getBranchObservable().size());
        assertSame(1, lists.getBranchObservable().get(1).getId());
    }

    @Test
    public void testConfiguration() {
        assertSame(1, configuration.getBranchIndexs().get(0).getBranches().size());
        int index = configuration.getBranchIndexs().get(0).getBranches().get(0);
        assertSame(1, dataModel.getBranchId(index));
    }

}
