package com.accounting;

import java.util.Scanner;
import java.io.*;

public class Menu {
    public static void showHome() {
        Scanner scnr = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.print("""
                    \n•·················•·················•
                    What would you like to do?
                        (D) Add Deposit
                        (P) Make Payment (Debit)
                        (L) Ledger
                        (X) Exit
                    Enter choice (D, P, L, X):\s""");

            MenuOption.Home homeChoice = MenuOption.Home.valueOf(scnr.nextLine().trim().toUpperCase());


        }

    }

    public static void showLedger() {

    }

    public static void showReport() {

    }
}
