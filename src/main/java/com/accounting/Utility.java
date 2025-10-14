package com.accounting;

import java.util.Scanner;

public class Utility {
    public static String boldString(String stringToBold) {
        return "\u001B[1m" + stringToBold + "\u001B[0m";
    }

    public static void waitForKey(Scanner scnr) {
        System.out.print("\nPress enter when you're ready to continue.");
        scnr.nextLine();
    }
}
