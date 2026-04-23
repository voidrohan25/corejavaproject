package Savariii.com;

class Driver extends Person {

    private String vehicleType;
    private boolean available;
    private double earnings;

    public Driver(String name, String phone, String type) {
        super(name, phone);
        this.vehicleType = type;
        this.available = true;
        this.earnings = 0;
    }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean b) { available = b; }

    public String getVehicleType() { return vehicleType; }

    public void addEarnings(double amt) {
        earnings += amt;
    }

    public void showEarnings() {
        System.out.println("Total Earnings: " + earnings);
    }
}