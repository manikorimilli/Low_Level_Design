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