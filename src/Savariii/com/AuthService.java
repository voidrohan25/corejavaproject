package Savariii.com;



import java.util.Random;


class AuthService {

    private int otp;

    public void generateOTP() {
        Random r = new Random();
        otp = 1000 + r.nextInt(9000);

       

        System.out.println("OTP: " + otp);
    }

    public boolean verify(int userOtp) {
        return otp == userOtp;
    }
}
