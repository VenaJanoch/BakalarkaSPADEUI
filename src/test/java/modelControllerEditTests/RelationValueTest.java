package modelControllerEditTests;

import SPADEPAC.Relation;
import controllers.formControllers.FormController;
import controllers.formControllers.FormDataController;
import javafx.scene.control.TableView;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.ClassTable;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class RelationValueTest {

    Relation relation;
    SegmentLists lists;
    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        FormDataController formDataController = warmUp.getFormDataController();
        FormController formController = warmUp.getFormController();
        formController.createTableItem(SegmentType.Relation);
        TableView tableView = new TableView();
        formDataController.saveDataFromRelationForm(tableView, true);
        ArrayList<String> name = new ArrayList<>();
        name.add("");
        name.add("Test2");
        ArrayList<Integer> indicators = new ArrayList<>();
        indicators.add(1);
        indicators.add(0);
        warmUp.getEditFormController().editDataFromClass(SegmentType.Relation, "Test", name, indicators, indicators, indicators, name,
                name, new ClassTable("Test", "nevim", "nevim", false, 0), false, 0 );
        relation = warmUp.getDataModel().getRelation(0);

    }
    @Test
    public void testAlias() {
        assertEquals("Test", relation.getAlias() );
    }

    @Test
    public void testName() {
        assertEquals("", relation.getName().get(0) );
        assertEquals("Test2", relation.getName().get(1) );
        assertSame(2, relation.getName().size());
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, relation.getNameIndicator().get(0) );
        assertSame(0, relation.getNameIndicator().get(1) );
        assertSame(2, relation.getNameIndicator().size());
    }

    @Test
    public void testIndicatorRole() {
        assertSame(1, relation.getRelationClassIndex().get(0) );
        assertSame(1, relation.getRelationSuperClassIndex().get(0) );
    }

    @Test
    public void testId() {
        assertSame(0, relation.getId());
    }

    @Test
    public void testExist() {
        assertFalse(relation.isExist());
    }
}