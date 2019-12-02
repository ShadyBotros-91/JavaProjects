package de.tuhh.diss.harborstorage;

import de.tuhh.diss.harborstorage.sim.StorageException;
import de.tuhh.diss.io.SimpleIO;
import java.util.*;

public class HarborStorageApp {
	/*
	 * Initializing the HarborStorageManagement showing a live GUI of the storage
	 * area.
	 */

	static HarborStorageManagement hsm = new HarborStorageManagement();

	public static void main(String[] args) throws StorageException {
		int choice; // Providing different choices for the user
		boolean pin = true;
		SimpleIO.println("Welcome to TUHH/DISS Harbor Storage Management");

		/////////////////////////////////////////////////////////////////////////
		// User Interface
		/////////////////////////////////////////////////////////////////////////

		do {
			try {
				SimpleIO.println("***Main Menu***");
				SimpleIO.println("0: Quit Program");
				SimpleIO.println("1: Store a packet in HighbayStorage");
				SimpleIO.println("2: Retrive a Packet from HighBaystorage");
				SimpleIO.print("--YourChoice: ");
				choice = SimpleIO.readInt();
				if (choice == 0) {
					SimpleIO.println("System ends");
					hsm.shutdown();
					pin = false;
				} else if (choice == 1) {
					SimpleIO.println("* Store a packet *");
					store();
				}

				else if (choice == 2) {
					retrieve();
				} else {
					throw new IllegalArgumentException("");
				}
			} catch (InputMismatchException | IllegalArgumentException e) {
				SimpleIO.println("\"Please Enter Valid Input\"");
			}
		} while (pin);
	}

	/////////////////////////////////////////////////////////////////////////
	// methods (internal)
	/////////////////////////////////////////////////////////////////////////

	/*
	 * showAvailablePackets method :
	 * 
	 * used to display the stored packets to the user with their complete
	 * description in order to choose the one to retrieve from the storage area.
	 * 
	 * @return--->true if there are available packets to be retrieved , false if
	 * there are no available packets,
	 */
	private static void showAvailablePackets() throws StorageException {
		Packet[] ap = new Packet[hsm.getPackets().length];
		if (hsm.getPackets().length <= 0) {
			throw new StorageException("There are no packets");
		} else {
			SimpleIO.println("AvailablePackets");
			for (int i = 0; i <= hsm.getPackets().length - 1; i++) {
				ap = hsm.getPackets();
			}
			for (int i = 0; i <= hsm.getPackets().length - 1; i++) {
				SimpleIO.println(
						i + 1 + ":  Packet " + ("\"" + ap[i].getDescription() + "\"") + " size : " + ap[i].getWidth()
								+ "x" + ap[i].getHeight() + "x" + ap[i].getDepth() + " weight : " + ap[i].getWeight());
			}
		}
	}

	/*
	 * setPacketWidth method :
	 * 
	 * used to react to invalid Width input(throwing an exception).
	 * 
	 * @return w --->width of the packet.
	 */
	private static int setPacketWidth() {
		int w = 0;
		while (w <= 0) {
			try {
				SimpleIO.println("Enter Packet Width: ");
				w = SimpleIO.readInt();

				if (w <= 0) {
					throw new IllegalArgumentException("Invalid width");
				}
			} catch (InputMismatchException | IllegalArgumentException e) {
				SimpleIO.println("Please Enter Positive value of width ");
			}
		}
		return w;
	}

	/*
	 * setPacketHeight method :
	 * 
	 * used to react to invalid Height input(throwing an exception).
	 * 
	 * @return h --->height of the packet.
	 */
	private static int setPacketHeight() {
		int h = 0;
		while (h <= 0) {
			try {
				SimpleIO.println("Enter Packet Height: ");
				h = SimpleIO.readInt();
				if (h < 0) {
					throw new IllegalArgumentException("Invalid width");
				}

			} catch (InputMismatchException | IllegalArgumentException e) {
				SimpleIO.println("Please enter Positive of height");
			}
		}
		return h;
	}

	/*
	 * setPacketDepth method :
	 * 
	 * used to react to invalid depth input (throwing an exception).
	 * 
	 * @return d --->depth of the packet.
	 */
	private static int setPacketDepth() {
		int d = 0;
		while (d <= 0) {
			try {
				SimpleIO.println("Enter Packet Depth: ");
				d = SimpleIO.readInt();
				if (d < 0) {
					throw new IllegalArgumentException("Invalid width");
				}
			} catch (InputMismatchException | IllegalArgumentException e) {
				SimpleIO.println("Enter Positive value of depth");
			}
		}
		return d;
	}

	/*
	 * setPacketWeight method :
	 * 
	 * used to react to invalid weight input (throwing an exception).
	 * 
	 * @return we --->weight of the packet.
	 */
	private static int setPacketWeight() {
		int we = 0;
		while (we <= 0) {
			try {
				SimpleIO.println("Enter Packet Weight: ");
				we = SimpleIO.readInt();
				if (we < 0) {
					throw new IllegalArgumentException("Invalid width");
				}
			} catch (InputMismatchException | IllegalArgumentException e) {
				SimpleIO.println("Enter Positive value of weight");
			}
		}
		return we;
	}

	/*
	 * setPacketDepth method :
	 * 
	 * used to react to invalid description input (throwing an exception).
	 * 
	 * @return desc --->description of the packet.
	 */
	private static String setPacketDescription() {
		String desc;
		SimpleIO.println("Enter Packet description: ");
		desc = SimpleIO.readString();

		while (desc.length() == 0 || desc.equals("0")) {
			try {

				if (desc.length() == 0 || desc.equals("0")) {
					throw new NullPointerException("\"Invalid description(0 can not used as description)\"");
				}
			} catch (NullPointerException e) {
				SimpleIO.println("Invaid description.Enter again");
				desc = SimpleIO.readString();
			}
		}
		return desc;
	}

	/*
	 * store method :
	 * 
	 * used to obtain user inputs and calls the storePacket method from
	 * HarborStorageManagement class
	 */

	private static void store() {
		String description = setPacketDescription();
		int width = setPacketWidth();
		int height = setPacketHeight();
		int depth = setPacketDepth();
		int weight = setPacketWeight();
		int c = 0;
		c++;
		SimpleIO.println(" Your entered packet" + "\"Packet\"" + c + " of size " + width + "x" + height + "x" + depth
				+ " and weight " + weight);
		SimpleIO.println("Shall we store the packet? (y/n)");
		String input = SimpleIO.readString();
		if (input.equals("y") || input.equals("Y")) {
			try {
				int id = hsm.storePacket(width, height, depth, description, weight);
				SimpleIO.println("Packet stored in rack. The ID is " + id);
			} catch (StorageException e) {
				e.fillInStackTrace();
				SimpleIO.println(e.toString());
			}
		} else if (input.equals("n") || input.equals("N")) {
			// if user denies to store packet
		} else {
			SimpleIO.println("inavlid input");
		}
	}

	/*
	 * retrieve method :
	 * 
	 * used to retrieve the packet by calling retrievePacket method from
	 * HarborStorageManagementand class also react to empty storage area (throwing
	 * an exception for incorrect descriptions).
	 * 
	 */

	private static void retrieve() {

		try {
			showAvailablePackets();
			SimpleIO.println("*** Enter description of Packet to be retrieved (0 = Abort)::");
			String description = SimpleIO.readString();
			if (description.equals("0")) {
				// returns to the main menu
			} else {
				hsm.retrievePacket(description);
				SimpleIO.println("Packet retrieved");
			}
		}

		catch (StorageException e) {
			e.fillInStackTrace();
			SimpleIO.println(e.toString());
		}
	}
}
