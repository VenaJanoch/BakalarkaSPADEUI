package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import SPADEPAC.Artifact;
import SPADEPAC.ObjectFactory;
import SPADEPAC.Phase;
import services.FillForms;

public class ArtifactFormTest {

	private static ObjectFactory objF;
	Artifact artifact;
	LocalDate date;
	@Before
	public void setUp() throws Exception {
		objF = new ObjectFactory();
		artifact = (Artifact) objF.createArtifact();
		int[] pole = {1,2};		
		date = LocalDate.now();
		FillForms.fillArtifact(artifact, pole, "Desc", "Artifact", date, "Type", 4, 250, 350, 5, false, false, objF);
		
	}

	@Test
	public void testDes() {
		assertEquals(artifact.getDescriptoin(), "Desc");
	}
	
	@Test
	public void testName() {
		assertEquals(artifact.getName(), "Artifact");
	}
	
	@Test
	public void testCoorX() {
		assertEquals(artifact.getCoordinates().getXCoordinate().toString(), "250");
	}
	
	@Test
	public void testCoorY() {
		assertEquals(artifact.getCoordinates().getYCoordinate().toString(), "350");
	}
	
	
	
	@Test
	public void testRoleIndex() {
		assertEquals(artifact.getAuthorIndex().toString(), "3");
	}
	
	
	@Test
	public void testDate() {
		assertEquals(artifact.getCreated().toString(), date.toString()+"T00:00:00.000+02:00");
	}


	
}
