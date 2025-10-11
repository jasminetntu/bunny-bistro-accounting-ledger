package com.accounting;

import java.util.Scanner;

public class LedgerScreen {
    public static void showLedger(Scanner scnr) {
        final String BOLD_START = "\u001B[1m";
        final String BOLD_END = "\u001B[0m";

        boolean isRunning = true;
        LedgerOption ledgerChoice = null;

        while (isRunning) {
            System.out.printf("""
                    
                    •·················•·················•
                                    %sLedger%s
                    •·················•·················•
                    Display...
                        (A) All Transactions
                        (D) Deposits
                        (P) Payments
                        (R) Reports
                    
                        (H) Back to Home
                    > Enter choice (A, D, P, R, H):\s""", BOLD_START, BOLD_END);

            try {
                ledgerChoice = LedgerOption.fromInputLetter(scnr.nextLine());

                //only proceeds to switch if input is valid
                switch (ledgerChoice) {
                    case DISPLAY_ALL -> displayAll();
                    case DISPLAY_DEPOSITS -> displayDeposits();
                    case DISPLAY_PAYMENTS -> displayPayments();
                    case DISPLAY_REPORTS -> ReportScreen.showReport(scnr);
                    case BACK_HOME -> {
                        System.out.println("\n•··· Returning to home... ···•");
                        isRunning = false;
                    }
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println("Invalid menu choice. Please enter A, D, P, R, or H.");
            }
        } //end while

    }

    private static void displayAll() {
        System.out.println("TEST: displayAll() entered");
    }

    private static void displayDeposits() {
        System.out.println("TEST: displayDeposits() entered");
    }

    private static void displayPayments() {
        System.out.println("TEST: displayPayments() entered");
    }


}
