package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import SPADEPAC.ObjectFactory;
import SPADEPAC.Status;
import SPADEPAC.Status;
import services.FillForms;

public class StatusFormTest2 {

	private static ObjectFactory objF;
	Status status;
	LocalDate date;
	
	@Before
	public void setUp() throws Exception {
		objF = new ObjectFactory();
		int[] pole = { 1, 2 };
		date = LocalDate.now();
		status = FillForms.fillStatusType("1", "Status", "Class", "Super", objF, true);
	}

	@Test
	public void testName() {
		assertEquals(status.getName(), "Status");
	}

	@Test
	public void testClass() {
		assertEquals(status.getStatusClass(), "Class");
	}
	
	@Test
	public void testSuper() {
		assertEquals(status.getStatusSuperClass(), "Super");
	}

}
