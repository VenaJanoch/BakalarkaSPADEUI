package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import SPADEPAC.ObjectFactory;
import SPADEPAC.Severity;
import SPADEPAC.Severity;
import services.FillForms;

public class SeverityFormTest2 {

	private static ObjectFactory objF;
	Severity severity;
	LocalDate date;
	
	@Before
	public void setUp() throws Exception {
		objF = new ObjectFactory();
		int[] pole = { 1, 2 };
		date = LocalDate.now();
		severity = FillForms.fillSeverityType("1", "", "", "", objF, true);
	}

	@Test
	public void testName() {
		assertEquals(severity.getName(), null);
	}

	@Test
	public void testClass() {
		assertEquals(severity.getSeverityClass(), null);
	}
	
	@Test
	public void testSuper() {
		assertEquals(severity.getSeveritySuperClass(), null);
	}

}
