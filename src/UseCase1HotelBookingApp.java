import java.util.*;

/**
 * Represents an optional add-on service.
 */
class AddOnService {

    private String serviceName;
    private double price;

    public AddOnService(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getPrice() {
        return price;
    }

    public void displayService() {
        System.out.println(serviceName + " : Rs." + price);
    }
}

/**
 * Manages services attached to reservations.
 */
class AddOnServiceManager {

    // reservationID → list of services
    private Map<String, List<AddOnService>> reservationServices;

    public AddOnServiceManager() {
        reservationServices = new HashMap<>();
    }

    // Add a service to a reservation
    public void addService(String reservationId, AddOnService service) {

        reservationServices
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);

        System.out.println(service.getServiceName() +
                " added to Reservation " + reservationId);
    }

    // Display services for a reservation
    public void displayServices(String reservationId) {

        List<AddOnService> services = reservationServices.get(reservationId);

        if (services == null) {
            System.out.println("No services selected.");
            return;
        }

        System.out.println("\nServices for Reservation " + reservationId);
        System.out.println("----------------------------------");

        for (AddOnService s : services) {
            s.displayService();
        }
    }

    // Calculate total cost of services
    public double calculateTotalCost(String reservationId) {

        double total = 0;

        List<AddOnService> services = reservationServices.get(reservationId);

        if (services != null) {
            for (AddOnService s : services) {
                total += s.getPrice();
            }
        }

        return total;
    }
}

/**
 * Main class (your class name)
 */
public class UseCase1HotelBookingApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("       BOOK MY STAY APP");
        System.out.println("    Hotel Booking System v7.1");
        System.out.println("=================================");

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "RES101";

        // Create services
        AddOnService breakfast = new AddOnService("Breakfast", 500);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 1200);
        AddOnService spa = new AddOnService("Spa Access", 2000);

        // Guest selects services
        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, airportPickup);
        manager.addService(reservationId, spa);

        // Display services
        manager.displayServices(reservationId);

        // Calculate total additional cost
        double totalCost = manager.calculateTotalCost(reservationId);

        System.out.println("\nTotal Add-On Cost : Rs." + totalCost);
    }
}