/**
 * @author Jasmine Tu
 * Capstone 1 - Accounting Ledger App
 *
 * Entry point.
 */

package com.accounting;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // variables
        Scanner scnr = new Scanner(System.in);
        TransactionList transactionList = new TransactionList();
        Utility util = new Utility();

        //load CSV file of transactions
        transactionList.getTransactionsFromFile();

        //title
        System.out.println("•·················•·················•" +
                util.boldString("\n\t\t\tBunny Bistro") +
                "\n\t\t\t🍵ིྀ༘₍ᐢ. .ᐢ₎₊˚⊹" +
                "\n\t\t> Sales & Purchases <");

        //enter home screen
        HomeScreen hs = new HomeScreen();
        hs.showHome(scnr, transactionList, util);

        //save new transactions when exited
        transactionList.saveTransactionsToFile();
        scnr.close();
    }
}
