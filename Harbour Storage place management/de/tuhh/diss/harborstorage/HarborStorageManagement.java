package de.tuhh.diss.harborstorage;

import de.tuhh.diss.harborstorage.sim.HighBayStorage;
import de.tuhh.diss.harborstorage.sim.PhysicalHarborStorage;
import de.tuhh.diss.harborstorage.sim.StorageException;
import de.tuhh.diss.harborstorage.sim.StoragePlace;
import de.tuhh.diss.harborstorage.sim.*;

public class HarborStorageManagement implements HighBayStorage {

	/* attributes */

	private PhysicalHarborStorage phs; // reference to PhysicalHarborStorage class
	private PhysicalCrane cr; // reference to PhysicalCrane class
	private StoragePlace storage[]; // An array of "already provided" storage places
	private Packet[] packet; // An array of packets to be stored
	private Slot[] slot; // An array of available slots in the storage area
	private CraneControl cc; // reference to CraneControl class
	private int n = 0; // number of packets stored in Rack
	private int retrieve_id = 0; // An identity for retrieving packets
	public final static int MAX_DEPTH = 2; // the maximum permissible depth in the storage area
	public final static int MAX_HEIGHT = 4; // the maximum permissible height in the storage area
	public final static int MAX_WIDTH = 4; // the maximum permissible width in the storage area

	/*
	 * Constructor :
	 * 
	 * Initializing HarborStorageManagement with empty PhysicalHarborStorage,obtains
	 * its PhysicalCrane by calling getCrane() and constructs the CraneControl. Once
	 * it is created , a copy of the storage places is passed completely to the
	 * slots array
	 */

	public HarborStorageManagement() {
		phs = new PhysicalHarborStorage();
		cr = phs.getCrane();
		storage = phs.getStoragePlacesAsArray();
		cc = new CraneControl(cr);
		packet = new Packet[storage.length];
		slot = new Slot[storage.length];
		for (int i = 0; i <= storage.length - 1; i++) {
			StoragePlace s = storage[i];
			slot[i] = new Slot(s.getNumber(), s.getPositionX(), s.getPositionY(), s.getWidth(), s.getHeight(),
					s.getDepth(), s.getLoadCapacity());
		}
	}

/////////////////////////////////////////////////////////////////////////
// methods (public interface)
/////////////////////////////////////////////////////////////////////////

	/*
	 * " storePacket method :
	 * 
	 * used to store a packet in an adequate slot by constructing a new packet
	 * object from the user inputs and also checking for the following (throwing
	 * storage exception) : 1 - The size of the packet array and whether it is full
	 * or not 2 - The description entered by the user and whether it is provided
	 * before or not.
	 * 
	 * @param (width, height, depth, description, weight) ---> dimensions of packet
	 * to be stored
	 * 
	 * @return id--->packet id
	 */
	public int storePacket(int width, int height, int depth, String description, int weight) throws StorageException {
		int id = insertPacket(packet);
		boolean desc_check = checkPackets(description, packet);
		if (id >= packet.length) {
			throw new StorageException("\"slots are full can not store any packet\"");
		} else if (desc_check == true) {
			throw new StorageException(
					"\"description " + description + " already exists please enter unique description\"");
		} else if ((width > MAX_WIDTH) || (height > MAX_HEIGHT) || (depth > MAX_DEPTH)) {
			throw new StorageException("Entered dimensions exceed the permissible limit ");
		} else {
			Packet p = new Packet(id, width, height, depth, description, weight);
			int slot_Number = findSuitableSlot(p);
			int px = slot[slot_Number].getPositionX();
			int py = slot[slot_Number].getPositionY();
			cc.storePacket(px, py, p);
			packet[id] = p;
			p.setLocation(slot[slot_Number]);
			slot[slot_Number].setContainedPacket(p);
			n++;

		}
		return id;

	}

	/*
	 * retrievePacket method:
	 * 
	 * used to retrieve a stored packet from the storage area by calling its
	 * description , getting its location and passing the x and y positions to the
	 * crane .Once the packet is retrieved , the assigned slot and the packets'
	 * position in the array are cleared(throwing an exception if the description
	 * provided does't exist)
	 * 
	 * @param description --->description of the packet to be retrieved
	 */
	public void retrievePacket(String description) throws StorageException {
		if (checkPackets(description, packet) == true) {
			int rx = packet[retrieve_id].getLocation().getPositionX();
			int ry = packet[retrieve_id].getLocation().getPositionY();
			cc.retrievePacket(rx, ry);
			packet[retrieve_id].getLocation().setContainedPacket(null);
			packet[retrieve_id] = null;
			n--;
		} else {
			throw new StorageException("\"Packet not found. Check entered Description.\"");
		}

	}

	/*
	 * Packet [] getPackets method : used as a counter to show the available packet
	 * to the user .
	 * 
	 * @return storedPacket --->an array with the available packets in the storage
	 * area.
	 */
	public Packet[] getPackets() {
		Packet[] storedPacket = new Packet[n];
		int j = 0;
		for (int i = 0; i <= packet.length - 1; i++) {
			if (packet[i] != null) {
				storedPacket[j] = packet[i];
				j++;
			}
		}
		return storedPacket;
	}

	/*
	 * shutdown method :
	 * 
	 * switches off the crane by using CraneControl’s shutdown() method before
	 * terminating the program.
	 */
	public void shutdown() {
		cc.shutdown();

	}

/////////////////////////////////////////////////////////////////////////
// methods (internal)
/////////////////////////////////////////////////////////////////////////

	/*
	 * insertPacket method : used as a counter for assigning the stored packets in
	 * the packets' array.
	 * 
	 * @param packet --->an array of packets
	 * 
	 * @return packet.length---->size of packets' array (number of stored packets)
	 */

	private int insertPacket(Packet[] packet) {
		for (int i = 0; i <= packet.length - 1; i++) {
			if (packet[i] == null) {
				return i;
			}
		}
		return packet.length;
	}

	/*
	 * checkPacket method :
	 * 
	 * used to check if the entered description already matches a previously entered
	 * one or not.
	 * 
	 * @param description--->description of the packet to be stored
	 * 
	 * @param packet --->an array of stored packets
	 * 
	 * @return true if the description provided matches a one before , false if
	 * there is no packet stored before or the description provided doesn't match
	 * anyone provided before.
	 * 
	 */
	private boolean checkPackets(String description, Packet[] packet) throws StorageException {

		for (int i = 0; i <= packet.length - 1; i++) {
			if (packet[i] == null) {

			} else if (packet[i].getDescription().equals(description)) {
				retrieve_id = i;
				return true;
			}
		}

		return false;
	}

	/*
	 * findSuitableSlot method :
	 * 
	 * used as the most important algorithm to search for the most suitable slot ,
	 * taking into account the packets' dimensions and the closest slot to the
	 * loading position.(throwing an exception if there is no slot available for the
	 * given packet)
	 * 
	 * @param p--->a packet to be stored.
	 * 
	 * @return nearestSlot---> the most suitable slot according to the
	 * above-mentioned criteria.
	 */
	private int findSuitableSlot(Packet p) throws StorageException {
		int[] suitableSlots = new int[storage.length];
		int c = 0;
		int[] xy = new int[storage.length];
		int nearestSlot = 0;
		int tempXY;

		for (int i = 0; i <= storage.length - 1; i++) {
			if (p.getWidth() <= slot[i].getWidth() && p.getHeight() <= slot[i].getHeight()
					&& p.getDepth() <= slot[i].getDepth() && p.getWeight() <= slot[i].getLoadCapacity()
					&& slot[i].getContainedPacket() == null) {
				int x = slot[i].getPositionX();
				int y = slot[i].getPositionY();
				c++;
				suitableSlots[c - 1] = i;
				xy[c - 1] = x + y;

			}
		}
		nearestSlot = suitableSlots[0];
		tempXY = xy[0];
		if (c > 0) {
			for (int i = 0; i <= c - 1; i++) {
				if (xy[i] < tempXY) {
					nearestSlot = suitableSlots[i];
					tempXY = xy[i];
				}
			}
		} else {
			throw new StorageException("Slot of this dimension is full or Dimensions exceed the permissibe limits ");
		}
		return nearestSlot;
	}
}