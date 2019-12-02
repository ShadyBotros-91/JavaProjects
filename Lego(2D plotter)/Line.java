package de.tuhh.diss.plotbot;

public class Line {

	/* attributes */

	private Coordinate start; // reference to Coordinate class(starting point of
								// line)
	private Coordinate end; // reference to Coordinate class(Ending point of
							// line)

	/*
	 * Constructor :
	 * 
	 * Initializing Line with starting point and ending point
	 */
	public Line(Coordinate s, Coordinate e) {
		start = s;
		end = e;

	}
	/////////////////////////////////////////////////////////////////////////
	// methods (public interface)
	/////////////////////////////////////////////////////////////////////////

	/*
	 * getStart method :
	 * 
	 * used to get the starting point of the line
	 * 
	 * @return (start)---> Starting point.
	 */
	public Coordinate getStart() {
		return start;
	}

	/*
	 * getEnd method :
	 * 
	 * used to get the ending point of the line
	 * 
	 * @return (end)---> Ending point.
	 */
	public Coordinate getEnd() {
		return end;
	}
}
