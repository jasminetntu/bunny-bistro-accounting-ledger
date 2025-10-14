/**
 * @author Jasmine Tu
 * Capstone 1 - Accounting Ledger App
 *
 * Handles Home Screen operations (add deposit, make payment, view ledger).
 */
package com.accounting;

import java.util.Scanner;

public class HomeScreen {
    public static void showHome() {
        final String FILE_PATH = "src/main/resources/transactions.csv";
        final String BOLD_START = "\u001B[1m";
        final String BOLD_END = "\u001B[0m";

        Scanner scnr = new Scanner(System.in);
        TransactionList transactionList = new TransactionList();
        transactionList.loadFromCsv(FILE_PATH);

        boolean isRunning = true;
        HomeOption homeChoice;

        while (isRunning) {
            System.out.printf("""
                    
                    •·················•·················•
                                    %sHome%s
                    •·················•·················•
                    What would you like to do?
                        (D) Add Deposit
                        (P) Make Payment (Debit)
                        (L) View Ledger
                    
                        (X) Exit
                    > Enter choice (D, P, L, X):\s""", BOLD_START, BOLD_END);

            try {
                homeChoice = HomeOption.fromInputLetter(scnr.nextLine());

                //only proceeds to switch if input is valid
                switch (homeChoice) {
                    case ADD_DEPOSIT -> addDeposit(scnr, transactionList);
                    case MAKE_PAYMENT -> makePayment(scnr, transactionList);
                    case LEDGER -> LedgerScreen.showLedger(scnr, transactionList);
                    case EXIT -> {
                        System.out.println("\n•··· Goodbye! ···•");
                        transactionList.saveToCsv(FILE_PATH);
                        isRunning = false;
                        scnr.close();
                    }
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println("Invalid menu choice. Please enter D, P, L, or X.");
            }
        } //end while

    }

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
