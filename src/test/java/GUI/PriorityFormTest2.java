package GUI;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import SPADEPAC.Milestone;
import SPADEPAC.ObjectFactory;
import SPADEPAC.Priority;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.FillForms;

public class PriorityFormTest2 {

	private static ObjectFactory objF;
	Priority priority;
	LocalDate date;
	
	@Before
	public void setUp() throws Exception {
		objF = new ObjectFactory();
		int[] pole = { 1, 2 };
		date = LocalDate.now();
		priority = FillForms.fillPriorityType("1", "Priority", "Class", "Super", objF, true);
	}

	@Test
	public void testName() {
		assertEquals(priority.getName(), "Priority");
	}

	@Test
	public void testClass() {
		assertEquals(priority.getPriorityClass(), "Class");
	}
	
	@Test
	public void testSuper() {
		assertEquals(priority.getPrioritySuperClass(), "Super");
	}

}
