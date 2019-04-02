package modelControllerTests;

import controllers.FormController;
import controllers.FormDataController;
import SPADEPAC.Milestone;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import services.SegmentType;
import tables.CriterionTable;
import tables.MilestoneTable;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MilestoneValuesTest {

        Milestone milestone;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
            FormDataController formDataController = warmUp.getFormDataController();
            FormController formController = warmUp.getFormController();

            warmUp.getDataModel().getSaveDataModel().createNewMilestone("","",new ArrayList<>(),0);

            milestone = warmUp.getDataModel().getMilestone(0);
        }
    @Test
    public void testId() {
        assertSame(0, milestone.getId());
    }

    @Test
    public void testExist() {
        assertTrue(milestone.isExist());
    }
    }
