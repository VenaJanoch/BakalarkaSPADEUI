package modelControllerEditTests;

import SPADEPAC.Person;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentLists;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class RoleValueTest {

        Person person;
        SegmentLists lists;
        @Before
        public void setUp() throws Exception {

            WarmUp warmUp = new WarmUp();
            lists = warmUp.getLists();
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

            dataModel.getSaveDataModel().createNewPerson(2);
            dataModel.getEditDataModel().editDataInPerson("Test", name, indicators, indicators, indicators, 3, 1, false, 2);
            dataModel.getEditDataModel().editDataInPerson("Test", name, indicators, indicators, indicators, 3, 1,false, 2);
            person = dataModel.getPerson(2);
        }

    @Test
    public void testAlias() {
        assertEquals("Test", person.getAlias() );
    }

    @Test
    public void testName() {
        assertEquals("", person.getName().get(0) );
        assertEquals("Test2", person.getName().get(1) );
        assertSame(2, person.getName().size());
    }

    @Test
    public void testIndicatorName() {
        assertSame(1, person.getNameIndicator().get(0) );
        assertSame(0, person.getNameIndicator().get(1) );
        assertSame(2, person.getNameIndicator().size());
    }


    @Test
    public void testIndicatorConfiguration() {
        assertSame(1, person.getType().get(0) );
        assertSame(0, person.getTypeIndicator().get(1) );
    }

    @Test
    public void testId() {
        assertSame(2, person.getId());
    }

    @Test
    public void testExist() {
        assertFalse(person.isExist());
    }

}