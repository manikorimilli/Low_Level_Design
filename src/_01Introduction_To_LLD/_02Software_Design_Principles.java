package _01Introduction_To_LLD;

// YAGNI: Currently, only UPI payment is required.
// Bitcoin payment can be added later when required.
class PaymentService {

    public void payUsingUpi() {
        System.out.println("Payment completed using UPI");
    }
}

public class _02Software_Design_Principles {

    private static final double DISCOUNT_RATE = 0.10;

    // DRY: Don't Repeat Yourself
    public static double calculateDiscount(double price) {
        return price * DISCOUNT_RATE;
    }

    // KISS: Keep It Simple, Stupid
    public static boolean isEligible(int age) {
        return age >= 18;
    }

    public static void main(String[] args) {

        // DRY example
        double mobilePrice = 10000;
        double mobileFinalPrice =
                mobilePrice - calculateDiscount(mobilePrice);

        double laptopPrice = 50000;
        double laptopFinalPrice =
                laptopPrice - calculateDiscount(laptopPrice);

        System.out.println("Mobile final price: ₹" + mobileFinalPrice);
        System.out.println("Laptop final price: ₹" + laptopFinalPrice);

        // KISS example
        int age = 20;
        System.out.println("Eligible: " + isEligible(age));

        // YAGNI example
        PaymentService paymentService = new PaymentService();
        paymentService.payUsingUpi();
    }
}