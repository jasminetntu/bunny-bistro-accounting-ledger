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
    public static void showHome(Scanner scnr, TransactionList transactionList) {
        boolean isRunning = true;
        HomeOption homeChoice;

        while (isRunning) {
            System.out.print("\n•·················•·················•" +
                    Utility.boldString("\n\t\t\t\tHome") +
                    """
                    
                    •·················•·················•
                    What would you like to do?
                        (D) Add Deposit
                        (P) Make Payment (Debit)
                        (L) View Ledger
                    
                        (X) Exit
                    > Enter choice (D, P, L, X):\s""");

            try {
                homeChoice = HomeOption.fromInputLetter(scnr.nextLine());

                //only proceeds to switch if input is valid
                switch (homeChoice) {
                    case ADD_DEPOSIT -> addDeposit(scnr, transactionList);
                    case MAKE_PAYMENT -> makePayment(scnr, transactionList);
                    case LEDGER -> LedgerScreen.showLedger(scnr, transactionList);
                    case EXIT -> {
                        System.out.println("\n•··· Goodbye! ···•");
                        isRunning = false;
                    }
                }

                if (!homeChoice.equals(HomeOption.EXIT) && !homeChoice.equals(HomeOption.LEDGER)) {
                    Utility.waitForKey(scnr);
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println("Invalid menu choice. Please enter D, P, L, or X.");
            }
        } //end while

    }

    /**
     * Asks user for deposit details and adds to an arraylist of all transactions.
     * @param scnr Scanner object
     * @param transactionList TransactionList object containing arraylist of transactions
     */
    private static void addDeposit(Scanner scnr, TransactionList transactionList) {
        //System.out.println("TEST: addDeposit() entered");
        System.out.print("Enter deposit description: ");
        String description = scnr.nextLine().trim();
        System.out.print("Enter deposit vendor: ");
        String vendor = scnr.nextLine().trim();

        boolean isValid = false;
        double amount = 0;
        while (!isValid) {
            System.out.print("Enter deposit $ amount: ");

            try {
                amount = Double.parseDouble(scnr.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Try again.");
            }
        }

        transactionList.addTransaction(description, vendor, amount);
    }

    /**
     * Asks user for payment details and adds to an arraylist of all transactions.
     * @param scnr Scanner object
     * @param transactionList TransactionList object containing arraylist of transactions
     */
    private static void makePayment(Scanner scnr, TransactionList transactionList) {
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
                amount = -1 * Double.parseDouble(scnr.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Try again.");
            }
        }

        transactionList.addTransaction(description, vendor, amount);
    }
}
