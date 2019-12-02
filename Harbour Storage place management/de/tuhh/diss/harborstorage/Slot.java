package de.tuhh.diss.harborstorage;

import de.tuhh.diss.harborstorage.sim.StoragePlace;

/* attributes */

public class Slot implements StoragePlace {
	int number; // the number of the storage place (slot)
	int width; // the maximum width to be stored in the slot
	int height; // the maximum height to be stored in the slot
	int depth; // the maximum depth to be stored in the slot
	int loadCapacity; // the maximum weight to be stored in the slot
	int positionX; // the x-position of the storage place (slot)
	int positionY; // the y-position of the storage place (slot)
	Packet containedPacket; // the packet stored in the specific slot

	/*
	 * Constructor :
	 * 
	 * Dimensions and load capacities of the available storage places are passed as
	 * parameters when creating new object of this class.
	 * 
	 * @param (number, ,x-position , y-position ,width , height, depth, load
	 * capacity)
	 */
	public Slot(int num, int x, int y, int w, int h, int d, int load) {
		number = num;
		width = w;
		height = h;
		depth = d;
		loadCapacity = load;
		positionX = x;
		positionY = y;
		containedPacket = null;
	}

/////////////////////////////////////////////////////////////////////////
//methods (public interface)
/////////////////////////////////////////////////////////////////////////

	/*
	 * getNumber method :
	 * 
	 * gets the number of the slot.
	 * 
	 * @return number
	 */
	public int getNumber() {
		return number;
	}

	/*
	 * getWidth method :
	 * 
	 * gets the maximum width to be stored in the slot.
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/*
	 * getHeight method :
	 * 
	 * gets the maximum height to be stored in the slot
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/*
	 * getDepth method :
	 * 
	 * gets the maximum depth to be stored in the slot
	 * 
	 * @return depth
	 */
	public int getDepth() {
		return depth;
	}

	/*
	 * getLoadCapacity method :
	 * 
	 * gets the maximum weight to be stored in the slot
	 * 
	 * @return load capacity
	 */
	public int getLoadCapacity() {
		return loadCapacity;
	}

	/*
	 * getPositionX method :
	 * 
	 * gets the x-position of the slot.
	 * 
	 * @return positionX
	 */

	public int getPositionX() {
		return positionX;
	}

	/*
	 * getPositionY method :
	 * 
	 * gets the y-position of the slot.
	 * 
	 * @return positionY
	 */
	public int getPositionY() {
		return positionY;
	}

	/*
	 * getContainedPacket method :
	 * 
	 * gets the packet stored in the slot.
	 * 
	 * @return containedPacket
	 */

	public Packet getContainedPacket() {
		return containedPacket;
	}

	/*
	 * setContainedPacket method :
	 * 
	 * sets the packet to the suitable slot.
	 * 
	 * @param packet
	 */

	public void setContainedPacket(Packet packet) {
		containedPacket = packet;
	}

}