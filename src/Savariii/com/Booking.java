package Savariii.com;

import java.util.Random;

class Booking {

    private Passenger p;
    private Driver d;
    private Vehicle v;
    private double fare;
    private boolean active;
    private boolean accepted = false;

    private int otp;

    private String startLocation;
    private String endLocation;

    public Booking(Passenger p, Vehicle v,
                   double dist, String start, String end) {

        this.p = p;
        this.v = v;
        this.fare = dist * v.getRate();
        this.active = false;

        this.startLocation = start;
        this.endLocation = end;
    }

    public void assignDriver(Driver d) {
        this.d = d;
        this.accepted = true;
        d.setAvailable(false);
        v.setAvailable(false);

        generateOTP();
        System.out.println("Driver " + d.name + " accepted your ride");
        System.out.println("OTP: " + otp);
    }

    private void generateOTP() {
        Random r = new Random();
        otp = 1000 + r.nextInt(9000);
    }

    public boolean startRide(int userOtp) {
        if (userOtp == otp) {
            active = true;
            System.out.println("Ride Started ");
            return true;
        } else {
            System.out.println("Wrong OTP ");
            return false;
        }
    }

    public double getFare() {
        return fare;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void completeRide(Payment payment) {
        payment.pay(fare);
        d.addEarnings(fare);

        active = false;
        d.setAvailable(true);
        v.setAvailable(true);

        System.out.println("Ride Completed ");
    }

    public void cancel() {
        active = false;

        if (d != null) d.setAvailable(true);
        v.setAvailable(true);

        System.out.println("Ride Cancelled ");
    }

    public void showDetails() {
        System.out.println("Passenger: " + p.name);
        if (d != null)
            System.out.println("Driver: " + d.name);
        else
            System.out.println("Driver: Not Assigned Yet");

        System.out.println("Vehicle: " + v.getType());
        System.out.println("From: " + startLocation);
        System.out.println("To: " + endLocation);
        System.out.println("Fare: " + fare);
        System.out.println("----------------------------");
    }
}