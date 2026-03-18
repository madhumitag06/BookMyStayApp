// Simple Room class (no inheritance)
class Room {

    String roomType;
    int beds;
    int size;
    double price;

    // Constructor
    public Room(String roomType, int beds, int size, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    // Method to display room details
    public void displayDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + beds);
        System.out.println("Size      : " + size + " sq.ft");
        System.out.println("Price     : Rs." + price);
    }
}

/**
 * Main Application Class
 */
public class UseCase1HotelBookingApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("     BOOK MY STAY APP");
        System.out.println("   Hotel Booking System v1.0");
        System.out.println("=================================\n");

        // Create room objects directly
        Room single = new Room("Single Room", 1, 200, 2500);
        Room doubleRoom = new Room("Double Room", 2, 350, 4000);
        Room suite = new Room("Suite Room", 3, 600, 8000);

        // Static availability
        int singleAvailability = 10;
        int doubleAvailability = 6;
        int suiteAvailability = 3;

        // Display Single Room
        single.displayDetails();
        System.out.println("Available Rooms : " + singleAvailability);
        System.out.println("---------------------------------\n");

        // Display Double Room
        doubleRoom.displayDetails();
        System.out.println("Available Rooms : " + doubleAvailability);
        System.out.println("---------------------------------\n");

        // Display Suite Room
        suite.displayDetails();
        System.out.println("Available Rooms : " + suiteAvailability);
        System.out.println("---------------------------------\n");

        System.out.println("Room information displayed successfully.");
    }
}