package modelControllerEditTests;

import SPADEPAC.VCSTag;
import controllers.formControllers.FormDataController;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;
import tables.VCSTagTable;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

public class VCSTagValuesTest {

    VCSTag vcsTag;
    SegmentLists lists;

    @Before
    public void setUp() throws Exception {

        WarmUp warmUp = new WarmUp();
        lists = warmUp.getLists();
        FormDataController formDataController = warmUp.getFormDataController();
        formDataController.saveDataFromVCSTagForm(null, true);
        ArrayList<String> name = new ArrayList<>();
        name.add("");
        name.add("Test2");
        ArrayList<Integer> indicators = new ArrayList<>();
        indicators.add(1);
        indicators.add(0);

        warmUp.getEditFormController().editDataFromVCSTag("Test", name, name, indicators, indicators, new VCSTagTable("Test", false, 0), false, 0);
        warmUp.getEditFormController().editDataFromVCSTag("Test", name, name, indicators, indicators, new VCSTagTable("Test", false, 0), false, 0);

        vcsTag = warmUp.getDataModel().getVCSTag(0);
    }

    @Test
    public void testAlias() {
        assertEquals("Test", vcsTag.getAlias());
    }

    @Test
    public void testName() {
        assertEquals("", vcsTag.getName().get(0));
        assertEquals("Test2", vcsTag.getName().get(1));
        assertSame(2, vcsTag.getName().size());
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, vcsTag.getNameIndicator().get(0));
        assertSame(0, vcsTag.getNameIndicator().get(1));
        assertSame(2, vcsTag.getNameIndicator().size());
    }

    @Test
    public void testDescription() {
        assertEquals("", vcsTag.getDescription().get(0));
        assertEquals("Test2", vcsTag.getDescription().get(1));
        assertSame(2, vcsTag.getDescription().size());
    }

    @Test
    public void testIndicatorDescription() {
        assertSame(1, vcsTag.getDescriptionIndicator().get(0));
        assertSame(0, vcsTag.getDescriptionIndicator().get(1));
        assertSame(2, vcsTag.getDescriptionIndicator().size());
    }

    @Test
    public void testId() {
        assertSame(0, vcsTag.getId());
    }

    @Test
    public void testExist() {
        assertFalse(vcsTag.isExist());
    }
}
