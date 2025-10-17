/**
 * @author Jasmine Tu
 * Capstone 1 - Accounting Ledger App
 *
 * Handles Home Screen operations (add deposit, make payment, view ledger).
 */
package com.accounting;

import java.util.Scanner;

public class HomeScreen {

    /**
     * Entry point of Home Screen.
     * Lists options & calls functions to perform desired option.
     * @param scnr Scanner objects
     * @param transactionList TransactionList object containing arraylist of transactions
     */
    public void showHome(Scanner scnr, TransactionList transactionList, Utility util) {
        boolean isRunning = true;
        HomeOption homeChoice;

        while (isRunning) {
            System.out.print("""
                    
                       ∩_∩
                     („• •„)
                    •··U U·············•··················•""" +
                    util.boldString("\n\t\t\t\tHome 🌸") +
                    """
                    
                    •··················•··················•
                    What would you like to do?
                        (D) 🍀 Add Deposit
                        (P) 🍵 Make Payment (Debit)
                        (L) 📝 View Ledger
                    
                        (X) 👋 Exit
                    > Enter choice (D, P, L, X):\s""");

            try {
                homeChoice = HomeOption.fromInputLetter(scnr.nextLine());

                //only proceeds to switch if input is valid
                switch (homeChoice) {
                    case ADD_DEPOSIT -> addDeposit(scnr, transactionList);
                    case MAKE_PAYMENT -> makePayment(scnr, transactionList);
                    case LEDGER -> {
                        util.loadingBar("Accessing ledger...");

                        LedgerScreen ls = new LedgerScreen();
                        ls.showLedger(scnr, transactionList, util);
                    }
                    case EXIT -> {
                        util.loadingBar("Exiting...");
                        System.out.println("""
                                      
                                       ⊹ ࣪ ˖ Goodbye! ˖ ࣪ ⊹

                                        (\\_(\\     /)_/)
                                        (    )   (    )
                                       /    |     |    \\
                                      ( O    )   (    O )""");
                        isRunning = false;
                    }
                }

                if (!homeChoice.equals(HomeOption.EXIT) && !homeChoice.equals(HomeOption.LEDGER)) {
                    util.waitForKey(scnr);
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid menu choice. Please enter D, P, L, or X.");
            }
            catch (InterruptedException e) {
                System.out.println("Error encountered: Interrupted exception.");
            }
        } //end while

    }

    /**
     * Asks user for deposit details and adds to an arraylist of all transactions.
     * @param scnr Scanner object
     * @param transactionList TransactionList object containing arraylist of transactions
     */
    private void addDeposit(Scanner scnr, TransactionList transactionList) {
        //System.out.println("TEST: addDeposit() entered");
        String description = "";
        String vendor = "";
        double amount = 0;

        boolean isValid = false;
        while (!isValid) {
            System.out.print("Enter deposit description: ");
            description = scnr.nextLine().trim();

            if (description.isEmpty()) {
                System.out.println("Description cannot be empty. Try again.");
            }
            else {
                isValid = true;
            }
        }

        isValid = false;
        while (!isValid) {
            System.out.print("Enter deposit vendor: ");
            vendor = scnr.nextLine().trim();

            if (vendor.isEmpty()) {
                System.out.println("Vendor cannot be empty. Try again.");
            }
            else {
                isValid = true;
            }
        }

        isValid = false;
        while (!isValid) {
            System.out.print("Enter deposit $ amount: ");

            try {
                amount = Double.parseDouble(scnr.nextLine());

                if (amount < 0) {
                    System.out.println("❌ Invalid amount. Must be a positive number.");
                }
                else {
                    isValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid amount. Must be a positive number.");
            }
        }

        transactionList.addTransaction(description, vendor, amount);
    }

    /**
     * Asks user for payment details and adds to an arraylist of all transactions.
     * @param scnr Scanner object
     * @param transactionList TransactionList object containing arraylist of transactions
     */
    private void makePayment(Scanner scnr, TransactionList transactionList) {
        //System.out.println("TEST: makePayment() entered");

        System.out.print("Enter payment description: ");
        String description = scnr.nextLine().trim();
        System.out.print("Enter recipient of payment: ");
        String vendor = scnr.nextLine().trim();

        boolean isValid = false;
        double amount = 0;
        while (!isValid) {
            System.out.print("Enter payment $ amount: ");

            try {
                amount = Double.parseDouble(scnr.nextLine());

                if (amount < 0) {
                    System.out.println("❌ Invalid amount. Must be a positive number.");
                }
                else {
                    amount *= -1;
                    isValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid amount. Must be a positive number.");
            }
        }

        transactionList.addTransaction(description, vendor, amount);
    }
}
