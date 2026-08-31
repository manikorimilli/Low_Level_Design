# SOLID Principles in Java

SOLID is a set of five principles used to write clean and maintainable object-oriented code.

| Letter | Principle |
| --- | --- |
| S | Single Responsibility Principle |
| O | Open/Closed Principle |
| L | Liskov Substitution Principle |
| I | Interface Segregation Principle |
| D | Dependency Inversion Principle |

---

# 1. S — Single Responsibility Principle (SRP)

## Definition

> A class should have only one reason to change.

In simple words, a class should have only **one job or responsibility**.

If a class performs multiple jobs, changing one job may affect the others and make the code difficult to maintain.

## Real-Life Analogy

Imagine a chef who cooks, cleans, serves food, and orders groceries. The chef has too many responsibilities.

It is better to give each person one job:

- **Chef** — Cooks food
- **Cleaner** — Cleans the kitchen
- **Waiter** — Serves food
- **Manager** — Orders groceries

Similarly, each class should focus on only one responsibility.

---

## Java Example

```java
package _02Solid_Principles;

// Without SRP: One class has two responsibilities
class UserServiceWithoutSRP {

    void saveUser() {
        System.out.println("Saved User");
    }

    void sendEmail() {
        System.out.println("Email Sent");
    }
}

// With SRP: Only saves users
class UserService {

    void saveUser() {
        System.out.println("Saved User");
    }
}

// With SRP: Only sends emails
class EmailService {

    void sendEmail() {
        System.out.println("Email Sent");
    }
}

public class _01Single_Responsibility_Principle {

    public static void main(String[] args) {

        // Without SRP
        UserServiceWithoutSRP userWithoutSRP =
                new UserServiceWithoutSRP();

        userWithoutSRP.saveUser();
        userWithoutSRP.sendEmail();

        // With SRP
        UserService userService = new UserService();
        userService.saveUser();

        EmailService emailService = new EmailService();
        emailService.sendEmail();
    }
}
```

## Explanation

### Without SRP

`UserServiceWithoutSRP` performs two jobs:

- Saves the user
- Sends an email

### With SRP

- `UserService` only saves the user.
- `EmailService` only sends emails.

Now, changes to email logic will not affect user-saving logic.

---

## Advantages

- Easier to understand
- Easier to maintain
- Easier to test
- Reduces unexpected bugs

## Quick Revision

> **One class = One responsibility = One reason to change**

---

# Open/Closed Principle (OCP)

The **O** in SOLID stands for the **Open/Closed Principle**.

## Definition

> Software entities should be open for extension but closed for modification.

In simple words, we should be able to **add new functionality without changing existing working code**.

---

## Real-Life Analogy: Tax Calculation

Imagine an online shopping application that calculates tax based on the customer's country:

- India has 18% tax.
- The US has 10% tax.

Later, if the application starts operating in the UK, it must support UK tax.

Instead of changing the existing India and US tax code, we create a new `UKTax` class. This adds new functionality without modifying existing working classes.

This follows the Open/Closed Principle:

- **Open for extension:** We can add tax calculations for new countries.
- **Closed for modification:** Existing tax classes do not need to be changed.

---

## Without OCP — Bad Practice

In this example, tax calculations for every country are written inside one method:

```java
class InvoiceProcessor {

    public double calculateTotal(String region, double amount) {

        if (region.equalsIgnoreCase("India")) {
            return amount + (amount * 0.18);
        } else if (region.equalsIgnoreCase("US")) {
            return amount + (amount * 0.08);
        } else if (region.equalsIgnoreCase("UK")) {
            return amount + (amount * 0.12);
        } else {
            return amount;
        }
    }
}
```

This violates OCP because adding a new country requires modifying the existing `calculateTotal()` method. More countries will also create more `if-else` conditions.

---

## With OCP — Good Practice

```java
package _02Solid_Principles;

interface TaxCalculator {
    double calculateAfterTax(double amount);
}

class IndianTax implements TaxCalculator {

    public double calculateAfterTax(double amount) {
        return amount + (0.18 * amount);
    }
}

class USTax implements TaxCalculator {

    public double calculateAfterTax(double amount) {
        return amount + (0.10 * amount);
    }
}

public class _02Open_Closed_Principle {

    public static void main(String[] args) {

        // Indian Tax
        TaxCalculator india = new IndianTax();
        System.out.println(india.calculateAfterTax(1000));

        // US Tax
        TaxCalculator us = new USTax();
        System.out.println(us.calculateAfterTax(1000));
    }
}
```

## Output

```text
1180.0
1100.0
```

---

## Explanation

- `TaxCalculator` defines a common method for calculating the final amount.
- `IndianTax` provides the Indian tax calculation.
- `USTax` provides the US tax calculation.
- New countries can be supported by creating new classes.

For example, UK tax can be added like this:

```java
class UKTax implements TaxCalculator {

    public double calculateAfterTax(double amount) {
        return amount + (0.12 * amount);
    }
}
```

No changes are required in `IndianTax`, `USTax`, or `TaxCalculator`.

---

## Advantages

- New functionality can be added easily.
- Existing working code remains unchanged.
- Reduces the risk of breaking old features.
- Makes the code easier to maintain and test.

---

## Quick Revision

> **Open for extension:** New functionality can be added.
>
> **Closed for modification:** Existing working code should not be changed.

### Easy Formula

> **Add a new class instead of changing an existing class.**
> 
---

 


# Liskov Substitution Principle (LSP)

The **L** in SOLID stands for the **Liskov Substitution Principle**.

## Definition

> A child class should be able to replace its parent class without breaking the program.

In simple words, a subclass should correctly support the behaviour it inherits from its parent.

---

## Real-Life Analogy: Birds

All birds can eat, but not all birds can fly:

- A **sparrow** can eat and fly.
- A **penguin** can eat but cannot fly.

Therefore, `fly()` should not be placed inside the general `Bird` class. Otherwise, `Penguin` would inherit a behaviour it cannot perform.

---

## Without LSP — Bad Practice

```java
class Bird {

    void eat() {
        System.out.println("Bird is Eating...");
    }

    void fly() {
        System.out.println("Bird is Flying...");
    }
}

class Penguin extends Bird {

    @Override
    void fly() {
        throw new UnsupportedOperationException(
                "Penguin cannot fly"
        );
    }
}
```

This violates LSP because replacing `Bird` with `Penguin` breaks the program:

```java
Bird bird = new Penguin();
bird.fly(); // Runtime error
```

---

## With LSP — Good Practice

```java
package _02Solid_Principles;

class Bird {

    void eat() {
        System.out.println("Bird is Eating...");
    }
}

class FlyingBird extends Bird {

    void fly() {
        System.out.println("Bird is Flying...");
    }
}

class Sparrow extends FlyingBird {

}

class Penguin extends Bird {

}

public class _03Liskov_Substitution_Principle {

    public static void main(String[] args) {

        Sparrow sparrow = new Sparrow();
        sparrow.eat();
        sparrow.fly();

        Penguin penguin = new Penguin();
        penguin.eat();
    }
}
```

## Output

```text
Bird is Eating...
Bird is Flying...
Bird is Eating...
```

---

## Explanation

- `Bird` contains only the common `eat()` behaviour.
- `FlyingBird` adds the `fly()` behaviour.
- `Sparrow` extends `FlyingBird`, so it can eat and fly.
- `Penguin` extends `Bird`, so it can eat but does not have `fly()`.

Now both child classes correctly support the behaviour inherited from their parent classes.

---

## Advantages

- Prevents unexpected runtime errors.
- Creates correct inheritance relationships.
- Makes the code easier to understand and maintain.
- Child objects can safely replace parent objects.

---

## Quick Revision

> **A child class should behave correctly wherever its parent class is expected.**

### Easy Formula

> **If a child cannot support a parent's behaviour, it should not inherit that behaviour.**




 # Interface Segregation Principle (ISP)

The **I** in SOLID stands for the **Interface Segregation Principle**.

## Definition

> A class should not be forced to implement methods that it does not need.

In simple words, create **small and specific interfaces** instead of one large interface containing unrelated methods.

---

## Real-Life Analogy: Uber

Uber has two types of users with different requirements:

### Rider

- Books a ride
- Tracks the driver
- Makes payment
- Rates the driver

### Driver

- Accepts a ride
- Starts the ride
- Tracks earnings
- Rates the passenger

A rider does not need driver features, and a driver does not need rider features. Therefore, they should not be forced to use the same large interface.

---

## Without ISP — Bad Practice

One large interface contains both rider and driver operations:

```java
interface UberUser {

    void bookRide();
    void trackDriver();
    void makePayment();

    void acceptRide();
    void startRide();
    void trackEarnings();
}

class Rider implements UberUser {

    public void bookRide() {
        System.out.println("Rider booked a ride...");
    }

    public void trackDriver() {
        System.out.println("Rider is tracking the driver...");
    }

    public void makePayment() {
        System.out.println("Rider made the payment...");
    }

    public void acceptRide() {
        // Not required for Rider
    }

    public void startRide() {
        // Not required for Rider
    }

    public void trackEarnings() {
        // Not required for Rider
    }
}
```

The `Rider` is forced to implement driver methods it does not need. Similarly, a `Driver` would be forced to implement rider methods.

This violates the Interface Segregation Principle.

---

## With ISP — Good Practice

We divide the large interface into two smaller interfaces:

```java
package _02Solid_Principles;

interface RiderActions {

    void bookRide();
    void trackDriver();
    void makePayment();
    void rateDriver();
}

interface DriverActions {

    void acceptRide();
    void startRide();
    void trackEarnings();
    void ratePassenger();
}

class Rider implements RiderActions {

    public void bookRide() {
        System.out.println("Rider booked a ride...");
    }

    public void trackDriver() {
        System.out.println("Rider is tracking the driver...");
    }

    public void makePayment() {
        System.out.println("Rider made the payment...");
    }

    public void rateDriver() {
        System.out.println("Rider rated the driver...");
    }
}

class Driver implements DriverActions {

    public void acceptRide() {
        System.out.println("Driver accepted the ride...");
    }

    public void startRide() {
        System.out.println("Driver started the ride...");
    }

    public void trackEarnings() {
        System.out.println("Driver is checking earnings...");
    }

    public void ratePassenger() {
        System.out.println("Driver rated the passenger...");
    }
}

public class _04Interface_Segregation_Principle {

    public static void main(String[] args) {

        Rider rider = new Rider();
        rider.bookRide();
        rider.trackDriver();
        rider.makePayment();
        rider.rateDriver();

        Driver driver = new Driver();
        driver.acceptRide();
        driver.startRide();
        driver.trackEarnings();
        driver.ratePassenger();
    }
}
```

## Output

```text
Rider booked a ride...
Rider is tracking the driver...
Rider made the payment...
Rider rated the driver...
Driver accepted the ride...
Driver started the ride...
Driver is checking earnings...
Driver rated the passenger...
```

---

## Explanation

- `RiderActions` contains only rider-related methods.
- `DriverActions` contains only driver-related methods.
- `Rider` implements only `RiderActions`.
- `Driver` implements only `DriverActions`.

Now, neither class is forced to implement unnecessary methods.

---

## Advantages

- Avoids unnecessary methods.
- Makes interfaces easier to understand.
- Makes classes easier to maintain and test.
- Reduces the effect of interface changes.

---

## Quick Revision

> **Clients should not be forced to depend on methods they do not use.**

### Easy Formula

> **Create multiple small interfaces instead of one large interface.**





 # Dependency Inversion Principle (DIP)

The **D** in SOLID stands for the **Dependency Inversion Principle**.

## Definition

> High-level classes should not directly depend on low-level classes. Both should depend on an abstraction, such as an interface.

In simple words, a class should depend on an **interface** instead of directly depending on a specific class.

---

## Real-Life Analogy: Sending Notifications

An application may send notifications through:

- Email
- SMS
- WhatsApp

The main notification service should not be permanently connected to only email. It should work with any notification method.

---

## Without DIP — Bad Practice

```java
class EmailSender {

    void sendMessage() {
        System.out.println(
                "Notification sent through Email..."
        );
    }
}

class NotificationService {

    private EmailSender emailSender = new EmailSender();

    void sendNotification() {
        emailSender.sendMessage();
    }
}
```

`NotificationService` directly depends on `EmailSender`.

To use SMS, we must modify the `NotificationService` class. This creates tight coupling and violates DIP.

---

## With DIP — Good Practice

```java
package _02Solid_Principles;

interface MessageSender {

    void sendMessage();
}

class EmailSender implements MessageSender {

    public void sendMessage() {
        System.out.println(
                "Notification sent through Email..."
        );
    }
}

class SmsSender implements MessageSender {

    public void sendMessage() {
        System.out.println(
                "Notification sent through SMS..."
        );
    }
}

class NotificationService {

    private MessageSender messageSender;

    NotificationService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    void sendNotification() {
        messageSender.sendMessage();
    }
}

public class _05Dependency_Inversion_Principle {

    public static void main(String[] args) {

        MessageSender email = new EmailSender();
        NotificationService emailService =
                new NotificationService(email);

        emailService.sendNotification();

        MessageSender sms = new SmsSender();
        NotificationService smsService =
                new NotificationService(sms);

        smsService.sendNotification();
    }
}
```

## Output

```text
Notification sent through Email...
Notification sent through SMS...
```

---

## Explanation

- `MessageSender` is the abstraction.
- `EmailSender` and `SmsSender` are implementations.
- `NotificationService` depends on `MessageSender`, not on a specific sender.
- The required sender is passed through the constructor.

Passing a dependency from outside through a constructor is called **Dependency Injection**.

We can add WhatsApp notifications by creating another class that implements `MessageSender`. The `NotificationService` class does not need to change.

---

## Advantages

- Reduces tight coupling.
- Makes implementations easy to replace.
- Makes code easier to extend and test.
- Existing high-level code requires fewer changes.

---

## Quick Revision

> **Depend on abstractions, not concrete classes.**

### Easy Formula

> **Class → Interface → Implementation**