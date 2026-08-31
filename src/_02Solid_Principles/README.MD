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

