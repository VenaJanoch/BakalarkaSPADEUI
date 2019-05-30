package modelControllerEditTests;

import SPADEPAC.Milestone;
import controllers.formControllers.FormController;
import controllers.formControllers.FormDataController;
import javafx.scene.control.TableView;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.MilestoneTable;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class MilestoneValuesTest {

        Milestone milestone;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();
                        ArrayList<String> name = new ArrayList<>();
            name.add("");
            name.add("Test2");
            ArrayList<Integer> indicators = new ArrayList<>();
            indicators.add(1);
            indicators.add(0);
            ArrayList<ArrayList<Integer>> unit = new ArrayList<>();
            unit.add(indicators);
            unit.add(indicators);

            formController.createTableItem(SegmentType.Milestone);
            TableView tableView = new TableView();
            formDataController.saveDataFromMilestoneForm(tableView, true);

            warmUp.getDataModel().getEditDataModel().editDataInMilestone("Test", name,name, indicators,  indicators, indicators, unit, false, 0);
            warmUp.getDataModel().getEditDataModel().editDataInMilestone("Test", name,name, indicators,  indicators, indicators, unit, false, 0);

            milestone = warmUp.getDataModel().getMilestone(0);
        }
    @Test
    public void testAlias() {
        assertEquals("Test", milestone.getAlias() );
    }

    @Test
    public void testName() {
        assertEquals("", milestone.getName().get(0) );
        assertEquals("Test2", milestone.getName().get(1) );
        assertSame(2, milestone.getName().size());
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, milestone.getNameIndicator().get(0) );
        assertSame(0, milestone.getNameIndicator().get(1) );
        assertSame(2, milestone.getNameIndicator().size());
    }

    @Test
    public void testDescription() {
        assertEquals("", milestone.getDescription().get(0) );
        assertEquals("Test2", milestone.getDescription().get(1) );
        assertSame(2, milestone.getDescription().size());
    }

    @Test
    public void testIndicatorDescription() {
        assertSame(1, milestone.getDescriptionIndicator().get(0) );
        assertSame(0, milestone.getDescriptionIndicator().get(1) );
        assertSame(2, milestone.getDescriptionIndicator().size());
    }

    @Test
    public void testIndicatorCriterion() {
        assertSame(2, milestone.getCriteriaIndexs().size());
    }

    @Test
    public void testId() {
        assertSame(0, milestone.getId());
    }

    @Test
    public void testExist() {
        assertFalse(milestone.isExist());
    }
    }
