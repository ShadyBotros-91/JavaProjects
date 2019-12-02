package de.tuhh.diss.plotbot;

public class Coordinate {

	/* attributes */

	private int co_X;// X-Coordinate
	private int co_Y;// Y-Coordinate

	/*
	 * Constructor :
	 * 
	 * Initializing class with the X and Y coordinates
	 * 
	 */
	public Coordinate(int x, int y) {
		co_X = x;
		co_Y = y;
	}
	////////////////////////////////////////////////////////////////////////
	// methods (public interface)
	/////////////////////////////////////////////////////////////////////////

	/*
	 * getCoordinateX method :
	 * 
	 * used to get the X-Coordinate of the point
	 * 
	 * @return (co_X)
	 */
	public int getCoordinateX() {
		return co_X;
	}

	/*
	 * getCoordinateY method :
	 * 
	 * used to get the Y-Coordinate of the point
	 * 
	 * @return (co_Y)
	 */
	public int getCoordinateY() {
		return co_Y;
	}
}
