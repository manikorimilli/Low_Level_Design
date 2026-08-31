package _02Solid_Principles;

interface MessageSender{
    void sendMessage();
}

class EmailSender implements MessageSender{

    public void sendMessage(){
        System.out.println("Notification sent through Email");
    }
}

class SmsSender implements  MessageSender{
    public void sendMessage(){
        System.out.println("Notification sent through SMS");
    }
}

class NotificationService{

   private MessageSender message;

   NotificationService(MessageSender message){
       this.message=message;
   }

   void sendNotification(){
       this.message.sendMessage();
   }
}
public class _05Dependency_Inversion_Principle {
    public static void main(String[] args) {

        MessageSender email=new EmailSender();
        NotificationService notification=new NotificationService(email);
        notification.sendNotification();

        MessageSender sms=new SmsSender();
        NotificationService notification2=new NotificationService(sms);
        notification2.sendNotification();



    }
}
