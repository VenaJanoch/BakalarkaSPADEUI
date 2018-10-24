package GUI;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import SPADEPAC.ObjectFactory;
import SPADEPAC.Type;
import SPADEPAC.Type;
import services.FillForms;

public class TypeFormTest2 {

	private static ObjectFactory objF;
	Type type;
	LocalDate date;
	
	@Before
	public void setUp() throws Exception {
		objF = new ObjectFactory();
		int[] pole = { 1, 2 };
		date = LocalDate.now();
		type = FillForms.fillType("1", "", "", "", objF, true);
	}

	@Test
	public void testName() {
		assertEquals(type.getName(), null);
	}

	@Test
	public void testClass() {
		assertEquals(type.getTypeClass(), null);
	}
	
	@Test
	public void testSuper() {
		assertEquals(type.getTypeSuperClass(), null);
	}

}
