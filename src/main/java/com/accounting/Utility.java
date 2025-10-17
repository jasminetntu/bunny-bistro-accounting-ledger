/**
 * @author Jasmine Tu
 * Capstone 1 - Accounting Ledger App
 *
 * Holds random utilities/tools used throughout the rest of the application.
 */
package com.accounting;

import java.util.Scanner;
import java.time.*;

public class Utility {
    /**
     * Returns a bolded version of a String.
     *
     * @param stringToBold the string to bold
     * @return bolded version of String
     */
    public String boldString(String stringToBold) {
        return "\u001B[1m" + stringToBold + "\u001B[0m";
    }

    /**
     * Waits for user to press enter for better menu flow.
     *
     * @param scnr Scanner object
     */
    public void waitForKey(Scanner scnr) {
        System.out.print("\n> Press enter to continue.");
        scnr.nextLine();
    }

    /**
     * Prints a 1500 ms (1.5 s) loading bar.
     * @param loadingMessage the message to print before loading bar
     * @throws InterruptedException if sleep is interrupted
     */
    public void loadingBar(String loadingMessage) throws InterruptedException {
        System.out.println("\n⊹ ࣪ ˖ " + loadingMessage);

        for (int i = 0; i < 10; ++i) {
            Thread.sleep(150);
            System.out.print("▒▒▒");
        }
        System.out.println();
        Thread.sleep(200);
    }
}
