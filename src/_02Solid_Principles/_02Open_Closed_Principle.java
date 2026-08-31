package _02Solid_Principles;


interface TaxCalculator{
    double calculateAfterTax(double amount);
}

class IndianTax implements TaxCalculator{

    public double calculateAfterTax(double amount){
        return (amount + 0.18*amount);
    }
}

class UsTax implements TaxCalculator{
    public double calculateAfterTax(double amount){
        return (amount + 0.10*amount);
    }
}
public class _02Open_Closed_Principle {
    public static void main(String[] args) {

        //Indian Tax
        TaxCalculator india=new IndianTax();
        System.out.println(india.calculateAfterTax(1000));

        //Us Tax
        TaxCalculator us=new UsTax();
        System.out.println(us.calculateAfterTax(1000));



    }
}
