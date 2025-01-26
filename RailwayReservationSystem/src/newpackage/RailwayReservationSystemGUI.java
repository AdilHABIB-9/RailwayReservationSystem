package newpackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RailwayReservationSystemGUI {
    private RailwayReservationSystem system;
    private JFrame frame;
    private JTextArea outputArea;
    private JTextField usernameField, passwordField, trainNameField, seatNumberField;
    private JButton registerButton, loginButton, viewTrainsButton, bookButton, bookingsButton;
    private User loggedInUser;

    public RailwayReservationSystemGUI() {
        system = new RailwayReservationSystem();
        frame = new JFrame("Railway Reservation System");
        outputArea = new JTextArea(12, 40);
        usernameField = new JTextField(15);
        passwordField = new JTextField(15);
        trainNameField = new JTextField(15);
        seatNumberField = new JTextField(5);

        setupGUI();
    }

    private void setupGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Registration Section
        frame.add(new JLabel("Username:"));
        frame.add(usernameField);
        frame.add(new JLabel("Password:"));
        frame.add(passwordField);
        registerButton = new JButton("Register");
        frame.add(registerButton);

        // Login Section
        loginButton = new JButton("Login");
        frame.add(loginButton);

        // Trains and Booking Section
        viewTrainsButton = new JButton("View Available Trains");
        frame.add(viewTrainsButton);

        frame.add(new JLabel("Train Name:"));
        frame.add(trainNameField);
        frame.add(new JLabel("Seat Number:"));
        frame.add(seatNumberField);
        bookButton = new JButton("Book Train Seat");
        frame.add(bookButton);

        bookingsButton = new JButton("View My Bookings");
        frame.add(bookingsButton);

        // Output Area
        outputArea.setEditable(false);
        frame.add(new JScrollPane(outputArea));

        // Button Listeners
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        viewTrainsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAvailableTrains();
            }
        });

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBooking();
            }
        });

        bookingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayUserBookings();
            }
        });

        frame.pack();  // Adjusts the frame size based on its content
        frame.setVisible(true);
    }

    private void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            outputArea.setText("Username and Password cannot be empty.");
            return;
        }

        String result = system.registerUser(username, password);
        outputArea.setText(result);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        loggedInUser = system.loginUser(username, password);
        if (loggedInUser != null) {
            outputArea.setText("Login successful! Welcome " + username);
        } else {
            outputArea.setText("Invalid login credentials. Please try again.");
        }
    }

    private void handleBooking() {
        if (loggedInUser == null) {
            outputArea.setText("You need to log in first.");
            return;
        }

        String trainName = trainNameField.getText();
        String seatNumberText = seatNumberField.getText();

        try {
            int seatNumber = Integer.parseInt(seatNumberText);
            String result = system.bookTrainSeat(loggedInUser.getUsername(), trainName, seatNumber);
            outputArea.setText(result);
        } catch (NumberFormatException ex) {
            outputArea.setText("Please enter a valid seat number.");
        }
    }

    private void displayUserBookings() {
        if (loggedInUser == null) {
            outputArea.setText("You need to log in first.");
            return;
        }

        String result = system.getUserBookings(loggedInUser.getUsername());
        outputArea.setText(result);
    }

    private void displayAvailableTrains() {
        String result = system.displayTrains();
        outputArea.setText(result);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RailwayReservationSystemGUI();  // Initialize and show the GUI
            }
        });
    }
}