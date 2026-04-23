package Savariii.com;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RideService service = new RideService();
        EmailService emailService = new EmailService();

        Admin admin = new Admin("Admin", "999", "admin", "123");
        Passenger passenger = new Passenger("Rohan", "999", "rohan", "123");

        service.addVehicle(new Vehicle("V1", "Bike", 10));
        service.addVehicle(new Vehicle("V2", "Car", 20));

        service.addDriver(new Driver("Rohit", "123", "Bike"));
        service.addDriver(new Driver("Amit", "456", "Car"));

        while (true) {

            System.out.println("\n===== OLA APP =====");
            System.out.println("1. Passenger");
            System.out.println("2. Driver");
            System.out.println("3. Admin");
            System.out.println("4. Exit");
            System.out.println("Enter your choice:");

            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    sc.nextLine();

                    System.out.print("Enter your email: ");
                    String email = sc.nextLine();

                    emailService.sendOTP(email);

                    System.out.print("Enter OTP: ");
                    int userOtp = sc.nextInt();

                    if (!emailService.verifyOTP(userOtp)) {
                        System.out.println("Wrong OTP");
                        break;
                    }

                    System.out.println("Login successful");

                    while (true) {

                        System.out.println("\n--- Passenger Menu ---");
                        System.out.println("1. Book Ride");
                        System.out.println("2. Cancel Ride");
                        System.out.println("3. Complete Ride (Payment)");
                        System.out.println("4. View History");
                        System.out.println("5. Logout");
                        System.out.println("6. Start Ride (Enter OTP)");

                        int c = sc.nextInt();

                        if (c == 1) {
                            sc.nextLine();

                            System.out.print("Enter Starting Location: ");
                            String start = sc.nextLine();

                            System.out.print("Enter Destination: ");
                            String end = sc.nextLine();

                            System.out.print("Enter vehicle type (Bike/Car): ");
                            String type = sc.nextLine();

                            System.out.print("Enter distance: ");
                            double dist = sc.nextDouble();

                            Booking b = service.requestRide(passenger, type, dist, start, end);

                            if (b != null) {
                                b.showDetails();
                            } else {
                                System.out.println("No vehicle available");
                            }
                        }

                        else if (c == 2) {
                            Booking b = service.getLatestBooking();

                            if (b != null) {
                                b.cancel();
                            } else {
                                System.out.println("No ride");
                            }
                        }

                        else if (c == 3) {
                            Booking b = service.getActiveRide();

                            if (b != null) {
                                System.out.println("1. Cash  2. UPI");
                                int pay = sc.nextInt();

                                Payment payment =
                                        (pay == 1) ? new CashPayment() : new UpiPayment();

                                b.completeRide(payment);
                            } else {
                                System.out.println("No active ride");
                            }
                        }

                        else if (c == 4) {
                            passenger.showHistory();
                        }

                        else if (c == 6) {
                            Booking ride = service.getLatestBooking();

                            if (ride != null && ride.isAccepted()) {
                                System.out.print("Enter OTP: ");
                                int otp = sc.nextInt();

                                ride.startRide(otp);
                            } else {
                                System.out.println("Ride not accepted yet");
                            }
                        }

                        else {
                            break;
                        }
                    }
                    break;

                case 2:
                    System.out.println("\n--- Driver Panel ---");

                    System.out.println("1. Accept Ride Request");
                    System.out.println("2. View Earnings");

                    int dch = sc.nextInt();

                    if (dch == 1) {
                        service.driverAcceptRide();
                    } else {
                        for (Driver d : service.drivers) {
                            System.out.println("Driver: " + d.name);
                            d.showEarnings();
                        }
                    }
                    break;

                case 3:
                    sc.nextLine();

                    System.out.print("Username: ");
                    String u = sc.nextLine();

                    System.out.print("Password: ");
                    String p = sc.nextLine();

                    if (admin.login(u, p)) {

                        while (true) {

                            System.out.println("\n--- Admin Menu ---");
                            System.out.println("1. Add Driver");
                            System.out.println("2. Add Vehicle");
                            System.out.println("3. Logout");

                            int a = sc.nextInt();

                            if (a == 1) {
                                sc.nextLine();

                                System.out.print("Name: ");
                                String n = sc.nextLine();

                                System.out.print("Phone: ");
                                String ph = sc.nextLine();

                                System.out.print("Vehicle Type: ");
                                String t = sc.nextLine();

                                service.addDriver(new Driver(n, ph, t));
                                System.out.println("Driver added");
                            }

                            else if (a == 2) {
                                sc.nextLine();

                                System.out.print("Vehicle ID: ");
                                String id = sc.nextLine();

                                System.out.print("Type: ");
                                String t = sc.nextLine();

                                System.out.print("Rate: ");
                                double r = sc.nextDouble();

                                service.addVehicle(new Vehicle(id, t, r));
                                System.out.println("Vehicle added");
                            }

                            else {
                                break;
                            }
                        }
                    } else {
                        System.out.println("Invalid login");
                    }
                    break;

                case 4:
                    System.exit(0);
            }
        }
    }
}