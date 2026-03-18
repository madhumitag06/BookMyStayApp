import java.util.*;

/*
 * Reservation class represents a confirmed booking
 */
class Reservation {

    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayReservation() {
        System.out.println("Reservation ID : " + reservationId);
        System.out.println("Guest Name     : " + guestName);
        System.out.println("Room Type      : " + roomType);
        System.out.println("-------------------------------");
    }
}

/*
 * BookingHistory stores confirmed reservations
 */
class BookingHistory {

    private List<Reservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    // Add reservation to history
    public void addReservation(Reservation reservation) {
        history.add(reservation);
        System.out.println("Reservation stored in history: " +
                reservation.getReservationId());
    }

    public List<Reservation> getHistory() {
        return history;
    }
}

/*
 * BookingReportService generates reports
 */
class BookingReportService {

    private BookingHistory bookingHistory;

    public BookingReportService(BookingHistory bookingHistory) {
        this.bookingHistory = bookingHistory;
    }

    // Display full booking history
    public void displayBookingHistory() {

        System.out.println("\nBooking History Report");
        System.out.println("========================");

        List<Reservation> reservations = bookingHistory.getHistory();

        for (Reservation r : reservations) {
            r.displayReservation();
        }
    }

    // Generate summary report
    public void generateSummaryReport() {

        Map<String, Integer> summary = new HashMap<>();

        for (Reservation r : bookingHistory.getHistory()) {

            summary.put(
                    r.getRoomType(),
                    summary.getOrDefault(r.getRoomType(), 0) + 1
            );
        }

        System.out.println("\nBooking Summary Report");
        System.out.println("========================");

        for (String roomType : summary.keySet()) {
            System.out.println(roomType + " Bookings : " + summary.get(roomType));
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
        System.out.println("    Hotel Booking System v1.0");
        System.out.println("=================================");

        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings
        Reservation r1 = new Reservation("RES101", "Mukesh", "Single Room");
        Reservation r2 = new Reservation("RES102", "Rahul", "Double Room");
        Reservation r3 = new Reservation("RES103", "Ananya", "Single Room");

        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        // Generate reports
        BookingReportService reportService = new BookingReportService(history);

        reportService.displayBookingHistory();

        reportService.generateSummaryReport();
    }
}