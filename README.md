# Bunny Bistro Accounting Ledger 🍵₊˚⊹
> **Capstone 1: Accounting Ledger App**
> 
> Year Up United (Bay Bytes) - _Technical Academy_
---

## 🐰 Description

An accounting ledger app interactable through the console, designed for a fictional cafe named "Bunny Bistro."
This tool helps the cafe efficiently track their financial activity.

Built entirely in Java to practice application development, specifically to gain familiarity with streams and enums.

### 🗝️ Key Features

- **Transaction Management**: Easily input new deposits and outgoing payments.

- **Filtering**: View past transactions using pre-set common filters or custom parameters.

- **Transaction History**: Provides a clear history of all financial activity, stored inside a CSV file.


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

🌟 This code snippet shows how I handled the edge case in which there are no matching transactions.

In my first attempt, I checked whether the `tempStream` variable was empty or not inside the `if` condition. 
However, doing so closed the stream, causing an error to be thrown in the case where the stream was NOT empty.
This is because the `else` statement would try to read from a stream that was already closed due to me checking its elements.

To fix this, I collected the filtered stream into a list using `toList()`, then checked if the list was empty.
Even though `toList()` closed my stream, storing it inside a list allowed me to restore it by creating a new stream from the list.

I thought this was interesting because it was something I struggled with during the programming process, since I was unfamiliar with streams.
It was both challenging and fun to find a workaround to checking whether my stream is empty or not.

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
