package modelControllerCopyTests;

import SPADEPAC.Artifact;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.time.LocalDate;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

public class ArtefaktValueCopyTest {

    Artifact artifact;
    SegmentLists lists;
    LocalDate date;

    @Before
    public void setUp() throws Exception {

        this.lists = new SegmentLists();

        date = LocalDate.of(2018, 10, 10);

        WarmUp warmUp = new WarmUp();
        DataModel dataModel = warmUp.getDataModel();
        ArrayList<String> name = new ArrayList<>();
        name.add("");
        name.add("Test2");
        ArrayList<Integer> indicators = new ArrayList<>();
        indicators.add(1);
        indicators.add(0);
        ArrayList<ArrayList<Integer>> unit = new ArrayList<>();
        unit.add(indicators);
        unit.add(indicators);

        ArrayList<LocalDate> dates = new ArrayList<>();
        dates.add(date);
        dataModel.getSaveDataModel().createNewArtifact(1);
        dataModel.getEditDataModel().editDataInArtifact("Test", name, indicators, name, indicators, dates, indicators, false,
                indicators, indicators, indicators, indicators, 3, 2, 1);
        dataModel.getEditDataModel().editDataInArtifact("Test", name, indicators, name, indicators, dates, indicators, false,
                indicators, indicators, indicators, indicators, 3, 2, 1);
        dataModel.getSaveDataModel().createNewArtifact(3);
        dataModel.getDataManipulator().copyDataFromArtifact(1, 3, 43, 45);
        artifact = dataModel.getArtifact(3);
    }

    @Test
    public void testAlias() {
        assertEquals("3", artifact.getAlias());
    }

    @Test
    public void testName() {
        assertEquals("", artifact.getName().get(0));
        assertEquals("Test2", artifact.getName().get(1));
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, artifact.getNameIndicator().get(0));
        assertSame(0, artifact.getNameIndicator().get(1));
    }

    @Test
    public void testDescription() {
        assertEquals("", artifact.getDescription().get(0));
        assertEquals("Test2", artifact.getDescription().get(1));
    }

    @Test
    public void testDate() {
        assertEquals(date.toString() + "T00:00:00.000+02:00", artifact.getCreated().get(0).toString());
        assertSame(1, artifact.getCreated().size());
    }

    @Test
    public void testDateIndicator() {
        assertSame(1, artifact.getCreatedIndicator().get(0));
        assertSame(0, artifact.getCreatedIndicator().get(1));
    }

    @Test
    public void testIndicatorDescription() {
        assertSame(1, artifact.getDescriptionIndicator().get(0));
        assertSame(0, artifact.getDescriptionIndicator().get(1));
    }

    @Test
    public void testCoords() {
        assertSame(43, artifact.getCoordinates().getXCoordinate());
        assertSame(45, artifact.getCoordinates().getYCoordinate());
    }

    @Test
    public void testType() {
        assertSame(1, artifact.getMimeTypeIndex().get(0));
        assertSame(0, artifact.getMimeTypeIndex().get(1));
        assertSame(1, artifact.getMimeTypeIndicator().get(0));
        assertSame(0, artifact.getMimeTypeIndicator().get(1));
    }

    @Test
    public void testId() {
        assertSame(3, artifact.getId());
    }

    @Test
    public void testExist() {
        assertFalse(artifact.isExist());
    }
}