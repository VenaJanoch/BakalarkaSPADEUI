package modelControllerEditTests;

import SPADEPAC.Priority;
import controllers.formControllers.FormDataController;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.ClassTable;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

public class PriorityValueTest {

    Priority priority;
    SegmentLists lists;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        FormDataController formDataController = warmUp.getFormDataController();
        formDataController.saveDataFromPriority(null, true);
        ArrayList<String> name = new ArrayList<>();
        name.add("");
        name.add("Test2");
        ArrayList<Integer> indicators = new ArrayList<>();
        indicators.add(1);
        indicators.add(0);
        warmUp.getEditFormController().editDataFromClass(SegmentType.Priority, "Test", name, indicators, indicators, indicators, name,
                name, new ClassTable("Test", "nevim", "nevim", false, 0), false, 0);
        priority = warmUp.getDataModel().getPriority(0);
    }

    @Test
    public void testAlias() {
        assertEquals("Test", priority.getAlias());
    }

    @Test
    public void testName() {
        assertEquals("", priority.getName().get(0));
        assertEquals("Test2", priority.getName().get(1));
        assertSame(2, priority.getName().size());
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, priority.getNameIndicator().get(0));
        assertSame(0, priority.getNameIndicator().get(1));
        assertSame(2, priority.getNameIndicator().size());
    }

    @Test
    public void testIndicatorRole() {
        assertSame(1, priority.getPriorityClassIndex().get(0));
        assertSame(1, priority.getPrioritySuperClassIndex().get(0));
    }

    @Test
    public void testId() {
        assertSame(0, priority.getId());
    }

    @Test
    public void testExist() {
        assertFalse(priority.isExist());
    }
}