package _02Solid_Principles;

class Bird{
    void eat(){
        System.out.println("Bird is Eating...");
    }
}

class FlyingBird extends Bird{
    void fly(){
        System.out.println("Bird is Flying...");
    }
}

class Sparrow extends  FlyingBird{

}

class Penguin extends Bird{

}
public class _03Liskov_Substitution_Principle {
    public static void main(String[] args) {

        Sparrow s=new Sparrow();
        s.eat();
        s.fly();

        Penguin p=new Penguin();
        p.eat();


    }
}
