package de.tuhh.diss.plotbot;

public class PlotbotControl {

	/* attributes */

	private Line line; // reference to Line class
	private MotorControl motor; // reference to the motor control class
	private CoordinateTransform cT; // reference to Coordinate transform class
	private PenControl pC; // reference to Pen control class
	private Calibration cal; // reference to calibration class
	private Coordinate c; // reference to Coordinate class
	private int maxWidth; // maximum width handled by the robot arm
	private int armOffset = 0; // offset used as a compensation for gears'
								// clearance.

	/*
	 * Constructor :
	 * 
	 * Initializing PlotbotControl by creating instances of motor control ,
	 * coordinate transform , pen control and calibration classes. While setting
	 * the references of both Line and Coordinate to null.
	 */

	public PlotbotControl() {
		cT = new CoordinateTransform();
		motor = new MotorControl();
		pC = new PenControl();
		cal = new Calibration();
		line = null;
		c = null;
	}

	/////////////////////////////////////////////////////////////////////////
	// methods (public interface)
	/////////////////////////////////////////////////////////////////////////

	/*
	 * startCalibration method :
	 * 
	 * used to start calibration of the robot to obtain maximum swivel arm and
	 * pen's motors angles , getting black bar threshold value and hence
	 * calibrating the position at the origin.
	 */
	public void startCalibration() {
		cal.caliPen();
		cal.setThresholdValue();
		cal.caliArm();
		cal.caliPos();
	}

	/*
	 * setOffset method :
	 * 
	 * used to set the offset needed to compensate for the clearance between the
	 * gears as the arm has to be moved slightly further when the direction has
	 * changed
	 * 
	 *
	 * @param (x)--->offset in degrees
	 */
	public void setOffset(int x) {
		armOffset = x;
	}

	/*
	 * getMaxWidth method :
	 * 
	 * used to obtain the maximum width handled by the robot arm
	 * 
	 * @return maxWidth
	 */
	public int getMaxWidth() {
		cT.angleToXy(cal.getMaxArmAngle() - 900, 0);
		maxWidth = cT.getTCoordinateX();
		return maxWidth;
	}

	/*
	 * giveCoordinate method :
	 * 
	 * used to set the coordinates of a specific point
	 * 
	 * @param (x , y) ---> x and y coordinates of the point
	 * 
	 * @return c (Coordinate)
	 */
	public Coordinate giveCoordinate(int x, int y) {
		c = new Coordinate(x, y);
		return c;
	}

	/*
	 * giveLineCoordinates method :
	 * 
	 * used to set the starting and ending points of a line
	 * 
	 * @param( s, e) --->starting and ending points
	 * 
	 * @return Line
	 */
	public Line giveLineCoordinates(Coordinate s, Coordinate e) {
		line = new Line(s, e);
		return line;
	}

	/*
	 * penDown method :
	 * 
	 * used to get the pen down to the drawing board.
	 * 
	 */
	public void penDown() {
		pC.movePenDown(-cal.getMaxPenAngle());
	}

	/*
	 * penUp method :
	 * 
	 * used to get the pen up to the touch sensor.
	 * 
	 */
	public void penUp() {
		pC.movePenUp(cal.getMaxPenAngle());
	}

	/*
	 * PlotLine method :
	 * 
	 * used to draw the line depending on whether it is horizontal , vertical or
	 * diagonal.
	 * 
	 * @param (l) ---> line to be drawn
	 */
	public void PlotLine(Line l) {

		if (l.getStart().getCoordinateX() == l.getEnd().getCoordinateX()) {
			cT.xyToAngle(0, l.getEnd().getCoordinateY() - l.getStart().getCoordinateY());
			motor.moveInDirectionY(cT.getAngleY());
		}

		else if (l.getStart().getCoordinateY() == l.getEnd().getCoordinateY()) {
			int distance = 0;

			if (l.getEnd().getCoordinateX() > l.getStart().getCoordinateX()) {

				double rad_A = (float) (l.getEnd().getCoordinateX() - l.getStart().getCoordinateX())
						/ (float) CoordinateTransform.R_ARM;
				double d = CoordinateTransform.R_ARM * (1 - Math.cos(rad_A));
				distance = (int) d;
			} else if (l.getEnd().getCoordinateX() < l.getStart().getCoordinateX()) {
				double rad_A = (float) (l.getStart().getCoordinateX() - l.getEnd().getCoordinateX())
						/ (float) CoordinateTransform.R_ARM;
				double d = CoordinateTransform.R_ARM * (1 - Math.cos(rad_A));
				distance = (int) d;
			}

			cT.xyToAngle(l.getEnd().getCoordinateX() - l.getStart().getCoordinateX(), distance);
			motor.moveInDirectionX(cT.getAngleX(), cT.getAngleY(), armOffset);
		}

		else {
			cT.xyToAngle(l.getEnd().getCoordinateX() - l.getStart().getCoordinateX(),
					l.getEnd().getCoordinateY() - l.getStart().getCoordinateY());
			motor.moveInXY(cT.getAngleX(), cT.getAngleY(), armOffset);
		}

	}
}
