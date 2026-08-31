package _02Solid_Principles;

// Without SRP
class UserServiceWithoutSRP{

    void saveUser(){
        System.out.println("Saved User");
    }

    void sentEmail(){
        System.out.println("Email Sent");
    }
}

//With SRP
class UserService{
    void saveUser(){
        System.out.println("Saved User");
    }
}

class EmailService{
    void sentEmail(){
        System.out.println("Email Sent");
    }
}
public class _01Single_Responsbility_Principle {
    public static void main(String[] args) {

        //Without SRP
        UserServiceWithoutSRP userWithoutSRP=new UserServiceWithoutSRP();
        userWithoutSRP.saveUser();
        userWithoutSRP.sentEmail();


        //With SRP
        UserService user=new UserService();
        user.saveUser();

        EmailService email=new EmailService();
        email.sentEmail();

    }

}
