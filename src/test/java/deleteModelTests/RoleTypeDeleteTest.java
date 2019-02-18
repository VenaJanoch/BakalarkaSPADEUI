package deleteModelTests;

import SPADEPAC.Project;
import SPADEPAC.Role;
import controllers.DeleteFormController;
import controllers.FormController;
import controllers.FormDataController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.BasicTable;
import tables.ClassTable;
import tables.RoleTable;

import java.util.ArrayList;

import static org.junit.Assert.assertSame;

public class RoleTypeDeleteTest {

        Role role;
        Project project;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            DataModel dataModel = warmUp.getDataModel();

            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();
            formController.createTableItem(SegmentType.RoleType);
            formDataController.saveDataFromRoleTypeForm("Jmeno1", new ClassTable("0_Jmeno1","Test", "Test",0));
            formDataController.saveDataFromRoleTypeForm("Jmeno1", new ClassTable("0_Jmeno1","Test", "Test",1));
            DeleteFormController deleteFormController = warmUp.getDeleteFormController();

            ArrayList<Integer> list = new ArrayList<>();
            list.add(1);
            ObservableList observableList = FXCollections.observableList(list);


            formDataController.saveDataFromRoleForm("Jmeno1", 1, new RoleTable("0_Jmeno1", "NO", "", 0));


            ArrayList<BasicTable> basicTables = new ArrayList<>();
            basicTables.add(new BasicTable("Table",0));

            deleteFormController.deleteRoleType(observableList, basicTables);
            role = dataModel.getRole(0);
            project = dataModel.getProject();

        }

    @Test
    public void testExist() {
        assertSame(1, project.getRoleType().size());
    }

    @Test
    public void testList() {
        assertSame(1, lists.getRoleTypeObservable().get(1).getId());
        assertSame(2, lists.getRoleTypeObservable().size());

    }

    @Test
    public void testRemoveFromMilestone() {
        assertSame(-1, role.getType());
    }


}