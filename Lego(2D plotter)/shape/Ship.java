package de.tuhh.diss.plotbot.shape;

import de.tuhh.diss.plotbot.shape.Plottable;
import de.tuhh.diss.plotbot.Coordinate;
import de.tuhh.diss.plotbot.Line;
import de.tuhh.diss.plotbot.PlotbotControl;

public class Ship implements Plottable {

	/* attributes */

	private int width; // width of the ship
	public final static int MAX_Y = 255;// Upper limit of the drawing board

	/*
	 * Constructor :
	 * 
	 * Initializing ship with its width.
	 */
	public Ship(int x) {
		width = x;
	}

	/////////////////////////////////////////////////////////////////////////
	// methods (public interface)
	/////////////////////////////////////////////////////////////////////////

	/*
	 * plot method :
	 * 
	 * used to implement the drawing procedure.
	 * 
	 * @param (pc)---> a reference to PlotbotControl class
	 */
	public void plot(PlotbotControl pc) {

		Coordinate cInit = pc.giveCoordinate(0, 0);
		Coordinate c1 = pc.giveCoordinate(0, MAX_Y - (int) (0.7 * width));
		Coordinate c2 = pc.giveCoordinate(width, MAX_Y - (int) (0.7 * width));
		Coordinate c3 = pc.giveCoordinate((int) (0.8 * width), MAX_Y - width);
		Coordinate c4 = pc.giveCoordinate((int) (0.2 * width), MAX_Y - width);
		Coordinate c5 = pc.giveCoordinate((int) (0.2 * width), MAX_Y - (int) (0.7 * width));
		Coordinate c6 = pc.giveCoordinate((int) (0.4 * width), MAX_Y - (int) (0.3 * width));

		Coordinate c7 = pc.giveCoordinate((int) (0.4 * width), MAX_Y);
		Coordinate c8 = pc.giveCoordinate((int) (0.7 * width), MAX_Y);
		Coordinate c9 = pc.giveCoordinate((int) (0.7 * width), MAX_Y - (int) (0.2 * width));
		Coordinate c10 = pc.giveCoordinate((int) (0.4 * width), MAX_Y - (int) (0.2 * width));

		Coordinate c11 = pc.giveCoordinate((int) (0.4 * width), MAX_Y - (int) (0.7 * width));
		Coordinate c12 = pc.giveCoordinate((int) (0.8 * width), MAX_Y - (int) (0.7 * width));
		Coordinate c13 = pc.giveCoordinate((int) (0.4 * width), MAX_Y - (int) (0.2 * width));

		Line lInit = pc.giveLineCoordinates(cInit, c1);
		Line l1 = pc.giveLineCoordinates(c1, c2);
		Line l2 = pc.giveLineCoordinates(c2, c3);
		Line l3 = pc.giveLineCoordinates(c3, c4);
		Line l4 = pc.giveLineCoordinates(c4, c1);
		Line l5 = pc.giveLineCoordinates(c1, c5);
		Line l6 = pc.giveLineCoordinates(c5, c6);
		Line l7 = pc.giveLineCoordinates(c6, c7);
		Line l8 = pc.giveLineCoordinates(c7, c8);
		Line l9 = pc.giveLineCoordinates(c8, c9);
		Line l10 = pc.giveLineCoordinates(c9, c10);
		Line l11 = pc.giveLineCoordinates(c10, c11);
		Line l12 = pc.giveLineCoordinates(c11, c12);
		Line l13 = pc.giveLineCoordinates(c12, c13);
		Line l14 = pc.giveLineCoordinates(c13, cInit);

		pc.setOffset(900);

		pc.PlotLine(lInit);
		pc.penDown();

		pc.PlotLine(l1);
		pc.PlotLine(l2);

		pc.setOffset(0);

		pc.PlotLine(l3);
		pc.PlotLine(l4);

		pc.setOffset(900);
		pc.penUp();
		pc.PlotLine(l5);

		pc.setOffset(0);
		pc.penDown();
		pc.PlotLine(l6);
		pc.PlotLine(l7);
		pc.PlotLine(l8);
		pc.PlotLine(l9);

		pc.setOffset(900);
		pc.PlotLine(l10);
		pc.PlotLine(l11);

		pc.penUp();
		pc.PlotLine(l12);

		pc.penDown();
		pc.PlotLine(l13);
		pc.penUp();
		pc.setOffset(0);
		pc.PlotLine(l14);

	}
}