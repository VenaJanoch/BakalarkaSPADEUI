package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import SPADEPAC.ObjectFactory;
import SPADEPAC.RoleType;
import SPADEPAC.RoleType;
import services.FillForms;

public class RoleTypeFormTest {

	private static ObjectFactory objF;
	RoleType role;
	LocalDate date;
	
	@Before
	public void setUp() throws Exception {
		objF = new ObjectFactory();
		int[] pole = { 1, 2 };
		date = LocalDate.now();
		role = FillForms.fillRoleType("1", "RoleType", "Class", "Super", objF, true);
	}

	@Test
	public void testName() {
		assertEquals(role.getName(), "RoleType");
	}

	@Test
	public void testClass() {
		assertEquals(role.getRoleClass(), "Class");
	}
	
	@Test
	public void testSuper() {
		assertEquals(role.getRoleSuperClass(), "Super");
	}

}
