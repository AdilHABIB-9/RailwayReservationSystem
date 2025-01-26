package newpackage;

import java.util.ArrayList;
import java.util.HashMap;

public class RailwayReservationSystem {
    private ArrayList<Train> trains;
    private HashMap<String, User> users;
    private ArrayList<Booking> bookings;

    public RailwayReservationSystem() {
        trains = new ArrayList<>();
        users = new HashMap<>();
        bookings = new ArrayList<>();
        initializeTrains();
    }

    // Preload train data
    private void initializeTrains() {
        trains.add(new Train("Rajdhani Express", "Delhi", "Mumbai", 10));
        trains.add(new Train("Shatabdi Express", "Chennai", "Bangalore", 15));
        trains.add(new Train("Duronto Express", "Kolkata", "Delhi", 8));
    }

    // Register a user
    public String registerUser(String username, String password) {
        if (users.containsKey(username)) {
            return "Registration failed: Username already exists!";
        }
        users.put(username, new User(username, password));
        return "Registration successful! Welcome, " + username + "!";
    }

    // User login
    public User loginUser(String username, String password) {
        if (users.containsKey(username) && users.get(username).validatePassword(password)) {
            return users.get(username);
        }
        return null;
    }

    // Display list of available trains
    public String displayTrains() {
        StringBuilder result = new StringBuilder("Available Trains:\n");
        for (Train train : trains) {
            result.append(train).append("\n");
        }
        return result.toString();
    }

    // Book a train seat
    public String bookTrainSeat(String username, String trainName, int seatNumber) {
        for (Train train : trains) {
            if (train.getTrainName().equalsIgnoreCase(trainName)) {
                if (train.bookSeat(seatNumber)) {
                    Booking booking = new Booking(username, trainName, seatNumber);
                    bookings.add(booking);
                    return "Booking successful for Train: " + trainName + ", Seat: " + seatNumber + "!";
                }
                return "Failed to book: Seat " + seatNumber + " is unavailable.";
            }
        }
        return "Train not found!";
    }

    // Display bookings for a user
    public String getUserBookings(String username) {
        StringBuilder result = new StringBuilder("Bookings for " + username + ":\n");
        boolean hasBookings = false;
        for (Booking booking : bookings) {
            if (booking.toString().contains(username)) {
                result.append(booking).append("\n");
                hasBookings = true;
            }
        }
        return hasBookings ? result.toString() : "No bookings found!";
    }
}