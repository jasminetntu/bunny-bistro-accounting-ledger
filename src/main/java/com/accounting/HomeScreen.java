package com.accounting;

import java.util.Scanner;

public class HomeScreen {
    public static void showHome() {
        Scanner scnr = new Scanner(System.in);
        boolean isRunning = true;
        HomeOption homeChoice = null;

        while (isRunning) {
            System.out.print("""
                    
                    •·················•·················•
                                    Home
                    •·················•·················•
                    > What would you like to do?
                        (D) Add Deposit
                        (P) Make Payment (Debit)
                        (L) View Ledger
                        (X) Exit
                    Enter choice (D, P, L, X):\s""");

            try {
                homeChoice = HomeOption.fromInputLetter(scnr.nextLine());

                //only proceeds to switch if input is valid
                switch (homeChoice) {
                    case ADD_DEPOSIT -> addDeposit();
                    case MAKE_PAYMENT -> makePayment();
                    case LEDGER -> LedgerScreen.showLedger(scnr);
                    case EXIT -> {
                        System.out.println("\n•··· Goodbye! ···•");
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

    private static void addDeposit() {
        System.out.println("TEST: addDeposit() entered");
    }

    private static void makePayment() {
        System.out.println("TEST: makePayment() entered");
    }
}
