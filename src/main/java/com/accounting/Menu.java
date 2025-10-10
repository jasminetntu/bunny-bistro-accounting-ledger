package com.accounting;

import java.util.Scanner;
import java.io.*;

public class Menu {
    public static void showHome() {
        Scanner scnr = new Scanner(System.in);
        boolean isRunning = true;
        HomeOption homeChoice;

        while (isRunning) {
            System.out.print("""
                    \n•·················•·················•
                    What would you like to do?
                        (D) Add Deposit
                        (P) Make Payment (Debit)
                        (L) Ledger
                        (X) Exit
                    Enter choice (D, P, L, X):\s""");

            try {
                homeChoice = HomeOption.fromInputLetter(scnr.nextLine());
            }
            catch (Exception e) {
                System.out.println(e.toString());
            }

            switch (homeChoice) {
                case ADD_DEPOSIT ->
                case MAKE_PAYMENT ->
                case LEDGER ->
                case EXIT -> {
                    System.out.println("Goodbye!");
                }
                default:
                    System.out.println("Invalid input. Please try again.");
            }

        }

    }

    public static void showLedger() {

    }

    public static void showReport() {

    }
}
