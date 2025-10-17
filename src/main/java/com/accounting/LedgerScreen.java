/**
 * @author Jasmine Tu
 * Capstone 1 - Accounting Ledger App
 *
 * Handles Ledger Screen operations (display all transactions/deposits/payments, view reports).
 */
package com.accounting;

import java.util.Scanner;

public class LedgerScreen {

    /**
     * Entry point of Ledger Screen.
     * Lists options & calls functions to perform desired option.
     * @param scnr Scanner objects
     * @param transactionList TransactionList object containing arraylist of transactions
     */
    public void showLedger(Scanner scnr, TransactionList transactionList, Utility util) {
        boolean isRunning = true;
        LedgerOption ledgerChoice;

        while (isRunning) {
            System.out.printf("""
                    
                       ∩_∩
                     („• •„)
                    •··U U·············•··················•""" +
                    util.boldString("\n\t\t\t\tLedger") +
                    """
                    
                    •··················•··················•
                    Display...
                        (A) All Transactions
                        (D) Deposits
                        (P) Payments
                        (R) Reports
                    
                        (H) Back to Home
                    > Enter choice (A, D, P, R, H):\s""");

            try {
                ledgerChoice = LedgerOption.fromInputLetter(scnr.nextLine());

                //only proceeds to switch if input is valid
                switch (ledgerChoice) {
                    case DISPLAY_ALL -> {
                        System.out.println(util.boldString("\n⊹ ࣪ ˖ All Transactions\n") + util.tableHeader());
                        transactionList.displayAll();
                        System.out.println(util.separator());
                    }
                    case DISPLAY_DEPOSITS -> {
                        System.out.println(util.boldString("\n⊹ ࣪ ˖ All Deposits\n") + util.tableHeader());
                        transactionList.displayAllDeposits();
                        System.out.println(util.separator());
                    }
                    case DISPLAY_PAYMENTS -> {
                        System.out.println(util.boldString("\n⊹ ࣪ ˖ All Payments\n") + util.tableHeader());
                        transactionList.displayAllPayments();
                        System.out.println(util.separator());
                    }
                    case REPORT -> {
                        util.loadingBar("Accessing reports...");

                        ReportScreen rs = new ReportScreen();
                        rs.showReport(scnr, transactionList, util);
                    }
                    case BACK_HOME -> {
                        util.loadingBar("Returning to home...");
                        isRunning = false;
                    }
                }

                if (!ledgerChoice.equals(LedgerOption.BACK_HOME) && !ledgerChoice.equals(LedgerOption.REPORT)) {
                    util.waitForKey(scnr);
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println("Invalid menu choice. Please enter A, D, P, R, or H.");
            }
            catch (InterruptedException e) {
                System.out.println("Error encountered: Interrupted exception.");
            }
        } //end while

    }
}
