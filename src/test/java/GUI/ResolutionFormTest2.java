package GUI;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import SPADEPAC.ObjectFactory;
import SPADEPAC.Relation;
import services.FillForms;

public class ResolutionFormTest2 {

	private static ObjectFactory objF;
	Relation relation;
	LocalDate date;
	
	@Before
	public void setUp() throws Exception {
		objF = new ObjectFactory();
		int[] pole = { 1, 2 };
		date = LocalDate.now();
		relation = FillForms.fillRelationType("2", "", "", "", objF, true);
	}

	@Test
	public void testName() {
		assertEquals(relation.getName(), null);
	}

	@Test
	public void testClass() {
		assertEquals(relation.getRelationClass(), null);
	}
	
	@Test
	public void testSuper() {
		assertEquals(relation.getRelationSuperClass(), null);
	}

}
