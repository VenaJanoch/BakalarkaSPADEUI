package modelControllerDeleteTests;

import SPADEPAC.Person;
import SPADEPAC.RoleType;
import SPADEPAC.WorkUnit;
import controllers.formControllers.DeleteFormController;
import controllers.formControllers.FormController;
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
import tables.ClassTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class RoleTypeDeleteTest {

    DataModel dataModel;
    SegmentLists lists;
    LocalDate date;
    Person person;
    MapperTableToObject mapperTableToObject;
    @Before
    public void setUp() throws Exception {
        
        WarmUp warmUp = new WarmUp();
        dataModel = warmUp.getDataModel();
        FormController formController = warmUp.getFormController();
        lists = warmUp.getLists();
        FormDataController formDataController = warmUp.getFormDataController();
        DeleteFormController deleteFormController = warmUp.getDeleteFormController();
        ClassTable table1 = new ClassTable("","","",true, 0);
        formController.createTableItem(SegmentType.Role_Type);
        formDataController.saveDataFromRoleTypeForm(0 + "", table1);
        formController.createTableItem(SegmentType.Role_Type);
        formDataController.saveDataFromRoleTypeForm(0 + "", new ClassTable("","","",true, 1));


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
        dataModel.getSaveDataModel().createNewPerson(2);
        dataModel.getEditDataModel().editDataInPerson("Test", name, indicators, indicators, indicators, 3, false, 2);


        ObservableList list = FXCollections.observableArrayList();
        list.add(0);
        person = dataModel.getPerson(2);
        ArrayList<BasicTable> branchTables = new ArrayList<>();
        branchTables.add(table1);
        mapperTableToObject = warmUp.getMapperTableToObject();
        mapperTableToObject.mapTableToObject(SegmentType.Person, indicators, new TableToObjectInstanc("Name",2,
                SegmentType.Person));
        deleteFormController.deleteRoleType(list, branchTables);

    }

    @Test
    public void testMapper() {
        Set instacies =  mapperTableToObject.getPersonToRoleTypeMapper().keySet();
        assertSame(1, instacies.size());
        assertSame(1, instacies.iterator().next());
    }
    @Test
    public void testListSize() {
        assertSame(1, dataModel.getRoleTypes().size());
    }

    @Test
    public void testDeleteCorectItem() {
        assertSame(1, dataModel.getRoleTypes().get(0).getId());
    }

    @Test
    public void testSegmentList() {
        assertSame(2, lists.getRoleTypeObservable().size());
        assertSame(1, lists.getRoleTypeObservable().get(1).getId());
    }

    @Test
    public void testWU() {
        assertSame(1, person.getType().size());
        int index = person.getType().get(0);
        assertSame(1, dataModel.getRoleTypeId(index));
    }
}