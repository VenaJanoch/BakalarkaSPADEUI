package modelControllerDeleteTests;

import SPADEPAC.Relation;
import SPADEPAC.WorkUnit;
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
import tables.BasicTable;
import tables.ClassTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class RelationDeleteTest {

    DataModel dataModel;
    SegmentLists lists;
    LocalDate date;
    WorkUnit workUnit;
    MapperTableToObject mapperTableToObject;
    @Before
    public void setUp() throws Exception {


        WarmUp warmUp = new WarmUp();
        dataModel = warmUp.getDataModel();
        lists = warmUp.getLists();
        FormDataController formDataController = warmUp.getFormDataController();
        DeleteFormController deleteFormController = warmUp.getDeleteFormController();
        ClassTable table1 = new ClassTable("","","",true, 0);
        formDataController.saveDataFromRelationForm(null, true);
        formDataController.saveDataFromRelationForm(null, true);


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
        ArrayList<Double> estimate = new ArrayList<>();
        estimate.add(1.0);
        estimate.add(0.0);
        ArrayList<LocalDate> dates = new ArrayList<>();
        dates.add(date);
        ArrayList<Integer> progress = new ArrayList<>();
        progress.add(12);
        progress.add(23);
        dataModel.getSaveDataModel().createNewWorkUnit(2);
        dataModel.getEditDataModel().editDataInWorkUnit("Test", progress, indicators, name,name, name, indicators, indicators, indicators, indicators, indicators, indicators,
                indicators,estimate, indicators,  indicators, indicators, indicators, indicators,indicators, indicators,indicators, indicators,indicators, indicators,
                dates, indicators, false, indicators, unit, 2);

        ObservableList list = FXCollections.observableArrayList();
        list.add(0);
        workUnit = dataModel.getWorkUnit(2);
        ArrayList<BasicTable> branchTables = new ArrayList<>();
        branchTables.add(table1);
        mapperTableToObject = warmUp.getMapperTableToObject();
        mapperTableToObject.mapTableToWU(indicators, indicators, indicators, indicators, indicators, indicators
                ,indicators, 0,"Test");
        deleteFormController.deleteRelation(list, branchTables);

    }

    @Test
    public void testListSize() {
        assertSame(1, dataModel.getRelations().size());
    }

    @Test
    public void testDeleteCorectItem() {
        assertSame(1, dataModel.getRelations().get(0).getId());
    }

    @Test
    public void testSegmentList() {
        assertSame(2, lists.getRelationTypeObservable().size());
        assertSame(1, lists.getRelationTypeObservable().get(1).getId());
    }

    @Test
    public void testWU() {
        assertSame(1, workUnit.getRelationIndex().size());
        int index = workUnit.getRelationIndex().get(0);
        assertSame(1, dataModel.getRelationId(index));
    }
}