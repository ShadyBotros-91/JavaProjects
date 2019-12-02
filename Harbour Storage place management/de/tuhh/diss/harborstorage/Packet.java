package de.tuhh.diss.harborstorage;

import de.tuhh.diss.harborstorage.sim.StorageElement;

public class Packet implements StorageElement {

	/* attributes */

	private int id_num; // id of the packet to be stored
	private int width; // width of the packet to be stored
	private int height; // height of the packet to be stored
	private int depth; // depth of the packet to be stored
	private int weight; // weight of the packet to be stored
	private String description; // description of the packet to be stored
	private Slot location; // location of the stored packet

	/*
	 * Constructor :
	 * 
	 * Dimensions and description of the packet to be stored are passed as
	 * parameters when creating a new object of this class .
	 * 
	 * @param (id,,width, height, depth, description, weight) ---> dimensions of
	 * packet to be stored
	 */

	public Packet(int id, int w, int h, int d, String desc, int wt) {
		id_num = id;
		width = w;
		height = h;
		depth = d;
		description = desc;
		weight = wt;
		location = null;
	}

/////////////////////////////////////////////////////////////////////////
//methods (public interface)
/////////////////////////////////////////////////////////////////////////

	/*
	 * getWidth method :
	 * 
	 * gets the width of the packet to be stored.
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/*
	 * getHeight method :
	 * 
	 * gets the height of the packet to be stored.
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/*
	 * getDepth method :
	 * 
	 * gets the depth of the packet to be stored.
	 * 
	 * @return depth
	 */

	public int getDepth() {
		return depth;
	}

	/*
	 * getId method :
	 * 
	 * gets the id of the packet to be stored.
	 * 
	 * @return id_num
	 */

	public int getId() {
		return id_num;
	}

	/*
	 * getDescription method :
	 * 
	 * gets the description of the packet to be stored.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/*
	 * getWeight method :
	 * 
	 * gets the weight of the packet to be stored.
	 * 
	 * @return weight
	 */
	public int getWeight() {
		return weight;
	}

	/*
	 * getLocation method :
	 * 
	 * gets the location of the stored packet.
	 * 
	 * @return location
	 */
	public Slot getLocation() {
		return location;
	}

	/*
	 * setLocation method :
	 * 
	 * sets the suitable location for the packet to be stored in.
	 * 
	 * @param slot
	 */
	public void setLocation(Slot slot) {
		location = slot;
	}
}