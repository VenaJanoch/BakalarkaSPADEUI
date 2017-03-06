package Obsluha;

import Grafika.CanvasItem;

public class IdentificatorCreater {

	private static int linesID;
	private static int phaseID;

	public IdentificatorCreater() {

		linesID = -1;
		phaseID = -1;

	}

	public int createLineID() {

		linesID++;

		return linesID;

	}

	public int createPhaseID() {

		phaseID++;

		return phaseID;

	}

}
