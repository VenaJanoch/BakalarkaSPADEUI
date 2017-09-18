package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import SPADEPAC.ObjectFactory;
import SPADEPAC.Type;
import SPADEPAC.Type;
import services.FillForms;

public class TypeFormTest {

	private static ObjectFactory objF;
	Type type;
	LocalDate date;
	
	@Before
	public void setUp() throws Exception {
		objF = new ObjectFactory();
		int[] pole = { 1, 2 };
		date = LocalDate.now();
		type = FillForms.fillType("1", "Type", "Class", "Super", objF, true);
	}

	@Test
	public void testName() {
		assertEquals(type.getName(), "Type");
	}

	@Test
	public void testClass() {
		assertEquals(type.getTypeClass(), "Class");
	}
	
	@Test
	public void testSuper() {
		assertEquals(type.getTypeSuperClass(), "Super");
	}

}
