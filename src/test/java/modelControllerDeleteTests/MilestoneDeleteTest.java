package modelControllerDeleteTests;

import SPADEPAC.Milestone;
import SPADEPAC.Phase;
import SPADEPAC.WorkUnitList;
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
import tables.CriterionTable;
import tables.MilestoneTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class MilestoneDeleteTest {

    DataModel dataModel;
    SegmentLists lists;
    LocalDate date;
    Phase phase;
    MapperTableToObject mapperTableToObject;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            dataModel = warmUp.getDataModel();
             lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            DeleteFormController deleteFormController = warmUp.getDeleteFormController();
            MilestoneTable table1 = new MilestoneTable("",true, 0);
            formDataController.saveDataFromMilestoneForm(null, true);
            formDataController.saveDataFromMilestoneForm(null, true);


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

            dataModel.getSaveDataModel().createNewPhase(2);
            dataModel.getEditDataModel().editDataInPhase("Test", name, dates, name, indicators, indicators, unit, indicators,
                    indicators, indicators, indicators, indicators, indicators, false, 2);

            ObservableList list = FXCollections.observableArrayList();
            list.add(0);
            ArrayList<BasicTable> branchTables = new ArrayList<>();
            branchTables.add(table1);
            mapperTableToObject = warmUp.getMapperTableToObject();
            mapperTableToObject.mapTableToPhase(indicators, indicators, indicators , "Text", 2);

            deleteFormController.deleteMilestone(list, branchTables);
            phase = dataModel.getPhase(2);
        }

    @Test
    public void testMapper() {
        Set instacies =  mapperTableToObject.getPhaseToMilestone().keySet();
        assertSame(1, instacies.size());
        assertSame(1, instacies.iterator().next());
    }

        @Test
    public void testListSize() {
        assertSame(1, dataModel.getMilestones().size());
    }

    @Test
    public void testDeleteCorectItem() {
        assertSame(1, dataModel.getMilestones().get(0).getId());
    }

    @Test
    public void testSegmentList() {
        assertSame(2, lists.getMilestoneObservable().size());
        assertSame(1, lists.getMilestoneObservable().get(1).getId());
    }

    @Test
    public void testPhase() {
        assertSame(1, phase.getMilestoneIndex().size());
        int index = phase.getMilestoneIndex().get(0);
        assertSame(1, dataModel.getMilestoneId(index));
    }

}
