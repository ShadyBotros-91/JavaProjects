package de.tuhh.diss.plotbot;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;

public class MotorControl {
	/* attributes */
	private NXTRegulatedMotor mWheel;// a reference to NXTRegulatedMotor class
										// (Wheel's motor)
	private NXTRegulatedMotor mArm;// a reference to NXTRegulatedMotor class
									// (Arm's motor)

	/*
	 * Constructor :
	 * 
	 * Initializing two different motors and connecting them to their
	 * corresponding ports
	 */

	public MotorControl() {
		mWheel = new NXTRegulatedMotor(MotorPort.C);
		mArm = new NXTRegulatedMotor(MotorPort.A);
	}

	/////////////////////////////////////////////////////////////////////////
	// methods (public interface)
	/////////////////////////////////////////////////////////////////////////

	/*
	 * moveInDirectionY method :
	 * 
	 * used to rotate the wheels' motor in the forward and backward positions
	 * (in the vertical direction) and resetting the tachometer count after
	 * every position.
	 * 
	 * @param (aY) ---> degrees to be rotated (positive or negative)
	 */
	public void moveInDirectionY(int aY) {
		mWheel.rotate(aY);
		mWheel.resetTachoCount();
	}

	/*
	 * moveInDirectionX method :
	 * 
	 * used to control the movement of the swivel arm (in a horizontal
	 * direction) depending on the direction of the line to be drawn whether it
	 * is to the right or to the left.
	 * 
	 * @param (aX , aY ,armOffset) ---> degrees to be rotated in horizontal and
	 * vertical directions (positive or negative) and a compensation for the
	 * gears' clearance.
	 */

	public void moveInDirectionX(float aX, float aY, int armOffset) {

		mArm.setSpeed(360);

		if (aX > 0) {

			mWheel.setSpeed(((aY / aX) * 360));// varying the motor speed
			while (mWheel.getTachoCount() < (aY) && mArm.getTachoCount() > (-(aX + armOffset))) {
				mArm.backward();
				if (mArm.getTachoCount() <= (-armOffset)) {
					mWheel.forward();

				}
			}
			mArm.stop();
			mWheel.stop();
		}

		else if (aX < 0) {

			mWheel.setSpeed(((-aY / aX) * 360));// varying the motor speed
			while (mWheel.getTachoCount() > (-(aY)) && mArm.getTachoCount() < (-aX + armOffset)) {
				mArm.forward();
				if (mArm.getTachoCount() >= armOffset) {
					mWheel.backward();
				}
			}
			mArm.stop();
			mWheel.stop();
		}

		mWheel.setSpeed(360);
		mWheel.resetTachoCount();
		mArm.resetTachoCount();
	}

	/*
	 * moveInXY method :
	 * 
	 * used to control the movement of the wheels and swivel arm (diagonally)
	 * depending on the direction of the line to be drawn whether it is to the 
	 * upperleft , upperright , lowerleft or lowerright.
	 * 
	 * @param (x , y ,armOffset) ---> degrees to be rotated in horizontal and
	 * vertical directions (positive or negative) and a compensation for the
	 * gears' clearance.
	 */
	public void moveInXY(float aX, float aY, int armOffset) {

		mArm.setSpeed(360);

		if (aX < 0 && aY > 0) {

			mWheel.setSpeed(((-aY / aX) * 360));
			while (mWheel.getTachoCount() < aY && mArm.getTachoCount() < (-aX + armOffset)) {
				mArm.forward();

				if (mArm.getTachoCount() >= armOffset) {
					mWheel.forward();
				}
			}
			mArm.stop();
			mWheel.stop();
		}

		else if (aX > 0 && aY > 0) {

			mWheel.setSpeed(((aY / aX) * 360));
			while (mWheel.getTachoCount() < aY && mArm.getTachoCount() > (-(aX + armOffset))) {
				mArm.backward();

				if (mArm.getTachoCount() <= (-armOffset)) {
					mWheel.forward();
				}
			}
			mArm.stop();
			mWheel.stop();
		}

		else if (aX > 0 && aY < 0) {

			mWheel.setSpeed(((-aY / aX) * 360));
			while (mWheel.getTachoCount() > aY && mArm.getTachoCount() > (-(aX + armOffset))) {
				mArm.backward();

				if (mArm.getTachoCount() <= (-armOffset)) {
					mWheel.backward();
				}
			}
			mArm.stop();
			mWheel.stop();
		}

		else if (aX < 0 && aY < 0) {

			mWheel.setSpeed(((aY / aX) * 360));
			while (mWheel.getTachoCount() > aY && mArm.getTachoCount() < (-aX + armOffset)) {
				mArm.forward();

				if (mArm.getTachoCount() >= armOffset) {
					mWheel.backward();
				}
			}
			mArm.stop();
			mWheel.stop();
		}

		mWheel.resetTachoCount();
		mArm.resetTachoCount();
		mWheel.setSpeed(360);
	}

}
