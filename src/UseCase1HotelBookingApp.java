import java.util.HashMap;


class RoomInventory {

    // HashMap to store room type and availability
    private HashMap<String, Integer> inventory;

    // Constructor to initialize inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        // Initialize room availability
        inventory.put("Single Room", 10);
        inventory.put("Double Room", 6);
        inventory.put("Suite Room", 3);
    }

    // Method to get availability of a room type
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Method to update availability
    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Method to display inventory
    public void displayInventory() {

        System.out.println("Current Room Inventory");
        System.out.println("--------------------------");

        for (String roomType : inventory.keySet()) {
            System.out.println(roomType + " : " + inventory.get(roomType));
        }
    }
}



public class UseCase1HotelBookingApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("       BOOK MY STAY APP");
        System.out.println("    Hotel Booking System v3.1");
        System.out.println("=================================\n");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display current inventory
        inventory.displayInventory();

        System.out.println("\nChecking availability of Double Room...");
        System.out.println("Available: " + inventory.getAvailability("Double Room"));

        System.out.println("\nUpdating Suite Room availability...");

        // Update availability
        inventory.updateAvailability("Suite Room", 2);

        System.out.println("\nUpdated Inventory:");
        inventory.displayInventory();
    }
}