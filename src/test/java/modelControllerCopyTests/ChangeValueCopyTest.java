package modelControllerCopyTests;

import SPADEPAC.Change;
import controllers.formControllers.EditFormController;
import controllers.formControllers.FormDataController;
import controllers.formControllers.FormFillController;
import javafx.scene.control.TableView;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

public class ChangeValueCopyTest {

    Change change;
    SegmentLists lists;

    @Before
    public void setUp() throws Exception {

        this.lists = new SegmentLists();

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        FormDataController formDataController = warmUp.getFormDataController();
        formDataController.saveDataFromChangeForm(null, true);
        ArrayList<String> name = new ArrayList<>();
        name.add("");
        name.add("Test2");
        ArrayList<Integer> indicators = new ArrayList<>();
        indicators.add(1);
        indicators.add(0);
        warmUp.getDataModel().getEditDataModel().editDataInChange("Test", name, name, indicators, indicators, indicators,  false, 0);
        FormFillController formFillController = warmUp.getFormFillController();
        formFillController.fillChangeForm(null, 0);
        change = warmUp.getDataModel().getChange(1);

    }

    @Test
    public void testAlias() {
        assertEquals("1", change.getAlias() );
    }

    @Test
    public void testName() {
        assertEquals("", change.getName().get(0) );
        assertEquals("Test2", change.getName().get(1) );
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, change.getNameIndicator().get(0) );
        assertSame(0, change.getNameIndicator().get(1) );
    }

    @Test
    public void testDescription() {
        assertEquals("", change.getDescription().get(0) );
        assertEquals("Test2", change.getDescription().get(1) );
    }

    @Test
    public void testIndicatorDescription() {
        assertSame(1, change.getDescriptionIndicator().get(0) );
        assertSame(0, change.getDescriptionIndicator().get(1) );
    }

    @Test
    public void testId() {
        assertSame(1, change.getId());
    }

    @Test
    public void testExist() {
        assertFalse(change.isExist());
    }


}
