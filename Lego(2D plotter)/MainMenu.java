package de.tuhh.diss.plotbot;

import de.tuhh.diss.plotbot.shape.*;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.util.TextMenu;

public class MainMenu {

	/* attributes */

	// User interface
	private static final String TITLE = "Choose Shape";
	private static final String[] ITEMS = { "Calibration", "Rectangle ", "Ship", "Exit" };

	private TextMenu menu; // a reference to TextMenu class
	private int width, height; // width and height of shapes to be drawn
	private Rectangle rect; // a reference to Rectangle class
	private Ship ship; // a reference to Ship class
	private PlotbotControl pc; // a reference to PlotbotControl class
	private int selection = -1; // selection index

	/*
	 * Constructor :
	 * 
	 * Initializing the MainMenu with two instances of TextMenu and
	 * PlobotControl classes
	 */
	public MainMenu() {
		menu = new TextMenu(ITEMS, 1, TITLE);
		pc = new PlotbotControl();
	}

	/////////////////////////////////////////////////////////////////////////
	// methods (public interface)
	/////////////////////////////////////////////////////////////////////////

	/*
	 * start method :
	 * 
	 * used to select different options from the main menu.
	 * 
	 */
	public void start() {
		do {
			selection = menu.select();
			if (Button.ENTER.isDown()) {
				Button.ENTER.waitForPressAndRelease();
				switch (selection) {
				case 0: {
					pc.startCalibration();
					break;
				}
				case 1: {
					LCD.clear();
					width = setWidth();
					Button.ENTER.waitForPressAndRelease();
					height = setHeight();
					rect = new Rectangle(width, height);
					rect.plot(pc);
					break;
				}
				case 2: {
					LCD.clear();
					width = setWidth();
					ship = new Ship(width);
					ship.plot(pc);
					break;
				}
				case 3: {
					LCD.clear();
					LCD.drawString("Press ESC", 0, 0);
					Button.ESCAPE.waitForPress();
					break;
				}
				}
			}
		} while (!Button.ESCAPE.isDown());

	}

	/////////////////////////////////////////////////////////////////////////
	// methods (internal)
	/////////////////////////////////////////////////////////////////////////

	/*
	 * width method :
	 * 
	 * used to input the required width of the shape to be drawn
	 * 
	 * @return (count) ---> returning the width in mm
	 */
	private int setWidth() {
		int count = 0;
		LCD.drawString("width in mm", 2, 2);
		LCD.drawString(" < DECREASE ", 3, 3);
		LCD.drawString(" INCREASE > ", 4, 4);

		do {
			LCD.drawString("WIDTH", 1, 5);
			LCD.drawInt(count, 3, 7);

			if (Button.LEFT.isDown() && count != 0) {
				Button.LEFT.waitForPressAndRelease();
				count-=10;
			} else if (Button.RIGHT.isDown()) {
				Button.RIGHT.waitForPressAndRelease();
				count+=10;
			} else if (Button.LEFT.isDown() && count == 0) {
				LCD.drawString("use >", 5, 5);
			}

		} while (!Button.ENTER.isDown());

		return count;
	}

	/*
	 * height method :
	 * 
	 * used to input the required height of the rectangle
	 * 
	 * @return (count) ---> returning the height in mm
	 */
	private  int setHeight() {
		int count = 0;
		LCD.drawString("height in mm", 2, 2);
		LCD.drawString(" < DECREASE ", 3, 3);
		LCD.drawString(" INCREASE > ", 4, 4);

		do {
			LCD.drawString("height", 1, 5);
			LCD.drawInt(count, 3, 7);

			if (Button.LEFT.isDown() && count != 0) {
				Button.LEFT.waitForPressAndRelease();
				count-=10;
			} else if (Button.RIGHT.isDown()) {
				Button.RIGHT.waitForPressAndRelease();
				count+=10;
			} else if (Button.LEFT.isDown() && count == 0) {
				LCD.drawString("Press >", 5, 5);
			}

		} while (!Button.ENTER.isDown());

		return count;
	}

}