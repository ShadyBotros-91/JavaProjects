package de.tuhh.diss.plotbot;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;

public class PenControl {

	/* attributes */

	private NXTRegulatedMotor mPen; // reference to NXTRegulatedMotor class
									// (Motor controlling the pen movement)

	/*
	 * Constructor :
	 * 
	 * Initializing motor controlling the pen and connecting it to its
	 * corresponding port.
	 */
	public PenControl() {
		mPen = new NXTRegulatedMotor(MotorPort.B);
	}

	/////////////////////////////////////////////////////////////////////////
	// methods (public interface)
	/////////////////////////////////////////////////////////////////////////

	/*
	 * movePenDown method :
	 * 
	 * used to move the pen down
	 * 
	 * @param (x)---> angles in degrees (obtained from calibration)
	 * 
	 */
	public void movePenDown(int x) {
		mPen.rotate(x);
	}

	/*
	 * movePenUp method :
	 * 
	 * used to move the pen up
	 * 
	 * @param (x)---> angles in degrees (obtained from calibration)
	 * 
	 */
	public void movePenUp(int x) {
		mPen.rotate(x); 
	}

}
