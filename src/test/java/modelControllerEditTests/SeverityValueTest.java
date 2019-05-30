package modelControllerEditTests;

import SPADEPAC.Severity;
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

public class SeverityValueTest {

        Severity severity;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();
            formController.createTableItem(SegmentType.Severity);
            TableView tableView = new TableView();
            formDataController.saveDataFromSeverity(tableView, true);
            ArrayList<String> name = new ArrayList<>();
            name.add("");
            name.add("Test2");
            ArrayList<Integer> indicators = new ArrayList<>();
            indicators.add(1);
            indicators.add(0);
            warmUp.getEditFormController().editDataFromClass(SegmentType.Severity, "Test", name, indicators, indicators, indicators, name,
                    name, new ClassTable("Test", "nevim", "nevim", false, 0), false, 0 );
            severity = warmUp.getDataModel().getSeverity(0);

        }
    @Test
    public void testAlias() {
        assertEquals("Test", severity.getAlias() );
    }

    @Test
    public void testName() {
        assertEquals("", severity.getName().get(0) );
        assertEquals("Test2", severity.getName().get(1) );
        assertSame(2, severity.getName().size());
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, severity.getNameIndicator().get(0) );
        assertSame(0, severity.getNameIndicator().get(1) );
        assertSame(2, severity.getNameIndicator().size());
    }

    @Test
    public void testIndicatorRole() {
        assertSame(1, severity.getSeverityClassIndex().get(0) );
        assertSame(1, severity.getSeveritySuperClassIndex().get(0) );
    }

    @Test
    public void testId() {
        assertSame(0, severity.getId());
    }

    @Test
    public void testExist() {
        assertFalse(severity.isExist());
    }

}