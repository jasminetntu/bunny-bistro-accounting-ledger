package com.accounting;

import java.util.Scanner;
import java.time.*;

public class Utility {
    public String boldString(String stringToBold) {
        return "\u001B[1m" + stringToBold + "\u001B[0m";
    }

    public void waitForKey(Scanner scnr) {
        System.out.print("\n> Press enter to continue.");
        scnr.nextLine();
    }

    public void loadingBar() throws InterruptedException {
        for (int i = 0; i < 10; ++i) {
            Thread.sleep(150);
            //System.out.print("𐙚 ₍ᐢ..ᐢ₎ ");
            System.out.print("▒▒▒");
        }
        System.out.println();
        Thread.sleep(200);
    }
}
