import java.util.HashMap;

abstract class Room {

    protected String type;
    protected int beds;
    protected int size;
    protected double price;

    public Room(String type, int beds, int size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void displayDetails() {
        System.out.println("Room Type : " + type);
        System.out.println("Beds      : " + beds);
        System.out.println("Size      : " + size + " sq.ft");
        System.out.println("Price     : Rs." + price);
    }
}

/* Concrete Room Classes */

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 200, 2500);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 350, 4000);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 600, 8000);
    }
}

class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 10);
        inventory.put("Double Room", 5);
        inventory.put("Suite Room", 0); // Example unavailable room
    }

    // Read-only access
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

class SearchService {

    private RoomInventory inventory;

    public SearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void searchRooms(Room[] rooms) {

        System.out.println("Available Rooms");
        System.out.println("---------------------------");

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.getType());

            // Show only available rooms
            if (available > 0) {
                room.displayDetails();
                System.out.println("Available Rooms : " + available);
                System.out.println("---------------------------");
            }
        }
    }
}

public class UseCase1HotelBookingApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("       BOOK MY STAY APP");
        System.out.println("    Hotel Booking System v4.1");
        System.out.println("=================================\n");

        // Create room domain objects
        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Initialize search service
        SearchService searchService = new SearchService(inventory);

        // Perform room search
        searchService.searchRooms(rooms);
    }
}