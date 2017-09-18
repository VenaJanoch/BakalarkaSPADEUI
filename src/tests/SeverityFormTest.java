package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import SPADEPAC.ObjectFactory;
import SPADEPAC.Severity;
import SPADEPAC.Severity;
import services.FillForms;

public class SeverityFormTest {

	private static ObjectFactory objF;
	Severity severity;
	LocalDate date;
	
	@Before
	public void setUp() throws Exception {
		objF = new ObjectFactory();
		int[] pole = { 1, 2 };
		date = LocalDate.now();
		severity = FillForms.fillSeverityType("1", "Severity", "Class", "Super", objF, true);
	}

	@Test
	public void testName() {
		assertEquals(severity.getName(), "Severity");
	}

	@Test
	public void testClass() {
		assertEquals(severity.getSeverityClass(), "Class");
	}
	
	@Test
	public void testSuper() {
		assertEquals(severity.getSeveritySuperClass(), "Super");
	}

}
