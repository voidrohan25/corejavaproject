package Savariii.com;

import java.util.*;

class RideService {

    ArrayList<Driver> drivers = new ArrayList<>();
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    ArrayList<Booking> bookings = new ArrayList<>();

    Queue<Booking> pendingRequests = new LinkedList<>();

    public void addDriver(Driver d) {
        drivers.add(d);
    }

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    // Passenger creates request
    public Booking requestRide(Passenger p, String type,
                               double dist, String start, String end) {

        for (Vehicle v : vehicles) {
            if (v.getType().equalsIgnoreCase(type) && v.isAvailable()) {

                Booking b = new Booking(p, v, dist, start, end);

                bookings.add(b);
                pendingRequests.add(b);
                p.addBooking(b);

                System.out.println("Ride request sent... ⏳");

                return b;
            }
        }
        return null;
    }

    public void driverAcceptRide() {

        if (pendingRequests.isEmpty()) {
            System.out.println("No pending requests");
            return;
        }

        Booking b = pendingRequests.poll();

        for (Driver d : drivers) {
            if (d.isAvailable()) {
                b.assignDriver(d);
                return;
            }
        }

        System.out.println("No drivers available");
    }

    public Booking getActiveRide() {
        for (Booking b : bookings) {
            if (b.isActive()) return b;
        }
        return null;
    }

    public Booking getLatestBooking() {
        if (bookings.isEmpty()) return null;
        return bookings.get(bookings.size() - 1);
    }
}