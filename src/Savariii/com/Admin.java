package Savariii.com;



class Admin extends Person {

    private String username;
    private String password;

    public Admin(String name, String phone, String u, String p) {
        super(name, phone);
        this.username = u;
        this.password = p;
    }

    public boolean login(String u, String p) {
        return username.equals(u) && password.equals(p);
    }
}
