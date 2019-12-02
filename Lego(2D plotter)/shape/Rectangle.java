package de.tuhh.diss.plotbot.shape;

import de.tuhh.diss.plotbot.shape.Plottable;
import de.tuhh.diss.plotbot.Coordinate;
import de.tuhh.diss.plotbot.Line;
import de.tuhh.diss.plotbot.PlotbotControl;

public class Rectangle implements Plottable {
	/* attributes */

	private int width; // width of the rectangle
	private int height; // height of rectangle

	/*
	 * Constructor :
	 * 
	 * Initializing rectangle with its dimensions.
	 */

	public Rectangle(int x, int y) {
		width = x;
		height = y;
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
		Coordinate c1 = pc.giveCoordinate(0, Ship.MAX_Y);
		Coordinate c2 = pc.giveCoordinate(0, Ship.MAX_Y - height);
		Coordinate c3 = pc.giveCoordinate(width, Ship.MAX_Y - height);
		Coordinate c4 = pc.giveCoordinate(width, Ship.MAX_Y);
		Line lInit = pc.giveLineCoordinates(cInit, c1);
		Line l1 = pc.giveLineCoordinates(c1, c2);
		Line l2 = pc.giveLineCoordinates(c2, c3);
		Line l3 = pc.giveLineCoordinates(c3, c4);
		Line l4 = pc.giveLineCoordinates(c4, c1);
		Line l5 = pc.giveLineCoordinates(c1, cInit);

		pc.setOffset(900);
		pc.PlotLine(lInit);
		pc.penDown();
		pc.PlotLine(l1);
		pc.PlotLine(l2);
		pc.PlotLine(l3);
		pc.PlotLine(l4);
		pc.penUp();
		pc.PlotLine(l5);
	}

}