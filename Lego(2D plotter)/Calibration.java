package de.tuhh.diss.plotbot;

import lejos.nxt.Motor;
import lejos.nxt.Sound;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.TouchSensor;
import lejos.nxt.SensorPort;

public class Calibration {

	/* attributes */
	private TouchSensor t_Arm; // a reference to touch sensor class
	private TouchSensor t_Pen; // a reference to touch sensor class
	private LightSensor ls;// a reference to light sensor class
	private int maxArmAngle;// maximum angle to be rotated by the swivel arm
	private int maxPenAngle; // maximum angle to be rotated by the pen's motor
	private int minLightValue; // minimum light value to be detected by the
								// light sensor

	/*
	 * Constructor :
	 * 
	 * Instantiating 2 touch sensors and one light sensor
	 * 
	 */

	public Calibration() {
		t_Arm = new TouchSensor(SensorPort.S1);
		t_Pen = new TouchSensor(SensorPort.S2);
		ls = new LightSensor(SensorPort.S3, false);

	}
	/////////////////////////////////////////////////////////////////////////
	// methods (public interface)
	/////////////////////////////////////////////////////////////////////////

	/*
	 * caliArm method :
	 * 
	 * used to calibrate the swivel arm for the maximum rotating angle
	 */

	public void caliArm() {
		while (!t_Arm.isPressed()) {
			Motor.A.backward();
			maxArmAngle = Motor.A.getTachoCount();
		}
		Motor.A.stop();
		Motor.A.rotateTo(0);
		Motor.A.resetTachoCount();
	}

	/*
	 * caliPen method :
	 * 
	 * used to calibrate the pen's motor for the maximum rotating angle
	 */

	public void caliPen() {
		while (!t_Pen.isPressed()) {
			Motor.B.forward();
			maxPenAngle = Motor.B.getTachoCount();
		}
		Motor.B.stop();
		Motor.B.resetTachoCount();
	}

	/*
	 * caliPos method :
	 * 
	 * used to calibrate the robot for the starting position at the origin
	 * according to the minimum light value passed from getThresholdValue()
	 * method.
	 */
	public void caliPos() {
		if (!ls.isFloodlightOn()) {
			ls.setFloodlight(true);
		}
		while (ls.readValue() > minLightValue + 2) {
			Motor.C.backward();
		}
		Motor.C.resetTachoCount();
		Motor.C.stop();
	}

	/*
	 * getThresholdValue method :
	 * 
	 * used to obtain the minimum light value (at the black bar) when pressing
	 * escape as the robots have different threshold values.
	 */
	public void setThresholdValue() {
		if (!ls.isFloodlightOn()) {
			ls.setFloodlight(true);
		}
		LCD.drawString("Press ESC", 0, 0);

		while (!Button.ESCAPE.isDown()) {
			Motor.C.backward();
			minLightValue = ls.readValue();
			Sound.twoBeeps();
		}
		Motor.C.rotate(720);
		Motor.C.resetTachoCount();

	}

	/*
	 * getMaxArmAngle method :
	 * 
	 * used to get the maximum angle to be rotated by the swivel arm
	 * 
	 * @return (maxArmAngle)
	 */
	public int getMaxArmAngle() {
		return maxArmAngle;
	}

	/*
	 * getMaxPenAngle method :
	 * 
	 * used to get the maximum angle to be rotated by the pen's motor
	 * 
	 * @return (maxPenAngle)
	 */
	public int getMaxPenAngle() {
		return maxPenAngle;
	}
}
