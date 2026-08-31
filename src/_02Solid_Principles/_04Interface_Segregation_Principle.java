package _02Solid_Principles;


//Without ISP
interface UberServices{

    void bookRider();
    void makePayment();
    void rateDriver();

    void acceptRide();
    void startRide();
    void trackpasanger();

}

//With ISP
interface RiderActions{
    void bookRider();
    void makePayment();
    void rateDriver();
}

interface DriverActions{
    void acceptRide();
    void startRide();
    void trackPasanger();

}

//class Rider implements RiderActions{
//    //Implements it's corresponding actions
//}

//class Driver implements DriverActions{
//    //Implements it's corresponding actions
//}
public class _04Interface_Segregation_Principle {
    public static void main(String[] args) {

    }
}
