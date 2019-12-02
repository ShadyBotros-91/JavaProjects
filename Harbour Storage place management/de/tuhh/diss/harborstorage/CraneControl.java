package de.tuhh.diss.harborstorage;

import de.tuhh.diss.harborstorage.sim.PhysicalCrane;
import de.tuhh.diss.harborstorage.sim.StorageElement;

public class CraneControl {

	/* attributes */

	PhysicalCrane cr; // reference to PhysicalCrane class
	StorageElement packet; // reference to storage element interface

	public CraneControl() {
		super();
	}

	/*
	 * Constructor :
	 * 
	 * @param cr ---> constructs a CraneControl object that uses the physical crane
	 * given as parameter.
	 */
	public CraneControl(PhysicalCrane cr) {
		this.cr = cr;
		packet = null;
	}

/////////////////////////////////////////////////////////////////////////
//methods (public interface)
/////////////////////////////////////////////////////////////////////////

	/*
	 * storePacket method :
	 * 
	 * controls the physical crane to move to the loading position, to pick up the
	 * packet, to drive to the chosen slot at (x, y) and to store the packet.
	 * 
	 * @param x --->x-position of the suitable slot.
	 * 
	 * @param y --->y-position of the suitable slot.
	 * 
	 * @param packet --->The packet to be stored
	 */
	public void storePacket(int x, int y, StorageElement packet) {
		cr.start();

		int x1 = cr.getLoadingPosX(); // x position where packet should be loaded
		int y1 = cr.getLoadingPosY(); // y position where packet should be loaded
		moveToX(x1);
		moveToY(y1);
		cr.loadElement(packet);
		moveToX(x);
		moveToY(y);
		cr.storeElement();

	}

	/*
	 * retrievePacket method :
	 * 
	 * controls the physical crane to load the packet from slot at (x, y) and unload
	 * it at the loading position
	 * 
	 * @param x ---> x-position of the stored packet.
	 * 
	 * @param y ---> y-position of the stored packet.
	 * 
	 * @return packet ---> the storage element to be retrieved from the particular
	 * Slot.
	 */
	public StorageElement retrievePacket(int x, int y) {
		cr.start();
		moveToX(x);
		moveToY(y);
		cr.retrieveElement();
		int x1 = cr.getLoadingPosX(); // x position where packet should be loaded
		int y1 = cr.getLoadingPosY(); // y position where packet should be loaded
		moveToX(x1);
		moveToY(y1);
		cr.unloadElement();
		return packet;
	}

	/*
	 * shutdown method:
	 * 
	 * switches off all motors of the crane before terminating the program.
	 * 
	 */
	public void shutdown() {
		cr.shutdown();

	}

/////////////////////////////////////////////////////////////////////////
//methods (internal)
/////////////////////////////////////////////////////////////////////////

	/*
	 * moveToX method:
	 * 
	 * moving the Crane in the Horizontal Direction.
	 * 
	 * @param x ---> x-position of the storage place.
	 */
	private void moveToX(int x) {
		if (x < cr.getPositionX()) {
			while (x < cr.getPositionX()) {
				cr.backward();
			}
			cr.stopX();
		} else if (x > cr.getPositionX()) {

			while (x > cr.getPositionX()) {
				cr.forward();
			}
			cr.stopX();
		} else if (cr.isStalledX() == true) {
			cr.stopX();
			if (x > cr.getLoadingPosX()) {
				cr.backward();
			} else {
				cr.forward();
			}

		}
	}

	/*
	 * moveToY method:
	 * 
	 * moving the Crane in the Horizontal Direction.
	 * 
	 * @param y ---> y-position of the storage place.
	 */

	private void moveToY(int y) {
		if (y < cr.getPositionY()) {
			while (y < cr.getPositionY()) {
				cr.down();

			}
			cr.stopY();

		} else if (y > cr.getPositionY()) {
			while (y > cr.getPositionY()) {
				cr.up();

			}
			cr.stopY();

		} else if (cr.isStalledY() == true) {
			cr.stopY();
			if (y > cr.getLoadingPosY()) {
				cr.down();
			} else {
				cr.up();
			}

		}
	}

}