# Bunny Bistro 🍵₊˚⊹
> Capstone 1 - Accounting Ledger App
> 
> Year Up United - Technical Academy
> 
> LC 1 - Bay Bytes
---

### 🐰 Description

An accounting ledger app to keep track of deposits & payments of a cafe named "Bunny Bistro." 
Allows you to add deposits, make payments, and view your past transactions based on pre-set or custom filters. 


### 💭 Interesting Piece of Code
``` java
//collect matching results into list to check if empty
List<Transaction> matchingTransactions = tempStream.toList();

if (matchingTransactions.isEmpty()) {
    System.out.println("No matching transactions found.");
}
else { //print results only if there are matching transactions
    matchingTransactions.stream()
        .sorted(SORT_BY_MOST_RECENT)
        .forEach(t -> System.out.println("> " + t));
}
```

insert explanation here

---

## 📸 Screenshots

### Home Screen
insert ss here

### Ledger Screen
insert ss here

### Report Screen
insert ss here

### Display Transaction Example
insert ss here

### Report Example
insert ss here

### CSV File
insert ss here

---
