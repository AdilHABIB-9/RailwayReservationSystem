package newpackage;

public class Booking {
    private String username;
    private String trainName;
    private int seatNumber;

    public Booking(String username, String trainName, int seatNumber) {
        this.username = username;
        this.trainName = trainName;
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return String.format("User: %s | Train: %s | Seat: %d",
                username, trainName, seatNumber);
    }
}


