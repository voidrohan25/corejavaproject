package Savariii.com;



import java.util.*;

class Passenger extends Person {

    private String username;
    private String password;
    private ArrayList<Booking> history = new ArrayList<>();

    public Passenger(String name, String phone, String u, String p) {
        super(name, phone);
        this.username = u;
        this.password = p;
    }

    public boolean login(String u, String p) {
        return username.equals(u) && password.equals(p);
    }

    public void addBooking(Booking b) {
        history.add(b);
    }

    public void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No rides yet");
        } else {
            for (Booking b : history) {
                b.showDetails();
            }
        }
    }
}
