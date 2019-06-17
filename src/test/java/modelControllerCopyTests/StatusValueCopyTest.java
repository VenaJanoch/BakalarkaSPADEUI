package modelControllerCopyTests;

import SPADEPAC.Status;
import controllers.formControllers.FormController;
import controllers.formControllers.FormDataController;
import controllers.formControllers.FormFillController;
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

public class StatusValueCopyTest {

    Status status;
    SegmentLists lists;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        FormDataController formDataController = warmUp.getFormDataController();
        formDataController.saveDataFromStatusForm(null, true);

        ArrayList<String> name = new ArrayList<>();
        name.add("");
        name.add("Test2");
        ArrayList<Integer> indicators = new ArrayList<>();
        indicators.add(1);
        indicators.add(0);
        warmUp.getEditFormController().editDataFromClass(SegmentType.Status, "Test", name, indicators, indicators, indicators, name,
                name, new ClassTable("Test", "nevim", "nevim", false, 0), false, 0);
        FormFillController formFillController = warmUp.getFormFillController();
        formFillController.fillStatusForm(null, 0);
        status = warmUp.getDataModel().getStatus(1);

    }

    @Test
    public void testAlias() {
        assertEquals("1", status.getAlias());
    }

    @Test
    public void testName() {
        assertEquals("", status.getName().get(0));
        assertEquals("Test2", status.getName().get(1));
        assertSame(2, status.getName().size());
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, status.getNameIndicator().get(0));
        assertSame(0, status.getNameIndicator().get(1));
        assertSame(2, status.getNameIndicator().size());
    }

    @Test
    public void testIndicatorRole() {
        assertSame(1, status.getStatusClassIndex().get(0));
        assertSame(1, status.getStatusSuperClassIndex().get(0));
    }

    @Test
    public void testId() {
        assertSame(1, status.getId());
    }

    @Test
    public void testExist() {
        assertFalse(status.isExist());
    }
}