package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import SPADEPAC.ObjectFactory;
import SPADEPAC.Priority;
import SPADEPAC.Role;
import services.FillForms;

public class RoleFormTest {

	private static ObjectFactory objF;
	Role role;
	LocalDate date;
	
	@Before
	public void setUp() throws Exception {
		objF = new ObjectFactory();
		int[] pole = { 1, 2 };
		date = LocalDate.now();
		role = FillForms.fillRole("1", "Desc", "Role", 2, objF, true);
	}

	@Test
	public void testName() {
		assertEquals(role.getName(), "Role");
	}

	@Test
	public void testDesc() {
		assertEquals(role.getDescription(), "Desc");
	}
	
	@Test
	public void testType() {
		assertEquals(role.getType().intValue(), 1);
	}

}
