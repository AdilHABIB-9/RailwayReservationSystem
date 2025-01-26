package newpackage;

import java.util.ArrayList;

public class Train {
    private String trainName;
    private String source;
    private String destination;
    private int availableSeats;
    private ArrayList<Integer> seats;

    public Train(String trainName, String source, String destination, int availableSeats) {
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.availableSeats = availableSeats;

        // Initialize seat numbers
        seats = new ArrayList<>();
        for (int i = 1; i <= availableSeats; i++) {
            seats.add(i);
        }
    }

    public Train(String trainName, String source, String destination, int availableSeats, String travelTime) {
    }

    public String getTrainName() {
        return trainName;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public ArrayList<Integer> getAvailableSeats() {
        return new ArrayList<>(seats); // Return a copy of seat numbers
    }

    public boolean bookSeat(int seatNumber) {
        if (seats.contains(seatNumber)) {
            seats.remove(Integer.valueOf(seatNumber));
            availableSeats--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Train: %s | Source: %s | Destination: %s | Seats Available: %d",
                trainName, source, destination, availableSeats);
    }
}
