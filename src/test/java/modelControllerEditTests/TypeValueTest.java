package modelControllerEditTests;

import SPADEPAC.Type;
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

public class TypeValueTest {

    Type type;
    SegmentLists lists;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        FormDataController formDataController = warmUp.getFormDataController();
        formDataController.saveDataFromTypeForm(null, true);
        ArrayList<String> name = new ArrayList<>();
        name.add("");
        name.add("Test2");
        ArrayList<Integer> indicators = new ArrayList<>();
        indicators.add(1);
        indicators.add(0);
        warmUp.getEditFormController().editDataFromClass(SegmentType.Type, "Test", name, indicators, indicators, indicators, name,
                name, new ClassTable("Test", "nevim", "nevim", false, 0), false, 0);
        type = warmUp.getDataModel().getType(0);

    }

    @Test
    public void testAlias() {
        assertEquals("Test", type.getAlias());
    }

    @Test
    public void testName() {
        assertEquals("", type.getName().get(0));
        assertEquals("Test2", type.getName().get(1));
        assertSame(2, type.getName().size());
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, type.getNameIndicator().get(0));
        assertSame(0, type.getNameIndicator().get(1));
        assertSame(2, type.getNameIndicator().size());
    }

    @Test
    public void testIndicatorRole() {
        assertSame(1, type.getTypeClassIndex().get(0));
        assertSame(1, type.getTypeSuperClassIndex().get(0));
    }

    @Test
    public void testId() {
        assertSame(0, type.getId());
    }

    @Test
    public void testExist() {
        assertFalse(type.isExist());
    }
}