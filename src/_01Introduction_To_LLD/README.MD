# Software Design Principles: DRY, KISS and YAGNI

Software design principles are guidelines that help us write code that is clean, simple, reusable, and easy to maintain.

This note covers three important principles:

1. DRY â€” Don't Repeat Yourself
2. KISS â€” Keep It Simple, Stupid
3. YAGNI â€” You Aren't Gonna Need It

---

## 1. DRY â€” Don't Repeat Yourself

### Definition

Do not write the same logic in multiple places. Write it once in a reusable method and call that method wherever it is needed.

### Simple example

```java
public static double calculateDiscount(double price) {
    return price * 0.10;
}

double mobileDiscount = calculateDiscount(10000);
double laptopDiscount = calculateDiscount(50000);
```

The discount logic is written only once. If the rate changes, we only need to update the `calculateDiscount()` method.

### Benefits

- Reduces duplicate code
- Makes changes easier
- Prevents inconsistent calculations
- Improves reusability

### Easy way to remember

> Write the logic once and reuse it.

---

## 2. KISS â€” Keep It Simple, Stupid

### Definition

Write code in the simplest clear way. Avoid unnecessary conditions, classes, or complicated logic.

### Complicated version

```java
public static boolean isEligible(int age) {
    if (age >= 18) {
        return true;
    } else {
        return false;
    }
}
```

### Simple KISS version

```java
public static boolean isEligible(int age) {
    return age >= 18;
}
```

Both versions give the same result, but the second version is shorter and easier to understand.

### Benefits

- Code is easier to read
- Code is easier to test
- Fewer chances of bugs
- Maintenance becomes easier

### Easy way to remember

> Do not complicate simple logic.

---

## 3. YAGNI â€” You Aren't Gonna Need It

### Definition

Do not create a feature until it is actually required. Avoid writing code only because it might be useful someday.

### Simple example

Suppose the current requirement supports only UPI payments:

```java
class PaymentService {

    public void payUsingUpi() {
        System.out.println("Payment completed using UPI");
    }
}
```

We should not add Bitcoin, card, wallet, or other payment methods until they become real requirements.

### Benefits

- Saves development time
- Keeps the codebase small
- Reduces unnecessary testing
- Avoids maintaining unused code

### Easy way to remember

> Build what is required now, not what might be required later.

### Important note

If Bitcoin payment is already a requirement, adding it does not violate YAGNI. YAGNI only discourages speculative and unnecessary features.

---

## Quick Revision

| Principle | Full form | Main idea | Example |
| --- | --- | --- | --- |
| DRY | Don't Repeat Yourself | Avoid duplicate logic | Use one discount method |
| KISS | Keep It Simple, Stupid | Avoid unnecessary complexity | Return `age >= 18` directly |
| YAGNI | You Aren't Gonna Need It | Avoid unrequired features | Add only required payment methods |

### One-line interview answers

- **DRY:** It prevents duplicate logic by keeping reusable code in one place.
- **KISS:** It encourages developers to choose the simplest clear solution.
- **YAGNI:** It prevents developers from building features before they are actually required.

### Final memory trick

> DRY: Don't duplicate.  
> KISS: Don't complicate.  
> YAGNI: Don't build unnecessarily.