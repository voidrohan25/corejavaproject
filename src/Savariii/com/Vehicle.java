package Savariii.com;



class Vehicle {

    private String id;
    private String type;
    private double rate;
    private boolean available = true;

    public Vehicle(String id, String type, double rate) {
        this.id = id;
        this.type = type;
        this.rate = rate;
    }

    public String getId() { return id; }
    public String getType() { return type; }
    public double getRate() { return rate; }

    public boolean isAvailable() { return available; }

    public void setAvailable(boolean b) {
        available = b;
    }
}
