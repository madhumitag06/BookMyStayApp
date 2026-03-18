import java.util.HashMap;

/*
 * Custom Exception for invalid booking situations
 */
class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}

/*
 * Inventory Service
 * Maintains room availability
 */
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 0);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, -1);
    }

    public void decreaseRoom(String roomType) throws InvalidBookingException {

        int current = inventory.get(roomType);

        if (current <= 0) {
            throw new InvalidBookingException(
                    "No rooms available for " + roomType);
        }

        inventory.put(roomType, current - 1);
    }

    public void displayInventory() {

        System.out.println("\nCurrent Inventory");
        System.out.println("--------------------");

        for (String type : inventory.keySet()) {
            System.out.println(type + " : " + inventory.get(type));
        }
    }
}

/*
 * Validator class for booking input
 */
class BookingValidator {

    public static void validateRoomType(String roomType,
                                        RoomInventory inventory)
            throws InvalidBookingException {

        int availability = inventory.getAvailability(roomType);

        if (availability == -1) {
            throw new InvalidBookingException(
                    "Invalid room type: " + roomType);
        }

        if (availability <= 0) {
            throw new InvalidBookingException(
                    "Requested room type not available: " + roomType);
        }
    }
}

/*
 * Booking Service
 */
class BookingService {

    private RoomInventory inventory;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void bookRoom(String guestName, String roomType) {

        try {

            // Step 1: Validate input
            BookingValidator.validateRoomType(roomType, inventory);

            // Step 2: Allocate room
            inventory.decreaseRoom(roomType);

            System.out.println("Booking Confirmed");
            System.out.println("Guest : " + guestName);
            System.out.println("Room Type : " + roomType);
            System.out.println("----------------------");

        } catch (InvalidBookingException e) {

            System.out.println("Booking Failed for " + guestName);
            System.out.println("Reason: " + e.getMessage());
            System.out.println("----------------------");
        }
    }
}

/*
 * Main class for Use Case 1
 */
public class UseCase1HotelBookingApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("       BOOK MY STAY APP");
        System.out.println("    Hotel Booking System v9.1");
        System.out.println("=================================");

        RoomInventory inventory = new RoomInventory();

        BookingService bookingService = new BookingService(inventory);

        // Valid booking
        bookingService.bookRoom("Mukesh", "Single Room");

        // Invalid room type
        bookingService.bookRoom("Rahul", "Luxury Room");

        // Room not available
        bookingService.bookRoom("Ananya", "Suite Room");

        // Display remaining inventory
        inventory.displayInventory();
    }
}