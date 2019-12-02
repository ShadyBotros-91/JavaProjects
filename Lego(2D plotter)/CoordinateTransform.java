package de.tuhh.diss.plotbot;

public class CoordinateTransform {

	/* attributes */

	private int x;// X-coordinate of a point to be drawn
	private int y; // Y-coordinate of a point to be drawn
	private int angleX; // translated swivel arm motor rotation angle
	private int angleY; // translated wheel motor rotation angle
	public final static int R_ARM = 80;// Distance from arm’s rotation center to
										// pen
	private final static int R_WHEEL = 28;// radius of the wheel
	private final static int GR_ARM = 84;// gear ratio of (motor : arm)
	private final static int GR_WHEEL = 5;// gear ratio of (motor : wheel)

	/*
	 * Constructor :
	 * 
	 * Initializing Coordinate transform at the origin
	 * 
	 */
	public CoordinateTransform() {
		x = 0;
		y = 0;
		angleX = 0;
		angleY = 0;
	}

	/////////////////////////////////////////////////////////////////////////
	// methods (public interface)
	/////////////////////////////////////////////////////////////////////////

	/*
	 * xyToangle method :
	 * 
	 * used to convert distance (in terms of certain coordinates) into angles
	 * according to the following relation : angle = (gear
	 * ratio*(distance/(2*pi*radius))*360 (in degrees)
	 * 
	 * @param (x , y) ---> X and Y coordinate of a point to be drawn
	 */
	public void xyToAngle(int x, int y) {
		angleX = (int) ((GR_ARM * x / (2 * Math.PI * R_ARM)) * 360);
		angleY = (int) ((GR_WHEEL * y / (2 * Math.PI * R_WHEEL)) * 360);
	}

	/*
	 * angleToxy method :
	 * 
	 * used to convert angles (in degrees) into XY coordinates according to the
	 * following relation : distance = ((angle * pi * radius )/(gear ratio *
	 * 180)) (in mm)
	 * 
	 * @param (xA , yA) ---> Angles of arms' and wheels' motors respectively
	 */
	public void angleToXy(int xA, int yA) {
		x = (int) (xA * Math.PI * R_ARM / (GR_ARM * 180));
		y = (int) (yA * Math.PI * R_WHEEL / (GR_WHEEL* 180));

	}

	/*
	 * getAngleX method :
	 * 
	 * used to get the converted angle to be rotated in X - direction
	 * 
	 * @return (angleX)
	 */
	public int getAngleX() {
		return angleX;
	}

	/*
	 * getAngleY method :
	 * 
	 * used to get the converted angle to be rotated in Y - direction
	 * 
	 * @return (angleY)
	 */
	public int getAngleY() {
		return angleY;
	}

	/*
	 * getCoordinateX method :
	 * 
	 * used to get the X-coordinate resulting from converting the angle
	 * 
	 * @return (x)
	 */
	public int getTCoordinateX() {
		return x;
	}

	/*
	 * getCoordinateY method :
	 * 
	 * used to get the Y-coordinate resulting from converting the angle
	 * 
	 * @return (y)
	 */
	public int getTCoordinateY() {
		return y;
	}
}
