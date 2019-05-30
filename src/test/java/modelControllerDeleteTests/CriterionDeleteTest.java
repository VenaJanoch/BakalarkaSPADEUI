package modelControllerDeleteTests;

import SPADEPAC.Criterion;
import SPADEPAC.Milestone;
import controllers.formControllers.DeleteFormController;
import controllers.formControllers.FormController;
import controllers.formControllers.FormDataController;
import database.MilestoneDAO;
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
import tables.CriterionTable;
import tables.MilestoneTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class CriterionDeleteTest {

    DataModel dataModel;
    SegmentLists lists;
    LocalDate date;
    Milestone milestone;

    MapperTableToObject mapperTableToObject;

    @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            dataModel = warmUp.getDataModel();
            FormController formController = warmUp.getFormController();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            DeleteFormController deleteFormController = warmUp.getDeleteFormController();
            CriterionTable table1 = new CriterionTable("",true, 0);
            formController.createTableItem(SegmentType.Criterion);
        TableView tableView = new TableView();
            formDataController.saveDataFromCriterionForm(tableView, true);
            formController.createTableItem(SegmentType.Criterion);
            formDataController.saveDataFromCriterionForm(tableView, true);


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
            formController.createTableItem(SegmentType.Milestone);

            formDataController.saveDataFromMilestoneForm(tableView, true);

            warmUp.getDataModel().getEditDataModel().editDataInMilestone("Test", name,name, indicators,  indicators, indicators, unit, false, 0);
            mapperTableToObject = warmUp.getMapperTableToObject();
            mapperTableToObject.mapTableToObject(SegmentType.Milestone, indicators, new TableToObjectInstanc(table1.getAlias(), 0, SegmentType.Milestone));
            milestone = dataModel.getMilestone(0);

            ObservableList list = FXCollections.observableArrayList();
            list.add(0);
            ArrayList<BasicTable> branchTables = new ArrayList<>();
            branchTables.add(table1);
            deleteFormController.deleteCriterion(list, branchTables);
        }

    @Test
    public void testMapper() {
        Set instacies =  mapperTableToObject.getMilestoneToCriterionMapper().keySet();
        assertSame(1, instacies.size());
        assertSame(1, instacies.iterator().next());
    }

    @Test
    public void testListSize() {
        assertSame(1, dataModel.getCriterions().size());
    }

    @Test
    public void testDeleteCorectItem() {
        assertSame(1, dataModel.getCriterions().get(0).getId());
    }

    @Test
    public void testSegmentList() {
        assertSame(2, lists.getCriterionObservable().size());
        assertSame(1, lists.getCriterionObservable().get(1).getId());
    }

    @Test
    public void testMilestone() {
        assertSame(1, milestone.getCriteriaIndexs().get(0).getCriterions().size());
        int index = milestone.getCriteriaIndexs().get(0).getCriterions().get(0);
        assertSame(1, dataModel.getCriterionId(index));
    }

    }
